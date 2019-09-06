package AuthorizationProviderService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AuthorizationProviderService.pages.IGroupServicePage;

public class IGroupServiceTest extends IGroupServicePage{

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void AAC64335VerifyClientGroupDetails() {
		VerifyClientGroup("GetClientGroups");
	}
	
	@Test
	public void C64336VerifyClientSpecificGroupDetails() {
		VerifyClientSpecificGroup("GetClientSpecificGroup");
	}
	
	@Test
	public void C64337GetClientInvalidGroup() {
		VerifyClientInvalidGroup("GetClientInvalidGroup","test");
	}
	
	@Test
	public void C64339GetClientGroupIdExt() {
		GetClientGroupIdExt("GetClientGroupIdExt","3d0ce0ad-eafa-4976-a7f6-16220288ed0f");
	}
	
	@Test
	public void C64340GetClientGroupWithInvalidIdExt() {
		VerifyClientInvalidGroup("GetClientGroupIdExt","3d0ce0ad-eafa-4976-a7f6-16220288");
	}
	
	@Test
	public void C64344GetClientGroupWithRoleId() {
		GetClientGroupRole("GetClientGroupRole","1bf57795-5d16-44e0-b323-e964b4f66bcf");
	}
	
	@Test
	public void C64345GetClientGroupWithInValidRoleId() {
		VerifyClientInvalidGroup("GetClientGroupRole","3d0ce0ad-eafa-4976-a7f6-16220288");
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
	
}
