package zf.FleetManagementService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.FleetManagementService.test.pages.MiscPage;


public class MiscTest extends MiscPage{
	Response response=null;
	String getSaga=null;
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}	

	@Test (priority=2 ,groups = { "Multitenancy13" })
	public void C63855VerifyChangesAreMadeInGlobalDBAfterAssigningFleetManagementServiceToAClient() {
		response = VerifyChangesAreMadeInGlobalDBAfterAssigningFleetManagementServiceToAClient();
		ValidateResponseCode202(response);
		getSaga= GetSagaResponse(response);
		Response sagaEntityRequest = SagaEntityRequest(getSaga);
		ValidatingClientServiceInDB(sagaEntityRequest);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
