package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.RestApiUtility;
import zf.MulticlientSampleServiceSuite.pages.GlobalGreetingLogsServicePages;

public class GlobalGreetingLogsServiceTest extends GlobalGreetingLogsServicePages{
	String GreetingID=null;
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
@Test
	public void C61739VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase(){
		GreetingID= VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase("ClientGreeting","GlobalGreetings");
		VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId(GreetingID);
	}
 
@Test
	public void C61740VerifyUserIsAbleToGetAllTheGreetingSendForSavedToTheGlobalDatabase() {
		VerifyUserIsAbleToGetAllTheGreetingSendForSavedToTheGlobalDatabase("GlobalGreetings");
	}

@Test
	public void C61742VerifyUserIsAbleToGetTheGreetingSavedTheGlobalDatabaseUsingCorrectGreetingId() {
		GreetingID= VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase("ClientGreeting","GlobalGreetings");
		VerifyUserIsAbleToGetTheGreetingSavedTheGlobalDatabaseUsingCorrectGreetingId(GreetingID);
		VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId(GreetingID);
	}

@Test
	public void C61743VerifyUserIsAbleToGetTheMessageSavedTheGlobalDatabaseUsingWrongGreetingId() {
		GreetingID= VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase("ClientGreeting","GlobalGreetings");
		VerifyUserIsAbleToGetTheMessageSavedTheGlobalDatabaseUsingWrongGreetingId(GreetingID+getRandomNumber());
	}
	
@Test
	public void C61744VerifyUserIsAbleToGetTheMessageSavedTheGlobalDatabaseUsingNoGreetingId() {
		VerifyUserIsAbleToGetTheMessageSavedTheGlobalDatabaseUsingNoGreetingId();
	}

@Test
	public void C61745VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId() {
		GreetingID= VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase("ClientGreeting","GlobalGreetings");
		VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId(GreetingID);
	}
	
@Test
	public void C61746VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithWrongGreetingId() {
		GreetingID= VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase("ClientGreeting","GlobalGreetings");
		VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithWrongGreetingId(GreetingID+getRandomNumber());
		VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId(GreetingID);
	}
	
@Test
	public void C61747VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithNoGreetingId() {
		VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithNoGreetingId();
	}
	
@Test
	public void C61748VerifyUserAbleToSendGreetingToGlobalDBFromTheRollbackAndSentGreetingShouldnotSaveToDB() {
		GreetingID=VerifyUserAbleToSendGreetingToGlobalDBFromTheRollbackAndSentGreetingShouldnotSaveToDB("ClientGreeting","RollbackGlobalGreetings","GlobalGreetings");
		//TODO - partially automated because of its having bug ,need more clarification 
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}