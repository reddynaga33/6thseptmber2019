package com.openmatics.testinstrumentation.utils.azure;

import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.IMessageSender;
import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.ISubscriptionClient;
import com.microsoft.azure.servicebus.ITopicClient;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.SubscriptionClient;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.microsoft.azure.servicebus.ReceiveMode.RECEIVEANDDELETE;

public class ServiceBusClient {
    private final String connectionString;

    public ServiceBusClient(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Send message to the topic.
     *
     * @param topicName topic name
     * @param message   message for sending
     */
    public void sendToTopic(String topicName, Message message) {
        sendToTopic(topicName, Collections.singletonList(message));
        String string = message.getMessageBody().toString();
    }

    /**
     * Send messages to the topic.
     *
     * @param topicName topic name
     * @param messages  messages for sending
     */
    public void sendToTopic(String topicName, List<Message> messages) {
        final IMessageSender sender;
        try {
            sender = createTopicClient(topicName);
            send(sender, messages);
        } catch (ServiceBusException | InterruptedException e) {
            System.err.println("The topic can't be send due to " + e);
        }
    }

    /**
     * Send message to the queue.
     *
     * @param queueName queueName
     * @param message   message for sending
     */
    public void sendToQueue(String queueName, Message message) {
        sendToQueue(queueName, Collections.singletonList(message));
    }

    /**
     * Send messages to the queue.
     *
     * @param queueName queueName
     * @param messages  messages for sending
     */
    public void sendToQueue(String queueName, List<Message> messages) {
        final IMessageSender sender;
        try {
            sender = createQueueClient(queueName);
            send(sender, messages);
        
            sender.close();
        } catch (ServiceBusException | InterruptedException e) {
            System.err.println("The connection is not established to service bus due to " + e);
        }
    }

    /**
     * Receive messages from the queue.
     *
     * @param queueName      queue name
     * @param messageHandler Handler that processed the received messages.
     * @return queueClient. It should be closed before terminating.
     */
    public IQueueClient receiveQueueMessage(String queueName, IMessageHandler messageHandler) {
        try {
            System.out.println("Registering queue handler.");
            IQueueClient client = createQueueClient(queueName);
            ExecutorService executorService = Executors.newCachedThreadPool();
            client.registerMessageHandler(
                    messageHandler,
                    executorService
            );
            return client;
        } catch (ServiceBusException | InterruptedException e) {
            System.err.println("The connection is not established to service bus due to " + e);
        }
        return null;
    }

    /**
     * Receive messages from the topic's subscription.
     *
     * @param topicName        queue name.
     * @param subscriptionName subscription name.
     * @param messageHandler   Handler that processed the received messages.
     * @return ISubscriptionClient. It should be closed before terminating.
     */
    public ISubscriptionClient receiveTopicMessage(String topicName, String subscriptionName, IMessageHandler messageHandler) {
        try {
            ISubscriptionClient client = createSubscriptionClient(topicName, subscriptionName);
            ExecutorService executorService = Executors.newCachedThreadPool();
            client.registerMessageHandler(
                    messageHandler,
                    executorService
            );
            return client;
        } catch (ServiceBusException | InterruptedException e) {
            System.err.println("The connection is not established to service bus due to " + e);
        }
        return null;
    }

    private IQueueClient createQueueClient(String queueName) throws ServiceBusException, InterruptedException {
        ConnectionStringBuilder builder = new ConnectionStringBuilder(connectionString, queueName);
        IQueueClient queueClient = new QueueClient(builder, RECEIVEANDDELETE);
        return queueClient;
    }

    private ITopicClient createTopicClient(String topicName) throws ServiceBusException, InterruptedException {
        ConnectionStringBuilder builder = new ConnectionStringBuilder(connectionString, topicName);
        ITopicClient topicClient = new TopicClient(builder);

        return topicClient;
    }

    public ISubscriptionClient createSubscriptionClient(String topicName, String subscriptionName) throws ServiceBusException, InterruptedException {
        String entityName = String.format("%s/subscriptions/%s", topicName, subscriptionName);
        ConnectionStringBuilder builder = new ConnectionStringBuilder(connectionString, entityName);
        ISubscriptionClient subscriptionClient = new SubscriptionClient(builder, RECEIVEANDDELETE);
        return subscriptionClient;
    }


    private void send(IMessageSender messageSender, List<Message> messages) {
        List<CompletableFuture<Void>> completableFutures = messages.stream()
                .map(messageSender::sendAsync)
                .collect(Collectors.toList());
        completableFutures.forEach(CompletableFuture::join);
    }
}
