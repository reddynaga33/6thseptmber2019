package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.pages.GlobalInfoServicePage;
import zf.insprint.multitenancy10.pages.SMSMCPage;

public class GlobalInfoServiceTest extends GlobalInfoServicePage {
	SMSMCPage smsmcpage=new SMSMCPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void C61707VerifyRequestToGlobalInfoServicestartedMC() {
		smsmcpage.configureServiceBus();
		HashMap<String, Integer> configureTopicSubscription = smsmcpage.configureTopicSubscription();
		smsmcpage.VerifyRequestToGlobalInfoServicestartedMC();
		smsmcpage.DeleteTopicSubscription(configureTopicSubscription);
	}

	@Test /* partially scripted - need to script the create service code */
	public void C61795VerifyUserIsAbleToAssignAndUnassignTheClientsToTheSampleServicesUsingCUSRequest() {
		VerifyUserIsAbleToAssignAndUnassignTheClientsToTheSampleServicesUsingCUSRequest();
	}
	
	@Test
	public void C61715VerifyUuserIsAbleToDeleteTheGreetingsOfNonExistingClient() {
		VerifyUuserIsAbleToDeleteTheGreetingsOfNonExistingClient("DeleteGreeting");
	}
	@Test
	public void C61716VerifyUserAbleToDeleteTheGreetingsOfNoClient() {
		VerifyUserAbleToDeleteTheGreetingsOfNoClient();
	}
	@Test
	public void C61725VerifyUserIsAbleToDeleteTheMessageSendToClientWithWrongClientAndCorrectGreetingId() {
		String GreetingID=VerifyUserIsAbleToCreateGreeting();
		VerifyUserIsAbleToDeleteTheMessageSendToClientWithWrongClientAndCorrectGreetingId(GreetingID);
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
