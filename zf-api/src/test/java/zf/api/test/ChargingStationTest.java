package zf.api.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.api.pages.ChargingStationPage;

public class ChargingStationTest extends ChargingStationPage{

	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test(priority=1,enabled = true)
	public void CreateChargingStation() throws InterruptedException {
		createChargingStation();
	}
	
	@Test(priority=1,enabled = true)
	public void ValidatenewlycreatedChargingStationInDB() throws InterruptedException {
		String ChargingStationID = createChargingStation();
		ValidatenewlycreatedChargingStationInDB(ChargingStationID);
	}
	
	@Test(priority=1,enabled = true)
	public void ValidatenewlycreatedVehicleUsingApi() {
		String VehicleID = createChargingStation();
		validateNewChargingStationusingGetAPI(VehicleID);
	}
	
	@Test(priority=1,enabled = true)
	public void DeleteVehicleInDB() {
		String VehicleID = createChargingStation();
		deleteChargingStationInDB(VehicleID);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
	
}
