package zf.servicecatalog.pages;

import org.openqa.selenium.By;

import framework.DriverManager;
import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class CheckAssignedUsersPage extends ElementManager {
	
	
	
	
	private static By PROJECT_USER_MANAGE_LINK	        =By.xpath("//div[@ng-reflect-text='View Assigned Users']");
	private static By ADMIN_EMAIL_LEBEL_MANAGE_PROJECT	=By.xpath("(//span[@class='ng-star-inserted'])[1]");
	private static By LOGOUT_ARROW			 			=By.xpath("//div[@class='icon sprite_service_catalog  logout-icon-propt']");
	private static By LOGOUT		 			 	    =By.xpath("//div[text()='Logout']");
	private String  MANAGE_PROJECT_ADMIN				 ="//div[@ng-reflect-text='{}']/parent::div/following-sibling::div[@class='width-9 col2 border-right']/div";

	private static By ASSIGNSERVICE_BT    =   By.xpath("//*[text()='Assign Services']");
	private static By SELECTPROJECT_DOWN  =   By.xpath("//label[text()='Select Project*']"); 
	private static By PROJECT_ITEM        =   By.xpath("//span[text()='124']");
	private static By FORWARD_BTN         =   By.xpath(" //div[@class='bt-forward-icon client-profile-sprite ng-star-inserted']");
	private static By SELECTSERVICE_DOWN  =   By.xpath("//label[text()='Select Service to Assign*']");
	private static By GATEAWAYSERVICE     =   By.xpath("//span[text()='Gateway Management']");
	private static By CONTINUE_BT         =   By.xpath("//span[text()='Continue']");
	private static By ENTEEMAIL_TXT       =   By.xpath("//input[@type='email'][@placeholder='Enter Users E-mail ID']");
	private static By SEARCH_BT           =   By.xpath("//span[text()='Search']");
	private static By ASSIGN_BT           =   By.xpath("//span[text()='Assign']"); 
	private static By CLOSE_BT           =   By.xpath("//span[text()='Close']");      
	
	public void assignservicetouser( ) {
		
		
		try {
		//JSONObject AssignServicesdata = JsonReader.getJsonObject(payload);
	
		waitElementVisibleClick(ASSIGNSERVICE_BT,300);
		waitElementVisibleClick(SELECTPROJECT_DOWN,300);
		waitElementVisibleClick(PROJECT_ITEM,300);
		waitElementVisibleClick(FORWARD_BTN,300);
		waitElementVisibleClick(SELECTSERVICE_DOWN,300);
		waitElementVisibleClick(GATEAWAYSERVICE,300);
		elementClick(CONTINUE_BT);
		elementSendKeys(ENTEEMAIL_TXT,"nagarjuna.alamuri@sasken.com");
		elementClick(SEARCH_BT);
		elementClick(CONTINUE_BT);
		waitElementVisibleClick(ASSIGN_BT,300);
		waitElementVisibleClick(CLOSE_BT,300);
		DriverManager.getDriverInstance().navigate().refresh();
		
		}catch (Exception e) {
			info(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	

	public void checkassigneduseremail(String Payload,String ProjectName) {
		JSONObject viewassignedemailofuser = JsonReader.getJsonObject(Payload);
		try {
			sleep(4000);
			elementClick(PROJECT_USER_MANAGE_LINK);
			compareText(viewassignedemailofuser.getAsString("USER EMAIL"), elementGetText(ADMIN_EMAIL_LEBEL_MANAGE_PROJECT));
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	
	public void logoutfromportal() {
		elementClick(LOGOUT_ARROW);
		elementClick(LOGOUT);
	}
	
	
	public void checkssignedadminemail(String Payload,String ProjectName) {
		JSONObject viewassignedemailofadmin = JsonReader.getJsonObject(Payload);
		try {
			sleep(4000);
			elementClick(By.xpath(dynamicXpath(MANAGE_PROJECT_ADMIN,ProjectName)));
			compareText(viewassignedemailofadmin.getAsString("EMAIL"), elementGetText(ADMIN_EMAIL_LEBEL_MANAGE_PROJECT));
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());

		}
	}

	
	
	

}
