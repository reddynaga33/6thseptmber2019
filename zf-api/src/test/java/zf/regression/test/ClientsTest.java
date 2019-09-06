package zf.regression.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.regression.pages.ClientsPage;

public class ClientsTest extends ClientsPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	String ClientName=null;
	String SubClientID=null;
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
@Test
	public void TC01AddSubClient() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
//		microsoftlogin.microsoftLogin(EnvironmentManager.getPrivilegeofSubClientCreationAdminUserName(),EnvironmentManager.getPrivilegeofSubClientCreationAdminPassword());
		ClientName = addSubClient();
	
	}
	
@Test
	public void TC02VerifyTheNewlyCreatedSubClientIntoApplicationDB() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		ClientName = addSubClient();
		SubClientID=VerifyTheSubClientIntoApplicationDB(ClientName);
	
	}
	
	@Test
	public void TC03VerifyTheNewlyCreatedSubClientInformationFromAPI() {
		VerifyTheNewlyCreatedSubClientInformationFromAPI(SubClientID);
	}
	
	@Test
	public void TC04VerifyUserCanEditTheSubClient() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getPrivilegeofSubClientCreationAdminUserName(),EnvironmentManager.getPrivilegeofSubClientCreationAdminPassword());
		VerifyUserCanEditTheSubClient();
	
	}
	
@Test
	public void TC05VerifyUserCanUpdateSubclientCreatedByBackofficeUI() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getPrivilegeofSubClientCreationAdminUserName(),EnvironmentManager.getPrivilegeofSubClientCreationAdminPassword());
		VerifyUserCanUpdateSubclientCreatedByBackofficeUI();
	
	}
	
@Test
	public void TC06VerifyTheUpdatedSubClientInformationIntoApplicationDB() {
		String clientID = VerifyTheSubClientIntoApplicationDB(ClientName);
//		VerifyDeleteSubClientInformationFromAPI("client",clientID);
		
	}
	
@Test
	public void TC07VerifyTheUpdatedSubClientInformationIntoAPI() {
		String verifyTheSubClientIntoApplicationDB = VerifyTheSubClientIntoApplicationDB(ClientName);
		VerifyTheNewlyCreatedSubClientInformationFromAPI(verifyTheSubClientIntoApplicationDB);
		}
	
@Test
	public void TC08VerifyTheUpdatedSubClientInformationIntoApplicationDB() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getPrivilegeofSubClientCreationAdminUserName(),EnvironmentManager.getPrivilegeofSubClientCreationAdminPassword());
		String subClientName = addSubClient();
		String SubClientIDFromDB = VerifyTheSubClientIntoApplicationDB(subClientName);
		updateSubclientInfoInAPI("EditClientWithAPI","EditClientWithAPI",SubClientIDFromDB);
	
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}