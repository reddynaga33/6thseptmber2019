package zf.api.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.JsonReader;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.ServiceManagementServiceMulticlientSuite.pages.IClientServicePage;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;
import zf.ServiceManagementServiceMulticlientSuite.test.IServiceDesciptorServiceNewTest;
import zf.api.pages.CUSPropertyPage;
import zf.api.pages.ClientPropertyPage;

public class CUSPropertyTest extends CUSPropertyPage{

	public Response createPropertyForCUSTrue=null;
	public Response updatePropertyForCUSTrue=null;
	public Response deletePropertyForCUSTrue=null;
	public Response createPropertyForCUSFalse=null;
	public Response updatePropertyForCUSFalse=null;
	public Response deletePropertyForCUSFalse=null;
	ClientPropertyPage clientpropertypage=new ClientPropertyPage();
	IServiceDesciptorServiceNewPage sdNewObject=new IServiceDesciptorServiceNewPage();
	IClientServicePage iClientServiceObject=new IClientServicePage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		clientpropertypage.configureServiceBus();
	}

	@Test(enabled=true)
	public void C63568VerifyUserIsAbleToCreateTheCUSPropertiesWithReconfigTrue()
	{
		String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscription,"true");
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty",SDName);
		String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}


	@Test(enabled=true)
	public void C63569VerifyUserIsAbleToGetCUSPropertiesWithReconfigTrue()
	{
		String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty",SDName);
		String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}


	@Test(enabled=true)
	public void C63570VerifyUserIsAbleToDeleteCUSConfigPropertiesTrue() {
		String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		deletePropertyForCUSTrue = deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","true",SDName);
		String responseValue = clientpropertypage.getResponseValue(deletePropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForDelete, "true");
		String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}

	@Test(enabled=true)
	public void C63571VerifyUserIsAbleToCreateTheCUSPropertiesWithReconfigFalse()
	{
		String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
		HashMap<String, Integer> configureTopicSubscriptionForConfigureFalse = clientpropertypage.configureTopicSubscription();
		createPropertyForCUSFalse=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false",SDName);
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSFalse,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForConfigureFalse, "false");
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty",SDName);
		String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}


	@Test(enabled=true)
	public void C63572VerifyUserIsAbleToDeleteCUSConfigPropertiesFalse() 
	{String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
	String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
	iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
	createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false",SDName);
	HashMap<String, Integer> configureTopicSubscriptionForConfigureFalseToDelete = clientpropertypage.configureTopicSubscription();
	deletePropertyForCUSFalse = deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","false",SDName);
	String responseValue = clientpropertypage.getResponseValue(deletePropertyForCUSFalse,"status");
	clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
	clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForConfigureFalseToDelete, "false");
	String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
	iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);

	}


	@Test(enabled=true)
	public void C63852VerifyUserIsAbleToGetHierarchyForCUS() {
		String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty",SDName);
		String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}	