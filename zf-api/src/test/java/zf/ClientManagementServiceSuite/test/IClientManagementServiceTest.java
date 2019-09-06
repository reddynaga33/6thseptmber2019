package zf.ClientManagementServiceSuite.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.ClientManagementServiceSuite.pages.IClientManagementServicePage;

public class IClientManagementServiceTest extends IClientManagementServicePage{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test
	public void C62925GetRequestForClientContextClientIdClients() {
		GetRequestForClientContextClientIdClients();
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
