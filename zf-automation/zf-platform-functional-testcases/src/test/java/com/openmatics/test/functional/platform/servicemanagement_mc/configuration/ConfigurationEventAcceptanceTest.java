package com.openmatics.test.functional.platform.servicemanagement_mc.configuration;

import com.microsoft.azure.servicebus.ISubscriptionClient;
import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.SmsmcApiProvider;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.ConfigurationChangedHandler;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.entity.ServiceConfigurationChanged;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceDescriptorRepository;
import com.openmatics.testinstrumentation.service.smsmc.CusService;
import com.openmatics.testinstrumentation.service.smsmc.ConfigurationService;
import com.openmatics.testinstrumentation.service.smsmc.SagaService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceDescriptorService;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static com.openmatics.test.functional.platform.servicemanagement_mc.configuration.ConfigurationUtil.ARTIFACT_ID;
import static com.openmatics.test.functional.platform.servicemanagement_mc.configuration.ConfigurationUtil.GROUP_ID;
import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_MC_CONFIGURATION_CHANGED;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 28.03.2019.
 */
@Test(testName = "ConfigurationEventAcceptanceTest")
public class ConfigurationEventAcceptanceTest extends DbBaseTest {
    private static final String CLIENT_PREFIX = "client-property";
    private static final String APP_ID_SERVICE_NAME = "multiclient-test-service";
    private String appId;
    private static final String CLIENT_USES_SERVICE_PREFIX = "cus-property";
    private static final String SUBSCRIPTION = "automated-test";
    private static final int ATTEMPT = 10;
    private static final int SLEEP_S = 5;

    private ServiceDescriptorService serviceDescriptorService;
    private ConfigurationChangedHandler configurationChangedHandler;
    private ConfigurationService configurationService;
    private ServiceBusDestinationsHandler serviceBusDestinationsHandler;
    private ServiceBusClient serviceBusClient;
    private ISubscriptionClient subscriptionClient;
    private SagaService sagaService;
    private CusService cusService;

    private ServiceDescriptor serviceDescriptor;
    private String clientId;


    @BeforeClass
    public void beforeConfigurationAcceptanceEventTest() throws Exception {
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration(APP_ID_SERVICE_NAME, platformInstrumentation.getEnvConf());
        appId = serviceConfiguration.getApplicationClientId();

        ServiceDescriptorRepository serviceDescriptorRepository = new ServiceDescriptorRepository(connectionFactory);

        SmsmcApiProvider smsmcApiProvider = new SmsmcApiProvider(platformInstrumentation);
        configurationService = new ConfigurationService(smsmcApiProvider.getRestApi().getConfigurationReadApi(), smsmcApiProvider.getEventApi().getConfigurationModifyApi());
        sagaService = new SagaService(smsmcApiProvider.getRestApi().getSagaApi());
        cusService = new CusService(smsmcApiProvider.getEventApi().getCusApi());
        serviceDescriptorService = new ServiceDescriptorService(serviceDescriptorRepository, smsmcApiProvider.getEventApi().getSmsmcServiceEventApi(), smsmcApiProvider.getRestApi().getServiceApi());
        serviceBusDestinationsHandler = new ServiceBusDestinationsHandler(platformInstrumentation.getEnvConf().getServiceBusProperty());
        serviceBusClient = new ServiceBusClient(platformInstrumentation.getEnvConf().getServiceBusProperty().getConnectionString());
    }

    @AfterClass
    public void afterConfigurationAcceptanceEventTest() throws Exception {
        serviceBusDestinationsHandler.deleteSubscription(SMS_MC_CONFIGURATION_CHANGED, SUBSCRIPTION);
        subscriptionClient.close();
        deleteEntities();
    }

    @Override
    protected DbConnectionFactory initiateDbConnection() throws Exception {
        return new DbConnectionFactory(platformInstrumentation.getEnvConf().getDbConnectionString());
    }

    @Test
    public void givenClient() throws Exception {
        clientId = platformInstrumentation.getClientConf().getClient().getId();
    }

    @Test(dependsOnMethods = "givenClient")
    public void givenDeleteEntities() throws Exception {
        deleteEntities();
    }

    @Test(dependsOnMethods = "givenDeleteEntities")
    public void givenLoadServiceDescriptor() throws Exception {
        String sagaId = serviceDescriptorService.saveServiceDescriptor(GROUP_ID, ARTIFACT_ID, appId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.SERVICE_DESCRIPTOR, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaId + "' about saving service descriptor success?").isTrue();
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        System.out.println("Using this service descriptor: " + serviceDescriptor);
    }

    @Test(dependsOnMethods = "givenLoadServiceDescriptor")
    public void givenClientUsesService() throws Exception {
        String sagaId = cusService.assignServiceToClient(serviceDescriptor.getId(), clientId);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.CLIENT_USES_SERVICE, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaId + "' about saving service descriptor success?").isTrue();
    }

    @Test(dependsOnMethods = "givenClientUsesService")
    public void givenConfigurationChangeSubscriptionListener() throws Exception {
        serviceBusDestinationsHandler.ensureSubscriptionCreation(SMS_MC_CONFIGURATION_CHANGED, SUBSCRIPTION);
        configurationChangedHandler = new ConfigurationChangedHandler(serviceDescriptor.getArtifactId(), clientId, serviceDescriptor.getGroupId());
        subscriptionClient = serviceBusClient.receiveTopicMessage(SMS_MC_CONFIGURATION_CHANGED, SUBSCRIPTION, configurationChangedHandler);
    }

    @Test(dependsOnMethods = "givenConfigurationChangeSubscriptionListener")
    public void whenClientConfigurationCreate() throws Exception {
        String id = configurationService.createClientConfiguration(clientId, ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX), true);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(id, SagaType.SERVICE_CONFIGURATION, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + id + "' about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientConfigurationCreate")
    public void thenClientConfigurationCreationTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientConfiguration(clientId);
        Map<String, String> defaultConfigurationMap = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX);
        assertThat(clientConfiguration.isEmpty()).isFalse();
        defaultConfigurationMap.forEach((key, value) -> assertThat(clientConfiguration.getOrDefault(key, null)).isEqualTo(value));
    }

    @Test(dependsOnMethods = "thenClientConfigurationCreationTest")
    public void thenClientConfigurationCreationEventProducerTest() throws Exception {
        Map<String, String> defaultConfigurationMap = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX);
        ServiceConfigurationChanged serviceConfigurationChanged = waitFotConfigurationChangeEvent();
        assertThat(serviceConfigurationChanged.getProperties()).isNotNull();
        assertThat(serviceConfigurationChanged.getClientId()).isEqualTo(clientId);
        defaultConfigurationMap.forEach((key, value) -> assertThat(serviceConfigurationChanged.getProperties().getOrDefault(key, null)).isEqualTo(value));
    }


    @Test(dependsOnMethods = "thenClientConfigurationCreationEventProducerTest")
    public void whenClientUsesServiceConfigurationCreate() throws Exception {
        String id = configurationService.createClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId, ConfigurationUtil.getDefaultConfigurationMap(CLIENT_USES_SERVICE_PREFIX), true);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(id, SagaType.SERVICE_CONFIGURATION, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + id + "' about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientUsesServiceConfigurationCreate")
    public void thenClientUsesServiceConfigurationTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId);
        Map<String, String> defaultConfigurationMap = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_USES_SERVICE_PREFIX);
        defaultConfigurationMap.forEach((key, value) -> assertThat(clientConfiguration.getOrDefault(key, null)).isEqualTo(value));
    }

    @Test(dependsOnMethods = {"thenClientUsesServiceConfigurationTest", "thenClientConfigurationCreationTest"})
    public void thenConfigurationCreationMergingTest() throws Exception {
        // The merged configuration is containing service descriptor and global properties too.
        // The merge should be without these properties. Issue CPF-4157 was created
        Map<String, String> merged = configurationService.getMergedConfiguration(serviceDescriptor.getId(), clientId);
        ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX)
                .forEach((key, value) -> assertThat(merged.getOrDefault(key, null)).isEqualTo(value));
        ConfigurationUtil.getDefaultConfigurationMap(CLIENT_USES_SERVICE_PREFIX)
                .forEach((key, value) -> assertThat(merged.getOrDefault(key, null)).isEqualTo(value));
    }

    @Test(dependsOnMethods = "thenConfigurationCreationMergingTest")
    public void thenClientUsesServiceConfigurationCreationEventProducerTest() throws Exception {
        // event should contain both type of configuration.
        Map<String, String> defaultConfigurationMap = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_USES_SERVICE_PREFIX);
        defaultConfigurationMap.putAll(ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX));
        ServiceConfigurationChanged serviceConfigurationChanged = waitFotConfigurationChangeEvent();
        assertThat(serviceConfigurationChanged.getProperties()).isNotNull();
        assertThat(serviceConfigurationChanged.getClientId()).isEqualTo(clientId);
        ServiceDescriptor sdReturned = serviceConfigurationChanged.getServiceDescriptor();
        assertThat(sdReturned).isNotNull();
        assertThat(sdReturned.getId()).isEqualTo(serviceDescriptor.getId());
        assertThat(sdReturned.getArtifactId()).isEqualTo(serviceDescriptor.getArtifactId());
        assertThat(sdReturned.getGroupId()).isEqualTo(serviceDescriptor.getGroupId());
        defaultConfigurationMap.forEach((key, value) -> assertThat(serviceConfigurationChanged.getProperties().getOrDefault(key, null)).isEqualTo(value));
    }

    @Test(dependsOnMethods = "thenClientUsesServiceConfigurationCreationEventProducerTest")
    public void whenClientUsesServiceConfigurationDelete() throws Exception {
        String id = configurationService.deleteClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId, true);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(id, SagaType.SERVICE_CONFIGURATION, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientUsesServiceConfigurationDelete")
    public void thenClientUsesServiceConfigurationDeletionTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId);
        assertThat(clientConfiguration).isEmpty();
    }

    @Test(dependsOnMethods = "thenClientUsesServiceConfigurationDeletionTest")
    public void thenClientUsesServiceConfigurationDeletionEventProducerTest() throws Exception {
        // event should contain both type of configuration.
        Map<String, String> missing = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_USES_SERVICE_PREFIX);
        Map<String, String> contain = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX);
        ServiceConfigurationChanged serviceConfigurationChanged = waitFotConfigurationChangeEvent();
        assertThat(serviceConfigurationChanged.getProperties()).isNotNull();
        assertThat(serviceConfigurationChanged.getClientId()).isEqualTo(clientId);
        ServiceDescriptor sdReturned = serviceConfigurationChanged.getServiceDescriptor();
        assertThat(sdReturned).isNotNull();
        assertThat(sdReturned.getId()).isEqualTo(serviceDescriptor.getId());
        assertThat(sdReturned.getArtifactId()).isEqualTo(serviceDescriptor.getArtifactId());
        assertThat(sdReturned.getGroupId()).isEqualTo(serviceDescriptor.getGroupId());
        contain.forEach((key, value) -> assertThat(serviceConfigurationChanged.getProperties().getOrDefault(key, null)).isEqualTo(value));
        System.out.println("Properties:" + serviceConfigurationChanged.getProperties());
        System.out.println("Properties missing:" + missing);
        missing.forEach((key, value) -> assertThat(serviceConfigurationChanged.getProperties().containsKey(key)).isFalse());
    }

    @Test(dependsOnMethods = "thenClientUsesServiceConfigurationDeletionEventProducerTest")
    public void whenClientConfigurationDelete() throws Exception {
        String id = configurationService.deleteClientConfiguration(clientId, true);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(id, SagaType.SERVICE_CONFIGURATION, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientConfigurationDelete")
    public void thenClientConfigurationDeletionTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientConfiguration(clientId);
        assertThat(clientConfiguration).isEmpty();
    }

    @Test(dependsOnMethods = "thenClientConfigurationDeletionTest")
    public void thenClientConfigurationDeletionEventProducerTest() throws Exception {
        // event should contain both type of configuration.
        Map<String, String> missing = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_USES_SERVICE_PREFIX);
        missing.putAll(ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX));
        ServiceConfigurationChanged serviceConfigurationChanged = waitFotConfigurationChangeEvent();
        assertThat(serviceConfigurationChanged.getProperties()).isNotNull();
        assertThat(serviceConfigurationChanged.getClientId()).isEqualTo(clientId);
        assertThat(serviceConfigurationChanged.getServiceDescriptor()).isEqualTo(serviceDescriptor);
        missing.forEach((key, value) -> assertThat(serviceConfigurationChanged.getProperties().containsKey(key)).isFalse());
    }

    private ServiceConfigurationChanged waitFotConfigurationChangeEvent() throws InterruptedException {
        ServiceConfigurationChanged serviceConfigurationChanged = null;
        for (int i = 0; i < ATTEMPT; i++) {
            System.out.println("Waiting for configuration change event: " + i);
            serviceConfigurationChanged = configurationChangedHandler.pullMessage(SLEEP_S);
            if (serviceConfigurationChanged != null) {
                break;
            }
        }
        assertThat(serviceConfigurationChanged).as("Was the service configuration change received?").isNotNull();
        return serviceConfigurationChanged;
    }

    private void deleteEntities() throws Exception {
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        if (serviceDescriptor != null) {
            System.out.println("Service descriptor exists. It has to be deleted.");
            whenClientConfigurationDelete();
            thenClientConfigurationDeletionTest();
            whenClientUsesServiceConfigurationDelete();
            thenClientUsesServiceConfigurationDeletionTest();
            String sagaId = cusService.unassignServiceToClient(serviceDescriptor.getId(), clientId);
            boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.CLIENT_USES_SERVICE, ATTEMPT);
            if (sagaSuccess) {
                System.out.println("Client uses service was deleted.");
            } else {
                System.out.println("Client uses service was not assigned.");
            }
            serviceDescriptorService.deleteServiceDescriptor(GROUP_ID, ARTIFACT_ID);
            serviceDescriptor = null;
        } else {
            System.out.println("Service descriptor doesn't exist. ");
        }
    }
}
