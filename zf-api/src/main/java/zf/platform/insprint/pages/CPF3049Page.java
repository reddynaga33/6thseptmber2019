package zf.platform.insprint.pages;


import java.util.HashMap;
import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class CPF3049Page extends RestApiUtility {
	JsonReader jsonReader=new JsonReader();
	DatabaseUtility databaseutility=new DatabaseUtility();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	List<String> SelectQueryResult = null;
	String greetingMessage =null;


	public void globalInfoClients() {
		Response globalInfoClientsResponse = GetServices("GlobalInfoClients");
		info("Response body is : "+globalInfoClientsResponse.getBody().asString());
		if(globalInfoClientsResponse.getStatusCode()==200) {
			testPassed("Client information is retrieved");
		}else {
			testFailed("Test failed Client information is not retrieved");
		}
	}

	public void ServiceRrespectsClientContext(String ServiceName, String ContextName) {

		JSONObject Client=JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());
		String ClientId=Client.getAsString("CLIENT2_ID");
		Response ClientContextResponse=GetServices(ServiceName,ContextName);
		if(ClientContextResponse.getBody().asString().contains(ClientId) && ClientContextResponse.getStatusCode()==200)
		{
			info("The ClientContext of Current Client Response is :"+ClientContextResponse.getBody().asString());
			testPassed("The Client Id from URL and Client Id from Reponse is Same");
		}
		else
		{
			info("The ClientContext of Current Client Response is :"+ClientContextResponse.getBody().asString());
			testFailed("The Client Id from URL and Client Id from Reponse is Same");
		}
	}
	public void PrometheusMetrics()
	{
		JSONObject matricsjsonObject2 = JsonReader.getJsonObject("PrometheusMetrics");
		String MetricsResponse = matricsjsonObject2.getAsString("Response");
		String MetricsResponse1 = matricsjsonObject2.getAsString("HealthCheck");
		String MetricsResponse2 = matricsjsonObject2.getAsString("Method");
		String MetricsResponse3 = matricsjsonObject2.getAsString("Get");
		String MetricsResponse4 = matricsjsonObject2.getAsString("Interval");
		String ValidationString=MetricsResponse+"\""+"/"+MetricsResponse1+"\""+MetricsResponse2+"\""+MetricsResponse3+"\"";
		String Validationinterval=MetricsResponse+"\""+"/"+MetricsResponse1+"\""+MetricsResponse2+"\""+MetricsResponse3+"\""+MetricsResponse4;
		//		String b="jersey_request_count{uri=\"/global-info/clients\",method=\"GET\","
		Response PrometheusMetricsResponse=GetServices("PrometheusMetrics");
		if(PrometheusMetricsResponse.getStatusCode()==200 && PrometheusMetricsResponse.getBody().asString().contains(ValidationString))
		{
			info("Expected Response should be something like :"+MetricsResponse+" and it is matched with the Actual Response");
			testPassed("The PrometheusMetrics Response is :"+PrometheusMetricsResponse.getBody().asString());
		}
		else
		{
			info("Expected Response should be something like :"+MetricsResponse+" and it is not matched with the Actual Response");
			testFailed("The PrometheusMetrics Response is :"+PrometheusMetricsResponse.getBody().asString());
		}
		//TODO Steps 2 & 3 need to check with nikhil for the changing the config map item and then have to verify the metrics.
	}
	public void HealthCheck() {

		for(int i=0;i<3;i++) {
			Response HealthCheck = GetServices("HealthCheck");
			if(HealthCheck.getBody().asString().contains("OK")) {
				testPassed("Health Check response is :"+HealthCheck.getBody().asString());
			}
			else
			{
				testFailed("Health Check response is :"+HealthCheck.getBody().asString());
			}
		}
	}

	public Response sendClientGreetings(String jsonObject, String clientGreeting) {
		JSONObject greetingJsonObject = null;
		Response Client1GreetingResponse =null;
		try {
			greetingJsonObject=JsonReader.getJsonObject(jsonObject);			
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

	public void getGreetingLogsPostitiveResponse(String Greetingslogs, Response ClientGreetingResponse) {
		try {
			String responseValue = getResponseValue(ClientGreetingResponse,"id");
			Response getGreetingResponse = GetServices(Greetingslogs,responseValue);
			if(getGreetingResponse.getStatusCode()==200) {
				testPassed("Client Greeting logs response is :"+getGreetingResponse.getBody().asString());
			}else {
				testFailed("Client Greeting logs response is not as expected. The response details is : "+getGreetingResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during Get request and message is : "+e.getMessage());
		}
	}

	public void getGreetingLogsNegativeResponse(String Greetingslogs, Response ClientGreetingResponse) {
		try {
			String responseValue = getResponseValue(ClientGreetingResponse,"id");
			Response getGreetingResponse = GetServices(Greetingslogs,responseValue);
			if(getGreetingResponse.getStatusCode()==404) {
				testPassed("Client Greeting logs response is :"+getGreetingResponse.getBody().asString());
			}else {
				testFailed("Client Greeting logs response is not as expected. The response details is : "+getGreetingResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during Get request and message is : "+e.getMessage());
		}
	}


	public Response proxyGreeting(String proxyGreeting, String proxyGreetingjson) {
		JSONObject greetingJsonObject = null;
		JSONObject tauriEnvironmentObject=null;
		Response proxyGreetingResponse=null;
		HashMap<String, String> responseForRequestContextBefore=new HashMap<String, String>();
		HashMap<String, String> responseForRequestContextAfter=new HashMap<String, String>();
		HashMap<String, String> responseForRequestClientContextBefore=new HashMap<String, String>();
		HashMap<String, String> responseForRequestClientContextAfter=new HashMap<String, String>();
		try {
			tauriEnvironmentObject = JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());
			greetingJsonObject=JsonReader.getJsonObject(proxyGreetingjson);			
			proxyGreetingResponse = PostServices(proxyGreeting,greetingJsonObject);

			if(proxyGreetingResponse.getStatusCode()==200) {
				info("Client Proxy Greeting  response is :"+proxyGreetingResponse.getBody().asString());
				responseForRequestContextBefore =getResponseObject(proxyGreetingResponse,"requestContextBefore");
				responseForRequestContextAfter = getResponseObject(proxyGreetingResponse,"requestContextAfter");
				responseForRequestClientContextBefore =getResponseObject(proxyGreetingResponse,"clientContextBefore");
				responseForRequestClientContextAfter = getResponseObject(proxyGreetingResponse,"clientContextAfter");
				String contextIdInContextBefore=responseForRequestContextBefore.get("contextId");
				String contextIdInContextAfter=responseForRequestContextAfter.get("contextId");
				String clientContextIdInContextBefore=responseForRequestClientContextBefore.get("contextId");
				String clientContextIdInContextAfter=responseForRequestClientContextAfter.get("contextId");
				if((responseForRequestContextBefore.get("clientId")).equals(tauriEnvironmentObject.getAsString("CLIENT2_ID"))&&
						(responseForRequestContextAfter.get("clientId")).equals(tauriEnvironmentObject.getAsString("CLIENT2_ID"))&&
						contextIdInContextBefore.equals(contextIdInContextAfter))
					if(clientContextIdInContextBefore.equals(clientContextIdInContextAfter)) {
						info("Request context before and after contains same client id. The client id is  :"+responseForRequestContextBefore.get("clientId"));
						testPassed("The context ID for RequestContextBefore is : "+contextIdInContextBefore+" and context ID for RequestContextAfter is : "+contextIdInContextAfter);
					}else{
						testFailed("Request context before and after contains are not same client id. The expected client id is  : "+tauriEnvironmentObject.getAsString("CLIENTID2"));
					}
			}else {
				testFailed("Client Proxy Greeting  response is :"+proxyGreetingResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during post request and message is : "+e.getMessage());
		}
		return proxyGreetingResponse;

	}

	public void getGreetingLogForClient(Response proxyGreetingResponse) {
		try {

			Response getGreetingResponse = GetServices("getGreetingLogForClient");
			if(getGreetingResponse.getStatusCode()==200) {
				info("Client Greeting logs response is :"+getGreetingResponse.getBody().asString());
				HashMap<String, String> greetResponseDetails = getResponseObject(proxyGreetingResponse,"greetResponse");
				sleep(5000);
				String asString = getGreetingResponse.getBody().asString();
				if(asString.contains(greetResponseDetails.get("id"))) {
					testPassed("Client Greeting logs contains greeting reposse id and the ID is :"+greetResponseDetails.get("id"));
				}else {
					testFailed("Client Greeting logs does not contains greeting reposse id and the ID is : "+greetResponseDetails.get("id"));
				}
			}else {
				testFailed("Client Greeting logs response is not as expected. The response details is : "+getGreetingResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during Get request and message is : "+e.getMessage());
		}
	}
	public HashMap<String, String> getResponseObject(Response responseObject2, String value) {
		HashMap<String, String> responseObject=new HashMap<String, String>();

		try{
			responseObject = responseObject2.jsonPath().getJsonObject(value);


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return responseObject;
	}

	public void client1DBValidation(Response clientGreetingsResponse) {
		try {

			String SelectQuery = jsonObject.getAsString("SelectGreetingDetails");
			SelectQuery = SelectQuery.replaceFirst("tempValue", greetingMessage);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
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
	public void client2DBValidation(Response clientGreetingsResponse) {
		try {

			String SelectQuery = jsonObject.getAsString("SelectGreetingDetails");

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
}