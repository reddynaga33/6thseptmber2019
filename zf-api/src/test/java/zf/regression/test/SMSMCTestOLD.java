package zf.regression.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.api.pages.CUSPropertyPage;
import zf.api.pages.ClientPropertyPage;
import zf.pages.PasswordEncryptDecrypt;
import zf.regression.pages.SMSMCPage;

public class SMSMCTestOLD extends SMSMCPage{
	ClientPropertyPage clientpropertypage=new ClientPropertyPage();
	CUSPropertyPage clientUserServicepropertypage=new CUSPropertyPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		
		startTest(testName.getName());
		clientpropertypage.configureServiceBus();
		
	}

@Test
	public void TC01getMessageDestinationUsingRestAPI() {
		destinationUsingRestAPI("destinationUsingRestAPI","messages");
	}
@Test
	public void TC02getEventDestinationUsingRestAPI() {
		destinationUsingRestAPI("destinationUsingRestAPI","events");
	}

@Test
	public void TC03createClientConfigurationPropertiesWithReconfigureTrue() {
		clientpropertypage.configureServiceBus();
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscription,"true");
	}

@Test
	public void TC04getcreatedClientConfigurationPropertiesWithReconfigureTrue() {
		JSONObject createPropertyForClient = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		String clientAndCUSProperty = clientpropertypage.getClientAndCUSProperty("GetClientProperty");
		validateCreatePropertyAndGetproperty(clientAndCUSProperty,createPropertyForClient);
	}
	
@Test
		public void TC05deleteClientConfigurationPropertiesWithReconfigureTrue() {

		createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		clientpropertypage.deletePropertyClientConfig("DeletePropertyClientConfigProperty","true");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForDelete, "true");
	}

@Test
	public void TC06getDeletedClientConfigurationPropertiesWithReconfigureTrue() {
		createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","true");
		clientpropertypage.deletePropertyClientConfig("DeletePropertyClientConfigProperty","true");
		clientpropertypage.getDeleteClientAndCUSProperty("GetClientProperty");
	}

@Test
	public void TC07createClientConfigurationPropertiesWithReconfigureFalse() {
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscription,"false");
	}

@Test
	public void TC08getcreatedClientConfigurationPropertiesWithReconfigureFalse() {
		JSONObject createPropertyForClient = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		String clientAndCUSProperty = clientpropertypage.getClientAndCUSProperty("GetClientProperty");
		validateCreatePropertyAndGetproperty(clientAndCUSProperty,createPropertyForClient);
	}

@Test
	public void TC09deleteClientConfigurationPropertiesWithReconfigureFalse() {

		createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		clientpropertypage.deletePropertyClientConfig("DeletePropertyClientConfigProperty","false");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForDelete, "false");
	}

@Test
	public void TC10getDeletedClientConfigurationPropertiesWithReconfigureFalse() {

		createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		clientpropertypage.deletePropertyClientConfig("DeletePropertyClientConfigProperty","false");
		clientpropertypage.getDeleteClientAndCUSProperty("GetClientProperty");
	}

@Test
	public void TC11createClientUsesServicesConfigurationPropertiesWithReconfigureTrue() {
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscription,"true");
	}

@Test
	public void TC12getCreatedClientUsesServicesConfigurationPropertiesWithReconfigureTrue() {
		JSONObject createPropertyForCUS = createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
	 Response clientAndCUSProperty = clientpropertypage.getClientAndCUSPropertyResponse("GetCUSPropertyDetails");
	 validateCreatePropertyAndGetpropertyResponse(clientAndCUSProperty, createPropertyForCUS);
	}
	
@Test
	public void TC13deleteClientUsesServicesConfigurationPropertiesWithReconfigureTrue() {
		createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		clientUserServicepropertypage.deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","true");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForDelete, "true");

	}
@Test
	public void TC14getDeletedClientUsesServicesConfigurationPropertiesWithReconfigureTrue() {
		createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		clientUserServicepropertypage.deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","true");
		clientpropertypage.getDeleteClientAndCUSProperty("GetCUSProperty");
	}

@Test
	public void TC15createClientUsesServicesConfigurationPropertiesWithReconfigureFalse() {
		HashMap<String, Integer> configureTopicSubscription = clientpropertypage.configureTopicSubscription();
		createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscription,"false");
	}

@Test
	public void TC16getCreatedClientUsesServicesConfigurationPropertiesWithReconfigureFalse() {
		JSONObject createPropertyForCUS = createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
	 Response clientAndCUSProperty = clientpropertypage.getClientAndCUSPropertyResponse("GetCUSPropertyDetails");
	 validateCreatePropertyAndGetpropertyResponse(clientAndCUSProperty,createPropertyForCUS);
	}

@Test
	public void TC17deleteClientUsesServicesConfigurationPropertiesWithReconfigureFalse() {
		createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
		HashMap<String, Integer> configureTopicSubscriptionForDelete = clientpropertypage.configureTopicSubscription();
		clientUserServicepropertypage.deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","false");
		clientpropertypage.DeleteTopicSubscription(configureTopicSubscriptionForDelete, "false");
	}
	
@Test
	public void TC18getDeletedClientUsesServicesConfigurationPropertiesWithReconfigureFalse() {
		createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","false");
		clientUserServicepropertypage.deletePropertyCUSConfig("DeletePropertyCUSConfigProperty","false");
		clientpropertypage.getDeleteClientAndCUSProperty("GetCUSProperty");
	}

@Test
	public void TC19getHierarchicalpropertiesClientUsesServices() {
		JSONObject createPropertyForCUS = createPropertyForCUS("CreateUpdateCUSProperty","CreatePropertyForCUS","true");
		Response hierarchicalCUSProperty = clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalCUSProperty");
		validateCreateCUSAndHierarchicalproperty(createPropertyForCUS,hierarchicalCUSProperty,"CLIENT_USES_SERVICE");
	}

@Test
	public void TC20getHierarchicalpropertiesClient() {
		JSONObject createPropertyForClient = createPropertyForClient("CreateUpdateClientProperty","CreatePropertyForClient","false");
		Response hierarchicalClientProperty = clientpropertypage.getConfigHierarchicalClientAndCUSProperty("GetConfigHierarchicalClientProperty");
		validateCreateCUSAndHierarchicalproperty(createPropertyForClient,hierarchicalClientProperty,"CLIENT");
	}

//@Test
	public void TC21createServiceDescriptorID() {
		configureServiceBus();
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription("CreateServiceDescriptorTopicName","ServiceDescriptor","ServiceDescriptorResult");
		String createServiceDescriptorID = createServiceDescriptor("CreateServiceDescriptor");
		DeleteTopicSubscription("CreateServiceDescriptorTopicName","ServiceDescriptor","ServiceDescriptorResult",configureTopicSubscription);
		validateServiceDescriptionDetails(createServiceDescriptorID);
	}
	
//@Test
	public void TC22getcreatedServiceDescriptorID() {
		
		String createServiceDescriptorID = createServiceDescriptor("CreateServiceDescriptor");
		getcreatedServiceDescriptorID("GetserviceDescriptor",createServiceDescriptorID);
	}

//@Test
	public void TC23createServiceDescriptorIDWithWrongAPPID() {
		Response createServiceDescriptorResponse = createServiceDescriptorResponse("CreateServiceDescriptorWithWrongAppID");
		String responseValue = getResponseValue(createServiceDescriptorResponse,"status");
		clientpropertypage.getSagaEntityValidateClientAndCUSProperty("GetSagaEntity",responseValue);
		}

//@Test
	public void TC24UpdateArtifactIDWithExistingSDID() {
		String createServiceDescriptorID = createServiceDescriptor("CreateServiceDescriptor");
		Response updateArtifactIDResponse = UpdateArtifactIDwithExistingSDID(createServiceDescriptorID,"CreateServiceDescriptor");
		String responseValue = getResponseValue(updateArtifactIDResponse,"status");
		getSagaEntityValidate("GetSagaEntity",responseValue);
		}
		
//@Test
	public void TC25GetClientsDetails()
	{
		getClientDetails("GetClientDetails");
	}

//@Test
	public void TC26GetServiceDetailsOfOneClient()
	{
		getServiceDetailsOfOneClient("GetServiceDetailsOfAClient");
	}
	
//@Test
	public void TC27AssignNewServicesToClient()
	{	configureServiceBus();
		String createServiceDescriptorID = createServiceDescriptor("CreateServiceDescriptor");
		HashMap<String, Integer> configureTopicSubscription = configureTopicSubscription("ClientUserServiceTopicName","clientusesservice","clientusesserviceresult");
		assignNewServicesToClient("assignNewServicesToClient",createServiceDescriptorID);
		DeleteTopicSubscription("ClientUserServiceTopicName","clientusesservice","clientusesserviceresult",configureTopicSubscription);
		validateServiceDescriptionDetails(createServiceDescriptorID);
		}


//@Test
	public void TC28GetSDIDOfTheUser()
	{String createServiceDescriptorID = createServiceDescriptor("CreateServiceDescriptor");
		getSDIDOfTheUser("GetSDIDOfTheUser",createServiceDescriptorID);
	}
	
	
@Test
	public void C62122CreatedServiceDescriptorIDwithCorrectID() {
		
		String createSDIDResponse = createServiceDescriptorResponse("CreateServiceDescriptor","CreateServiceDescriptor");
		//TODO getting 405 error
		getSagaStatus("GetSagaEntity",createSDIDResponse);
		getServiceDescriptorID("GetServiceDescriptor");
	}
	
	@Test
	public void C62154CreatedServiceDescriptorIDwithWrongID() {
		
		String createSDIDResponse = createServiceDescriptorWithWrongID("CreateServiceDescriptor","CreateServiceDescriptorWrongID");
		//TODOYet to validate
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
