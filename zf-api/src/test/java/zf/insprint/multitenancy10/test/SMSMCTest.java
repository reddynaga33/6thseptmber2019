package zf.insprint.multitenancy10.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zf.api.pages.ClientPropertyPage;
import zf.insprint.multitenancy10.pages.SMSMCPage;

public class SMSMCTest extends SMSMCPage{

	ClientPropertyPage clientpropertypage=new ClientPropertyPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		configureServiceBus();
	}
	
//	@Test
	public void C62418VerifyRequestToGlobalInfoServicestartedMC(){
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription();
		VerifyRequestToGlobalInfoServicestartedMC();
		DeleteTopicSubscription(configureTopicSubscription);
	}
	@Test
	public void aC62823VerifyMulticlientSampleServiceSubscriptionGetDeletedFromTheTopics() {
		VerifyMulticlientSampleServiceSubscriptionGetDeletedFromTheTopics();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}