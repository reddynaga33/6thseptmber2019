package zf.regression.pages;



import org.openqa.selenium.By;

import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;
import zf.iris.pages.RealTimeDataPage;

public class UIPage extends ElementManager{
	JsonReader jsonData=new JsonReader();
	JsonReader jsonReader=new JsonReader();
	RealTimeDataPage RTD=new RealTimeDataPage();

	private static By MICROSOFTLOGIN_EMAILUSERNAME_EB         =By.xpath("//input[@type='email']");	
	private static By MICROSOFTLOGIN_EMAILPASSWORD_EB         =By.id("i0118");
	private static By MICROSOFTLOGIN_NEXT_BT                  =By.id("idSIButton9");
	private static By MICROSOFTLOGIN_STAYSIGNIN_BT            =By.xpath("//input[@id='idBtn_Back']");
	private static By MICROSOFTLOGIN_ERROR_MSG_TXT            =By.xpath("//div[@id='passwordError']");
	private static By MICROSOFTLOGIN_ERROR_EMAIL_TXT          =By.xpath("//div[@id='usernameError']");
	private static By MICROSOFTLOGIN_SUCCESS_TXT              =By.xpath("//h1[text()='Welcome to Openmatics portal']");

	private static By MICROSOFTLOGIN_SUCCESS_STAY_TXT         =By.xpath("//div[text()='Stay signed in?']");
	private static By OPENMATICS_DASHBOARD_TXT                =By.xpath("(//div[text()='DASHBOARD'])[1]");
	private static By DASHBOARD_DASHBOARD_TXT                 =By.xpath("(//div[text()='Dashboard'])[1]");
	private static By OPENMATICS_DETAGTIVE_TXT				  =By.xpath("(//div[text()='DETAGTIVE'])[1]");
	private static By DETAGTIVE_DETAGTIVE_IMG				  =By.xpath("//div[@class='v-slot v-slot-margin-bottom']/img");
	private static By OPENMATICS_MAINTENENCE_TXT			  =By.xpath("(//div[text()='MAINTENANCE'])[1]");
	private static By MAINTENENCE_MAINTENENCE_TXT			  =By.xpath("(//div[@class='header-content']//span)[2]");
	private static By BACKOFFICE_BACKOFFICE_TXT			      =By.xpath("(//div[@class='header-content']//span)[2]");
	private static By HELP_HELP_TXT			     			  =By.xpath("(//div[@class='header-content']//span)[2]");
	private static By OPENMATICS_REPORTS_TXT				  =By.xpath("(//div[text()='REPORTS'])[1]");
	private static By OPENMATICS_TRIPS_TXT					  =By.xpath("(//div[text()='TRIPS'])[1]");
	private static By TRIPS_TRIPS_TXT					      =By.xpath("(//div[text()='Trips'])[1]");
	private static By OPENMATICS_WINTERSERVICE_TXT			  =By.xpath("(//div[text()='WINTERSERVICE'])[1]");
	private static By OPENMATICS_ZF_ESYNCH_TXT				  =By.xpath("(//div[text()='ZF ESYNC'])[1]");
	private static By OPENMATICS_ASSET_DEV_MANAGEMENT_TXT	  =By.xpath("(//div[text()='ASSET & DEVICE MANAGEMENT'])[1]");
	private static By GATEWAY_ADDGATEWAY_BT                     =By.xpath("//button[contains(text(),'Add Gateway')]");
	private static By OPENMATICS_BACKOFFICE_TXT				  =By.xpath("(//div[text()='BACKOFFICE'])[1]");
	private static By OPENMATICS_HELP_TXT					  =By.xpath("(//div[text()='HELP'])[1]");
	//private static By OPENMATICS_BLANK_TXT					  =By.xpath("(//img[@class='app-box-img'])[11]");
	private static By SPINNER 								  =By.xpath("//div[@class='circle-container']");
	private static By OPENMATICS_ZF_ESYNCH_SPINNER 			  =By.xpath("//sota-app/img");
	private static By OPENMATICS_DASHBOARD_HEADER_TXT 		  =By.xpath("(//div[text()='Dashboard'])[1]");
	
	
    private static By HELP_TAB							=By.xpath("//div[text()='HELP']");	
	private static By HELP_ABOUT_OPENMATICS_TAB			=By.xpath("//span[text()='About OPENMATICS']");
	private static By HELP_DASHBOARD_TAB                =By.xpath("//div/span[text()='Dashboard']");
	private static By HELP_BACK_BT			            =By.xpath("//b[text()='BACK']");
	private static By HELP_INTELIGENT_CONNECTIVITY_TXT	=By.xpath("(//p[@class='ng-star-inserted'])[1]");
	private static By HELP_OPEN_NEW_WORLD               =By.xpath("(//p[@class='ng-star-inserted'])[2]");
	private static By HELP_DB_VEHICLELIST_FILTER =By.xpath("//div/b[text()='Vehicle List and Filter']");
	private static By HELP_DB_VEHICLELIST_TXT           =By.xpath("//div[@class='collapse show ng-star-inserted']");	
	private static By HELP_BACKOFFICE_TAB				=By.xpath("//div/span[text()='Backoffice']");
	private static By HELP_BACKOFFICE_FLEET             =By.xpath("//div/b[text()='Fleets']");
	private static By HELP_BACKOFFICE_FLEET_TXT_P1         =By.xpath("//div[@class='card-body']/p[1]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P2         =By.xpath("//div[@class='card-body']/p[2]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P3         =By.xpath("//div[@class='card-body']/p[3]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P4         =By.xpath("//div[@class='card-body']/p[4]");	
	private static By HELP_BACKOFFICE_FLEET_TXT_P5         =By.xpath("//div[@class='card-body']/p[5]");		
	private static By HELP_BACKOFFICE_CREATE_FLEET       =By.xpath("//div/b[text()='Creating a Fleet']");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP1       =By.xpath("//div[@class='card-body']/p[1]");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP2       =By.xpath("//div[@class='card-body']/p[2]");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP3       =By.xpath("//div[@class='card-body']/p[3]");
	private static By HELP_BACKOFFICE_CREATEFLEET_TXTP4       =By.xpath("//div[@class='card-body']/p[4]");
	private static By HELP_BACKOFFICE_EDIT_FLEET         =By.xpath("//div/b[text()='Editing / Deleting a Fleet']");
	//VEHICLE MANAGEMENT
	private static By HELP_VEHICLE_MANAGEMENT_TAB		=By.xpath("//div/span[text()='Vehicle Management']");
	private static By HELP_VEHICLE_MANAGEMENT_VEHICLE		=By.xpath("//div/b[text()='Vehicles']");
	private static By HELP_VEHICLE_MANAGEMENT_VEHICLE_TXT		=By.xpath("//div[@class='collapse show ng-star-inserted']");	
	private static By HELP_VEHICLE_MANAGEMENT_EDIT_VH	=By.xpath("//div/b[text()='Editing vehicles']");
	private static By HELP_GETTXT	=By.xpath("//div[@class='collapse show ng-star-inserted']");	
	//Help user management
	private static By HELP_USER_MANAGEMENT_TAB			=By.xpath("//div/span[text()='User Management']");
	private static By HELP_USER_MNGT_USER	            =By.xpath("//div/b[text()='Users']");
	private static By HELP_USER_MNGT_CREATE_USER	    =By.xpath("//div/b[text()='Creating Users']");
	
    private static By HELP_DB_VEHICLELISTP1_TXT            =By.xpath("//div[@class='card-body']/p[1]");
    private static By HELP_DB_VEHICLELISTP2_TXT            =By.xpath("//div[@class='card-body']/p[2]");
    private static By HELP_DB_VEHICLELISTP3_TXT            =By.xpath("//div[@class='card-body']/p[3]");
    private static By HELP_DB_VEHICLELISTP4_TXT            =By.xpath("//div[@class='card-body']/p[4]");
    private static By HELP_DB_VEHICLELISTP5_TXT            =By.xpath("//div[@class='card-body']/p[5]");
    private static By HELP_DB_VEHICLELISTP6_TXT            =By.xpath("//div[@class='card-body']/p[6]");
    private static By HELP_DB_VEHICLELISTP7_TXT            =By.xpath("//div[@class='card-body']/p[7]");
    private static By HELP_DB_VEHICLELISTP8_TXT            =By.xpath("//div[@class='card-body']/p[8]");
    
    
    private static By HELP_DB_KPIS                                =By.xpath("//b[text()='KPIs']");
    private static By HELP_DB_KPIS_P1_TXT                  =By.xpath("//p[@class='ng-star-inserted'][1]");
    private static By HELP_DB_KPIS_P2_TXT                  =By.xpath("//p[@class='ng-star-inserted'][2]");
    private static By HELP_DB_KPIS_P3_TXT                  =By.xpath("//p[@class='ng-star-inserted'][3]");
    private static By HELP_DB_KPIS_P4_TXT                  =By.xpath("//p[@class='ng-star-inserted'][4]");
    
    private static By HELP_DB_OVERVIEW                            =By.xpath("//b[text()='Overview on Map']");
    private static By HELP_DB_OVERVIEW_P1_TXT                     =By.xpath("//p[@class='ng-star-inserted'][1]");
    private static By HELP_DB_OVERVIEW_P2_TXT                     =By.xpath("//p[@class='ng-star-inserted'][2]");
    private static By HELP_DB_OVERVIEW_P3_TXT                     =By.xpath("//p[@class='ng-star-inserted'][3]");
    private static By HELP_DB_OVERVIEW_P4_TXT                     =By.xpath("//p[@class='ng-star-inserted'][4]");
    private static By HELP_DB_OVERVIEW_P5_TXT                     =By.xpath("//p[@class='ng-star-inserted'][5]");
    private static By HELP_DB_OVERVIEW_P6_TXT                     =By.xpath("//p[@class='ng-star-inserted'][6]");
    private static By HELP_DB_OVERVIEW_P7_TXT                     =By.xpath("//p[@class='ng-star-inserted'][7]");
    
    private static By HELP_DB_VH_DETAIL                           =By.xpath("//b[text()='Vehicle Detail']");
    private static By HELP_DB_VH_DETAIL_P1_TXT                    =By.xpath("//p[@class='ng-star-inserted'][1]");
    private static By HELP_DB_VH_DETAIL_P2_TXT                    =By.xpath("//p[@class='ng-star-inserted'][2]");
    private static By HELP_DB_VH_DETAIL_P3_TXT                    =By.xpath("//p[@class='ng-star-inserted'][3]");
    private static By HELP_DB_VH_DETAIL_P4_TXT                    =By.xpath("//p[@class='ng-star-inserted'][4]");
    private static By HELP_DB_VH_DETAIL_P5_TXT                    =By.xpath("//p[@class='ng-star-inserted'][5]");
    
    private static By HELP_DB_TECH_OVERVIEW             =By.xpath("//b[text()='Technical Overview']");
    private static By HELP_DB_TECH_OVERVIEW_TXT                   =By.xpath("//p[@class='ng-star-inserted']");
    
    private static By HELP_DB_KPI_SETTINGS                        =By.xpath("//b[text()='KPI Settings']");
    private static By HELP_DB_KPI_SETTINGS_P1_TXT          =By.xpath("//p[@class='ng-star-inserted'][1]");
    private static By HELP_DB_KPI_SETTINGS_P2_TXT          =By.xpath("//p[@class='ng-star-inserted'][2]");
    private static By HELP_DB_KPI_SETTINGS_P3_TXT          =By.xpath("//p[@class='ng-star-inserted'][3]");
    private static By HELP_DB_KPI_SETTINGS_P4_TXT          =By.xpath("//p[@class='ng-star-inserted'][4]");
    private static By HELP_DB_KPI_SETTINGS_P5_TXT          =By.xpath("//p[@class='ng-star-inserted'][5]");
    
    //28-may-2019
    private static By HELP_ABT_OPEN_INTELLIGENT_CONNECTIVITY          =By.xpath("//b[text()='Intelligent connectivity']");
    private static By HELP_ABT_OPEN_FOR_A_NEW_WORLD          =By.xpath("//b[text()='Open for a new world']");

	
	public void microsoftLoginstaysignedin(String userName,String password) {
			try {
				JsonReader.getJsonObject("LoginCredentialsScenarios");
				elementSendKeys(MICROSOFTLOGIN_EMAILUSERNAME_EB,userName);
				elementClick(MICROSOFTLOGIN_NEXT_BT);
				if(elementDisplayed(MICROSOFTLOGIN_EMAILPASSWORD_EB)) {
				elementSendKeyWithActions(MICROSOFTLOGIN_EMAILPASSWORD_EB,passwordDecript(password));
				elementClick(MICROSOFTLOGIN_NEXT_BT);				
				compareText(elementGetText(MICROSOFTLOGIN_SUCCESS_STAY_TXT),jsonData.getJsonData("LoginStaySignedIn"));
				elementClick(MICROSOFTLOGIN_STAYSIGNIN_BT);
			} }catch (Exception e) {
				TestLogger.appInfo(e.getMessage());
			}
		}
			
	public void microsoftLoginWithWrongEmail(String userName) {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("LoginCredentialsScenarios");
			elementSendKeys(MICROSOFTLOGIN_EMAILUSERNAME_EB,userName);
			elementClick(MICROSOFTLOGIN_NEXT_BT);
			compareText(elementGetText(MICROSOFTLOGIN_ERROR_EMAIL_TXT),jsonObject.getAsString("LoginWrongEmailMessage"));
		} catch (Exception e) {
			testFailed("An expection occured while validating Wrong email and the erroe message is : "+e.getMessage());
		}
	}
	public void microsoftLoginWrongPassword(String userName, String password) {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("LoginCredentialsScenarios");
			elementSendKeys(MICROSOFTLOGIN_EMAILUSERNAME_EB,userName);
			elementClick(MICROSOFTLOGIN_NEXT_BT);
			elementSendKeyWithActions(MICROSOFTLOGIN_EMAILPASSWORD_EB,password);
			elementClick(MICROSOFTLOGIN_NEXT_BT);
			compareText(elementGetText(MICROSOFTLOGIN_ERROR_MSG_TXT),jsonObject.getAsString("LoginWrongPasswordErrorMessage"));
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	 
	public void launchAllApplications() throws InterruptedException
	{
		try {
//			RTD.ClientHomePagePortal();
		JSONObject jsonObject = JsonReader.getJsonObject("launchAllApplications");
		
		waitElementVisibleClick(OPENMATICS_DASHBOARD_TXT,100);
		sleep(10000);
		elementAvailability(DASHBOARD_DASHBOARD_TXT);
		compareText(elementGetText(DASHBOARD_DASHBOARD_TXT), jsonObject.getAsString("Dashboard"));
		
//		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
//		waitElementVisibleClick(OPENMATICS_DETAGTIVE_TXT,100);
//		elementAvailability(DETAGTIVE_DETAGTIVE_IMG);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_MAINTENENCE_TXT,100);
		sleep(10000);
		elementAvailability(MAINTENENCE_MAINTENENCE_TXT);
		compareText(elementGetText(MAINTENENCE_MAINTENENCE_TXT), jsonObject.getAsString("Maintenance"));
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_REPORTS_TXT,100); //TODO Functionality implementation is pending 
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_TRIPS_TXT,100);
		sleep(30000);
		elementAvailability(TRIPS_TRIPS_TXT);
		compareText(elementGetText(TRIPS_TRIPS_TXT), jsonObject.getAsString("Trips"));
		
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_WINTERSERVICE_TXT,100); //TODO Functionality implementation is pending 
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_ZF_ESYNCH_TXT,100); //TODO Functionality implementation is pending 
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_ASSET_DEV_MANAGEMENT_TXT,100);
		sleep(10000);
		elementAvailability(GATEWAY_ADDGATEWAY_BT);
		compareText(elementGetText(GATEWAY_ADDGATEWAY_BT), jsonObject.getAsString("AddGateWay"));
		
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		waitElementVisibleClick(OPENMATICS_BACKOFFICE_TXT,100);
		sleep(20000);
		elementAvailability(BACKOFFICE_BACKOFFICE_TXT); 
		compareText(elementGetText(BACKOFFICE_BACKOFFICE_TXT), jsonObject.getAsString("Backoffice"));
		
		
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		waitElementVisibleClick(OPENMATICS_HELP_TXT,100);
		sleep(10000);
		elementAvailability(HELP_HELP_TXT); 
		compareText(elementGetText(HELP_HELP_TXT), jsonObject.getAsString("Help"));
		
		
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}

	}
	public void checkForSpinners() throws InterruptedException
	   {
		elementDisplayed(SPINNER);
		
		waitElementVisibleClick(OPENMATICS_DASHBOARD_TXT,100);
		elementDisplayed(SPINNER);
		//elementDisplayed(OPENMATICS_DASHBOARD_HEADER_TXT);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_DETAGTIVE_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_MAINTENENCE_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_REPORTS_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_TRIPS_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_WINTERSERVICE_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_ZF_ESYNCH_TXT,100);
		elementDisplayed(OPENMATICS_ZF_ESYNCH_SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_ASSET_DEV_MANAGEMENT_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_BACKOFFICE_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		waitElementVisibleClick(OPENMATICS_HELP_TXT,100);
		elementDisplayed(SPINNER);
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		
		/*waitElementVisibleClick(OPENMATICS_BLANK_TXT,100);
		elementDisplayed(SPINNER);*/
      }


	public void checkHelpOpenmatrics()
	{
		try {
			JsonReader.getJsonObject("clickHelpAndVerifyTabs");
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			waitElementVisibleClick(HELP_TAB,300);	
			waitElementVisibleClick(HELP_ABOUT_OPENMATICS_TAB,100);	
			elementAvailability(HELP_ABT_OPEN_INTELLIGENT_CONNECTIVITY);
			compareText(jsonData.getJsonData("IntelligentConnectivityExpMsg"),elementGetText(HELP_INTELIGENT_CONNECTIVITY_TXT));
			elementAvailability(HELP_ABT_OPEN_FOR_A_NEW_WORLD);
		    compareText(jsonData.getJsonData("openForANewWorld"),elementGetText(HELP_OPEN_NEW_WORLD));
		    elementClick(HELP_BACK_BT);	
		}
		catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	public void checkHelpDashBoard()
	{
		try {
			JsonReader.getJsonObject("clickHelpAndVerifyTabs");
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			waitElementVisibleClick(HELP_TAB,300);
			waitElementVisibleClick(HELP_DASHBOARD_TAB,300);
		    
			elementAvailability(HELP_DB_VEHICLELIST_FILTER);
		    elementClick(HELP_DB_VEHICLELIST_FILTER);
		    compareText(jsonData.getJsonData("VehicleListandFilterP1").replaceAll("–", ""),elementGetText(HELP_DB_VEHICLELISTP1_TXT).replaceAll("–", ""));
		    compareText(jsonData.getJsonData("VehicleListandFilterP2"),elementGetText(HELP_DB_VEHICLELISTP2_TXT));
		    compareText(jsonData.getJsonData("VehicleListandFilterP3"),elementGetText(HELP_DB_VEHICLELISTP3_TXT));
		    compareText(jsonData.getJsonData("VehicleListandFilterP4").replaceAll("–", ""),elementGetText(HELP_DB_VEHICLELISTP4_TXT).replaceAll("–", ""));
		    compareText(jsonData.getJsonData("VehicleListandFilterP5"),elementGetText(HELP_DB_VEHICLELISTP5_TXT));
		    compareText(jsonData.getJsonData("VehicleListandFilterP6"),elementGetText(HELP_DB_VEHICLELISTP6_TXT));
		    compareText(jsonData.getJsonData("VehicleListandFilterP7"),elementGetText(HELP_DB_VEHICLELISTP7_TXT));
		    compareText(jsonData.getJsonData("VehicleListandFilterP8").replaceAll("–", ""),elementGetText(HELP_DB_VEHICLELISTP8_TXT).replaceAll("–", ""));
			    
		    elementAvailability(HELP_DB_KPIS);
		    elementClick(HELP_DB_KPIS);
			compareText(jsonData.getJsonData("KPIP1"),elementGetText(HELP_DB_KPIS_P1_TXT));
			compareText(jsonData.getJsonData("KPIP2"),elementGetText(HELP_DB_KPIS_P2_TXT));
			compareText(jsonData.getJsonData("KPIP3"),elementGetText(HELP_DB_KPIS_P3_TXT));
			compareText(jsonData.getJsonData("KPIP4"),elementGetText(HELP_DB_KPIS_P4_TXT));
			
			elementAvailability(HELP_DB_OVERVIEW);  	
		     elementClick(HELP_DB_OVERVIEW);
			 compareText(jsonData.getJsonData("OVERVIEW_P1"),elementGetText(HELP_DB_OVERVIEW_P1_TXT));
			 compareText(jsonData.getJsonData("OVERVIEW_P2"),elementGetText(HELP_DB_OVERVIEW_P2_TXT));
			 compareText(jsonData.getJsonData("OVERVIEW_P3"),elementGetText(HELP_DB_OVERVIEW_P3_TXT));
			 compareText(jsonData.getJsonData("OVERVIEW_P4"),elementGetText(HELP_DB_OVERVIEW_P4_TXT));
			 compareText(jsonData.getJsonData("OVERVIEW_P5"),elementGetText(HELP_DB_OVERVIEW_P5_TXT));
			 compareText(jsonData.getJsonData("OVERVIEW_P6"),elementGetText(HELP_DB_OVERVIEW_P6_TXT));
			 compareText(jsonData.getJsonData("OVERVIEW_P7"),elementGetText(HELP_DB_OVERVIEW_P7_TXT));
			  
			  elementAvailability(HELP_DB_OVERVIEW);
			   elementClick(HELP_DB_VH_DETAIL);
			   compareText(jsonData.getJsonData("VH_DETAIL_P1"),elementGetText(HELP_DB_VH_DETAIL_P1_TXT));
			   compareText(jsonData.getJsonData("VH_DETAIL_P2"),elementGetText(HELP_DB_VH_DETAIL_P2_TXT));
			   compareText(jsonData.getJsonData("VH_DETAIL_P3"),elementGetText(HELP_DB_VH_DETAIL_P3_TXT));
			   compareText(jsonData.getJsonData("VH_DETAIL_P4"),elementGetText(HELP_DB_VH_DETAIL_P4_TXT));
			   compareText(jsonData.getJsonData("VH_DETAIL_P5"),elementGetText(HELP_DB_VH_DETAIL_P5_TXT));
			    
			    elementAvailability(HELP_DB_OVERVIEW);
			    elementClick(HELP_DB_TECH_OVERVIEW);
			    compareText(jsonData.getJsonData("TECH_OVERVIEW_P1"),elementGetText(HELP_DB_TECH_OVERVIEW_TXT));
			    
			    elementAvailability(HELP_DB_OVERVIEW);
			    elementClick(HELP_DB_KPI_SETTINGS);
			    compareText(jsonData.getJsonData("KPI_SETTINGS_P1"),elementGetText(HELP_DB_KPI_SETTINGS_P1_TXT));
			    compareText(jsonData.getJsonData("KPI_SETTINGS_P2"),elementGetText(HELP_DB_KPI_SETTINGS_P2_TXT));
			    compareText(jsonData.getJsonData("KPI_SETTINGS_P3"),elementGetText(HELP_DB_KPI_SETTINGS_P3_TXT));
			    compareText(jsonData.getJsonData("KPI_SETTINGS_P4"),elementGetText(HELP_DB_KPI_SETTINGS_P4_TXT));
			    compareText(jsonData.getJsonData("KPI_SETTINGS_P5"),elementGetText(HELP_DB_KPI_SETTINGS_P5_TXT));
			    elementClick(HELP_BACK_BT);	

		}
		catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	
	public void helpBackOffice()
	{
		try { 
		JsonReader.getJsonObject("clickHelpAndVerifyTabs");
		navigateToPortalUrl(EnvironmentManager.getPortalUrl());
		waitElementVisibleClick(HELP_TAB,300);	
		waitElementVisibleClick(HELP_BACKOFFICE_TAB,300);
		
		elementAvailability(HELP_BACKOFFICE_FLEET);
	    waitElementVisibleClick(HELP_BACKOFFICE_FLEET, 300);
	    compareText(jsonData.getJsonData("BackOffice_Fleet_textP1"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P1));
	    compareText(jsonData.getJsonData("BackOffice_Fleet_textP2"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P2));
	    compareText(jsonData.getJsonData("BackOffice_Fleet_textP3"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P3));
	    compareText(jsonData.getJsonData("BackOffice_Fleet_textP4"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P4));
	    compareText(jsonData.getJsonData("BackOffice_Fleet_textP5"),elementGetText(HELP_BACKOFFICE_FLEET_TXT_P5));
	    
	    elementAvailability(HELP_BACKOFFICE_CREATE_FLEET);
	    elementClick(HELP_BACKOFFICE_CREATE_FLEET);
	    compareText(jsonData.getJsonData("BackOffice_Create_Fleet_textP1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
	    compareText(jsonData.getJsonData("BackOffice_Create_Fleet_textP2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
	    compareText(jsonData.getJsonData("BackOffice_Create_Fleet_textP3"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP3).replaceAll("  ", ""));
	    compareText(jsonData.getJsonData("BackOffice_Create_Fleet_textP4"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP4));
    
	    elementAvailability(HELP_BACKOFFICE_EDIT_FLEET);
	    elementClick(HELP_BACKOFFICE_EDIT_FLEET);
	    compareText(jsonData.getJsonData("Backoffice_Edit_Delete_text1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
	    compareText(jsonData.getJsonData("Backoffice_Edit_Delete_text2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
	    compareText(jsonData.getJsonData("Backoffice_Edit_Delete_text3"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP3));
	    compareText(jsonData.getJsonData("Backoffice_Edit_Delete_text4"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP4));
	    elementClick(HELP_BACK_BT);	
			
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	public void helpVehicleManagement()
	{
		try { 
			JsonReader.getJsonObject("clickHelpAndVerifyTabs");
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			waitElementVisibleClick(HELP_TAB,300);	
			waitElementVisibleClick(HELP_VEHICLE_MANAGEMENT_TAB,300);
			
			elementAvailability(HELP_VEHICLE_MANAGEMENT_VEHICLE);
			waitElementVisibleClick(HELP_VEHICLE_MANAGEMENT_VEHICLE,300);
		    compareText(jsonData.getJsonData("Vehicle_Management_vehicle_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
		    compareText(jsonData.getJsonData("Vehicle_Management_vehicle_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2).replaceAll("  ", ""));
			
		    elementAvailability(HELP_VEHICLE_MANAGEMENT_EDIT_VH);
		    waitElementVisibleClick(HELP_VEHICLE_MANAGEMENT_EDIT_VH,300);
		    compareText(jsonData.getJsonData("VehicleMgmnt_editing vehicle_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
		    compareText(jsonData.getJsonData("VehicleMgmnt_editing vehicle_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
		    compareText(jsonData.getJsonData("VehicleMgmnt_editing vehicle_txt3"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP3));
			elementClick(HELP_BACK_BT);			
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	
	public void helpUserManagement()
	{
		try { 
			JsonReader.getJsonObject("clickHelpAndVerifyTabs");
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			waitElementVisibleClick(HELP_TAB,300);	
			waitElementVisibleClick(HELP_USER_MANAGEMENT_TAB,300);
			
			elementAvailability(HELP_USER_MNGT_USER);
			waitElementVisibleClick(HELP_USER_MNGT_USER,300);
		    compareText(jsonData.getJsonData("User_Management_user_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
		    compareText(jsonData.getJsonData("User_Management_user_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));	
		    
		    elementAvailability(HELP_USER_MNGT_CREATE_USER);
			waitElementVisibleClick(HELP_USER_MNGT_CREATE_USER,300);
		    compareText(jsonData.getJsonData("User_Management_creatingUser_txt1"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP1));
		    compareText(jsonData.getJsonData("User_Management_creatingUser_txt2"),elementGetText(HELP_BACKOFFICE_CREATEFLEET_TXTP2));
			elementClick(HELP_BACK_BT);	
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

}
