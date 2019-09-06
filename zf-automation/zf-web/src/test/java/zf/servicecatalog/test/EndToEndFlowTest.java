package zf.servicecatalog.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import zf.pages.MicrosoftLoginPage;
import zf.servicecatalog.pages.CreateProjectPage;
import zf.servicecatalog.pages.EndToEndFlowPage;

public class EndToEndFlowTest extends EndToEndFlowPage{

	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	CreateProjectPage createProjectObject = new CreateProjectPage();
	String ProjectName=null;
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		}
	
	//@Test
	public void adminLogin()
	{
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("SCPROJECTDASHBOARDTAURI"));
		microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentSCProperties(),EnvironmentManager.getEnvironmentSCPWDProperties());
	}
//	@Test
	public void  createProject() {
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("SCPROJECTDASHBOARDTAURI"));
		microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentSCProperties(),EnvironmentManager.getEnvironmentSCPWDProperties());
			ProjectName=createProjectObject.createProjectWithAllFeilds("createproject");
			createProjectObject.logouts();	
			DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("SCPROJECTDASHBOARDTAURI"));
			microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentSCProperties(),EnvironmentManager.getEnvironmentSCPWDProperties());
		
	}
	

	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
//		DriverManager.closeAllBrowser();
	}

}
