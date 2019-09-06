package zf.TauriIRIS.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.TauriIRIS.pages.TauriBackofficePage;
import zf.pages.MicrosoftLoginPage;

public class TauriBackofficeTest extends TauriBackofficePage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
//		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
//		microsoftlogin.microsoftLogin(EnvironmentManager.getIRISAdminUserName(),EnvironmentManager.getIRISAdminPassword());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	}

	@Test
	public void AAC55134CreateFleetAndAddSomeVehicle()
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
	{String vehicleName=null;
		BackofficeEditVechileTypeInSomeVehicle();
	}
	@Test
	public void C55134BackofficeEditSomeRole()
	{	
	String createdUser = BackofficeCreateNewUser();
		BackofficeEditSomeRole(createdUser);
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}