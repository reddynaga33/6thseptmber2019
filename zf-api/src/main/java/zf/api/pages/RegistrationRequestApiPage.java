package zf.api.pages;

import java.util.HashMap;
import java.util.List;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.ExtentReport;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class RegistrationRequestApiPage extends ExtentReport {
	JsonReader jsonData=new JsonReader();
	RestApiUtility restapiutility=new RestApiUtility();
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	String RegistrationRequestID=null;

	public String acceptRegistrationRequest() {
		String acceptRegistrationRequestID = null;
		try {

			JSONObject AcceptRegistrationRequestJson = null;
			String AcceptRegistrationRequestID = null;
			Response AcceptRegistrationResponse = null;

			try {
				AcceptRegistrationRequestJson = JsonReader.getJsonObject("AcceptRegistrationRequest");
				AcceptRegistrationRequestJson.put("action",AcceptRegistrationRequestJson.get("action").toString() );
				AcceptRegistrationRequestJson.put("message",AcceptRegistrationRequestJson.get("message").toString() );
				AcceptRegistrationRequestJson.put("id_ext",AcceptRegistrationRequestJson.get("id_ext").toString() );

				ExtentReport.info("Accepting Registration Requestwith:"+AcceptRegistrationRequestJson.toJSONString());
				AcceptRegistrationResponse = restapiutility.CreateServices("registration",AcceptRegistrationRequestJson);	

				if( AcceptRegistrationResponse!=null && AcceptRegistrationResponse.getStatusCode()==200) {
					ExtentReport.info("The Accept Request response status code is : "+AcceptRegistrationResponse.getStatusCode());
					AcceptRegistrationRequestID = AcceptRegistrationResponse.getBody().asString();	
					ExtentReport.info("The Accept Request id is : "+AcceptRegistrationRequestID);
				}
				else {
					ExtentReport.info("The Accept Request response status code is : "+AcceptRegistrationResponse.getStatusCode());
				}
			} catch(Exception e) {
				ExtentReport.info("An exception has occured while Creating Asset payload: AcceptRegistrationRequest");
				AcceptRegistrationRequestID = null;
			}		
			return AcceptRegistrationRequestID;

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return acceptRegistrationRequestID;
	}
	public String createRegistrationRequestUsedEmail() {
		String createRegistrationRequestUsedEmailID = null;
		try {
			JSONObject CreateRegistrationRequestJson = null;

			Response RegistrationRequestResponse = null;

			try {
				CreateRegistrationRequestJson = JsonReader.getJsonObject("RegistrationRequestWithAlreadyUsedEmail");			
				CreateRegistrationRequestJson.put("registrationNumber",CreateRegistrationRequestJson.get("registrationNumber").toString() );
				CreateRegistrationRequestJson.put("adminEmail",CreateRegistrationRequestJson.get("adminEmail").toString() );
				CreateRegistrationRequestJson.put("requeststatus",CreateRegistrationRequestJson.get("requeststatus").toString() );
				CreateRegistrationRequestJson.put("requestedOn",CreateRegistrationRequestJson.get("requestedOn").toString() );
				CreateRegistrationRequestJson.put("clientInfoModel",CreateRegistrationRequestJson.get("clientInfoModel").toString() );
				CreateRegistrationRequestJson.put("divisionId",CreateRegistrationRequestJson.get("divisionId").toString() );

				ExtentReport.info("Creating REGREQ with:"+CreateRegistrationRequestJson.toJSONString());
				RegistrationRequestResponse = restapiutility.CreateServices("registration",CreateRegistrationRequestJson);	

				if( RegistrationRequestResponse!=null && RegistrationRequestResponse.getStatusCode()==200) {
					ExtentReport.info("The created RegReq response status code is : "+RegistrationRequestResponse.getStatusCode());
					createRegistrationRequestUsedEmailID = RegistrationRequestResponse.getBody().asString();	
					ExtentReport.info("The created RegReq id is : "+createRegistrationRequestUsedEmailID);
				}
				else {
					ExtentReport.info("The created RegReq response status code is : "+RegistrationRequestResponse.getStatusCode());
				}
			} catch(Exception e) {
				ExtentReport.info("An exception has occured while Creating Asset payload: RegistrationRequestWithAlreadyUsedEmail");
				createRegistrationRequestUsedEmailID = null;
			}		
			return createRegistrationRequestUsedEmailID;

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return createRegistrationRequestUsedEmailID;
	}
	public String createRegistrationRequest()

	{

		try {
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());

			JSONObject CreateRegistrationRequestJson = null;
			String RegistrationReqestID = null;
			Response RegistrationRequestResponse = null;

			try {
				CreateRegistrationRequestJson = JsonReader.getJsonObject("CreatingRegistrationData");	
				Object clientInfoModel = CreateRegistrationRequestJson.get("clientInfoModel");
				String[] email = ((HashMap<String, Object>) clientInfoModel).get("email").toString().split("@");
				String emailFront = email[0];
				String emailback = email[1];
				((HashMap<String, Object>) clientInfoModel).put( "email",emailFront+ restapiutility.getRandomNumber()+"@"+emailback);
				ExtentReport.info("Executing Post Request against RegistrationRequest using payload : "+CreateRegistrationRequestJson.toJSONString());
				RegistrationRequestResponse = restapiutility.CreateServices("serviceCatalog",CreateRegistrationRequestJson);	

				if( RegistrationRequestResponse!=null && RegistrationRequestResponse.getStatusCode()==200) {
					ExtentReport.info("The created RegistrationRequest response status code is : "+RegistrationRequestResponse.getStatusCode());
					ExtentReport.info("The created RegistrationRequest response is : "+RegistrationRequestResponse.getBody().asString());
					RegistrationReqestID = RegistrationRequestResponse.getBody().asString();	
					ExtentReport.info("The created RegistrationRequest id is : "+RegistrationReqestID);
				}
				else {
					ExtentReport.testFailed("The created Registration Request response status code is : "+RegistrationRequestResponse.getStatusCode());
				}


			}catch(Exception e) {
				ExtentReport.info("An exception has occured while Creating Registration Request payload: CreatingRegistrationData");
				RegistrationReqestID = null;
			}		

			//	return RegistrationReqestID;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return RegistrationRequestID;
	}



	public String rejectRegistrationRequest()
	{
		String RejectRegistrationReqestID = null;
		try {


			JSONObject RejectRegistrationRequestJson = null;
			//		String RejectRegistrationReqestID = null;
			Response RejectRegistrationRequestResponse = null;

			RejectRegistrationRequestJson = JsonReader.getJsonObject("RejectRegistrationData");			
			RejectRegistrationRequestJson.put("action",RejectRegistrationRequestJson.get("action").toString());
			RejectRegistrationRequestJson.put("message",RejectRegistrationRequestJson.get("message").toString());
			RejectRegistrationRequestJson.put("id_ext",RejectRegistrationRequestJson.get("id_ext").toString());


			ExtentReport.info("Executing put Request against RejectRegistrationRequest using payload : "+RejectRegistrationRequestJson.toJSONString());
			//ExtentReport.info("Created ChargingStationData payload is "+CreateChargingStationJson.toJSONString());
			RejectRegistrationRequestResponse = restapiutility.CreateServices("rejectRegistration",RejectRegistrationRequestJson);	

			if( RejectRegistrationRequestResponse!=null && RejectRegistrationRequestResponse.getStatusCode()==200) {
				ExtentReport.info("The Rejected RegistrationRequest response status code is : "+RejectRegistrationRequestResponse.getStatusCode());
				ExtentReport.info("The Rejected RegistrationRequest response is : "+RejectRegistrationRequestResponse.getBody().asString());
				RejectRegistrationReqestID = RejectRegistrationRequestResponse.getBody().asString();	
				ExtentReport.info("The Rejected RegistrationRequest id is : "+RejectRegistrationReqestID);
			}
			else {
				ExtentReport.info("The Rejected Registration Request response status code is : "+RejectRegistrationRequestResponse.getStatusCode());
			}


		}catch(Exception e) {
			ExtentReport.info("An exception has occured while Reject Registration Request payload: RejectRegistrationData");
			RejectRegistrationReqestID = null;
		}		


		return RejectRegistrationReqestID;
	}

	
	public void getAllRegistrationRequest() {
		Response getAllRegistration = null;			
		String getAllRegistrationResponse = null;

		try {

			ExtentReport.info("Getting All Registration Details using Get API");


			getAllRegistration = restapiutility.GetServices("getAllRegistration");
			System.out.println(getAllRegistration.body().toString());
			if(getAllRegistration.getStatusCode()==200) {
				getAllRegistrationResponse = getAllRegistration.getBody().asString();

				ExtentReport.info("ServiceId : " +" is present in the available service");
			}else {
				testFailed("Asset with AssetId " +" is not present in the available Assets and the response message is : "+getAllRegistration.getBody().jsonPath().getString("message"));
				getAllRegistrationResponse = null;
			}	
		}catch(Exception e) {
			ExtentReport.info("An execption has generated while working with getAsset and the message is : "+e.getMessage());
		}
		//return assetResponse;		
		
	
	}
	
}



