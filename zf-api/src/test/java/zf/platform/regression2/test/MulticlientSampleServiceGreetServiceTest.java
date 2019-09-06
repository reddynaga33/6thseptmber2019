package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.GreetServiceTest;

public class MulticlientSampleServiceGreetServiceTest extends GreetServiceTest{
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	
	}
	@Test(groups = { "Multitenancy 8" })
	public void C62377VerifyUserSendGreetRequestWithCorrectClientID () {
		C61727VerifyUserAbleToSendGreetRequestWithCorrectClientId();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62380VerifyUserAbleToSendGreetRequestToClientIdAndGlobalDB() {
		C61730VerifyUserAbleToSendGreetRequestToClientIdAndGlobalDB();
	}
	
	@Test(groups = { "Multitenancy 8" })
	public void C62383GetGreetLogsWithCorrectClient() {
		C61734GetGreetLogsWithCorrectClient();
	}
	
	
	@Test(groups = { "Multitenancy 8" })
	public void C62386SendGreetingToclientFromRollback() {
		C61733SendGreetingToclientFromRollback();
	}
@AfterMethod
public void afterMethod(ITestResult result,Method testName)
{
	getResult(result,testName.getName());
}

	
}
