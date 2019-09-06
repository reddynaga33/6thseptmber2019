package zf.iris.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class HelpPage extends ElementManager{
	JsonReader jsonData=new JsonReader();

	private static By HELP_TAB							  =By.xpath("//div[text()='HELP']");	
	private static By HELP_ABOUT_OPENMATICS_TAB		      =By.xpath("//span[text()='About OPENMATICS']");
	private static By HELP_DASHBOARD_TAB                  =By.xpath("//div/span[text()='Dashboard']");
	private static By HELP_BACK_BT			              =By.xpath("//b[text()='BACK']");
	private static By HELP_INTELIGENT_CONNECTIVITY_TXT	  =By.xpath("(//p[@class='ng-star-inserted'])[1]");
	private static By HELP_OPEN_NEW_WORLD                 =By.xpath("(//p[@class='ng-star-inserted'])[2]");
	private static By HELP_DB_VEHICLELIST_FILTER          =By.xpath("//div/b[text()='Vehicle List and Filter']");
	private static By HELP_BACKOFFICE_TAB			      =By.xpath("//div/span[text()='Backoffice']");
	private static By HELP_BACKOFFICE_FLEET               =By.xpath("//div/b[text()='Fleets']");
	private static By HELP_BACKOFFICE_FLEET_TXT_P1        =By.xpath("//div[@class='card-body']/p[1]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P2        =By.xpath("//div[@class='card-body']/p[2]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P3        =By.xpath("//div[@class='card-body']/p[3]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P4        =By.xpath("//div[@class='card-body']/p[4]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P5        =By.xpath("//div[@class='card-body']/p[5]");		
	private static By HELP_BACKOFFICE_CREATE_FLEET        =By.xpath("//div/b[text()='Creating a Fleet']");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP1   =By.xpath("//div[@class='card-body']/p[1]");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP2   =By.xpath("//div[@class='card-body']/p[2]");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP3   =By.xpath("//div[@class='card-body']/p[3]");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP4   =By.xpath("//div[@class='card-body']/p[4]");
	private static By HELP_BACKOFFICE_EDIT_FLEET          =By.xpath("//div/b[text()='Editing / Deleting a Fleet']");
	private static By HELP_VEHICLE_MANAGEMENT_TAB		  =By.xpath("//div/span[text()='Vehicle Management']");
	private static By HELP_VEHICLE_MANAGEMENT_VEHICLE	  =By.xpath("//div/b[text()='Vehicles']");
	private static By HELP_VEHICLE_MANAGEMENT_EDIT_VH	  =By.xpath("//div/b[text()='Editing vehicles']");
	private static By HELP_USER_MANAGEMENT_TAB			  =By.xpath("//div/span[text()='User Management']");
	private static By HELP_USER_MNGT_USER				  =By.xpath("//div/b[text()='Users']");
	private static By HELP_USER_MNGT_CREATE_USER	      =By.xpath("//div/b[text()='Creating Users']");
	private static By HELP_DB_VEHICLELISTP1_TXT           =By.xpath("//div[@class='card-body']/p[1]");
	private static By HELP_DB_VEHICLELISTP2_TXT           =By.xpath("//div[@class='card-body']/p[2]");
	private static By HELP_DB_VEHICLELISTP3_TXT           =By.xpath("//div[@class='card-body']/p[3]");
	private static By HELP_DB_VEHICLELISTP4_TXT           =By.xpath("//div[@class='card-body']/p[4]");
	private static By HELP_DB_VEHICLELISTP5_TXT           =By.xpath("//div[@class='card-body']/p[5]");
	private static By HELP_DB_VEHICLELISTP6_TXT           =By.xpath("//div[@class='card-body']/p[6]");
	private static By HELP_DB_VEHICLELISTP7_TXT           =By.xpath("//div[@class='card-body']/p[7]");
	private static By HELP_DB_VEHICLELISTP8_TXT           =By.xpath("//div[@class='card-body']/p[8]");
	private static By HELP_DB_KPIS                        =By.xpath("//b[text()='KPIs']");
	private static By HELP_DB_KPIS_P1_TXT                 =By.xpath("//p[@class='ng-star-inserted'][1]");
	private static By HELP_DB_KPIS_P2_TXT                 =By.xpath("//p[@class='ng-star-inserted'][2]");
	private static By HELP_DB_KPIS_P3_TXT                 =By.xpath("//p[@class='ng-star-inserted'][3]");
	private static By HELP_DB_KPIS_P4_TXT                 =By.xpath("//p[@class='ng-star-inserted'][4]");
	private static By HELP_DB_OVERVIEW                    =By.xpath("//b[text()='Overview on Map']");
	private static By HELP_DB_OVERVIEW_P1_TXT             =By.xpath("//p[@class='ng-star-inserted'][1]");
	private static By HELP_DB_OVERVIEW_P2_TXT             =By.xpath("//p[@class='ng-star-inserted'][2]");
	private static By HELP_DB_OVERVIEW_P3_TXT             =By.xpath("//p[@class='ng-star-inserted'][3]");
	private static By HELP_DB_OVERVIEW_P4_TXT   	      =By.xpath("//p[@class='ng-star-inserted'][4]");
	private static By HELP_DB_OVERVIEW_P5_TXT             =By.xpath("//p[@class='ng-star-inserted'][5]");
	private static By HELP_DB_OVERVIEW_P6_TXT             =By.xpath("//p[@class='ng-star-inserted'][6]");
	private static By HELP_DB_OVERVIEW_P7_TXT             =By.xpath("//p[@class='ng-star-inserted'][7]");
	private static By HELP_DB_VH_DETAIL                   =By.xpath("//b[text()='Vehicle Detail']");
	private static By HELP_DB_VH_DETAIL_P1_TXT            =By.xpath("//p[@class='ng-star-inserted'][1]");
	private static By HELP_DB_VH_DETAIL_P2_TXT            =By.xpath("//p[@class='ng-star-inserted'][2]");
	private static By HELP_DB_VH_DETAIL_P3_TXT            =By.xpath("//p[@class='ng-star-inserted'][3]");
	private static By HELP_DB_VH_DETAIL_P4_TXT            =By.xpath("//p[@class='ng-star-inserted'][4]");
	private static By HELP_DB_VH_DETAIL_P5_TXT            =By.xpath("//p[@class='ng-star-inserted'][5]");
	private static By HELP_DB_TECH_OVERVIEW               =By.xpath("//b[text()='Technical Overview']");
	private static By HELP_DB_TECH_OVERVIEW_TXT           =By.xpath("//p[@class='ng-star-inserted']");
	private static By HELP_DB_KPI_SETTINGS                =By.xpath("//b[text()='KPI Settings']");
	private static By HELP_DB_KPI_SETTINGS_P1_TXT   	  =By.xpath("//p[@class='ng-star-inserted'][1]");
	private static By HELP_DB_KPI_SETTINGS_P2_TXT         =By.xpath("//p[@class='ng-star-inserted'][2]");
	private static By HELP_DB_KPI_SETTINGS_P3_TXT         =By.xpath("//p[@class='ng-star-inserted'][3]");
	private static By HELP_DB_KPI_SETTINGS_P4_TXT         =By.xpath("//p[@class='ng-star-inserted'][4]");
	private static By HELP_DB_KPI_SETTINGS_P5_TXT         =By.xpath("//p[@class='ng-star-inserted'][5]");

	RealTimeDataPage RTD=new RealTimeDataPage();

	public void helpAndVerifyTabs()
	{
		try {
			JSONObject clickHelpAndVerifyTabsjsonObject = JsonReader.getJsonObject("clickHelpAndVerifyTabs");
			navigateToPortalUrl(EnvironmentManager.getIRISPortalUrl());
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(HELP_TAB,300);
			waitElementVisibleClick(HELP_ABOUT_OPENMATICS_TAB,300);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("IntelligentConnectivityExpMsg"),elementGetText(HELP_INTELIGENT_CONNECTIVITY_TXT));			
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("openForANewWorld"),elementGetText(HELP_OPEN_NEW_WORLD));
			elementClick(HELP_BACK_BT);	
			elementClick(HELP_DASHBOARD_TAB);	    
			elementClick(HELP_DB_VEHICLELIST_FILTER);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP1").replaceAll("–", ""),elementGetText(HELP_DB_VEHICLELISTP1_TXT).replaceAll("–", ""));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP2"),elementGetText(HELP_DB_VEHICLELISTP2_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP3"),elementGetText(HELP_DB_VEHICLELISTP3_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP4").replaceAll("–", ""),elementGetText(HELP_DB_VEHICLELISTP4_TXT).replaceAll("–", ""));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP5"),elementGetText(HELP_DB_VEHICLELISTP5_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP6"),elementGetText(HELP_DB_VEHICLELISTP6_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP7"),elementGetText(HELP_DB_VEHICLELISTP7_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleListandFilterP8").replaceAll("–", ""),elementGetText(HELP_DB_VEHICLELISTP8_TXT).replaceAll("–", ""));
			elementClick(HELP_DB_KPIS);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPIP1"),elementGetText(HELP_DB_KPIS_P1_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPIP2"),elementGetText(HELP_DB_KPIS_P2_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPIP3"),elementGetText(HELP_DB_KPIS_P3_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPIP4"),elementGetText(HELP_DB_KPIS_P4_TXT));
			elementClick(HELP_DB_OVERVIEW);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P1"),elementGetText(HELP_DB_OVERVIEW_P1_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P2"),elementGetText(HELP_DB_OVERVIEW_P2_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P3"),elementGetText(HELP_DB_OVERVIEW_P3_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P4"),elementGetText(HELP_DB_OVERVIEW_P4_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P5"),elementGetText(HELP_DB_OVERVIEW_P5_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P6"),elementGetText(HELP_DB_OVERVIEW_P6_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("OVERVIEW_P7"),elementGetText(HELP_DB_OVERVIEW_P7_TXT));
			elementClick(HELP_DB_VH_DETAIL);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VH_DETAIL_P1"),elementGetText(HELP_DB_VH_DETAIL_P1_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VH_DETAIL_P2"),elementGetText(HELP_DB_VH_DETAIL_P2_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VH_DETAIL_P3"),elementGetText(HELP_DB_VH_DETAIL_P3_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VH_DETAIL_P4"),elementGetText(HELP_DB_VH_DETAIL_P4_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VH_DETAIL_P5"),elementGetText(HELP_DB_VH_DETAIL_P5_TXT));
			elementClick(HELP_DB_TECH_OVERVIEW);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("TECH_OVERVIEW_P1"),elementGetText(HELP_DB_TECH_OVERVIEW_TXT));
			elementClick(HELP_DB_KPI_SETTINGS);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPI_SETTINGS_P1"),elementGetText(HELP_DB_KPI_SETTINGS_P1_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPI_SETTINGS_P2"),elementGetText(HELP_DB_KPI_SETTINGS_P2_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPI_SETTINGS_P3"),elementGetText(HELP_DB_KPI_SETTINGS_P3_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPI_SETTINGS_P4"),elementGetText(HELP_DB_KPI_SETTINGS_P4_TXT));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("KPI_SETTINGS_P5"),elementGetText(HELP_DB_KPI_SETTINGS_P5_TXT));
			elementClick(HELP_BACK_BT);	
			elementClick(HELP_BACKOFFICE_TAB);
			waitElementVisibleClick(HELP_BACKOFFICE_FLEET, 300);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Fleet_textP1"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Fleet_textP2"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P2));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Fleet_textP3"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P3));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Fleet_textP4"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P4));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Fleet_textP5"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P5));
			elementClick(HELP_BACKOFFICE_CREATE_FLEET);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Create_Fleet_textP1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Create_Fleet_textP2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Create_Fleet_textP3"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP3).replaceAll("  ", ""));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("BackOffice_Create_Fleet_textP4"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP4));
			elementClick(HELP_BACKOFFICE_EDIT_FLEET);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("Backoffice_Edit_Delete_text1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("Backoffice_Edit_Delete_text2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("Backoffice_Edit_Delete_text3"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP3));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("Backoffice_Edit_Delete_text4"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP4));
			elementClick(HELP_BACK_BT);	
			waitElementVisibleClick(HELP_VEHICLE_MANAGEMENT_TAB,300);
			waitElementVisibleClick(HELP_VEHICLE_MANAGEMENT_VEHICLE,300);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("Vehicle_Management_vehicle_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("Vehicle_Management_vehicle_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2).replaceAll("  ", ""));
			waitElementVisibleClick(HELP_VEHICLE_MANAGEMENT_EDIT_VH,300);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleMgmnt_editing vehicle_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleMgmnt_editing vehicle_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("VehicleMgmnt_editing vehicle_txt3"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP3));
			elementClick(HELP_BACK_BT);
			waitElementVisibleClick(HELP_USER_MANAGEMENT_TAB,300);
			waitElementVisibleClick(HELP_USER_MNGT_USER,300);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("User_Management_user_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("User_Management_user_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
			waitElementVisibleClick(HELP_USER_MNGT_CREATE_USER,300);
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("User_Management_creatingUser_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
			compareText(clickHelpAndVerifyTabsjsonObject.getAsString("User_Management_creatingUser_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
			elementClick(HELP_BACK_BT);	
		}
		catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
}

