package zf.servicecatalog.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.servicecatalog.pages.AssigningServicesAsAdminPage;

public class AssigningServicesAsAdminTest extends AssigningServicesAsAdminPage{
	
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	String ProjectName=null;
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getSCProjectDashboardDaedalusUrl());
		microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentSCProperties(),EnvironmentManager.getEnvironmentSCPWDProperties());
	}
//	@Test
	public void AssignExistingService()
	{
		AssignServicesAlreadyExist("testshaik","Gateway management","Assignservice");
	}
	
	
	@Test
	public void AssignService()
	{
		AssignServices("testshaik","Asset Management","Assignservice");
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
//		DriverManager.closeAllBrowser();
	}

}
