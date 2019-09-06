package zf.ServiceManagementServiceMulticlientSuite.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.JsonReader;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.ServiceManagementServiceMulticlientSuite.pages.ISagaServicePage;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;
import zf.platform.regression2.pages.SMSMCISagaServicePage;

public class ISagaServiceTest extends ISagaServicePage{
	SMSMCISagaServicePage SagaPage=new SMSMCISagaServicePage();
	IServiceDesciptorServiceNewPage SDService=new IServiceDesciptorServiceNewPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void C62838VerifyTheSagaEntityForServiceDescriptorWithCorrectSagaId() {
		JSONObject SDNameJson = null;
		SDNameJson=JsonReader.getJsonObject("SDName");			
		SDNameJson.put("name",SDNameJson.get("name").toString()+getRandomNumber(1,9999));
		String SDName=SDNameJson.getAsString("name");
		Response ServiceDescriptorResponse = SDService.VerifyServiceDescriptorIsCreated(SDName);
		SDService.ValidateResponseCode202(ServiceDescriptorResponse);
		String getSagaResponse = SDService.GetSagaResponse(ServiceDescriptorResponse);
		String SagaID=SDService.ReturnSagaID(getSagaResponse);
		SagaPage.VerifyTheSagaEntityForServiceDescriptorWithCorrectSagaId(getSagaResponse,SagaID);
	}
	@Test
	public void C62839VerifyTheSagaEntityForServiceDescriptorWithWrongSagaId() {
		JSONObject SDNameJson = null;
		SDNameJson=JsonReader.getJsonObject("SDName");			
		SDNameJson.put("name",SDNameJson.get("name").toString()+getRandomNumber(1,9999));
		String SDName=SDNameJson.getAsString("name");
		Response ServiceDescriptorResponse = SDService.VerifyServiceDescriptorIsCreated(SDName);
		SDService.ValidateResponseCode202(ServiceDescriptorResponse);
		String getSagaResponse = SDService.GetSagaResponse(ServiceDescriptorResponse);
		SagaPage.VerifyTheSagaEntityForServiceDescriptorWithWrongSagaId(getSagaResponse);
	}
	@Test
	public void C62840VerifyTheSagaEntityForServiceDescriptorWithNoSagaId() {
		JSONObject SDNameJson = null;
		SDNameJson=JsonReader.getJsonObject("SDName");			
		SDNameJson.put("name",SDNameJson.get("name").toString()+getRandomNumber(1,9999));
		String SDName=SDNameJson.getAsString("name");
		Response ServiceDescriptorResponse = SDService.VerifyServiceDescriptorIsCreated(SDName);
		SDService.ValidateResponseCode202(ServiceDescriptorResponse);
		String getSagaResponse = SDService.GetSagaResponse(ServiceDescriptorResponse);
		String SagaID=SDService.ReturnSagaID(getSagaResponse);
		SagaPage.VerifyTheSagaEntityForServiceDescriptorWithNoSagaId(getSagaResponse,SagaID);
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
