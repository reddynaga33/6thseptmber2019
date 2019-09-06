package zf.ServiceManagementServiceMulticlientSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.ServiceManagementServiceMulticlientSuite.pages.IDestinationServicesPage;

public class IDestinationServiceTest extends IDestinationServicesPage {
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	//v 26th june C62231 Verify user is able to get the Destination with enum valuse as xxx 		
//	@Test
	public void C62231GetVerifyUserAbleToGetDestinationEnum() {

		VerifyUserAbleToGetDestinationEnum("getDestinationWithEnumValues");
	}

	//v 26th june C62229 Verify user is able to get the Event_hub Destination		
//	@Test
	public void C62229GetVerifyUserAbleToGetEventHubDestination() {

		VerifyUserAbleToGetEventHubDestination("getEventHubDestination");
	}
	//v 26th june C62228 Verify user is able to get the topic destination 
//	@Test
	public void C62228GetVerifyUserAbleToGetTopicDestination() {

		VerifyUserAbleToGetTopicDestination("UserGetTopicDestination");
	}
	//v 26th june C62133 Destinations API isn't working	
	@Test
	public void C62133GetCheckDestinationAPIisnotWorking() {

		getDestinationAPIworking("getDestination");
	}
	
	
	//s 26th June - C62227 - Verify user is able to get the queue destinations
	//@Test
	public void C62227VerifyGetQueueDestinations()
	{
		getQueueDestinations("GetQueueDestinations");
	}
	
	//s 26th June -C62230 - Verify user is able to get the Destination
	//@Test
	public void C62230GetDestinations()
	{
		getDestinations("GetDestinations");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
