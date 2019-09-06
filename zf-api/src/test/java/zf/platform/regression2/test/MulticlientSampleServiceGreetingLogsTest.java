package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.GreetingLogsServiceTest;

public class MulticlientSampleServiceGreetingLogsTest extends GreetingLogsServiceTest{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	
	}
	@Test(groups = { "Multitenancy 8" })
	public void C62398VerifyUserAbleToCreateGreetinglogsToCorrectExistingClient() {
		C61708VerifyUserAbleToCreateGreetinglogsToCorrectExistingClient();
	}

	
	@Test(groups = { "Multitenancy 8" })
	public void C62399VerifyUserAbleToCreateGreetinglogsToCorrectExistingClient() {
		C61709VerifyUserAbleToGetCreateGreetinglogsToCorrectExistingClient();
	}

	@Test(groups = { "Multitenancy 8" })
	public void C62400VerifyUserAbleToDeleteGreetinglogsToCorrectExistingClient() {
		C61710VerifyUserAbleToDeleteGreetinglogsToCorrectExistingClient();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62407VerifyUserAbleToUpdateGreetinglogsToCorrectExistingClient() {
		C61717VerifyUserAbleToUpdateGreetinglogsToCorrectExistingClient();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62410VerifyUserAbleToGetGreetinglogsToCorrectExistingClientID() {
		C61720VerifyUserAbleToGetGreetinglogsToCorrectExistingClientID();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62413VerifyUserAbleToGetGreetinglogsWithWrongGreetingID() {
		C61723VerifyUserAbleToGetGreetinglogsWithWrongGreetingID();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62414VerifyUserAbleToDeletedSpecificGreetinglogsToCorrectExistingClient() {
		C61724VerifyUserAbleToDeletedSpecificGreetinglogsToCorrectExistingClient();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
