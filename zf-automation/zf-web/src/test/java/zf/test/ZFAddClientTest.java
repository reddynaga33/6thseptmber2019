package zf.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.pages.ZFClientHomePage;

public class ZFAddClientTest extends ZFClientHomePage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	}

	@Test
	public void C52518AddSubClientWithAllFeilds() throws InterruptedException {
		AddSubClient();
	}
	
	@Test
	public void C63881AddSubClientWithMandatoryDetails()  throws InterruptedException {
		addSubClientWithMandatoryDetails();
	}
	
	@Test
	public void C52518CreateNewSubClientValidation()
	{
		verifyCreateNewSubClientValidation();
	}
	
	@Test
	public void C52525C52526CreateNewSubClientEamilValidation()
	{
		verifyCreateNewSubClientEmailValidation();
	}

	@Test
	public void C52523CreateNewSubClientWithWrongTelephoneFormat()
	{
		verifyCreateNewSubClientWithWrongTelephoneFormat();
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}