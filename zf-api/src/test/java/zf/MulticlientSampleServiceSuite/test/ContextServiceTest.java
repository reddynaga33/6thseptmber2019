package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.pages.ContextServicePage;

public class ContextServiceTest extends ContextServicePage {

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	
	@Test
	public void C61570GetCurrentClientIdDetailsWithCorrectClientID() {
		getCurrentClientIdDetailsWithCorrectClientID("CurrentClientContext");
	}
	
	@Test
	public void C61670GetCurrentContextDetailsWithCorrectClientID() {
		getCurrentContextDetails("CurrentContextClient");
	}
	
	@Test
	public void C61673GetCurrentRequestContextDetailsWithCorrectClientID() {
		getCurrentRequestContextDetails("CurrentRequestContextClient");
	}
	
	@Test
	public void C61571GetCurrentClientIdContextWithCorrectClientID() {
		getCurrentClientIdContextWithCorrectClientID("CurrentClientContext");
	}
	
	@Test
	public void CC61575GetClientDetailsWithoutClientID()
	{
		getClientDetailsWithoutClientID("GetClientDetailsWithoutClientID");
	}	

	@Test
	public void C61576GetClientContextWithWrongClientID()
	{
		verifyGetCurrentClientWithWrongCliendId("GetClientContextWithWrongClientID");
	}	

	@Test
	public void AAC61578GetCurrentClientIDWithWrongClientID()
	{
		verifyGetCurrentClientWithWrongCliendId("GetCurrentClientIDWithWrongClientID");
	}	

	@Test
	public void C61579GetCurrentClientIDWithNoClientID()
	{
		getCurrentClientIDWithNoClientID("GetCurrentClientIDWithNoClientID");
	}

	@Test
	public void C61672GetCurrentContextIDWithNoClientID()
	{
		getCurrentContextIDWithNoClientID("GetCurrentContextIDWithNoClientID");
	}

	@Test
	public void C61574GetCurrentClientWithWrongCliendId() {

		verifyGetCurrentClientWithWrongCliendId("GetWrongClientIdDetails");

	}	
	@Test
	public void C61577GetVerifyCurrentClientContextWithNoClientId() {

		VerifyCurrentClientContextWithNoClientId("CurrentClientContextWithNoClientID");

	}
	@Test
	public void C61671GetVerifyCurrentClientContextWithCliendId() {

		getCurrentClientIdContextWithCorrectClientID("CurrentClientContextWithCliendId");

	}	
	@Test
	public void C61692GetCurrentRequestContextWithWrongClientId() {

		verifyGetCurrentClientWithWrongCliendId("CurrentClientContextWithWrongCliendId");

	}		
	@Test
	public void C61693GetVerifyCurrentRequestContextNoclientId() {

		GetCurrentRequestContextNoclientId("CurrentRequestContextNoclientId");
	}
	
	
	@Test
	public void C61572GetVerifyCurrentClientIdWithCorrectClientID() {

		getVerifyCurrentClientIdWithCorrectClientID("CurrentClientIDWithClientId");
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
