package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.MulticlientSampleServiceSuite.pages.GreetingLogsServicePage;

public class GreetingLogsServiceTest extends GreetingLogsServicePage {

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

	@Test
	public void C61711CreateGreetingLogsWithWrongClientID()
	{
		createGreetingLogsWithWrongClientID("CreateGreetingLogsWithWrongClientID","JSONCreateGreetingLogsWithWrongClientID");
	}

	@Test
	public void C61721GetMessageWithWrongClientIDCorrectGreetingID()
	{
		getMessageWithWrongClientIDCorrectGreetingID("GetMessageWithWrongClientIDCorrectGreetingID");
	}


	@Test
	public void C61713GetGreetingWithWrongClientID()
	{
		getGreetingWithWrongClientID("GetGreetingWithWrongClientID");
	}

	@Test
	public void C61718SendGreetingWithWrongClientID()
	{
		sendGreetingWithWrongClientID("SendGreetingWithWrongClientID","JSONSendGreetingWithWrongClientID");
	}	

	@Test
	public void C61722GetVerifyUserMessageToClientwithWrongClientId() {

		getMessageWithWrongClientIDCorrectGreetingID("UserMesgToClientwithWrongClientIdGreet");
	}

	@Test
	public void C61712CreateGreetingLogwithNoClientid() throws InterruptedException {

		createGreetingLogWithNoClientid();
	}
	@Test
	public void C61714VerifyGetGreetingLogwithNoClientid() throws InterruptedException {
		//	createGreetingLogWithNoClientid();
		getGreetingLogwithNoClientid("GreetingLogwithNoClientId");		
	}
	@Test
	public void C61719PutVerifyGreetingWrongIdWithNoClientId() throws InterruptedException {

		GreetingLogIdWithNoClientid("GreetingLogIdWithNoClientId","GreetingLogIdWithNoClientIdData");
	}	

	@Test
	public void C61708VerifyUserAbleToCreateGreetinglogsToCorrectExistingClient() {
		createGreetingLogsWithCorrectClientID("CreateGreetingLogWithClientId","GreetingsLog","SelectClientGreetingDetails");
	}

	
	@Test
	public void C61709VerifyUserAbleToGetCreateGreetinglogsToCorrectExistingClient() {
		Response createdGreetingResponse = createGreetingLogsWithCorrectClientID("CreateGreetingLogWithClientId","GreetingsLog","SelectClientGreetingDetails");
String responseValue = getResponseValue(createdGreetingResponse, "greetingMessage");
getGreetinglogDetails("GetGreetingLog",responseValue);
deleteGreetingLogsWithCorrectClientID("DeleteGreetingLogWithClientId","SelectClientGreetingDetails",responseValue);
	}

	@Test
	public void C61710VerifyUserAbleToDeleteGreetinglogsToCorrectExistingClient() {
		Response createGreetingMessageResponse = createGreetingLogsWithCorrectClientID("CreateGreetingLogWithClientId","GreetingsLog","SelectClientGreetingDetails");
		deleteGreetingLogsWithCorrectClientID("DeleteGreetingLogWithClientId","SelectClientGreetingDetails",getResponseValue(createGreetingMessageResponse,"greetingMessage"));
	}

	@Test
	public void C61717VerifyUserAbleToUpdateGreetinglogsToCorrectExistingClient() {
		Response createGreetingMessageResponse = createGreetingLogsWithCorrectClientID("CreateGreetingLogWithClientId","GreetingsLog","SelectClientGreetingDetails");
		Response updateGreetingLog = updateGreetingLogsWithCorrectClientID("UpdateGreetingLogWithClientId","GreetingsLog","SelectClientGreetingDetails",getResponseValue(createGreetingMessageResponse,"id"));
		deleteGreetingLogsWithCorrectClientID("DeleteGreetingLogWithClientId","SelectClientGreetingDetails",getResponseValue(updateGreetingLog,"greetingMessage"));
	}



	@Test
	public void C61720VerifyUserAbleToGetGreetinglogsToCorrectExistingClientID() {
		Response createGreetingMessageResponse = createGreetingLogsWithCorrectClientID("CreateGreetingLogWithClientId","GreetingsLog","SelectClientGreetingDetails");
		getGreetingLogsWithCorrectClientID("GetGreetingLogWithClientId",getResponseValue(createGreetingMessageResponse,"id"),
				getResponseValue(createGreetingMessageResponse,"greetingMessage"));
		deleteSpecificGreetingWithMessageID("DeleteSpecificGreetingWithClientId","SelectClientGreetingDetails",
				getResponseValue(createGreetingMessageResponse,"id"),getResponseValue(createGreetingMessageResponse,"greetingMessage"));
	}

	@Test
	public void C61724VerifyUserAbleToDeletedSpecificGreetinglogsToCorrectExistingClient() {
		C61720VerifyUserAbleToGetGreetinglogsToCorrectExistingClientID();
	}

	@Test
	public void C61723VerifyUserAbleToGetGreetinglogsWithWrongGreetingID(){
		getGreetingLogsWithWrongGreetingID("GetGreetingLogWithClientId","wrongGreetingID");
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

}
