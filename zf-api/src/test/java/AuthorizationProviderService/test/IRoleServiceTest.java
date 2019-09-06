package AuthorizationProviderService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AuthorizationProviderService.pages.EnhanceAPIPage;
import AuthorizationProviderService.pages.IRoleServicePage;
import io.restassured.response.Response;

public class IRoleServiceTest extends IRoleServicePage{
	EnhanceAPIPage enhanceAPIObject=new EnhanceAPIPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
@Test
public void C64359GetClientRoles() {
	
	GetClientRoles("GetClientRoles");
}

@Test
public void C64360GetClientRoleIdDetails() {
	Response createANewRole = enhanceAPIObject.createNewRoleWithResponse("CreateNewRole","CreateNewRolePayloadWithOutUser");
	String responseValue = getResponseValue(createANewRole, "id");
	GetClientRoleDetails("GetClientRoleIdDetails",responseValue);
}

@Test
public void C64361getRoles() {

	Response createANewRole = enhanceAPIObject.createNewRoleWithResponse("CreateNewRole","CreateNewRolePayloadWithOutUser");
	String responseValue = getResponseValue(createANewRole, "name");
	GetClientRoles("GetClientRoles");
	validateRoleIDINDB(responseValue,"SelectAPSRoleNameDetails");
}


@Test
public void C64362GetRolesDetails() {
	Response createANewRole = enhanceAPIObject.createNewRoleWithResponse("CreateNewRole","CreateNewRolePayloadWithOutUser");
	String responseValue = getResponseValue(createANewRole, "id");
	GetClientRoleDetails("GetClientRoleIdDetails",responseValue);
	validateRoleIDINDB(responseValue,"SelectAPSDetails");
}

@AfterMethod
public void afterMethod(ITestResult result,Method testName)
{
	getResult(result,testName.getName());
}
}
