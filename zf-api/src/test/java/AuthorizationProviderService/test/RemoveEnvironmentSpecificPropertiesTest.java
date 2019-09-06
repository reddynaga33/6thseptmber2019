package AuthorizationProviderService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AuthorizationProviderService.pages.RemoveEnvironmentSpecificPropertiesPage;

public class RemoveEnvironmentSpecificPropertiesTest extends RemoveEnvironmentSpecificPropertiesPage {
	EnhanceAPITest enhanceAPITestObject=new EnhanceAPITest();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	
	@Test
	public void C63860GetUsersWithRoleCodeUsingNewAPI() {
		enhanceAPITestObject.C63284VerifyNewlyCreatedRoleIdentifiedUsingAPIWithUsers();
	}
	
@Test (priority=1 ,groups = { "Multitenancy13" })
public void C62238VerifyAPSHealthAPI() {
	VerifyHealthAPI("HealthCheck");
}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
