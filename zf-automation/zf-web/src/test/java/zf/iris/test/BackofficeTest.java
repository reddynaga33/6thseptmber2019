package zf.iris.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.iris.pages.BackofficePage;
import zf.pages.MicrosoftLoginPage;

public class BackofficeTest extends BackofficePage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISAdminUserName(),EnvironmentManager.getIRISAdminPassword());
	}

	@Test
	public void C55134CreateFleetAndAddSomeVehicle()
	{
		CreateFleetAndAddSomeVehicle();
	}
	@Test
	public void C55134BackofficeCreateNewUser()
	{
		BackofficeCreateNewUser();
	}
	@Test
	public void C55134BackofficeEditVechileTypeInSomeVehicle()
	{
		BackofficeEditVechileTypeInSomeVehicle();
	}
	@Test
	public void C55134BackofficeEditSomeRole()
	{
		BackofficeEditSomeRole();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}