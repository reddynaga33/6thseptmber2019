package BackofficeService.pages;

import java.util.HashMap;
import java.util.List;

import com.microsoft.azure.servicebus.Message;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import net.minidev.json.JSONObject;

public class BackofficeServiceMulticlientPage extends RestApiUtility{
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JSONObject BOSjsonObject = JsonReader.getJsonObject("BackOfficeServiceTopicName");
	DatabaseUtility databaseutility=new DatabaseUtility();
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

	public HashMap<String, Integer> configureTopicSubscription(String BOSpayload) {
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		try{
			Message message=new Message(JsonReader.getJsonObject(BOSpayload).toString());
			sbDestinationsHandler.ensureTopicCreation(BOSjsonObject.getAsString("BOServiceStarted"));
			sbDestinationsHandler.ensureSubscriptionCreation(BOSjsonObject.getAsString("BOServiceStarted"), BOSjsonObject.getAsString("SubscriptionName"));
			subscriptionMessageCountAfterConfigure.put("BOServiceStarted",(int)sbDestinationsHandler.getSubscriptionMessageCount(BOSjsonObject.getAsString("BOServiceStarted"), BOSjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
			sbClient.sendToTopic(BOSjsonObject.getAsString("BOServiceStarted"), message);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

	public HashMap<String, Integer> DeleteTopicSubscription(HashMap<String, Integer> subscriptionMessageCount) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		try{
			subscriptionMessageCountBeforeDelete.put("BOServiceStarted",(int)sbDestinationsHandler.getSubscriptionMessageCount(BOSjsonObject.getAsString("BOServiceStarted"), BOSjsonObject.getAsString("SubscriptionName")));
			sbDestinationsHandler.deleteSubscription(BOSjsonObject.getAsString("BOServiceStarted"), BOSjsonObject.getAsString("SubscriptionName"));
			info("Message count for SubScription  is : "+subscriptionMessageCountBeforeDelete);
			serviceBusValidation(subscriptionMessageCount,subscriptionMessageCountBeforeDelete);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}


	public void serviceBusValidation(HashMap<String, Integer> subscriptionMessageAfterConfiure ,HashMap<String, Integer> subscriptionMessageBeforeDelete) {
		try{	
			if(subscriptionMessageAfterConfiure.get("BOServiceStarted")<subscriptionMessageBeforeDelete.get("BOServiceStarted")){
					
				testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
			}else {
				testFailed("Message count is same");
			}							}catch(Exception e) {
				testFailed("Exception occured while comparing  the subscription message count:"+e.getMessage());
			}
	}
}
