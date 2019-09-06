package zf.ClientManagementServiceSuite.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.ClientManagementServiceSuite.pages.IClientServicePage;
import zf.ClientManagementServiceSuite.pages.MiscPage;

public class MiscTest extends MiscPage{
	IClientServicePage ICLientServicePageobject=new IClientServicePage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		configureServiceBus();
	}

	@Test(priority=1 ,groups = { "Multitenancy13" })
	public void C65638CUSGenerateEventAfterSendEventFromCUA() {
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription("CMSPayload");
		DeleteTopicSubscription(configureTopicSubscription);
	}
	
	@Test(priority=1 ,groups = { "Multitenancy13" })
	public void C63410UserAbleToCreateCLientWithSameClientId() {
		String createdClient = ICLientServicePageobject.CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		ICLientServicePageobject.getClientDetails("GetClientsDetails",createdClient);
		String createdClientID = ICLientServicePageobject.CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
				ICLientServicePageobject.getOneClientDetails("GetOneClientDetails",createdClientID);
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
