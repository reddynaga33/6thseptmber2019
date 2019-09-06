package AuthorizationProviderService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AuthorizationProviderService.pages.IApplicationServicePage;

public class IApplicationServiceTest extends IApplicationServicePage{
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	//s 09Aug - C64074 - Verify the list of applications should be returned from the GET /clients/{client-id}/applications api 
	@Test
	public void AC64074VerifyListOfApplicationsReturnedFromGETapi() {
		VerifyListOfApplicationsReturnedFromGETapi("GetAppsUsingNewAPI");
	}
	
	//s 09Aug - C64075 - Verify the specific application details should be returned when send the valid application id to the api
	@Test
	public void C64075VerifySpecificAppDetailsUsingGETapi() {
		VerifySpecificAppDetailsUsingGETapi("GetSpecificAppDetailsUsingNewAPI");
	}
	
	//s 09Aug - C64076 - Verify the response code should be 404 and no application details should be returned when send the invalid application id to
	@Test
	public void C64076VerifyNoApplDetailsWithWrongAppID() {
		VerifyNoApplDetailsWithWrongAppID("GetSpecificAppDetailsWithWrongAppID");
	}
	
	//s 09Aug - C64329 - Verify specific application details should be returned as API response when send the appId to the application API
	@Test
	public void C64329VerifySpecificApplicationDetailsUsingAppId() {
		verifySpecificAppDetailsUsingCorrectAppId("GetSpecificAppDetailsWithAppID");
	}
	
	
	@Test
	public void C64330VerifyInvalidAppIdToApplicationId() {
		VerifyInvalidAppIdToApplicationId("InvalidAppIdToApplicationApi");
	}
	//C64331-Verify specific application details should not be returned when do not send the appId to the application API
	@Test
	public void C64331VerifyNoAppidToApplication() {
		VerifyNoAppidToApplication("NoAppIdToApplicationApiDetails");
	}
	
	//C64332-Verify specific application details should be returned as API response when send the appId to the application API
	@Test
	public void C64332VerifyWithvalidIdExtRepose() {
	VerifyWithvalidIdExtRepose("AppIdToApplicationApi");
	}
	//C64333-Verify specific application details should not be returned when send the invalid idExt to the application API
	@Test
	public void C64333VerifyWithInvalidIdExtRepose() {
	VerifyWithInvalidIdExtRepose("InvalidIdExtApplicationApi");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
