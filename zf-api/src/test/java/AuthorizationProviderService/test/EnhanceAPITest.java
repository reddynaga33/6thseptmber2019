package AuthorizationProviderService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AuthorizationProviderService.pages.EnhanceAPIPage;

public class EnhanceAPITest extends EnhanceAPIPage {

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	

	@Test
	public void C63406VerifyNewlyCreatedRoleIdentifiedUsingAPIWithoutUsers() {
		String createANewRole = createANewRole("CreateNewRole","CreateNewRolePayloadWithOutUser");
		getUsersWithRoleCodeUsingNewAPI("GetUsersWithRoleCodeUsingNewAPI",createANewRole);
	}
	
	@Test
	public void C63284VerifyNewlyCreatedRoleIdentifiedUsingAPIWithUsers() {
		String createANewRoleName = createANewRole("CreateNewRole","CreateNewRolePayload");
		getUsersWithRoleCodeUsingNewAPI("GetUsersWithRoleCodeUsingNewAPI",createANewRoleName);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
