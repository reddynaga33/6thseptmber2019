package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.pages.ProxyServicePage;

public class ProxyServiceTest extends ProxyServicePage {
	
	@BeforeMethod
	public void beforeMethod(Method testName)
	{
		startTest(testName.getName());
	}
	@Test
	public void C61751VerifyUserIsAbleToGetTheCurrentContextUsingCorrectClientId() {
		VerifyUserIsAbleToGetTheCurrentContextUsingCorrectClientId();
	}
	
	@Test
	public void C61752VerifyUserIsAbleToGetTheCurrentContextUsingWrongClientId() {
		VerifyUserIsAbleToGetTheCurrentContextUsingWrongClientId();
	}
	@Test
	public void C61753VerifyUserIsAbleToGetTheCurrentContextUsingNoClientId() {
		VerifyUserIsAbleToGetTheCurrentContextUsingNoClientId("proxyCurrentContext");
	}
	@Test
	public void C61755VerifyUserIsAbleToSendGreetingToTheClientWithTheWrongClientId() {
		VerifyUserIsAbleToSendGreetingToTheClientWithTheWrongClientId("ClientGreeting","proxyGreeting");
	}
	@Test
	public void C61754VerifyUserIsAbleToSendGreetingToTheClientWithCorrectClientId() {
		VerifyUserIsAbleToSendGreetingToClientWithCorrectClientId("ClientGreeting","proxyGreeting","SelectGreetingDetails");
	}

	@Test
	public void C61756VerifyUserIsAbleToSendGreetingToTheClientWithTheNoClientId() {
		VerifyUserIsAbleToSendGreetingToTheClientWithTheNoClientId("ClientGreeting","proxyGreetingNoClientId");
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}