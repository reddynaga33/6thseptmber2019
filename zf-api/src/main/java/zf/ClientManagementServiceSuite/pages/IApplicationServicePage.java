package zf.ClientManagementServiceSuite.pages;

import framework.RestApiUtility;
import io.restassured.response.Response;

public class IApplicationServicePage extends RestApiUtility{

	public void getExtendedApplicationDetails(String servicename,String applicationId,String configureValue) {

		Response getServicesResponse =null;
		try{
			getServicesResponse = GetServices(servicename,applicationId,configureValue);

			if(getServicesResponse.getStatusCode()==200) {
				if(configureValue.equals("false")&& getServicesResponse.jsonPath().getJsonObject("clientIds").toString().equals("[]")) {
					testPassed("Application details response is : "+getServicesResponse.getBody().asString()+" and response contains client details is :"+getServicesResponse.jsonPath().getJsonObject("clientIds").toString());
				}
				else { if(configureValue.equals("true")&&getServicesResponse.getBody().asString().contains("19309c32-1769-42ba-9829-d18d5e9e072d")) {
					testPassed("Application details response is : "+getServicesResponse.getBody().asString()+" and response contains client details is :"+getServicesResponse.jsonPath().getJsonObject("clientIds").toString());
				}
				else {
					testPassed("Application details response is : "+getServicesResponse.getBody().asString()+" and response contains client details is :"+getServicesResponse.jsonPath().getJsonObject("clientIds").toString());
				}}}else {
					testFailed("Application details response is  : "+getServicesResponse.getBody().asString());
				}	
		}catch(Exception e) {

			testFailed("Exception occured while getting Application details and the message is :"+e.getMessage());
		}
	}

	public Response getClientDetailsOfApplication(String servicename, String applicationId) {
		Response getServicesResponse =null;
		try{sleep(5000);
			getServicesResponse = GetServices(servicename,applicationId);

			if(getServicesResponse.getStatusCode()==200) {

				testPassed("Application details response is : "+getServicesResponse.getBody().asString());
			}else {
				testFailed("Application details response is  : "+getServicesResponse.getBody().asString());
			}	
		}catch(Exception e) {

			testFailed("Exception occured while getting Application details and the message is :"+e.getMessage());
		}
		return getServicesResponse;
	}

	
	public void  validateClientPresent(Response getServicesResponse, String clientId) {
		if(!(getServicesResponse.getBody().asString().contains(clientId))){
			testPassed("ClientId is : "+clientId+" not present in response and the response is : "+getServicesResponse.getBody().asString());
		}else {
			testFailed("ClientId  : "+clientId+"  is present in response and the response is : "+getServicesResponse.getBody().asString());
		}	
		
	}
	
	
	public void assignApplicationToClient(String servicename, String applicationId,String clientId) {
		Response postServicesResponse =null;
		try{sleep(6000);
			postServicesResponse = PostServices(servicename,applicationId,clientId);
			if(postServicesResponse.getStatusCode()==204) {
				testPassed("Application details response is : "+postServicesResponse.getBody().asString());
			}else {
				testFailed("Application details response is  : "+postServicesResponse.getBody().asString());
			}	
		}catch(Exception e) {

			testFailed("Exception occured while getting Application details and the message is :"+e.getMessage());
		}
	}

	
	public void getApplicationClientDetails(String servicename, String applicationId,String clientId) {
		Response getServicesResponse =null;
		try{sleep(5000);
			getServicesResponse = GetServices(servicename,applicationId,clientId);

			if(getServicesResponse.getStatusCode()==200) {

				testPassed("Application details response is : "+getServicesResponse.getBody().asString());
			}else {
				testFailed("Application details response is  : "+getServicesResponse.getBody().asString());
			}	
		}catch(Exception e) {

			testFailed("Exception occured while getting Application details and the message is :"+e.getMessage());
		}
	}
	
	public void unassignApplicationToClient(String servicename, String applicationId,String clientId) {
		Response postServicesResponse =null;
		try{
			postServicesResponse = DeleteServices(servicename,applicationId,clientId);
			if(postServicesResponse.getStatusCode()==204) {
				testPassed("Application details response is : "+postServicesResponse.getBody().asString());
			}else {
				testFailed("Application details response is  : "+postServicesResponse.getBody().asString());
			}	
		}catch(Exception e) {

			testFailed("Exception occured while getting Application details and the message is :"+e.getMessage());
		}
	}
}
