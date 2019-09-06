package zf.platform.insprint.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.platform.insprint.pages.CPF3049Page;

public class CPF3049Test extends CPF3049Page
{
	
	@BeforeMethod
	public void beforeMethod(Method testName)
	{
		startTest(testName.getName());
	}
	
//	@Test
	public void TC01ServiceAwareOfClients()
	{
		globalInfoClients();
	}
	
//	@Test
	public void TC02ServiceRrespectsClientContextCurrentClient()
	{
		ServiceRrespectsClientContext("ClientContext","current-client");
		ServiceRrespectsClientContext("ClientContext","current-client-context");
		ServiceRrespectsClientContext("ClientContext","current-client-id");
		ServiceRrespectsClientContext("ClientContext","current-request-context");
	}
	
//	@Test
	public void ATCPrometheusMetrics()
	{
		PrometheusMetrics();
		HealthCheck();
		PrometheusMetrics();
	}
//	@Test
	public void TC03ServiceRoutesClientDataIntoAppropriateClientDatabase() {
		Response client1GreetingsResponse = sendClientGreetings("ClientGreeting","ClientGreetingsClient1");
//		client1DBValidation(client1GreetingsResponse);
		Response client2GreetingsResponse =sendClientGreetings("ClientGreeting","ClientGreetingsClient2");
		client2DBValidation(client2GreetingsResponse);
		getGreetingLogsPostitiveResponse("GreetingslogsClient1",client1GreetingsResponse);
		getGreetingLogsNegativeResponse("GreetingslogsClient1",client2GreetingsResponse);
		getGreetingLogsNegativeResponse("GreetingslogsClient2",client1GreetingsResponse);
		getGreetingLogsPostitiveResponse("GreetingslogsClient2",client2GreetingsResponse);

	}
	@Test
	public void TC06ServicePassCurrentClientContextWithRESTAPICalls() {
		Response proxyGreetingResponse = proxyGreeting("proxyGreeting","ClientProxyGreeting");
		getGreetingLogForClient(proxyGreetingResponse);
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}