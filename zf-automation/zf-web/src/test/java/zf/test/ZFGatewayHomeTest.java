package zf.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.pages.ZFGatewayHomePage;

public class ZFGatewayHomeTest extends ZFGatewayHomePage
{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	ZFAddSingleGatewayTest zfaddsinglegateway=new ZFAddSingleGatewayTest();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
	}

	@Test
	public void C63913OperatorHasNoAccessToAddGateway() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		operatorHasNoAccessToAddGateway();
	}
	@Test
	public void C63914OperatorNoAccessToEdit() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		verifyOperatorHasNoAccessToEditGateway();
	}
	@Test
	public void C63937OperatorNoAccessToDisable() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		verifyOperatorHasNoAccessToDisableGateway();
	}
	@Test
	public void C63983OperatorNoAccessToDecommission() {
		
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		verifyOperatorHasNoAccessToDecommissionGateway();
	}

	@Test
	public void C48361ManageSoftwareInterface() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyManageSoftwareInterface();
	}
	@Test
	public void AAAC63948DisableAndEnableGateway() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		disableAndEnableGateway();
	}

	@Test
	public void C63954OperatorHasAccessToViewGateway()
	{		
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		operatorHasAccessToViewGateway();
	}
	@Test
	public void C48362UserInterfaceOfAddSoftware()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyUserInterfaceOfGatewayAddSoftware();
	}
	@Test
	public void C63953UserWithNoPrivilegeCannotAccessGatewayPage()

	{		
		microsoftlogin.microsoftLogin(EnvironmentManager.getNoPrivilegeUserName(),EnvironmentManager.getNoPrivilegePassword());
		UserWithNoPrivilegeCannotAccessGatewayPage();
	}


	@Test
	public void C48364ManageSoftwareUploadAndInstallInvalidSoftwareName()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		ManageSoftwareUploadAndInstallInvalidSoftwareName();
	}

	@Test
	public void C48365ManageSoftwareUploadAndInstallInvalidSoftwarePackage()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		ManageSoftwareUploadAndInstallInvalidSoftwarePackage();
	}

	@Test
	public void C48363ManageSoftwareAddSoftwareUploadInstallValidUserInputs()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		ManageSoftwareAddSoftwareUploadInstallValidUserInputs();
	}

	@Test
	public void C47485ValidateCommissionedGatewayStatus()
	{		
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		validateCommissionedGatewayStatus();
	}
	@Test
	public void C47485ValidateDecommissionedGatewayStatus()
	{		
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		validateDecommissionedGatewayStatus();
	}

	@Test
	public void C47485ValidatePrecommissionedGatewayStatus()
	{		
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		validatePreCommissionedGatewayStatus();
	}
	@Test
	public void C63957verifyDefaultGatewayStatus()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyDefaultGatewayStatus();
	}

	@Test
	public void C63959VerifyClearSelectedOptionsInGatewayStatusSearchDropdown()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyClearSelectedOptionsInGatewayStatusSearchDropdown();
	}
	
	@Test
	public void C63962VerifySelectAllOptionInGatewayStatusSearchDropdown()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifySelectAllOptionInGatewayStatusSearchDropdown();
	}
	@Test
	public void C63968VerifyClearDatesFromDatePicker()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyClearDatesFromDatePicker();
	}

	@Test
	public void C63970VerifySearchOptionsSearchDropdown()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifySearchOptionsSearchDropdown();
	}


	@Test(enabled=false)
	public void C63972verifySearchGatewayDateRange()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		zfaddsinglegateway.addSingleGatewayWithAllValidInputs();
		verifySearchGatewayDateRange();
	}

	@Test
	public void C63973EditSingleGateway() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		// zfaddsinglegateway.addSingleGatewayWithAllValidInputs();
		editSingleGateway();
	}

	/***** TC03DropdownGateway : verifySearchUsingAParticularSearchCategoryFromDropdown *****/
	@Test
	public void C63977DropdownGateway() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		showDropDownGateway();//need to write a script for this 
	}

	/***** TC04GatewaySorTCatagory : verifySortUsingAParticularSorTCategory *****/
	@Test
	public void C63978GatewaySortCatagory() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		getSortCategories();
	}

	/***** TC05VerifyGatewayColumn :  Validate the column names of gateway list section *****/
	@Test
	public void C47482VerifyGatewayColumn() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyGatewayColumn();
	}

	/***** TC06VerifyGatewayLifeCycle :  Validate the Gateway life cycle in the gateway landing page *****/
	// @Test(priority=6)
	public void C47483VerifyGatewayLifeCycle() {   	
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		verifyGatewayLifeCycle();    	
	} 


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}

}
