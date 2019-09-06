package zf.api.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class CUSPropertyPage extends RestApiUtility{
	ClientPropertyPage clientpropertypage=new ClientPropertyPage();
	List<String> SelectQueryResult = null;
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	RestApiUtility restapiutility=new RestApiUtility();
	DatabaseUtility databaseutility=new DatabaseUtility();

	public Response createPropertyForCUS(String servicename, String payLoad,String reconfigureValue,String SDName){
		Response putServicesResponse =null;
		JSONObject createPropertyForCUSJson = null;
		try {
			createPropertyForCUSJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForCUSJson.put("test",createPropertyForCUSJson.get("test").toString() + getRandomNumber());
			
			putServicesResponse = PutServices(servicename, createPropertyForCUSJson, reconfigureValue,SDName);
			if(putServicesResponse.getStatusCode()==202) {

				testPassed("Create CUS property response is : "+putServicesResponse.getBody().asString());
			}
			else {
				info("Create CUS property status code is : "+putServicesResponse.getStatusCode());
				testFailed("Create CUS property response is : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return putServicesResponse;
	}
	
	public Response createPropertyForCUS(String servicename, String payLoad,String reconfigureValue){
		Response putServicesResponse =null;
		JSONObject createPropertyForCUSJson = null;
		try {
			createPropertyForCUSJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForCUSJson.put("test",createPropertyForCUSJson.get("test").toString() + getRandomNumber());
			
			putServicesResponse = PutServices(servicename, createPropertyForCUSJson, reconfigureValue);
			if(putServicesResponse.getStatusCode()==202) {

				testPassed("Create CUS property response is : "+putServicesResponse.getBody().asString());
			}
			else {
				info("Create CUS property status code is : "+putServicesResponse.getStatusCode());
				testFailed("Create CUS property response is : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return putServicesResponse;
	}

	public Response UpdatePropertyForCUS(String servicename, String payLoad,String reconfigureValue){
		Response putServicesResponse =null;
		JSONObject createPropertyForclientJson = null;
		try {
			createPropertyForclientJson=JsonReader.getJsonObject(payLoad);			
			createPropertyForclientJson.put("test",createPropertyForclientJson.get("test").toString() +getRandomNumber());

			putServicesResponse = PutServices(servicename, createPropertyForclientJson, reconfigureValue);
			if(putServicesResponse.getStatusCode()==202) {
				testPassed("Updated CUS property response is : "+putServicesResponse.getBody().asString());
			}
			else {
				info("Updated CUS property status code is : "+putServicesResponse.getStatusCode());
				testFailed("Updated CUS property response is : "+putServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while updating CUS property and the message is :"+e.getMessage());
			}
		return putServicesResponse;
	}

	public Response deletePropertyCUSConfig(String servicename, String reconfigValue) {
		Response deleteClientCUSResponse=null;
		try{
			deleteClientCUSResponse = DeleteServices(servicename,reconfigValue);
			if(deleteClientCUSResponse.getStatusCode()==202) {
				testPassed("Delete CUS Property Response detail is : "+deleteClientCUSResponse.getBody().asString());
			}
			else {
				info("Delete CUS Property response status code is : "+deleteClientCUSResponse.getStatusCode());
				testFailed("Delete CUS Property response detail is : "+deleteClientCUSResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while deleting CUS property details and the message is : "+e.getMessage());
			}
		return deleteClientCUSResponse;
	}
	
	public Response deletePropertyCUSConfig(String servicename, String reconfigValue,String SDName) {
		Response deleteClientCUSResponse=null;
		try{
			deleteClientCUSResponse = DeleteServices(servicename,reconfigValue,SDName);
			if(deleteClientCUSResponse.getStatusCode()==202) {
				testPassed("Delete CUS Property Response detail is : "+deleteClientCUSResponse.getBody().asString());
			}
			else {
				info("Delete CUS Property response status code is : "+deleteClientCUSResponse.getStatusCode());
				testFailed("Delete CUS Property response detail is : "+deleteClientCUSResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while deleting CUS property details and the message is : "+e.getMessage());
			}
		return deleteClientCUSResponse;
	}

	public void getConfigHierarchicalClientAndCUSProperty(String servicename) {
		Response getClientCUSResponse=null;
		try {
			getClientCUSResponse = GetServices(servicename);
			if(getClientCUSResponse.getStatusCode()==200) {
				testPassed("Get CUS Hierarchical Property Response detail is : "+getClientCUSResponse.getBody().asString());
			}
			else {
				info("Get CUS Hierarchical Property Response status code is : "+getClientCUSResponse.getStatusCode());
				testFailed("Get CUS Hierarchical Property Response detail is : "+getClientCUSResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while getting hierarchical the CUS property details and the message is : "+e.getMessage());
			}
	}

	public Response getTheCUSWithWrongSSID(String servicename) {
		Response getWrongClientIDPropertyResponse =null;
		try {
			getWrongClientIDPropertyResponse = GetServices(servicename);
			if(getWrongClientIDPropertyResponse.getStatusCode()==404) {
				testPassed("Response detail is : "+getWrongClientIDPropertyResponse.getBody().asString());
			}
			else {
				testFailed("Client response status code is : "+getWrongClientIDPropertyResponse.getStatusCode());
				testFailed("Client response detail is : "+getWrongClientIDPropertyResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while getting wrong SSID for CUS  details and the message is : "+e.getMessage());
			}
		return getWrongClientIDPropertyResponse;
	}

	public void validateCUSWrongSDIDInDB(Response CUSWithWrongSSIDResponse) {
		try{
			String CUSWithWrongSSIDResponseMessage = CUSWithWrongSSIDResponse.jsonPath().getJsonObject("message").toString();
			String[] responseSplit = CUSWithWrongSSIDResponseMessage.split("serviceDescriptorId=");
			String[] serviceDescriptorID = responseSplit[1].split(" was not found");
			String SelectQuery = jsonObject.getAsString("SelectWrongSSIDProperty");
			SelectQuery = SelectQuery.replaceFirst("tempValue", serviceDescriptorID[0]);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(SelectQueryResult.isEmpty()){
				testPassed("DB does not contains the wrong service descriptor ID : "+serviceDescriptorID[0]);
			}else {
				testFailed("DB contains the wrong service descriptor ID : "+serviceDescriptorID[0]);
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
	}


	public void validateCUSPropertyInDB(Response ClientAndCUSPropertyResponse) {
		Set<String> keySet=null;
		Object[] array=null;
		HashMap<String, Object> ClientProp=null;
		int count = 0;
		try {
			String SelectQuery = jsonObject.getAsString("SelectCUSProperty");
			ClientProp=ClientAndCUSPropertyResponse.jsonPath().getJsonObject("CLIENT_USES_SERVICE");
			keySet = ClientProp.keySet();
			array = keySet.toArray();
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			for(count=0;count<keySet.size();count++){
				if(DatabaseUtility.selectQueryComparision(SelectQueryResult,array[count].toString()) && DatabaseUtility.selectQueryComparision(SelectQueryResult,ClientProp.get(array[count]).toString())){
					testPassed("Value from DB  contains the  : "+array[count]+" : "+ClientProp.get(array[count]));
				}else {
					testFailed("Value from DB  contains : "+array[count]+" : "+ClientProp.get(array[count]));
				}
			}
		}catch(Exception e) {
			testFailed("Exception occured while validating data from DB:"+e.getMessage());
		}
	}

}