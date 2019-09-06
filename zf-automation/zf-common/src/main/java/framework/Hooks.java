package framework;


import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Hooks extends ExtentReport{
	SendMailSSL mail = new SendMailSSL();
	ExtentReport extentReport=new ExtentReport();
	static ISuite suite;
	static String suiteName;
	static ITestContext ctx;
	
	@BeforeSuite
	public void beforeSuite(ITestContext ctx) {
		suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		System.out.println("Suite Name::"+suiteName);

		//	System.out.println("Execution start Time::"+ctx.getStartDate().getTime());
	}
	@BeforeTest
	public void beforeTest() {
		ExtentReport.configureReport();
	}


	@AfterTest
	public void afterTest()
	{
		ExtentReport.reportClose();
	}

	@AfterSuite
	public void afterSuite(){

		Map<String,ISuiteResult> suiteResults = suite.getResults();
		for (ISuiteResult sr : suiteResults.values()) {
			ITestContext tc = sr.getTestContext();
			int PassStatus = tc.getPassedTests().getAllResults().size();
			int failStatus = tc.getFailedTests().getAllResults().size();
			int skipStatus = tc.getSkippedTests().getAllResults().size();
			String ReportPath = ExtentReport.zipAutomationReport();
			mail.AutomationReport(ReportPath,PassStatus,failStatus,skipStatus);
		}}
}
