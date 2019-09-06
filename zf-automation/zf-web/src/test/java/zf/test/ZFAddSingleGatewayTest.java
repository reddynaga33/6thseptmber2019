package zf.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.ZFAddSingleGatewayPage;
import zf.pages.MicrosoftLoginPage;

public class ZFAddSingleGatewayTest extends ZFAddSingleGatewayPage{

	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	}
	@Test(enabled=false)
	public void C63891AddSingleGatewayWithAllValidInputs() {

		addSingleGatewayWithAllValidInputs();

	}

	@Test
	public void C63892AddSingleGatewayWithMacIDAlreadyExists() {

		addSingleGatewayWithMacIDAlreadyExists();

	}

//	@Test
	public void C63893AddSingleGatewayWithCertificateAlreadyExists() {

		addSingleGatewayWithCertificateAlreadyExists();

	}

	@Test(enabled=false)
	public void C63895AddSingleGatewayWithMandatoryFields() {

		addSingleGatewayWithMandatoryFields();
	}

	@Test
	public void C63897AddSingleGatewayWithoutMandatoryInputs() {

		addSingleGatewayWithOutMandatoryFields();
	}


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}

