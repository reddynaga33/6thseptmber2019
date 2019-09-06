package zf.ClientManagementServiceSuite.pages;

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
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class MiscPage extends RestApiUtility {
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JSONObject CMSjsonObject = JsonReader.getJsonObject("ClientManagmentServiceTopicName");
	DatabaseUtility databaseutility=new DatabaseUtility();
	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;

	public Response VerifyUserIsAbleToCreateClientWithSameClientIdViaCMS() {
		Response ClientsServiceResponse = null;
		ClientsServiceResponse=GetServices("client");
		info("The Client serice response body is :"+ClientsServiceResponse.getBody().asString());
		return ClientsServiceResponse;
	}
	public void ValidateResponseCode200(Response response) {
		if(response.getStatusCode()==200) {
			testPassed("The API response code is Successfully validated");
		}else {
			testFailed("The API response code is not successfully validated");
		}
	}

	public Response VerifyUserIsAbleToCreateClient() {
		Response ClientCreationResponse=null;
		JSONObject PayLoad=null;
		PayLoad=JsonReader.getJsonObject("Payload");
		PayLoad.put("name", PayLoad.get("name").toString()+getRandomNumber());
		ClientCreationResponse=PostServices("client",PayLoad);
		info("The API Response Body is: "+ClientCreationResponse.getBody().asString());		
		return ClientCreationResponse;
	}

	public Response VerifyUserIsAbleToVerifyGerSingleClientDetails(String ClientId) {
		Response SingleClientResponse=null;
		SingleClientResponse=GetServices("client",ClientId);
		info("The Response API Body is: "+SingleClientResponse.getBody().asString());
		return SingleClientResponse;
	}
	public void ValidateRessponseWithId(Response response,String ClientID) {
		if(ClientID==response.jsonPath().getJsonObject("id")) {
			testPassed("The Client id is matched");
		}else {
			testFailed("The Client is is not matched");
		}
	}

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

	public HashMap<String, Integer> configureTopicSubscription(String CMSpayload) {
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		try{
			Message message=new Message(JsonReader.getJsonObject(CMSpayload).toString());
			sbDestinationsHandler.ensureTopicCreation(CMSjsonObject.getAsString("CUS"));
			sbDestinationsHandler.ensureSubscriptionCreation(CMSjsonObject.getAsString("CUS"), CMSjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.ensureTopicCreation(CMSjsonObject.getAsString("CUA"));
			sbDestinationsHandler.ensureSubscriptionCreation(CMSjsonObject.getAsString("CUA"), CMSjsonObject.getAsString("SubscriptionName"));
			subscriptionMessageCountAfterConfigure.put("CUS",(int)sbDestinationsHandler.getSubscriptionMessageCount(CMSjsonObject.getAsString("CUS"), CMSjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountAfterConfigure.put("CUA",(int)sbDestinationsHandler.getSubscriptionMessageCount(CMSjsonObject.getAsString("CUA"), CMSjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
			sbClient.sendToTopic(CMSjsonObject.getAsString("CUS"), message);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

	public HashMap<String, Integer> DeleteTopicSubscription(HashMap<String, Integer> subscriptionMessageCount) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		try{
			subscriptionMessageCountBeforeDelete.put("CUS",(int)sbDestinationsHandler.getSubscriptionMessageCount(CMSjsonObject.getAsString("CUS"), CMSjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountBeforeDelete.put("CUA",(int)sbDestinationsHandler.getSubscriptionMessageCount(CMSjsonObject.getAsString("CUA"), CMSjsonObject.getAsString("SubscriptionName")));
			sbDestinationsHandler.deleteSubscription(CMSjsonObject.getAsString("CUS"), CMSjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.deleteSubscription(CMSjsonObject.getAsString("CUA"), CMSjsonObject.getAsString("SubscriptionName"));
			info("Message count for SubScription  is : "+subscriptionMessageCountBeforeDelete);
			serviceBusValidation(subscriptionMessageCount,subscriptionMessageCountBeforeDelete);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}


	public void serviceBusValidation(HashMap<String, Integer> subscriptionMessageAfterConfiure ,HashMap<String, Integer> subscriptionMessageBeforeDelete) {
		try{	
			if(subscriptionMessageAfterConfiure.get("CUS")<subscriptionMessageBeforeDelete.get("CUS") &&
					subscriptionMessageAfterConfiure.get("CUA")<subscriptionMessageBeforeDelete.get("CUA")) {
				testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
			}else {
				testFailed("Message count is same");
			}							}catch(Exception e) {
				testFailed("Exception occured while comparing  the subscription message count:"+e.getMessage());
			}
	}
}


