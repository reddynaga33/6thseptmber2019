package BackofficeService.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BackofficeService.pages.BackofficeServiceMulticlientPage;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.regression.pages.FleetPage;

public class BackofficeServiceMulticlientTest extends BackofficeServiceMulticlientPage{

FleetPage fleetPageObject=new FleetPage();
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());
	
	}
	@Test
	public void C64696CRUDFleet() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		String FleetName = fleetPageObject.addNewFleetwithDefaultVehicleAdded();
		int afterAddVehicleCount = fleetPageObject.getDefaultFleetDetails();
		String editedFleetName = fleetPageObject.editNewFleet(FleetName);
		fleetPageObject.deleteFleet(editedFleetName);
		int afterDeleteVehicleCount = fleetPageObject.getDefaultFleetDetails();
		fleetPageObject.campareDefaultfleetsize(afterAddVehicleCount,afterDeleteVehicleCount);
	}

//	@Test(priority=1 ,groups = { "Multitenancy13" })
	public void C64704ServiceStaretdWithNewDestination() {
		configureServiceBus();
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription("BackOfficeServicePayload");
		DeleteTopicSubscription(configureTopicSubscription);
	}

	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
