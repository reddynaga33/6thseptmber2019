package zf.ServiceManagementServiceMulticlientSuite.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.ServiceBusClients;
import framework.ServiceBusConnectionConfigurations;
import framework.ServiceBusDestinationsHandlers;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IClientServicePage extends RestApiUtility {
	//	JsonReader jsonData=new JsonReader();
	DatabaseUtility databaseutility=new DatabaseUtility();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	List<String> SelectQueryResult = null;

	public void getOneClientdetails(String servicename) {
		Response ServiceDescriptorResponse=null;
		try {
			ServiceDescriptorResponse = GetServices(servicename);

			if(ServiceDescriptorResponse.getStatusCode()==200) {				

				testPassed("The get  Response is : "+ServiceDescriptorResponse.getBody().asString());
			}else {
				testFailed("The Response is "+ServiceDescriptorResponse.getBody().jsonPath());
			}	

		}catch(Exception e) {
			testFailed("An execption has generated while getting client details and the message is : "+e.getMessage());
		}
	}
	public Response VerifyUserISAbleToAssignTheServicesToTheClient(String ServiceDescriptorId) {

		Response SDClientAssignResponse=null;
		SDClientAssignResponse=PutServices("AssignClientServiceSD",ServiceDescriptorId);
		info("Service Descriptor assigning response is: "+SDClientAssignResponse.getBody().asString());
		if(SDClientAssignResponse.getStatusCode()==202) {
			testPassed("The Service assigned to the Client is Success");
		}else {
			testFailed("The Service assigned to the Client is Success");
		}
		return SDClientAssignResponse;
	}

	public void getWrongClientdetails(String servicename) {
		Response ServiceDescriptorResponse=null;
		try {
			ServiceDescriptorResponse = GetServices(servicename);

			if(ServiceDescriptorResponse.getStatusCode()==404) {				

				testPassed("The get  Response is : "+ServiceDescriptorResponse.getBody().asString());
			}else {
				testFailed("The Response is "+ServiceDescriptorResponse.getBody().jsonPath());
			}	

		}catch(Exception e) {
			testFailed("An execption has generated while getting client details and the message is : "+e.getMessage());
		}
	}
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

	public void configureTopicSubscription(String TopicName) {
		JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());
		try{
			sbDestinationsHandler.ensureTopicCreation(jsonObject.getAsString(TopicName));
			sbDestinationsHandler.ensureSubscriptionCreation(jsonObject.getAsString(TopicName), jsonObject.getAsString("ServiceSubscription"));
		}catch(Exception e) {
			testFailed("Exception occured while creating the subscription:"+e.getMessage());
		}
	}
	//v 27th june C62232 Verify user is able to get the client details
	public void VerifyUserIsAbleToGetClientDetails(String servicename)
	{
		Response UserIsAbleToGetClientDetailsResponse = GetServices("UserClientDetails");
		if(UserIsAbleToGetClientDetailsResponse.getStatusCode()==200) {
			testPassed("Response detail is : "+UserIsAbleToGetClientDetailsResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+UserIsAbleToGetClientDetailsResponse.getBody().asString());
			testFailed("Get Property detail is : "+UserIsAbleToGetClientDetailsResponse);

		}
	}

	public String getserviceDetails(String servicename,String SDName)
	{
		Response serviceResponse =	null;
		try {
			serviceResponse=GetServices(servicename,SDName);

			if(serviceResponse.getStatusCode()==200){
				testPassed("Response detail is : "+serviceResponse.getBody().asString());
			}
			else {
				testFailed("Get Property detail is : "+serviceResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An excepton occured"+e.getMessage());
		}
		return serviceResponse.getBody().asString();}


	public String getServiceDescriptorDetailsInDB(String queryDetails,String SDName) {
		List<String> SDDetails=null;
		String SDData=null;
		String[] SDID=null;
		try {
			String SelectQuery =jsonObject.getAsString(queryDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", SDName);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,SDName)){
				SDDetails=SelectQueryResult;
				SDID=SDDetails.toString().split(" ");
				SDData=SDID[0].replace("[", "");

				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the Service Descriptor Id : "+SDName+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the Service Descriptor Id : "+SDName+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
		return SDData;
	}

	public String getServiceDescriptorAndClientInDB(String queryDetails,String SDName) {
		String SDDetails=null;
		try {
			String SelectQuery =jsonObject.getAsString(queryDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", SDName);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,SDName)){
				SDDetails=SelectQueryResult.toString();
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the Service Descriptor Id : "+SDName+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the Service Descriptor Id : "+SDName+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
		return SDDetails;
	}

	public String getServiceDescriptorNotINClientDB(String queryDetails,String SDName) {
		String SDDetails=null;
		try {
			String SelectQuery =jsonObject.getAsString(queryDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", SDName);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(!(DatabaseUtility.selectQueryComparision(SelectQueryResult,SDName))){
				SDDetails=SelectQueryResult.toString();
				testPassed("Value from DB "+SelectQueryResult.toString()+" does notcontains the Service Descriptor Id : "+SDName+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" contain the Service Descriptor Id : "+SDName+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
		return SDDetails;
	}

	public void validateClientDetails(String databaseResult,String clientID) {
		try {
			if(databaseResult.contains(clientID)) {
				testPassed("The service is assigned to client and the database result is : "+databaseResult);}
			else {
				testFailed("The service is not assigned for the client :"+clientID);
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
	}

	public void validateSDAndClientDetailsPresent(String databaseResult,String clientID) {
		try {
			if(!(databaseResult.contains(clientID.toLowerCase()))) {
				testPassed("After Unassigned the client or SD details not present : "+databaseResult);}
			else {
				testFailed("The service is not unassigned for the client :"+clientID);
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
	}

	public String getServiceDetailsOfAClient(String servicename,String SDName) {
		Response serviceResponse =	null;
		try {
			serviceResponse=GetServices(servicename);

			if((serviceResponse.getStatusCode()==200)){
				testPassed("Response detail is : "+serviceResponse.getBody().asString());
			}
			else {
				testFailed("Get Property detail is : "+serviceResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An excepton occured"+e.getMessage());
		}
		return serviceResponse.getBody().asString();
	}

	public String assigningServiceDescriptorToClient(String servicename,String SDName) {
		String result = null;

		Response ServiceDescriptorResponse = null;	
		try {

			ServiceDescriptorResponse = PutServices(servicename,SDName);
			if( ServiceDescriptorResponse.getStatusCode()==202) {
				testPassed("Assigned ServiceDescriptor Response is : "+ServiceDescriptorResponse.getBody().asString());
				result=ServiceDescriptorResponse.jsonPath().getJsonObject("status");
			}
			else {
				testFailed("ServiceDescriptorToCLientWithoutAppId Response is : "+ServiceDescriptorResponse);
			}
		} catch(Exception e) {
			testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return result;
	}

	public Response getSagaStatus(String servicename,String SagaStatus) {
		Response serviceDescriptorJson = null;			
		String serviceDescriptorResponse = null;
		try {
			sleep(20000);
			serviceDescriptorJson = GetServices(servicename,SagaStatus);
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
		return serviceDescriptorJson;
	}

	public String unAssigningServiceDescriptorToClient(String servicename,String SDName) {
		Response serviceDescriptorJson = null;			
		String serviceDescriptorResponse = null;
		String result=null;
		try {
			sleep(20000);
			serviceDescriptorJson = DeleteServices(servicename,SDName);
			serviceDescriptorResponse = serviceDescriptorJson.getBody().asString();
			if(serviceDescriptorJson.getStatusCode()==202) {
				result=serviceDescriptorJson.jsonPath().getJsonObject("status");
				testPassed("ServiceDescriptorResponse is :"+serviceDescriptorResponse);
			}else {
				info("serviceDescriptor Statuc Code is :"+ serviceDescriptorJson.getStatusCode());
				testFailed("ServiceDescriptorResponse is :"+serviceDescriptorResponse);
			}	
		}catch(Exception e) {
			testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return result;
	}
}