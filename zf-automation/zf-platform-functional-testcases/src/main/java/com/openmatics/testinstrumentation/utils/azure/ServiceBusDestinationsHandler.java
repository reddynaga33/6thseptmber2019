package com.openmatics.testinstrumentation.utils.azure;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.servicebus.Queue;
import com.microsoft.azure.management.servicebus.ServiceBusNamespace;
import com.microsoft.azure.management.servicebus.ServiceBusSubscription;
import com.microsoft.azure.management.servicebus.ServiceBusSubscriptions;
import com.microsoft.azure.management.servicebus.Topic;
import com.microsoft.azure.management.servicebus.implementation.ServiceBusManager;
import com.openmatics.testinstrumentation.platform.utils.ServiceBusConnectionConfiguration;
import com.openmatics.testinstrumentation.utils.azure.exception.DestinationHandlerException;

import java.util.List;
import java.util.Objects;

/**
 * Created by Marek Rojik (marek.rojik@inventi.cz) on 11. 02. 2019
 */
public class ServiceBusDestinationsHandler {

    private ServiceBusConnectionConfiguration serviceBusConnectionConfiguration;

    private ServiceBusNamespace namespace;

    public ServiceBusDestinationsHandler(ServiceBusConnectionConfiguration serviceBusConnectionConfiguration) {
        this.serviceBusConnectionConfiguration = serviceBusConnectionConfiguration;
        initializeServiceBus();
    }

    /**
     * Create connection to service bus
     */
    private void initializeServiceBus() {
        ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(
                serviceBusConnectionConfiguration.getClientId(),
                serviceBusConnectionConfiguration.getTenant(),
                serviceBusConnectionConfiguration.getSecret(),
                AzureEnvironment.AZURE);
        ServiceBusManager manager = ServiceBusManager.authenticate(credentials, serviceBusConnectionConfiguration.getSubscriptionId());
        this.namespace = manager.namespaces().getByResourceGroup(serviceBusConnectionConfiguration.getResourceGroupName(),
                serviceBusConnectionConfiguration.getNamespace());
    }


    /**
     * Create queue in service bus
     *
     * @param queueName queue to create
     * @throws DestinationHandlerException problem during creating queue
     */
    public void ensureQueueCreation(String queueName) throws DestinationHandlerException {
        if (isQueueExists(queueName)) {
            return;
        }

        createQueue(queueName);
    }

    /**
     * Create topic in service bus
     *
     * @param topicName topic to create
     * @throws DestinationHandlerException problem during creating topic
     */
    public void ensureTopicCreation(String topicName) throws DestinationHandlerException {
        if (isTopicExists(topicName)) {
            return;
        }

        createTopic(topicName);
    }

    /**
     * Create subscription in specified topic
     *
     * @param topicName        topic name
     * @param subscriptionName new subscription to create
     * @throws DestinationHandlerException problem during creating subscription
     */
    public void ensureSubscriptionCreation(String topicName, String subscriptionName) throws DestinationHandlerException {
        if (!isTopicExists(topicName)) {
            return;
        }
        if (isSubscriptionExists(topicName, subscriptionName)) {
            return;
        }

        createSubscription(topicName, subscriptionName);
    }


    /**
     * Create topic with subscriptions
     *
     * @param topicName     topic to create
     * @param subscriptions list of subscriptions to create
     * @throws DestinationHandlerException problem during creating topic
     */
    public void ensureTopicWithSubscriptionCreation(String topicName, List<String> subscriptions) throws DestinationHandlerException {
        if (isTopicExists(topicName)) {
            updateSubscriptionsInTopic(topicName, subscriptions);
        } else {
            createTopicWithSubscription(topicName, subscriptions);
        }
    }

    /**
     * Create topic in service bus
     *
     * @param topicName topic to create
     * @throws DestinationHandlerException problem during creating topic
     */
    private Topic createTopic(String topicName) throws DestinationHandlerException {
        Topic topic;
        try {
            topic = namespace.topics().define(topicName).create();
        } catch (CloudException e) {
            throw new DestinationHandlerException(e);
        }
        return topic;
    }

    /**
     * Create queue in service bus
     *
     * @param queueName queue to create
     * @throws DestinationHandlerException problem during creating queue
     */
    private Queue createQueue(String queueName) throws DestinationHandlerException {
        Queue queue;
        try {
            queue = namespace.queues().define(queueName).create();
        } catch (CloudException e) {
            throw new DestinationHandlerException(e);
        }
        return queue;
    }

    /**
     * Create topic with subscriptions in service bus
     *
     * @param topicName     topic to create
     * @param subscriptions list of subscriptions to create
     * @throws DestinationHandlerException problem during creating topic
     */
    private Topic createTopicWithSubscription(String topicName, List<String> subscriptions) throws DestinationHandlerException {
        Topic.DefinitionStages.WithCreate topic = namespace.topics().define(topicName);
        subscriptions
                .forEach(topic::withNewSubscription);
        Topic result;
        try {
            result = topic.create();
        } catch (CloudException e) {
            throw new DestinationHandlerException(e);
        }
        return result;
    }

    /**
     * Update subscription in existing topic.
     * Method only create new subscriptions, not delete.
     *
     * @param topicName     topic to update
     * @param subscriptions list of subscriptions to create
     * @return updated topic
     * @throws DestinationHandlerException problem during updating topic with new subscriptions
     */
    private Topic updateSubscriptionsInTopic(String topicName, List<String> subscriptions) throws DestinationHandlerException {
        Topic topic = namespace.topics().getByName(topicName);
        Topic.Update updatedTopic = topic.update();
        subscriptions.stream().filter(newSubs -> topic.subscriptions().list().stream()
                .noneMatch(sa -> sa.name().equalsIgnoreCase(newSubs)))
                .forEach(updatedTopic::withNewSubscription);

        Topic result;
        try {
            result = updatedTopic.apply();
        } catch (CloudException e) {
            throw new DestinationHandlerException(e);
        }
        return result;
    }

    /**
     * Create subscription in existing topic
     *
     * @param topicName        topic to update
     * @param subscriptionName subscription to create
     * @return created subscription
     * @throws DestinationHandlerException problem during updating topic with new subscription
     */
    private ServiceBusSubscription createSubscription(String topicName, String subscriptionName) throws DestinationHandlerException {
        ServiceBusSubscription subscription;
        try {
            subscription = namespace.topics().getByName(topicName)
                    .subscriptions()
                    .define(subscriptionName).create();
        } catch (CloudException e) {
            throw new DestinationHandlerException(e);
        }

        return subscription;
    }

    /**
     * Check if topic is exist
     *
     * @param topicName topic name
     * @return true if topic is exist
     */
    public boolean isTopicExists(String topicName) {
        Topic topic = namespace.topics().getByName(topicName);
        return Objects.nonNull(topic);
    }

    /**
     * Check if subscription is exist
     *
     * @param subscriptionName subscription name
     * @return true if subscription is exist
     */
    public boolean isSubscriptionExists(String topicName, String subscriptionName) {
        ServiceBusSubscription subscription = null;
        if (isTopicExists(topicName)) {
            ServiceBusSubscriptions subscriptions = namespace.topics().getByName(topicName).subscriptions();
            if (subscriptions != null) {
                try {
                    subscription = subscriptions.getByName(subscriptionName);
                } catch (NullPointerException ignored) {
                }
                return Objects.nonNull(subscription);
            }
        }
        return false;
    }

    public long getSubscriptionMessageCount(String topicName, String subscriptionName) {
        long messageCount = namespace.topics().getByName(topicName).subscriptions().getByName(subscriptionName).messageCount();

     
        return messageCount;
    }

    /**
     * Check if queue is exist
     *
     * @param queueName queue name
     * @return true if queue is exist
     */
    public boolean isQueueExists(String queueName) {
        Queue queue = namespace.queues().getByName(queueName);
        return Objects.nonNull(queue);
    }

    /**
     * Delete queue in service bus
     *
     * @param queueName queue to delete
     */
    public void deleteQueue(String queueName) {
        namespace.queues().deleteByName(queueName);
    }

    /**
     * Delete topic in service bus
     *
     * @param topicName topic to delete
     */
    public void deleteTopic(String topicName) {
        namespace.topics().deleteByName(topicName);
    }

    /**
     * Delete subscription from topic in service bus
     *
     * @param subscriptionName subscription to delete
     */
    public void deleteSubscription(String topicName, String subscriptionName) {
        namespace.topics().getByName(topicName).subscriptions().deleteByName(subscriptionName);
    }
}
