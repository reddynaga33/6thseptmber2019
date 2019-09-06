package zf.platform.regression2.test;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.JsonReader;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;
import zf.platform.regression2.pages.SMSMCISagaServicePage;

public class SMSMCISagaServiceTest extends SMSMCISagaServicePage {
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
		VerifyTheSagaEntityForServiceDescriptorWithCorrectSagaId(getSagaResponse,SagaID);
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
		VerifyTheSagaEntityForServiceDescriptorWithWrongSagaId(getSagaResponse);
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
		VerifyTheSagaEntityForServiceDescriptorWithNoSagaId(getSagaResponse,SagaID);
	}
}