package zf.ClientManagementServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.ClientManagementServiceSuite.pages.IUserServicePage;

public class IUserServiceTest extends IUserServicePage {
	String CreatedUserId=null;

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void C62906VerifyUserIsAbleToGetTheUserDetails() {
		VerifyUserIsAbleToGetTheUserDetails();
	}
	@Test 
	public void C62907VerifyUserIsAbleToGetTheUserDetailsOfaClient() {
		VerifyUserIsAbleToGetTheUserDetailsOfaClient();
	}

	@Test
	public void C62908VerifyUserIsAbleToCreateUser() {
		CreatedUserId=VerifyUserIsAbleToCreateUser();
	}
	@Test
	public void C62909VerrifyUserIsAbleToDeleteTheUser() {
		CreatedUserId=VerifyUserIsAbleToCreateUser();
		VerrifyUserIsAbleToDeleteTheUser(CreatedUserId);
	}
	@Test
	public void C62910VerifyUserIsAbleToGetSingleUserDetails() {
		CreatedUserId=VerifyUserIsAbleToCreateUser();
		VerifyUserIsAbleToGetSingleUserDetails(CreatedUserId);
	}
	@Test
	public void C62911VerifyUserIsAbleToUpdateTheUserDetails() {
		CreatedUserId=VerifyUserIsAbleToCreateUser();
		VerifyUserIsAbleToUpdateTheUserDetails(CreatedUserId);
	}
	@Test
	public void C62912VerifyUserIsAbleToGetTheClientDetailsAssociatedToTheUserId() {
		VerifyUserIsAbleToGetTheClientDetailsAssociatedToTheUserId(CreatedUserId);
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}