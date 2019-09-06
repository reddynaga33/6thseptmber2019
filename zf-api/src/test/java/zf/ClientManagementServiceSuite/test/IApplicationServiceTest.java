package zf.ClientManagementServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.ClientManagementServiceSuite.pages.IApplicationServicePage;
import zf.ClientManagementServiceSuite.pages.IClientServicePage;

public class IApplicationServiceTest  extends IApplicationServicePage {
	IClientServicePage clientServicePageObject=new IClientServicePage();
	@BeforeMethod
	public void beforeMethod(Method testName) {
		
		startTest(testName.getName());
		
	}
	@Test 
	public void C62895VerifyGetExtendedApplicationDetails() {
		getExtendedApplicationDetails("GetApplicationExtendedDetails","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6","false");
		getExtendedApplicationDetails("GetApplicationExtendedDetails","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6","true");
		}
	
	@Test 
	public void C62896VerifyGetClientDetailsOfApplication() {
		getClientDetailsOfApplication("GetClientDetailsOfApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6");
				}
	
	@Test 
	public void C62897UnassignApplicationClient() {
		String createdClientID = clientServicePageObject.CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		assignApplicationToClient("AssignApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6",createdClientID);
		unassignApplicationToClient("UnassignApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6",createdClientID);
		Response clientDetailsOfApplication = getClientDetailsOfApplication("GetClientDetailsOfApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6");
		validateClientPresent(clientDetailsOfApplication,createdClientID);
		
		}
	
	@Test 
	public void C62898GetAssignApplicationClient() {
		String createdClientID = clientServicePageObject.CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		assignApplicationToClient("AssignApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6",createdClientID);
		getApplicationClientDetails("GetClientApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6",createdClientID);
		
		}
	
	@Test 
	public void C62899AssignApplicationToClient() {
		String createdClientID = clientServicePageObject.CreateClient("CreateIClientDetails","CreateIClientDetailsPayload");
		assignApplicationToClient("AssignApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6",createdClientID);
		getApplicationClientDetails("GetClientApplication","896f3a33-66f1-40ae-9de0-1bd9fc3f61f6",createdClientID);
		
		}
	
	


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());

	}
}
