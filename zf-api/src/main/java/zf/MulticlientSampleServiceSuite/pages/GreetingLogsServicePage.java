package zf.MulticlientSampleServiceSuite.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class GreetingLogsServicePage extends RestApiUtility{

	JsonReader jsonData=new JsonReader();
	JSONObject sqljsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;





	public Response createGreetingLogsWithCorrectClientID(String servicename,String payload,String SchemaDetails) {
		JSONObject CreateGreetingJson = null;
		Response CreateGreetingWithResponse = null;
		String CreateGreetingMessage = null;
		try {
			CreateGreetingJson = JsonReader.getJsonObject(payload);	
			CreateGreetingMessage=CreateGreetingJson.get("greetingMessage").toString() + getRandomNumber();
			CreateGreetingJson.put("greetingMessage",CreateGreetingMessage);	
			info("Creating Greeting with:"+CreateGreetingJson.toJSONString());
			CreateGreetingWithResponse = CreateServices(servicename,CreateGreetingJson);	
			if( CreateGreetingWithResponse.getStatusCode()==200 &&
					CreateGreetingWithResponse.jsonPath().getJsonObject("greetingMessage").equals(CreateGreetingMessage)) {
				testPassed("The created Greeting Log idetail is : "+CreateGreetingWithResponse.getBody().asString());
			}
			else {
				testFailed("Greeting Log is not created and the body is : "+CreateGreetingWithResponse.getBody().asString());
			}
			String SelectQuery = sqljsonObject.getAsString(SchemaDetails);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),sqljsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if((DatabaseUtility.selectQueryComparision(SelectQueryResult,CreateGreetingMessage))&&
					(DatabaseUtility.selectQueryComparision(SelectQueryResult,CreateGreetingJson.get("clientId").toString()))){

				testPassed("Database does not contain the message. The message is  : "+CreateGreetingMessage);
			}else {
				testFailed("DB contains contain the message and the message  is :"+CreateGreetingMessage);
			}
		}	catch(Exception e) {
			testFailed("An exception has occured while Creating Greeting Log payload: "+e.getMessage());
		}
		return CreateGreetingWithResponse;
	}


	public void deleteGreetingLogsWithCorrectClientID(String servicename,String SchemaDetails,String GreetingMessage) {
		Response deleteGreetingWithResponse = null;
		try {
			deleteGreetingWithResponse = DeleteServices(servicename);	
			if( deleteGreetingWithResponse.getStatusCode()==204) {
				testPassed("The message is deleted ");
			}
			else {
				testFailed("Greeting message is not deleted");
			}
			String SelectQuery = sqljsonObject.getAsString(SchemaDetails);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),sqljsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(!(DatabaseUtility.selectQueryComparision(SelectQueryResult,GreetingMessage))){

				testPassed("Database does not contain the message. The message is  : "+GreetingMessage);
			}else {
				testFailed("DB contains contain the message and the message  is :"+GreetingMessage);
			}
		}	catch(Exception e) {
			testFailed("An exception has occured while Creating Greeting Log payload: "+e.getMessage());
		}
	}

	public void deleteSpecificGreetingWithMessageID(String servicename,String SchemaDetails,String serviceId,String message) {
		Response deleteGreetingWithResponse = null;
		try {
			deleteGreetingWithResponse = DeleteServices(servicename,serviceId);	
			if( deleteGreetingWithResponse.getStatusCode()==204) {
				testPassed("The message is deleted ");
			}
			else {
				testFailed("Greeting message is not deleted");
			}
			String SelectQuery = sqljsonObject.getAsString(SchemaDetails);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),sqljsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(!(DatabaseUtility.selectQueryComparision(SelectQueryResult,serviceId))){

				testPassed("Database does not contain the message . The message ID is  : "+serviceId);
			}else {
				testFailed("DB contains contain the message and the message ID is :"+serviceId);
			}
		}	catch(Exception e) {
			testFailed("An exception has occured while deleting Greeting Log payload: "+e.getMessage());
		}
	}

	public Response updateGreetingLogsWithCorrectClientID(String servicename,String payload,String SchemaDetails, String serviceId) {
		JSONObject UpdateGreetingJson = null;
		Response UpdateGreetingWithResponse = null;
		String UpdateGreetingMessage = null;
		try {
			UpdateGreetingJson = JsonReader.getJsonObject(payload);	
			UpdateGreetingMessage=UpdateGreetingJson.get("greetingMessage").toString() + getRandomNumber();
			UpdateGreetingJson.put("greetingMessage",UpdateGreetingMessage);
			info("Creating Greeting with:"+UpdateGreetingJson.toJSONString());
			UpdateGreetingWithResponse = PutServices(servicename,UpdateGreetingJson,serviceId);	
			if( UpdateGreetingWithResponse.getStatusCode()==200) {
				testPassed("The message is deleted ");
			}
			else {
				testFailed("Greeting message is not deleted");
			}		
			String SelectQuery = sqljsonObject.getAsString(SchemaDetails);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),sqljsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,UpdateGreetingMessage)){

				testPassed("Database  contain the message. The message is  : "+UpdateGreetingMessage);
			}else {
				testFailed("Database does not contains  the message and the message  is :"+UpdateGreetingMessage);
			}
		}	catch(Exception e) {
			testFailed("An exception has occured while Creating Greeting Log payload: "+e.getMessage());
		}
		return UpdateGreetingWithResponse;
	}


	public void getGreetingLogsWithCorrectClientID(String servicename,String serviceId,String greetingMSG)
	{
		Response getserviceResponse = null;
		try{getserviceResponse=GetServices(servicename,serviceId);
		if(getserviceResponse.getStatusCode()==200 && 
				getserviceResponse.jsonPath().getJsonObject("greetingMessage").toString().contains(greetingMSG)) {
			testPassed("The Get Property detail is : "+getserviceResponse.getBody().asString()+" and the get response contain the greeting message : "+greetingMSG);
		}
		else {
			testFailed("Get Property response detail is : "+getserviceResponse.getBody().asString()+" and the response dos not contain the greeting message : "+greetingMSG);
		}}catch(Exception e) {
			testFailed("An excption occured during get service and error message is :"+e.getMessage());
		}
	}

	public void getGreetingLogsWithWrongGreetingID(String servicename,String serviceId)
	{
		Response getserviceResponse = null;
		try{getserviceResponse=GetServices(servicename,serviceId);
		if(getserviceResponse.getStatusCode()==404) {
			testPassed("The Get Property detail is : "+getserviceResponse.getBody().asString());}
		else {
			testFailed("Get Property response detail is : "+getserviceResponse.getBody().asString());
		}}catch(Exception e) {
			testFailed("An excption occured during get service and error message is :"+e.getMessage());
		}
	}

	public void getMessageWithWrongClientIDCorrectGreetingID(String servicename)
	{
		Response MessageWithWrongClientIDCorrectGreetingIDResponse = null;
		try	{MessageWithWrongClientIDCorrectGreetingIDResponse=GetServices(servicename);
		if(MessageWithWrongClientIDCorrectGreetingIDResponse.getStatusCode()==404 &&
				MessageWithWrongClientIDCorrectGreetingIDResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("Get Property detail when wrong client ID is : "+MessageWithWrongClientIDCorrectGreetingIDResponse.getBody().asString());
		}
		else {
			testFailed("Get Property response detail when wrong client ID is : "+MessageWithWrongClientIDCorrectGreetingIDResponse.getBody().asString());
		}}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void getGreetingWithWrongClientID(String servicename)
	{Response GreetingWithWrongClientIDResponse =null;
	try {
		GreetingWithWrongClientIDResponse = GetServices(servicename);
		if(GreetingWithWrongClientIDResponse.getStatusCode()==404 &&
				GreetingWithWrongClientIDResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("Get Property detail is : "+GreetingWithWrongClientIDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+GreetingWithWrongClientIDResponse.getBody().asString());
			testFailed("Get Property detail is : "+GreetingWithWrongClientIDResponse);
		}}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}


	public Response sendGreetingWithWrongClientID(String servicename, String payLoad){
		Response sendGreetingWithWrongClientIDResponse =null;
		JSONObject sendGreetingWithWrongClientIDJson = null;
		sendGreetingWithWrongClientIDJson=JsonReader.getJsonObject(payLoad);			
		sendGreetingWithWrongClientIDJson.put("clientId",sendGreetingWithWrongClientIDJson.get("clientId").toString() +getRandomNumber());
		sendGreetingWithWrongClientIDJson.put("greetingMessage",sendGreetingWithWrongClientIDJson.get("greetingMessage").toString() +getRandomNumber());
		sendGreetingWithWrongClientIDResponse = PutServices(servicename, sendGreetingWithWrongClientIDJson);
		if(sendGreetingWithWrongClientIDResponse.getStatusCode()==404 &&
				sendGreetingWithWrongClientIDResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("Send greeting log response is : "+sendGreetingWithWrongClientIDResponse.getBody().asString());
		}
		else {
			info("Send greeting log status code is : "+sendGreetingWithWrongClientIDResponse.getStatusCode());
			testFailed("Send greeting log  response is : "+sendGreetingWithWrongClientIDResponse.getBody().asString());
		}
		return sendGreetingWithWrongClientIDResponse;
	}
	public String createGreetingLogWithNoClientid() {

		JSONObject CreateGreetingLogsWithNoclientIdJson = null;
		String GreetingLogId = null;
		Response GreetingLogResponse = null;
		try {
			CreateGreetingLogsWithNoclientIdJson = JsonReader.getJsonObject("CreateGreetingLogswithNoClientIdData");			
			CreateGreetingLogsWithNoclientIdJson.put("clientId",CreateGreetingLogsWithNoclientIdJson.get("clientId").toString());
			CreateGreetingLogsWithNoclientIdJson.put("greetingMessage",CreateGreetingLogsWithNoclientIdJson.get("greetingMessage").toString() + getRandomNumber());	

			info("Creating Greeting logs with no client id :"+CreateGreetingLogsWithNoclientIdJson.toJSONString());
			GreetingLogResponse =CreateServices("GreetingLogType",CreateGreetingLogsWithNoclientIdJson);	
			GreetingLogId = GreetingLogResponse.getBody().asString();	
			if( GreetingLogResponse!=null && GreetingLogResponse.getStatusCode()==404) {
				testPassed("The created GreetingLog id is : "+GreetingLogId);
			}
			else {
				testFailed("GreetingType is not created and the body is : "+GreetingLogResponse.getBody().asString());
			}

		}catch(Exception e) {
			testFailed("An exception has occured while Creating GreetingType payload:CreateGreetingLogswithNoClientIdData ");
		}					
		return GreetingLogId;
	}	
	public void getGreetingLogwithNoClientid(String servicename)
	{
		Response GreetingLogwithNoClientidResponse = GetServices("GreetingLogwithNoClientId");
		if(GreetingLogwithNoClientidResponse.getStatusCode()==404) {
			testPassed("Response detail is : "+GreetingLogwithNoClientidResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+GreetingLogwithNoClientidResponse.getBody().asString());
			testFailed("Get Property detail is : "+GreetingLogwithNoClientidResponse);

		}
	}	
	public Response GreetingLogIdWithNoClientid(String servicename, String payLoad) {

		Response putServicesResponse =null;
		JSONObject GreetingLogIdWithNoClientidJson = null;
		GreetingLogIdWithNoClientidJson=JsonReader.getJsonObject(payLoad);			
		GreetingLogIdWithNoClientidJson.put("clientId",GreetingLogIdWithNoClientidJson.get("clientId").toString());
		GreetingLogIdWithNoClientidJson.put("greetingMessage",GreetingLogIdWithNoClientidJson.get("greetingMessage").toString() + getRandomNumber());	
		//	createPropertyForclientJson.put("test2",createPropertyForclientJson.get("test2").toString() + getRandomNumber());
		putServicesResponse = PutServices(servicename, GreetingLogIdWithNoClientidJson);
		if(putServicesResponse.getStatusCode()==404) {

			testPassed("Create Client property response is : "+putServicesResponse.getBody().asString());
		}
		else {
			info("Create Client property status code is : "+putServicesResponse.getStatusCode());
			testFailed("Create Client property response is : "+putServicesResponse.getBody().asString());
		}
		return putServicesResponse;
	}	

	public String createGreetingLogsWithWrongClientID(String servicename, String jsonpayload) {
		JSONObject CreateGreetingWithWrongIDJson = null;
		Response CreateGreetingWithWrongIDResponse = null;
		String CreateGreetingId = null;
		try {
			CreateGreetingWithWrongIDJson = JsonReader.getJsonObject(jsonpayload);			
			CreateGreetingWithWrongIDJson.put("clientId",CreateGreetingWithWrongIDJson.get("clientId").toString() + getRandomNumber());
			CreateGreetingWithWrongIDJson.put("greetingMessage",CreateGreetingWithWrongIDJson.get("greetingMessage").toString() + getRandomNumber());	
			info("Creating Greeting with:"+CreateGreetingWithWrongIDJson.toJSONString());
			CreateGreetingWithWrongIDResponse = CreateServices(servicename,CreateGreetingWithWrongIDJson);	

			if(CreateGreetingWithWrongIDResponse.getStatusCode()==404 &&
					CreateGreetingWithWrongIDResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
				testPassed("The created Greeting Log id is : "+CreateGreetingId);
			}
			else {
				testFailed("Greeting Log is not created and the body is : "+CreateGreetingWithWrongIDResponse.getBody().asString());
			}
		}	catch(Exception e) {
			testFailed("An exception has occured while Creating Greeting Log payload: JSONCreateGreetingLogsWithWrongClientID ");
		}
		return CreateGreetingId;
	}
	
	public void getGreetinglogDetails(String servicename,String greetingMessage) {
		Response getResponse=null;
		try {
			getResponse=GetServices(servicename);
			if(getResponse.getStatusCode()==200 && getResponse.getBody().asString().contains(greetingMessage)){
				testPassed("The get response details is ::"+getResponse.getBody().asString());
			}else {
				testFailed("The get response details is ::"+getResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured: "+e.getMessage());
		}
	}
}
