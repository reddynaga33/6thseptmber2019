package zf.MulticlientSampleServiceSuite.pages;

import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class ClientUsesSampleServicePage extends RestApiUtility{

	public Response assignSampleServiceToNewClient(String servicename,String payload) {
		JSONObject sampleserviceJsonObject = null;
		Response sampleserviceResponse =null;
		try {
			sampleserviceJsonObject=JsonReader.getJsonObject(payload);	
			String serviceID = sampleserviceJsonObject.get("serviceID").toString();
			sampleserviceJsonObject.put("artifactId",sampleserviceJsonObject.get("artifactId").toString() + getRandomNumber());
			sampleserviceJsonObject.remove("serviceID");
			sampleserviceResponse=	PutServices(servicename,sampleserviceJsonObject,serviceID);
			if(sampleserviceResponse.getStatusCode()==202) {
				testPassed(" assignSampleService response is : "+sampleserviceResponse.getBody().asString());
			}
			else {
				info("assignSampleService status code is : "+sampleserviceResponse.getStatusCode());
				testFailed("assignSampleService response is : "+sampleserviceResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during put request and message is : "+e.getMessage());
		}
		return sampleserviceResponse;

	}

	public Response assignSampleServiceToWrongClient(String servicename,String payload) {
		JSONObject sampleserviceJsonObject = null;
		Response sampleserviceResponse =null;
		try {
			sampleserviceJsonObject=JsonReader.getJsonObject(payload);	

			sampleserviceJsonObject.put("artifactId",sampleserviceJsonObject.get("artifactId").toString() + getRandomNumber());

			sampleserviceResponse=	PutServices(servicename,sampleserviceJsonObject);
			if(sampleserviceResponse.getStatusCode()==202) {
				testPassed(" assignSampleService response is : "+sampleserviceResponse.getBody().asString());
			}
			else {
				info("assignSampleService status code is : "+sampleserviceResponse.getStatusCode());
				testFailed("assignSampleService response is : "+sampleserviceResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed ("An exception occured during put request and message is : "+e.getMessage());
		}
		return sampleserviceResponse;

	}


	public void getSaga(String servicename,String sagaStatus) {
		GetServices(servicename,sagaStatus);
	}

	public Response deleteservice(String servicename, String payload) {
		Response sampleserviceResponse =null;
		JSONObject sampleserviceJsonObject = null;
		String serviceId=null;
		try {
			sampleserviceJsonObject=JsonReader.getJsonObject(payload);
			serviceId = sampleserviceJsonObject.get("serviceID").toString();
			sampleserviceResponse = DeleteServices(servicename, serviceId);
			if(sampleserviceResponse.getStatusCode()==202) {
				testPassed("Delete response is : "+sampleserviceResponse.getBody().asString());
			}
			else {
				info("Delete property status code is : "+sampleserviceResponse.getStatusCode());
				testFailed("delete response is : "+sampleserviceResponse.getBody().asString());
			}

		}catch(Exception e) {
			testFailed ("An exception occured during delete request and message is : "+e.getMessage());
		}
		return sampleserviceResponse;}

	public void getSagaEntityValidateSampleservice(String servicename,String sagastatus) {
		Response getSagaEntityResponse =null;
		try {
			sleep(10000);
			getSagaEntityResponse =  GetServices(servicename, sagastatus);
			String SagaStatusResponseValue = getResponseValue(getSagaEntityResponse,"status");
			if(getSagaEntityResponse.getStatusCode()==200) {
				testPassed("Saga status response body  is :"+getSagaEntityResponse.getBody().asString()+"  and status is : "+SagaStatusResponseValue);
			}
			else {
				info("Saga response detail is : "+getSagaEntityResponse.getBody().asString());
				testFailed("Saga status detail is : "+SagaStatusResponseValue);
			}}catch(Exception e) {
				testFailed("Exception occured while getting the saga details and the message is :"+e.getMessage());
			}
	}
}
