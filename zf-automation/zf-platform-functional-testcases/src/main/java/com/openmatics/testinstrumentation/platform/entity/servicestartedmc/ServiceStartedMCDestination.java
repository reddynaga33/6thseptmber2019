package com.openmatics.testinstrumentation.platform.entity.servicestartedmc;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 01.03.2019.
 */
public class ServiceStartedMCDestination {

    private String nameIdentifier;
    private ServiceStartedMCDestinationType type;
    private ServiceStartedMCDirection serviceStartedMCDirection;
    private MessageTypeMC messageType;
    private boolean partitioning;
    private boolean singleClient;

    public ServiceStartedMCDestination() {}

    public ServiceStartedMCDestination(String nameIdentifier, ServiceStartedMCDestinationType type,
                                       ServiceStartedMCDirection serviceStartedMCDirection, MessageTypeMC messageType) {
        this(nameIdentifier, type, serviceStartedMCDirection, messageType, false, false);
    }

    public ServiceStartedMCDestination(String nameIdentifier, ServiceStartedMCDestinationType type,
                                       ServiceStartedMCDirection serviceStartedMCDirection, MessageTypeMC messageType,
                                       boolean partitioning, boolean singleClient) {
        this.nameIdentifier = nameIdentifier;
        this.type = type;
        this.serviceStartedMCDirection = serviceStartedMCDirection;
        this.messageType = messageType;
        this.partitioning = partitioning;
        this.singleClient = singleClient;
    }

    public ServiceStartedMCDestinationType getType() {
        return type;
    }

    public ServiceStartedMCDestination setType(ServiceStartedMCDestinationType type) {
        this.type = type;
        return this;
    }

    public ServiceStartedMCDirection getServiceStartedMCDirection() {
        return serviceStartedMCDirection;
    }

    public ServiceStartedMCDestination setServiceStartedMCDirection(ServiceStartedMCDirection serviceStartedMCDirection) {
        this.serviceStartedMCDirection = serviceStartedMCDirection;
        return this;
    }

    public boolean getPartitioning() {
        return partitioning;
    }

    public ServiceStartedMCDestination setPartitioning(boolean partitioning) {
        this.partitioning = partitioning;
        return this;
    }

    public boolean getSingleClient() {
        return singleClient;
    }

    public ServiceStartedMCDestination setSingleClient(boolean singleClient) {
        this.singleClient = singleClient;
        return this;
    }

    public String getNameIdentifier() {
        return nameIdentifier;
    }

    public ServiceStartedMCDestination setNameIdentifier(String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
        return this;
    }

    public MessageTypeMC getMessageType() {
        return messageType;
    }

    public ServiceStartedMCDestination setMessageType(MessageTypeMC messageType) {
        this.messageType = messageType;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceStartedMCDestination{" +
                "nameIdentifier='" + nameIdentifier + '\'' +
                ", type=" + type +
                ", serviceStartedMCDirection=" + serviceStartedMCDirection +
                ", partitioning=" + partitioning +
                ", singleClient=" + singleClient +
                '}';
    }
}
