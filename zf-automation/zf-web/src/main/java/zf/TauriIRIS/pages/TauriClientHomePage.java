package zf.TauriIRIS.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;

public class TauriClientHomePage extends ElementManager{
	JsonReader jsonData=new JsonReader();

	private static By USER_ICON   	 	                 =By.xpath("//span[contains(text(),'Users')]");
	private static By USER_ACTIVE_ICON   	 	         =By.xpath("//div[contains(text(),'Users')]");
	private static By CLIENTS_OPENMATICS_CLIENT			 =By.xpath("//span[contains(text(),'Openmatics 1')]");
	private static By CLIENTS_OPENMATIC2_CLIENT			 =By.xpath("//div[contains(text(),'Openmatics 2')]");
	private static By CLIENTS_OPENMATIC1_CLIENT			 =By.xpath("//div[contains(text(),'Openmatics 1')]");
	private static By CLIENTS_OPENMATIC_CLIENT_TITLE	 =By.xpath("//div[@class='app-name ng-binding']");
	private static By FLEET_ICON						 =By.xpath("//div[contains(text(),'Fleets')]");
	private static By VEHICLE_ICON						 =By.xpath("//div[contains(text(),'Vehicles')]");
	private static By GATEWAY_ICON						 =By.xpath("//div[contains(text(),'Gateway')]");
	private static By CLIENTS_ICON						 =By.xpath("//div[contains(text(),'Clients')]");

	public void LoginWithMoreThanOneClient() {
		JsonReader.getJsonObject("OpenmaticClients");
		try {
			sleep(5000);
			waitElementVisibleClick(USER_ICON,300);
			sleep(7000);
			if(!elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE).contentEquals(jsonData.getJsonData("CLIENTS_OPENMATIC1_CLIENT"))) 
			{
				elementClick(CLIENTS_OPENMATICS_CLIENT);
				elementClick(CLIENTS_OPENMATIC1_CLIENT);
			}
			compareValue(elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE),jsonData.getJsonData("CLIENTS_OPENMATIC1_CLIENT"));
			elementAvailability(FLEET_ICON,"FLEET_ICON");
			elementAvailability(VEHICLE_ICON,"VEHICLE_ICON");
			elementAvailability(USER_ACTIVE_ICON,"USER_ACTIVE_ICON");
			elementAvailability(GATEWAY_ICON,"GATEWAY_ICON");
			elementAvailability(CLIENTS_ICON,"CLIENTS_ICON");
			waitElementVisibleClick(CLIENTS_OPENMATICS_CLIENT, 300);
			waitElementVisibleClick(CLIENTS_OPENMATIC2_CLIENT,300);
			if(!elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE).contentEquals(jsonData.getJsonData("CLIENTS_OPENMATIC2_CLIENT"))) 
			{
				elementClick(CLIENTS_OPENMATICS_CLIENT);
				elementClick(CLIENTS_OPENMATIC2_CLIENT);
			}
			compareValue(elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE),jsonData.getJsonData("CLIENTS_OPENMATIC2_CLIENT"));
			elementAvailability(FLEET_ICON,"FLEET_ICON");
			elementAvailability(VEHICLE_ICON,"VEHICLE_ICON");
			elementAvailability(USER_ACTIVE_ICON,"USER_ACTIVE_ICON");
			elementAvailability(GATEWAY_ICON,"GATEWAY_ICON");
			elementAvailability(CLIENTS_ICON,"CLIENTS_ICON");
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
}