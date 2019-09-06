package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.MulticlientSampleServiceSuite.pages.ClientUsesSampleServicePage;

public class ClientUsesSampleServceTest extends ClientUsesSampleServicePage{
	GreetServiceTest greetservice=new GreetServiceTest();

	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());
	}


	@Test
	public void C62311VerifyAssignSampleServiceToNewClient() {
		deleteservice("deleteSampleService","DeleteSampleServicepayload");
		Response assignSampleServiceToNewClient = assignSampleServiceToNewClient("assignSampleService","assignSampleServicePayload");
		String responseValue = getResponseValue(assignSampleServiceToNewClient,"status");
		getSagaEntityValidateSampleservice("GetSagaEntity",responseValue);
		greetservice.VerifyuserAbleToGetGlobalInfoClients("GlobalInfoClients");
	}

	@Test
	public void C62312VerifyAssignSampleServiceToWrongClient() {
		//deleteservice("deleteSampleService","DeleteSampleServicepayload");
		Response assignSampleServiceToNewClient = assignSampleServiceToWrongClient("assignSampleServiceWrongClientID","assignSampleServicePayload");
		String responseValue = getResponseValue(assignSampleServiceToNewClient,"status");
		getSagaEntityValidateSampleservice("GetSagaEntity",responseValue);
		greetservice.VerifyuserAbleToGetGlobalInfoClients("GlobalInfoClients");
	}

	@Test
	public void C62313UnassignAndAssignSampleServiceToClient() {
		Response deleteservice = deleteservice("deleteSampleService","DeleteSampleServicepayload");
		String responseValue = getResponseValue(deleteservice,"status");
		getSagaEntityValidateSampleservice("GetSagaEntity",responseValue);
		greetservice.VerifyuserAbleToGetGlobalInfoClients("GlobalInfoClients");
		assignSampleServiceToNewClient("assignSampleService","assignSampleServicePayload");
		greetservice.VerifyuserAbleToGetGlobalInfoClients("GlobalInfoClients");
		//partially automated
	}


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());

	}
}
