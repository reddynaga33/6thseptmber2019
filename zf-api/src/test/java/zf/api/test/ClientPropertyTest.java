package zf.api.test;

import org.testng.annotations.Test;



import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.restassured.response.Response;
import zf.api.pages.ClientPropertyPage;

public class ClientPropertyTest extends ClientPropertyPage{
	public Response createPropertyForClientTrue =null;
	public Response updatePropertyForClientTrue =null;
	public Response deletePropertyForClientTrue =null;
	public Response createPropertyForClientFalse=null;
	public Response updatePropertyForClientFalse=null;
	public Response deletePropertyClientConfigFalse=null;
	String deletePropertyClientConfigTrue =null;


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
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}