package zf.api.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.api.pages.VehicleApiPage;

public class VehicleApiTest extends VehicleApiPage{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test
	public void TC01CreateNewVehicle() throws InterruptedException {
		String vehicleTypeId=createVehicleType();
		validateNewVehicleInDB(vehicleTypeId);
//		deleteVehicleTypeInDB(vehicleTypeId);
	}
	
	@Test
	public void TC02ValidateAllAvailableVehicleTypes() throws InterruptedException {
		Response validateVehicleTypeGetAPI = validateVehicleTypeGetAPI();
		validateVehicleTypeDetailsInDB(validateVehicleTypeGetAPI);

	}
	
	@Test
	public void TC03ValidateDetailsOfSpecificVehicleType() throws InterruptedException {
		String vehicleTypeID = createVehicleType();
		Response vehicleTypeResponse=validateNewVehicleusingGetAPI(vehicleTypeID);
		validateSpecificVehicleTypeInDB(vehicleTypeResponse);
		deleteVehicleTypeInDB(vehicleTypeID);
		

	}
	@Test
	public void TC04DeleteTheSpecifiedVehicleType() {
		String VehicleID = createVehicleType();
		deleteVehicleTypeInDB(VehicleID);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
