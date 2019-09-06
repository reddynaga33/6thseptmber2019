package zf.TauriIRIS.pages;

import java.util.Random;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class TauriBackofficePage extends ElementManager {

	JsonReader jsonData=new JsonReader();



	private static  By USER_ICON   	 	             				 =By.xpath("//a[@title='Users']");
	private static  By USERS_ADDUSER_BT                			     =By.xpath("//span[text()='Create New User']");
	private static  By USERS_ADDUSER_NAME_EB             		     =By.xpath("(//input[@type='text'])[2]");
	private static  By USERS_ADDUSER_EMAIL_EB    				     =By.xpath("//input[@type='email']");
	private static  By USERS_ADDUSER_SURNAME_EB   				     =By.xpath("(//input[@type='text'])[3]");
	private static  By USERS_ADDUSER_USERROLES_EB 					 =By.xpath("//input[@placeholder='Choose user roles']");
	private static  By USERS_ADDUSER_SAVE_BT     					 =By.xpath("//span[text()='Save']");
	private static  By USERS_ADDUSER_TOASTER_DT  				 	 =By.xpath("//div[@class='toast-title']");
	private static  By USERS_EDITUSER_ROLE_DD  			  			 =By.xpath("(//button[@tabindex='-1'])[1]");
	private static  By USERS_ADDUSER_FILTER_ST  			  	     =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static  By USERS_ADDUSER_SEARCH_EB  			  		 =By.xpath("//input[@placeholder='Search']");
	private static  By USERS_ADDUSER_SEARCHFIRSTVALUE_DT  	  		 =By.xpath("(//span[@class='name'])[1]");
	private static  By USERS_ADDUSER_EDITUSER_BT   		  			 =By.xpath("//span[text()='Edit Data']");
	private static  By VEHICLE_ICON									 =By.xpath("//a[@title='Vehicles']//i[@class='menu-icon om-icon-vehicle-list ng-star-inserted']");
	private static  By VEHICLES_EDIT_DATA_BT					     =By.xpath("//span[text()='Edit Data']");
	private static  By VEHICLES_TYPE_DD						    	 =By.xpath("(//i[@class='om-icon-filter-down'])[2]");
	private static  By VEHICLES_TYPE_DD_LIST						 =By.xpath("//div[text()='Electric Car']");
	private static  By VEHICLES_SAVE_CHANGES_BT						 =By.xpath("//span[text()='Save']");
	private static  By VEHICLES_SAVED_TOASTER_MSG					 =By.xpath("//div[@class='ng-binding ng-scope']");
	private static  By VEHICLE_SERACH_EB							 =By.xpath("//input[@placeholder='Search']");
	private static  By VEHICLE_FLEET_DB								 =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static  By VEHICLE_SELECT								 =By.xpath("(//span[@class='name'])[1]");
	private static  By VEHICLE_NAME_TITLE							 =By.xpath("//div[@class='name ng-binding']");
	private static  By FLEETTAB_ICON                                 =By.xpath("//a[@title='Fleets']");
	private static  By FLEET_CREATENEWFLEETBUTTON_BT                 =By.xpath("//span[text()='Create New Fleet']");
	private static  By FLEET_BASICDATAFLEETNAME_EB                   =By.xpath("(//input[@type='text'])[2]");
	private static  By FLEET_BASICDATAINFOTEXT_EB                    =By.xpath("//textarea[@formcontrolname='description']");
	private static  By FLEET_NEXTSTEP_BT                             =By.xpath("//span[text()='Next Step']");
	private static  By FLEET_ASSIGNVEHICLESSEARCH_EB                 =By.xpath("//input[@placeholder='Search for available vehicles']");
	private static  By FLEET_ASSIGNVEHICLELOADER_BT                  =By.xpath("(//i[@class='om-icon-plus-circle ng-star-inserted'])[1]");
	private static  By FLEET_CREATEFLEET_BT                          =By.xpath("//span[text()='Create Fleet']");
	private static  By FLEET_CREATE_TOASTER_MSG_TXT				 	 =By.xpath("//div[@class='toast-title']");
	public String   FleetName=null;

	public void CreateFleetAndAddSomeVehicle()
	{
		waitElementVisibleClick(FLEETTAB_ICON,300);
		sleep(25000);
		waitElementVisibleClick(FLEET_CREATENEWFLEETBUTTON_BT, 300);
		JSONObject FleetjsonObject = JsonReader.getJsonObject("Fleet");
		try {
			FleetName=FleetjsonObject.getAsString("fleetname")+getRandomNumber();
			elementSendKeys(FLEET_BASICDATAFLEETNAME_EB, FleetName);
			elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, FleetjsonObject.getAsString("infotext"));
			elementClick(FLEET_NEXTSTEP_BT);
			sleep(10000);
			waitElementToBeVisibleSendValue(FLEET_ASSIGNVEHICLESSEARCH_EB, 300,FleetjsonObject.getAsString("vehicle"));
			waitElementVisibleClick(FLEET_ASSIGNVEHICLELOADER_BT,300);
			elementClick(FLEET_NEXTSTEP_BT);
			elementClick(FLEET_CREATEFLEET_BT);
			verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",50);
		}catch(Exception e)
		{
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
	}
	public void verifyToastermessage(By ByType, String Jsondata,long timeoutSeconds) {
		try {
			String toasterMessage = waitElementVisibleGetTextForToaster(ByType,timeoutSeconds);
			compareText(jsonData.getJsonData(Jsondata),toasterMessage);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public String BackofficeCreateNewUser(){
		String userName=null;
		JSONObject addUserjsonObject = JsonReader.getJsonObject("Tc01addUser");
		try {
			waitElementVisibleClick(USER_ICON,600);
			sleep(20000);
			waitElementVisibleClick(USERS_ADDUSER_BT,700);
			userName=addUserjsonObject.getAsString("Name")+getRandomNumber(1,999);
			waitElementToBeVisibleSendValue(USERS_ADDUSER_NAME_EB,500,userName);
			elementSendKeys(USERS_ADDUSER_EMAIL_EB, addUserjsonObject.getAsString("Email"));
			elementSendKeys(USERS_ADDUSER_SURNAME_EB, addUserjsonObject.getAsString("SureName"));
			elementSendKeys(USERS_ADDUSER_USERROLES_EB, addUserjsonObject.getAsString("Role"));
			waitElementVisibleClick(USERS_EDITUSER_ROLE_DD,600);
			elementClick(USERS_ADDUSER_SAVE_BT);
			verifyUserCreated(addUserjsonObject);
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
		return userName;
	}

	public void verifyUserCreated(JSONObject addUserjsonObject)
	{
		try {
			String Sucessmsg = waitElementVisibleGetTextForToaster(USERS_ADDUSER_TOASTER_DT,300);
			compareText(addUserjsonObject.getAsString("ToasterMsgSuccessCreated"), Sucessmsg);
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void BackofficeEditSomeRole(String createdUser) {
		JSONObject jsonObject = JsonReader.getJsonObject("Tc02EditUser");
		try {
			waitElementVisibleClick(USER_ICON,600);
			sleep(20000);
			waitElementVisibleClick(USERS_ADDUSER_FILTER_ST,600);
			waitElementToBeVisibleSendValue(USERS_ADDUSER_SEARCH_EB,300,createdUser);
			waitElementVisibleClick(USERS_ADDUSER_SEARCHFIRSTVALUE_DT,300);
			waitElementVisibleClick(USERS_ADDUSER_EDITUSER_BT,300);
			sleep(8000);
			elementSendKeys(USERS_ADDUSER_USERROLES_EB, jsonObject.getAsString("EditRole"));
			waitElementVisibleClick(USERS_EDITUSER_ROLE_DD,600);
			elementClick(USERS_ADDUSER_SAVE_BT);
			verifyUserCreated(jsonObject);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void BackofficeEditVechileTypeInSomeVehicle()
	{
		JSONObject VehiclesjsonObject = JsonReader.getJsonObject("Vehicles");		
		try {
			waitElementVisibleClick(VEHICLE_ICON, 300);
			sleep(25000);
			waitElementVisibleClick(VEHICLE_FLEET_DB,300);
			elementSendKeys(VEHICLE_SERACH_EB, jsonData.getJsonData("Vehicle"));
			elementClick(VEHICLE_SELECT);
			sleep(20000);
			compareValue(elementGetText(VEHICLE_NAME_TITLE), jsonData.getJsonData("Vehicle"));
			waitElementVisibleClick(VEHICLES_EDIT_DATA_BT,300);
			sleep(30000);
			elementClick(VEHICLES_TYPE_DD);
			elementClick(VEHICLES_TYPE_DD_LIST);
			elementClick(VEHICLES_SAVE_CHANGES_BT);
			verifyToastermessage(VehiclesjsonObject,"VehicleToasterMessage",300);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}

	}
	public void verifyToastermessage(JSONObject VehiclesjsonObject,String Jsondata, long timeoutSeconds) {
		String toasterText;
		try {
			toasterText = waitElementVisibleGetTextForToaster(VEHICLES_SAVED_TOASTER_MSG,timeoutSeconds);
			compareText(VehiclesjsonObject.getAsString(Jsondata),toasterText);
		} catch (InterruptedException e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	public int generateRandomNumber() {
		Random rnd = new Random();
		int randomNum = (int) (rnd.nextInt(9999));
		return randomNum;
	}
}