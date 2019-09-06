package zf.api.pages;

import java.util.List;



import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.ExtentReport;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class ServiceDescriptorPage extends RestApiUtility{

	JsonReader jsonData=new JsonReader();
	DatabaseUtility databaseutility=new DatabaseUtility();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	List<String> SelectQueryResult = null;


	public Response getServiceDescriptor() {
		Response ServiceDescriptorJson = null;			
		String ServiceDescriptorResponse = null;
		try {
			ServiceDescriptorJson = GetServices("servicedescriptorservice");
			ServiceDescriptorResponse = ServiceDescriptorJson.getBody().asString();

			if(ServiceDescriptorJson.getStatusCode()==200) {				
			
				 testPassed("Servicedescriptor Response is : "+ServiceDescriptorResponse);
			}else {
					testFailed("ServiceDescripter Response is "+ServiceDescriptorJson.getBody().jsonPath());
			}	
		}catch(Exception e) {
			testFailed("An execption has generated while working with ServiceDescripter and the message is : "+e.getMessage());
		}
		return ServiceDescriptorJson;	

	}
	public String assignServiceDescriptorToCLientWithoutAppId() {
		String result=null;
		Response serviceDescriptorJson = null;			
		String serviceDescriptorResponse = null;
		JSONObject ServiceDescriptorJson=null;
		try {
			ServiceDescriptorJson = JsonReader.getJsonObject("ServiceDescriptorWithOutAppId");		

			serviceDescriptorJson = PutServices("assignServiceToClientWithoutAppid",ServiceDescriptorJson);
			serviceDescriptorResponse = serviceDescriptorJson.getBody().asString();
			if(serviceDescriptorJson.getStatusCode()==202) {
				testPassed("ServiceDescriptor To CLientWithoutAppId Response is : "+serviceDescriptorResponse);
				result=serviceDescriptorJson.jsonPath().getJsonObject("status");
			}else {
				testFailed("ServiceDescriptor To CLientWithoutAppId Response is : "+serviceDescriptorResponse);
			}	
		}catch(Exception e) {
			 testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return result;
	}
	
	public Response getSagaStatusCLientWithoutAppId(String SagaStatus) {
		Response serviceDescriptorJson=null;
		try {
			serviceDescriptorJson = GetServices("sagastatusfail",SagaStatus);
			if(serviceDescriptorJson.getStatusCode()==200) {
				 testPassed("ServiceDescriptor Response is :"+serviceDescriptorJson.getBody().asString());
			}else {
				testFailed("ServiceDescriptor Response is : "+serviceDescriptorJson.getBody().asString());
			}	
		}catch(Exception e) {
			 testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return serviceDescriptorJson;
	}

	public String AssigningServiceDescriptorWithAppidToTheClient() {
		String result = null;
		JSONObject ServiceDescriptorRequestJson = null;
		Response ServiceDescriptorResponse = null;	
		try {
			ServiceDescriptorRequestJson = JsonReader.getJsonObject("ServiceDescriptorWithAppId");		
			ServiceDescriptorResponse = PutServices("ServiceDescriptorWithAppId",ServiceDescriptorRequestJson);
			if( ServiceDescriptorResponse!=null && ServiceDescriptorResponse.getStatusCode()==202) {
				testPassed("ServiceDescriptorToCLientWithoutAppId Response is : "+ServiceDescriptorResponse);
				result=ServiceDescriptorResponse.jsonPath().getJsonObject("status");
			}
			else {
				testFailed("ServiceDescriptorToCLientWithoutAppId Response is : "+ServiceDescriptorResponse);
			}
		} catch(Exception e) {
			 testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return result;
	}

	public Response getSagaStatusCLientWithAppId(String SagaStatus) {
		Response serviceDescriptorJson = null;			
		String serviceDescriptorResponse = null;
		try {
			serviceDescriptorJson = GetServices("sagastatussuccess",SagaStatus);
			serviceDescriptorResponse = serviceDescriptorJson.getBody().asString();
			if(serviceDescriptorJson.getStatusCode()==200) {
				 testPassed("ServiceDescriptorResponse is :"+serviceDescriptorResponse);
			}else {
				 info("serviceDescriptor Statuc Code is :"+ serviceDescriptorJson.getStatusCode());
				testFailed("ServiceDescriptorResponse is :"+serviceDescriptorResponse);
			}	
		}catch(Exception e) {
			testFailed("An execption has generated while working with serviceDescriptor and the message is : "+e.getMessage());
		}
		return serviceDescriptorJson;
	}
	
	public String returnSDidwithoutAppid(Response SD)
	{
		List <String> SDid=null;
		List <String> AppSDid=null;
		String SDidWithoutAppId=null;
		SDid=SD.jsonPath().getList("id");
		AppSDid=SD.jsonPath().getList("appId");
		for(int i=0;i<SDid.size();i++)
		{
			if(AppSDid.get(i)==null)
			{
				SDidWithoutAppId=SDid.get(i);
				info("Service Descriptor id :"+SDidWithoutAppId );
				break;
			}
		}
		return SDidWithoutAppId;		
	}


	public void validateSDidinDB(String returnSDidwithoutAppid) {
		try {
		
			String SelectQuery =jsonObject.getAsString("SelectServiceDescriptorIdWithoutAppId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", returnSDidwithoutAppid);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,returnSDidwithoutAppid)){
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the Service Descriptor Id : "+returnSDidwithoutAppid+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the Service Descriptor Id : "+returnSDidwithoutAppid+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
	}

	public void validateAssigningSagaStatusInDB(String SagaStausId) {
		String SagaId=null;
		String EventStatus=null;
		try {
			String[] SagaStausIdArray = SagaStausId.split("/");
			SagaId = SagaStausIdArray[2];
			EventStatus=SagaStausIdArray[4];
			String SelectQuery = jsonObject.getAsString("SelectSagaId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", SagaId);
			Thread.sleep(10000);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,SagaId)&&DatabaseUtility.selectQueryComparision(SelectQueryResult,EventStatus)){
				info("Value from DB "+SelectQueryResult.toString()+" contains the Saga Status Id : "+SagaId+ " in the database" );
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the Saga Event Status Id : "+EventStatus+ " in the database" );
			}else {
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the Saga Event Status Id : "+EventStatus+ " in the database" );
			}
		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
	}

	public void validateSagaStatusCLientStatusInDB(Response SagaResponse) {
		String SagaId=null;
		String SagaOperation=null;
		String SagaStatus=null;
		try {
			SagaId=SagaResponse.jsonPath().getJsonObject("sagaId");
			SagaOperation=SagaResponse.jsonPath().getJsonObject("operation");
			SagaStatus=SagaResponse.jsonPath().getJsonObject("status");
			String SelectQuery = jsonObject.getAsString("SelectSagaId");	
			SelectQuery = SelectQuery.replaceFirst("tempValue", SagaId);
			Thread.sleep(5000);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,SagaId)&&DatabaseUtility.selectQueryComparision(SelectQueryResult,SagaOperation)&&DatabaseUtility.selectQueryComparision(SelectQueryResult,SagaStatus)){
				info("Value from DB "+SelectQueryResult.toString()+" contains the Saga Status Id : "+SagaId+ " in the database" );
				info("Value from DB "+SelectQueryResult.toString()+" contains the Saga Operation Id : "+SagaOperation+ " in the database" );
				testPassed("Value from DB "+SelectQueryResult.toString()+" contains the Saga Status : "+SagaStatus+ " in the database" );

			}else {
				info("Value from DB "+SelectQueryResult.toString()+" don't the Saga Status Id : "+SagaId+ " in the database" );
				info("Value from DB "+SelectQueryResult.toString()+" don't the Saga Operation Id : "+SagaOperation+ " in the database" );
				testFailed("Value from DB "+SelectQueryResult.toString()+" don't the Saga Status : "+SagaStatus+ " in the database" );

			}

		}catch(Exception e) {
			testFailed("An Exception occured while validating API response with Database and the message is : "+e.getMessage());
		}
	}

}