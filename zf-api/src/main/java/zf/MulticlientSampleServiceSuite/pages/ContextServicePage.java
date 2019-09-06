package zf.MulticlientSampleServiceSuite.pages;

import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class ContextServicePage extends RestApiUtility {
	JsonReader jsonObj = new JsonReader();

	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());

	public void getClientDetailsWithoutClientID(String servicename)
	{try {
		Response ClientDetailsWithoutClientIDResponse = GetServices(servicename);
		if(ClientDetailsWithoutClientIDResponse.getStatusCode()==404) {
			testPassed("Get Property detail is : "+ClientDetailsWithoutClientIDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+ClientDetailsWithoutClientIDResponse.getBody().asString());
			testFailed("Get Property detail is : "+ClientDetailsWithoutClientIDResponse);
		}}catch(Exception e) {
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}

	

	public void getCurrentClientIDWithWrongClientID(String servicename)
	{
		Response CurrentClientIDWithWrongIDResponse = GetServices(servicename);
		if(CurrentClientIDWithWrongIDResponse.getStatusCode()==500) {
			testPassed("Get Property detail is : "+CurrentClientIDWithWrongIDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+CurrentClientIDWithWrongIDResponse.getBody().asString());
			testFailed("Get Property detail is : "+CurrentClientIDWithWrongIDResponse);
		}
	}

	public void getCurrentClientIDWithNoClientID(String servicename)
	{
		Response CurrentClientIDWithNoClientIDResponse = GetServices(servicename);
		if(CurrentClientIDWithNoClientIDResponse.getStatusCode()==404) {
			testPassed("Get Property detail is : "+CurrentClientIDWithNoClientIDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+CurrentClientIDWithNoClientIDResponse.getBody().asString());
			testFailed("Get Property detail is : "+CurrentClientIDWithNoClientIDResponse);
		}
	}


	public void getCurrentContextIDWithNoClientID(String servicename)
	{
		Response CurrentContextIDWithNoClientIDResponse = GetServices(servicename);
		if(CurrentContextIDWithNoClientIDResponse.getStatusCode()==404) {
			testPassed("Get Property detail is : "+CurrentContextIDWithNoClientIDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+CurrentContextIDWithNoClientIDResponse.getBody().asString());
			testFailed("Get Property detail is : "+CurrentContextIDWithNoClientIDResponse);
		}
	}

	public void verifyGetCurrentClientWithWrongCliendId(String servicename) {
		Response getCurrentWrongClientIDResponse =null;
		try {
			getCurrentWrongClientIDResponse = GetServices(servicename);
		
		if(getCurrentWrongClientIDResponse.getStatusCode()==404 &&
				getCurrentWrongClientIDResponse.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("Response detail is : "+getCurrentWrongClientIDResponse.getBody().asString());
		}
		else {
			testFailed("Get Property response detail is : "+getCurrentWrongClientIDResponse.getBody().asString());
		}}catch(Exception e) {
			testFailed("An exception occured during get request and error message is : "+e.getMessage());
	}
	}
	
	//v C61577 Verify User is able to get the Current client Context with No client ID(Get request for GET /clients/{client-id}/current-client-context)
	public void VerifyCurrentClientContextWithNoClientId(String servicename)
	{

		Response getCurrentClientContextNoClientIdDResponse = GetServices("CurrentClientContextWithNoClientID");
		if(getCurrentClientContextNoClientIdDResponse.getStatusCode()==404) {
			testPassed("Response detail is : "+getCurrentClientContextNoClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextNoClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextNoClientIdDResponse);

		}
	}
	//v C61571 Verify user is able to get the current context with  client id (Get request for GET /clients/{client-id}/current-contexts)
	public void getCurrentClientIdContextWithCorrectClientID(String servicename)
	{Response getCurrentClientContextClientIdDResponse=null;
	try {
		;
		getCurrentClientContextClientIdDResponse = GetServices(servicename);
		if(getCurrentClientContextClientIdDResponse.getStatusCode()==200 && 
				getCurrentClientContextClientIdDResponse.jsonPath().getJsonObject("clientId").equals(jsonObject.getAsString("CLIENT1_ID"))) {
			testPassed("Response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextClientIdDResponse);

		}}catch(Exception e) {
			testFailed("An exception occured  and the error message is : "+e.getMessage());
		}
	}
	//V C61693 Verify user is able to get the current request context with no client id (Get request for GET /clients/{client-id}/current-request-context)	
	public void GetCurrentRequestContextNoclientId(String servicename)
	{

		Response getCurrentClientContextNoClientIdDResponse = GetServices("CurrentRequestContextNoclientId");
		if(getCurrentClientContextNoClientIdDResponse.getStatusCode()==404) {
			testPassed("Response detail is : "+getCurrentClientContextNoClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextNoClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextNoClientIdDResponse);

		}
	}		

	public void getCurrentClientIdDetailsWithCorrectClientID(String servicename)
	{
		Response getCurrentClientContextClientIdDResponse=null;
		try{getCurrentClientContextClientIdDResponse = GetServices(servicename);
		if(getCurrentClientContextClientIdDResponse.getStatusCode()==200) {
			testPassed("Response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextClientIdDResponse);

		}}catch(Exception e){
			testFailed("Anexception occured dring get request"+e.getMessage());
		}
	}
	public void getVerifyCurrentClientIdWithCorrectClientID(String servicename)
	{
		Response getCurrentClientContextClientIdDResponse=null;
		try{getCurrentClientContextClientIdDResponse = GetServices(servicename);
		if(getCurrentClientContextClientIdDResponse.getStatusCode()==200 && jsonObject.getAsString("CLIENT1_ID").contains(getCurrentClientContextClientIdDResponse.getBody().asString()) ) {
			testPassed("Response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextClientIdDResponse);

		}}catch(Exception e){
			testFailed("Anexception occured dring get request"+e.getMessage());
		}
	}
	
	public void getCurrentContextDetails(String servicename)
	{
		Response getCurrentClientContextClientIdDResponse=null;
		try{getCurrentClientContextClientIdDResponse = GetServices(servicename);
		if(getCurrentClientContextClientIdDResponse.getStatusCode()==200 && getCurrentClientContextClientIdDResponse.getBody().asString().contains(jsonObject.getAsString("CLIENT1_ID"))) {
			testPassed("Response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextClientIdDResponse);

		}}catch(Exception e){
			testFailed("Anexception occured dring get request"+e.getMessage());
		}
	}
	
	

	public void getCurrentRequestContextDetails(String servicename)
	{
		Response getCurrentClientContextClientIdDResponse=null;
		try{getCurrentClientContextClientIdDResponse = GetServices(servicename);
		if(getCurrentClientContextClientIdDResponse.getStatusCode()==200 && getCurrentClientContextClientIdDResponse.getBody().asString().contains(jsonObject.getAsString("CLIENT1_ID"))) {
			testPassed("Response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getCurrentClientContextClientIdDResponse.getBody().asString());
			testFailed("Get Property detail is : "+getCurrentClientContextClientIdDResponse);

		}}catch(Exception e){
			testFailed("Anexception occured dring get request"+e.getMessage());
		}
	}

}
