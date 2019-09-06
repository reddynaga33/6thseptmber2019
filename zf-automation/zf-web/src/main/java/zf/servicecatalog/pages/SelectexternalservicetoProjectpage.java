
package zf.servicecatalog.pages;

import org.openqa.selenium.By;

import framework.DriverManager;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class SelectexternalservicetoProjectpage extends ElementManager {
	
	


	
		
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}

	
	

