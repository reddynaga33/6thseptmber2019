package AuthorizationProviderService.pages;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class RemoveEnvironmentSpecificPropertiesPage extends RestApiUtility{

	public void VerifyHealthAPI(String servicename) {
		try {
			Response getServicesResponse = GetServices(servicename);
			if(getServicesResponse.getStatusCode()==200 && getServicesResponse.getBody().asString().equals("OK")) {
				testPassed("The response for get response is :: "+getServicesResponse.getBody().asString());
			}else {
				testFailed("The Response for get details is :: "+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is :: "+e.getMessage());
		}
	}


}
