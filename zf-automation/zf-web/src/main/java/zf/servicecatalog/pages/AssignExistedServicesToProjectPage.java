package zf.servicecatalog.pages;

import org.openqa.selenium.By;

import framework.DriverManager;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class AssignExistedServicesToProjectPage extends ElementManager {
	
	

	
	
	JsonReader jsonData=new JsonReader();

	private static By SERVICEASSIGN_BT                 				 =By.xpath("//span[text()='Assign Services']");
	private static By SELECT_PROJECT_DD                 	         =By.xpath("//label[text()='Select Project*']");
	private String PROJECT_CHOOSE                	 				 ="//span[text()='{}']";
	private static By NEXT_BT                                        =By.xpath("//div[@class='bt-forward-icon client-profile-sprite ng-star-inserted']");	
	private static By SERVICE_SELECT                                 =By.xpath("//label[text()='Select Service to Assign*']");
	private String SERVICES_SELECT                	                 ="//span[text()='{}']";
	private static By CONTINUE               	                     =By.xpath("//span[text()='Continue']");
	private static By EMAIL_TB               	                     =By.xpath("//input[@type='email']");
	private static By SEARCH_TXT              	                     =By.xpath("//span[text()='Search']");
	private static By ASSIGN              	                         =By.xpath("//span[text()='Assign']");
	private static By SUBSCRIPTION              	                 =By.xpath("//span[@class='ng-star-inserted']");
	private static By CROSS              	                         =By.xpath("//i[@class='sprite-client fr close-btn']");
	
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
	

	public void Assignexistedservice(String ProjectName, String servicename,String payload){
		try {
			JSONObject AssignServicesdata = JsonReader.getJsonObject(payload);
			sleep(5000);
			assignservicetouser();
			waitElementVisibleClick(SERVICEASSIGN_BT ,300);
			waitElementVisibleClick( SELECT_PROJECT_DD ,300);
			elementClick(By.xpath(dynamicXpath(PROJECT_CHOOSE ,ProjectName)));
			elementClick(NEXT_BT);
			elementClick(SERVICE_SELECT );
			elementClick(By.xpath(dynamicXpath(SERVICES_SELECT ,servicename)));
			elementClick(CONTINUE);
			elementSendKeys(EMAIL_TB, AssignServicesdata.getAsString("USERNAME"));
			elementClick(SEARCH_TXT);
			elementClick(CONTINUE);
			elementClick(ASSIGN);
			compareText(elementGetText(SUBSCRIPTION), AssignServicesdata.getAsString("SUBSCRIPTIONEXIST"));
			elementClick(CROSS);
		
		}catch (Exception e) {
			info(e.getMessage());
		}
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
	
	
	

}
