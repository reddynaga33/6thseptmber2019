package zf.regression.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.api.pages.CUSPropertyPage;
import zf.api.pages.ClientPropertyPage;
import zf.pages.PasswordEncryptDecrypt;
import zf.regression.pages.SMSMCPage;

public class SMSMCTest extends SMSMCPage{
	ClientPropertyPage clientpropertypage=new ClientPropertyPage();
	CUSPropertyPage clientUserServicepropertypage=new CUSPropertyPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		
		startTest(testName.getName());
			
	}



	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
