package AuthorizationProviderService.pages;

import framework.RestApiUtility;
import io.restassured.response.Response;

public class IPrivilegeServicePage extends RestApiUtility{

	
	public void getListAllAvailabePrivilage(String servicename) {
		 Response getServicesResponse =null;
		try {
			getServicesResponse = GetServices(servicename);
			if(getServicesResponse.getStatusCode()==200) {
				testPassed("The response deatil is ::"+getServicesResponse.getBody().asString());
			}else
			{
				testFailed("The response details is ::"+getServicesResponse.getBody().asString());
			}
		 }catch(Exception e) {
			 testFailed("An exception occured and the error message is "+e.getMessage());
		 }
	}
}
