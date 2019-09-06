package zf.api.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.api.pages.SubscribeToSandboxApiPage;

public class SubscribeToSandboxApiTest extends SubscribeToSandboxApiPage {
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test(priority=1,enabled = true)
	public void CreateSandboxResources() throws InterruptedException {
		createSandboxResources("sandbox");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
