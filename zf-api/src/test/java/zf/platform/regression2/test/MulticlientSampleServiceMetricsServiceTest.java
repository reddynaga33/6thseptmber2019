package zf.platform.regression2.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.test.MetricsServiceTest;

public class MulticlientSampleServiceMetricsServiceTest extends MetricsServiceTest{
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	@Test
	public void C62427VerifyUuserIsAbleToSeeTheMetricsForPrometheus() {
		C61757VerifyUuserIsAbleToSeeTheMetricsForPrometheus();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
	
}
