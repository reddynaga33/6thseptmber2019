package framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * This class help to create method for generating report
 * 
 */
public class ExtentReport extends CaptureScreenshot {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static  ExtentTest reporter;
	public static String startTime=getTimeStamp();
	static String timeStamp = EnvironmentManager.getReportPath()+startTime; 
	static CaptureScreenshot captureScreenshot=new CaptureScreenshot();

	/**
	 * This method is used for creating a new folder named Reports for storing the report
	 * 
	 * @return method will return the absolute path
	 */
	private static String loadReportFolder() {

		String path=null;
		File reportsfolder = new File(timeStamp);
		if (reportsfolder.exists()) {
			path=reportsfolder.toString()+"\\";
		} else {
			reportsfolder.mkdir();
			path=reportsfolder.toString()+"\\";
		}
		return path;
	}


	/**
	 * This Method is used for generating report with below configuration
	 * 
	 */
	public static Object screenCapture(String longdetails, String imagepath) throws IOException {
		reporter.log(Status.INFO,longdetails, MediaEntityBuilder.createScreenCaptureFromPath(imagepath).build());
		return reporter;

	}


	/**
	 * This Method is used for generating report with below configuration
	 * 
	 */
	public static void configureReport(){

		try {

			htmlReporter=new ExtentHtmlReporter(loadReportFolder()+getTimeStamp()+EnvironmentManager.getDotHtml());
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle(EnvironmentManager.getReportTitle());
			htmlReporter.config().setReportName(EnvironmentManager.getReportName());
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			TestLogger.fileInfo(loadReportFolder()+getTimeStamp()+EnvironmentManager.getDotHtml());;
		}
		catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
	}

	/**
	 * This method is the starting point of the test.
	 * 
	 */
	public void startTest(String methodName) {

		try {
			reporter=extent.createTest(methodName);
			TestLogger.beforORAfterMetod(methodName);
		}
		catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
	}


	/**
	 * Method will be update the test result in the report
	 * 
	 */
	public void getResult(ITestResult result, String methodName) {

		try {
			if(result.getStatus() == ITestResult.FAILURE) {
				reporter.log(Status.FAIL, " Test case Failed due to below issues:");
				reporter.error(result.getThrowable());
				takeScreenShot(methodName);
			}else if(result.getStatus() == ITestResult.SUCCESS){
				reporter.log(Status.PASS, methodName+" PASSED");
			}
			else {
				reporter.log(Status.SKIP, "Test Skipped");
				reporter.skip(result.getThrowable());
			}
		} catch (IOException e) {
			TestLogger.errorMessage(e.getMessage());
		}catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
	}


	/**
	 * Method will be used to write all the data in the report
	 * 
	 */
	public static void reportClose(){
		try {
			extent.flush();
		}catch(Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}

	}

	/**
	 * Method will be used for comparing two values
	 * 
	 */
	public static void compareText(String expected, String actual){
		try {
			if(expected.trim().equals(actual.trim())) {
				reporter.info("Expected value is : "+expected +"      Actual value is : "+actual);
				takeScreenShot(expected);
			}else {
				Assert.fail("Expected value is : "+expected +"      Actual value is : "+actual);
				takeScreenShot(actual);

			}
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}
	
	
	/**
	 * Method will be used for comparing two values
	 * 
	 */
	public static void compareContainsText(String expected, String actual){
		try {
			if(expected.trim().contains(actual.trim())) {
				reporter.info("Expected value is : "+expected +"      Actual value contains : "+actual);
				takeScreenShot(expected);
			}else {
				Assert.fail("Expected value is : "+expected +"      Actual value contains : "+actual);

				takeScreenShot(actual);

			}
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}
	/**
	 * Method will be used for comparing two values
	 * 
	 */
	public static void compareTextIgnoreCase(String expected, String actual){
		try {
			if(expected.trim().equalsIgnoreCase(actual.trim())) {
				reporter.info("Expected value is : "+expected +"      Actual value is : "+actual);
				takeScreenShot(expected);
			}else {
				Assert.fail("Expected value is : "+expected +"      Actual value is : "+actual);

				takeScreenShot(actual);

			}
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}


	/**
	 * Method will be used to write log statement
	 * 
	 */
	public static void info(String message){
		try {
			TestLogger.appInfo(message);
			reporter.info(message);
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}

	/**
	 * Method will be used during failure
	 * 
	 */
	public static void testFailed(String message){
		try {
			TestLogger.appInfo(message);
			Assert.fail(message);
			reporter.log(Status.FAIL, message);
			takeScreenShot(getTimeStamp());
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}

	/**
	 * Method will be used during failure
	 * 
	 */
	public static void testPassed(String message){
		try {
			TestLogger.appInfo(message);
			reporter.info(message);
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
	}



	/**
	 * This method will capture the current time stamp
	 * 
	 * @return Returned the current date
	 */
	public static String getTimeStamp() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(EnvironmentManager.getDateForamt());
		Date dateObject = new Date();
		return simpleDateFormat.format(dateObject);
	}


	public void elementCatch(By byType, long explicittime, Exception message, String error) {
		//			takeScreenShot(error+getTimeStamp());
		TestLogger.elementIdentifierFail("ErrorMessage: Web element " + byType + " is not working");
		Assert.fail("ErrorMessage: Web element "+ byType +"  is not present after waiting for " + explicittime +" seconds."
				+ error);
		testFailed("ErrorMessage: Web element " + byType + " is not working. ");
	}


	public static String  zipAutomationReport() {
		String path = "\\"+timeStamp.replace("./","")+".zip";
		try {

			FileZip appZip = new FileZip();
			appZip.generateFileList(new File(System.getProperty("user.dir")+timeStamp.replace("./", "\\")));
			appZip.zipIt(timeStamp.replace("./","")+".zip",System.getProperty("user.dir")+timeStamp.replace("./", "\\"));
			return System.getProperty("user.dir")+path;

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return path;
	}
}

