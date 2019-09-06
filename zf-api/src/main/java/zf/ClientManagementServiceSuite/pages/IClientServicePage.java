package zf.ClientManagementServiceSuite.pages;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IClientServicePage extends RestApiUtility {

	public void getClientDetailsWithExtendedInfo(String servicename, String clientId,String configureValue ) {
		Response getServicesResponse =null;
		try{
			getServicesResponse = GetServices(servicename,clientId,configureValue);

			if(getServicesResponse.getStatusCode()==200) {
				if(configureValue.equals("false")&& getServicesResponse.getBody().asString().contains("applications")) {
					testPassed("Client details response is : "+getServicesResponse.getBody().asString());
				}
				else { if(configureValue.equals("true")&&getServicesResponse.getBody().asString().contains("users") &&getServicesResponse.getBody().asString().contains("userGroups")) {
					testPassed("Client details response is : "+getServicesResponse.getBody().asString());
				}
				else {
					testFailed("Client details descriptor response is  : "+getServicesResponse.getBody().asString());
				}}}else {
					testFailed("Client details descriptor response is  : "+getServicesResponse.getBody().asString());
				}	
		}catch(Exception e) {

			testFailed("Exception occured while getting client details and the message is :"+e.getMessage());
		}
	}


	public void editClientDetails(String servicename,String clientID,String payload) {
		JSONObject ClientdetailsJson = null;
		Response putServicesResponse =null;
		try{
			ClientdetailsJson=JsonReader.getJsonObject(payload);
			ClientdetailsJson.put("name",ClientdetailsJson.get("name").toString() + getRandomNumber());
			putServicesResponse =  PutServices(servicename, ClientdetailsJson, clientID);
			if(putServicesResponse.getStatusCode()==204) {

				testPassed("Client data is updated");
			}
			else {
				testFailed("Client data is not updated");
			}}catch(Exception e) {
				testFailed("Exception occured while cediting client details and the message is :"+e.getMessage());
			}
	}
	public String CreateClient(String servicename,String payload) {
		JSONObject ClientdetailsJson = null;
		String clientId=null;
		Response postServicesResponse =null;
		try{
			ClientdetailsJson=JsonReader.getJsonObject(payload);
			ClientdetailsJson.put("name",ClientdetailsJson.get("name").toString() + getRandomNumber());
			postServicesResponse =  PostServices(servicename, ClientdetailsJson);
			if(postServicesResponse.getStatusCode()==200) {
				clientId=postServicesResponse.getBody().asString();
				testPassed("Client is created and the clientID is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Client data is not updated and response is :"+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while cediting client details and the message is :"+e.getMessage());
			}
		return clientId;
	}


	public void getClientDetails(String servicename, String createdClientID) {
		Response getServicesResponse =null;
		try{
			getServicesResponse = GetServices(servicename);

			if(getServicesResponse.getStatusCode()==200  && (getServicesResponse.jsonPath().getJsonObject("id").toString()).contains(createdClientID)) {

				testPassed("Client details response is : "+getServicesResponse.getBody().asString()+" and the response contain created client id : "+createdClientID);
			}
			else {
				testFailed("Client details descriptor response is  : "+getServicesResponse.getBody().asString()+" and the response does not contain created client id : "+createdClientID);
			}}catch(Exception e) {
				testFailed("Exception occured while getting client details and the message is :"+e.getMessage());
			}
	}
	public void getOneClientDetails(String servicename, String createdClientID) {
		Response getServicesResponse =null;
		try{
			getServicesResponse = GetServices(servicename,createdClientID);

			if(getServicesResponse.getStatusCode()==200  && (getServicesResponse.jsonPath().getJsonObject("id").toString()).contains(createdClientID)) {

				testPassed("Client details response is : "+getServicesResponse.getBody().asString()+" and the response contain created client id : "+createdClientID);
			}
			else {
				testFailed("Client details descriptor response is  : "+getServicesResponse.getBody().asString()+" and the response does not contain created client id : "+createdClientID);
			}}catch(Exception e) {
				testFailed("Exception occured while getting client details and the message is :"+e.getMessage());
			}
	}
}
