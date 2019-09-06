package zf.platform.regression2.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.GreetServiceTest;
import zf.platform.regression2.pages.MulticlientSampleServiceGlobalInfoServicePage;

public class MulticlientSampleServiceGlobalInfoServiceTest extends MulticlientSampleServiceGlobalInfoServicePage {
	GreetServiceTest greetingServiceObject=new GreetServiceTest();
	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());
		configureServiceBus();

	}

	@Test
	public void C62418VerifyPOSTRequestToGlobalinfoToGenerateEventIntopic() {
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription();
		globalInfoServiceStarted("MulitClientSampleServiceSubscription");
		DeleteTopicSubscription(configureTopicSubscription);
		//TODO Openshift step needs to be added 		
	}
	
	@Test
	public void C62417VerifyGetGlobalInfoClients() {
		greetingServiceObject.C61706VerifyuserAbleToGetGlobalInfoClients();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}


}
