package com.openmatics.test.functional.platform.servicemanagement_mc.configuration;

import com.openmatics.test.functional.DbBaseTest;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.SmsmcApiProvider;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.db.repository.ServiceDescriptorRepository;
import com.openmatics.testinstrumentation.service.smsmc.ConfigurationService;
import com.openmatics.testinstrumentation.service.smsmc.SagaService;
import com.openmatics.testinstrumentation.service.smsmc.ServiceDescriptorService;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static com.openmatics.test.functional.platform.servicemanagement_mc.configuration.ConfigurationUtil.ARTIFACT_ID;
import static com.openmatics.test.functional.platform.servicemanagement_mc.configuration.ConfigurationUtil.GROUP_ID;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 28.03.2019.
 */
@Test(testName = "ConfigurationRestAcceptanceTest")
public class ConfigurationRestAcceptanceTest extends DbBaseTest {
    private static final String CLIENT_PREFIX = "client-property";
    private static final String CLIENT_USES_SERVICE_PREFIX = "client-property";
    private static final String APP_ID = "it-doesnt-matter";
    private static final int ATTEMPT = 10;
    private ServiceDescriptorService serviceDescriptorService;
    private ConfigurationService configurationService;
    private SagaService sagaService;

    private ServiceDescriptor serviceDescriptor;
    private String clientId;


    @BeforeClass
    public void beforeConfigurationAcceptanceTest() throws Exception {
        platformInstrumentation.getClientConf().getClient().getId();
        ServiceDescriptorRepository serviceDescriptorRepository = new ServiceDescriptorRepository(connectionFactory);
        SmsmcApiProvider smsmcApiProvider = new SmsmcApiProvider(platformInstrumentation);
        configurationService = new ConfigurationService(smsmcApiProvider.getRestApi().getConfigurationReadApi(), smsmcApiProvider.getRestApi().getConfigurationModifyApi());
        sagaService = new SagaService(smsmcApiProvider.getRestApi().getSagaApi());
        serviceDescriptorService = new ServiceDescriptorService(serviceDescriptorRepository, smsmcApiProvider.getEventApi().getSmsmcServiceEventApi(), smsmcApiProvider.getRestApi().getServiceApi());
        serviceDescriptorService.deleteServiceDescriptor(GROUP_ID, ARTIFACT_ID);
    }

    @AfterClass
    public void afterConfigurationAcceptanceTest() throws Exception {
        whenClientConfigurationDelete();
        thenClientConfigurationDeletionTest();
        whenClientUsesServiceConfigurationDelete();
        thenClientUsesServiceConfigurationDeletionTest();
        serviceDescriptorService.deleteServiceDescriptor(GROUP_ID, ARTIFACT_ID);
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
    public void givenLoadServiceDescriptor() throws Exception {
        String sagaId = serviceDescriptorService.saveServiceDescriptor(GROUP_ID, ARTIFACT_ID, APP_ID);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaId, SagaType.SERVICE_DESCRIPTOR, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaId + "' about saving service descriptor success?").isTrue();
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
        serviceDescriptor = serviceDescriptorService.findByGroupIdAndArtifactId(GROUP_ID, ARTIFACT_ID);
    }

    @Test(dependsOnMethods = "givenLoadServiceDescriptor")
    public void whenClientConfigurationCreate() throws Exception {
        String sagaStatusUrl = configurationService.createClientConfiguration(clientId, ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX), false);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaStatusUrl, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaStatusUrl + "' about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientConfigurationCreate")
    public void thenClientConfigurationCreationTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientConfiguration(clientId);
        Map<String, String> defaultConfigurationMap = ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX);
        defaultConfigurationMap.forEach((key, value) -> assertThat(clientConfiguration.getOrDefault(key, null)).isEqualTo(value));
    }


    @Test(dependsOnMethods = "givenLoadServiceDescriptor")
    public void whenClientUsesServiceConfigurationCreate() throws Exception {
        String sagaStatusUrl = configurationService.createClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId, ConfigurationUtil.getDefaultConfigurationMap(CLIENT_PREFIX), false);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaStatusUrl, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga '" + sagaStatusUrl + "' about creating configuration in state success?").isTrue();
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
    public void whenClientUsesServiceConfigurationDelete() throws Exception {
        String sagaStatus = configurationService.deleteClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId, false);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaStatus, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientUsesServiceConfigurationDelete")
    public void thenClientUsesServiceConfigurationDeletionTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientUsesServiceConfiguration(serviceDescriptor.getId(), clientId);
        assertThat(clientConfiguration).isEmpty();
    }

    @Test(dependsOnMethods = "thenConfigurationCreationMergingTest")
    public void whenClientConfigurationDelete() throws Exception {
        String sagaStatus = configurationService.deleteClientConfiguration(clientId, false);
        boolean sagaSuccess = sagaService.isSagaStatusSuccess(sagaStatus, ATTEMPT);
        assertThat(sagaSuccess).as("Is saga about creating configuration in state success?").isTrue();
    }

    @Test(dependsOnMethods = "whenClientConfigurationDelete")
    public void thenClientConfigurationDeletionTest() throws Exception {
        Map<String, String> clientConfiguration = configurationService.getClientConfiguration(clientId);
        assertThat(clientConfiguration).isEmpty();
    }

}
