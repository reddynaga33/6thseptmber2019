package zf.api.test;

import org.testng.annotations.Test;



import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.restassured.response.Response;
import zf.api.pages.ClientPropertyPage;

public class ClientPropertyTestOLD extends ClientPropertyPage{
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
	public void CreatePropertyForClientWithReconfigureTrue()
	{
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription();
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		String responseValue = getResponseValue(createPropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscription,"true");
		getClientAndCUSProperty("GetClientProperty");
		Response ClientPropertyResponse=getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateClientPropertyInDB(ClientPropertyResponse);
		}
	@Test
	public void UpdatePropertyForClientWithReconfigureTrue()
	{
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		HashMap<String, Integer> configureTopicSubscriptionForUpdateClient = configureTopicSubscription();
		updatePropertyForClientTrue = UpdatePropertyForClient("CreateUpdateClientProperty","UpdatePropertyForClient","true");
		String responseValue = getResponseValue(updatePropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionForUpdateClient,"true");
		getClientAndCUSProperty("GetClientProperty");
		Response ClientPropertyResponse=getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateClientPropertyInDB(ClientPropertyResponse);
	}

	@Test
	public void DeletePropertyClientConfigPropertyTrue() {
		createPropertyForClientTrue = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
	HashMap<String, Integer> configureTopicSubscriptionForDelete = configureTopicSubscription();
		deletePropertyForClientTrue = deletePropertyClientConfig("DeletePropertyClientConfigProperty","true");
		String responseValue = getResponseValue(deletePropertyForClientTrue,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionForDelete, "true");
		getDeleteClientAndCUSProperty("GetClientProperty");
		Response configHierarchicalClientAndCUSProperty = getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateDeleteClientAndCUSPropertyInDB(configHierarchicalClientAndCUSProperty,"SelectClientProperty");
	}

	@Test
	public void CreatePropertyForClientWithReconfigureFalse() {
		HashMap<String, Integer> configureTopicSubscriptionConfFlase = configureTopicSubscription();
		createPropertyForClientFalse=createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		String responseValue = getResponseValue(createPropertyForClientFalse,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionConfFlase, "false");
		getClientAndCUSProperty("GetClientProperty");
		Response ClientPropertyResponse=getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateClientPropertyInDB(ClientPropertyResponse);
	}
	@Test
	public void UpdatePropertyForClientWithReconfigureFalse() 
	{
		createPropertyForClientFalse=createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		HashMap<String, Integer> configureTopicSubscriptionConfFlase = configureTopicSubscription();
		updatePropertyForClientFalse=UpdatePropertyForClient("CreateUpdateClientProperty","UpdatePropertyForClient","false");
		String responseValue = getResponseValue(updatePropertyForClientFalse,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionConfFlase, "false");
		getClientAndCUSProperty("GetClientProperty");
		Response ClientPropertyResponse=getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateClientPropertyInDB(ClientPropertyResponse);
	}

	@Test
	public void DeletePropertyClientConfigPropertyFalse() {
		createPropertyForClientFalse=createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		HashMap<String, Integer> configureTopicSubscriptionConfFlase = configureTopicSubscription();
		deletePropertyClientConfigFalse = deletePropertyClientConfig("DeletePropertyClientConfigProperty","false");
		String responseValue = getResponseValue(deletePropertyClientConfigFalse,"status");
		getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		DeleteTopicSubscription(configureTopicSubscriptionConfFlase, "false");
		getDeleteClientAndCUSProperty("GetClientProperty");
		Response configHierarchicalClientAndCUSProperty = getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateDeleteClientAndCUSPropertyInDB(configHierarchicalClientAndCUSProperty,"SelectClientProperty");
	}
	@Test
	public void GetWrongClientIDPropertyDetailsFalse()
	{
		Response wrongClientIDPropertyDetails = getWrongClientIDPropertyDetails("GetWrongClientIDPropertyDetails", "false");
		validateWrongClientInDB(wrongClientIDPropertyDetails);
	}
	@Test
	public void GetWrongClientIDPropertyDetailsTrue() {

		Response wrongClientIDPropertyDetails =getWrongClientIDPropertyDetails("GetWrongClientIDPropertyDetails", "true");
		validateWrongClientInDB(wrongClientIDPropertyDetails);
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}