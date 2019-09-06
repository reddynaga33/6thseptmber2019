package zf.ServiceManagementServiceMulticlientSuite.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.ServiceManagementServiceMulticlientSuite.pages.IClientServicePage;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;
import zf.api.pages.CUSPropertyPage;
import zf.api.pages.ClientPropertyPage;

public class ExtendConfigurationAPIncorporateRequestChangesTest extends ClientPropertyPage{

	
	public Response createPropertyForClientTrue =null;
	public Response updatePropertyForClientTrue =null;
	public Response deletePropertyForClientTrue =null;
	public Response createPropertyForClientFalse=null;
	public Response updatePropertyForClientFalse=null;
	public Response deletePropertyClientConfigFalse=null;
	String deletePropertyClientConfigTrue =null;

	public Response createPropertyForCUSTrue=null;
	public Response updatePropertyForCUSTrue=null;
	public Response deletePropertyForCUSTrue=null;
	public Response createPropertyForCUSFalse=null;
	public Response updatePropertyForCUSFalse=null;
	public Response deletePropertyForCUSFalse=null;
	ClientPropertyPage clientpropertypage=new ClientPropertyPage();
	IServiceDesciptorServiceNewPage sdNewObject=new IServiceDesciptorServiceNewPage();
	IClientServicePage iClientServiceObject=new IClientServicePage();
	CUSPropertyPage CUSPropertyPageObject=new CUSPropertyPage();


	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		configureServiceBus();
	}
	@Test
	public void C63563VerifyUserIsAbleToCreatePropertyForClientTrue()
	{
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription();
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		String responseValue = getResponseValue(createPropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscription,"true");
		getClientAndCUSProperty("GetClientProperty");

	}
	@Test
	public void C63564VerifyUserIsAbleToGetTheCreatedConfigProeprtiesOfClientTrue() {
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		String responseValue = getResponseValue(createPropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		getClientAndCUSProperty("GetClientProperty");
	}

	@Test
	public void C63565VerifyUserIsAbleToDeleteTheClientConfigPropertiesTrue() {
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		HashMap<String, Integer> configureTopicSubscriptionForDelete = configureTopicSubscription();
		deletePropertyForClientTrue = deletePropertyClientConfig("DeletePropertyClientConfigProperty","true");
		String responseValue = getResponseValue(deletePropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionForDelete, "true");
		getDeleteClientAndCUSProperty("GetClientProperty");
	}
	@Test
	public void C63566VerifyUserIsAbleToCreateClientConfigPropertiesFalse() {
		HashMap<String, Integer> configureTopicSubscriptionConfFlase = configureTopicSubscription();
		createPropertyForClientFalse=createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		String responseValue = getResponseValue(createPropertyForClientFalse,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionConfFlase, "false");
		getClientAndCUSProperty("GetClientProperty");
	}
	
	@Test
	public void C63567VerifyUserIsAbleToDeleteTheClientPropertiesWithReconfigFalse() {
		createPropertyForClientFalse=createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		HashMap<String, Integer> configureTopicSubscriptionConfFlase = configureTopicSubscription();
		deletePropertyClientConfigFalse = deletePropertyClientConfig("DeletePropertyClientConfigProperty","false");
		String responseValue = getResponseValue(deletePropertyClientConfigFalse,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionConfFlase, "false");
		getDeleteClientAndCUSProperty("GetClientProperty");
	}
	
	@Test
	public void C63853VerifyUserIsAbleToGetHierarchyForClient()
	{
	
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		String responseValue = getResponseValue(createPropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		getClientAndCUSProperty("GetClientProperty");
	getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		
		}
	
	
	@Test(enabled=true)
	public void C63568VerifyUserIsAbleToCreateTheCUSPropertiesWithReconfigTrue()
	{
		String SDName = sdNewObject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = iClientServiceObject.assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",sagaStatus);
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForCUSTrue=CUSPropertyPageObject.createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
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
		createPropertyForCUSTrue=CUSPropertyPageObject.createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
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
		createPropertyForCUSTrue=CUSPropertyPageObject.createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		deletePropertyForCUSTrue = CUSPropertyPageObject.deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","true",SDName);
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
		createPropertyForCUSFalse=CUSPropertyPageObject.createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false",SDName);
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
	createPropertyForCUSTrue=CUSPropertyPageObject.createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false",SDName);
	HashMap<String, Integer> configureTopicSubscriptionForConfigureFalseToDelete = clientpropertypage.configureTopicSubscription();
	deletePropertyForCUSFalse = CUSPropertyPageObject.deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","false",SDName);
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
		createPropertyForCUSTrue=CUSPropertyPageObject.createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true",SDName);
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty",SDName);
		String unAssigningSDClientSagaStatus = iClientServiceObject.unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		iClientServiceObject.getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}
}
