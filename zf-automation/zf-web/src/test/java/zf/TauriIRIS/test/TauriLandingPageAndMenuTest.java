package zf.TauriIRIS.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.TauriIRIS.pages.TauriLandingPageAndMenuPage;
import zf.pages.MicrosoftLoginPage;

public class TauriLandingPageAndMenuTest extends TauriLandingPageAndMenuPage{

	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
//		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());	
	}
	
	@Test
	public void C55133LoginWithMoreThanOneClient()
	{	microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
//		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISAdminUserName(),EnvironmentManager.getIRISAdminPassword());
		LoginWithMoreThanOneClient();
	}
	
	@Test
	public void C55133BothClientsGlobalMenu()
	{
//		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISPrivilegeMultiClientsUserName(),EnvironmentManager.getIRISPrivilegeMultiClientsPassword());
		microsoftlogin.microsoftLogin(EnvironmentManager.getPrivilegeMultiClientsUserName(),EnvironmentManager.getPrivilegeMultiClientsPassword());
		BothClientsGlobalMenu();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}