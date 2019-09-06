package zf.platform.insprint.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.platform.insprint.pages.CPF2826Page;

public class CPF2826Test extends CPF2826Page{

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}
	@Test
	public void ATCAddSupportForChangeClientContextExtractor()
	{
		AddSupportForChangeClientContextExtractor();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}