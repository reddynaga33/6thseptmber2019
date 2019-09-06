package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;

import java.util.Map;
import java.util.Objects;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 04.04.2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceConfigurationChanged {
    private ServiceDescriptor serviceDescriptor;
    private String clientId;
    private Map<String, String> properties;

    public ServiceConfigurationChanged() {
    }

    public ServiceConfigurationChanged(ServiceDescriptor serviceDescriptor, String clientId, Map<String, String> properties) {
        this.serviceDescriptor = serviceDescriptor;
        this.clientId = clientId;
        this.properties = properties;
    }

    public ServiceDescriptor getServiceDescriptor() {
        return serviceDescriptor;
    }

    public void setServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceDescriptor = serviceDescriptor;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
        ServiceConfigurationChanged that = (ServiceConfigurationChanged) o;
        return Objects.equals(serviceDescriptor, that.serviceDescriptor) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceDescriptor, clientId, properties);
    }

    @Override
    public String toString() {
        return "ServiceConfigurationChanged{" +
                "serviceDescriptor=" + serviceDescriptor +
                ", clientId='" + clientId + '\'' +
                ", properties=" + properties +
                '}';
    }
}
