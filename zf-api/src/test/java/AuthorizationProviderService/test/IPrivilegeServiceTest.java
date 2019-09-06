package AuthorizationProviderService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AuthorizationProviderService.pages.IPrivilegeServicePage;

public class IPrivilegeServiceTest extends IPrivilegeServicePage{
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	

	@Test
	public void C64349GetListAllAvailabePrivilage() {
		getListAllAvailabePrivilage("getAvailablePrivilage");
	}
	
	
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
