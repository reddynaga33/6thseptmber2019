package zf.MulticlientSampleServiceSuite.pages;

import java.util.HashMap;

import com.microsoft.azure.servicebus.Message;

import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import net.minidev.json.JSONObject;

public class MiscellaneousPage extends RestApiUtility{

	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;

	public void configureServiceBus() {
		try{
			JSONObject jsonObject = JsonReader.getJsonObject("serviceBus");
			ServiceBusConnectionConfigurations serviceBusProperty = new ServiceBusConnectionConfigurations(jsonObject);
			sbDestinationsHandler = new ServiceBusDestinationsHandlers(serviceBusProperty);
			sbClient = new ServiceBusClients(jsonObject.getAsString("connectionString"));

		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
	}


	public HashMap<String, Integer> configureTopicSubscription(String clientPropertyDetails) {
		JSONObject miscellaneousjsonObject = JsonReader.getJsonObject("MiscellaneousTopicName");
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		HashMap<String, String> messageProperty = new HashMap<String, String>();
		Message message=new Message(JsonReader.getJsonObject("MiscellaneousTopicPayload").toString());
		try{
			sbDestinationsHandler.ensureTopicCreation(miscellaneousjsonObject.getAsString("ServiceAllGreetingRequest"));
			sbDestinationsHandler.ensureSubscriptionCreation(miscellaneousjsonObject.getAsString("ServiceAllGreetingRequest"), miscellaneousjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.ensureTopicCreation(miscellaneousjsonObject.getAsString("ServiceAllGreetingResult"));
			sbDestinationsHandler.ensureSubscriptionCreation(miscellaneousjsonObject.getAsString("ServiceAllGreetingResult"), miscellaneousjsonObject.getAsString("SubscriptionName"));

			subscriptionMessageCountAfterConfigure.put("ServiceAllGreetingRequest",(int)sbDestinationsHandler.getSubscriptionMessageCount(miscellaneousjsonObject.getAsString("ServiceAllGreetingRequest"), miscellaneousjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
			messageProperty.put("MachineName", "PLNC00182");
			messageProperty.put("UserName", "Z228584");
			messageProperty.put("clientId", "19309c32-1769-42ba-9829-d18d5e9e072d");
			message.setProperties(messageProperty);
			sbClient.sendToTopic(miscellaneousjsonObject.getAsString("ServiceAllGreetingRequest"), message);
			subscriptionMessageCountAfterConfigure.put("ServiceAllGreetingRequest",(int)sbDestinationsHandler.getSubscriptionMessageCount(miscellaneousjsonObject.getAsString("ServiceAllGreetingRequest"), miscellaneousjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

}
