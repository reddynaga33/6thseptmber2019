package Multiclientlibs.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MulticlientlibsPages.BroadcastEventForAllClientPage;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;

public class BroadcastEventForAllClientTest extends BroadcastEventForAllClientPage {
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
//	configureServiceBus();
	}
	@Test(priority=3 ,groups = { "Multitenancy13" })
	public void C63577VerifySendEventToTopicToRemovePrivilegesInAllDefault()
	{
//		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription("APSMultipleRemoveAllDefaultPayload");
//		DeleteTopicSubscription(configureTopicSubscription);
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		validateDefaultRoleInUI();
		}
	
	@Test(priority=2 ,groups = { "Multitenancy13" })
	public void C63582VerifySendEventToTopicToRemovePrivilegesInMultipleDefault() {
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription("APSMultipleRemoveMultipleDefaultPayload");
		DeleteTopicSubscription(configureTopicSubscription);
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		validateDefaultRoleInUI();
	}

	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
