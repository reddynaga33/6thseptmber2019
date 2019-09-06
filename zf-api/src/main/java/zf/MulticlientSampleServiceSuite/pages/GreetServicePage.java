package zf.MulticlientSampleServiceSuite.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class GreetServicePage extends RestApiUtility{

	
	JsonReader jsonReader=new JsonReader();
	DatabaseUtility databaseutility=new DatabaseUtility();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	List<String> SelectQueryResult = null;
	String greetingMessage =null;

	public void VerifyuserAbleToGetGlobalInfoClients(String servicename) {
		Response globalInfoClientsResponse = GetServices(servicename);
		info("Response body is : "+globalInfoClientsResponse.getBody().asString());
		if(globalInfoClientsResponse.getStatusCode()==200) {
			testPassed("Client information is retrieved");
		}else {
			testFailed("Test failed Client information is not retrieved");
		}
	}

	public Response VerifyUserAbleToSendGreetRequestWithCorrectClientId(String payload, String clientGreeting) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
			greetingJsonObject.put("message",greetingJsonObject.get("message").toString()+ getRandomNumber());
			greetingMessage = greetingJsonObject.get("message").toString();
			String clientIdDetails = greetingJsonObject.get("clientid").toString();
			Client1GreetingResponse = PostServices(clientGreeting,greetingJsonObject,clientIdDetails);
			info("Response from greeting is : "+Client1GreetingResponse.getBody().asString());
			if(Client1GreetingResponse.getStatusCode()==200) {
				testPassed("Greeting is send to client");
			}else {
				testFailed("Test failed Greeting is not send to client");
			}
		}catch(Exception e) {
			testFailed ("An exception occured during post request and message is : "+e.getMessage());
		}
		return Client1GreetingResponse;
	}
	
	public Response VerifyUserAbleToSendGreetRequestWithWrongClientId(String payload, String clientGreeting) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
			greetingJsonObject.put("message",greetingJsonObject.get("message").toString()+ getRandomNumber());
			greetingMessage = greetingJsonObject.get("message").toString();
			String clientIdDetails = greetingJsonObject.get("clientid").toString();
			Client1GreetingResponse = PostServices(clientGreeting,greetingJsonObject,clientIdDetails);
			info("Response from greeting is : "+Client1GreetingResponse.getBody().asString());
			if(Client1GreetingResponse.getStatusCode()==404 &&
					Client1GreetingResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
				testPassed("Greeting is not send to wrong client and Response is  : "+Client1GreetingResponse.getBody().asString());
			}else {
				testFailed("The response is not as expected and the response is :"+Client1GreetingResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during post request and message is : "+e.getMessage());
		}
		return Client1GreetingResponse;
	}

	
	public Response VerifyUserAbleToSendGreetRequestToClientIdAndGlobalDB(String payload, String clientGreeting) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
			greetingJsonObject.put("message",greetingJsonObject.get("message").toString()+ getRandomNumber());
			greetingMessage = greetingJsonObject.get("message").toString();
			Client1GreetingResponse = PostServices(clientGreeting,greetingJsonObject);
			info("Response from greeting is : "+Client1GreetingResponse.getBody().asString());
			if(Client1GreetingResponse.getStatusCode()==200) {
				testPassed("Greeting is send to client");
			}else {
				testFailed("Test failed Greeting is not send to client");
			}
		}catch(Exception e) {
			testFailed ("An exception occured during post request and message is : "+e.getMessage());
		}
		return Client1GreetingResponse;
	}

	public void clientDBValidation(Response clientGreetingsResponse, String queryDetails) {
		try {

			String SelectQuery = jsonObject.getAsString(queryDetails);
			SelectQuery = SelectQuery.replaceFirst("tempValue", greetingMessage);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASEClient65")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,greetingMessage)){
				testPassed("Database contain the message. The message is  : "+greetingMessage);
			}else {
				testFailed("DB contains does not contain the message. "+greetingMessage);
			}
		}catch(Exception e) {
			testFailed ("An exception occured during database validation. the message is  : "+e.getMessage());
		} 
	}
	
	public void globalClientDBValidation(Response clientGreetingsResponse, String GlobalGreetingDetails) {
		try {

			String SelectQuery = jsonObject.getAsString(GlobalGreetingDetails);
			SelectQuery = SelectQuery.replaceFirst("tempValue", greetingMessage);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,greetingMessage)){
				testPassed("Database contain the message. The message is  : "+greetingMessage);
			}else {
				testFailed("DB contains does not contain the message. "+greetingMessage);
			}
		}catch(Exception e) {
			testFailed ("An exception occured during database validation. the message is  : "+e.getMessage());
		} 
	}
	
	public Response VerifyUserAbleToSendGreetRequestWithNoClient(String clientGreeting,String payload) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
			greetingJsonObject.put("message",greetingJsonObject.get("message").toString()+getRandomNumber());
			greetingMessage = greetingJsonObject.get("message").toString();
			
			Client1GreetingResponse = PostServices(clientGreeting,greetingJsonObject);
			info("Response from greeting is : "+Client1GreetingResponse.getBody().asString());
			if(Client1GreetingResponse.getStatusCode()==404) {
				testPassed("Greeting not send to client");
			}else {
				testFailed("Test failed Greeting is not send to client");
			}
		}catch(Exception e) {
			testFailed ("An exception occured during post request and message is : "+e.getMessage());
		}
		return Client1GreetingResponse;
	}
	
	public void GetGreetLogsWithCorrectClient(String servicename, String payload) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
		String clientIdDetails = greetingJsonObject.get("clientid").toString();
		Client1GreetingResponse = GetServices(servicename,clientIdDetails);
		if(Client1GreetingResponse.getStatusCode()==200) {
			testPassed("The response for the Get greeting is : "+Client1GreetingResponse.getBody().asString());
		}else {
			testFailed("The get response got failed and the response is : "+Client1GreetingResponse.getBody().asString());
		}
	}catch(Exception e) {
		testFailed ("An exception occured during get request and message is : "+e.getMessage());
	}
	}
	
	public void GetGreetLogsWithWrongClient(String servicename, String payload) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
		String clientIdDetails = greetingJsonObject.get("clientid").toString();
		Client1GreetingResponse = GetServices(servicename,clientIdDetails);
		if(Client1GreetingResponse.getStatusCode()==404 && 
				Client1GreetingResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("The response for the Get greeting is : "+Client1GreetingResponse.getBody().asString());
		}else {
			testFailed("The get response got failed and the response is : "+Client1GreetingResponse.getBody().asString());
		}
	}catch(Exception e) {
		testFailed ("An exception occured during get request and message is : "+e.getMessage());
	}
	}
	
	public Response GetGreetLogsWithNoClient(String servicename) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
						Client1GreetingResponse = GetServices(servicename);
			info("Response from greeting is : "+Client1GreetingResponse.getBody().asString());
			if(Client1GreetingResponse.getStatusCode()==404) {
				testPassed("The response for the Get greeting is : "+Client1GreetingResponse.getBody().asString());
			}else {
				testFailed("The get response got failed and the response is : "+Client1GreetingResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during get request and message is : "+e.getMessage());
		}
		return Client1GreetingResponse;
	}
	
	public void SendGreetingToclientFromRollback(String servicename, String payload) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
		String clientIdDetails = greetingJsonObject.get("clientid").toString();
		greetingJsonObject.put("message",greetingJsonObject.get("message").toString()+getRandomNumber());
		greetingMessage = greetingJsonObject.get("message").toString();
		
		Client1GreetingResponse = PostServices(servicename,greetingJsonObject,clientIdDetails);
		if(Client1GreetingResponse.getStatusCode()==500) {
			testPassed("The response for the Get greeting is : "+Client1GreetingResponse.getBody().asString());
		}else {
			testFailed("The get response got failed and the response is : "+Client1GreetingResponse.getBody().asString());
		}
	}catch(Exception e) {
		testFailed ("An exception occured during get request and message is : "+e.getMessage());
	}
	}
	
	public void SendGreetingToWrongclientFromRollback(String servicename, String payload) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(payload);	
		String clientIdDetails = greetingJsonObject.get("clientid").toString();
		greetingJsonObject.put("message",greetingJsonObject.get("message").toString()+getRandomNumber());
		greetingMessage = greetingJsonObject.get("message").toString();
		
		Client1GreetingResponse = PostServices(servicename,greetingJsonObject,clientIdDetails);
		if(Client1GreetingResponse.getStatusCode()==404 &&
				Client1GreetingResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("The response for the Get greeting is : "+Client1GreetingResponse.getBody().asString());
		}else {
			testFailed("The get response got failed and the response is : "+Client1GreetingResponse.getBody().asString());
		}
	}catch(Exception e) {
		testFailed ("An exception occured during get request and message is : "+e.getMessage());
	}
	}
}
