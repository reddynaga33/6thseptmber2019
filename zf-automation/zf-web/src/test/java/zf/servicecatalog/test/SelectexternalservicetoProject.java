package zf.servicecatalog.test;

import org.testng.annotations.BeforeMethod;

import zf.pages.MicrosoftLoginPage;
import zf.servicecatalog.pages.SelectexternalservicetoProjectpage;



import java.lang.reflect.Method;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;

public class SelectexternalservicetoProject extends SelectexternalservicetoProjectpage {
	
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	@BeforeMethod
	public void beforeMethod(Method testName) {
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getSCProjectDashboardTauriUrl());
	
	}

	@Test
	public void registration() throws InterruptedException {
		//microsoftlogin.microsoftLoginsandbox(EnvironmentManager.getEnvironmentProperties("SANDBOX_PRIVILEGEMULTICLIENTSUSERNAME"));
		Thread.sleep(200);
		assignservicetouser();
	
	
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
