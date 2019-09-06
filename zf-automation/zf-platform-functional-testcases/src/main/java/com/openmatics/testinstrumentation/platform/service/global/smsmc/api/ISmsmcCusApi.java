package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 24.04.2019.
 */
public interface ISmsmcCusApi {
    /**
     * Assign service descriptor to the client.
     *
     * @param serviceDescriptorId service descriptor id
     * @param clientId            client id
     * @return sagaUrl
     */
    String assignServiceToClient(String serviceDescriptorId, String clientId);

    /**
     * Unassign service descriptor to the client.
     *
     * @param serviceDescriptorId service descriptor id
     * @param clientId            client id
     * @return sagaUrl
     */
    String unassignServiceToClient(String serviceDescriptorId, String clientId);
}
