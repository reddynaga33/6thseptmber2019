package zf.insprint.multitenancy10.pages;

import java.util.HashMap;
import com.microsoft.azure.servicebus.Message;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class SMSMCPage extends RestApiUtility {
	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;
	JSONObject servicestartedmcjsonObject = JsonReader.getJsonObject("ServicestartedMc");
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
	public HashMap<String, Integer> configureTopicSubscription() {
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		try{
			sbDestinationsHandler.ensureTopicCreation(servicestartedmcjsonObject.getAsString("ServiceStartedmcService"));
			sbDestinationsHandler.ensureTopicCreation(servicestartedmcjsonObject.getAsString("DestinationChangedService"));
			sbDestinationsHandler.ensureSubscriptionCreation(servicestartedmcjsonObject.getAsString("ServiceStartedmcService"), servicestartedmcjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.ensureSubscriptionCreation(servicestartedmcjsonObject.getAsString("DestinationChangedService"), servicestartedmcjsonObject.getAsString("SubscriptionName"));
			subscriptionMessageCountAfterConfigure.put("ServiceStartedmcService",(int)sbDestinationsHandler.getSubscriptionMessageCount(servicestartedmcjsonObject.getAsString("ServiceStartedmcService"), servicestartedmcjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountAfterConfigure.put("DestinationChangedService",(int)sbDestinationsHandler.getSubscriptionMessageCount(servicestartedmcjsonObject.getAsString("DestinationChangedService"), servicestartedmcjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}
	public HashMap<String, Integer> DeleteTopicSubscription(HashMap<String, Integer> subscriptionMessageCount) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		try{
			subscriptionMessageCountBeforeDelete.put("ServiceStartedmcService",(int)sbDestinationsHandler.getSubscriptionMessageCount(servicestartedmcjsonObject.getAsString("ServiceStartedmcService"), servicestartedmcjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountBeforeDelete.put("DestinationChangedService",(int)sbDestinationsHandler.getSubscriptionMessageCount(servicestartedmcjsonObject.getAsString("DestinationChangedService"), servicestartedmcjsonObject.getAsString("SubscriptionName")));
			sbDestinationsHandler.deleteSubscription(servicestartedmcjsonObject.getAsString("ServiceStartedmcService"), servicestartedmcjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.deleteSubscription(servicestartedmcjsonObject.getAsString("DestinationChangedService"), servicestartedmcjsonObject.getAsString("SubscriptionName"));
			info("Message count for SubScription  is : "+subscriptionMessageCountBeforeDelete);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}

	public String VerifyRequestToGlobalInfoServicestartedMC() {
		String Id=null;
		Response SMSMCResponse=PostServices("ServicestartedMC");
		if(SMSMCResponse.getStatusCode()==200) {
			testPassed("The Reponse body is :"+SMSMCResponse.getBody().asString());
		}else {
			testFailed("The Reponse body is :"+SMSMCResponse.getBody().asString());

		}
		return Id;
	}	
	public void VerifyMulticlientSampleServiceSubscriptionGetDeletedFromTheTopics(){
		Response GreetingLogs= null;
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("MulticlientSamplesService");
			HashMap<String, String> MessageProperties=new HashMap<String, String>();
			MessageProperties.put(jsonObject.getAsString("ClientKey"),jsonObject.getAsString("ClientValue"));
			Message ServiceMessage=new Message(jsonObject.getAsString("ServicePayload"));
			Message ServiceMessage1=new Message(jsonObject.getAsString("ServiceMessagePayload1"));

			
			PostServices("MulitClientSampleServiceSubscription");
			if(sbDestinationsHandler.isSubscriptionExists(jsonObject.getAsString("ClientUsesServiceResult"), jsonObject.getAsString("ServiceSubscription"))&&sbDestinationsHandler.isSubscriptionExists(jsonObject.getAsString("AllGreetingRequest"), jsonObject.getAsString("ServiceSubscription"))) {
				info("Subscriptions are Created successfully");
			}else {
				info("Subscriptions are not created");
			}
			sbClient.sendToTopic(jsonObject.getAsString("ServiceStartedmc"), ServiceMessage);
			if(!sbDestinationsHandler.isSubscriptionExists(jsonObject.getAsString("ClientUsesServiceResult"), jsonObject.getAsString("ServiceSubscription"))&&!sbDestinationsHandler.isSubscriptionExists(jsonObject.getAsString("AllGreetingRequest"), jsonObject.getAsString("ServiceSubscription"))) {
				info("Subscriptions are deleted successfully");
			}else {
				info("Subscriptions are not deleted");
			}
			ServiceMessage1.setProperties(MessageProperties);
			PostServices("MulitClientSampleServiceSubscription");
			sbClient.sendToTopic(jsonObject.getAsString("AllGreetingRequest"), ServiceMessage1);

			GreetingLogs=GetServices("getGreetingLogForClient");
			System.out.println(GreetingLogs.getBody().asString());
			System.out.println(GreetingLogs.jsonPath().getJsonObject("greetingMessage").toString());

		}catch(Exception e) {
			info(e.getMessage());
		}

	}
}