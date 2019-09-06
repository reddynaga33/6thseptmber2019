package zf.iris.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.iris.pages.ZFRBACPage;
import zf.pages.MicrosoftLoginPage;

public class RBACTest extends ZFRBACPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void C55132PrivilegeToChangeThresholds()  {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISPortalUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISAdminUserName(),EnvironmentManager.getIRISAdminPassword());
		privilegeToChangeThresholds();
	}
		

	@Test
	public void C55132NoPrivilegeToEditUser()  {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISOperatorUserName(),EnvironmentManager.getIRISOperatorPassword());
		noPrivilegeToEditUser();
	}
	
	@Test
	public void AAC55132NoPrivilegeToEditUserRole()  {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISOperatorUserName(),EnvironmentManager.getIRISOperatorPassword());
		noPrivilegeToEditUserRole();
	}
	
	@Test
	public void C55132NoPrivilegeToChangeThresholds()  {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISPortalUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISNOPrivilegeWithThresholdUserName(),EnvironmentManager.getIRISNOPrivilegeWithThresholdPassword());
		noPrivilegeToChangeThresholds();
	}
	
	@Test
	public void C55132NoPrivilegeToEditAssets()  {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISOperatorUserName(),EnvironmentManager.getIRISOperatorPassword());
		noPrivilegeToEditAssets();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
