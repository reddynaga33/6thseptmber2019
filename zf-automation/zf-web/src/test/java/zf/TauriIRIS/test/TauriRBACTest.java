package zf.TauriIRIS.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.TauriIRIS.pages.TauriZFRBACPage;
import zf.pages.MicrosoftLoginPage;

public class TauriRBACTest extends TauriZFRBACPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
	}

	@Test
	public void C55132PrivilegeToChangeThresholds()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getPrivilegeWithThresholdUserName(),EnvironmentManager.getPrivilegeWithThresholdPassword());
		privilegeToChangeThresholds();
	}
		
	@Test
	public void C55132NoPrivilegeToEditUser()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		noPrivilegeToEditUser();
	}
	
	@Test
	public void C55132NoPrivilegeToEditUserRole()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		noPrivilegeToEditUser();
	}
	
	@Test
	public void C55132NoPrivilegeToChangeThresholds()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		editUserWithNoRole();
		DriverManager.closeAllBrowser();
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		noPrivilegeToChangeThresholds();
	}
	
	
	@Test
	public void C55132NoPrivilegeToEditAssets()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		noPrivilegeToEditAssets();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
