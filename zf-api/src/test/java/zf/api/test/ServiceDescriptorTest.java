package zf.api.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.api.pages.ServiceDescriptorPage;

public class ServiceDescriptorTest extends ServiceDescriptorPage{

	public String SagaStatusWithoutAppid=null;
	public String SagaStatusWithAppid=null;
//	IServiceDesciptorServiceNewTest iSDserviceObject
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

//    @Test
	public void TC1GetServiceDescriptor() throws InterruptedException {
//		String SDName = C63028VerifyServiceDescriptorIsCreated();
    	Response serviceDescriptor = getServiceDescriptor();
    	String returnSDidwithoutAppid = returnSDidwithoutAppid(serviceDescriptor);
    	validateSDidinDB(returnSDidwithoutAppid);
	}

//	@Test
	public void TC2AssignServiceDescriptorToCLientWithoutAppId() throws InterruptedException {
		SagaStatusWithoutAppid=assignServiceDescriptorToCLientWithoutAppId();
		validateAssigningSagaStatusInDB(SagaStatusWithoutAppid);
	}
//	@Test
	public void TC3GetSagaStatusClientWithoutAppId() throws InterruptedException {
		Response SagaResponse=getSagaStatusCLientWithoutAppId(SagaStatusWithoutAppid);
		validateSagaStatusCLientStatusInDB(SagaResponse);

	}

//	@Test
	public void TC4AssigningServiceDescriptorWithAppidToTheClient()
	{
		SagaStatusWithAppid=AssigningServiceDescriptorWithAppidToTheClient();
		validateAssigningSagaStatusInDB(SagaStatusWithAppid);

	}

//	@Test
	public void TC5GetSagaStatusClientWithAppId() throws InterruptedException {
		
		Response SagaResponse=getSagaStatusCLientWithAppId(SagaStatusWithAppid);
		validateSagaStatusCLientStatusInDB(SagaResponse);

	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}