package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.ProxyServiceTest;

public class MulticlientSampleServiceProxyServiceTest extends ProxyServiceTest{

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	@Test(groups = { "Multitenancy 8" })
	public void C62421VerifyGetCurrentContextUsingCorrectClientId() {
		
		C61751VerifyUserIsAbleToGetTheCurrentContextUsingCorrectClientId();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62424VerifySendGreetingToCorrectClientId() {
		
		C61754VerifyUserIsAbleToSendGreetingToTheClientWithCorrectClientId();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
