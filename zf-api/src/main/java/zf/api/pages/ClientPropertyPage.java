package zf.api.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.microsoft.azure.management.servicebus.ServiceBusNamespace;
import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class ClientPropertyPage extends RestApiUtility{
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JSONObject clientPropertyjsonObject = JsonReader.getJsonObject("ClientPropertyTopicName");
	DatabaseUtility databaseutility=new DatabaseUtility();
	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;

	
	public Response createPropertyForClient(String servicename, String payLoad,String reconfigureValue){
		Response putServicesResponse =null;
		 JSONObject createPropertyForclientJson = null;
		try {		
			createPropertyForclientJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForclientJson.put("test",createPropertyForclientJson.get("test").toString() + getRandomNumber());
			putServicesResponse = PutServices(servicename, createPropertyForclientJson, reconfigureValue);
			if(putServicesResponse.getStatusCode()==202) {
				testPassed("Create Client property response is : "+putServicesResponse.getBody().asString());
			}
			else {
				info("Create Client property status code is : "+putServicesResponse.getStatusCode());
				testFailed("Create Client property response is : "+putServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("Exception occured while creating client property and the message is :"+e.getMessage());
		}
		return putServicesResponse;
	}

	public Response UpdatePropertyForClient(String servicename, String payLoad,String reconfigureValue){

		Response putServicesResponse =null;
		JSONObject createPropertyForclientJson = null;
		try {
			createPropertyForclientJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForclientJson.put("test",createPropertyForclientJson.get("test").toString() + getRandomNumber());
			putServicesResponse = PutServices(servicename, createPropertyForclientJson, reconfigureValue);
			if(putServicesResponse.getStatusCode()==202) {
				testPassed("Updated Client property response is : "+putServicesResponse.getBody().asString());
			}
			else {
				info("Updated Client property status code is : "+putServicesResponse.getStatusCode());
				testFailed("Updated Client property response is : "+putServicesResponse.getBody().asString());

			}}catch(Exception e) {
				testFailed("Exception occured while updating client property and the message is :"+e.getMessage());
			}
		return putServicesResponse;
	}

	public void getSagaEntityValidateClientAndCUSProperty(String servicename, String getSagaStatus) {
		try{Response getSagaEntityResponse = GetServices(servicename, getSagaStatus);
		String SagaStatusResponseValue = getResponseValue(getSagaEntityResponse,"status");
		if(getSagaEntityResponse.getStatusCode()==200) {
			testPassed("Saga status response body  is :"+getSagaEntityResponse.getBody().asString()+"  and status is : "+SagaStatusResponseValue);
		}
		else {
			info("Saga response detail is : "+getSagaEntityResponse.getBody().asString());
			testFailed("Saga status detail is : "+SagaStatusResponseValue);
		}}catch(Exception e) {
			testFailed("Exception occured while getting the saga details and the message is :"+e.getMessage());
		}
	}

	public String getClientAndCUSProperty(String servicename) {
		String ClientResponseValue =null;
		Response getClientCUSResponse =null;
		try {
			sleep(10000);
			getClientCUSResponse = GetServices(servicename);
			ClientResponseValue = getResponseValue(getClientCUSResponse,"test");
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get Property detail is : "+ClientResponseValue);
			}
			else {
				info("Get Property response detail is : "+getClientCUSResponse.getBody().asString());
				testFailed("Get Property detail is : "+ClientResponseValue);
			}}catch(Exception e) {
				testFailed("Exception occured while getting the client property details and the message is : "+e.getMessage());
			}
		return ClientResponseValue;
	}
	
	public String getClientAndCUSProperty(String servicename,String SDName) {
		String ClientResponseValue =null;
		Response getClientCUSResponse =null;
		try {
			getClientCUSResponse = GetServices(servicename,SDName);
			System.out.println(getClientCUSResponse.getBody().asString());
			ClientResponseValue = getResponseValue(getClientCUSResponse,"test");
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get Property detail is : "+ClientResponseValue);
			}
			else {
				info("Get Property response detail is : "+getClientCUSResponse.getBody().asString());
				testFailed("Get Property detail is : "+ClientResponseValue);
			}}catch(Exception e) {
				testFailed("Exception occured while getting the client property details and the message is : "+e.getMessage());
			}
		return ClientResponseValue;
	}

	public Response getClientAndCUSPropertyResponse(String servicename) {
		String ClientResponseValue =null;
		Response getClientCUSResponse =null;
		try {sleep(5000);
			getClientCUSResponse = GetServices(servicename);
			info("Get Property Response detail is : "+getClientCUSResponse.getBody().asString());
//			ClientResponseValue = getResponseValue(getClientCUSResponse,"test");
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get Property detail is : "+ClientResponseValue);
			}
			else {
				info("Get Property response detail is : "+getClientCUSResponse.getBody().asString());
				testFailed("Get Property detail is : "+ClientResponseValue);
			}}catch(Exception e) {
				testFailed("Exception occured while getting the client property details and the message is : "+e.getMessage());
			}
		return getClientCUSResponse;}
	
	public void getDeleteClientAndCUSProperty(String servicename) {
		try {
			sleep(10000);
			Response getClientCUSResponse = GetServices(servicename);
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get Property Response detail is : "+getClientCUSResponse.getBody().asString());
			}
			else {
				info("Get Property Response status code is : "+getClientCUSResponse.getStatusCode());
				testFailed("Get Property Response detail is : "+getClientCUSResponse.getBody().asString());

			}}catch(Exception e) {
				testFailed("Exception occured while getting the client property details and the message is : "+e.getMessage());
			}
	}

	public Response getConfigHierarchicalClientAndCUSProperty(String servicename) {
		Response getClientCUSResponse =null;
		try {
			getClientCUSResponse = GetServices(servicename);
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get Property detail is : "+getClientCUSResponse.getBody().asString());
			}
			else {
				info("Get Property response status code is : "+getClientCUSResponse.getStatusCode());
				testFailed("Get Property detail is : "+getClientCUSResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while getting hierarchical the client property details and the message is : "+e.getMessage());
			}
		return getClientCUSResponse;
	}
	
	public Response getConfigHierarchicalClientAndCUSProperty(String servicename,String SDName) {
		Response getClientCUSResponse =null;
		try {
			getClientCUSResponse = GetServices(servicename,SDName);
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get Property detail is : "+getClientCUSResponse.getBody().asString());
			}
			else {
				info("Get Property response status code is : "+getClientCUSResponse.getStatusCode());
				testFailed("Get Property detail is : "+getClientCUSResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while getting hierarchical the client property details and the message is : "+e.getMessage());
			}
		return getClientCUSResponse;
	}

	public Response deletePropertyClientConfig(String servicename, String reconfigValue) {
		Response deleteClientCUSResponse=null;
		try{
			deleteClientCUSResponse = DeleteServices(servicename,reconfigValue);
			if(deleteClientCUSResponse.getStatusCode()==202) {
				testPassed("Response detail is : "+deleteClientCUSResponse.getBody().asString());
			}
			else {
				info("Client response status code is : "+deleteClientCUSResponse.getStatusCode());
				testFailed("Client response detail is : "+deleteClientCUSResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while deleting client property details and the message is : "+e.getMessage());
			}
		return deleteClientCUSResponse;
	}

	public Response getWrongClientIDPropertyDetails(String servicename, String reconfigValue) {
		Response getWrongClientIDPropertyResponse =null;
		try {
			getWrongClientIDPropertyResponse = GetServices(servicename,reconfigValue);
			if(getWrongClientIDPropertyResponse.getStatusCode()==404) {
				testPassed("Response detail is : "+getWrongClientIDPropertyResponse.getBody().asString());
			}
			else {
				info("Client response status code is : "+getWrongClientIDPropertyResponse.getStatusCode());
				testFailed("Client response detail is : "+getWrongClientIDPropertyResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while getting wrong client property details and the message is : "+e.getMessage());
			}
		return getWrongClientIDPropertyResponse;
	}

	public void validateClientPropertyInDB(Response ClientAndCUSPropertyResponse) {
		Set<String> keySet=null;
		Object[] array=null;
		HashMap<String, Object> ClientProp=null;
		int count = 0;
		try {
			String SelectQuery = jsonObject.getAsString("SelectClientProperty");
			ClientProp=ClientAndCUSPropertyResponse.jsonPath().getJsonObject("CLIENT");
			keySet = ClientProp.keySet();
			array = keySet.toArray();
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			for(count=0;count<keySet.size();count++){
				if(DatabaseUtility.selectQueryComparision(SelectQueryResult,array[count].toString()) && DatabaseUtility.selectQueryComparision(SelectQueryResult,ClientProp.get(array[count]).toString())){
					testPassed("Value from DB  contains the  : "+array[count]+" : "+ClientProp.get(array[count]));
				}else {
					testFailed("Value from DB  contains : "+array[count]+" : "+ClientProp.get(array[count]));
				}
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
	}

	public void validateDeleteClientAndCUSPropertyInDB(Response configHierarchicalClientAndCUSProperty,String query) {
		try {
			String configHierarchicalClientAndCUSPropertyResponse = configHierarchicalClientAndCUSProperty.getBody().asString();
			String SelectQuery = jsonObject.getAsString(query);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);

			if(!DatabaseUtility.selectQueryComparision(SelectQueryResult,configHierarchicalClientAndCUSPropertyResponse)){
				testPassed("Value from DB : "+SelectQueryResult.toString());
			}else {
				testFailed("Value from DB : "+SelectQueryResult.toString());
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
	}

	public void validateWrongClientInDB(Response WrongClientResponse) {
		try{
			String WrongClientResponseMessage = WrongClientResponse.jsonPath().getJsonObject("message").toString();

			String[] responseSplit = WrongClientResponseMessage.split("Client with ID ");
			String[]ClientID = responseSplit[1].split(" was not found");
			String SelectQuery = jsonObject.getAsString("SelectWrongClientProperty");
			SelectQuery = SelectQuery.replaceFirst("tempValue", ClientID[0]);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(SelectQueryResult.isEmpty()){
				testPassed("DB does not contains the wrong ClientID : "+ClientID[0]);
			}else {
				testFailed("DB contains the wrong ClientID : "+ClientID[0]);
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
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

	public HashMap<String, Integer> configureTopicSubscription() {
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		try{
			sbDestinationsHandler.ensureTopicCreation(clientPropertyjsonObject.getAsString("ServiceConfiguration"));
			sbDestinationsHandler.ensureSubscriptionCreation(clientPropertyjsonObject.getAsString("ServiceConfiguration"), clientPropertyjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.ensureTopicCreation(clientPropertyjsonObject.getAsString("ServiceConfigurationChanged"));
			sbDestinationsHandler.ensureSubscriptionCreation(clientPropertyjsonObject.getAsString("ServiceConfigurationChanged"), clientPropertyjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.ensureTopicCreation(clientPropertyjsonObject.getAsString("ServiceConfigurationResult"));
			sbDestinationsHandler.ensureSubscriptionCreation(clientPropertyjsonObject.getAsString("ServiceConfigurationResult"), clientPropertyjsonObject.getAsString("SubscriptionName"));
			subscriptionMessageCountAfterConfigure.put("ServiceConfiguration",(int)sbDestinationsHandler.getSubscriptionMessageCount(clientPropertyjsonObject.getAsString("ServiceConfiguration"), clientPropertyjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountAfterConfigure.put("ServiceConfigurationChanged",(int)sbDestinationsHandler.getSubscriptionMessageCount(clientPropertyjsonObject.getAsString("ServiceConfigurationChanged"), clientPropertyjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountAfterConfigure.put("ServiceConfigurationResult",(int)sbDestinationsHandler.getSubscriptionMessageCount(clientPropertyjsonObject.getAsString("ServiceConfigurationResult"), clientPropertyjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

	public HashMap<String, Integer> DeleteTopicSubscription(HashMap<String, Integer> subscriptionMessageCount, String  reconfigure) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		try{
			subscriptionMessageCountBeforeDelete.put("ServiceConfiguration",(int)sbDestinationsHandler.getSubscriptionMessageCount(clientPropertyjsonObject.getAsString("ServiceConfiguration"), clientPropertyjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountBeforeDelete.put("ServiceConfigurationChanged",(int)sbDestinationsHandler.getSubscriptionMessageCount(clientPropertyjsonObject.getAsString("ServiceConfigurationChanged"), clientPropertyjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountBeforeDelete.put("ServiceConfigurationResult",(int)sbDestinationsHandler.getSubscriptionMessageCount(clientPropertyjsonObject.getAsString("ServiceConfigurationResult"), clientPropertyjsonObject.getAsString("SubscriptionName")));
			sbDestinationsHandler.deleteSubscription(clientPropertyjsonObject.getAsString("ServiceConfiguration"), clientPropertyjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.deleteSubscription(clientPropertyjsonObject.getAsString("ServiceConfigurationChanged"), clientPropertyjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.deleteSubscription(clientPropertyjsonObject.getAsString("ServiceConfigurationResult"), clientPropertyjsonObject.getAsString("SubscriptionName"));
			info("Message count for SubScription  is : "+subscriptionMessageCountBeforeDelete);
			serviceBusValidation(subscriptionMessageCount,subscriptionMessageCountBeforeDelete,reconfigure);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}


	public void serviceBusValidation(HashMap<String, Integer> subscriptionMessageAfterConfiure ,HashMap<String, Integer> subscriptionMessageBeforeDelete, String  reconfigure) {

		try{
			if(reconfigure.equals("true")) {
				if(subscriptionMessageAfterConfiure.get("ServiceConfiguration")<subscriptionMessageBeforeDelete.get("ServiceConfiguration") &&
						subscriptionMessageAfterConfiure.get("ServiceConfigurationResult")<subscriptionMessageBeforeDelete.get("ServiceConfigurationResult")&&
						subscriptionMessageAfterConfiure.get("ServiceConfigurationChanged")<subscriptionMessageBeforeDelete.get("ServiceConfigurationChanged")) {
					testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
				}else {
					testFailed("Message count is same");
				}
			}
			else if (reconfigure.equals("false")){
				if(subscriptionMessageAfterConfiure.get("ServiceConfiguration")<subscriptionMessageBeforeDelete.get("ServiceConfiguration") &&
						subscriptionMessageAfterConfiure.get("ServiceConfigurationResult")<subscriptionMessageBeforeDelete.get("ServiceConfigurationResult") 
								&& subscriptionMessageAfterConfiure.get("ServiceConfigurationChanged")<subscriptionMessageBeforeDelete.get("ServiceConfigurationChanged")) {
					testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
				}else {
					testFailed("Message count is same");
				}
			}
		}catch(Exception e) {
			testFailed("Exception occured while comparing  the subscription message count:"+e.getMessage());
		}
	}
}