package zf.iris.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;

public class RealTimeDataPage extends ElementManager{
	JsonReader jsonData=new JsonReader();
	
	private static By PORTAL_DASHBOARD_ICON                             =By.xpath("//div[text()='DASHBOARD']");
	private static By GLOBAL_SETTINGS_ICON                              =By.xpath("//div[text()='Settings']");
	private static By SETTING_KPISETTING_LK                             =By.xpath("//span[text()='KPI Settings']");
	private static By SETTING_KPISETTING_AMOUNTOFACTIVEVEHICLES_CB   	=By.xpath("(//div[@class='item input ng-scope'])[5]");
	private static By DASHBOARD_ICON                                    =By.xpath("//div[@class='text ng-scope ng-binding'][text()='Dashboard']");
	private static By DASHBOARD_ACTIVEVIHICLE_TXT                       =By.xpath("//div[text()='Active vehicles']");
	private static By DASHBOARD_ACTIVEVIHICLE_NUMBER              		=By.xpath("//div[text()='Active vehicles']/preceding-sibling::div[@class='value']/span[@class='ng-binding']");
	private static By DASHBOARD_NOOFACTIVEVIHICLE_COUNT        			=By.xpath("//div[@class='vehicle-name']");
	private static By DASHBOARD_VEHICLE_DETAILS_ARROW					=By.xpath("(//div[@class='active-icon'])[1]");
	private static By DASHBOARD_VEHICLEDETAILSARROW_VEHICLESETTINGS_BT	=By.xpath("//button[text()='Vehicle settings']");
	private static By DASHBOARD_VEHICLESETTINGS_EDITDATA_BT				=By.xpath("//div/div/div/div/button");
	private static By DASHBOARD_VEHICLESETTINGS_VEHICLETYPE_DD			=By.xpath("//div[1]/div[1]/om-dropdown/div/div/div/span");
	private static By DASHBOARD_VEHICLESETTINGS_VEHICLETYPE_DD2		    =By.xpath("//div[1]/div[1]/om-dropdown/div/ul/li[2]");
	private static By DASHBOARD__EDITVEHICLES_SAVE_BT		         	=By.xpath("//button[@disabled='disabled']");
	private static By DASHBOARD_VEHICLEDETAILSARROW_ROW2				=By.xpath("(//div[@class='active-icon'])[1]");
	private static By DASHBOARD_VEHICLE_SPEED_METER						=By.xpath("(//*[@id='text'])[1]");
	private static By DASHBOARD__ACTIVE_VEHICLES						=By.xpath("//div[text()='Active vehicles']");
	private static By DAHBOARD_PAGE										=By.xpath("//h4[@class='panel-title ng-binding']");
	private static By DAHBOARD_EXPAND_ARROW								=By.xpath("//div[@id='filter-toggle']");
	private static By DAHBOARD_SEARCH_EB								=By.xpath("//input[@placeholder='Search']");
	String ACTIVEVEHICLE_LT 											="(//div[@class='item-content'])[";
	String ACTIVE_STATUS_ERROR											="]//div[@class='event-icon active-status-error-icon']";
	String ACTIVE_GPS_ERROR                                             ="]//div[@class='event-icon ng-scope event-never-localized-active']";
	String ACTIVE_VEHICLE_ARROW_ICON									="(//div[@class='active-icon'])[";
	String DASHBOARD_CLIENT_TXT											="(//h4[@class='panel-title'])[";

	public void ClientHomePagePortal() {
		sleep(10000);
		int Count=elementCount(DAHBOARD_PAGE);
		if(!elementGetText(DAHBOARD_PAGE).contains("Test client")) {
			for(int i=1;i<=Count;i++) {
				if(elementGetText(By.xpath(DASHBOARD_CLIENT_TXT+i+"]")).contentEquals("Test client")) {
					int j=i+1;
					ScrollTillElementVisible(By.xpath(DASHBOARD_CLIENT_TXT+j+"]"));
					waitElementVisibleClick(By.xpath(DASHBOARD_CLIENT_TXT+i+"]"),300);
					break;
				}
			}
		}
	}
	public void verifyActiveVehiclesDisplay()
	{
		try {
			navigateToPortalUrl(EnvironmentManager.getIRISPortalUrl());
			sleep(20000);
			ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_DASHBOARD_ICON,300);
			waitElementVisibleClick(GLOBAL_SETTINGS_ICON,300);
			waitElementVisibleClick(SETTING_KPISETTING_LK,300);	
			checkActiveVehicleCheckbox( SETTING_KPISETTING_AMOUNTOFACTIVEVEHICLES_CB);
			waitElementVisibleClick(DASHBOARD_ICON,300);
			waitElementVisibleClick(DASHBOARD_ACTIVEVIHICLE_TXT,300);
			String str=elementGetText(DASHBOARD_ACTIVEVIHICLE_NUMBER);
			String[] numbers = str.split("/");
			System.out.println(numbers[0]);
			int countOfactiveVihicle= elementCount(DASHBOARD_NOOFACTIVEVIHICLE_COUNT);	
			String ActiveVihiclecount=String.valueOf(countOfactiveVihicle);		   
			compareText(numbers[0],ActiveVihiclecount);	
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void clickEditVehicleAndEdit() {
		try {
			navigateToPortalUrl(EnvironmentManager.getIRISPortalUrl());
			sleep(20000);
			ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_DASHBOARD_ICON,300);
			waitElementVisibleClick(DASHBOARD__ACTIVE_VEHICLES,300);
			waitElementVisibleClick(DASHBOARD_VEHICLE_DETAILS_ARROW,300);
			switchToWindow();
			waitElementVisibleClick(DASHBOARD_VEHICLEDETAILSARROW_VEHICLESETTINGS_BT,300);
			waitElementVisibleClick(DASHBOARD_VEHICLESETTINGS_EDITDATA_BT,300);
			info("Save button is disabled before editing ");
			waitElementVisibleClick(DASHBOARD_VEHICLESETTINGS_VEHICLETYPE_DD,300);
			waitElementVisibleClick(DASHBOARD_VEHICLESETTINGS_VEHICLETYPE_DD2,300);
			if(!elementDisplayed(DASHBOARD__EDITVEHICLES_SAVE_BT)) {
				testPassed("Save button is enabled after editing the value ");
			}
		}
		catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}

	}
	public void vehicleSpeedCheck()
	{
		try {
			navigateToPortalUrl(EnvironmentManager.getIRISPortalUrl());
			sleep(20000);
			ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_DASHBOARD_ICON,300);
			waitElementVisibleClick(DASHBOARD__ACTIVE_VEHICLES,300);
			waitElementVisibleClick(DAHBOARD_EXPAND_ARROW,300);
			elementSendKeys(DAHBOARD_SEARCH_EB,"Test Vehicle3");
			waitElementVisibleClick(DASHBOARD_VEHICLEDETAILSARROW_ROW2,300);
			switchToWindow();
			String vehicleDataBefore=elementGetText(DASHBOARD_VEHICLE_SPEED_METER);
			System.out.println("VehicleDataBefore : "+ vehicleDataBefore);
			Thread.sleep(15000);
			String vehicleDataAfter=elementGetText(DASHBOARD_VEHICLE_SPEED_METER);
			System.out.println("VehicleDataAfter : "+ vehicleDataAfter);
			if(vehicleDataBefore.equals(vehicleDataAfter))
			{
				testFailed("Vehicle Data is Not Updating");
			}else {
				testPassed("Vehicle Data is Updating");
			}
		}
		catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}


}
