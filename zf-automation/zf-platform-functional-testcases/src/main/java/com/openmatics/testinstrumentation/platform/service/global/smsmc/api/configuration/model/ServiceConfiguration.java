package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.model;

import java.util.Map;
import java.util.Objects;

public class ServiceConfiguration {

    private String id;
    private String serviceDescriptorId;
    private String clientId;
    private boolean reconfigure;

    private Map<String, String> properties;

    public ServiceConfiguration() {
    }

    public ServiceConfiguration(String serviceDescriptorId, String clientId, boolean reconfigure) {
        this.serviceDescriptorId = serviceDescriptorId;
        this.clientId = clientId;
        this.reconfigure = reconfigure;
    }

    public ServiceConfiguration(String serviceDescriptorId, String clientId, boolean reconfigure, Map<String, String> properties) {
        this.serviceDescriptorId = serviceDescriptorId;
        this.clientId = clientId;
        this.properties = properties;
        this.reconfigure = reconfigure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceDescriptorId() {
        return serviceDescriptorId;
    }

    public void setServiceDescriptorId(String serviceDescriptorId) {
        this.serviceDescriptorId = serviceDescriptorId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isReconfigure() {
        return reconfigure;
    }

    public void setReconfigure(boolean reconfigure) {
        this.reconfigure = reconfigure;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceConfiguration that = (ServiceConfiguration) o;
        return reconfigure == that.reconfigure &&
                Objects.equals(id, that.id) &&
                Objects.equals(serviceDescriptorId, that.serviceDescriptorId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceDescriptorId, clientId, reconfigure, properties);
    }
}
