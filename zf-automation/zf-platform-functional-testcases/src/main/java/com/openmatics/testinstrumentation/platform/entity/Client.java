package com.openmatics.testinstrumentation.platform.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Marek Rojik (marek.rojik@inventi.cz) on 10. 01. 2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Client {

    private String id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ServiceDescriptor> services;

    public Client() {}

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
        services = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CmsClient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", services=" + services +
                '}';
    }

    public String getId() {
        return id;
    }

    public Client setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public List<ServiceDescriptor> getServices() {
        return services;
    }

    public Client setServices(List<ServiceDescriptor> services) {
        this.services = services;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
