package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openmatics.testinstrumentation.platform.entity.Client;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;

import java.util.Objects;

/**
 * Created by Marek Rojik (marek.rojik@inventi.cz) on 10. 01. 2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Destination {

    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nameIdentifier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DestinationType type;
    private Boolean partitioning;
    private Direction direction;
    private Client client;
    private ServiceDescriptor serviceDescriptor;
    private Boolean singleClient;

    /**
     * Attribute will be deleted after Message dispatcher wouldn't need to know message type of queue destination
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MessageType messageType;

    public Destination() {
    }

    /**
     * Create multi-client destination with specified arguments.
     *
     * @param id             id of the destination
     * @param nameIdentifier name of the destination
     * @param type           type of the destination
     */
    public Destination(String id, String nameIdentifier, DestinationType type, Direction direction) {
        this(id, nameIdentifier, type, false, direction);
    }

    public Destination(String id, String nameIdentifier, DestinationType type, Boolean singleClient, Direction direction) {
        this(id, nameIdentifier, type, singleClient, direction, null);
    }

    public Destination(String id, String nameIdentifier, DestinationType type, Boolean singleClient, Direction direction, Boolean partitioning) {
        this(id, nameIdentifier, type, singleClient, direction, partitioning, null);
    }

    public Destination(String id, String nameIdentifier, DestinationType type, Boolean singleClient, Direction direction, Boolean partitioning, MessageType messageType) {
        this.id = id;
        this.nameIdentifier = nameIdentifier;
        this.type = type;
        this.partitioning = partitioning;
        this.direction = direction;
        this.singleClient = singleClient;
        this.messageType = messageType;
    }

    public String getId() {
        return id;
    }

    public Destination setId(String id) {
        this.id = id;
        return this;
    }

    public String getNameIdentifier() {
        return nameIdentifier;
    }

    public Destination setNameIdentifier(String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
        return this;
    }

    public DestinationType getType() {
        return type;
    }

    public Destination setType(DestinationType type) {
        this.type = type;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Destination setClient(Client client) {
        this.client = client;
        return this;
    }

    public ServiceDescriptor getServiceDescriptor() {
        return serviceDescriptor;
    }

    public Destination setServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceDescriptor = serviceDescriptor;
        return this;
    }

    public Boolean getSingleClient() {
        return singleClient;
    }

    public Destination setSingleClient(Boolean singleClient) {
        this.singleClient = singleClient;
        return this;
    }

    public Boolean getPartitioning() {
        return partitioning;
    }

    public Destination setPartitioning(Boolean partitioning) {
        this.partitioning = partitioning;
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public Destination setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Destination setMessageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameIdentifier, that.nameIdentifier) &&
                type == that.type &&
                Objects.equals(singleClient, that.singleClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameIdentifier, type, singleClient);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id='" + id + '\'' +
                ", nameIdentifier='" + nameIdentifier + '\'' +
                ", type=" + type +
                ", singleClient=" + singleClient +
                ", messageType=" + messageType +
                '}';
    }
}
