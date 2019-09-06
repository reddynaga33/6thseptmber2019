package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.servicebus.Message;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventEnvelope;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventHeader;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventOutcome;
import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcConfigurationModifyApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.model.ServiceConfiguration;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.UUID;

import static com.openmatics.testinstrumentation.platform.utils.DestinationName.SMS_MC_CONFIGURATION;


/**
 * @author marek.rasocha@inventi.cz
 *         date: 29.03.2019.
 */
public class SmsmcConfigurationEventApi implements ISmsmcConfigurationModifyApi {
    private ServiceBusClient client;


    public SmsmcConfigurationEventApi(ServiceBusClient client) {
        this.client = client;
    }

    @Override
    public String saveClientConfiguration(String clientId, Map<String, String> configuration, boolean reconfigure) {
        return produceEvent(Operation.CREATE, null, clientId, configuration, reconfigure);
    }

    @Override
    public String saveClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, Map<String, String> configuration, boolean reconfigure) {
        return produceEvent(Operation.CREATE, serviceDescriptorId, clientId, configuration, reconfigure);
    }

    @Override
    public String deleteClientConfiguration(String clientId, boolean reconfigure) {
        return produceEvent(Operation.DELETE, null, clientId, null, reconfigure);
    }

    @Override
    public String deleteClientUsesServiceConfiguration(String serviceDescriptorId, String clientId, boolean reconfigure) {
        return produceEvent(Operation.DELETE, serviceDescriptorId, clientId, null, reconfigure);
    }

    private String produceEvent(Operation operation, String serviceDescriptorId, String clientId, Map<String, String> configuration, boolean reconfigure) {
        String eventId = UUID.randomUUID().toString();
        String causeEventId = UUID.randomUUID().toString();
        String correlationId = UUID.randomUUID().toString();
        String sagaId = UUID.randomUUID().toString();

        EventHeader eventHeader = new EventHeader(eventId, new DateTime(), SMS_MC_CONFIGURATION, causeEventId, correlationId, sagaId);

        ServiceConfiguration serviceConfiguration = new ServiceConfiguration(serviceDescriptorId, clientId, reconfigure, configuration);


        EventOutcome<ServiceConfiguration> payload = new EventOutcome<>(operation, serviceConfiguration);
        try {
            EventEnvelope<EventOutcome<ServiceConfiguration>> event = new EventEnvelope<>(eventHeader, payload);
            String body = JsonUtils.toJson(event);
            client.sendToTopic(SMS_MC_CONFIGURATION, new Message(body));
            return sagaId;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("I can't serialize the object", e);
        }
    }

}
