package zf.api.pages;

import java.util.HashMap;
import java.util.List;

import framework.DatabaseUtility;
import framework.ElementManager;
import framework.ExtentReport;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class SubscribeToSandboxApiPage  extends ElementManager {
	
	
	RestApiUtility restapiutility=new RestApiUtility();
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	 String subscriptionID=null;
	
	  public String createSandboxResources(String payLoad) {
			JSONObject SandbonRequestJson = null;
			String SandboRequestID = null;
			Response SandbonResponse = null;

			try {
				SandbonRequestJson = JsonReader.getJsonObject(payLoad);
				SandbonRequestJson.put("divisionName",SandbonRequestJson.get("divisionName").toString()+restapiutility.getRandomNumber() );
				SandbonRequestJson.put("workspaceName",SandbonRequestJson.get("workspaceName").toString()+restapiutility.getRandomNumber()  );

				ExtentReport.info("Accepting Registration Requestwith:"+SandbonRequestJson.toJSONString());
				SandbonResponse = restapiutility.CreateServices("sandbox",SandbonRequestJson);	

				if( SandbonResponse!=null && SandbonResponse.getStatusCode()==200) {
					ExtentReport.info("The Sanbox Request response status code is : "+SandbonResponse.getStatusCode());
					SandboRequestID = SandbonResponse.getBody().asString();	
					ExtentReport.info("The Sanbox Request id is : "+SandboRequestID);
					if(SandbonRequestJson.get("subscribedBy").equals(((HashMap<String, Object>) SandbonResponse).get("subscribedBy"))) {
						ExtentReport.testPassed("Expected subscribedBy is "+SandbonRequestJson.get("subscribedBy") +" "+"Actual subscribedBy is"+((HashMap<String, Object>) SandbonResponse).get("subscribedBy"));
					}else {
						ExtentReport.testFailed("Expected subscribedBy is "+SandbonRequestJson.get("subscribedBy") +" "+"Actual subscribedBy is"+((HashMap<String, Object>) SandbonResponse).get("subscribedBy"));
					}
				}
				else {
					ExtentReport.info("The Sanbox Request response status code is : "+SandbonResponse.getStatusCode());
				}
			} catch(Exception e) {
				ExtentReport.info("An exception has occured while Creating Sanbox payload: "+payLoad);
				SandboRequestID = null;
			}		

		return subscriptionID;
	}

}
