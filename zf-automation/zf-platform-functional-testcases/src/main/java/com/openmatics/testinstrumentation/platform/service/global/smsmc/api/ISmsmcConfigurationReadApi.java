package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.model.PropertyType;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;

import java.util.Map;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 02.04.2019.
 */
public interface ISmsmcConfigurationReadApi {

    /**
     * Get merged configuration according to method's parameters.
     * ---------------------------------------------------------
     * | serviceDescriptorId | clientId |    propertyType      |
     * |---------------------|----------|----------------------|
     * |         null        |   null   |       GLOBAL         |
     * |        nonnull      |   null   |  SERVICE_DESCRIPTOR  |
     * |         null        | nonnull  |       CLIENT         |
     * |         nonull      | nonnull  |  CLIENT_USES_SERVICE |
     * |---------------------|----------|----------------------|
     * NOTE: The propertyTypes are order according to priority. The CLIENT_USES_SERVICE has the highest priority.
     * NOTE: The merged configuration contains all property types above.
     *
     * @param serviceDescriptorId service descriptor id
     * @param clientId            client id
     * @return merged configuration
     * @throws ObjectNotExistsException configuration not found
     */
    Map<String, String> getConfiguration(String serviceDescriptorId, String clientId) throws ObjectNotExistsException;

    /**
     * Get service descriptor configuration
     *
     * @param serviceDescriptorId optional query parameter if the configuration should be limited for service descriptor ID
     * @param clientId            optional query parameter if get configuration for specified client
     * @return map contains properties for available {@link PropertyType}. Map has hierarchical structure (key is property type, value is map with properties)
     * @throws ObjectNotExistsException configuration not found
     */
    Map<PropertyType, Map<String, String>> getHierarchicalConfiguration(String serviceDescriptorId, String clientId) throws ObjectNotExistsException;
}
