package zf.MulticlientSampleServiceSuite.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.TestLogger;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class GlobalGreetingLogsServicePages extends RestApiUtility{

	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	JsonReader jsonReader=new JsonReader();
	String greetingMessage =null;

	public String VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase(String Greeting, String globalGreeting) {

		Response GlobalGreetingsResponse=null;
		String GreetingID = null;
		JSONObject GreetingJsonObject = null;
		try {
			GreetingJsonObject=JsonReader.getJsonObject(Greeting);			
			GreetingJsonObject.put("message",GreetingJsonObject.get("message").toString()+ getRandomNumber());
			greetingMessage = GreetingJsonObject.get("message").toString();
			GlobalGreetingsResponse = PostServices(globalGreeting,GreetingJsonObject);
			GreetingID=GlobalGreetingsResponse.jsonPath().getJsonObject("id");
			info("Response from greeting is : "+GlobalGreetingsResponse.getBody().asString());
			if(GlobalGreetingsResponse.getStatusCode()==200 && GlobalGreetingsResponse.jsonPath().getJsonObject("message").equals(greetingMessage) && GlobalGreetingsResponse.jsonPath().getJsonObject("scope").equals("GLOBAL")){
				testPassed("Greeting is send to client");
			}else {
				testFailed("Test failed Greeting is not send to client");
			}

			String SelectQuery = jsonObject.getAsString("SelectGlobalGreetings");
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,greetingMessage)){
				testPassed("Database contain the message is  : "+greetingMessage);
			}else {
				testFailed("DB contains does not contain the message. is :"+greetingMessage);
			}
		}catch(Exception e) {
			testFailed ("An exception occured during request and message is : "+e.getMessage());
		}
		return GreetingID;
	}

	public void VerifyUserIsAbleToGetAllTheGreetingSendForSavedToTheGlobalDatabase(String globalGreeting) {

		Response GetGlobalGreetingsResponse=null;
		try {
			GetGlobalGreetingsResponse = GetServices(globalGreeting);
			info("Response from getting request of Greeting is : "+GetGlobalGreetingsResponse.getBody().asString());
			if(GetGlobalGreetingsResponse.getStatusCode()==200 && GetGlobalGreetingsResponse.jsonPath().getJsonObject("scope").toString().contains("GLOBAL")){
				testPassed("Getting request of the Global Greetings is Successfull and Response from getting request of Greeting is : "+GetGlobalGreetingsResponse.getBody().asString());
			}else {
				testFailed("Getting request of the Global Greetings is Failed");
			}		
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());	
		}
	}
	public void VerifyUserIsAbleToGetTheGreetingSavedTheGlobalDatabaseUsingCorrectGreetingId(String GreetingID) {
		Response GreetingResponse=null;
	try {	GreetingResponse=GetServices("GlobalGreetings",GreetingID);
		if(GreetingResponse.getStatusCode()==200 && GreetingResponse.jsonPath().getJsonObject("id").equals(GreetingID)) {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testPassed("The Greeting id from Global database is passed");
		}
		else {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testFailed("The Greeting id from Global database is failed");
		}}catch(Exception e){
			testFailed("An exception occured and the error message is "+e.getMessage());
		}
	}
	
	public void VerifyUserIsAbleToGetTheMessageSavedTheGlobalDatabaseUsingWrongGreetingId(String GreetingID) {
		Response GreetingResponse=null;
		try{GreetingResponse=GetServices("GlobalGreetings",GreetingID);
		if(GreetingResponse.getStatusCode()==500) {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testPassed("The Greeting id from Global database is failed because of wrong id");
		}
		else {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testFailed("The Greeting id from Global database is passed becaus of id");
		}}catch(Exception e){
			testFailed("An exception occured and the error message is "+e.getMessage());
		}
	}
	public void VerifyUserIsAbleToGetTheMessageSavedTheGlobalDatabaseUsingNoGreetingId() {
		Response GreetingResponse=null;
		try{GreetingResponse=GetServices("GlobalGreetings");
		if(GreetingResponse.getStatusCode()==200) {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testPassed("The Greeting id from Global database is failed because of no id passed");
		}
		else {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testFailed("The Greeting id from Global database is passed because of id passed");
		}}catch(Exception e){
			testFailed("An exception occured and the error message is "+e.getMessage());
		}
	}
	public void VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId(String GreetingID) {
		Response DeleteGreetinReponse=null;
		try{DeleteGreetinReponse=DeleteServices("GlobalGreetings",GreetingID);
		if(DeleteGreetinReponse.getStatusCode()==204) {
			testPassed("Greeting is deleted Successfully");
		}
		else {
			testFailed("THe Delete Greeting From DB is Failed");
		}}catch(Exception e){
			testFailed("An exception occured and the error message is "+e.getMessage());
		}
	}
	public void VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithWrongGreetingId(String GreetingID) {
		Response DeleteGreetinReponse=null;
		try{DeleteGreetinReponse=DeleteServices("GlobalGreetings",GreetingID);
		if(DeleteGreetinReponse.getStatusCode()==500) {
			testPassed("The Delete Greeting From DB is unsuccessfull because of wrong greeting id");
		}
		else {
			testFailed("THe Delete Greeting From DB is successfull becaus of correct greeting id");
		}}catch(Exception e){
			testFailed("An exception occured and the error message is "+e.getMessage());
		}
	}
	public void VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithNoGreetingId() {
		Response GreetingResponse=null;
	try {	GreetingResponse=DeleteServices("DeleteNoGreetinId");
		if(GreetingResponse.getStatusCode()==405) {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testPassed("The Greeting id from Global database is failed because of no id passed");
		}
		else {
			info("Getting Specific Greetings Response Body is :"+GreetingResponse.getBody().asString());
			testFailed("The Greeting id from Global database is passed because of id passed");
		}}catch(Exception e){
			testFailed("An exception occured and the error message is "+e.getMessage());
		}
	}
	public String VerifyUserAbleToSendGreetingToGlobalDBFromTheRollbackAndSentGreetingShouldnotSaveToDB(String Greeting,String GlobalGreetings, String Greetinglogs) {
		Response GreetingsRollbackResponse=null;
		Response GreetingsLogsResponse=null;
		
		JSONObject GreetingJsonObject = null;
		try {
			GreetingJsonObject=JsonReader.getJsonObject(Greeting);			
			GreetingJsonObject.put("message",GreetingJsonObject.get("message").toString()+ getRandomNumber());
			greetingMessage = GreetingJsonObject.get("message").toString();
			GreetingsRollbackResponse = PostServices(GlobalGreetings,GreetingJsonObject);
			info("Response from greeting is : "+GreetingsRollbackResponse.getBody().asString());
			if(GreetingsRollbackResponse.getStatusCode()==409){
				testPassed("Greeting is send to the client and conflit occured");
			}else {
				testFailed("Test failed Greeting is not send to client");
			}

			String SelectQuery = jsonObject.getAsString("SelectGlobalGreetings");
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(!(DatabaseUtility.selectQueryComparision(SelectQueryResult,greetingMessage))){
				
				testPassed("Database does not contain the message. The message is  : "+greetingMessage);
			}else {
				testFailed("DB contains contain the message and the message  is :"+greetingMessage);
			}
			GreetingsLogsResponse=GetServices(Greetinglogs);
			if(!(GreetingsLogsResponse.getBody().asString().contains(greetingMessage))) {
				
				testPassed("Logs does not contain the message is  : "+greetingMessage);
			}else {
				testFailed("Logs contain the message. is :"+greetingMessage);
			}
		}catch(Exception e) {
			testFailed ("An exception occured during request and message is : "+e.getMessage());
		}
		return greetingMessage;
	}

}