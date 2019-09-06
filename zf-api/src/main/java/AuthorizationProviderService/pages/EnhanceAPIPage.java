package AuthorizationProviderService.pages;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class EnhanceAPIPage extends RestApiUtility {
	
	public String createANewRole(String servicename, String payload) {
		JSONObject CreateNewRoleJson = null;
		Response CreateRoleResponse=null;
		String codeName=null;
		try {	CreateNewRoleJson=JsonReader.getJsonObject(payload);
		codeName=CreateNewRoleJson.get("code").toString()+getRandomNumber();
		CreateNewRoleJson.put("code",codeName);
		CreateRoleResponse=PostServices(servicename,CreateNewRoleJson);
		if( CreateRoleResponse!=null && CreateRoleResponse.getStatusCode()==200) {

			testPassed("The created Role Details is : "+CreateRoleResponse.getBody().asString());
		}
		else {
			testFailed("The created role response status code is : "+CreateRoleResponse.getStatusCode());
		}}catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}
		return codeName;
	}
	
	public void getUsersWithRoleCodeUsingNewAPI(String ServiceName, String serviceId) {
		Response GetUserResponse=null;
		String RoleId = null;
		try {	
			GetUserResponse=GetServices("GetUsersWithRoleCodeUsingNewAPI",serviceId);
			if( GetUserResponse!=null && GetUserResponse.getStatusCode()==200) {
				RoleId = GetUserResponse.getBody().asString();	
				testPassed("The created Role details is : "+RoleId);
			}
			else {
				testFailed("The created role response status code is : "+GetUserResponse.getStatusCode()+" and the details is:"+GetUserResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}
	public Response createNewRoleWithResponse(String servicename, String payload) {
		JSONObject CreateNewRoleJson = null;
		Response CreateRoleResponse=null;
		String roleID=null;
		try {	CreateNewRoleJson=JsonReader.getJsonObject(payload);
		roleID=CreateNewRoleJson.get("code").toString()+getRandomNumber();
		CreateNewRoleJson.put("code",roleID);
		CreateRoleResponse=PostServices(servicename,CreateNewRoleJson);
		if( CreateRoleResponse!=null && CreateRoleResponse.getStatusCode()==200) {

			testPassed("The created Role Details is : "+CreateRoleResponse.getBody().asString());
		}
		else {
			testFailed("The created role response status code is : "+CreateRoleResponse.getStatusCode());
		}}catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}
		return CreateRoleResponse;
	}

}
