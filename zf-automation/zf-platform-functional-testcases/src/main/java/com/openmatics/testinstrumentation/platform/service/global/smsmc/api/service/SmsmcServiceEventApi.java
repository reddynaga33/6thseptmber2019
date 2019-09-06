package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.servicebus.Message;
import com.openmatics.testinstrumentation.platform.entity.servicestartedmc.MessageTypeMC;
import com.openmatics.testinstrumentation.platform.entity.servicestartedmc.ServiceStartedMC;
import com.openmatics.testinstrumentation.platform.entity.servicestartedmc.ServiceStartedMCDestination;
import com.openmatics.testinstrumentation.platform.entity.servicestartedmc.ServiceStartedMCDestinationType;
import com.openmatics.testinstrumentation.platform.entity.servicestartedmc.ServiceStartedMCDirection;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventEnvelope;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventHeader;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventOutcome;
import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Destination;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_MC_SERVICE_DESCRIPTOR_CHANGE;
import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_MC_SERVICE_STARTED;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 02.04.2019.
 */
public class SmsmcServiceEventApi {

    private ServiceBusClient client;

    public SmsmcServiceEventApi(ServiceBusClient client) {
        this.client = client;
    }

    public void sendServiceStartedMC(String groupId, String artifactId, List<Destination> destinations) {
        EventHeader eventHeader = new EventHeader(UUID.randomUUID().toString(), new DateTime(), SMS_MC_SERVICE_DESCRIPTOR_CHANGE, UUID.randomUUID().toString());

        ServiceStartedMC serviceStartedMC = new ServiceStartedMC();
        serviceStartedMC.setGroupId(groupId)
                .setArtifactId(artifactId)
                .setServiceStartedMCDestinations(convertDestination(destinations));
        try {
            EventEnvelope<ServiceStartedMC> event = new EventEnvelope<>(eventHeader, serviceStartedMC);
            System.out.println(JsonUtils.toJson(event));
            client.sendToTopic(SMS_MC_SERVICE_STARTED, new Message(JsonUtils.toJson(event)));
        } catch (JsonProcessingException e) {
            System.err.println("I can serialize the object due to " + e.getMessage());
            throw new RuntimeException("I can serialize the object", e);
        }
    }

    public String sendServiceDescriptorChange(Operation operation, ServiceDescriptor serviceDescriptor) {
        try {
            String eventId = UUID.randomUUID().toString();
            String causeEventId = UUID.randomUUID().toString();
            String correlationId = UUID.randomUUID().toString();
            String sagaId = UUID.randomUUID().toString();

            EventHeader eventHeader = new EventHeader(eventId, new DateTime(), SMS_MC_SERVICE_DESCRIPTOR_CHANGE, causeEventId, correlationId, sagaId);
            EventOutcome<ServiceDescriptor> cudRequestEvent = new EventOutcome<>(operation, serviceDescriptor);
            EventEnvelope<EventOutcome> event = new EventEnvelope<>(eventHeader, cudRequestEvent);
            client.sendToTopic(SMS_MC_SERVICE_DESCRIPTOR_CHANGE, new Message(JsonUtils.toJson(event)));
            return sagaId;
        } catch (JsonProcessingException e) {
            System.err.println("I can serialize the object due to " + e.getMessage());
            throw new RuntimeException("I can serialize the object", e);
        }
    }

    private List<ServiceStartedMCDestination> convertDestination(List<Destination> destinations) {
        List<ServiceStartedMCDestination> collect = destinations.stream()
                .map(destination -> new ServiceStartedMCDestination(destination.getNameIdentifier(),
                        ServiceStartedMCDestinationType.valueOf(destination.getType().name()),
                        ServiceStartedMCDirection.valueOf(destination.getDirection().name()),
                        destination.getMessageType() != null ? new MessageTypeMC(destination.getMessageType().getName(), destination.getMessageType().getVersion()) : null,
                        destination.getPartitioning() == null ? false : destination.getPartitioning(),
                        destination.getSingleClient() == null ? false : destination.getSingleClient()
                )).collect(Collectors.toList());
        return collect;
    }
}
