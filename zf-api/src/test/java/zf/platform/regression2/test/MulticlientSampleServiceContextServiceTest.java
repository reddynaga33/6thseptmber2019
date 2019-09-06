package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.ContextServiceTest;

public class MulticlientSampleServiceContextServiceTest extends ContextServiceTest {
//	ContextServiceTest contextServiceObject=new ContextServiceTest();

	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());


	}
	@Test
	public void C62433GetCurrentClientIdDetailsWithCorrectClientID() {
		C61570GetCurrentClientIdDetailsWithCorrectClientID();
		
	}
	@Test
	public void C62436VerifyUserAbleToGetCurrentClientContextWithCorrectClientID() {
		C61671GetVerifyCurrentClientContextWithCliendId();
	}
	
	@Test
	public void C62439VerifyUserAbleToGetCurrentClientContextWithCorrectClientID() {
		C61572GetVerifyCurrentClientIdWithCorrectClientID();
	}
	
	@Test
	public void C62442VerifyUserAbleToGetCurrentContext() {
		C61670GetCurrentContextDetailsWithCorrectClientID();
	}

	
	@Test
	public void C62445VerifyUserAbleToGetCurrentRequestContext() {
		C61673GetCurrentRequestContextDetailsWithCorrectClientID();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
			}
}
