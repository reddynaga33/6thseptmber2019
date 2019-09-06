package zf.api.pages;

import java.util.List;

import org.testng.Assert;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.ExtentReport;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PropulsionTypePage extends RestApiUtility {
	JsonReader jsonData=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;


	public boolean DeletePropulsionType(String createPropulsionTypeID) {
		boolean result = false;
		Response deletePropulsionTypeResponse = null;		
		String PropulsionTypeId = null;
		try {
			try {
				PropulsionTypeId = (String)JsonReader.getJsonObject(createPropulsionTypeID).get("id");
			}catch(Exception e) {
				PropulsionTypeId = createPropulsionTypeID;
			}

			info("Executing Delete Request against Propulsion Type id : "+PropulsionTypeId);

			deletePropulsionTypeResponse = DeleteServices("propulsionType",PropulsionTypeId);
			if( deletePropulsionTypeResponse!=null && deletePropulsionTypeResponse.getStatusCode()==204) {

				info("The deleteted PropulsionType id "+PropulsionTypeId+" response is "+deletePropulsionTypeResponse.getBody().asString());
				testPassed("The PropulsionType id "+PropulsionTypeId+" is deleted successfully");
				result = true;
			}
			else {
				info("The deleteted PropulsionType id "+PropulsionTypeId+" status code is "+deletePropulsionTypeResponse.getBody().jsonPath().getString("message"));
				testFailed("The user with PropulsionType id "+PropulsionTypeId+" is not deleted successfully");
				result = false;
			}

		} catch(Exception e) {
			testFailed("An Exception occured while deleting Propulsiontype and the message is : "+e.getMessage());
		}
		return result;
	}

	public Response getSpecificPropulsionTypeDetails(String PropulsionTypeDetails, String PropulsionTypeDetailsId) {

		Response PropulsionTypeDetailJson = null;			
		String PropulsionTypeDetailResponse = null;

		try {

			PropulsionTypeDetailJson = GetServices(PropulsionTypeDetails,PropulsionTypeDetailsId);
			PropulsionTypeDetailResponse = PropulsionTypeDetailJson.getBody().asString();
			if(PropulsionTypeDetailJson.getStatusCode()==200) {

				ExtentReport.testPassed("PropulsionType Response is : "+PropulsionTypeDetailResponse);
			}else {
				ExtentReport.testFailed("PropulsionType is not present in the response message is : "+PropulsionTypeDetailJson.getBody().jsonPath().getString("message"));
				PropulsionTypeDetailResponse = null;
			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Propulsiontype and the message is : "+e.getMessage());
		}
		return PropulsionTypeDetailJson;	
	}

	public Response getPropulsionTypeDetails(String PropulsionTypeDetails) {
		Response PropulsionTypeDetailsJson = null;			

		String PropulsionTypeDetailsResponse = null;

		try {

			PropulsionTypeDetailsJson = GetServices(PropulsionTypeDetails);
			PropulsionTypeDetailsResponse = PropulsionTypeDetailsJson.getBody().asString();
		
			if(PropulsionTypeDetailsJson.getStatusCode()==200) {
				ExtentReport.testPassed("PropulsionType Response : "+PropulsionTypeDetailsJson.getBody().asString());
			}else {
				testFailed("PropulsionType Responde code is : "+PropulsionTypeDetailsJson.getStatusCode() +" and the response message is : "+PropulsionTypeDetailsJson.getBody().jsonPath().getString("message"));
				PropulsionTypeDetailsResponse = null;
			}	
		}catch(Exception e) {
			testFailed("An execption has generated while working with getpropulsionType details and the message is : "+e.getMessage());
		}
		return PropulsionTypeDetailsJson;
	}

	public String createPropulsionType(String payLoad) {
		JSONObject CreatePropulsionTypeJson = null;
		String CreatePropulsionTypeID = null;
		Response CreatePropulsionTypeResponse = null;

		try {
			CreatePropulsionTypeJson = JsonReader.getJsonObject(payLoad);
			CreatePropulsionTypeJson.put("name",CreatePropulsionTypeJson.get("name").toString()+getRandomNumber() );

			ExtentReport.info("Create Propulsion Type with:"+CreatePropulsionTypeJson.toJSONString());
			CreatePropulsionTypeResponse = CreateServices("propulsion",CreatePropulsionTypeJson);	

			if( CreatePropulsionTypeResponse!=null && CreatePropulsionTypeResponse.getStatusCode()==200) {
				CreatePropulsionTypeID = CreatePropulsionTypeResponse.getBody().asString();	
			testPassed("Create Propulsion Type id is : "+CreatePropulsionTypeID);
			}
			else {
				testFailed("The Create Propulsion Type response status code is : "+CreatePropulsionTypeResponse.getStatusCode());
			}
		} catch(Exception e) {
			testFailed("An Exception occured while creating  Propulsiontype and the message is : "+e.getMessage());
		}		
		return CreatePropulsionTypeID;

	}
	public void validatePropulsionTypeIdInDB(String PropulsionTypeId) {
		try {
			
			String SelectQuery = jsonObject.getAsString("SelectPropulsionTypeID");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", PropulsionTypeId);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
		
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,PropulsionTypeId)){
				testPassed("Database contains the PropulsionType Id : "+PropulsionTypeId );
			}else {
				testFailed("Database does not contains the PropulsionType Id : "+PropulsionTypeId);
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
	}
	public void validateSpecificPropulsionTypeIdInDB(Response PropulsionTypeDetails) {
		try {
			String PropulsionTypeName = PropulsionTypeDetails.jsonPath().getJsonObject("name").toString();
			String PropulsionTypeId=PropulsionTypeDetails.jsonPath().getJsonObject("id").toString();
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonObject.getAsString("SelectPropulsionTypeID");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", PropulsionTypeId);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
		
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,PropulsionTypeId) && DatabaseUtility.selectQueryComparision(SelectQueryResult,PropulsionTypeName)){
				testPassed("Database contains the PropulsionType Id : "+PropulsionTypeId+" and PropulsionType Name : "+PropulsionTypeName);
			}else {
				testFailed("Database does not contains the PropulsionType Id : "+PropulsionTypeId+" and PropulsionType Name: "+PropulsionTypeName);
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
	}

	public void validatePropulsionTypeDetailsInDB(Response PropulsionTypeDetails) {
		try {

			List<String> PropulsionTypeId=PropulsionTypeDetails.jsonPath().getJsonObject("id");
			System.out.println(PropulsionTypeId.size());
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonObject.getAsString("SelectPropulsionTypeIDDetails");	


			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(SelectQueryResult.size()==PropulsionTypeId.size()) {
				for (int i=0;i<SelectQueryResult.size();i++) {
					if(DatabaseUtility.selectQueryComparision(SelectQueryResult,PropulsionTypeId.get(i))){
						testPassed("Database contains the PropulsionType Id : "+PropulsionTypeId.get(i));
					}else {
						testFailed("Database does not contains the PropulsionType Id : "+PropulsionTypeId.get(i));
					}}
			}else {
				testFailed("Database does not contains same number of values present in API. API response count :"+PropulsionTypeId.size()+"Database response count : "+SelectQueryResult.size());
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
	}
	public void validatePropulsionTypeIdDeleteInDB(String PropulsionTypeId) {
		try {
//			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonObject.getAsString("SelectPropulsionTypeID");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", PropulsionTypeId);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);

			if(!DatabaseUtility.selectQueryComparision(SelectQueryResult,PropulsionTypeId)){
				testPassed("PropulsionType Id : "+PropulsionTypeId+" id deleted from data base");
			}else {
				info("Values from Database is : "+SelectQueryResult.toString());
				testFailed("Database contains the PropulsionType Id : "+PropulsionTypeId+" and is present in database");
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database: "+e.getMessage());
		}
	}
}