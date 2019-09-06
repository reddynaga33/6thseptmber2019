package zf.MulticlientSampleServiceSuite.pages;

import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class DestinationServicePage extends RestApiUtility {
	String UUIDNumber = null;
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;

	
	public String[] getAllDestinationsFromStorage(String servicename) 
	{String[]destinationdetailsArray=null;
		Response GetAllDestinationsFromStorageResponse = GetServices(servicename);
		Object jsonObject = GetAllDestinationsFromStorageResponse.jsonPath().getJsonObject("nameIdentifier");
		String allDesinations=jsonObject.toString();
		String destinationdetails=allDesinations.replace("[", "");
		String destinationdetailsWithoutarray=destinationdetails.replace("]", "");
				destinationdetailsArray=destinationdetailsWithoutarray.split(",");
						if(GetAllDestinationsFromStorageResponse.getStatusCode()==200) {
			
							testPassed("Get Destinations detail is : "
							+GetAllDestinationsFromStorageResponse.getBody().asString()+" and the name identifier details  is:: "+destinationdetailsArray);
			
		}
		else {
			info("Get Destinations response status code is : "+GetAllDestinationsFromStorageResponse.getStatusCode());
			testFailed("Get Destinations detail is : "+GetAllDestinationsFromStorageResponse.getBody().asString());
		}
		return destinationdetailsArray;
	}
	
	
	public void validateNewAssetInDB(String[] name) {
		try {
			JSONObject sqljsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = sqljsonObject.getAsString("nameidentifier");	
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),sqljsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			for(int i=0;i<SelectQueryResult.size();i++) {
				
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,name[i])){
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the AssetID :  in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the AssetID  in the database" );
			}}
		}catch(Exception e) {
			testFailed("Value from DB "+SelectQueryResult.toString()+" don't the AssetID :  in the database" );
		}
	}
	
}
