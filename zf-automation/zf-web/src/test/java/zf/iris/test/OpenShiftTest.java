package zf.iris.test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import framework.DriverManager;
import framework.EnvironmentManager;
import zf.iris.pages.OpenShiftPage;
import zf.pages.MicrosoftLoginPage;

public class OpenShiftTest extends OpenShiftPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("IRISOPENSHIFTURL"));
		microsoftlogin.microsoftLogin(EnvironmentManager.getEnvironmentProperties("IRISOPENSHIFTUSERNAME"),EnvironmentManager.getEnvironmentProperties("IRISOPENSHIFTPASSWORD"));
	}
	
	@Test
	public void C55138applicationDeploymentStatus()
	{
		ApplicationDeploymentStatus();
	}
	
	@Test
	public void C55138quotaStatus()
	{
		QuotaStatus();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}

}
