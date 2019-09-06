package zf.ClientManagementServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.ClientManagementServiceSuite.pages.IClientServicePage;

public class IClientServiceTest extends IClientServicePage{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		
		startTest(testName.getName());
		
	}
	@Test 
	public void C62901VerifyCreateIClient() {
		String createdClientID = CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		getClientDetails("GetClientsDetails",createdClientID);
		}
	
	@Test 
	public void C62905VerifyCreateIClient() {
		String createdClientID = CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		getOneClientDetails("GetOneClientDetails",createdClientID);
		}
	
	@Test 
	public void C62903VerifyGetIClientDetails() {
		String createdClientID = CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		getClientDetailsWithExtendedInfo("GetClientExtendedDetails",createdClientID,"false");
		getClientDetailsWithExtendedInfo("GetClientExtendedDetails",createdClientID,"true");
		getClientDetailsWithExtendedInfo("GetClientExtendedDetails","19309c32-1769-42ba-9829-d18d5e9e072d","true");
	
		}

	@Test 
	public void  C62904EditIClientDetails() {
		String createdClientID = CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		editClientDetails("EditIClientDetails",createdClientID,"EditIClientDetailsPayload");
		getOneClientDetails("GetOneClientDetails",createdClientID);
		}
	
	

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());

	}


}
