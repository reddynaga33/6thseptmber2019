package zf.platform.regression2.pages;

import framework.RestApiUtility;
import io.restassured.response.Response;

public class SMSMCISagaServicePage extends RestApiUtility{

	public void VerifyTheSagaEntityForServiceDescriptorWithCorrectSagaId(String SagaReponse,String SagaId) {
		Response ServiceDescriptorResponse=null;
		ServiceDescriptorResponse=GetServices("sagastatussuccess",SagaReponse);
		info("The Get response is::  "+ServiceDescriptorResponse.getBody().asString());
		if(ServiceDescriptorResponse.getStatusCode()==200 && ServiceDescriptorResponse.jsonPath().getJsonObject("sagaId").equals(SagaId)) {
			testPassed("The Sagaid of a get request is correct");
		}else {
			testFailed("The Sagaid of a get request is incorrect");
		}
	}
	public void VerifyTheSagaEntityForServiceDescriptorWithWrongSagaId(String SagaReponse) {
		Response ServiceDescriptorResponse=null;
		String SagaWrong = SagaReponse.replace("c","a");
		ServiceDescriptorResponse=GetServices("sagastatussuccess",SagaWrong);
		info("The Get response is::  "+ServiceDescriptorResponse.getBody().asString());
		if(ServiceDescriptorResponse.getStatusCode()==404) {
			testPassed("The Sagaid of a get request is passed as wrong");
		}else {
			testFailed("The Sagaid of a get request is passed as correct");
		}
	}
	public void VerifyTheSagaEntityForServiceDescriptorWithNoSagaId(String SagaReponse,String SagaId) {
		Response ServiceDescriptorResponse=null;
		String SagaWrong = SagaReponse.replace("/"+SagaId,"");
		ServiceDescriptorResponse=GetServices("sagastatussuccess",SagaWrong);
		info("The Get response is::  "+ServiceDescriptorResponse.getBody().asString());
		if(ServiceDescriptorResponse.getStatusCode()==404) {
			testPassed("The Sagaid of a get request is passed with no sagaid");
		}else {
			testFailed("The Sagaid of a get request is passed with sagaid");
		}
	}
}