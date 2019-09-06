package zf.ClientManagementServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.ClientManagementServiceSuite.pages.IUserGroupServicePage;

public class IUserGroupServiceTest extends IUserGroupServicePage{

	String UserGroupId = null;
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	

	public void UserGroup() {
		UserGroups();
	}
	@Test
	public void C62923VerifyUserIsAbleToCreateUserGroup() {
		UserGroupId=VerifyUserIsAbleToCreateUserGroup();
		getGroupDetails("GetUserGroupDetails",UserGroupId,"false","false");
		getGroupDetails("GetUserGroupDetails",UserGroupId,"true","false");
		getGroupDetails("GetUserGroupDetails",UserGroupId,"true","true");
	}
	@Test
	public void C62924VerifyUserIsAbleToUpdateThUserGroup() {
		UserGroupId=VerifyUserIsAbleToCreateUserGroup();
		VerifyUserIsAbleToUpdateUserGroup(UserGroupId);
	}

	public void DeleteUserGroup() {
		DeleteUserGroupId(UserGroupId);
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());

	}
}
