package zf.api.pages;

import java.util.List;

import org.testng.Assert;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.TestLogger;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class VehicleApiPage extends RestApiUtility {
	String vehicleID = null;	
	DatabaseUtility databaseutility=new DatabaseUtility();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JsonReader jsonData=new JsonReader();
	List<String> SelectQueryResult = null;
	String vehicleResponse = null;
	boolean vehicleDelete = false;	


	public String createVehicleType() {

		JSONObject CreateVehicleTypeJson = null;
		String vehicleTypeId = null;
		Response vehicleTypeResponse = null;
		try {
			CreateVehicleTypeJson = JsonReader.getJsonObject("CreateVehicleTypeData");			
			CreateVehicleTypeJson.put("name",CreateVehicleTypeJson.get("name").toString() + getRandomNumber());
			CreateVehicleTypeJson.put("description",CreateVehicleTypeJson.get("description").toString() + getRandomNumber());	

			info("Creating Vehicle with:"+CreateVehicleTypeJson.toJSONString());
			vehicleTypeResponse =CreateServices("VehicleType",CreateVehicleTypeJson);	
			vehicleTypeId = vehicleTypeResponse.getBody().asString();	
			if( vehicleTypeResponse!=null && vehicleTypeResponse.getStatusCode()==200) {
				testPassed("The created VehicleType id is : "+vehicleTypeId);
			}
			else {
				testFailed("VehicleType is not created and the body is : "+vehicleTypeResponse.getBody().asString());
			}

		}catch(Exception e) {
			testFailed("An exception has occured while Creating VehicleType payload:CreateVehicleTypeData ");
				}
		
		return vehicleTypeId;

	}

	public Response validateNewVehicleusingGetAPI(String vehicleID) {
		Response vehicleResponse = null;
		try {
			String vehicleId = null;

			try {
				vehicleId = (String)JsonReader.getJsonObject(vehicleID).get("id");
			}catch(Exception e) {
				vehicleId = vehicleID;
			}	
			vehicleResponse = GetServices("vehicleTypeOne",vehicleId);
			if(vehicleResponse.getStatusCode()==200) {
				testPassed("Value from GetAPI for VehicleType is :  "+vehicleResponse.toString()+" contains the vehicleID : "+vehicleID );
			}else {
				testFailed("Vehicle with vehicleId "+vehicleId +" is not present in the available Vehicles");
			}	
		}catch(Exception e) {
			testFailed("An exception has generated while working with getvehicle and the message is : "+e.getMessage());
		}
		return vehicleResponse;
	}


	public void validateNewVehicleInDB(String vehicleID) {
		try {
		
			String SelectQuery = jsonObject.getAsString("SelectVehicleTypeId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", vehicleID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,vehicleID)){
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the VehicleId : "+vehicleID+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the VehicleId : "+vehicleID+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing value from database : "+e.getMessage());
		}
	}

	public void deleteVehicleInDB(String vehicleID) {
		try {

			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonObject.getAsString("SelectAssetQueryUsingVehicleId");
			vehicleDelete = DeleteVehicle(vehicleID);

			if(vehicleDelete) {
				testPassed("vehicleID "+vehicleID +"is deleted from DB");
			}else {
				testFailed("vehicleID "+vehicleID +"is not deleted from DB");
			}
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);

			if(!DatabaseUtility.selectQueryComparision(SelectQueryResult,vehicleID)){
				testPassed("Unable to find the vehicle with id :"+vehicleID +" deleted sucessfully in DB" );
			}else {
				testFailed("Able to find the vehicle with id :"+vehicleID +" in DB" );
			}
			vehicleResponse = GetVehicle(vehicleID);
			Assert.assertNull(vehicleResponse, "Unable to find the vehicle with id :"+vehicleID +" deleted sucessfully Checking using Get vehicle API");
		}catch(Exception e) {
			testFailed("An exception has generated while working database : "+e.getMessage());
		}
	}


	public Response validateVehicleTypeGetAPI() {
		String vehicleTypeResponse = null;
		Response vehicleTypeJson = null;			
		try {
			vehicleTypeJson = GetServices("vehicleType");
			vehicleTypeResponse = vehicleTypeJson.getBody().asString();
			if(vehicleTypeJson.getStatusCode()==200) {
				testPassed("Get Vehicle Type Details are :"+vehicleTypeResponse);
			}else {
				testFailed("Get Vehicle Type Details are :"+vehicleTypeResponse);
			}	
		}catch(Exception e) {
			testFailed("An exception has generated while working with getvehicle and the message is : "+e.getMessage());
		}
		return vehicleTypeJson;
	}

	public boolean DeleteTheSpecifiedVehicleType(String VehicleID)
	{
		boolean result = false;
		boolean DeleteVehicleTypeRequestID = false;
		try {
			Response deleteVehicleResponse = null;		
			String vehicleId = null;

			try {
				vehicleId = (String)JsonReader.getJsonObject(VehicleID).get("id");
			}catch(Exception e) {
				vehicleId = VehicleID;
			}

			info("Executing Delete Request against vehicle using vehicleId : "+vehicleId);

			try {
				deleteVehicleResponse =DeleteServices("vehicle",vehicleId);

				if( deleteVehicleResponse!=null && deleteVehicleResponse.getStatusCode()==204) {
					info("The deleteted Vehicle id "+vehicleId+" status code is "+deleteVehicleResponse.getStatusCode());
					info("The deleteted Vehicle id "+vehicleId+" response is "+deleteVehicleResponse.getBody().asString());
					testPassed("The Vehicle id "+vehicleId+" is deleted successfully");
					result = true;
				}
				else {
					info("The deleteted vehicle "+vehicleId+" status code is "+deleteVehicleResponse.getStatusCode());
					testFailed("The Vehicle id "+vehicleId+" is not deleted successfully");
					result = false;
				}
			}catch(Exception e) {
				info("An execption has generated while working with deleteVehicle and the message is : "+e.getMessage());
			}

		} catch(Exception e) {
			testFailed("An excption has generated while deleting vehicletype : "+e.getMessage());
		}
		return result;
	}

	public void validateVehicleTypeDetailsInDB(Response vehicleDetails) {
		try {

			List<String> VehicleTypeId=vehicleDetails.jsonPath().getJsonObject("id");
			System.out.println(VehicleTypeId.size());
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonData.getJsonData("SelectAllVehicleTypeId"); 
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(SelectQueryResult.size()==VehicleTypeId.size()) {
				for (int i=0;i<SelectQueryResult.size();i++) {
					if(DatabaseUtility.selectQueryComparision(SelectQueryResult,VehicleTypeId.get(i))){
						testPassed("Database contains the PropulsionType Id : "+VehicleTypeId.get(i));
					}else {
						testFailed("Database does not contains the PropulsionType Id : "+VehicleTypeId.get(i));
					}}
			}else {
				testFailed("Database does not contains same number of values present in API. API response count :"+VehicleTypeId.size()+"Database response count : "+SelectQueryResult.size());
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing value in the database: "+e.getMessage());
		}
	}
	
	public void validateSpecificVehicleTypeInDB(Response vehicleTypeResponse) {
		try {

			System.out.println(vehicleTypeResponse.getBody().asString());
			String vehicleTypeId=vehicleTypeResponse.jsonPath().getJsonObject("id");
			String vehicleTypeName=vehicleTypeResponse.jsonPath().getJsonObject("name");
			String SelectQuery = jsonObject.getAsString("SelectVehicleTypeId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", vehicleTypeId);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,vehicleTypeId)&&DatabaseUtility.selectQueryComparision(SelectQueryResult,vehicleTypeName)){
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the VehicleId : "+vehicleTypeId+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the VehicleId : "+vehicleTypeId+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An excption has generated while comparing value in the database. The message is : "+e.getMessage());
		}
	}
	
	public void deleteVehicleTypeInDB(String vehicleID) {
		try {
			String SelectQuery = jsonObject.getAsString("SelectAssetQueryUsingVehicleId");
			SelectQuery = SelectQuery.replaceFirst("tempValue", vehicleID);
			vehicleDelete = DeleteTheSpecifiedVehicleType(vehicleID);
			if(vehicleDelete) {
				testPassed("vehicleID "+vehicleID +"is deleted from DB");
			}else {
				testFailed("vehicleID "+vehicleID +"is not deleted from DB");
			}
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(!DatabaseUtility.selectQueryComparision(SelectQueryResult,vehicleID)){
				testPassed("Unable to find the vehicle with id :"+vehicleID +" deleted sucessfully in DB" );
			}else {
				testFailed("Able to find the vehicle with id :"+vehicleID +" in DB" );
			}
			vehicleResponse = GetVehicle(vehicleID);
			Assert.assertNull(vehicleResponse, "Unable to find the vehicle with id :"+vehicleID +" deleted sucessfully Checking using Get vehicle API");
		}catch(Exception e) {
			testFailed("An excption has generated while comparing value in the database. The message is : "+e.getMessage());
		}
	}
}
