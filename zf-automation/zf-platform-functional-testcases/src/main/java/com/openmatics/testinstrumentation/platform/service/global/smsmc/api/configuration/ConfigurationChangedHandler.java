package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventEnvelope;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventOutcome;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.entity.ServiceConfigurationChanged;
import com.openmatics.testinstrumentation.utils.JsonUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 04.04.2019.
 */
public class ConfigurationChangedHandler implements IMessageHandler {
    private static final String PREFIX_LOG = "ConfigurationChangedHandler: ";
    private static final JavaType JAVA_TYPE = TypeFactory.defaultInstance().constructParametricType(EventEnvelope.class, TypeFactory.defaultInstance().constructParametricType(EventOutcome.class, ServiceConfigurationChanged.class));
    private BlockingQueue<ServiceConfigurationChanged> storage = new ArrayBlockingQueue<>(100);
    private final String groupId;
    private final String artifactId;
    private final String clientId;

    public ConfigurationChangedHandler(String artifactId, String clientId, String groupId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.clientId = clientId;
    }


    @Override
    public CompletableFuture<Void> onMessageAsync(IMessage message) {
        List<byte[]> binaryData = message.getMessageBody().getBinaryData();
        if (!binaryData.isEmpty()) {
            String jsonBody = new String(binaryData.get(0), Charset.forName("UTF-8"));
            try {
                ServiceConfigurationChanged serviceConfigurationChanged = deserializeMessage(jsonBody);
                if (serviceConfigurationChanged != null && clientId.equalsIgnoreCase(serviceConfigurationChanged.getClientId())) {
                    if (serviceConfigurationChanged.getServiceDescriptor() != null) {
                        String groupId = serviceConfigurationChanged.getServiceDescriptor().getGroupId();
                        String artifactId = serviceConfigurationChanged.getServiceDescriptor().getArtifactId();
                        if (this.groupId.equalsIgnoreCase(groupId) && this.artifactId.equalsIgnoreCase(artifactId)) {
                            storage.offer(serviceConfigurationChanged);
                        } else {
                            System.out.println(PREFIX_LOG + "The received message was rejected due to service descriptor validation: " + serviceConfigurationChanged);
                        }
                    } else {
                        storage.offer(serviceConfigurationChanged);
                    }
                } else {
                    System.out.println(PREFIX_LOG + "The received message was rejected due to client validation: " + serviceConfigurationChanged);
                }
            } catch (IOException e) {
                System.err.println(PREFIX_LOG + "Received message can't be configured due to " + e.getMessage());
            }
        }

        return CompletableFuture.completedFuture(null);
    }

    private ServiceConfigurationChanged deserializeMessage(String jsonBody) throws IOException {
        EventEnvelope<EventOutcome<ServiceConfigurationChanged>> eventEnvelope = JsonUtils.fromJson(jsonBody, JAVA_TYPE);
        if (eventEnvelope != null && eventEnvelope.getPayload() != null) {
            return eventEnvelope.getPayload().getPayload();
        }
        return null;
    }

    @Override
    public void notifyException(Throwable exception, ExceptionPhase phase) {
        System.err.println("The handler throws an error: " + exception);
    }

    public ServiceConfigurationChanged pullMessage(long second) throws InterruptedException {
        ServiceConfigurationChanged poll = storage.poll(second, TimeUnit.SECONDS);
        return poll;
    }


}
