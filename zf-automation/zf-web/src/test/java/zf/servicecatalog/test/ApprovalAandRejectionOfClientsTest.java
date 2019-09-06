package zf.servicecatalog.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.servicecatalog.pages.ApprovalAandRejectionOfClientsPage;

public class ApprovalAandRejectionOfClientsTest extends ApprovalAandRejectionOfClientsPage {
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("SCOTAURI"));
		microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentProperties("PRIVILEGEMULTICLIENTSUSERNAME"),EnvironmentManager.getEnvironmentProperties("PRIVILEGEMULTICLIENTSPASSWORD"));
	}
	
	@Test
	public void C59980SCOApprovingClientRequestedByUser() {
		SCOApprovingClientRequestedByUser();
	}
	
	@Test
	public void C60020SCORejectClientRequestedByUser() {
		SCORejectingClientRequestedByUser();
	}
	
	@Test
	public void C60021ServiceCatalogOwnerCancelingTheRejectOfTheClient() {
		ServiceCatalogOwnerCancelingTheRejectOfTheClient();
	}
	
	@Test
	public void C60023SCORejectingTheClientRequestedByUserWithoutAddingNotes() {
		SCORejectingTheClientRequestedByUserWithoutAddingNotes();
	}
	
	@Test
	public void C60024ViewingTheDetailsOfClient() {
		
		ViewingTheDetailsOfClient();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
//		DriverManager.closeAllBrowser();
	}
}
