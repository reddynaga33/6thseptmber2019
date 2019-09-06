package zf.api.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import zf.api.pages.CUSPropertyPage;
import zf.api.pages.ClientPropertyPage;

public class CUSPropertyTestOLD extends CUSPropertyPage{

	public Response createPropertyForCUSTrue=null;
	public Response updatePropertyForCUSTrue=null;
	public Response deletePropertyForCUSTrue=null;
	public Response createPropertyForCUSFalse=null;
	public Response updatePropertyForCUSFalse=null;
	public Response deletePropertyForCUSFalse=null;
	ClientPropertyPage clientpropertypage=new ClientPropertyPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		clientpropertypage.configureServiceBus();
	}

	@Test(enabled=true)
	public void CreatePropertyForCUSWithReconfigureTrue()
	{
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscription,"true");
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty");
		Response CUSPropertyResponse=clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		validateCUSPropertyInDB(CUSPropertyResponse);

	}

	@Test(enabled=true)
	public void UpdatePropertyForCUSWithReconfigureTrue() {
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		HashMap<String, Integer> configureTopicSubscriptionForUpdateClient = clientpropertypage.configureTopicSubscription();
		updatePropertyForCUSTrue = UpdatePropertyForCUS("CreateUpdateCUSProperty","UpdatePropertyForCUS","true");
		String responseValue = clientpropertypage.getResponseValue(updatePropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForUpdateClient,"true");
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty");
		Response CUSPropertyResponse=clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		validateCUSPropertyInDB(CUSPropertyResponse);

	}

	@Test(enabled=true)
	public void DeletePropertyForCUSWithReconfigPropertyTrue() {
		createPropertyForCUSTrue=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		deletePropertyForCUSTrue = deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","true");
		String responseValue = clientpropertypage.getResponseValue(deletePropertyForCUSTrue,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForDelete, "true");
		clientpropertypage.getDeleteClientAndCUSProperty("GetCUSProperty");
		Response configHierarchicalClientAndCUSProperty = clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		clientpropertypage.validateDeleteClientAndCUSPropertyInDB(configHierarchicalClientAndCUSProperty,"SelectCUSProperty");

	}
	@Test(enabled=true)
	public void aCreatePropertyForCUSWithReconfigureFalse()
	{
		HashMap<String, Integer> configureTopicSubscriptionForConfigureFalse = clientpropertypage.configureTopicSubscription();
		createPropertyForCUSFalse=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
		String responseValue = clientpropertypage.getResponseValue(createPropertyForCUSFalse,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForConfigureFalse, "false");
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty");
		Response CUSPropertyResponse=clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		validateCUSPropertyInDB(CUSPropertyResponse);
	}

	@Test(enabled=true)
	public void UpdatePropertyForCUSWithReconfigureFalse() 
	{
		createPropertyForCUSFalse=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
		HashMap<String, Integer> configureTopicSubscriptionForConfigureFalseToUpdate = clientpropertypage.configureTopicSubscription();
		updatePropertyForCUSFalse = UpdatePropertyForCUS("CreateUpdateCUSProperty","UpdatePropertyForCUS","false");
		String responseValue = clientpropertypage.getResponseValue(updatePropertyForCUSFalse,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForConfigureFalseToUpdate, "false");
		clientpropertypage.getClientAndCUSProperty("GetCUSProperty");
		Response CUSPropertyResponse=clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		validateCUSPropertyInDB(CUSPropertyResponse);

	}

	@Test(enabled=true)
	public void DeletePropertyForCUSWithReconfigPropertyFalse() 
	{
		createPropertyForCUSFalse=createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
		HashMap<String, Integer> configureTopicSubscriptionForConfigureFalseToDelete = clientpropertypage.configureTopicSubscription();
		deletePropertyForCUSFalse = deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","false");
		String responseValue = clientpropertypage.getResponseValue(deletePropertyForCUSFalse,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForConfigureFalseToDelete, "false");
		clientpropertypage.getDeleteClientAndCUSProperty("GetClientProperty");
		Response configHierarchicalClientAndCUSProperty = clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		clientpropertypage.validateDeleteClientAndCUSPropertyInDB(configHierarchicalClientAndCUSProperty,"SelectCUSProperty");
	}

	@Test(enabled=true)
	public void GetTheCUSWithWorngSDID() {
		Response CUSWithWrongSSIDResponse = getTheCUSWithWrongSSID("GetWrongSSIDPropertyDetails");
		validateCUSWrongSDIDInDB(CUSWithWrongSSIDResponse);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}	