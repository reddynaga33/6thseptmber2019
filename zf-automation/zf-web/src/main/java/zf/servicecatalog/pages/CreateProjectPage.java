package zf.servicecatalog.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class CreateProjectPage  extends ElementManager{
	JsonReader jsonData=new JsonReader();

	private static By CREATEPROJECT_CREATEPROJECT_BT                 =By.xpath("//button[@class='but1 primary zf-sc-button ng-star-inserted']");
	private static By CREATEPROJECT_PROJECTNAME_EB                 	 =By.xpath("//input[@id='projectName']");
	private static By CREATEPROJECT_LOCATION_EB                 	 =By.xpath("//input[@id='location']");
	private static By CREATEPROJECT_COSTCENTERCODE_EB                =By.xpath("//input[@id='costCenterCode']");
	private static By CREATEPROJECT_PRIORITIZATIONCODE_EB            =By.xpath("//input[@id='prioritizationCode']");
	private static By CREATEPROJECT_DESCRIPTION_EB                 	 =By.xpath("//textarea[@id='clientFurtherInfo']");
	private static By CREATEPROJECT_CONTINUE_BT               	 	 =By.xpath("(//button[@class='but1 primary zf-sc-button ng-star-inserted'])[2]");
	private static By CREATEPROJECT_ASSIGNADMIN_BT               	 =By.xpath("//input[@type='email']");
	private static By CREATEPROJECT_SEARCH_BT               	 	 =By.xpath("//button[@class='invite-button primary zf-sc-button']");
	private static By CREATEPROJECT_CREATE_BT               	     =By.xpath("//button[@class='but3 primary zf-sc-button ng-star-inserted']");
	private static By CREATEPROJECT_SUCCESSMESSAGE_DT                =By.xpath("//span[@class='ng-star-inserted']");
	private static By MANAGE_PROJECT_ADMIN_EMAIL_LEBEL				 =By.xpath("(//span[@class='ng-star-inserted'])[1]");
	private static By MANAGE_PROJECT_USER_LINK				 		 =By.xpath("//div[@ng-reflect-text='View Assigned Users']");
	private static By MANAGE_PROJECT_ADMIN_USER_LEBEL				 =By.xpath("(//span[@class='ng-star-inserted'])[1]");
	private static By MANAGE_ACTIONS				 				 =By.xpath("(//div[@class='action-dots sprite-client'])[3]");
	private static By MANAGE_ACTIONS_ASSIGNSERVICE					 =By.xpath("//div[text()='Assign Service ']");
	private static By SELECT_SERVICE				 				 =By.xpath("//label[text()='Select Service to Assign*']");
	private static By SELECT_OTA_SERVICE 					     	 =By.xpath("(//span[@class='ng-tns-c33-6 ng-star-inserted'])[2]");
	private static By SELECT_GATEWAY_SERVICE 					     =By.xpath("//span[@class='ng-tns-c40-17 ng-star-inserted'])[1]");
	private static By SELECT_ASSET_SERVICE 					     	 =By.xpath("//span[@class='ng-tns-c40-19 ng-star-inserted'])[3]");
	private static By SELECT_CONTINUE 								 =By.xpath("//span[text()='Continue']");
	private static By ENTER_ADMIN_EMAIL 							 =By.xpath("//input[@type='email']");
	private static By CLICK_SEARCH									 =By.xpath("//button[@class='invite-button primary zf-sc-button']");
	private static By CLICK_CONTINUE 							     =By.xpath("//span[text()='Continue']");
	private static By CLICK_ASSIGN 							         =By.xpath("//span[text()='Assign']");
	private static By ASSIGNE_SERVICE_VALIDATION					 =By.xpath("//span[@class='ng-star-inserted']");
	private static By ASSIGN_SERVICE_CLOSE_CROSS					 =By.xpath("//i[@class='sprite-client fr close-btn']");
	private static By VIEW_DETAILS 									 =By.xpath("(//div[@class='actn-item'])[1]");
	private static By PROJECT_NAME 									 =By.xpath("(//p[@class='content-value'])[1]");
	private static By PROJECT_LOCATION 								 =By.xpath("(//p[@class='content-value'])[3]");
	private static By PROJECT_COSTCENTERCODE						 =By.xpath("(//p[@class='content-value'])[4]");
	private static By PROJECT_PRIORITIZATION_CODE					 =By.xpath("(//p[@class='content-value'])[5]");
	private static By PROJECT_DESCRIPTION				 			 =By.xpath("//p[@class='content-value project-desc']");
	private static By LAUNCH_OTA				 			 		 =By.xpath("//span[@ng-reflect-text='Go to OTA Capabilities Workspa'])[2]");
	private static By LAUNCH_GATEWAY				 			 	 =By.xpath("//span[@ng-reflect-text='Go to Gateway Management Works'])[2]");
	private static By LAUNCH_ASSET				 			 	 	 =By.xpath("//span[@ng-reflect-text='Go to Asset Management Workspa'])[2]");
	private static By LOGOUT_ARROW			 			 	 	 	 =By.xpath("//div[@class='icon sprite_service_catalog  logout-icon-propt']");
	private static By LOGOUT		 			 	 	 	 		 =By.xpath("//div[text()='Logout']");
	private String  MANAGE_PROJECT_ADMIN						     ="//div[@ng-reflect-text='{}']/parent::div/following-sibling::div[@class='width-9 col2 border-right']/div";
	private String  MANAGE_PROJECT_VIEWDETAILS					     ="//div[@ng-reflect-text='{}']/parent::div/following-sibling::div[5]//div[@class='action-dots sprite-client']";

	public String  createProjectWithAllFeilds(String Payload) {
		long randomNumber = getRandomNumber();
		String projectname=null;
		try {
			JSONObject createprojectdata = JsonReader.getJsonObject(Payload);
			sleep(2000);
			elementClick(CREATEPROJECT_CREATEPROJECT_BT);
			projectname = createprojectdata.getAsString("PROJECTNAME")+randomNumber;
			elementSendKeys(CREATEPROJECT_PROJECTNAME_EB, projectname);
			elementSendKeys(CREATEPROJECT_LOCATION_EB, createprojectdata.getAsString("LOCATION"));
			elementSendKeys(CREATEPROJECT_COSTCENTERCODE_EB, createprojectdata.getAsString("COSTCENTERCODE"));
			elementSendKeys(CREATEPROJECT_PRIORITIZATIONCODE_EB, createprojectdata.getAsString("PRIORITIZATIONCODE"));
			elementSendKeys(CREATEPROJECT_DESCRIPTION_EB, createprojectdata.getAsString("DESCRIPTION_EB"));
			elementClick(CREATEPROJECT_CONTINUE_BT);
			sleep(4000);
			elementSendKeys(CREATEPROJECT_ASSIGNADMIN_BT, createprojectdata.getAsString("EMAIL"));
			elementClick(CREATEPROJECT_SEARCH_BT);
			elementClick(CREATEPROJECT_CONTINUE_BT);
			elementClick(CREATEPROJECT_CREATE_BT);
			String Sucessmessage = elementGetText(CREATEPROJECT_SUCCESSMESSAGE_DT);
			compareText(projectname+createprojectdata.getAsString("SUCESSMESSAGE"), Sucessmessage);
			sleep(5000);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return projectname;
	}

	public void viewassignedadminemail(String Payload,String ProjectName) {
		JSONObject viewassignedemailofadmin = JsonReader.getJsonObject(Payload);
		try {
			sleep(4000);
			elementClick(By.xpath(dynamicXpath(MANAGE_PROJECT_ADMIN,ProjectName)));
			compareText(viewassignedemailofadmin.getAsString("EMAIL"), elementGetText(MANAGE_PROJECT_ADMIN_EMAIL_LEBEL));
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());

		}
	}

	public void viewassigneduseremail(String Payload,String ProjectName) {
		JSONObject viewassignedemailofuser = JsonReader.getJsonObject(Payload);
		try {
			sleep(4000);
			elementClick(MANAGE_PROJECT_USER_LINK);
			compareText(viewassignedemailofuser.getAsString("USER EMAIL"), elementGetText(MANAGE_PROJECT_ADMIN_USER_LEBEL));
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void AssigningOTAServiceToProject(String Payload,String ProjectName) {
		JSONObject assignservice = JsonReader.getJsonObject(Payload);
		String ActualAssignService=null;
		String ExpectedServiceMessae=assignservice.getAsString("OTACAPABILITIES")+" "+assignservice.getAsString("ASSIGNEDTOTHE")+" "+ProjectName+" "+assignservice.getAsString("SUCCESSFULLY");
		try {
			sleep(4000);
			elementClick(MANAGE_ACTIONS);
			waitElementVisibleClick(MANAGE_ACTIONS_ASSIGNSERVICE,50);
			elementClick(SELECT_SERVICE);
			elementClick(SELECT_OTA_SERVICE);
			elementClick(SELECT_CONTINUE);
			elementSendKeys(ENTER_ADMIN_EMAIL,assignservice.getAsString("ADMINEMAIL"));
			elementClick(CLICK_SEARCH);
			elementClick(CLICK_CONTINUE);
			elementClick(CLICK_ASSIGN);
			ActualAssignService=elementGetText(ASSIGNE_SERVICE_VALIDATION);
			compareText(ExpectedServiceMessae,ActualAssignService);
			elementClick(ASSIGN_SERVICE_CLOSE_CROSS);
			sleep(4000);
			elementClick(LAUNCH_OTA);
			sleep(12000);
		}catch(Exception e){
			testFailed("An exception occured"+e.getMessage());
		}
	}

	public void AssigningGatewayServiceToProject(String Payload,String ProjectName) {
		JSONObject assignservice = JsonReader.getJsonObject(Payload);
		String ActualAssignService=null;
		String ExpectedServiceMessae=assignservice.getAsString("Gateway Management")+" "+assignservice.getAsString("ASSIGNEDTOTHE")+" "+ProjectName+" "+assignservice.getAsString("SUCCESSFULLY");
		try {
			sleep(4000);
			elementClick(MANAGE_ACTIONS);
			waitElementVisibleClick(MANAGE_ACTIONS_ASSIGNSERVICE,50);
			elementClick(SELECT_SERVICE);
			elementClick(SELECT_GATEWAY_SERVICE);
			elementClick(SELECT_CONTINUE);
			elementSendKeys(ENTER_ADMIN_EMAIL,assignservice.getAsString("ADMINEMAIL"));
			elementClick(CLICK_SEARCH);
			elementClick(CLICK_CONTINUE);
			elementClick(CLICK_ASSIGN);
			ActualAssignService=elementGetText(ASSIGNE_SERVICE_VALIDATION);
			compareText(ExpectedServiceMessae,ActualAssignService);
			elementClick(ASSIGN_SERVICE_CLOSE_CROSS);
			sleep(4000);
			elementClick(LAUNCH_GATEWAY);
			sleep(12000);
		}catch(Exception e){
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void AssigningAssetServiceToProject(String Payload,String ProjectName) {
		JSONObject assignservice = JsonReader.getJsonObject(Payload);
		String ActualAssignService=null;
		String ExpectedServiceMessae=assignservice.getAsString("Asset Management")+" "+assignservice.getAsString("ASSIGNEDTOTHE")+" "+ProjectName+" "+assignservice.getAsString("SUCCESSFULLY");
		try {
			sleep(4000);
			elementClick(MANAGE_ACTIONS);
			waitElementVisibleClick(MANAGE_ACTIONS_ASSIGNSERVICE,50);
			elementClick(SELECT_SERVICE);
			elementClick(SELECT_ASSET_SERVICE);
			elementClick(SELECT_CONTINUE);
			elementSendKeys(ENTER_ADMIN_EMAIL,assignservice.getAsString("ADMINEMAIL"));
			elementClick(CLICK_SEARCH);
			elementClick(CLICK_CONTINUE);
			elementClick(CLICK_ASSIGN);
			ActualAssignService=elementGetText(ASSIGNE_SERVICE_VALIDATION);
			compareText(ExpectedServiceMessae,ActualAssignService);
			elementClick(ASSIGN_SERVICE_CLOSE_CROSS);
			sleep(4000);
			elementClick(LAUNCH_ASSET);
			sleep(12000);
		}catch(Exception e){
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void viewprojectdetails(String Payload,String ProjectName) {

		JSONObject viewingdetailsofproject = JsonReader.getJsonObject(Payload);
		try {
			sleep(4000);
			elementClick(By.xpath(dynamicXpath(MANAGE_PROJECT_VIEWDETAILS,ProjectName)));
			elementClick(VIEW_DETAILS);
			compareText(ProjectName, elementGetText(PROJECT_NAME));
			compareText(viewingdetailsofproject.getAsString("LOCATION"), elementGetText(PROJECT_LOCATION));
			compareText(viewingdetailsofproject.getAsString("COSTCENTERCODE"), elementGetText(PROJECT_COSTCENTERCODE));
			compareText(viewingdetailsofproject.getAsString("PRIORITIZATIONCODE"), elementGetText(PROJECT_PRIORITIZATION_CODE));
			compareText(viewingdetailsofproject.getAsString("DESCRIPTION_EB"), elementGetText(PROJECT_DESCRIPTION));
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void logouts() {
		elementClick(LOGOUT_ARROW);
		elementClick(LOGOUT);
	}

}

