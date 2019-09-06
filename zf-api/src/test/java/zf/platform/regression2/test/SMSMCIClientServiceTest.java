package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.ServiceManagementServiceMulticlientSuite.test.IClientServiceTest;

public class SMSMCIClientServiceTest extends IClientServiceTest{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void C62848GetVerifyUserIsAbleToGetClientDetails() {

		C62232GetVerifyUserIsAbleToGetClientDetails();
	}
	
	@Test
	public void C62849VerifyGetClientDetails(){
		C62233VerifyGetClientDetails();
	}

	@Test
	public void C62850VerifyGetClientDetailsWrongClientID(){
		C62234VerifyGetClientDetailsWrongClientID();
	}
	
	@Test
	public void C62851VerifyUserIsAbleToAssignServiceDescriptoToClient() {
		C63868VerifyUserIsAbleToAssignServiceDescriptoToClient();
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
