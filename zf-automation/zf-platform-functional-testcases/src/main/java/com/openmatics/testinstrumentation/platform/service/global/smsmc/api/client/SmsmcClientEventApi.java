package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.servicebus.Message;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventEnvelope;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventHeader;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventOutcome;
import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcCusApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.client.model.ClientUsesService;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import org.joda.time.DateTime;

import java.util.UUID;

import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_MC_CLIENT_USES_SERVICE;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 02.04.2019.
 */
public class SmsmcClientEventApi implements ISmsmcCusApi {
    private ServiceBusClient client;

    public SmsmcClientEventApi(ServiceBusClient client) {
        this.client = client;
    }

    public String assignServiceToClient(String serviceDescriptorId, String clientId) {
        String sagaId = produceEvent(Operation.CREATE, serviceDescriptorId, clientId);
        return sagaId;
    }

    public String unassignServiceToClient(String serviceDescriptorId, String clientId) {
        String sagaId = produceEvent(Operation.DELETE, serviceDescriptorId, clientId);
        return sagaId;
    }


    private String produceEvent(Operation operation, String serviceDescriptorId, String clientId) {
        String eventId = UUID.randomUUID().toString();
        String causeEventId = UUID.randomUUID().toString();
        String correlationId = UUID.randomUUID().toString();
        String sagaId = UUID.randomUUID().toString();

        EventHeader eventHeader = new EventHeader(eventId, new DateTime(), SMS_MC_CLIENT_USES_SERVICE, causeEventId, correlationId, sagaId);

        EventOutcome<ClientUsesService> payload = new EventOutcome<>(operation, new ClientUsesService(serviceDescriptorId, clientId));
        EventEnvelope<EventOutcome<ClientUsesService>> event = new EventEnvelope<>(eventHeader, payload);

        try {
            client.sendToTopic(SMS_MC_CLIENT_USES_SERVICE, new Message(JsonUtils.toJson(event)));
        } catch (JsonProcessingException e) {
            System.err.println("I can't serialize the object due to " + e.getMessage());
            throw new RuntimeException("I can't serialize the object", e);
        }
        return sagaId;
    }
}
