package com.openmatics.testinstrumentation.platform.service.global.smsmc.db.model;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 24.04.2019.
 */
public class ClientUsesService {
    private String id;
    private String clientId;
    private String serviceDescriptorId;

    public String getId() {
        return id;
    }

    public ClientUsesService setId(String id) {
        this.id = id;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public ClientUsesService setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getServiceDescriptorId() {
        return serviceDescriptorId;
    }

    public ClientUsesService setServiceDescriptorId(String serviceDescriptorId) {
        this.serviceDescriptorId = serviceDescriptorId;
        return this;
    }
}
