package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 17.04.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Deployment {
    private String id;
    private String deploymentStatus;
    private String clientId;
    private String serviceVersionDescriptorId;
    private String serviceDescriptorId;

    private List<EventDestination> eventDestinations = new ArrayList();

    public String getId() {
        return id;
    }

    public Deployment setId(String id) {
        this.id = id;
        return this;
    }

    public String getDeploymentStatus() {
        return deploymentStatus;
    }

    public Deployment setDeploymentStatus(String deploymentStatus) {
        this.deploymentStatus = deploymentStatus;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public Deployment setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getServiceVersionDescriptorId() {
        return serviceVersionDescriptorId;
    }

    public Deployment setServiceVersionDescriptorId(String serviceVersionDescriptorId) {
        this.serviceVersionDescriptorId = serviceVersionDescriptorId;
        return this;
    }

    public String getServiceDescriptorId() {
        return serviceDescriptorId;
    }

    public Deployment setServiceDescriptorId(String serviceDescriptorId) {
        this.serviceDescriptorId = serviceDescriptorId;
        return this;
    }

    public List<EventDestination> getEventDestinations() {
        return eventDestinations;
    }

    public Deployment setEventDestinations(List<EventDestination> eventDestinations) {
        this.eventDestinations = eventDestinations;
        return this;
    }

    @Override
    public String toString() {
        return "Deployment{" +
                "id='" + id + '\'' +
                ", deploymentStatus='" + deploymentStatus + '\'' +
                ", clientId='" + clientId + '\'' +
                ", serviceVersionDescriptorId='" + serviceVersionDescriptorId + '\'' +
                ", serviceDescriptorId='" + serviceDescriptorId + '\'' +
                '}';
    }
}
