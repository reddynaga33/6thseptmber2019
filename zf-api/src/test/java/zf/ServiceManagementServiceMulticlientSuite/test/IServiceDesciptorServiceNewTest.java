package zf.ServiceManagementServiceMulticlientSuite.test;

import org.testng.annotations.Test;
import framework.JsonReader;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;

public class IServiceDesciptorServiceNewTest extends IServiceDesciptorServiceNewPage{

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	
	@Test 
	public void C63028VerifyServiceDescriptorIsCreated(){
		JSONObject SDNameJson = null;
		SDNameJson=JsonReader.getJsonObject("SDName");			
		SDNameJson.put("name",SDNameJson.get("name").toString()+getRandomNumber(1,9999));
		String SDName=SDNameJson.getAsString("name");
		Response ServiceDescriptorResponse = VerifyServiceDescriptorIsCreated(SDName);
		ValidateResponseCode202(ServiceDescriptorResponse);
		String getSagaResponse = GetSagaResponse(ServiceDescriptorResponse);
		VerifyServiceDescripterCreation(getSagaResponse);

	}
	
	@Test
	public void C63030VerifyUserIsAbleToGetTheDetailsOfSingleClient() {
		String SDName = VerifyServiceDescriptorIsCreated();
		Response getResponse = VerifyUserIsAbleToGetTheDetailsOfSingleClient(SDName);
		ValidateResponseCode200(getResponse);
	}
	
	
	@Test
	public void C63029VerifyUserIsAbleToGetTheServices() {
		String SDName = VerifyServiceDescriptorIsCreated();
		Response getResponse = VerifyUserIsAbleToGetTheServices();
		ValidateResponseCode200(getResponse);
		ResponsePerview(getResponse,SDName);
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
