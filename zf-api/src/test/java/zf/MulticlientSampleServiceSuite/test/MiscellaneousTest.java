package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.MulticlientSampleServiceSuite.pages.MiscellaneousPage;

public class MiscellaneousTest extends MiscellaneousPage{

	
	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());
		configureServiceBus();
	}


	@Test
	public void C59815GreetingSendAndReceiveFromEventAPI() {
		configureTopicSubscription("");
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());

	}
}
