package zf.ServiceManagementServiceMulticlientSuite.pages;

import java.util.List;
import java.util.UUID;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class IServiceDescriptorServicePageOLD extends RestApiUtility {
	String UUIDNumber = null;
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;

	public String VerifyUserIsAbleToGetAllTheServices() {
		Response GetAllServices=null;
		String Serviceid=null;
		try {
			GetAllServices=GetServices("servicedescriptorservice");
			info("The Get Service Response Body is :"+GetAllServices.getBody().asString());
			String id = GetAllServices.jsonPath().getJsonObject("id").toString();
			String[] ServiceId=id.split(",");
			Serviceid=ServiceId[0];
			if(GetAllServices.getStatusCode()==200) {
				info("The sample service id is: "+GetAllServices.jsonPath().getJsonObject("id"));
				testPassed("The Services are succefully listed");
			}else {
				testFailed("The Services are not listed");
			}}catch(Exception e) {testFailed("The Services is not available and the message is :"+e.getMessage());}
		return Serviceid;
	}


	public void VerifyUserIsAbleToGetOneServiceWihCorrectServiceDescriptorID(String ServiceId) {
		Response GetOneService=null;
		try {
			GetOneService=GetServices("GetserviceDescriptor",ServiceId);
			info("The Get Service Response Body is :"+GetOneService.getBody().asString());
			if(GetOneService.getStatusCode()==200 && GetOneService.jsonPath().getJsonObject("id").equals(ServiceId)) {
				info("The service id is: "+GetOneService.jsonPath().getJsonObject("id"));
				testPassed("The Services id is avialbe");
			}else {
				testFailed("The Services is not available ");
			}}catch(Exception e) {testFailed("The Services is not available and the message is :"+e.getMessage());}
	}

	public void VerifyUserIsAbleToGetOneServiceWihwrongServiceDescriptorID(String ServiceId) {
		Response GetOneWrongService=null;
		String WrongServiceId=ServiceId+getRandomNumber();
		try{
			GetOneWrongService=GetServices("GetserviceDescriptor",WrongServiceId);

			info("The Get Service Response Body is :"+GetOneWrongService.getBody().asString());
			if(GetOneWrongService.getStatusCode()==404) {
				info("The Response message of service id is: "+GetOneWrongService.jsonPath().getJsonObject("message"));
				testPassed("The Services id is not found");
			}else {
				testFailed("The Services is not found ");
			}}catch(Exception e) {testFailed("The Services is not found and the message is :"+e.getMessage());}
	}

	public void CreateServiceDescriptorWithNoUUID(String servicename) {
		Response CreateServiceDescriptorWithNoUUID=null;
		try{
			CreateServiceDescriptorWithNoUUID=PutServices(servicename);

			if(CreateServiceDescriptorWithNoUUID.getStatusCode()==405) {
				testPassed("The Response message is: "+CreateServiceDescriptorWithNoUUID.getBody().asString());

			}else {
				testFailed("The Response message is: "+CreateServiceDescriptorWithNoUUID.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("Exception occured while creating ServiceDescriptor With NoUUID and the message is :"+e.getMessage());
		}
	}

	public String createServiceDescriptorResponse(String servicename,String payload){
		UUIDNumber=UUID.randomUUID().toString();
		info("Service Descriptor ID  : "+UUIDNumber);
		JSONObject createServiceDescriptorJson = null;
		String sagaStatus=null;
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);
			createServiceDescriptorJson.put("name",createServiceDescriptorJson.get("name").toString() + getRandomNumber());
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			createServiceDescriptorJson.put("uuid",UUIDNumber);
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson);
			sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
			if(postServicesResponse.getStatusCode()==202) {
				sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}
	public String createServiceDescriptorWithWrongID(String servicename,String payload){
		//		String UUIDNumber = UUID.randomUUID().toString();

		JSONObject createServiceDescriptorJson = null;
		String sagaStatus=null;
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("name",createServiceDescriptorJson.get("name").toString() + getRandomNumber());
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			createServiceDescriptorJson.put("uuid",createServiceDescriptorJson.get("uuid").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson);
			if(postServicesResponse.getStatusCode()==400) {

				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}


	public void getSagaStatus(String servicename,String sagaStatus) {
		Response getServicesResponse =null;
		try{
			getServicesResponse = GetServices(servicename,sagaStatus);
			if(getServicesResponse.getStatusCode()==200) {

				testPassed("Create Service descriptor response is : "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+getServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}

	}
	public String getServiceDescriptorID(String servicename) {
		Response getServicesResponse =null;
		try {
			getServicesResponse = GetServices(servicename,UUIDNumber);
			if(getServicesResponse.getStatusCode()==200) {

				testPassed("Create Service descriptor response is : "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+getServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return UUIDNumber;
	}

	public void getServiceDescriptorIDForWrongDetails(String servicename) {
		Response getServicesResponse =null;
		try {
			getServicesResponse = GetServices(servicename,UUIDNumber);
			if(getServicesResponse.getStatusCode()==404) {

				testPassed("Create Service descriptor response is : "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+getServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
	}
	
	

	public void createServiceDescriptorWithNoName(String servicename,String payload) {
		JSONObject createServiceDescriptorJson = null;
		UUIDNumber=UUID.randomUUID().toString();
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson,UUIDNumber);
			if(postServicesResponse.getStatusCode()==400) {

				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}

	}

	public void createServiceDescriptorDefaultvalue(String servicename,String payload) {
		JSONObject createServiceDescriptorJson = null;
		UUIDNumber=UUID.randomUUID().toString();
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			//createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson,UUIDNumber);
			if(postServicesResponse.getStatusCode()==202) {

				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
	}


	public String createServiceDescriptorWithDuplicateArtifactID(String servicename,String payload) {
		JSONObject createServiceDescriptorJson = null;
		UUIDNumber=UUID.randomUUID().toString();
		String sagaStatus=null;
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("name",createServiceDescriptorJson.get("name").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson,UUIDNumber);
			sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
			if(postServicesResponse.getStatusCode()==202) {

				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}

	public String createServiceDescriptorWithDuplicateName(String servicename,String payload) {
		JSONObject createServiceDescriptorJson = null;
		String sagaStatus=null;
		UUIDNumber=UUID.randomUUID().toString();
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson,UUIDNumber);
			if(postServicesResponse.getStatusCode()==202) {
				sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}
	public String serviceDescriptorWithDuplicateName(String servicename,String payload) {
		JSONObject createServiceDescriptorJson = null;
		String sagaStatus=null;
		UUIDNumber=UUID.randomUUID().toString();
		Response postServicesResponse=null;
		try{
			createServiceDescriptorJson=JsonReader.getJsonObject(payload);	
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson,UUIDNumber);
			
			
			createServiceDescriptorJson.put("name",createServiceDescriptorJson.get("name").toString() + getRandomNumber());
			createServiceDescriptorJson.put("artifactId",createServiceDescriptorJson.get("artifactId").toString() + getRandomNumber());
			createServiceDescriptorJson.put("uuid",UUIDNumber);
			postServicesResponse =  PutServices(servicename, createServiceDescriptorJson);
			sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
			if(postServicesResponse.getStatusCode()==202) {
				sagaStatus=postServicesResponse.jsonPath().getJsonObject("status");
				testPassed("Create Service descriptor response is : "+postServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+postServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
		return sagaStatus;
	}
	
	public void getWrongServiceDescriptorIDDetails(String servicename) {
		Response getServicesResponse =null;
		try {
			Long UUID= getRandomNumber();
			String UUIDNumber=Long.toString(UUID);
			getServicesResponse = GetServices(servicename,UUIDNumber);
			if(getServicesResponse.getStatusCode()==404) {

				testPassed("Create Service descriptor response is : "+getServicesResponse.getBody().asString());
			}
			else {
				testFailed("Create Service descriptor response is  : "+getServicesResponse.getBody().asString());
			}}catch(Exception e) {
				testFailed("Exception occured while creating CUS property and the message is :"+e.getMessage());
			}
	}
	
	
	
	public Response createServDescWithCorrectUUID(String servicename, String payLoad){
		UUIDNumber=	 UUID.randomUUID().toString(); 
		Response CreateServDescWithCorrectUUIDResponse =null;
		JSONObject CreateServDescWithCorrectUUIDJson = null;
		CreateServDescWithCorrectUUIDJson=JsonReader.getJsonObject(payLoad);			
		CreateServDescWithCorrectUUIDJson.put("name",CreateServDescWithCorrectUUIDJson.get("name").toString() +getRandomNumber());
		CreateServDescWithCorrectUUIDJson.put("artifactId",CreateServDescWithCorrectUUIDJson.get("artifactId").toString() +getRandomNumber());
		CreateServDescWithCorrectUUIDJson.put("groupId",CreateServDescWithCorrectUUIDJson.get("groupId").toString() +getRandomNumber());
		CreateServDescWithCorrectUUIDResponse = PutServices(servicename, CreateServDescWithCorrectUUIDJson,UUIDNumber);
		if(CreateServDescWithCorrectUUIDResponse.getStatusCode()==202) {
			testPassed("Create Service Descriptor With Correct UUID response is : "+CreateServDescWithCorrectUUIDResponse.getBody().asString());
		}
		else {
			info("Create Service Descriptor With Correct UUID status code is : "+CreateServDescWithCorrectUUIDResponse.getStatusCode());
			testFailed("Create Service Descriptor With Correct UUID  response is : "+CreateServDescWithCorrectUUIDResponse.getBody().asString());
		}
		return CreateServDescWithCorrectUUIDResponse;
	}
	
	public Response getSDWithCorrectUUID(String servicename,String serviceId) 
	{
		Response GetServDescWithCorrectUUIDResponse = GetServices(servicename,serviceId);
		if(GetServDescWithCorrectUUIDResponse.getStatusCode()==200) {
			testPassed("Get Destinations detail is : "+GetServDescWithCorrectUUIDResponse.getBody().asString());
		}
		else {
			info("Get Destinations response status code is : "+GetServDescWithCorrectUUIDResponse.getStatusCode());
			testFailed("Get Destinations detail is : "+GetServDescWithCorrectUUIDResponse.getBody().asString());
		}
		return GetServDescWithCorrectUUIDResponse;
	}
	
	public Response getValidateSDWithCorrectUUID(String servicename) 
	{
		Response GetServDescWithCorrectUUIDResponse = GetServices(servicename,UUIDNumber);
		if(GetServDescWithCorrectUUIDResponse.getStatusCode()==200) {
			testPassed("Get Destinations detail is : "+GetServDescWithCorrectUUIDResponse.getBody().asString());
		}
		else {
			info("Get Destinations response status code is : "+GetServDescWithCorrectUUIDResponse.getStatusCode());
			testFailed("Get Destinations detail is : "+GetServDescWithCorrectUUIDResponse.getBody().asString());
		}
		return GetServDescWithCorrectUUIDResponse;
	}
	
	
	
	
}
