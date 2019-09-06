package com.openmatics.test.functional.platform.servicemanagement_mc;


import com.microsoft.azure.servicebus.ISubscriptionClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.test.functional.platform.servicemanagement_mc.util.DestinationTestUtil;
import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.SmsmcApiProvider;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.DestinationChangeHandler;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Destination;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.DestinationChanged;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.DestinationType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Direction;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceDescriptorRepository;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceUsesClientDestinationRepository;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceUsesDestinationRepository;
import com.openmatics.testinstrumentation.service.smsmc.CusService;
import com.openmatics.testinstrumentation.service.smsmc.SagaService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceDescriptorService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceUsesClientDestinationService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceUsesDestinationService;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import org.apache.commons.lang3.BooleanUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.openmatics.test.functional.platform.servicemanagement_mc.util.DestinationTestUtil.*;
import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_MC_DESTINATION_CHANGE;
import static org.assertj.core.api.Java6Assertions.assertThat;




@Test(testName = "ServiceStartedAcceptanceTest")
public class ServiceStartedAcceptanceTest extends DbBaseTest {
    private static final String SUBSCRIPTION = "automated-test";
    private static final String APP_ID_SERVICE_NAME = "multiclient-test-service";
    private static final int SLEEP_S = 5;
    private static final int ATTEMPT = 10;

    private SmsmcApiProvider smsService;
    private ServiceBusDestinationsHandler serviceBusHandler;
    private ServiceBusClient serviceBusClient;
    private ServiceDescriptorService serviceDescriptorService;
    private ServiceUsesDestinationService serviceUsesDestinationService;
    private ServiceUsesClientDestinationService serviceUsesClientDestinationService;
    private SagaService sagaService;
    private CusService cusService;

    private String appId;
    private String clientId;
    private ServiceDescriptor serviceDescriptor;
    private DestinationChangeHandler destinationChangeHandler;
    private ISubscriptionClient subscriptionClient;
    private List<Destination> clientUsesCreatedDestination = new ArrayList<>();


    @BeforeClass
    public void beforeServiceBusCreationAcceptanceTest() throws Exception {
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration(APP_ID_SERVICE_NAME, platformInstrumentation.getEnvConf());
        appId = serviceConfiguration.getApplicationClientId();

        smsService = new SmsmcApiProvider(platformInstrumentation);
        ServiceDescriptorRepository serviceDescriptorRepository = new ServiceDescriptorRepository(connectionFactory);
        ServiceUsesDestinationRepository serviceUsesDestinationRepository = new ServiceUsesDestinationRepository(connectionFactory);
        ServiceUsesClientDestinationRepository serviceUsesClientDestinationRepository = new ServiceUsesClientDestinationRepository(connectionFactory);
        serviceBusHandler = new ServiceBusDestinationsHandler(platformInstrumentation.getEnvConf().getServiceBusProperty());
        serviceBusClient = new ServiceBusClient(platformInstrumentation.getEnvConf().getServiceBusProperty().getConnectionString());
        serviceUsesDestinationService = new ServiceUsesDestinationService(serviceUsesDestinationRepository, serviceBusHandler);
        serviceUsesClientDestinationService = new ServiceUsesClientDestinationService(serviceUsesClientDestinationRepository);
        serviceDescriptorService = new ServiceDescriptorService(serviceDescriptorRepository, smsService.getEventApi().getSmsmcServiceEventApi(), smsService.getRestApi().getServiceApi());
        sagaService = new SagaService(smsService.getRestApi().getSagaApi());
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        cusService = new CusService(smsService.getEventApi().getCusApi());
        clientId = platformInstrumentation.getClientConf().getClient().getId();

        deleteEntities();
    }

    @AfterClass
    public void afterServiceBusCreationAcceptanceTest() throws ServiceBusException {
        deleteEntities();
    }

    /**
     * Load service descriptor
     */
    @Test
    public void givenServiceDescriptor() {
        String sagaId = serviceDescriptorService.saveServiceDescriptor(GROUP_ID, ARTIFACT_ID, appId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.SERVICE_DESCRIPTOR, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaId + "' about saving service descriptor success?").isTrue();
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        System.out.println("Test is using service descriptor: " + serviceDescriptor);
    }

    /**
     * Check if the destination doesn't exist in the service bus.
     */
    @Test(dependsOnMethods = "givenServiceDescriptor")
    public void givenDestinationNotExist() {
        assertThat(serviceUsesDestinationService.getDestinationNamesByServiceDescriptorId(serviceDescriptor.getId())).isEmpty();
        getMtDestination(getDefaultDestinations()).forEach(destination -> assertThat(serviceUsesDestinationService.existDestinationInServiceBus(destination,
                BooleanUtils.isNotTrue(destination.getSingleClient()) ? serviceDescriptor.getId() : destination.getId())).isFalse());
    }

    /**
     * Assign service descriptor to the client and wait to the saga status. Saga status has to be SUCCESS.
     *
     * @throws Exception assignment fail
     */
    @Test(dependsOnMethods = "givenDestinationNotExist")
    public void givenClientUsesService() throws Exception {
        String sagaId = cusService.assignServiceToClient(serviceDescriptor.getId(), clientId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.CLIENT_USES_SERVICE, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaId + "' about saving service descriptor success?").isTrue();
    }

    /**
     * Destination change listener initialization.
     *
     * @throws Exception initialization failed
     */
    @Test(dependsOnMethods = "givenClientUsesService")
    public void givenDestinationChangeListener() throws Exception {
        serviceBusHandler.ensureSubscriptionCreation(SMS_MC_DESTINATION_CHANGE, SUBSCRIPTION);
        destinationChangeHandler = new DestinationChangeHandler(serviceDescriptor.getGroupId(), serviceDescriptor.getArtifactId());
        subscriptionClient = serviceBusClient.receiveTopicMessage(SMS_MC_DESTINATION_CHANGE, SUBSCRIPTION, destinationChangeHandler);
    }

    /**
     * Send service started
     */
    @Test(dependsOnMethods = "givenDestinationChangeListener")
    public void whenServiceStartedEvent() {
        smsService.getEventApi().getSmsmcServiceEventApi().sendServiceStartedMC(GROUP_ID, ARTIFACT_ID, DestinationTestUtil.getDefaultDestinations());
    }

    /**
     * Waiting for destination change event and comparing the payload of the event.
     *
     * @throws InterruptedException Interrupting during the sleeping
     */
    @Test(dependsOnMethods = "whenServiceStartedEvent")
    public void thenDestinationChangeReceived() throws InterruptedException {
        DestinationChanged destinationChanged = waitForDestinationChangeEvent();
        List<Destination> expectedDestination = getMtDestination(DestinationTestUtil.getDefaultDestinations());
        expectedDestination.addAll(getStDestination(DestinationTestUtil.getDefaultDestinations(), clientId));
        List<Destination> createdDestination = destinationChanged.getChanges().getOrDefault(Operation.CREATE, new ArrayList<>());
        assertThat(createdDestination).isNotEmpty();
        assertThat(createdDestination).hasSize(expectedDestination.size());
        expectedDestination.forEach(destination ->
                assertThat(createdDestination
                        .stream()
                        .anyMatch(destination1 -> destination1.getNameIdentifier().equalsIgnoreCase(destination.getNameIdentifier())))
                        .isTrue()
        );
        clientUsesCreatedDestination.addAll(createdDestination.stream().filter(destination -> BooleanUtils.isTrue(destination.getSingleClient())).collect(Collectors.toList()));
    }

    /**
     * Check if the destination exist in the DB and service bus.
     */
    @Test(dependsOnMethods = "thenDestinationChangeReceived")
    public void thenDestinationExists() {
        List<Destination> expected = DestinationTestUtil.getDefaultDestinations();
        checkServiceUseDestinationExistence(expected);
    }


    /**
     * Send service started again.
     * Any destination are added, any destination are removed
     */
    @Test(dependsOnMethods = "thenDestinationExists")
    public void whenServiceStartedEventAgain() {
        smsService.getEventApi().getSmsmcServiceEventApi().sendServiceStartedMC(GROUP_ID, ARTIFACT_ID, getDefaultDestinationsRemoved().get(CREATED));
    }

    /**
     * Waiting for destination change event and comparing the payload of the event.
     *
     * @throws InterruptedException Interrupting during the sleeping
     */
    @Test(dependsOnMethods = "whenServiceStartedEventAgain")
    public void thenDestinationChangeReceivedAgain() throws InterruptedException {
        DestinationChanged destinationChanged = waitForDestinationChangeEvent();
        Map<String, List<Destination>> destinationsMap = getDefaultDestinationsRemoved();
        List<Destination> createdDestination = destinationChanged.getChanges().getOrDefault(Operation.CREATE, new ArrayList<>());
        List<Destination> expectedDestination = getMtDestination(destinationsMap.get(ADDED));
        expectedDestination.addAll(getStDestination(destinationsMap.get(ADDED), clientId));
        assertThat(createdDestination).isNotEmpty();
        assertThat(createdDestination).hasSize(expectedDestination.size());

        // currently the destination change contains only added destination.
        expectedDestination.forEach(destination ->
                assertThat(createdDestination
                        .stream()
                        .anyMatch(destination1 -> destination1.getNameIdentifier().equalsIgnoreCase(destination.getNameIdentifier())))
                        .isTrue()
        );
        clientUsesCreatedDestination.addAll(createdDestination.stream().filter(destination -> BooleanUtils.isTrue(destination.getSingleClient())).collect(Collectors.toList()));
    }

    /**
     * Check if the expected deleted destination was deleted
     */
    @Test(dependsOnMethods = "thenDestinationChangeReceivedAgain")
    public void thenDeletedDoesntExist() {
        List<Destination> deleted = getDefaultDestinationsRemoved().get(DELETED);
        List<Destination> deletedMt = getMtDestination(deleted);
        checkServiceUseDestinationNonExistence(deletedMt);

        List<Destination> notExist = getStDestination(deleted, clientId);
        List<Destination> clientUsesDestination = clientUsesCreatedDestination
                .stream()
                .filter(destination -> notExist.stream().anyMatch(destination1 -> destination.getNameIdentifier().equalsIgnoreCase(destination1.getNameIdentifier())))
                .collect(Collectors.toList());
        checkServiceUseClientDestinationNonExistence(clientUsesDestination);
    }

    /**
     * Check if the expected destination still exist.
     */
    @Test(dependsOnMethods = "thenDeletedDoesntExist")
    public void thenDestinationExistsAgain() {
        List<Destination> created = getDefaultDestinationsRemoved().get(CREATED);
        checkServiceUseDestinationExistence(created);
        List<Destination> stShouldBeExist = getStDestination(created, clientId);

        List<Destination> clientUsesDestination = clientUsesCreatedDestination
                .stream()
                .filter(destination -> stShouldBeExist.stream().anyMatch(destination1 -> destination.getNameIdentifier().equalsIgnoreCase(destination1.getNameIdentifier())))
                .collect(Collectors.toList());

        checkServiceUseClientDestinationExistence(clientUsesDestination);
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }

    private void checkServiceUseClientDestinationExistence(List<Destination> destinations) {

        List<String> nameIdentifiers = serviceUsesClientDestinationService.getDestinationNamesByServiceDescriptorId(serviceDescriptor.getId());

        List<String> expected = destinations
                .stream()
                .map(Destination::getNameIdentifier)
                .collect(Collectors.toList());
        assertThat(nameIdentifiers).hasSameElementsAs(expected);

        destinations.forEach(destination -> assertThat(serviceUsesDestinationService.existDestinationInServiceBus(destination,
                BooleanUtils.isNotTrue(destination.getSingleClient()) ? serviceDescriptor.getId() : destination.getId())).isTrue());
    }

    private void checkServiceUseDestinationExistence(List<Destination> destinations) {
        List<String> existingDestinations = serviceUsesDestinationService.getDestinationNamesByServiceDescriptorId(serviceDescriptor.getId());

        List<String> expected = destinations
                .stream()
                .map(Destination::getNameIdentifier)
                .collect(Collectors.toList());
        assertThat(existingDestinations).hasSameElementsAs(expected);

        List<Destination> mtDestination = getMtDestination(destinations);
        mtDestination.forEach(destination -> assertThat(serviceUsesDestinationService.existDestinationInServiceBus(destination,
                BooleanUtils.isNotTrue(destination.getSingleClient()) ? serviceDescriptor.getId() : destination.getId())).isTrue());
    }

    private void checkServiceUseDestinationNonExistence(List<Destination> deletedDestination) {
        List<String> existingDestinations = serviceUsesDestinationService.getDestinationNamesByServiceDescriptorId(serviceDescriptor.getId());

        deletedDestination.forEach(destination -> assertThat(existingDestinations.stream()
                .anyMatch(nameIdentifier -> nameIdentifier.equalsIgnoreCase(destination.getNameIdentifier())))
                .isFalse());

        // Topic deletion is not supported yet.
        deletedDestination.stream()
                .filter(destination -> destination.getType() == DestinationType.TOPIC)
                .filter(destination -> destination.getDirection() != Direction.OUT)
                .forEach(destination -> assertThat(serviceUsesDestinationService.existDestinationInServiceBus(destination,
                        BooleanUtils.isNotTrue(destination.getSingleClient()) ? serviceDescriptor.getId() : destination.getId()))
                        .isFalse());

    }

    private void checkServiceUseClientDestinationNonExistence(List<Destination> deletedDestination) {

        List<String> nameIdentifiers = serviceUsesClientDestinationService.getDestinationNamesByServiceDescriptorId(serviceDescriptor.getId());

        deletedDestination.forEach(destination -> assertThat(nameIdentifiers.stream()
                .anyMatch(nameIdentifier -> nameIdentifier.equalsIgnoreCase(destination.getNameIdentifier())))
                .isFalse());
        // Topic deletion is not supported yet.
        deletedDestination.stream()
                .filter(destination -> destination.getType() == DestinationType.TOPIC)
                .filter(destination -> destination.getDirection() != Direction.OUT)
                .forEach(destination -> assertThat(serviceUsesDestinationService.existDestinationInServiceBus(destination,
                        BooleanUtils.isNotTrue(destination.getSingleClient()) ? serviceDescriptor.getId() : destination.getId()))
                        .isFalse());

    }


    private DestinationChanged waitForDestinationChangeEvent() throws InterruptedException {
        for (int i = 0; i < ATTEMPT; i++) {
            System.out.println("Waiting for destination change event: " + i);
            DestinationChanged destinationChanged = destinationChangeHandler.pullMessage(SLEEP_S);
            if (destinationChanged != null) {
                return destinationChanged;
            }
        }
        Assert.fail("Destination change event wasn't received during reasonable amount of time.");
        return null;
    }

    private void deleteEntities() throws ServiceBusException {
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        List<Destination> mtDestination = getMtDestination(getDefaultDestinations());
        mtDestination.stream()
                .filter(destination -> destination.getType().equals(DestinationType.QUEUE))
                .forEach(destination -> serviceBusHandler.deleteQueue(destination.getNameIdentifier()));

        mtDestination.stream()
                .filter(destination -> destination.getType().equals(DestinationType.TOPIC))
                .forEach(destination -> serviceBusHandler.deleteQueue(destination.getNameIdentifier()));

        if (serviceDescriptor != null) {
            serviceBusHandler.deleteSubscription(SMS_MC_DESTINATION_CHANGE, SUBSCRIPTION);
            if (subscriptionClient != null) {
                subscriptionClient.close();
            }
            String sagaId = cusService.unassignServiceToClient(serviceDescriptor.getId(), clientId);
            boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.CLIENT_USES_SERVICE, ATTEMPT);
            if (sagaSuccess) {
                System.out.println("Client uses service was deleted.");
            } else {
                System.out.println("Client uses service was not assigned.");
            }
            serviceUsesDestinationService.deleteByServiceDescriptorId(serviceDescriptor.getId());
            serviceDescriptorService.deleteServiceDescriptor(GROUP_ID, ARTIFACT_ID);
        }
    }
}

