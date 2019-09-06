package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import java.util.Map;

/**
 * The api is async. Each method return the saga id.
 *
 * @author marek.rasocha@inventi.cz
 *         date: 02.04.2019.
 */
public interface ISmsmcConfigurationModifyApi {

    /**
     * Save client configuration
     *
     * @param clientId      client identificator
     * @param configuration the saved config map
     * @param reconfigure the configuration change will be produced.
     * @return saga id
     */
    String saveClientConfiguration(String clientId, Map<String, String> configuration, boolean reconfigure);

    /**
     * Save client-uses-service configuration
     *
     * @param clientId            client identificator
     * @param serviceDescriptorId service descriptor id
     * @param configuration       the saved config map
     * @param reconfigure the configuration change will be produced.
     * @return saga id
     */
    String saveClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, Map<String, String> configuration, boolean reconfigure);

    /**
     * Delete client configuration.
     *
     * @param clientId client identificator
     * @param reconfigure the configuration change will be produced.
     * @return saga id
     */
    String deleteClientConfiguration(String clientId, boolean reconfigure);

    /**
     * Delete client configuration.
     *
     * @param clientId            client identificator
     * @param serviceDescriptorId service descriptor id
     * @param reconfigure the configuration change will be produced.
     * @return saga id
     */
    String deleteClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, boolean reconfigure);
}
