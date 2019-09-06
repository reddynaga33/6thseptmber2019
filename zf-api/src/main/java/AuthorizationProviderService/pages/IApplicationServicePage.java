package AuthorizationProviderService.pages;

import framework.RestApiUtility;
import io.restassured.response.Response;

public class IApplicationServicePage extends RestApiUtility {
	public void VerifyListOfApplicationsReturnedFromGETapi(String ServiceName)
	{
			Response GetAppResponse=null;
			String AppId = null;
			try {	
				GetAppResponse=GetServices(ServiceName);
				if( GetAppResponse!=null && GetAppResponse.getStatusCode()==200) {
					AppId = GetAppResponse.getBody().asString();	
					testPassed("The list of Applications are : "+AppId);
				}
				else{
					testFailed("The list of Applications are : "+GetAppResponse.getStatusCode()+" and the details is:"+GetAppResponse.getBody().asString());
				}
			} catch(Exception e) {
				testFailed("An exception occured :"+e.getMessage());
			}	}
	
	public void VerifySpecificAppDetailsUsingGETapi(String ServiceName)
	{
		Response GetSpecificAppDetailsResponse=null;
		String SpecificAppId = null;
		try {	
			GetSpecificAppDetailsResponse=GetServices("GetSpecificAppDetailsUsingNewAPI");
			if( GetSpecificAppDetailsResponse!=null && GetSpecificAppDetailsResponse.getStatusCode()==200) {
				SpecificAppId = GetSpecificAppDetailsResponse.getBody().asString();	
				testPassed("The details of Specific Application are : "+SpecificAppId);
			}
			else{
				testFailed("The details of Specific Application are : "+GetSpecificAppDetailsResponse.getStatusCode()+" and the details is:"+GetSpecificAppDetailsResponse.getBody().asString());
			}
		} catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}
	
	public void VerifyNoApplDetailsWithWrongAppID(String ServiceName) {
		Response GetSpecificAppDetailsWithWrongIDResponse=null;
		String SpecificAppWithWrongId = null;
		try {	
			GetSpecificAppDetailsWithWrongIDResponse=GetServices("GetSpecificAppDetailsWithWrongAppID");
			if( GetSpecificAppDetailsWithWrongIDResponse!=null && GetSpecificAppDetailsWithWrongIDResponse.getStatusCode()==404) {
				SpecificAppWithWrongId = GetSpecificAppDetailsWithWrongIDResponse.getBody().asString();	
				testPassed("The details of Specific Application with Wrong AppID are : "+SpecificAppWithWrongId);
			}
			else{
				testFailed("The details of Specific Application with Wrong AppID are : "+GetSpecificAppDetailsWithWrongIDResponse.getStatusCode()+" and the details is:"+GetSpecificAppDetailsWithWrongIDResponse.getBody().asString());
			}
		} catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}

	
	public void verifySpecificAppDetailsUsingCorrectAppId(String ServiceName) {
		Response GetAppDetailsWithAppIDResponse=null;
		String AppDetailsWithAppId = null;
		try {	
			GetAppDetailsWithAppIDResponse=GetServices("GetSpecificAppDetailsWithAppID");
			if( GetAppDetailsWithAppIDResponse!=null && GetAppDetailsWithAppIDResponse.getStatusCode()==200) {
				AppDetailsWithAppId = GetAppDetailsWithAppIDResponse.getBody().asString();	
				testPassed("The details of Specific Application with Wrong AppID are : "+AppDetailsWithAppId);
			}
			else{
				testFailed("The details of Specific Application with Wrong AppID are : "+GetAppDetailsWithAppIDResponse.getStatusCode()+" and the details is:"+GetAppDetailsWithAppIDResponse.getBody().asString());
			}
		} catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}
	
//	/C64330 - Verify specific application details should not be returned when send the invalid appId to the application API
	public void VerifyInvalidAppIdToApplicationId(String ServiceName) {
		Response GetinvalidappIdResponse=null;
		String RoleId = null;
		try {	
			GetinvalidappIdResponse=GetServices("InvalidAppIdToApplicationApi");
			if( GetinvalidappIdResponse!=null && GetinvalidappIdResponse.getStatusCode()==404) {
				RoleId = GetinvalidappIdResponse.getBody().asString();	
				testPassed("The invalid appId to the application API details: "+RoleId);
			}
			else {
				testFailed("The created  invalid appId to the application API details are : "+GetinvalidappIdResponse.getStatusCode()+" and the details is:"+GetinvalidappIdResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}
	
	
//	C64331-Verify specific application details should not be returned when do not send the appId to the application API
	public void VerifyNoAppidToApplication(String ServiceName) {
		Response GetNoApplicationDetailsResponse=null;
		String AppId = null;
		try {	
			GetNoApplicationDetailsResponse=GetServices("NoAppIdToApplicationApiDetails");
			if( GetNoApplicationDetailsResponse!=null && GetNoApplicationDetailsResponse.getStatusCode()==404) {
				AppId = GetNoApplicationDetailsResponse.getBody().asString();	
				testPassed("The No appId to the application API details: "+AppId);
			}
			else {
				testFailed("The created  with no appId to the application API details are : "+GetNoApplicationDetailsResponse.getStatusCode()+" and the details is:"+GetNoApplicationDetailsResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}
	//C64332-Verify specific application details should be returned as API response when send the appId to the application API
	public void VerifyWithvalidIdExtRepose(String ServiceName) {
		Response GetApplicationDetailsResponse=null;
		String RoleId = null;
		try {	
			GetApplicationDetailsResponse=GetServices("AppIdToApplicationApi");
			if( GetApplicationDetailsResponse!=null && GetApplicationDetailsResponse.getStatusCode()==200) {
				RoleId = GetApplicationDetailsResponse.getBody().asString();	
				testPassed("The specific application details of appId to the application API details: "+RoleId);
			}
			else {
				testFailed("The created   appId to the application API details are : "+GetApplicationDetailsResponse.getStatusCode()+" and the details is:"+GetApplicationDetailsResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception occured :"+e.getMessage());
		}	}
	
//C64333 Verify specific application details should not be returned when send the invalid idExt to the application API
	public void VerifyWithInvalidIdExtRepose(String ServiceName) {
	Response GetinvalididExtResponse=null;
	String RoleId = null;
	try {	
		GetinvalididExtResponse=GetServices("InvalidIdExtApplicationApi");
		if( GetinvalididExtResponse!=null && GetinvalididExtResponse.getStatusCode()==404) {
			RoleId = GetinvalididExtResponse.getBody().asString();	
			testPassed("The invalid idExt appId to the application API details: "+RoleId);
		}
		else {
			testFailed("The created  invalid idExt appId to the application API details are : "+GetinvalididExtResponse.getStatusCode()+" and the details is:"+GetinvalididExtResponse.getBody().asString());
		}
	}catch(Exception e) {
		testFailed("An exception occured :"+e.getMessage());
	}	}

}
