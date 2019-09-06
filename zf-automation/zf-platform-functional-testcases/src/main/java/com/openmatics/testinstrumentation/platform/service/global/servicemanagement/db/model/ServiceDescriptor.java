package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openmatics.testinstrumentation.platform.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Marek Rosolik (marek.rojik@inventi.cz) on 10. 01. 2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceDescriptor {

    private String id;
    private String artifactId;
    private String groupId;
    private Boolean singleClient;
    private String appId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Client> clients = new ArrayList<>();

    public ServiceDescriptor() {}

    public ServiceDescriptor(String id, String artifactId, String groupId, Boolean singleClient, String appId) {
        this.id = id;
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.singleClient = singleClient;
        this.appId = appId;
    }

    public String getId() {
        return id;
    }

    public ServiceDescriptor setId(String id) {
        this.id = id;
        return this;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public ServiceDescriptor setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public ServiceDescriptor setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public List<Client> getClients() {
        return clients;
    }

    public ServiceDescriptor setClients(List<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Boolean getSingleClient() {
        return singleClient;
    }

    public ServiceDescriptor setSingleClient(Boolean singleClient) {
        this.singleClient = singleClient;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public ServiceDescriptor setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "id='" + id + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", singleClient=" + singleClient +
                ", appId=" + appId +
                ", clients=" + clients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(artifactId, that.artifactId) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(singleClient, that.singleClient) &&
                Objects.equals(appId, that.appId) &&
                Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artifactId, groupId, singleClient, appId, clients);
    }
}
