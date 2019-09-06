package zf.MulticlientSampleServiceSuite.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zf.MulticlientSampleServiceSuite.pages.DestinationServicePage;

public class DestinationServiceTest extends DestinationServicePage{
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

		@Test
		public void C62798GetAllDestinationsFromStorage()
		{
			String[] allDestinationsFromStorage = getAllDestinationsFromStorage("GetAllDestinationsFromStorage");
			validateNewAssetInDB(allDestinationsFromStorage);
		}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}
}
