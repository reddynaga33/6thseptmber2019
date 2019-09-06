package framework;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.servicebus.Queue;
import com.microsoft.azure.management.servicebus.ServiceBusNamespace;
import com.microsoft.azure.management.servicebus.ServiceBusSubscription;
import com.microsoft.azure.management.servicebus.ServiceBusSubscriptions;
import com.microsoft.azure.management.servicebus.Topic;
import com.microsoft.azure.management.servicebus.implementation.ServiceBusManager;


import java.util.List;
import java.util.Objects;


public class ServiceBusDestinationsHandlers {

	private ServiceBusConnectionConfigurations serviceBusConnectionConfiguration;
    private ServiceBusNamespace namespace;

    public ServiceBusDestinationsHandlers(ServiceBusConnectionConfigurations serviceBusConnectionConfiguration) {
    	 this.serviceBusConnectionConfiguration = serviceBusConnectionConfiguration;
        initializeServiceBus();
    }

    
    private void initializeServiceBus() {
    	
//    	ApplicationTokenCredentials credentials = new ApplicationTokenCredentials("f4bf1ca1-2f17-4b80-b42f-0d6a13b0d410","eb70b763-b6d7-4486-8555-8831709a784e","ResVWywuDSeLC9/Kx7vN4zLjS3srLxKdnHemlGvMBGc=",AzureEnvironment.AZURE);
//		ServiceBusManager manager = ServiceBusManager.authenticate(credentials, "f889f019-873b-4c24-a776-bf10fc81df76");
//		this.namespace = manager.namespaces().getByResourceGroup("HCO-IoT-CDO-Test","tauri-servicebus-cdo-1");
//		
		
        ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(
                serviceBusConnectionConfiguration.getClientId(),
                serviceBusConnectionConfiguration.getTenant(),
                serviceBusConnectionConfiguration.getSecret(),
                AzureEnvironment.AZURE);
        ServiceBusManager manager = ServiceBusManager.authenticate(credentials, serviceBusConnectionConfiguration.getSubscriptionId());
        this.namespace = manager.namespaces().getByResourceGroup(serviceBusConnectionConfiguration.getResourceGroupName(),
                serviceBusConnectionConfiguration.getNamespace());
    }


    
    public void ensureQueueCreation(String queueName)  {
        if (isQueueExists(queueName)) {
            return;
        }

        createQueue(queueName);
    }

    
    public void ensureTopicCreation(String topicName)  {
        if (isTopicExists(topicName)) {
            return;
        }

        createTopic(topicName);
    }

    
    public void ensureSubscriptionCreation(String topicName, String subscriptionName)  {
        if (!isTopicExists(topicName)) {
            return;
        }
        if (isSubscriptionExists(topicName, subscriptionName)) {
            return;
        }

        createSubscription(topicName, subscriptionName);
    }


    
    public void ensureTopicWithSubscriptionCreation(String topicName, List<String> subscriptions)  {
        if (isTopicExists(topicName)) {
            updateSubscriptionsInTopic(topicName, subscriptions);
        } else {
            createTopicWithSubscription(topicName, subscriptions);
        }
    }

    
    private Topic createTopic(String topicName)  {
        Topic topic = null;
        try {
            topic = namespace.topics().define(topicName).create();
  
        } catch (CloudException e) {
        	 System.out.println(e.getMessage());
        }
        return topic;
    }

    
    private Queue createQueue(String queueName)  {
        Queue queue = null;
        try {
            queue = namespace.queues().define(queueName).create();
        } catch (CloudException e) {
        	 System.out.println(e.getMessage());
        }
        return queue;
    }

    
    private Topic createTopicWithSubscription(String topicName, List<String> subscriptions)  {
        Topic.DefinitionStages.WithCreate topic = namespace.topics().define(topicName);
        subscriptions
                .forEach(topic::withNewSubscription);
        Topic result = null;
        try {
            result = topic.create();
        } catch (CloudException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    
    private Topic updateSubscriptionsInTopic(String topicName, List<String> subscriptions)  {
        Topic topic = namespace.topics().getByName(topicName);
        Topic.Update updatedTopic = topic.update();
        subscriptions.stream().filter(newSubs -> topic.subscriptions().list().stream()
                .noneMatch(sa -> sa.name().equalsIgnoreCase(newSubs)))
                .forEach(updatedTopic::withNewSubscription);

        Topic result = null;
        try {
            result = updatedTopic.apply();
        } catch (CloudException e) {
        	System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Create subscription in existing topic
     *
     * @param topicName        topic to update
     * @param subscriptionName subscription to create
     * @return created subscription
     * @ problem during updating topic with new subscription
     */
    private ServiceBusSubscription createSubscription(String topicName, String subscriptionName)  {
        ServiceBusSubscription subscription = null;
        try {
            subscription = namespace.topics().getByName(topicName)
                    .subscriptions()
                    .define(subscriptionName).create();
        } catch (CloudException e) {
        	System.out.println(e.getMessage());
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
