package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventEnvelope;
import com.openmatics.testinstrumentation.platform.entity.wrapper.EventOutcome;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.DestinationChanged;
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
 *         date: 12.04.2019.
 */
public class DestinationChangeHandler implements IMessageHandler {
    private static final JavaType JAVA_TYPE = TypeFactory.defaultInstance().constructParametricType(EventEnvelope.class, TypeFactory.defaultInstance().constructParametricType(EventOutcome.class, DestinationChanged.class));
    private static final String PREFIX_LOG = "DestinationChangeHandler: ";
    private BlockingQueue<DestinationChanged> storage = new ArrayBlockingQueue<>(100);
    private final String groupId;
    private final String artifactId;

    public DestinationChangeHandler(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    @Override
    public CompletableFuture<Void> onMessageAsync(IMessage message) {
        List<byte[]> binaryData = message.getMessageBody().getBinaryData();
        if (!binaryData.isEmpty()) {
            String jsonBody = new String(binaryData.get(0), Charset.forName("UTF-8"));
            try {
                DestinationChanged serviceConfigurationChanged = deserializeMessage(jsonBody);
                if (serviceConfigurationChanged != null && serviceConfigurationChanged.getServiceDescriptor() != null) {
                    String groupId = serviceConfigurationChanged.getServiceDescriptor().getGroupId();
                    String artifactId = serviceConfigurationChanged.getServiceDescriptor().getArtifactId();
                    if (this.groupId.equalsIgnoreCase(groupId) && this.artifactId.equalsIgnoreCase(artifactId)) {
                        storage.offer(serviceConfigurationChanged);
                    } else {
                        System.out.println(PREFIX_LOG + "The received message was rejected due to service descriptor validation: " + serviceConfigurationChanged);
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


    private DestinationChanged deserializeMessage(String jsonBody) throws IOException {
        EventEnvelope<EventOutcome<DestinationChanged>> eventEnvelope = JsonUtils.fromJson(jsonBody, JAVA_TYPE);
        if (eventEnvelope != null && eventEnvelope.getPayload() != null) {
            return eventEnvelope.getPayload().getPayload();
        }
        return null;
    }

    @Override
    public void notifyException(Throwable exception, ExceptionPhase phase) {
        System.err.println("The handler throws an error: " + exception);
    }

    public DestinationChanged pullMessage(long second) throws InterruptedException {
        DestinationChanged poll = storage.poll(second, TimeUnit.SECONDS);
        return poll;
    }

}
