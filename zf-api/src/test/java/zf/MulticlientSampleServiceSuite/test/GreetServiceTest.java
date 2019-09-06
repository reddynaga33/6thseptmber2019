package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.MulticlientSampleServiceSuite.pages.GreetServicePage;

public class GreetServiceTest extends GreetServicePage{
	@BeforeMethod
	public void beforeMethod(Method testName) {
	
		startTest(testName.getName());
	}

	
//	GlobalInfoService (4)
@Test
	public void C61706VerifyuserAbleToGetGlobalInfoClients() {
	
		VerifyuserAbleToGetGlobalInfoClients("GlobalInfoClients");   
	}
	
	
	//GreetService (12)
	@Test
	public void C61727VerifyUserAbleToSendGreetRequestWithCorrectClientId() {
		Response greetingResponse = VerifyUserAbleToSendGreetRequestWithCorrectClientId("ClientGreetingWithClientID","SendGreetingsToClient");
		clientDBValidation(greetingResponse,"SelectGreetingDetails");
	}
	
	@Test
	public void C61728VerifyUserAbleToSendGreetRequestWithWrongClientId() {
	Response greetingResponse = VerifyUserAbleToSendGreetRequestWithWrongClientId("ClientGreetingWithWrongClientID","SendGreetingsToClient");
	
}
	@Test
	public void C61729VerifyUserAbleToSendGreetRequestWithoutClientId() {
		VerifyUserAbleToSendGreetRequestWithNoClient("SendGreetingsNoClient","ClientGreetingWithNoClientID");
	}

	@Test
	public void C61730VerifyUserAbleToSendGreetRequestToClientIdAndGlobalDB() {
	Response greetingResponse = VerifyUserAbleToSendGreetRequestWithCorrectClientId("SendGreetRequestToClientIdAndGlobalDB","SendGreetingsToClientAndGlobalDB");
	clientDBValidation(greetingResponse,"SelectGreetingDetails");
	globalClientDBValidation(greetingResponse,"SelectGlobalGreetings");
}
	
	@Test
	public void AAC61731VerifyUserAbleToSendGreetToWrongClientIdAndGlobalDB() {
 VerifyUserAbleToSendGreetRequestWithWrongClientId("SendGreetToWrongClientIdAndGlobalDB","SendGreetingsToClientAndGlobalDB");

}
	
@Test
	public void C61732VerifyUserAbleToSendGreetRequestNoClient() {
		VerifyUserAbleToSendGreetRequestWithNoClient("SendGreetingsNoClientGlobalDB","ClientGreetingWithNoClientID");

}
	
@Test
	public void C61734GetGreetLogsWithCorrectClient() {
		Response greetingResponse = VerifyUserAbleToSendGreetRequestWithCorrectClientId("GreetLogsWithCorrectClient","SendGreetingsToClient");
		GetGreetLogsWithCorrectClient("GetGreetingsToClient","GetGreetLogsWithCorrectClient");
}
	
@Test
	public void C61735GetGreetLogsWithWrongClient() {
//		Response greetingResponse = VerifyUserAbleToSendGreetRequestWithCorrectClientId("ClientGreetingWithClientID","SendGreetingsToClient");
		GetGreetLogsWithWrongClient("GetGreetingsToClient","GetGreetLogsWithWrongClient");
}
	
@Test
	public void C61736GetGreetLogsWithNoClient() {
//		Response greetingResponse = VerifyUserAbleToSendGreetRequestWithCorrectClientId("ClientGreetingWithClientID","SendGreetingsToClient");
		GetGreetLogsWithNoClient("GetGreetingsNoClient");
}
	
	@Test
	public void C61733SendGreetingToclientFromRollback() {
		SendGreetingToclientFromRollback("GreetingFromRollback","GreetingToclientFromRollback");
	
}
	@Test
	public void C61737SendGreetingToWrongclientFromRollback() {
		SendGreetingToWrongclientFromRollback("GreetingFromRollback","GreetingToWrongclientFromRollback");
}
	
	
	@Test
	public void C61738SendGreetingToWrongclientFromRollback() {
		VerifyUserAbleToSendGreetRequestWithNoClient("GreetingFromRollback","ClientGreetingWithWrongClientID");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	
	}


}
