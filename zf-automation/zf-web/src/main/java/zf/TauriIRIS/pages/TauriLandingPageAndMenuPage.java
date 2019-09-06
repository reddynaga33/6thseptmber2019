package zf.TauriIRIS.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class TauriLandingPageAndMenuPage extends ElementManager{
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
	private static By OPENMATIC_APPS					 =By.xpath("(//div[@class='panel-heading'])[1]");
	private static By PORTAL_DASHBOARD_BT              	 =By.xpath("//div[text()='DASHBOARD']");
	private static By PORTAL_DETAGTIVE_BT              	 =By.xpath("//div[text()='DETAGTIVE']");
	private static By PORTAL_MAINTENANCE_BT              =By.xpath("//div[text()='MAINTENANCE']");
	private static By PORTAL_REPORTS_BT              	 =By.xpath("//div[text()='REPORTS']");
	private static By PORTAL_TRIPS_BT              	     =By.xpath("//div[text()='TRIPS']");
	private static By PORTAL_WINTERSERVICE_BT            =By.xpath("//div[text()='WINTERSERVICE']");
	private static By PORTAL_ZF_ESYNC_BT              	 =By.xpath("//div[text()='TRIPS']");
	private static By PORTAL_ASSET_DEVICE_MANAGEMENT_BT  =By.xpath("//div[text()='ASSET & DEVICE MANAGEMENT']");
	private static By PORTAL_BACKOFFICE_BT               =By.xpath("//div[text()='BACKOFFICE']");
	private static By PORTAL_HELP_BT              	     =By.xpath("//div[text()='HELP']");
	private static By PORTAL_NONAME_BT              	 =By.xpath("(//div[@class='app-box-container'])[11]");
	private static By DASHBOARD_ICON					 =By.xpath("(//div[text()='Dashboard'])[2]");
	private static By HOME_ICON							 =By.xpath("//div[contains(text(),'Home')]");
	private static By MAINTANANCE_ICON					 =By.xpath("//i[@class='menu-icon om-icon-maintenance ng-star-inserted']");
	private static By HOME_ICON1						 =By.xpath("//span[contains(text(),'Home')]");
	private static By TRIPS_ICON						 =By.xpath("(//div[contains(text(),'Trips')])[2]");
	private static By ASSEST_ICON						 =By.xpath("//a[@title='Assets']");
	private static By HELP_ICON							 =By.xpath("//i[@class='menu-icon om-icon-help ng-star-inserted']");
	private static By ASSEST_LK							=By.xpath("//div[text()='Assets']");

	TauriRealTimeDataPage RTD = new TauriRealTimeDataPage();
	public void LoginWithMoreThanOneClient()
	{
		JSONObject OpenmaticClientsjsonObject = JsonReader.getJsonObject("OpenmaticClients");
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		sleep(10000);
		RTD.ClientHomePagePortal();
		waitElementToBeVisible(OPENMATIC_APPS,500);
		if(compareValue(elementGetText(OPENMATIC_APPS), OpenmaticClientsjsonObject.getAsString("CLIENTS_OPENMATIC1_CLIENT"))){
			elementAvailability(PORTAL_DASHBOARD_BT);
			elementAvailability(PORTAL_DETAGTIVE_BT);
			elementAvailability(PORTAL_MAINTENANCE_BT);
			elementAvailability(PORTAL_REPORTS_BT);
			elementAvailability(PORTAL_TRIPS_BT);
			elementAvailability(PORTAL_WINTERSERVICE_BT);
			elementAvailability(PORTAL_ZF_ESYNC_BT);
			elementAvailability(PORTAL_ASSET_DEVICE_MANAGEMENT_BT);
			elementAvailability(PORTAL_BACKOFFICE_BT);
			elementAvailability(PORTAL_HELP_BT);
			elementAvailability(PORTAL_NONAME_BT);
		}
		openmaticAppsGlobalMenuVerification();
	}	

	public void BothClientsGlobalMenu() 
	{
		JSONObject OpenmaticClientsjsonObject = JsonReader.getJsonObject("OpenmaticClients");
		try {
			sleep(5000);
			waitElementVisibleClick(USER_ICON,300);
			sleep(7000);
			if(!elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE).contentEquals(OpenmaticClientsjsonObject.getAsString("CLIENTS_OPENMATIC1_CLIENT"))) {
				elementClick(CLIENTS_OPENMATICS_CLIENT);
				elementClick(CLIENTS_OPENMATIC1_CLIENT);
			}
			compareValue(elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE),OpenmaticClientsjsonObject.getAsString("CLIENTS_OPENMATIC1_CLIENT"));
			elementAvailability(FLEET_ICON,"FLEET_ICON");
			elementAvailability(VEHICLE_ICON,"VEHICLE_ICON");
			elementAvailability(USER_ACTIVE_ICON,"USER_ACTIVE_ICON");
			elementAvailability(GATEWAY_ICON,"GATEWAY_ICON");
			elementAvailability(CLIENTS_ICON,"CLIENTS_ICON");
			waitElementVisibleClick(CLIENTS_OPENMATICS_CLIENT, 300);
			waitElementVisibleClick(CLIENTS_OPENMATIC2_CLIENT,300);
			if(!elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE).contentEquals(OpenmaticClientsjsonObject.getAsString("CLIENTS_OPENMATIC2_CLIENT"))) {
				elementClick(CLIENTS_OPENMATICS_CLIENT);
				elementClick(CLIENTS_OPENMATIC2_CLIENT);
			}
			compareValue(elementGetText(CLIENTS_OPENMATIC_CLIENT_TITLE),OpenmaticClientsjsonObject.getAsString("CLIENTS_OPENMATIC2_CLIENT"));
			elementAvailability(FLEET_ICON,"FLEET_ICON");
			elementAvailability(VEHICLE_ICON,"VEHICLE_ICON");
			elementAvailability(USER_ACTIVE_ICON,"USER_ACTIVE_ICON");
			elementAvailability(GATEWAY_ICON,"GATEWAY_ICON");
			elementAvailability(CLIENTS_ICON,"CLIENTS_ICON");
		} catch (Exception e) {
			testFailed("An exception occured while checking the global menu of both client  and the error message is : "+e.getMessage());
		}
	}

	private void openmaticAppsGlobalMenuVerification()
	{
		try {
			waitElementVisibleClick(PORTAL_DASHBOARD_BT,800);
			waitElementToBeVisible(DASHBOARD_ICON,300);
			waitElementVisibleClick(HOME_ICON,300);
			waitElementVisibleClick(PORTAL_MAINTENANCE_BT, 300);
			waitElementToBeVisible(MAINTANANCE_ICON,300);
			waitElementVisibleClick(HOME_ICON1,400);
			waitElementVisibleClick(PORTAL_TRIPS_BT, 300);
			waitElementToBeVisible(TRIPS_ICON,300);
			waitElementVisibleClick(HOME_ICON,400);
			waitElementVisibleClick(PORTAL_ASSET_DEVICE_MANAGEMENT_BT, 300);
			waitElementToBeVisible(ASSEST_ICON,300);
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_BACKOFFICE_BT, 600);
			waitElementToBeVisible(ASSEST_LK,500);
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_HELP_BT, 500);
			waitElementToBeVisible(HELP_ICON,300);
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			RTD.ClientHomePagePortal();

		} catch (Exception e) {
			testFailed("An exception occured while checking the global menu of both client  and the error message is : "+e.getMessage());
		}
	}
}