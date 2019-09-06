package zf.TauriIRIS.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.TauriIRIS.pages.TauriRealTimeDataPage;
import zf.pages.MicrosoftLoginPage;

public class TauriRealTimeDataTest extends TauriRealTimeDataPage {

MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
	}

//	@Test
	public void C55131Dashboard()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyActiveVehiclesDisplay();
	}

//	@Test
	public void C55131clickEditVehicleAndEdit()  {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		clickEditVehicleAndEdit();
	}
	@Test
	public void C55131VehicleSpeedCheck()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		vehicleSpeedCheck();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}