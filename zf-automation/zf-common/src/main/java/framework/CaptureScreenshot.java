package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.net.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


/*
 * This class help to create method for taking screenshot
 */
public class CaptureScreenshot {

	static String concatenate =".";

	/*
	 * This method will create a directory and return the
	 * directory path
	 * 
	 * @return directory path
	 */
	private static String loadScreenShotFolder() {

		String path=null;
		
		File reportsfolder = new File(ExtentReport.timeStamp+"/Screenshots");
		if (reportsfolder.exists()) {
			path=reportsfolder.toString()+"\\";
		} else {
			reportsfolder.mkdir();
			path=reportsfolder.toString()+"\\";
		}
		return path;
	}
	
	/*
	 * This method will attach the screenshot in report
	 */
	public static void takeScreenShot(String ssname) throws IOException {
		String screenshotname = concatenate+captureScreenshot(ssname);
		ExtentReport.screenCapture("Screen taken for :", screenshotname);
		
	}

	/*
	 * This method will take the path and append the screen shots folder
	 * destination
	 * 
	 * @return the screenshot name with path
	 */
	public static String captureScreenshot(String fileName) {


		String file=loadScreenShotFolder()+fileName+EnvironmentManager.getDotPng();
		File srcFile=((TakesScreenshot) DriverManager.getDriverInstance()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(file));
			TestLogger.fileInfo(file);
		} 
		catch (IOException e) {
			TestLogger.errorMessage(e.getMessage());
		}
		return file;
	}
}
