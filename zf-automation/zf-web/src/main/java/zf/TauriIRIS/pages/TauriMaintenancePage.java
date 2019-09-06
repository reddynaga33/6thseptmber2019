package zf.TauriIRIS.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;

public class TauriMaintenancePage extends ElementManager{
	JsonReader jsonData=new JsonReader();
	TauriRealTimeDataPage RD = new TauriRealTimeDataPage();
	private static By MAINTENANCE_ICON                  		 =By.xpath("//div[text()='MAINTENANCE']");
	private static By MAINTENANCE_FIRSTDEFAULTFLEET_VEHICLE      =By.xpath("(//div[@class='vehicle-row flex-wrapper'])[1]");
	private static By MAINTENANCE_EDIT_INTERVEL_BT               =By.xpath("//button[text()='Edit intervals ']");
	private static By MAINTENANCE_INTERVALTIME_EDIT_TXT 		 =By.xpath("//input[@placeholder='Maintenance interval time']");
	private static By MAINTENANCE_INTERVALKM_EDIT_TXT   		 =By.xpath("//input[@placeholder='Maintenance interval km']");
	private static By MAINTENANCE_OILCHANGE_INTERVALTIME_EDIT_TXT=By.xpath("//input[@placeholder='Oil change interval time']");
	private static By MAINTENANCE_OIL_CHANGE_INTERVAL_KM_EDIT_TXT=By.xpath("//input[@placeholder='Oil change interval km']");
	private static By MAINTENANCE_MOT_INTERVALTIME_EDIT_TXT      =By.xpath("//input[@placeholder='MOT interval time']");		
	private static By MAINTENANCE_SAVE_DATA_BT                   =By.xpath("//button[text()=' Save Data ']");
	private static By MAINTENANCE_SAVE_SUCCESS_TXT      		 =By.xpath("//div[text()='Save maintenance info success']");


	public void verifyMainteneceEditSavechange()
	{
		try {
			JsonReader.getJsonObject("mainteneceEditSavechange");
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			RD.ClientHomePagePortal();
			waitElementVisibleClick(MAINTENANCE_ICON,300);	
			sleep(20000);
			refreashPage();
			waitElementVisibleClick(MAINTENANCE_FIRSTDEFAULTFLEET_VEHICLE,300);
			waitElementVisibleClick(MAINTENANCE_EDIT_INTERVEL_BT,300);	
			String intervalTime = elementGetValue(MAINTENANCE_INTERVALTIME_EDIT_TXT, "value");
			String intervalKm = elementGetValue(MAINTENANCE_INTERVALKM_EDIT_TXT, "value");
			String oilIntervalTime = elementGetValue(MAINTENANCE_OILCHANGE_INTERVALTIME_EDIT_TXT, "value");
			String oilIntervalKm = elementGetValue(MAINTENANCE_OIL_CHANGE_INTERVAL_KM_EDIT_TXT, "value");
			String motIntervalTime = elementGetValue(MAINTENANCE_MOT_INTERVALTIME_EDIT_TXT, "value");
			elementSendKeys(MAINTENANCE_INTERVALTIME_EDIT_TXT, jsonData.getJsonData("Maintenance_interval_time"));
			elementSendKeys(MAINTENANCE_INTERVALKM_EDIT_TXT, jsonData.getJsonData("Maintenance_interval_km"));
			elementSendKeys(MAINTENANCE_OILCHANGE_INTERVALTIME_EDIT_TXT, jsonData.getJsonData("Oil_change_interval_time"));
			elementSendKeys(MAINTENANCE_OIL_CHANGE_INTERVAL_KM_EDIT_TXT, jsonData.getJsonData("Oil_change_interval_km"));
			elementSendKeys(MAINTENANCE_MOT_INTERVALTIME_EDIT_TXT, jsonData.getJsonData("Inspection_interval_time"));
			waitElementVisibleClick(MAINTENANCE_SAVE_DATA_BT,300);
			compareText(elementGetText(MAINTENANCE_SAVE_SUCCESS_TXT), jsonData.getJsonData("Save_maintenance_success"));
			waitElementVisibleClick(MAINTENANCE_EDIT_INTERVEL_BT,300);	
			elementSendKeys(MAINTENANCE_INTERVALTIME_EDIT_TXT,intervalTime);
			elementSendKeys(MAINTENANCE_INTERVALKM_EDIT_TXT,intervalKm);
			elementSendKeys(MAINTENANCE_OILCHANGE_INTERVALTIME_EDIT_TXT,oilIntervalTime);
			elementSendKeys(MAINTENANCE_OIL_CHANGE_INTERVAL_KM_EDIT_TXT, oilIntervalKm);
			elementSendKeys(MAINTENANCE_MOT_INTERVALTIME_EDIT_TXT, motIntervalTime);
			waitElementVisibleClick(MAINTENANCE_SAVE_DATA_BT,300);
			compareText(elementGetText(MAINTENANCE_SAVE_SUCCESS_TXT), jsonData.getJsonData("Save_maintenance_success"));
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
}
