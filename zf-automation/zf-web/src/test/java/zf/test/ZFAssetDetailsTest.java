package zf.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.pages.ZFAssetDetailsPage;

public class ZFAssetDetailsTest extends ZFAssetDetailsPage {

	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		
	}
	
	
	@Test
	public void C63951OperatorHasNoAccessToEditAssetDetails() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		OperatorHasNoAccessToEditAssetDetails();
	}
	
	@Test
	public void C63955OperatorHasNoAccessToCreateChildAssets() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		OperatorHasNoAccessToCreateChildAssets();
	}
	
	@Test
	public void C63956OperatorHasNoAccessToEditActions() {
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		OperatorHasNoAccessToEditActions();
	}
	
	@Test
	public void C63958operatorAccessToViewAssetDetails()
	{
		microsoftlogin.microsoftLogin(EnvironmentManager.getOperatorUserName(),EnvironmentManager.getOperatorPassword());
		operatorAccessToViewAssetDetails();
	}
	//TestRail#C48962 - View the details of existing assets
		@Test
		public void C48962ViewExistingAssetDetails(){
			microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
			viewExistingAssetDetails();
		}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}
