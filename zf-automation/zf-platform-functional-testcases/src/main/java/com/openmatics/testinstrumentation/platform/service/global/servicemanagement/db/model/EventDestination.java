package com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 17.04.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class EventDestination {

    private String id;

    private String destinationType;

    private String nameIdentifier;

    private String eventTypeId;


    public String getId() {
        return id;
    }

    public EventDestination setId(String id) {
        this.id = id;
        return this;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public EventDestination setDestinationType(String destinationType) {
        this.destinationType = destinationType;
        return this;
    }

    public String getNameIdentifier() {
        return nameIdentifier;
    }

    public EventDestination setNameIdentifier(String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
        return this;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public EventDestination setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
        return this;
    }

    @Override
    public String toString() {
        return "EventDestination{" +
                "id='" + id + '\'' +
                ", destinationType='" + destinationType + '\'' +
                ", nameIdentifier='" + nameIdentifier + '\'' +
                ", eventTypeId='" + eventTypeId + '\'' +
                '}';
    }
}
