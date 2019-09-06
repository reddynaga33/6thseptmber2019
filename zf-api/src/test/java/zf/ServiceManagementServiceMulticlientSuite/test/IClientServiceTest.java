package zf.ServiceManagementServiceMulticlientSuite.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.ServiceManagementServiceMulticlientSuite.pages.IClientServicePage;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;
import zf.api.pages.ClientPropertyPage;

public class IClientServiceTest extends IClientServicePage{
	IServiceDescriptorServiceTestOLD ServiceDescriptor=new IServiceDescriptorServiceTestOLD();
	IServiceDesciptorServiceNewPage serviceDescriptorobject=new IServiceDesciptorServiceNewPage();
	ClientPropertyPage CP=new ClientPropertyPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	// 27th june C62232 Verify user is able to get the client details
	@Test
	public void C62232GetVerifyUserIsAbleToGetClientDetails() {

		VerifyUserIsAbleToGetClientDetails("UserClientDetails");
	}
	@Test
	public void C62233VerifyGetClientDetails(){
		getOneClientdetails("GetServiceDetailsOfAClient");
	}

	//	@Test OLD TEST CASE_Not present in TestRail now
	public void C62236VerifyUserISAbleToAssignTheServicesToTheClient() {
		String ServiceDescriptorId=serviceDescriptorobject.VerifyServiceDescriptorIsCreated();
		configureServiceBus();
		configureTopicSubscription("ServiceDescriptor");
		Response SagaResponse=VerifyUserISAbleToAssignTheServicesToTheClient(ServiceDescriptorId);
		CP.getResponseValue(SagaResponse, "status");
	}

	@Test
	public void C63868VerifyUserIsAbleToAssignServiceDescriptoToClient() {
		String SDName = serviceDescriptorobject.VerifyServiceDescriptorIsCreated();
		String SDDetails = getserviceDetails("GetServiceDescriptor",SDName);
		validateClientDetails(SDDetails,SDName);
		String SDId = getServiceDescriptorDetailsInDB("SelectServiceDescriptorDetails",SDName);
		getServiceDetailsOfAClient("GetServiceDetailsOfAClient",SDName);
		String sagaStatus = assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		getSagaStatus("GetSagaEntity",sagaStatus);
		String serviceDescriptorDetails = getServiceDescriptorAndClientInDB("SelectClientUserServices",SDId);
		validateClientDetails(serviceDescriptorDetails,"19309c32-1769-42ba-9829-d18d5e9e072d");
		String getserviceDetails = getserviceDetails("GetServiceDescriptor",SDName);
		validateClientDetails(getserviceDetails,"19309c32-1769-42ba-9829-d18d5e9e072d");
		String serviceDetailsOfAClient = getServiceDetailsOfAClient("GetServiceDetailsOfAClient",SDName);
		validateClientDetails(serviceDetailsOfAClient,SDName);
		String unAssigningSDClientSagaStatus = unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
	}

	@Test
	public void C63871VerifyUserIsAbleToUnassignServiceDescriptoToClient() {
		String SDName = serviceDescriptorobject.VerifyServiceDescriptorIsCreated();
		String sagaStatus = assigningServiceDescriptorToClient("AssignClientServiceSD",SDName);
		getSagaStatus("GetSagaEntity",sagaStatus);
		String ServiceDescriptorId = getServiceDescriptorDetailsInDB("SelectServiceDescriptorDetails",SDName);
		String unAssigningSDClientSagaStatus = unAssigningServiceDescriptorToClient("UnAssignSDFromClient",SDName);
		getSagaStatus("GetSagaEntity",unAssigningSDClientSagaStatus);
		String getserviceDetails = getserviceDetails("GetServiceDescriptor",SDName);
		validateClientDetails(getserviceDetails,SDName);
		String ServicesOfAClient = getServiceDetailsOfAClient("GetServiceDetailsOfAClient",SDName);
		validateSDAndClientDetailsPresent(ServicesOfAClient,SDName);
		getServiceDescriptorDetailsInDB("SelectServiceDescriptorDetails",SDName);
		getServiceDescriptorNotINClientDB("SelectClientUserServices",ServiceDescriptorId);
	}

	@Test
	public void C62234VerifyGetClientDetailsWrongClientID(){
		getWrongClientdetails("GetServiceDetailsOfWrongClient");
	}


	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}