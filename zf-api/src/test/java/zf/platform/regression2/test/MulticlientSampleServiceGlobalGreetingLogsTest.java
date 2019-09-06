package zf.platform.regression2.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.GlobalGreetingLogsServiceTest;

public class MulticlientSampleServiceGlobalGreetingLogsTest extends GlobalGreetingLogsServiceTest{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {

		startTest(testName.getName());

	}

@Test(groups = { "Multitenancy 8" })
	public void C62389VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase(){
		 C61739VerifyUserIsAbleToSendGreetingsToGlobalAndMessageIsSavedToGlobalDatabase();
			}
	
@Test(groups = { "Multitenancy 8" })
	public void C62390VerifyUserIsAbleToGetAllTheGreetingSendForSavedToTheGlobalDatabase() {
	C61740VerifyUserIsAbleToGetAllTheGreetingSendForSavedToTheGlobalDatabase();
	}

	
@Test(groups = { "Multitenancy 8" })
	public void C62391VerifyUserIsAbleToGetTheGreetingSavedTheGlobalDatabaseUsingCorrectGreetingId() {
		C61742VerifyUserIsAbleToGetTheGreetingSavedTheGlobalDatabaseUsingCorrectGreetingId();
	}
	
@Test(groups = { "Multitenancy 8" })
	public void C62394VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId() {
		C61745VerifyUserIsAbleToDeleteTheGreetingsFromTheGlobalDatabaseWithCorrectGreetingId();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62397VerifyUserAbleToSendGreetingToGlobalDBFromTheRollbackAndSentGreetingShouldnotSaveToDB() {
		C61748VerifyUserAbleToSendGreetingToGlobalDBFromTheRollbackAndSentGreetingShouldnotSaveToDB();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}



}
