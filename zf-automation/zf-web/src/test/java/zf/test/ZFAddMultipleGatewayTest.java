package zf.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.pages.ZFAddMultipleGatewayPage;

public class ZFAddMultipleGatewayTest extends ZFAddMultipleGatewayPage {
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	}

	//TestRail#C47400 - Validate adding Multiple Gateways_New Enrollment group_Root certificate
//	@Test
	public void C47400AddMultipleGatewayWithNewEnrollmentGroupWithRootUploadCertificate() {
			addMultipleGatewayNewRoot();
	}
	
	//TestRail#C47402 - Validate adding Multiple Gateways_Existing Enrollment group
	@Test
	public void C47402AddMultipleGatewayWithExistingEnrollmentGroup() {
		addMultipleGatewayExisting();
	}
	
	//TestRail#C47401 - Validate adding Multiple Gateways_New Enrollment group_Intermediate certificate
	@Test(enabled=false)
	public void C47401AddMultipleGatewayWithNewEnrollmentGroupWithIntermediateUploadCertificate() {
		addMultipleGatewayNewIntermediate();

	}
	 //TestRail#C47403 - Validate adding Multiple Gateways_ New Enrollment group Without  Enrollment Group Name
	@Test
	public void C47403AddMultipleGatewayWithNewEnrollmentGroupWithoutMandatoryFields() {
		addMultipleGatewayWithoutMandatoryFields();

	}
   //TestRail#C47406 - Validate adding Multiple Gateways _New Enrollment group_Invalid Gateway Details
	@Test
	public void C47406AddMultipleGatewayWithInvalidDetails() {
		addMultipleGatewayWithInvalid();

	}
	//TestRail#C47399 - Validate the user interface of Multiple Gateway page
	@Test
	public void C47399AddMultipleGatewayWithUserInterfaceValidation() {

		validatUserInterface();
	}
	//TestRail#C47399 - Validate the look and feel of Multiple Gateway page - , dependsOnMethods = {"addMultipleGatewayNewAllValidInputsRoot"}
	@Test
	public void C47399AddMultipleGatewayWithLookAndFeelValidation() {

		validateLookAndFeelMultipleGateway();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}

}
