package zf.TauriIRIS.pages;

import org.openqa.selenium.By;

import framework.DriverManager;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.TestLogger;

public class TauriTripSelectSomeVehicleAndTimeIntervalPage extends ElementManager{

	TauriRealTimeDataPage RTD=new TauriRealTimeDataPage();
	private static By TRIP_ICON						= By.xpath("//div[contains(text(),'TRIPS')]");
	private static By TRIP_SELECTION_DB				= By.xpath("//input[@placeholder='Select Fleet']");
	private static By TRIP_ALLFLEET_SELECT			= By.xpath("//a[contains(text(),'All Fleets')]");
	private static By TRIP_VEHILCE_SELECTION_DB 	= By.xpath("//input[@placeholder='Select Vehicle']");
	
	public void SelectSomeVehicleAndTimeInterval() {

		try {
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(TRIP_ICON, 500);
			waitElementVisibleClick(TRIP_SELECTION_DB,300);
			elementClick(TRIP_ALLFLEET_SELECT);
			elementClick(TRIP_VEHILCE_SELECTION_DB);
			DriverManager.getDriverInstance().findElement(TRIP_ICON).isDisplayed();
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
}