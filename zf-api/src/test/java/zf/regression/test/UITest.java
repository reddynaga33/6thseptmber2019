package zf.regression.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.regression.pages.UIPage;

public class UITest extends UIPage {
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getPortalUrl());
	}

	@Test
	public void C64434MicrosoftLoginWrongEmail()
	{
		//microsoftlogin.microsoftLogin(EnvironmentManager.getWrongAdminUserName(),EnvironmentManager.getAdminPassword());
		microsoftLoginWithWrongEmail(EnvironmentManager.getWrongAdminUserName());
	}

	@Test
	public void C64529microsoftLoginWrongPassword()
	{
		microsoftLoginWrongPassword(EnvironmentManager.getAdminUserName(),EnvironmentManager.getWrongAdminPassword());
	}

	@Test
	public void C64530LoginWithValidCredentials()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());	
		microsoftlogin.validatePortalPageTitle();
	}

	@Test
	public void C64535LoginStaySignedIn()
	{
		microsoftLoginstaysignedin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());	
	}

	@Test
	public void C64536LaunchAllApplications() throws InterruptedException
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		launchAllApplications();
	}


	@Test
	public void C64539HelpCheckOpenmatrics()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		checkHelpOpenmatrics();

	}

	@Test
	public void C64540HelpDashBoard()
	{microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	checkHelpDashBoard();	
	}

	@Test
	public void C64541HelpBackOffice()
	{microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	helpBackOffice();	
	}	

	@Test
	public void C64542HelpVehicleManagement()
	{microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	helpVehicleManagement();	
	}	

	@Test
	public void C64543HelpUserManagement()
	{microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	helpUserManagement();	
	}	

	@Test
	public void C64538CheckForSpinners() throws InterruptedException
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		checkForSpinners();
	}


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}

}
