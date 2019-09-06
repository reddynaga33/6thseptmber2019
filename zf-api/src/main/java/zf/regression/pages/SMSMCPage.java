package zf.regression.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

public class SMSMCPage extends RestApiUtility{
	private ServiceBusNamespace namespace;
	private static ServiceBusDestinationsHandlers sbDestinationsHandler = null;
	private static ServiceBusClients sbClient = null;

	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	String UUIDNumber = UUID.randomUUID().toString();
	
	public void destinationUsingRestAPI(String destinationUsingRestAPI, String messgaeAndEvent) {
		try {	Response getMessageDestinationResponse = GetServices(destinationUsingRestAPI,messgaeAndEvent);
		info("Response body is : "+getMessageDestinationResponse.getBody().asString());
		if(getMessageDestinationResponse.getStatusCode()==404) {
			testPassed("Message destination is retrieved");
		}else {
			testFailed("Test failed Client information is not retrieved");
		}}catch(Exception e){
			testFailed("An exception occured while retrieving message destination and the message is : "+e.getMessage());
		}
	}

	public void eventDesinationService() {
		try {	Response getEventDestinationResponse = GetServices("eventDestination");
		info("Response body is : "+getEventDestinationResponse.getBody().asString());
		if(getEventDestinationResponse.getStatusCode()==404) {
			testPassed("Event Destination information is retrieved");
		}else {
			testFailed("Test failed while retrieving event destination information ");
		}
		}catch(Exception e){
			testFailed("An exception occured while retrieving event destination and the message is : "+e.getMessage());
		}
	}
	public void validateCreatePropertyAndGetproperty(String getResponsevalue, JSONObject createPropertyForClient) {
		try {	
		
			if(createPropertyForClient.get("test").equals(getResponsevalue)) {
				testPassed("The retrieved information is same as created property.The created value is : "+createPropertyForClient.get("test")+" and the get response is : "+getResponsevalue);
			}else {
				testFailed("The retrieved information is not same as created property.The created value is : "+createPropertyForClient.get("test")+" and the get response is : "+getResponsevalue);
			}
		}catch(Exception e){
			testFailed("An exception occured while comparing the data and the message is : "+e.getMessage());
		}}
	public void validateCreatePropertyAndGetpropertyResponse(Response getResponsevalue, JSONObject createPropertyForClient) {
		try {	
			String getResponsevalue1 = (getResponsevalue.jsonPath().getJsonObject("test")).toString();
			if(createPropertyForClient.get("test").equals(getResponsevalue1)) {
				testPassed("The retrieved information is same as created property.The created value is : "+createPropertyForClient.get("test")+" and the get response is : "+getResponsevalue1);
			}else {
				testFailed("The retrieved information is not same as created property.The created value is : "+createPropertyForClient.get("test")+" and the get response is : "+getResponsevalue1);
			}
		}catch(Exception e){
			testFailed("An exception occured while comparing the data and the message is : "+e.getMessage());
		}}

	public JSONObject createPropertyForClient(String servicename, String payLoad,String reconfigureValue){
		Response putServicesResponse =null;
		JSONObject createPropertyForclientJson = null;
		try {		
			createPropertyForclientJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForclientJson.put("test",createPropertyForclientJson.get("test").toString() + getRandomNumber());
			createPropertyForclientJson.put("test1",createPropertyForclientJson.get("test1").toString() + getRandomNumber());	
			createPropertyForclientJson.put("test2",createPropertyForclientJson.get("test2").toString() + getRandomNumber());
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
		return createPropertyForclientJson;
	}

	public JSONObject createPropertyForCUS(String servicename, String payLoad,String reconfigureValue){
		Response putServicesResponse =null;
		JSONObject createPropertyForCUSJson = null;
		try {
			createPropertyForCUSJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForCUSJson.put("test",createPropertyForCUSJson.get("test").toString() + getRandomNumber());
			createPropertyForCUSJson.put("test1",createPropertyForCUSJson.get("test1").toString() + getRandomNumber());	
			createPropertyForCUSJson.put("test2",createPropertyForCUSJson.get("test2").toString() + getRandomNumber());
			info("Creating Client property with  data : "+createPropertyForCUSJson.toJSONString());
			putServicesResponse = PutServices(servicename, createPropertyForCUSJson, reconfigureValue);
			if(putServicesResponse.getStatusCode()==202) {

				testPassed("Create CUS property response is : "+putServicesResponse.getBody().asString());
			}
			else {
				info("Create CUS property status code is : "+putServicesResponse.getStatusCode());
				testFailed("Create CUS property response is : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return createPropertyForCUSJson;
	}

	public void validateCreateCUSAndHierarchicalproperty(JSONObject createPropertyForCUS,Response HierarchicalCUSProperty, String clientAndCUS) {
		try{HashMap<String,String> HierarchicalCUSPropertyObject = HierarchicalCUSProperty.jsonPath().getJsonObject(clientAndCUS);
		if(HierarchicalCUSPropertyObject.get("test").equals(createPropertyForCUS.get("test"))&&
				HierarchicalCUSPropertyObject.get("test1").equals(createPropertyForCUS.get("test1"))&&
				HierarchicalCUSPropertyObject.get("test2").equals(createPropertyForCUS.get("test2"))) {	
			testPassed("Create CUS property  and Hierarchical property response is same. The HierarchicalCUSProperty response details is : "+
					HierarchicalCUSPropertyObject.values()+" and create client details is : "+createPropertyForCUS.values()+" are equal");
		}
		else {
			testFailed("Create CUS property  and Hierarchical property response is  not same.");
		}}catch(Exception e) {
			testFailed("Exception occured while comparing the  CUS property and get Hierarchical property :"+e.getMessage());
		}
	}	

	public String createServiceDescriptor(String payload){
		String UUIDNumber = UUID.randomUUID().toString();
		info("Service Descriptor ID  : "+UUIDNumber);
		JSONObject createServiceDescriptorJson = null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("name",createServiceDescriptorJson.get("name").toString() + getRandomNumber());
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			Response putServicesResponse =  PostServices("CreateServiceDescriptor", createServiceDescriptorJson, UUIDNumber);
			if(putServicesResponse.getStatusCode()==202) {

				testPassed("Create Service descriptor response is : "+putServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return UUIDNumber;
	}


	public String createServiceDescriptorResponse(String servicename,String payload){
	
		info("Service Descriptor ID  : "+UUIDNumber);
		JSONObject createServiceDescriptorJson = null;
		String sagaStatus=null;
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			createServiceDescriptorJson.put("uuid",UUIDNumber);
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson);
			sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
			if(postServicesResponse.getStatusCode()==202) {
				sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}
	public String createServiceDescriptorWithWrongID(String servicename,String payload){
 UUIDNumber = UUID.randomUUID().toString();
	
		JSONObject createServiceDescriptorJson = null;
		String sagaStatus=null;
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("name",createServiceDescriptorJson.get("name").toString() + getRandomNumber());
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			createServiceDescriptorJson.put("uuid",createServiceDescriptorJson.get("uuid").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson);
			if(postServicesResponse.getStatusCode()==400) {
			
				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}
	
	
	public void getSagaStatus(String servicename,String sagaStatus) {
		Response getServicesResponse =null;
		try{
			 getServicesResponse = GetServices(servicename,sagaStatus);
			if(getServicesResponse.getStatusCode()==200) {

				testPassed("Create Service descriptor response is : "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+getServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		
	}
	
	public void getServiceDescriptorID(String servicename) {
		Response getServicesResponse =null;
		try {
		getServicesResponse = GetServices(servicename,UUIDNumber);
		if(getServicesResponse.getStatusCode()==200) {

			testPassed("Create Service descriptor response is : "+getServicesResponse.getBody().asString());
		}
		else {
			testFailed("Create Service descriptor response is  : "+getServicesResponse.getBody().asString());
		}}catch(Exception e) {
			testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
		}
	}
	
	public Response UpdateArtifactIDwithExistingSDID(String ServiceDescriptprID,String payload){
		info("Service Descriptor ID  : "+ServiceDescriptprID);
		JSONObject createServiceDescriptorJson = null;
		Response putServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());

			putServicesResponse =  PutServices("CreateServiceDescriptor", createServiceDescriptorJson, ServiceDescriptprID);
			if(putServicesResponse.getStatusCode()==202) {

				testPassed("Create Service descriptor response is : "+putServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return putServicesResponse;
	}
	
	public void assignNewServicesToClient(String servicename,String ServiceDescriptprID) {
		Response putServicesResponse=null;
		JSONObject createServiceDescriptorJson = null;
		try {
			putServicesResponse =  PutServices(servicename,createServiceDescriptorJson,ServiceDescriptprID);
			if(putServicesResponse.getStatusCode()==202) {

				testPassed("Create Service descriptor response is : "+putServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
	}
	
	public HashMap<String, Integer> configureTopicSubscription(String CreateServiceDescriptorTopicName,String ServiceDescriptor, String ServiceDescriptorResult) {
		HashMap<String, Integer> subscriptionMessageCountAfterConfigure = new HashMap<String, Integer>();
		JSONObject ServiceDescriptorjsonObject = JsonReader.getJsonObject(CreateServiceDescriptorTopicName);
		try{
			sbDestinationsHandler.ensureTopicCreation(ServiceDescriptorjsonObject.getAsString(ServiceDescriptor));
			sbDestinationsHandler.ensureSubscriptionCreation(ServiceDescriptorjsonObject.getAsString(ServiceDescriptor), ServiceDescriptorjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.ensureTopicCreation(ServiceDescriptorjsonObject.getAsString(ServiceDescriptorResult));
			sbDestinationsHandler.ensureSubscriptionCreation(ServiceDescriptorjsonObject.getAsString(ServiceDescriptorResult), ServiceDescriptorjsonObject.getAsString("SubscriptionName"));
			subscriptionMessageCountAfterConfigure.put(ServiceDescriptor,(int)sbDestinationsHandler.getSubscriptionMessageCount(ServiceDescriptorjsonObject.getAsString(ServiceDescriptor), ServiceDescriptorjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountAfterConfigure.put(ServiceDescriptorResult,(int)sbDestinationsHandler.getSubscriptionMessageCount(ServiceDescriptorjsonObject.getAsString(ServiceDescriptorResult), ServiceDescriptorjsonObject.getAsString("SubscriptionName")));
			info("Message count for SubScriptions  is : "+subscriptionMessageCountAfterConfigure);
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountAfterConfigure;
	}

	public HashMap<String, Integer> DeleteTopicSubscription(String CreateServiceDescriptorTopicName,String ServiceDescriptor, String ServiceDescriptorResult,HashMap<String, Integer> subscriptionMessageCount) {
		HashMap<String, Integer> subscriptionMessageCountBeforeDelete = new HashMap<String, Integer>();
		JSONObject ServiceDescriptorjsonObject = JsonReader.getJsonObject(CreateServiceDescriptorTopicName);
		try{
			subscriptionMessageCountBeforeDelete.put(ServiceDescriptor,(int)sbDestinationsHandler.getSubscriptionMessageCount(ServiceDescriptorjsonObject.getAsString(ServiceDescriptor), ServiceDescriptorjsonObject.getAsString("SubscriptionName")));
			subscriptionMessageCountBeforeDelete.put(ServiceDescriptorResult,(int)sbDestinationsHandler.getSubscriptionMessageCount(ServiceDescriptorjsonObject.getAsString(ServiceDescriptorResult), ServiceDescriptorjsonObject.getAsString("SubscriptionName")));
			sbDestinationsHandler.deleteSubscription(ServiceDescriptorjsonObject.getAsString(ServiceDescriptor), ServiceDescriptorjsonObject.getAsString("SubscriptionName"));
			sbDestinationsHandler.deleteSubscription(ServiceDescriptorjsonObject.getAsString(ServiceDescriptorResult), ServiceDescriptorjsonObject.getAsString("SubscriptionName"));
			info("Message count for SubScription  is : "+subscriptionMessageCountBeforeDelete);
					serviceBusValidation(subscriptionMessageCount,ServiceDescriptor,ServiceDescriptorResult,subscriptionMessageCountBeforeDelete);
		}catch(Exception e) {
			testFailed("Exception occured while deleting  the subscription:"+e.getMessage());
		}
		return subscriptionMessageCountBeforeDelete;
	}

	
	public void serviceBusValidation(HashMap<String, Integer> subscriptionMessageAfterConfiure ,String ServiceDescriptor, String ServiceDescriptorResult,HashMap<String, Integer> subscriptionMessageBeforeDelete) {

		try{
			
				if(subscriptionMessageAfterConfiure.get(ServiceDescriptor)<subscriptionMessageBeforeDelete.get(ServiceDescriptor) &&
						subscriptionMessageAfterConfiure.get(ServiceDescriptorResult)<subscriptionMessageBeforeDelete.get(ServiceDescriptorResult)) {
					testPassed("Message count  before sending is more than  count after receiving the message is  Hence message is received to the subscription");
				}else {
					testFailed("Message count is same");
				}
			}
			catch(Exception e) {
			testFailed("Exception occured while comparing  the subscription message count:"+e.getMessage());
		}}


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

	public void validateServiceDescriptionDetails(String createServiceDescriptorID) {

		try {
			sleep(20000);
			String SelectQuery = jsonObject.getAsString("SelectcreatedServiceDescriptorID");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", createServiceDescriptorID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());

			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,createServiceDescriptorID)){
				testPassed("Database contains the service descriptor ID : "+createServiceDescriptorID );
			}else {
				testFailed("Database does not contains the service descriptor ID : "+createServiceDescriptorID);
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}

	}

	public void getcreatedServiceDescriptorID(String serviename,String serviceDescriptorID) {
		Response getServiceResponse=null;
	try {
		getServiceResponse=GetServices(serviename,serviceDescriptorID);
		if(getServiceResponse.getStatusCode()==200) {

			testPassed("Get Service descriptor response is : "+getServiceResponse.getBody().asString());
		}
		else {
			testFailed("get Service descriptor response is  : "+getServiceResponse.getBody().asString());
		}}catch(Exception e) {
			testFailed("Exception occured while  and the message is :"+e.getMessage());
		}
	}

	
	public Response getSagaStatusCLientWithAppId(String SagaStatus) {
		Response serviceDescriptorJson = null;			
		String serviceDescriptorResponse = null;
		try {
			serviceDescriptorJson = GetServices("sagastatussuccess",SagaStatus);
			serviceDescriptorResponse = serviceDescriptorJson.getBody().asString();
			if(serviceDescriptorJson.getStatusCode()==200) {
				 testPassed("ServiceDescriptorResponse is :"+serviceDescriptorResponse);
			}else {
				 info("serviceDescriptor Statuc Code is :"+ serviceDescriptorJson.getStatusCode());
				testFailed("ServiceDescriptorResponse is :"+serviceDescriptorResponse);
			}	
		}catch(Exception e) {
			testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return serviceDescriptorJson;}
	//Need Review

	public void getClientDetails(String servicename) 
	{
		Response ClientDetailsResponse = GetServices(servicename);
		if(ClientDetailsResponse.getStatusCode()==200) {
			testPassed("Get Property detail is : "+ClientDetailsResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+ClientDetailsResponse.getBody().asString());
			testFailed("Get Property detail is : "+ClientDetailsResponse);
		}
	}

	public void getServiceDetailsOfOneClient(String servicename) 
	{
		Response ServiceDetailsOfOneClientResponse = GetServices(servicename);
		if(ServiceDetailsOfOneClientResponse.getStatusCode()==200) {
			testPassed("Get Property detail is : "+ServiceDetailsOfOneClientResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+ServiceDetailsOfOneClientResponse.getBody().asString());
			testFailed("Get Property detail is : "+ServiceDetailsOfOneClientResponse);
		}
	}

	

	public void validateServiceDescriptionDetail(String createServiceDescriptorID) {

		try {
			String SelectQuery = jsonObject.getAsString("SelectClientUserService");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", createServiceDescriptorID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());

			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,createServiceDescriptorID)){
				testPassed("Database contains the service descriptor ID : "+createServiceDescriptorID );
			}else {
				testFailed("Database does not contains the service descriptor ID : "+createServiceDescriptorID);
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}

	}
	public void getSDIDOfTheUser(String servicename,String createServiceDescriptorID)
	{
		Response CUSDetailsOfCreatedCUSUserResponse = GetServices(servicename,createServiceDescriptorID);
		if(CUSDetailsOfCreatedCUSUserResponse.getStatusCode()==200) {
			testPassed("Get Property detail is : "+CUSDetailsOfCreatedCUSUserResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+CUSDetailsOfCreatedCUSUserResponse.getBody().asString());
			testFailed("Get Property detail is : "+CUSDetailsOfCreatedCUSUserResponse);
		}	
	}
	
	public void getSagaEntityValidate(String servicename, String getSagaStatus) {
		try{Response getSagaEntityResponse = GetServices(servicename, getSagaStatus);
		String SagaStatusResponseValue = getResponseValue(getSagaEntityResponse,"status");
		if(getSagaEntityResponse.getStatusCode()==404 && SagaStatusResponseValue.equals("FAIL")) {
			testPassed("Saga status response body  is :"+getSagaEntityResponse.getBody().asString()+"  and status is : "+SagaStatusResponseValue);
		}
		else {
			info("Saga response detail is : "+getSagaEntityResponse.getBody().asString());
			testFailed("Saga status detail is : "+SagaStatusResponseValue);
		}}catch(Exception e) {
			testFailed("Exception occured while getting the saga details and the message is :"+e.getMessage());
		}
	}
	
	public Response createServiceDescriptorResponse(String payload){
		String UUIDNumber = UUID.randomUUID().toString();
		info("Service Descriptor ID  : "+UUIDNumber);
		JSONObject createServiceDescriptorJson = null;
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			createServiceDescriptorJson.put("UUID",UUIDNumber);
			postServicesResponse =  PostServices("CreateServiceDescriptor", createServiceDescriptorJson);
			if(postServicesResponse.getStatusCode()==202) {

				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return postServicesResponse;
	}

}