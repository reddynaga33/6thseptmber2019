package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.ClientUsesSampleServceTest;

public class MulticlientSampleServiceCUSTest extends ClientUsesSampleServceTest{

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test
	public void C62429VerifyAssignSampleServiceToNewClient() {
		C62311VerifyAssignSampleServiceToNewClient();
	}
	
	
	@Test
	public void C62430VerifyAssignSampleServiceToWrongClient() {
		 C62312VerifyAssignSampleServiceToWrongClient();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
