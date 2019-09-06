package zf.api.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.api.pages.PropulsionTypePage;

public class PropulsionTypeTest extends PropulsionTypePage {

	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test
	public void TC01CreatePropulsionType() throws InterruptedException {
		String createPropulsionType = createPropulsionType("CreatePropulsionType");
		validatePropulsionTypeIdInDB(createPropulsionType);
		
	}
	@Test
	public void TC02DeletePropulsionType() throws InterruptedException {
		String createPropulsionTypeID=createPropulsionType("CreatePropulsionType");
		if(DeletePropulsionType(createPropulsionTypeID)) {
			validatePropulsionTypeIdDeleteInDB(createPropulsionTypeID);
		}
	}
	@Test
	public void TC03GetSpecificPropulsionTypeDetails(){
		String createPropulsionTypeID=createPropulsionType("CreatePropulsionType");
		Response specificPropulsionTypeDetails = getSpecificPropulsionTypeDetails("getSpecificPropulsionTypeDetails", createPropulsionTypeID);
		validateSpecificPropulsionTypeIdInDB(specificPropulsionTypeDetails);
	}
	@Test
	public void TC04GetPropulsionTypeDetails() {
		 Response propulsionTypeDetails = getPropulsionTypeDetails("getPropulsionTypeDetails");
		validatePropulsionTypeDetailsInDB(propulsionTypeDetails);
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}