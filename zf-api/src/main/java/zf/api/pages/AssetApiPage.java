package zf.api.pages;

import java.util.List;

import org.testng.Assert;
import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class AssetApiPage extends RestApiUtility{
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JsonReader jsonData=new JsonReader();
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	String assetResponse = null;
	boolean assetDelete = false;

	public String createAsset(String servicename,String PayLoad) {
		String AssetId = null;
		try {

			JSONObject CreateAssetJson = null;
			Response AssetResponse = null;

			try {
				CreateAssetJson = JsonReader.getJsonObject(PayLoad);			
				CreateAssetJson.put("name",CreateAssetJson.get("name").toString()+getRandomNumber());
				CreateAssetJson.put("description",CreateAssetJson.get("description").toString() +  getRandomNumber());	
				info("Creating asset with:"+CreateAssetJson.toJSONString());
				AssetResponse =  CreateServices(servicename,CreateAssetJson);	
				if( AssetResponse!=null && AssetResponse.getStatusCode()==200) {
					AssetId = AssetResponse.getBody().asString();	
					testPassed("The created Asset id is : "+AssetId);
				}
				else {
					testFailed("The created Asset response status code is : "+AssetResponse.getStatusCode());
				}
			}catch(Exception e) {
				testFailed("An exception has occured while Creating Asset payload: CreateAsset and the message is :"+e.getMessage());
				
			}		


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return AssetId;
	}

	public void validateNewAssetusingGetAPI(String assetID) {
		try {

			Response assetJson = null;			
			String assetId = null;
			String assetResponse = null;

			try {
				assetId = (String)JsonReader.getJsonObject(assetID).get("id");
			}catch(Exception e) {
				assetId = assetID;
			}	
			info("Getting Asset details using assetId : "+assetId);
			assetJson =  GetServices("asset",assetId);
			if(assetJson.getStatusCode()==200) {
				assetResponse = assetJson.getBody().asString();
				info("AssetId : "+assetId +" is present in the available Assets");
			}else {
				info("Asset with AssetId "+assetId +" is not present in the available Assets and the response message is : "+assetJson.getBody().jsonPath().getString("message"));
				assetResponse = null;
			}	

			if(assetResponse!= null) {
				testPassed("Value from GetAPI "+assetResponse.toString()+" contains the AssetID : "+assetID );
			}else {
				testFailed("Value from GetAPI "+assetResponse.toString()+" contains the AssetID : "+assetID );
			}
		}catch(Exception e) {
			testFailed("Assetid "+assetID +"is not deleted from DB");
		}}
	
	public void deleteAssetInDB(String assetID) {
		try {
			
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonData.getJsonData("SelectAssetQueryUsingAssetId");
			SelectQuery = SelectQuery.replaceFirst("tempValue", assetID);
			Response deleteAssetResponse = null;		
			boolean result = false;
			String assetId = null;
			try {
				assetId = (String)JsonReader.getJsonObject(assetID).get("id");
			}catch(Exception e) {
				// info(createAssetID +" dictionary is not found in the data file hence using it as asset Id string ");
				assetId = assetID;
			}

			info("Deleting the Asset using assetId : "+assetId);
			deleteAssetResponse =  DeleteServices("asset",assetId);
			if( deleteAssetResponse!=null && deleteAssetResponse.getStatusCode()==200) {
				info("The deleteted Asset id "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
				info("The Asset id "+assetId+" is deleted successfully");
				result = true;
			}
			else {
				info("The deleteted Asset "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
				info("The deleteted Asset "+assetId+" status code is "+deleteAssetResponse.getBody().jsonPath().getString("message"));
				info("The Asset id "+assetId+" is not deleted successfully");
				result = false;
			}
			if(result) {
				testPassed("Assetid "+assetID +"is deleted from DB");
			}else {
				testFailed("Assetid "+assetID +"is not deleted from DB");
			}

			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(!DatabaseUtility.selectQueryComparision(SelectQueryResult,assetID)){
				testPassed("Unable to find the Asset with id :"+assetID +" deleted sucessfully in DB" );
			}else {
				testFailed("Able to find the Asset with id :"+assetID +" in DB" );
			}
			assetResponse = GetAsset(assetID);
			Assert.assertNull(assetResponse, "Unable to find the Asset with id :"+assetID +" deleted sucessfully Checking using Get Assest API");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void validateNewAssetInDB(String assetID) {
		try {
			String SelectQuery = jsonData.getJsonData("SelectAssetQueryUsingAssetId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", assetID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,assetID)){
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the AssetID : "+assetID+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the AssetID : "+assetID+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("Value from DB "+SelectQueryResult.toString()+" don't the AssetID : "+assetID+ " in the database" );
		}
	}
	
	
	
	public void deleteAssetInDataBase(String assetID) {
		try {
			
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonData.getJsonData("SelectAssetQueryUsingAssetId");
			SelectQuery = SelectQuery.replaceFirst("tempValue", assetID);
			Response deleteAssetResponse = null;		
			String assetId = null;

			info("Deleting the Asset using assetId : "+assetID);
			deleteAssetResponse =  DeleteServices("asset",assetID);
			if( deleteAssetResponse!=null && deleteAssetResponse.getStatusCode()==200) {
				info("The deleteted Asset id "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
//				info("The Asset id "+assetId+" is deleted successfully");
			
			}
			else {
				info("The deleteted Asset "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
//			
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}


