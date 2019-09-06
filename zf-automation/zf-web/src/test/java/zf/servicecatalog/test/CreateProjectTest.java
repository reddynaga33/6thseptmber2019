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

public class CreateProjectTest extends CreateProjectPage{
	
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	String ProjectName=null;
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("SCPROJECTDASHBOARDTAURI"));
		microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentSCProperties(),EnvironmentManager.getEnvironmentSCPWDProperties());
	}
	
	//@Test
	public void adminLogin()
	{
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getEnvironmentProperties("SCPROJECTDASHBOARDTAURI"));
		microsoftlogin.microsoftLoginSC(EnvironmentManager.getEnvironmentSCProperties(),EnvironmentManager.getEnvironmentSCPWDProperties());
	}
//	@Test
	public void  createProject() {
		ProjectName=createProjectWithAllFeilds("createproject");
		
	}
	
//	@Test
	public void viewAssignedAdmin() {
		viewassignedadminemail("createproject","automation15841");
	}
	
	//@Test
	public void viewassigneduser() {
		viewassigneduseremail("createproject",ProjectName);
	}
	
	//@Test
	public void AssigningOTAService() {
	
		AssigningOTAServiceToProject("createproject",ProjectName);
	}
	
	//@Test
	public void AssigningGatewayService() {
	AssigningGatewayServiceToProject("createproject",ProjectName);
	}
	
	//@Test
	public void AssigningAssetService() {
	AssigningAssetServiceToProject("createproject",ProjectName);
	}
	
	
	//@Test
	public void Viewprojectdetails() {
		//ProjectName=createProjectWithAllFeilds("createproject");
		viewprojectdetails("createproject","auto761203");
	}
	
	@Test
	public void logout() {
		//ProjectName=createProjectWithAllFeilds("createproject");
		logouts();
	}
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
//		DriverManager.closeAllBrowser();
	}

}
