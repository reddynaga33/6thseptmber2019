package zf.servicecatalog.pages;

import org.openqa.selenium.By;

import framework.DriverManager;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class DeleteAssignedAdminsAndUsersPage extends ElementManager {
	
	
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

	public String  createProjectandassignadmin(String Payload) {
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
	
	
	public void deleteprojectadmin() {
		
		waitElementVisibleClick(SELECTSERVICE_DOWN,300);
		waitElementVisibleClick(GATEAWAYSERVICE,300);
		elementClick(CONTINUE_BT);
		elementSendKeys(ENTEEMAIL_TXT,"nagarjuna.alamuri@sasken.com");
		elementClick(SEARCH_BT);
		
	}
	
	public void deleteserviceuser(String Payload) {
		
		try {
		JSONObject createprojectdata = JsonReader.getJsonObject(Payload);
		elementSendKeys(CREATEPROJECT_ASSIGNADMIN_BT, createprojectdata.getAsString("EMAIL"));
		elementClick(CREATEPROJECT_SEARCH_BT);
		elementClick(CREATEPROJECT_CONTINUE_BT);
		elementClick(CREATEPROJECT_CREATE_BT);
	
	
	   }catch (Exception e) {
		info(e.getMessage());
	   }
	
	
	
	
	
	

	}}
