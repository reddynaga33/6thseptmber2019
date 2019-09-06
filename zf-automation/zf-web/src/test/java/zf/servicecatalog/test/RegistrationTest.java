package zf.servicecatalog.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import framework.DriverManager;
import framework.EnvironmentManager;
import zf.servicecatalog.pages.RegistrationPage;

public class RegistrationTest extends RegistrationPage  {
	
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getSCPublicLandingPageDaedalusUrl());
	}

	@Test
	public void C59890UserRegistration() {
	
	String emailID = userRegistrationSuccess("ServicecatalogRegisterationDetails");
	String clientInfoID = RegistrationDBValidation("RegistrationDetails",emailID);
	RegistrationStatusDBValidation("RegistrationStatus",clientInfoID);
	}
	
	@Test
	public void C59891UserRegistrationCancel() {
	
		userRegistrationCancel("ServicecatalogRegisterationDetails");
	
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
