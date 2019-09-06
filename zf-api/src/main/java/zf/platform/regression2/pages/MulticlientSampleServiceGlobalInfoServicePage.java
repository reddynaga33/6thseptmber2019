package zf.platform.regression2.pages;

import java.util.HashMap;

import com.microsoft.azure.servicebus.Message;

import framework.DatabaseUtility;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class MulticlientSampleServiceGlobalInfoServicePage extends RestApiUtility{
	DatabaseUtility databaseutility=new DatabaseUtility();
	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;
	JSONObject GlobalInfoServicejsonObject = JsonReader.getJsonObject("GlobalInfoService");
	
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
	
			Message message=new Message(JsonReader.getJsonObject("APSPayload").toString());
			sbDestinationsHandler.ensureTopicCreation(GlobalInfoServicejsonObject.getAsString("servicestartedmc"));
			sbDestinationsHandler.ensureTopicCreation(GlobalInfoServicejsonObject.getAsString("destinationchanged"));
			sbDestinationsHandler.ensureSubscriptionCreation(GlobalInfoServicejsonObject.getAsString("servicestartedmc"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName"));
			sbDestinationsHandler.ensureSubscriptionCreation(GlobalInfoServicejsonObject.getAsString("destinationchanged"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName"));
			subscriptionMessageCountAfterConfigure.put("servicestartedmc",(int)sbDestinationsHandler.getSubscriptionMessageCount(GlobalInfoServicejsonObject.getAsString("servicestartedmc"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName")));
			subscriptionMessageCountAfterConfigure.put("destinationchanged",(int)sbDestinationsHandler.getSubscriptionMessageCount(GlobalInfoServicejsonObject.getAsString("destinationchanged"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

	
	public HashMap<String, Integer> DeleteTopicSubscription(HashMap<String, Integer> subscriptionMessageCount) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		try{
			subscriptionMessageCountBeforeDelete.put("servicestartedmc",(int)sbDestinationsHandler.getSubscriptionMessageCount(GlobalInfoServicejsonObject.getAsString("servicestartedmc"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName")));
			subscriptionMessageCountBeforeDelete.put("destinationchanged",(int)sbDestinationsHandler.getSubscriptionMessageCount(GlobalInfoServicejsonObject.getAsString("destinationchanged"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName")));
			sbDestinationsHandler.deleteSubscription(GlobalInfoServicejsonObject.getAsString("servicestartedmc"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName"));
			sbDestinationsHandler.deleteSubscription(GlobalInfoServicejsonObject.getAsString("destinationchanged"), GlobalInfoServicejsonObject.getAsString("GlobalInfoSubscriptionName"));
			serviceBusValidation(subscriptionMessageCount,subscriptionMessageCountBeforeDelete);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}


	public void serviceBusValidation(HashMap<String, Integer> subscriptionMessageAfterConfiure ,HashMap<String, Integer> subscriptionMessageBeforeDelete) {

		try{
			
				if(subscriptionMessageAfterConfiure.get("servicestartedmc")<subscriptionMessageBeforeDelete.get("servicestartedmc") ) {
					testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
				}else {
					testFailed("Message count is same");
				}
			
		}catch(Exception e) {
			testFailed("Exception occured while comparing  the subscription message count:"+e.getMessage());
		}
	}
	
	public void globalInfoServiceStarted(String servicename) {
		Response postServicesResponse=null;
		try{ postServicesResponse = PostServices(servicename);
		if(postServicesResponse.getStatusCode()==200) {
			testPassed("The response for the Postservice is "+postServicesResponse.getBody().asString());
		
		}else {
			
		}
		}catch(Exception e) {
			testFailed("An exception occured and error message is:"+e.getMessage());
		}
	}
}
