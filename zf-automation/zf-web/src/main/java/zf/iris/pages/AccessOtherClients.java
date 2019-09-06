package zf.iris.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class AccessOtherClients extends ElementManager{

	JsonReader jsonData=new JsonReader();

	private static  By PORTAL_DASHBOARD_BT              	  =By.xpath("//div[text()='DASHBOARD']");
	private static  By PORTAL_DETAGTIVE_BT              	  =By.xpath("//div[text()='DETAGTIVE']");
	private static  By PORTAL_MAINTENANCE_BT              	  =By.xpath("//div[text()='MAINTENANCE']");
	private static  By PORTAL_REPORTS_BT              	      =By.xpath("//div[text()='REPORTS']");
	private static  By PORTAL_TRIPS_BT              	      =By.xpath("//div[text()='TRIPS']");
	private static  By PORTAL_WINTERSERVICE_BT                =By.xpath("//div[text()='WINTERSERVICE']");
	private static  By PORTAL_ZF_ESYNC_BT              	      =By.xpath("//div[text()='TRIPS']");
	private static  By PORTAL_ASSET_DEVICE_MANAGEMENT_BT      =By.xpath("//div[text()='WINTERSERVICE']");
	private static  By PORTAL_BACKOFFICE_BT              	  =By.xpath("//div[text()='BACKOFFICE']");
	private static  By PORTAL_HELP_BT              	          =By.xpath("//div[text()='HELP']");
	private static  By PORTAL_RESTRICTION_TXT              	  =By.xpath("//div[@class='text-header ng-scope']");
	private static By DASHBOARD_DASHBOARD_TXT                 =By.xpath("(//div[text()='Dashboard'])[1]");
	RealTimeDataPage RTD=new RealTimeDataPage();

	public void checkDashboardOfUserwithOneClientAccess() {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("launchAllApplications");
			navigateToPortalUrl(EnvironmentManager.getIRISPortalUrl());
			sleep(9000);
			RTD.ClientHomePagePortal();
			if(waitElementToBeVisible(PORTAL_DASHBOARD_BT,300)) {
				elementAvailability(PORTAL_DASHBOARD_BT, "PORTAL_DASHBOARD_BT");
				elementAvailability(PORTAL_DETAGTIVE_BT, "PORTAL_DETAGTIVE_BT");
				elementAvailability(PORTAL_MAINTENANCE_BT, "PORTAL_MAINTENANCE_BT");
				elementAvailability(PORTAL_REPORTS_BT, "PORTAL_REPORTS_BT");
				elementAvailability(PORTAL_TRIPS_BT, "PORTAL_TRIPS_BT");
				elementAvailability(PORTAL_WINTERSERVICE_BT, "PORTAL_WINTERSERVICE_BT");
				elementAvailability(PORTAL_ZF_ESYNC_BT, "PORTAL_ZF_ESYNC_BT");
				elementAvailability(PORTAL_ASSET_DEVICE_MANAGEMENT_BT, "PORTAL_ASSET_DEVICE_MANAGEMENT_BT");

				elementAvailability(PORTAL_BACKOFFICE_BT, "PORTAL_BACKOFFICE_BT");
				elementAvailability(PORTAL_HELP_BT, "PORTAL_HELP_BT");
				elementClick(PORTAL_DASHBOARD_BT);
				sleep(10000);
				compareText(elementGetText(DASHBOARD_DASHBOARD_TXT), jsonObject.getAsString("Dashboard"));

			}else {
				testFailed("Page is not loaded properly");
			}}catch(Exception e) {
				testFailed("An Exception occured while checking the portal and the error message is : "+e.getMessage());
			}}


	public void CheckDashboardOfAnotherClientAndUserWithNoAccess() {
		try {

			JSONObject checkDashboardOfUserwithOneClientAccessObject = JsonReader.getJsonObject("checkDashboardOfUserwithOneClientAccess");
			navigateToIRISOtherClientUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));
			navigateToIRISOtherClientDashboardUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));
			navigateToIRISOtherClientTripsUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));
			navigateToIRISOtherClientMaintenanceUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));
			navigateToIRISOtherClientAssetUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));
			navigateToIRISOtherClientBackofficeUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));
			navigateToIRISOtherClienthelpUrl();
			sleep(8000);
			compareText(checkDashboardOfUserwithOneClientAccessObject.getAsString("ToasterMessageFailed"),elementGetText(PORTAL_RESTRICTION_TXT));


		}catch(Exception e) {
			testFailed("An Exception occured while checking the portal and the error message is : "+e.getMessage());
		}
	}

}
