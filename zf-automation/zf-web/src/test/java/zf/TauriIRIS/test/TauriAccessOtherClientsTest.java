package zf.TauriIRIS.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.TauriIRIS.pages.TauriAccessOtherClients;
import zf.pages.MicrosoftLoginPage;

public class TauriAccessOtherClientsTest extends TauriAccessOtherClients {

	
MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());

	}

	@Test
	public void C55140CheckDashboardOfUserwithOneClientAccess()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
				checkDashboardOfUserwithOneClientAccess();
	}
	
	@Test
	public void C55140CheckDashboardOfAnotherClientAndUserWithNoAccess()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		CheckDashboardOfAnotherClientAndUserWithNoAccess();
		
	
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
