package zf.ServiceManagementServiceMulticlientSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDescriptorServicePageOLD;
import zf.regression.pages.SMSMCPage;

public class IServiceDescriptorServiceTestOLD extends IServiceDescriptorServicePageOLD {
	String ServiceId=null;
	SMSMCPage smsmcpageObject=new SMSMCPage();
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

@Test
	public void C62156VerifyUserIsAbleToGetAllTheServices() {
		ServiceId=VerifyUserIsAbleToGetAllTheServices();
	}
@Test
	public void C62157VerifyUserIsAbleToGetOneServiceWihCorrectServiceDescriptorID() {
		VerifyUserIsAbleToGetOneServiceWihCorrectServiceDescriptorID("02f66f5b-03ee-433e-a50a-933ff93ba360");
	}
@Test
	public void C62158VerifyUserIsAbleToGetOneServiceWihwrongServiceDescriptorID() {
		VerifyUserIsAbleToGetOneServiceWihwrongServiceDescriptorID("00c3193c-3bb0-49bb-b142-9ed6163b0422");
	}

	//New
	@Test
	public void C62813VerifyCreateServiceDescriptorWithWrongUUID() {
		smsmcpageObject.createServiceDescriptorWithWrongID("CreateServiceDescriptor","CreateServiceDescriptorWrongID");
	}

@Test
	public void C62814VerifyCreateServiceDescriptorWithNoUUID() {
		CreateServiceDescriptorWithNoUUID("CreateServiceDescriptorWithNoUUID");
	}
	
@Test
	public String C62122CreatedServiceDescriptorIDwithCorrectID() {
		
		String createSDIDResponse = createServiceDescriptorResponse("CreateServiceDescriptor","CreateServiceDescriptor");
		getSagaStatus("GetSagaEntity",createSDIDResponse);
		return getServiceDescriptorID("GetServiceDescriptor");
	}
	
//	@Test  ServiceDescriptor -Old
	public void C62154CreatedServiceDescriptorIDwithWrongID() {
		
	createServiceDescriptorWithWrongID("CreateServiceDescriptor","CreateServiceDescriptorWrongID");
		
	}
	
	
//@Test ServiceDescriptor -Old
	public void C62815CreateServiceDescriptorWithNoName() {
		createServiceDescriptorWithNoName("CreateServiceDescriptor","createServiceDescriptorWithNoName");
	}
	
//	@Test ServiceDescriptor -Old
	public void C62816CreateServiceDescriptorWithDuplicateArtifactID() {
		createServiceDescriptorDefaultvalue("CreateServiceDescriptor","CreateServiceDescriptor");
		String createSDIDSagaStatus = createServiceDescriptorWithDuplicateArtifactID("CreateServiceDescriptor","CreateServiceDescriptor");
		getSagaStatus("GetSagaEntity",createSDIDSagaStatus);
		getServiceDescriptorIDForWrongDetails("GetServiceDescriptor");
		
	}
	
//	@Test ServiceDescriptor -Old
	public void C62817CreateServiceDescriptorWithDuplicateName() {
		createServiceDescriptorWithDuplicateName("CreateServiceDescriptor","CreateServiceDescriptor");
		String createSDIDSagaStatus=createServiceDescriptorWithDuplicateName("CreateServiceDescriptor","CreateServiceDescriptor");
		getSagaStatus("GetSagaEntity",createSDIDSagaStatus);
		getServiceDescriptorIDForWrongDetails("GetServiceDescriptor");
	}
	
	@Test
	public void C62818CreateServiceDescriptorWithUUIDResuse() {
		createServiceDescriptorWithDuplicateName("CreateServiceDescriptor","CreateServiceDescriptor");
		String createSDIDSagaStatus=serviceDescriptorWithDuplicateName("CreateServiceDescriptor","CreateServiceDescriptor");
		getSagaStatus("GetSagaEntity",createSDIDSagaStatus);
		getServiceDescriptorID("GetServiceDescriptor");
	}
	
	@Test
	public void C62821GetServiceWithWrongServiceDescriptorID() {
		createServiceDescriptorWithDuplicateName("CreateServiceDescriptor","CreateServiceDescriptor");
		String createSDIDSagaStatus=createServiceDescriptorWithDuplicateName("CreateServiceDescriptor","CreateServiceDescriptor");
		getSagaStatus("GetSagaEntity",createSDIDSagaStatus);
		getServiceDescriptorIDForWrongDetails("GetServiceDescriptor");
	}
	
	
	@Test
	public void C62158getWrongServiceDescriptorIDDetails() {
		
		getWrongServiceDescriptorIDDetails("GetServiceDescriptor");
	}
	
//	@Test
//	public void C61764DestinationChangeEventContainAllAssignedDestinations() {
//		configureServiceBus();
//		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription();
//		VerifyRequestToGlobalInfoServicestartedMC();
//		DeleteTopicSubscription(configureTopicSubscription);
//	}
	
	@Test
	public void C62812CreateSDWithCorrectUUID()
	{
		Response res=createServDescWithCorrectUUID("CreateServDescWithCorrectUUID","JSONCreateSDWithCorrectUUID");
		String responseValue = getResponseValue(res,"status");
		getSDWithCorrectUUID("GetServDescWithCorrectUUID",responseValue);
		getValidateSDWithCorrectUUID("GetValidateServDescWithCorrectUUID");
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
