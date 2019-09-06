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

public class ChargingStationPage extends ExtentReport {
	String chargingStationID = null;	
	DatabaseUtility databaseutility=new DatabaseUtility();
	RestApiUtility restapiutility=new RestApiUtility();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	JsonReader jsonData=new JsonReader();
	List<String> SelectQueryResult = null;
	String vehicleResponse = null;
	boolean vehicleDelete = false;	
	String chargingStationResponse = null;
	boolean chargingStationDelete = false;	
	
	
	public String createChargingStation() {
		String ChargingStationId = null;
		try {
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	
			JSONObject CreateChargingStationJson = null;
			//String ChargingStationId = null;
			Response ChargingStationResponse = null;

			try {
				CreateChargingStationJson = JsonReader.getJsonObject("CreateChargingStationData");			
				CreateChargingStationJson.put("name",CreateChargingStationJson.get("name").toString() + restapiutility.getRandomNumber());

				ExtentReport.info("Executing Post Request against ChargingStation using payload : "+CreateChargingStationJson.toJSONString());
				//ExtentReport.info("Created ChargingStationData payload is "+CreateChargingStationJson.toJSONString());
				ChargingStationResponse = restapiutility.CreateServices("chargingstation",CreateChargingStationJson);	

				if( ChargingStationResponse!=null && ChargingStationResponse.getStatusCode()==200) {
					ExtentReport.info("The created ChargingStation response status code is : "+ChargingStationResponse.getStatusCode());
					ExtentReport.info("The created ChargingStation response is : "+ChargingStationResponse.getBody().asString());
					ChargingStationId = ChargingStationResponse.getBody().asString();	
					ExtentReport.info("The created ChargingStation id is : "+ChargingStationId);
				}
				else {
					ExtentReport.info("The created Charging station response status code is : "+ChargingStationResponse.getStatusCode());
				}


			}catch(Exception e) {
				ExtentReport.info("An exception has occured while Creating ChargingStation payload: CreateChargingStationData");
				ChargingStationId = null;
			}		

			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ChargingStationId;
	}
	
	public void validateNewChargingStationusingGetAPI(String chargingStationID) {
		try {
			Response ChargingStationJson = null;			
			String ChargingStationId = null;
			String ChargingStationResponse = null;

			try {
				ChargingStationId = (String)JsonReader.getJsonObject(chargingStationID).get("id");
			}catch(Exception e) {
				//ExtentReport.info(ChargingStationId +" dictionary is not found in the data file hence using it as ChargingStation Id string ");
				ChargingStationId = chargingStationID;
			}	

			ExtentReport.info("Executing Get Request against ChargingStation using chargingStationId : "+ChargingStationId);

			try {
				ChargingStationJson = restapiutility.GetServices("chargingstation",ChargingStationId);
				if(ChargingStationJson.getStatusCode()==200) {
					ExtentReport.info("Charging Station with ChargingStationId "+ChargingStationId +" is present in the available Charging Stations");
					ExtentReport.info("Charging Station with ChargingStationId "+ChargingStationId +" is present in the available Charging Stations and its response body is : "+ ChargingStationJson.getBody().asString());			
					ChargingStationResponse = ChargingStationJson.getBody().asString();
				}else {
					ExtentReport.info("Charging Station with ChargingStationId "+ChargingStationId +" is not present in the available Charging Stations");
					ChargingStationResponse = null;
				}	
			}catch(Exception e) {
				ExtentReport.info("An execption has generated while working with getChargingStation and the message is : "+e.getMessage());
			}
		//	return ChargingStationResponse;		
		//	chargingStationResponse = restapiutility.GetChargingStation(chargingStationID);
			
			if(ChargingStationResponse!= null) {
				ExtentReport.testPassed("Value from GetAPI "+ChargingStationResponse.toString()+" contains the chargingStationID : "+chargingStationID );
			}else {
				ExtentReport.testFailed("Value from GetAPI "+ChargingStationResponse.toString()+" contains the chargingStationID : "+chargingStationID );
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void ValidatenewlycreatedChargingStationInDB(String chargingStationID) {
		try {

			String SelectQuery = jsonData.getJsonData("SelectAssetQueryUsingChargingStationId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", chargingStationID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,chargingStationID)){
				ExtentReport.testPassed("Value from DB "+SelectQueryResult.toString()+" contains the chargingStationID : "+chargingStationID+ " in the database" );
			}else {
				ExtentReport.testFailed("Value from DB "+SelectQueryResult.toString()+" don't the chargingStationID : "+chargingStationID+ " in the database" );
			}
		}catch(Exception e) {
			ExtentReport.testFailed("Value from DB "+SelectQueryResult.toString()+" don't the chargingStationID : "+chargingStationID+ " in the database" );
		}
	}
	
	public void deleteChargingStationInDB(String chargingStationID) {
		try {
			
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonData.getJsonData("SelectAssetQueryUsingChargingStationId");
			chargingStationDelete = restapiutility.DeleteChargingStation(chargingStationID);
			
			if(chargingStationDelete) {
				ExtentReport.testPassed("chargingStationID "+chargingStationID +"is deleted from DB");
			}else {
				ExtentReport.testFailed("chargingStationID "+chargingStationID +"is not deleted from DB");
			}
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			
			if(!DatabaseUtility.selectQueryComparision(SelectQueryResult,chargingStationID)){
				ExtentReport.testPassed("Unable to find the charging Station with id :"+chargingStationID +" deleted sucessfully in DB" );
			}else {
				ExtentReport.testFailed("Able to find the charging Station with id :"+chargingStationID +" in DB" );
			}
			chargingStationResponse = restapiutility.GetChargingStation(chargingStationID);
			Assert.assertNull(vehicleResponse, "Unable to find the charging Station with id :"+chargingStationID +" deleted sucessfully Checking using Get charging Station API");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
