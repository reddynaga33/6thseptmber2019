package com.openmatics.test.functional.platform.servicemanagement;

import com.microsoft.azure.servicebus.ISubscriptionClient;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.cases.servicemanagement.SmsInstrumentation;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.initation.InitiationApi;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.ServiceManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.Deployment;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.EventDestination;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceVersionDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.DeploymentRepository;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.EventDestinationRepository;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.ServiceDescriptorRepository;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.repository.ServiceVersionDescriptorRepository;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.SmsmcApiProvider;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ClientUsesServiceRepository;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceUsesDestinationRepository;
import com.openmatics.testinstrumentation.platform.utils.OpenshiftClient;
import com.openmatics.testinstrumentation.platform.utils.Payload;
import com.openmatics.testinstrumentation.service.sms.DeploymentService;
import com.openmatics.testinstrumentation.service.sms.EventDestinationService;
import com.openmatics.testinstrumentation.service.sms.ServiceVersionDescriptorService;
import com.openmatics.testinstrumentation.service.smsmc.ClientService;
import com.openmatics.testinstrumentation.service.smsmc.CusService;
import com.openmatics.testinstrumentation.service.smsmc.SagaService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceDescriptorService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceUsesDestinationService;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import com.openmatics.testinstrumentation.utils.azure.exception.DestinationHandlerException;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import com.openmatics.testinstrumentation.utils.testng.Action;
import com.openshift.restclient.model.IDeploymentConfig;
import com.openshift.restclient.model.IResource;
import org.apache.commons.lang3.BooleanUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.openmatics.test.functional.platform.servicemanagement.SwitchTestUtil.TOPIC_FIRST;
import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_SERVICE_STARTED;
import static org.assertj.core.api.Assertions.assertThat;

/*
prerequisites
- The application with Identifier URI 'http://multiclient-test-service' has to exist in the AAD
*/
public class SwitchingMultiAndSingleClientAcceptanceTest extends DbBaseTest {
    private static final String ARTIFACT_ID = "multiclient-test-service";
    private static final String VERSION = "latest";
    private static final String NON_EMPTY_DESTINATION = TOPIC_FIRST;
    private final static String GROUP_ID = "com.openmatics.cloud.core.service";
    private static final int ATTEMPT = 10;
    private static final int SLEEP_S = 10;
    //    private final String client1 = "df8545d8-7179-44eb-94f7-51e6561e963b";
//    private final String client2 = "f2e129d8-8f15-4df3-af46-c7fc5513d0aa";
    private List<String> clients;

    private List<Deployment> deployments = new ArrayList<>();
    private List<Deployment> deploymentsRollback = new ArrayList<>();
    private ServiceBusDestinationsHandler sbDestinationsHandler = null;
    private ServiceBusClient sbClient = null;
    private OpenshiftClient openshift = null;
    private SmsInstrumentation smsInstr = null;
    private ServiceDescriptor serviceDescriptor = null;
    private ServiceDescriptorService smsmcServiceDescriptorService;
    private ServiceUsesDestinationService serviceUsesDestinationService;
    private com.openmatics.testinstrumentation.service.sms.ServiceDescriptorService smsServiceDescriptorService;
    private SagaService sagaService;
    private DeploymentService deploymentService;
    private EventDestinationService eventDestinationService;
    private CusService cusService;
    private InitiationApi initiationApi;
    private ClientService clientService;

    private String appId;



    @BeforeClass()
    public void beforeSwitchingMultiAndSingleClientAcceptanceTest() throws Exception {
        appId = new ServiceConfiguration(ARTIFACT_ID, platformInstrumentation.getEnvConf()).getApplicationClientId();
        sbDestinationsHandler = new ServiceBusDestinationsHandler(platformInstrumentation.getEnvConf().getServiceBusProperty());
        sbClient = new ServiceBusClient(platformInstrumentation.getEnvConf().getServiceBusProperty().getConnectionString());
        smsInstr = new SmsInstrumentation(connectionFactory);
        initiationApi = new InitiationApi(envKey);
        openshift = new OpenshiftClient(platformInstrumentation.getEnvConf());
        SmsmcApiProvider smsmcApiProvider = new SmsmcApiProvider(platformInstrumentation);
        ServiceManagementApi serviceManagementApi = new ServiceManagementApi(platformInstrumentation.getEnvConf());
        smsmcServiceDescriptorService = new ServiceDescriptorService(
                new com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceDescriptorRepository(connectionFactory),
                smsmcApiProvider.getEventApi().getSmsmcServiceEventApi(),
                smsmcApiProvider.getRestApi().getServiceApi());
        cusService = new CusService(new ClientUsesServiceRepository(connectionFactory), smsmcApiProvider.getEventApi().getCusApi());
        sagaService = new SagaService(smsmcApiProvider.getRestApi().getSagaApi());
        smsServiceDescriptorService = new com.openmatics.testinstrumentation.service.sms.ServiceDescriptorService(serviceManagementApi.getServiceApi(), new ServiceDescriptorRepository(connectionFactory));
        deploymentService = new DeploymentService(new DeploymentRepository(connectionFactory), initiationApi.getDeploymentsApi());
        eventDestinationService = new EventDestinationService(new EventDestinationRepository(connectionFactory));
        serviceUsesDestinationService = new ServiceUsesDestinationService(new ServiceUsesDestinationRepository(connectionFactory), sbDestinationsHandler);
        clientService = new ClientService(smsmcApiProvider.getRestApi().getClientApi());
    }

    @AfterClass
    public void afterSwitchingMultiAndSingleClientAcceptanceTest() throws Exception {
        List<Deployment> existingDeployments = deploymentService.loadAllServiceDescriptorDeployments(GROUP_ID, ARTIFACT_ID);
        List<Deployment> usedDeployment = new ArrayList<>();
        usedDeployment.addAll(deployments);
        usedDeployment.addAll(deploymentsRollback);
        List<String> deploymentsForDelete = usedDeployment.stream()
                .filter(deployment -> existingDeployments.stream().noneMatch(deployment1 -> deployment.getId().equalsIgnoreCase(deployment1.getId())))
                .map(Deployment::getId)
                .collect(Collectors.toList());
        if (!existingDeployments.isEmpty()) {
            List<EventDestination> eventDestinations = eventDestinationService.loadEventDestinationByDeploymentId(existingDeployments.get(0).getId());
            for (EventDestination eventDestination : eventDestinations) {
                String topicName = eventDestination.getNameIdentifier();
                System.out.println("Deleting destinations from the service bus: " + topicName + "Subscriptions: " + deploymentsForDelete);
                deploymentsForDelete.forEach(sbName -> sbDestinationsHandler.deleteSubscription(topicName, sbName));
            }
        }
    }
    
    @Test
    public void testing() {
    try {	Message a =new Message(Payload.getEventServiceStarted());
    	String topicname="com.openmatics.cloud.core.service_service-management-service-multiclient_serviceconfiguration";
    	sbDestinationsHandler.ensureTopicCreation(topicname);
    	long subscriptionMessageCount1 = sbDestinationsHandler.getSubscriptionMessageCount(topicname,"test");
    	System.out.println(subscriptionMessageCount1);
    	sbClient.sendToTopic(topicname, a);
    	long subscriptionMessageCount = sbDestinationsHandler.getSubscriptionMessageCount(topicname,"test");
    	System.out.println(subscriptionMessageCount);
    	}
    catch(Exception e) {
    	e.getMessage();
    }
    }
//    @Test
    public void testing1() {
    try {	Message a =new Message(Payload.getEventServiceStarted());
    	String topicname="com.openmatics.cloud.core.service_service-management-service-multiclient_serviceconfiguration";
    	sbDestinationsHandler.ensureTopicCreation(topicname);
    	long subscriptionMessageCount1 = sbDestinationsHandler.getSubscriptionMessageCount(topicname,"test");
    	System.out.println(subscriptionMessageCount1);
    	sbClient.sendToTopic(topicname, a);
    	long subscriptionMessageCount = sbDestinationsHandler.getSubscriptionMessageCount(topicname,"test");
    	System.out.println(subscriptionMessageCount);
    	}
    catch(Exception e) {
    	e.getMessage();
    }
    }

//    @Test(dependsOnMethods = "testing")
    public void givenClients() throws Exception {
        clients = platformInstrumentation.getEnvConf().getDeploymentConfiguration().getClients()
                .stream()
                .map(name -> clientService.findClientByName(name).getId())
                .collect(Collectors.toList());
        System.out.println("Deploying to this client.");
    }

//    @Test(dependsOnMethods = "givenClients")
    public void givenDeleteMtEntities() {
        ServiceDescriptor serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        if (serviceDescriptor != null) {
            clients.stream().filter(client -> cusService.isRecordInDbClientUsesService(client, serviceDescriptor.getId())).forEach(client -> {
                String sagaId = cusService.unassignServiceToClient(serviceDescriptor.getId(), client);
                boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.CLIENT_USES_SERVICE, ATTEMPT);
                if (sagaSuccess) {
                    System.out.println("Client uses service was deleted.");
                } else {
                    System.out.println("Unassignment service from client is failed. Perhaps the assignment doesn't exist.");
                }
            });
            clients.forEach(client -> assertThat(cusService.isRecordInDbClientUsesService(client, serviceDescriptor.getId())).isFalse());
            serviceUsesDestinationService.deleteByServiceDescriptorId(serviceDescriptor.getId(), false);
            assertThat(serviceUsesDestinationService.getDestinationNamesByServiceDescriptorId(serviceDescriptor.getId())).isEmpty();
        }
    }

    /**
     * Load service descriptor.
     *
     * @throws InterruptedException interrupted exception
     */
//    @Test(dependsOnMethods = "givenDeleteMtEntities")
    public void givenServiceDescriptor() throws InterruptedException {
        serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        if (serviceDescriptor == null) {
            smsServiceDescriptorService.createServiceDescriptor(new ServiceDescriptor(null, ARTIFACT_ID, GROUP_ID, true, appId));
            serviceDescriptor = waitForServiceDescriptor(true);
        }
        assertThat(serviceDescriptor).isNotNull();

    }

    /**
     * Check if the service descriptor is in the single tenant mode.
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "givenServiceDescriptor")
    public void givenIfServiceIsInSingleTenantMode() throws Exception {
        if (BooleanUtils.isFalse(serviceDescriptor.getSingleClient())) {
            System.out.println("Service descriptor is in the MT state. Switching to the SC");
            smsServiceDescriptorService.switchToStModeViaDatabase(serviceDescriptor);
            serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
            assertThat(serviceDescriptor.getSingleClient()).isTrue();
            System.out.println("The service descriptor is in the ST state. " + serviceDescriptor);
        }
        assertThat(serviceDescriptor.getSingleClient()).isTrue();
    }

//    @Test(dependsOnMethods = "givenIfServiceIsInSingleTenantMode")
    public void givenAssignDeployments() {
        // Create/load service version descriptor.
        ServiceVersionDescriptorService serviceVersionDescriptorService = new ServiceVersionDescriptorService(
                new ServiceVersionDescriptorRepository(connectionFactory),
                initiationApi.getVersionApi()
        );
        // Load or create the version
        ServiceVersionDescriptor serviceVersionDescriptor = serviceVersionDescriptorService.saveServiceVersionDescriptor(VERSION, serviceDescriptor);
        assertThat(serviceVersionDescriptor).as("Check existence of service version descriptor").isNotNull();
        System.out.println("Service version descriptor exists: " + serviceVersionDescriptor);
        // is client1 assigned to the version?
        System.out.println(String.format("Try to assign version: %s to clients %s", serviceVersionDescriptor, clients));
        clients.forEach(client -> assertThat(deploymentService.assignVersionToClient(serviceVersionDescriptor, serviceDescriptor, client)).isTrue());
        System.out.println("Service version descriptor was assigned to the client: " + clients);
    }

    /**
     * Create and check synthetic destination exists in the database.
     * 
     * 
     * 
     * 
     * 
     */
//    @Test(dependsOnMethods = "givenAssignDeployments")
    public void givenServiceBusTopicsDestinations() {
        SwitchTestUtil.getSyntheticDestinationName()
                .forEach(name -> {
                    try {
                        sbDestinationsHandler.ensureTopicCreation(name);
                    } catch (DestinationHandlerException e) {
                        System.err.println(String.format("Topic '%s' can't be created: ", name) + e);
                    }
                });
        SwitchTestUtil.getSyntheticDestinationName().forEach(name -> sbDestinationsHandler.isTopicExists(name));
    }

    /**
     * Load all deployments and create synthetic destination.
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "givenServiceBusTopicsDestinations")
    public void givenDeployments() throws Exception {
        deployments = loadDeployments(true);
    }

    /**
     * Delete MT destination from the service bus
     * Check if the mt subscription doesn't exist in the service bus.
     */
//    @Test(dependsOnMethods = "givenDeployments")
    public void givenMtDestinations() {
        for (Deployment deployment : deployments) {
            SwitchTestUtil.getSyntheticDestinationName().stream()
                    .filter(name -> sbDestinationsHandler.isSubscriptionExists(name, deployment.getServiceDescriptorId()))
                    .forEach(name -> {
                        sbDestinationsHandler.deleteSubscription(name, deployment.getServiceDescriptorId());
                        System.out.println(String.format("Deleting MT subscription. Topic: %s, Subscription: %s", name, deployment.getServiceDescriptorId()));
                    });
        }
        for (Deployment deployment : deployments) {
            SwitchTestUtil.getSyntheticDestinationName()
                    .forEach(name -> assertThat(sbDestinationsHandler.isSubscriptionExists(name, deployment.getServiceDescriptorId())).isFalse());
        }
    }


    /**
     * Every deployment has deployment config in the openshift
     */
//    @Test(dependsOnMethods = "givenMtDestinations")
    public void givenServiceHasDeploymentConfigForEachClient() {
        List<IDeploymentConfig> allConfigs = openshift.getDeploymentConfigList();
        deployments.stream()
                .map(deployment -> smsInstr.getOpenshiftDeploymentName(ARTIFACT_ID, deployment.getClientId()))
                .forEach(serviceName ->
                        assertThat(allConfigs.stream()
                                .anyMatch(config -> config.getName().equalsIgnoreCase(serviceName)))
                                .isTrue());
    }

    /**
     * The method checks if the <code>NON_EMPTY_DESTINATION</code> destination is not empty.
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "givenServiceHasDeploymentConfigForEachClient")
    public void givenTopicSubscriptionNotEmpty() throws Exception {
        // This method should be refactored.
        //   * The waiting should be written better.
        //   * NON_EMPTY_DESTINATION - the meaning is not clear. Why is used only this destination?
        for (Deployment deployment : deployments) {
            EventDestination destination = deployment.getEventDestinations().stream()
                    .filter(eventDestination -> eventDestination.getNameIdentifier().equalsIgnoreCase(NON_EMPTY_DESTINATION))
                    .findAny().orElseThrow(() -> new RuntimeException(String.format("The not empty destination has to exist for deployment. Deployment: %s", deployment.getId())));

            sendAndWaitForMessage(destination.getNameIdentifier(), deployment.getId());
        }
    }

    /**
     * Check if all synthetic subscription are empty.
     */
//    @Test(dependsOnMethods = "givenTopicSubscriptionNotEmpty")
    public void givenTopicSubscriptionEmpty() {
        Map<String, List<String>> destinationMap = deployments.stream()
                .collect(Collectors.toMap(Deployment::getId, o -> SwitchTestUtil.getSyntheticDestinationName().stream()
                        .filter(syntheticName -> !syntheticName.equalsIgnoreCase(NON_EMPTY_DESTINATION))
                        .collect(Collectors.toList())
                ));
        for (Map.Entry<String, List<String>> entry : destinationMap.entrySet()) {
            // the destination is named by the name and the subscription is named by deploymentId
            entry.getValue().forEach(destinationName ->
                    assertThat(sbDestinationsHandler.getSubscriptionMessageCount(destinationName, entry.getKey()))
                            .as(String.format("The subscription should be empty. Topic: '%s', Subscription: '%s'", destinationName, entry.getKey()))
                            .isEqualTo(0));
        }
    }

    /**
     * Switch to MC mode.
     *
     * @throws Exception exception
     */
    /* --------------- Action area: switch to multitenant --------------- */
//    @Action("Send service desriptor change and set service as multiclient")
//    @Test(dependsOnMethods = "givenTopicSubscriptionEmpty")
    public void whenISetServiceAsMultiClient() throws Exception {
        ServiceDescriptor serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        assertThat(serviceDescriptor.getSingleClient()).isTrue();
        serviceDescriptor.setSingleClient(false);

        smsServiceDescriptorService.updateServiceDescriptor(serviceDescriptor);
    }

    /**
     * Waiting for the switch to SM mode.
     *
     * @throws Exception exception
     */
    /* ------------------ Assert ---------------------- */
//    @Test(dependsOnMethods = "whenISetServiceAsMultiClient")
    public synchronized void thenICanGetSuccessfulResultOfToMT() throws Exception {
        //TODO: Check result of init. service.
        for (int i = 0; i < ATTEMPT; i++) {
            ServiceDescriptor serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
            if (!serviceDescriptor.getSingleClient()) {
                return;
            }
            TimeUnit.SECONDS.timedWait(this, 2 * SLEEP_S);
        }
        Assert.fail("Service descriptor wasn't changed to MC during reasonable amount of time.");
    }

    /**
     * Check if the topic still exist in the service bus.
     */
//    @Test(dependsOnMethods = "thenICanGetSuccessfulResultOfToMT")
    public void thenInServiceBusAreDestinationStill() {
        //destination must not be deleted (only empty subscription are deleted)
        Set<String> topicNames = deployments.stream().map(deploymentContext -> SwitchTestUtil.getSyntheticDestinationName())
                .flatMap(List::stream)
                .collect(Collectors.toSet());
        topicNames.forEach(name -> assertThat(sbDestinationsHandler.isTopicExists(name))
                .as("Check if the topic exists in the service bus. Topic:" + name)
                .isTrue());
    }

    /**
     * Check if the deleted subscriptions are deleted.
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "thenInServiceBusAreDestinationStill")
    public void thenAreDeletedEmptySTSubscription() throws Exception {
        // check if all empty single client subscription are delete
        for (Deployment deployment : deployments) {
            List<String> emptyDestinations = SwitchTestUtil.getSyntheticDestinationName().stream()
                    .filter(name -> !name.equalsIgnoreCase(NON_EMPTY_DESTINATION))
                    .collect(Collectors.toList());
            for (String name : emptyDestinations) {
                assertThat(sbDestinationsHandler.isSubscriptionExists(name, deployment.getId()))
                        .as(String.format("Single client empty subscription check: SB deletion, Subscription '%s' Topic: %s", deployment.getId(), name))
                        .isFalse();

                assertThat(smsInstr.isExistsSTSubscriptionInDb(name, deployment.getId()))
                        .as(String.format("Single client empty subscription check: DB deletion, Subscription '%s' Topic: %s", deployment.getId(), name))
                        .isFalse();
            }
        }
    }

    /**
     * Check if the not empty subscription is not deleted.
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "thenAreDeletedEmptySTSubscription")
    public void thenAreNotDeletedFullSTSubscription() throws Exception {
        for (Deployment deployment : deployments) {
            List<String> emptyDestinations = SwitchTestUtil.getSyntheticDestinationName().stream()
                    .filter(name -> name.equalsIgnoreCase(NON_EMPTY_DESTINATION))
                    .collect(Collectors.toList());
            for (String name : emptyDestinations) {
                assertThat(sbDestinationsHandler.isSubscriptionExists(name, deployment.getId()))
                        .as(String.format("Subscription %s in topic %s with events was deleted in ServiceBus!", deployment.getId(), name))
                        .isTrue();

                assertThat(smsInstr.isExistsSTSubscriptionInDb(name, deployment.getId()))
                        .as(String.format("Subscription %s in topic %s with events was deleted in db!", deployment.getId(), name))
                        .isFalse();
            }
        }
    }

    /**
     * Check if the MT subscription are added to the each topic
     */
//    @Test(dependsOnMethods = "thenAreNotDeletedFullSTSubscription")
    public void thenMTSubscriptionAreAddedToTheTopic() {
        // For new MT subscription will be use service descriptor id
        // not necessary check for each Deployment. Is enough only for service descriptor and destination
        for (Deployment deployment : deployments) {
            SwitchTestUtil.getSyntheticDestinationName().forEach(name -> assertThat(sbDestinationsHandler.isSubscriptionExists(name, deployment.getServiceDescriptorId()))
                    .as(String.format("Checking if the MT subscription exists in the Service Bus topic. Topic: %s, Subscription: %s", name, deployment.getServiceDescriptorId()))
                    .isTrue());
        }
    }

    /**
     * Check if client uses service was added to the database
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "thenMTSubscriptionAreAddedToTheTopic")
    public void thenClientUsesServiceRecordsAreAddedInDB() throws Exception {
        for (Deployment deployment : deployments) {
            assertThat(cusService.isRecordInDbClientUsesService(deployment.getClientId(), deployment.getServiceDescriptorId())).isTrue();
        }
    }

    /**
     * Check if the mt destination are added to the db
     */
//    @Test(dependsOnMethods = "thenClientUsesServiceRecordsAreAddedInDB")
    public void thenMTDestinationAreAddedInDB() {
        // For new MT subscription will be use service descriptor id
        for (Deployment deployment : deployments) {
            SwitchTestUtil.getSyntheticDestinationName()
                    .forEach(name -> assertThat(serviceUsesDestinationService.isRecordInServiceUsesDestination(deployment.getServiceDescriptorId(), name)).isTrue());

        }
    }

    /**
     * Check if the deployment configs were deleted from the openshift.
     */
//    @Test(dependsOnMethods = "thenMTDestinationAreAddedInDB")
    public void thenInOpenshiftAreDeletedDeploymentsConfigs() {
        List<IDeploymentConfig> allConfigs = openshift.getDeploymentConfigList();
        deployments.stream()
                .map(deployment -> smsInstr.getOpenshiftDeploymentName(ARTIFACT_ID, deployment.getClientId()))
                .forEach(serviceName ->
                        assertThat(allConfigs.stream()
                                .anyMatch(config -> config.getName().equalsIgnoreCase(serviceName)))
                                .isFalse());
    }

    /**
     * Check if the services were deleted from the openshift.
     */
//    @Test(dependsOnMethods = "thenInOpenshiftAreDeletedDeploymentsConfigs")
    public void thenInOpenshiftAreDeletedServices()  {

        List<IResource> allServices = openshift.getAllServices();
        deployments.stream()
                .map(deployment -> smsInstr.getOpenshiftDeploymentName(ARTIFACT_ID, deployment.getClientId()))
                .forEach(serviceName ->
                        assertThat(allServices.stream()
                                .anyMatch(config -> config.getName().equalsIgnoreCase(serviceName)))
                                .isFalse());
    }

    /**
     * Check if the config maps were deleted from the openshift.
     */
//    @Test(dependsOnMethods = "thenInOpenshiftAreDeletedServices")
    public void thenInOpenshiftAreDeletedConfigMaps() {
        List<IResource> allServices = openshift.getAllConfigMaps();
        deployments.stream()
                .map(deployment -> smsInstr.getOpenshiftDeploymentName(ARTIFACT_ID, deployment.getClientId()))
                .forEach(serviceName ->
                        assertThat(allServices.stream()
                                .anyMatch(config -> config.getName().equalsIgnoreCase(serviceName)))
                                .isFalse());
    }

    /**
     * Check if the deployment were deleted from the DB.
     */
//    @Test(dependsOnMethods = "thenInOpenshiftAreDeletedConfigMaps")
    public void thenDeploymentAreDeletedFromDB() {
        for (Deployment deployment : deployments) {
            String deploymentQuery = "select count(*) as number from [service_management_service_schema].[deployment] " +
                    "where [id]='%s'";
            deploymentQuery = String.format(deploymentQuery, deployment.getId());
            try {
                DbQueryService sql = new DbQueryService(connectionFactory);
                ResultSet rs = sql.executeQuery(deploymentQuery);
                rs.next();
                assertThat(rs.getInt(1)).isEqualTo(0);
                sql.closeQuery(rs);
            } catch (SQLException e) {
                System.err.println("The database operation can be executed due to: " + e.getMessage());
                throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
            }
        }
    }

    /* --------------- Action switch to back single tenant ---------- */


//    @Test(dependsOnMethods = "thenDeploymentAreDeletedFromDB")
    public void givenSendMessageToMTSubscription() throws InterruptedException {
        sbClient.sendToTopic(NON_EMPTY_DESTINATION, new Message("Test message"));
        sendAndWaitForMessage(NON_EMPTY_DESTINATION, serviceDescriptor.getId());

    }

//    @Action("Send service descriptor change and set service back as singleclient")
//    @Test(dependsOnMethods = "givenSendMessageToMTSubscription")
    public void whenISetBackServiceAsSingleClient() throws Exception {
        ServiceDescriptor serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        assertThat(serviceDescriptor.getSingleClient()).isFalse();
        serviceDescriptor.setSingleClient(true);

        smsServiceDescriptorService.updateServiceDescriptor(serviceDescriptor);
    }

    /**
     * Waiting for switch
     *
     * @throws Exception exception
     */
//    @Test(dependsOnMethods = "whenISetBackServiceAsSingleClient")
    public synchronized void thenICanGetSuccessfulResultOfToSingleClient() throws Exception {
        for (int i = 0; i < ATTEMPT; i++) {
            ServiceDescriptor serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
            if (serviceDescriptor.getSingleClient()) {
                return;
            }
            TimeUnit.SECONDS.timedWait(this, SLEEP_S);
        }
        Assert.fail("Service descriptor wasn't changed to SC during reasonable amount of time.");
    }

    /**
     * Check deployment existence in the DB
     */
//    @Test(dependsOnMethods = "thenICanGetSuccessfulResultOfToSingleClient")
    public void thenInDbAreCreatedDeploymentsForEachClient() throws Exception {
        Set<String> clientsBefore = deployments.stream().map(Deployment::getClientId).collect(Collectors.toSet());
        deploymentsRollback = loadDeployments(false);
        Set<String> clientsCurrent = deploymentsRollback.stream().map(Deployment::getClientId).collect(Collectors.toSet());
        assertThat(deploymentsRollback.size()).isEqualTo(deployments.size());
        assertThat(clientsCurrent).hasSameElementsAs(clientsBefore);
    }

    /**
     * Check destinations in the database
     */
//    @Test(dependsOnMethods = "thenInDbAreCreatedDeploymentsForEachClient")
    public void thenDbAreCreatedDestination() {
        // check if all empty single client subscription are delete
        Set<String> destinationBefore = deployments.stream().map(deploymentContext -> SwitchTestUtil.getSyntheticDestinationName())
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        for (Deployment deployment : deploymentsRollback) {
            List<String> inputDestinationName = deployment.getEventDestinations()
                    .stream()
                    .map(EventDestination::getNameIdentifier)
                    .collect(Collectors.toList());
            assertThat(destinationBefore).isSubsetOf(inputDestinationName);
        }
    }

    /**
     * Check service bus existence
     */
//    @Test(dependsOnMethods = "thenDbAreCreatedDestination")
    public void thenInServiceBusAreCreatedSTSubscription() throws ServiceBusException, InterruptedException {
        // check if all empty single client subscription are delete
        for (Deployment deployment : deploymentsRollback) {
            for (String name : SwitchTestUtil.getSyntheticDestinationName()) {
                assertThat(sbDestinationsHandler.isSubscriptionExists(name, deployment.getId()))
                        .as(String.format("Check if the ST subscription exists in the Service Bus. Topic: '%s'', Subscription: '%s'", name, deployment.getId()))
                        .isTrue();
                ISubscriptionClient subscriptionClient = sbClient.createSubscriptionClient(name, deployment.getId());
                assertThat(subscriptionClient.getRules()).isNotEmpty();
            }
        }
    }

    /**
     * CUS doesn't exist in the database
     */
//    @Test(dependsOnMethods = "thenInServiceBusAreCreatedSTSubscription")
    public void thenClientUsesServiceAssignmentIsDeletedInDb() {
        for (Deployment deployment : deployments) {
            assertThat(cusService.isRecordInDbClientUsesService(deployment.getClientId(), deployment.getServiceDescriptorId()))
                    .as(String.format("Check if in db was delete assignemt ClientUsesService for client %s and service descriptor %s", deployment.getClientId(), deployment.getServiceDescriptorId()))
                    .isFalse();
        }
    }

    /**
     * Check if the MT subscription have a RULE
     */
//    @Test(dependsOnMethods = "thenClientUsesServiceAssignmentIsDeletedInDb")
    public void thenInServiceBusMTSubscriptionHasDeleteRule() throws ServiceBusException, InterruptedException {
        for (String name : SwitchTestUtil.getSyntheticDestinationName()) {
            assertThat(sbDestinationsHandler.isSubscriptionExists(name, serviceDescriptor.getId()))
                    .as(String.format("Check if the ST subscription exists in the Service Bus. Topic: '%s'', Subscription: '%s'", name, serviceDescriptor.getId()))
                    .isTrue();
            ISubscriptionClient subscriptionClient = sbClient.createSubscriptionClient(name, serviceDescriptor.getId());
            assertThat(subscriptionClient.getRules()).isEmpty();
        }
    }

    /**
     * The messages are resent
     */
//    @Test(dependsOnMethods = "thenInServiceBusMTSubscriptionHasDeleteRule")
    public void thenEventsFromMTSubscriptionAreResend() {
        for (Deployment deployment : deploymentsRollback) {
            SwitchTestUtil.getSyntheticDestinationName().forEach(name ->
                    assertThat(sbDestinationsHandler.isSubscriptionExists(name, deployment.getServiceDescriptorId())).isTrue());
            assertThat(sbDestinationsHandler.getSubscriptionMessageCount(NON_EMPTY_DESTINATION, deployment.getId())).isNotEqualTo(0);
        }
        assertThat(sbDestinationsHandler.getSubscriptionMessageCount(NON_EMPTY_DESTINATION, serviceDescriptor.getId())).isEqualTo(0);
    }

    /**
     * Check resources in the openshift.
     */
//    @Test(dependsOnMethods = "thenEventsFromMTSubscriptionAreResend")
    public void thenInOpenshiftAreRunningSTServices() {
        List<String> allDc = openshift.getAllDeploymentConfigs().stream().map(resource -> resource.getName().toLowerCase()).collect(Collectors.toList());
        List<String> allServices = openshift.getAllServices().stream().map(resource -> resource.getName().toLowerCase()).collect(Collectors.toList());
        List<String> configMaps = openshift.getAllConfigMaps().stream().map(resource -> resource.getName().toLowerCase()).collect(Collectors.toList());
        List<String> names = deploymentsRollback.stream()
                .map(deployment -> smsInstr.getOpenshiftDeploymentName(ARTIFACT_ID, deployment.getClientId()).toLowerCase())
                .collect(Collectors.toList());
        for (String name : names) {
            assertThat(allDc.contains(name)).isTrue();
            assertThat(allServices.contains(name)).isTrue();
            assertThat(configMaps.contains(name)).isTrue();
        }
    }


    private synchronized List<Deployment> loadDeployments(boolean sendServiceStarted) throws Exception {
        List<Deployment> deployments = deploymentService.loadAllServiceDescriptorDeployments(GROUP_ID, ARTIFACT_ID);
        for (Deployment deployment : deployments) {
            if (sendServiceStarted) {
                Message sbMessage = new Message(SwitchTestUtil.getEventServiceStarted(deployment.getId()));
                sbClient.sendToTopic(SMS_SERVICE_STARTED, sbMessage);
            }
            //ST subscription use as name deploymentId
            for (String topic : SwitchTestUtil.getSyntheticDestinationName()) {
                System.out.println(String.format("Checking if the subscription '%s' exists in the topic '%s'", deployment.getId(), topic));
                smsInstr.waitForSubscription(topic, deployment.getId(), sbDestinationsHandler, 5);
                boolean destinationExists = false;
                List<EventDestination> eventDestinations = null;
                for (int i = 0; i < ATTEMPT; i++) {
                    eventDestinations = eventDestinationService.loadEventDestinationByDeploymentId(deployment.getId());
                    destinationExists = eventDestinations.stream().anyMatch(eventDestination -> eventDestination.getNameIdentifier().equalsIgnoreCase(topic));
                    if (destinationExists) {
                        break;
                    }
                    TimeUnit.SECONDS.timedWait(this, SLEEP_S);
                }
                assertThat(destinationExists).as("check if the destination exists in the database? " + topic).isTrue();
                deployment.setEventDestinations(eventDestinations);
            }
        }
        return deployments;
    }

    private synchronized ServiceDescriptor waitForServiceDescriptor(boolean exist) throws InterruptedException {
        ServiceDescriptor serviceDescriptor = null;
        for (int i = 0; i < ATTEMPT; i++) {
            serviceDescriptor = smsmcServiceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
            if (exist && serviceDescriptor != null) {
                break;
            } else if (!exist && serviceDescriptor == null) {
                break;
            }
            TimeUnit.SECONDS.timedWait(this, SLEEP_S);
        }
        return serviceDescriptor;
    }

    private synchronized void sendAndWaitForMessage(String nameIdentifier, String subscriptionName) throws InterruptedException {

        long messageCount = sbDestinationsHandler.getSubscriptionMessageCount(nameIdentifier, subscriptionName);
        sbClient.sendToTopic(nameIdentifier, new Message("Test message"));

        long messageCountNext = 0;
        for (int i = 0; i < ATTEMPT; i++) {
            messageCountNext = sbDestinationsHandler.getSubscriptionMessageCount(nameIdentifier, subscriptionName);
            if (messageCount < messageCountNext) {
                break;
            }
            TimeUnit.SECONDS.timedWait(true, SLEEP_S);
        }
        assertThat(messageCount).isLessThan(messageCountNext);
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }
}
