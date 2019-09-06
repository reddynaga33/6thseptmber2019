package zf.servicecatalog.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class AssigningServicesAsAdminPage extends ElementManager{
	JsonReader jsonData=new JsonReader();

	private static By ASSIGNSERVICE_BT                 				 =By.xpath("//span[text()='Assign Services']");
	private static By PROJECT_SELECT_DD                 	         =By.xpath("//label[text()='Select Project*']");
	private String CHOOSE_PROJECT                	 				 ="//span[text()='{}']";
	private static By NEXT_BT                                        =By.xpath("//div[@class='bt-forward-icon client-profile-sprite ng-star-inserted']");	
	private static By SELECT_SERVICE                                 =By.xpath("//label[text()='Select Service to Assign*']");
	private String SELECT_SERVICES                	                 ="//span[text()='{}']";
	private static By CONTINUE               	                     =By.xpath("//span[text()='Continue']");
	private static By SELECT_EMAIL_TB               	             =By.xpath("//input[@type='email']");
	private static By SEARCH              	                         =By.xpath("//span[text()='Search']");
	private static By ASSIGN              	                         =By.xpath("//span[text()='Assign']");
	private static By SUBSCRIPTION              	                 =By.xpath("//span[@class='ng-star-inserted']");
	private static By CROSS              	                         =By.xpath("//i[@class='sprite-client fr close-btn']");
	private static By CREATEPROJECT_SUCCESSMESSAGE_DT                =By.xpath("//span[@class='ng-star-inserted']");

	public void AssignServicesAlreadyExist(String ProjectName, String servicename,String payload){
		try {
			JSONObject AssignServicesdata = JsonReader.getJsonObject(payload);
			sleep(5000);
			waitElementVisibleClick(ASSIGNSERVICE_BT,300);
			waitElementVisibleClick(PROJECT_SELECT_DD,300);
			elementClick(By.xpath(dynamicXpath(CHOOSE_PROJECT,ProjectName)));
			elementClick(NEXT_BT);
			elementClick(SELECT_SERVICE);
			elementClick(By.xpath(dynamicXpath(SELECT_SERVICES,servicename)));
			elementClick(CONTINUE);
			elementSendKeys(SELECT_EMAIL_TB, AssignServicesdata.getAsString("USERNAME"));
			elementClick(SEARCH);
			elementClick(CONTINUE);
			elementClick(ASSIGN);
			compareText(elementGetText(SUBSCRIPTION), AssignServicesdata.getAsString("SUBSCRIPTIONEXIST"));
			elementClick(CROSS);
		}catch (Exception e) {
			info(e.getMessage());
		}
	}

	public void AssignServices(String ProjectName, String servicename,String payload){
		try {
			JSONObject AssignServicesdata = JsonReader.getJsonObject(payload);
			sleep(5000);
			waitElementVisibleClick(ASSIGNSERVICE_BT,300);
			waitElementVisibleClick(PROJECT_SELECT_DD,300);
			elementClick(By.xpath(dynamicXpath(CHOOSE_PROJECT,ProjectName)));
			elementClick(NEXT_BT);
			elementClick(SELECT_SERVICE);
			elementClick(By.xpath(dynamicXpath(SELECT_SERVICES,servicename)));
			elementClick(CONTINUE);
			elementSendKeys(SELECT_EMAIL_TB, AssignServicesdata.getAsString("USERNAME"));
			elementClick(SEARCH);
			elementClick(CONTINUE);
			elementClick(ASSIGN);
			String Sucessmessage = elementGetText(CREATEPROJECT_SUCCESSMESSAGE_DT);
			String successMSG=servicename+AssignServicesdata.getAsString("ASSIGNEDTOTHE")+ProjectName+AssignServicesdata.getAsString("SUCCESSFULLY");
			compareText(successMSG, Sucessmessage);
			elementClick(CROSS);
		}catch (Exception e) {
			info(e.getMessage());
		}
	}
}
