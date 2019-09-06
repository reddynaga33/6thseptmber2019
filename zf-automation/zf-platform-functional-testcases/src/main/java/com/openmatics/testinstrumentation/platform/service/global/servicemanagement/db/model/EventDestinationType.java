package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 17.04.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventDestinationType {
    private String id;
    private String name;
    private String version;

    public String getId() {
        return id;
    }

    public EventDestinationType setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventDestinationType setName(String name) {
        this.name = name;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public EventDestinationType setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "EventDestinationType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
