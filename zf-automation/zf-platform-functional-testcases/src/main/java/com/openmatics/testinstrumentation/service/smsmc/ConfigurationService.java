package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcConfigurationModifyApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcConfigurationReadApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.model.PropertyType;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 29.03.2019.
 */
public class ConfigurationService {
    private ISmsmcConfigurationReadApi configurationReadApi;
    private ISmsmcConfigurationModifyApi configurationModifyApi;

    public ConfigurationService(ISmsmcConfigurationReadApi configurationReadApi, ISmsmcConfigurationModifyApi configurationModifyApi) {
        this.configurationReadApi = configurationReadApi;
        this.configurationModifyApi = configurationModifyApi;
    }

    /**
     * Create request that creates client configuration.
     *
     * @param clientId      the configuration will belong to the client.
     * @param configuration the configuration will contain these map of properties.
     * @param reconfigure   Do you want create request that reconfigure all client's services?
     * @return sagaId or sagaUrl - depends on API. RestApi - URL, eventApi: sagaId.
     */
    public String createClientConfiguration(String clientId, Map<String, String> configuration, boolean reconfigure) {
        String sagaStatus = configurationModifyApi.saveClientConfiguration(clientId, configuration, reconfigure);
        return sagaStatus;
    }

    /**
     * Create request that creates client uses service configuration.
     *
     * @param serviceDescriptorId the configuration will belong to the service descriptor.
     * @param clientId            the configuration will belong to the client.
     * @param configuration       the configuration will contain these map of properties.
     * @param reconfigure         Do you want create request that reconfigure all client's services?
     * @return sagaId or sagaUrl - depends on API. RestApi - URL, eventApi: sagaId.
     */
    public String createClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, Map<String, String> configuration, boolean reconfigure) {
        String sagaStatus = configurationModifyApi.saveClientUsesServiceConfiguration(serviceDescriptorId, clientId, configuration, reconfigure);
        return sagaStatus;
    }

    /**
     * Load client configuration for specified client.
     *
     * @param clientId define the client
     * @return configuration for the client.
     * @throws ObjectNotExistsException the configuration not found
     */
    public Map<String, String> getClientConfiguration(String clientId) throws ObjectNotExistsException {
        Map<PropertyType, Map<String, String>> hierarchicalConfiguration = configurationReadApi.getHierarchicalConfiguration(null, clientId);
        Map<String, String> map = hierarchicalConfiguration.getOrDefault(PropertyType.CLIENT, new HashMap<>());
        return map;
    }

    /**
     * Load configuration for service descriptor and client.
     * The whole configuration is merged - the CLIENT configuration is override by the CLIENT_USES_SERVICE configuration.
     * @param clientId define the client.
     * @param serviceDescriptorId define the service descriptor.
     * @return configuration for the client.
     * @throws ObjectNotExistsException the configuration not found.
     */
    public Map<String, String> getMergedConfiguration(String serviceDescriptorId, String clientId) throws ObjectNotExistsException {
        Map<String, String> configuration = configurationReadApi.getConfiguration(serviceDescriptorId, clientId);
        return configuration;
    }

    /**
     * Load CLIENT_USES_SERVICE configuration for specified client.
     * @param clientId define the client
     * @param serviceDescriptorId define the service descriptor.
     * @return configuration for the client.
     * @throws ObjectNotExistsException the configuration not found
     */
    public Map<String, String> getClientUsesServiceConfiguration(String serviceDescriptorId, String clientId) throws ObjectNotExistsException {
        Map<PropertyType, Map<String, String>> hierarchicalConfiguration = configurationReadApi.getHierarchicalConfiguration(serviceDescriptorId, clientId);
        return hierarchicalConfiguration.getOrDefault(PropertyType.CLIENT_USES_SERVICE, new HashMap<>());
    }

    /**
     * Create client configuration deletion request.
     * @param clientId      the configuration will belong to the client.
     * @param reconfigure   Do you want create request that reconfigure all client's services?
     * @return sagaId or sagaUrl - depends on API. RestApi - URL, eventApi: sagaId.
     */
    public String deleteClientConfiguration(String clientId, boolean reconfigure) {
        String sagaStatus = configurationModifyApi.deleteClientConfiguration(clientId, reconfigure);
        return sagaStatus;
    }

    /**
     * Create request that delete CLIENT_USES_SERVICE configuration.
     * @param serviceDescriptorId the configuration will belong to the service descriptor.
     * @param clientId the configuration will belong to the client.
     * @param reconfigure Do you want create request that reconfigure all client's services?
     * @return sagaId or sagaUrl - depends on API. RestApi - URL, eventApi: sagaId.
     */
    public String deleteClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, boolean reconfigure) {
        String sagaStatus = configurationModifyApi.deleteClientUsesServiceConfiguration(serviceDescriptorId, clientId, reconfigure);
        return sagaStatus;
    }
}
