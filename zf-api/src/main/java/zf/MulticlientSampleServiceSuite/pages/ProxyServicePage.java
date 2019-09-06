package zf.MulticlientSampleServiceSuite.pages;


import java.util.HashMap;
import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class ProxyServicePage extends RestApiUtility {
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());
	JSONObject sqljsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;

	public void VerifyUserIsAbleToGetTheCurrentContextUsingCorrectClientId() {
		String proxyClientID = jsonObject.getAsString("CLIENT1_ID");
		HashMap<String, String> responseForRequestContextBefore=new HashMap<String, String>();
		HashMap<String, String> responseForRequestContextAfter=new HashMap<String, String>();
		Response getProxyResponse = GetServices("proxyCurrentContext",proxyClientID);
		if(getProxyResponse.getStatusCode()==200) {
			info("Proxy Current Client response is :"+getProxyResponse.getBody().asString());
			responseForRequestContextBefore =getProxyResponse.jsonPath().getJsonObject("before");
			responseForRequestContextAfter =getProxyResponse.jsonPath().getJsonObject("after");
			if(responseForRequestContextBefore.equals(responseForRequestContextAfter)) {
				testPassed("The Client id and Client context details are same after the request");
			}
		}else {
			testFailed("The Client id and Client context details are changed after the request");
		}
	}
	
	public void VerifyUserIsAbleToGetTheCurrentContextUsingWrongClientId() {
		String getWrongClientID = jsonObject.getAsString("CLIENT1_ID")+getRandomNumber();
		Response getResponseForWrongClientID = GetServices("proxyCurrentContext",getWrongClientID);
		if(getResponseForWrongClientID.getStatusCode()==404 &&
				getResponseForWrongClientID.jsonPath().getJsonObject("error").equals("Client not found")	) {
			testPassed("Proxy Current Client response is :"+getResponseForWrongClientID.getBody().asString());
		}else {
			testFailed("The Passed "+getResponseForWrongClientID.jsonPath().getJsonObject("message"));
		}
	}
	public void VerifyUserIsAbleToGetTheCurrentContextUsingNoClientId(String servicename) {
		Response getResponse =null;
			try {getResponse=	GetServices(servicename);
		if(getResponse.getStatusCode()==404 &&
				getResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("Get request Current Client response is :"+getResponse.getBody().asString());
		}else {
			testFailed("Get request response without CLientiDCurrent Client response is "+getResponse.getBody().asString());
		}}catch(Exception e){
			testFailed("An exception occured and error message is : "+e.getMessage());
		}
	}

	public void VerifyUserIsAbleToSendGreetingToTheClientWithTheWrongClientId(String Greeting,String GlobalGreetings) {
		String proxyClientID = jsonObject.getAsString("CLIENT1_ID")+getRandomNumber();
		Response GlobalGreetingsResponse=null;
		String GreetingID = null;
		JSONObject GreetingJsonObject = null;
		try {
			GreetingJsonObject=JsonReader.getJsonObject(Greeting);			
			GreetingJsonObject.put("message",GreetingJsonObject.get("message").toString()+ getRandomNumber());
			GreetingID = GreetingJsonObject.get("message").toString();
			GlobalGreetingsResponse = PostServices(GlobalGreetings,GreetingJsonObject,proxyClientID);
			if(GlobalGreetingsResponse.getStatusCode()==404 &&
					GlobalGreetingsResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
				testPassed("The Response Body is:"+GlobalGreetingsResponse.getBody().asString());
			}else {
				testFailed("The Response Body is:"+GlobalGreetingsResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured:"+e.getMessage());
		}
	}
	
	public void VerifyUserIsAbleToSendGreetingToClientWithCorrectClientId(String Greeting,String GlobalGreetings,String SchemaDetails) {
		String proxyClientID = jsonObject.getAsString("CLIENT1_ID");
		Response GlobalGreetingsResponse=null;
		String GreetingID = null;
		JSONObject GreetingJsonObject = null;
		try {
			GreetingJsonObject=JsonReader.getJsonObject(Greeting);			
			GreetingJsonObject.put("message",GreetingJsonObject.get("message").toString()+ getRandomNumber());
			GreetingID = GreetingJsonObject.get("message").toString();
			GlobalGreetingsResponse = PostServices(GlobalGreetings,GreetingJsonObject,proxyClientID);
			if(GlobalGreetingsResponse.getStatusCode()==200) {
				info("The Response message is :"+GlobalGreetingsResponse.jsonPath().getJsonObject("message"));
				testPassed("The Response Body is:"+GlobalGreetingsResponse.getBody().asString());
			}else {
				info("The Response message is :"+GlobalGreetingsResponse.jsonPath().getJsonObject("message"));
				testFailed("The Response Body is:"+GlobalGreetingsResponse.getBody().asString());
			}
			String SelectQuery = sqljsonObject.getAsString(SchemaDetails);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),sqljsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if((DatabaseUtility.selectQueryComparision(SelectQueryResult,GreetingID))){

				testPassed("Database does not contain the message. The message is  : "+GreetingID);
			}else {
				testFailed("DB contains contain the message and the message  is :"+GreetingID);
			}
		}catch(Exception e) {
			testFailed("An exception occured:"+e.getMessage());
		}
	}
	
	public void VerifyUserIsAbleToSendGreetingToTheClientWithTheNoClientId(String Greeting,String GlobalGreetings) {
		Response GlobalGreetingsResponse=null;
	
		JSONObject GreetingJsonObject = null;
		try {
			GreetingJsonObject=JsonReader.getJsonObject(Greeting);			
			GreetingJsonObject.put("message",GreetingJsonObject.get("message").toString()+ getRandomNumber());
			GlobalGreetingsResponse = PostServices(GlobalGreetings,GreetingJsonObject);
			if(GlobalGreetingsResponse.getStatusCode()==404 &&
					GlobalGreetingsResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
					testPassed("When Post request is send with no client ID then Response is:"+GlobalGreetingsResponse.getBody().asString());
			}else {
				testFailed("When Post request is send with no client ID then Response is::"+GlobalGreetingsResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured:"+e.getMessage());
		}
	}
}