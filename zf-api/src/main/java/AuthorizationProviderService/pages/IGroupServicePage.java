package AuthorizationProviderService.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IGroupServicePage extends RestApiUtility {
	JSONObject taurijsonObject = JsonReader.getJsonObject(EnvironmentManager.getEnvironmentFile());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	JsonReader jsonReader=new JsonReader();
	public void VerifyClientGroup(String servicename) {
		try {
			Response getServicesResponse = GetServices(servicename);
			if(getServicesResponse.getStatusCode()==200) {
				testPassed("Client group details response is :: "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Client group details is not displayed. the response is ::"+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	
	public void VerifyClientSpecificGroup(String servicename) {
		Response getServicesResponse =null;
		String groupID=null;
		try {
			groupID=taurijsonObject.getAsString("GROUPID");
			getServicesResponse = GetServices(servicename,groupID);
			if(getServicesResponse.getStatusCode()==200) {
				testPassed("Client group details response is :: "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Client group details is not displayed. the response is ::"+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
	public void VerifyClientInvalidGroup(String servicename,String groupID) {
		Response getServicesResponse =null;
		try {
	
			getServicesResponse = GetServices(servicename,groupID);
			if(getServicesResponse.getStatusCode()==404 &&
					getServicesResponse.jsonPath().getJsonObject("error").equals("Not Found")) {
				testPassed("The response when invalid id request is :: "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("The response when invalid id request is ::"+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
	
	public void GetClientGroupIdExt(String servicename,String groupID) {
		Response getServicesResponse =null;
		try {
	
			getServicesResponse = GetServices(servicename,groupID);
			if(getServicesResponse.getStatusCode()==200 ) {
				testPassed("Group details response is :: "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Group details is not displayed. The response is ::"+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
	
	public void GetClientGroupRole(String servicename,String groupID) {
		Response getServicesResponse =null;
		try {
	
			getServicesResponse = GetServices(servicename,groupID);
			if(getServicesResponse.getStatusCode()==200 ) {
				testPassed("Group details response is :: "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Group details is not displayed. The response is ::"+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
}
