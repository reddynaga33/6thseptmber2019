package AuthorizationProviderService.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.api.pages.AssetApiPage;

public class IRoleServicePage extends RestApiUtility{
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	AssetApiPage assetapipage=new AssetApiPage();
	
	public void GetClientRoles(String servicename) {
		Response getServicesResponse =null;
		try {
	
			getServicesResponse = GetServices(servicename);
			if(getServicesResponse.getStatusCode()==200 ) {
				testPassed("Role details response is :: "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Role details is not displayed. The response is ::"+getServicesResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured while getting role details and the error message is : "+e.getMessage());
		}
		
	}

	public void GetClientRoleIdDetails(String servicename,String roleId) {
	
		Response getroleDetailresponse=null;
		try {
			
			getroleDetailresponse = GetServices(servicename,roleId);
			if(getroleDetailresponse.getStatusCode()==200 ) {
				testPassed("RoleID details response is :: "+getroleDetailresponse.getBody().asString());
			}
			else {
				testFailed("RoleID  details is not displayed. The response is ::"+getroleDetailresponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured while getting roleid details and the error message is : "+e.getMessage());
		}
	}
	
	public void GetClientRoleDetails(String servicename,String serviceID) {
		Response getServices = GetServices(servicename, serviceID);
		if(getServices.getStatusCode()==200) {
			testPassed("The GET API response is: "+getServices.getBody().asString());
		}else {
			testFailed("The Response details is :"+getServices.getBody().asString());
		}
	}
	
	public void validateRoleIDINDB(String roleID,String queryDetails) {
		
		try {
			String SelectQuery = jsonObject.getAsString(queryDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", roleID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
			
			info("Values from Database is : "+SelectQueryResult.toString()+" and the role details is :"+roleID);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,roleID)){

				testPassed("Database contains the Asset Details : "+roleID );
			}else {
				testFailed("Database does not contains the Asset Details : "+roleID);
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
		
	}

}
