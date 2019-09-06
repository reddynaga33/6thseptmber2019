package zf.ServiceManagementServiceMulticlientSuite.pages;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IServiceDesciptorServiceNewPage extends RestApiUtility{

	public Response VerifyServiceDescriptorIsCreated(String SDName) {
		JSONObject ServiceDescriptorJson=null;
		Response ServiceDescriptorResponse = null;
		ServiceDescriptorJson=JsonReader.getJsonObject("ServiceDesciptorPayload");
//		ServiceDescriptorJson.put("artifactId",ServiceDescriptorJson.get("artifactId").toString()+getRandomNumber());
		ServiceDescriptorJson.put("artifactId",SDName);
		ServiceDescriptorResponse=PutServices("CreateServiceDescriptor",ServiceDescriptorJson,SDName);
		return ServiceDescriptorResponse;
	}
	public Response VerifyUserIsAbleToGetTheDetailsOfSingleClient(String SDName) {
		Response SingleClientDetailsResponse=null;
		SingleClientDetailsResponse=GetServices("GetserviceDescriptor",SDName);
		return SingleClientDetailsResponse;
	}
	public void ValidateResponseCode200(Response response) {
		try {
		if(response.getStatusCode()==200) {
			testPassed("The API response code is Successfully validated");
		}else {
			testFailed("The API response code is not successfully validated");
		}}
		catch(Exception e) {
		testFailed("An exception occured"+e.getMessage());
		}
	}
	public void ValidateResponseCode202(Response response) {
		if(response.getStatusCode()==202) {
			testPassed("The API response code is Successfully validated");
		}else {
			testFailed("The API response code is not successfully validated");
		}
	}
	public void ResponsePerview(Response response,String SDName) {
		info(response.getBody().asString());
		if(response.getBody().asString().contains(SDName)) {
			testPassed("The Client Details are validated");
		}else {
			testFailed("The Client Details Validation failed");
		}
	}
	public Response VerifyServiceDescripterCreation(String Saga) {
		
		Response ServiceDescriptorCreationResponse=null;
		 ServiceDescriptorCreationResponse=GetServices("sagastatussuccess",Saga);
		 info("The Get response is::  "+ServiceDescriptorCreationResponse.getBody().asString());
		return ServiceDescriptorCreationResponse;
	}
	public Response VerifyUserIsAbleToGetTheServices() {
		Response ListServicesResponse=null;
		ListServicesResponse=GetServices("GetServiceDescriptor");
		info("The GET Response is:: "+ListServicesResponse.getBody().asString());
		return ListServicesResponse;
		
	}
	public String GetSagaResponse(Response response ) {

		String responseValue=response.jsonPath().getJsonObject("status");
		return responseValue;
	}
	public String ReturnSagaID(String SagaResponse) {
		String[] SagaIdArray;
		String SagaId=null;
		SagaIdArray = SagaResponse.split("/");
		return SagaId=SagaIdArray[2];
	}
	
	public String VerifyServiceDescriptorIsCreated(){
		JSONObject SDNameJson = null;
		SDNameJson=JsonReader.getJsonObject("SDName");			
		SDNameJson.put("name",SDNameJson.get("name").toString()+getRandomNumber(1,9999));
		String SDName=SDNameJson.getAsString("name");
		Response ServiceDescriptorResponse = VerifyServiceDescriptorIsCreated(SDName);
		ValidateResponseCode202(ServiceDescriptorResponse);
		String getSagaResponse = GetSagaResponse(ServiceDescriptorResponse);
		VerifyServiceDescripterCreation(getSagaResponse);
		return SDName;
	}
}