package zf.FleetManagementService.test.pages;

import java.util.List;

import com.google.gson.JsonObject;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.TestLogger;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class MiscPage extends RestApiUtility{
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;

	public Response VerifyChangesAreMadeInGlobalDBAfterAssigningFleetManagementServiceToAClient() {
		Response FleetAssignClientResposne=null;
		FleetAssignClientResposne=PutServices("FleetService");
		info("The Fleet serice response body is :"+FleetAssignClientResposne.getBody().asString());
		return FleetAssignClientResposne;
	}
	public void ValidateResponseCode202(Response response) {
		if(response.getStatusCode()==202) {
			testPassed("The API response code is Successfully validated");
		}else {
			testFailed("The API response code is not successfully validated");
		}
	}
	public String GetSagaResponse(Response response ) {

		String responseValue=response.jsonPath().getJsonObject("status");
		return responseValue;
	}

	public Response SagaEntityRequest(String Saga) {
		Response SagaEntityRequestResponse=GetServices("sagastatussuccess",Saga);
		info("The Saga Response Status is:"+SagaEntityRequestResponse.getBody().asString());
		return SagaEntityRequestResponse;
	}

	public void ValidatingClientServiceInDB(Response ClientService) {
		try {
			String ClientId=jsonObject.getAsString("CLIENTNEW");
			String SelectQuery = jsonObject.getAsString("SelectAssignFleetToClient");
			SelectQuery = SelectQuery.replaceFirst("tempValue", ClientId);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,ClientId)){
				testPassed("Value from DB : "+SelectQueryResult.toString());
			}else {
				testFailed("Value from DB : "+SelectQueryResult.toString());
			}

		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
}

