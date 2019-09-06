package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.client.model;

import java.util.Objects;

/**
 * Created by Marek Rojik (marek.rojik@inventi.cz) on 07. 01. 2019
 */
public class ClientUsesService {

    private String id;

    private String serviceDescriptorId;

    private String clientId;

    public ClientUsesService() {
    }

    public ClientUsesService(String serviceDescriptorId, String clientId) {
        this.serviceDescriptorId = serviceDescriptorId;
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public ClientUsesService setId(String id) {
        this.id = id;
        return this;
    }

    public String getServiceDescriptorId() {
        return serviceDescriptorId;
    }

    public ClientUsesService setServiceDescriptorId(String serviceDescriptorId) {
        this.serviceDescriptorId = serviceDescriptorId;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public ClientUsesService setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientUsesService that = (ClientUsesService) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(serviceDescriptorId, that.serviceDescriptorId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, serviceDescriptorId, clientId);
    }

    @Override
    public String toString() {
        return "ClientUsesService{" +
                "id='" + id + '\'' +
                ", serviceDescriptorId='" + serviceDescriptorId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
