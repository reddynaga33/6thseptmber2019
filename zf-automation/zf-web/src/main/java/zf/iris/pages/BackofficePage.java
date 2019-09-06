package zf.iris.pages;

import java.util.Random;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class BackofficePage extends ElementManager {

	JsonReader jsonData=new JsonReader();
	public String FleetName;

	private static By FLEETS_ICON                                    =By.xpath("//a[@title='Fleets']");
	private static By FLEETS_CREATENEWFLEET_BT                       =By.xpath("//span[text()='Create New Fleet']");
	private static By FLEET_BASICDATAFLEETNAME_EB                    =By.xpath("(//input[@type='text'])[2]");
	private static By FLEET_BASICDATAINFOTEXT_EB                     =By.xpath("//textarea[@formcontrolname='description']");
	private static By FLEET_NEXTSTEP_BT                              =By.xpath("//span[text()='Next Step']");
	private static By FLEET_ASSIGNVEHICLESSEARCH_EB                  =By.xpath("//input[@placeholder='Search for available vehicles']");
	private static By FLEET_ASSIGNVEHICLELOADER_BT                   =By.xpath("(//i[@class='om-icon-plus-circle ng-star-inserted'])[1]");
	private static By FLEET_CREATEFLEET_BT                           =By.xpath("//span[text()='Create Fleet']");
	private static By USER_ICON   	 	             				 =By.xpath("//a[@title='Users']");
	private static By USERS_ADDUSER_BT                			     =By.xpath("//button[@class='btn btn-primary create']");
	private static By USERS_ADDUSER_NAME_EB             		     =By.xpath("(//input[@type='text'])[2]");
	private static By USERS_ADDUSER_EMAIL_EB    				     =By.xpath("//input[@type='email']");
	private static By USERS_ADDUSER_SURNAME_EB   				     =By.xpath("(//input[@type='text'])[3]");
	private static By USERS_ADDUSER_USERROLES_EB 					 =By.xpath("//input[@placeholder='Choose user roles']");
	private static By USERS_ADDUSER_SAVE_BT     					 =By.xpath("//span[text()='Save']");
	private static By USERS_ADDUSER_TOASTER_DT  				 	 =By.xpath("//div[@id='toast-container']/div[@ng-repeat='toaster in toasters']");
	private static By VEHICLE_ICON									 =By.xpath("//a[@title='Vehicles']//i[@class='menu-icon om-icon-vehicle-list ng-star-inserted']");
	private static By VEHICLES_EDIT_DATA_BT							 =By.xpath("//button[@class='btn btn-primary btn-edit-data']");
	private static By VEHICLES_SAVE_CHANGES_BT						 =By.xpath("//span[text()='Save']");
	private static By CREATE_TOASTER_MSG_TXT				 	     =By.xpath("//div[@class='toast-title']");
	private static By VEHICLE_FILTER_ST  				             =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static By VEHICLE_SEARCH_EB  				             =By.xpath("//input[@placeholder='Search']");
	private static By VEHICLE_SEARCHFIRSTVALUE_DT  	             	 =By.xpath("(//span[@class='name'])[1]");
	private static By VEHICLE_PTO 	                                 =By.xpath("//span[text()=' PTO ']");
	private static By ROLE_ICON   	 	                        	 =By.xpath("//a[@title='Roles']");
	private static By ROLE_FILTER_ST  				           		 =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static By ROLE_SEARCH_EB  				           		 =By.xpath("//input[@placeholder='Search']");
	private static By ROLE_SEARCHFIRSTVALUE_DT  	            	 =By.xpath("(//span[@class='name'])[1]");
	private static By ROLE_EDIT_BT  	                       		 =By.xpath("//span[text()='Edit Data']");
	private static By ROLE_CREATE_ASSET_CB  	               		 =By.xpath("//span[text()=' Asset Create ']");
	private static By ROLE_SAVE_CHANGE_BT  	                	     =By.xpath("//span[text()='Save Changes']");


	public void CreateFleetAndAddSomeVehicle()
	{
		JSONObject FleetjsonObject = JsonReader.getJsonObject("Fleet");
		try {
			waitElementVisibleClick(FLEETS_ICON,600);
			sleep(5000);
			waitElementVisibleClick(FLEETS_CREATENEWFLEET_BT, 500);

			FleetName=jsonData.getJsonData("fleetname")+generateRandomNumber();
			waitElementToBeVisibleSendValue(FLEET_BASICDATAFLEETNAME_EB,500, FleetName);
			elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, FleetjsonObject.getAsString("infotext"));
			elementClick(FLEET_NEXTSTEP_BT);
			waitElementToBeVisibleSendValue(FLEET_ASSIGNVEHICLESSEARCH_EB,300, FleetjsonObject.getAsString("vehicle"));
			waitElementVisibleClick(FLEET_ASSIGNVEHICLELOADER_BT,300);
			elementClick(FLEET_NEXTSTEP_BT);
			waitElementVisibleClick(FLEET_CREATEFLEET_BT,500);
			verifyToastermessage(CREATE_TOASTER_MSG_TXT,FleetjsonObject,"ToasterMessageSuccessReason",50);

		} catch (Exception e) {
			testFailed("An exception occured and error message is ::"+e.getMessage());
		}
	}


	public void verifyToastermessage(By ByType, JSONObject FleetjsonObject,String Jsondata,long timeoutSeconds) {
		try {

			String toasterMessage = waitElementVisibleGetTextForToaster(ByType,timeoutSeconds);
			compareText(FleetjsonObject.getAsString(Jsondata),toasterMessage);
		}catch (Exception e) {
			testFailed("An exception occured and error message is ::"+e.getMessage());
		}
	}

	public void BackofficeCreateNewUser()
	{
		JSONObject addUserjsonObject = JsonReader.getJsonObject("Tc01addUser");
		try {
			waitElementVisibleClick(USER_ICON,600);
			sleep(20000);
			waitElementVisibleClick(USERS_ADDUSER_BT,700);
			waitElementToBeVisibleSendValue(USERS_ADDUSER_NAME_EB,500, addUserjsonObject.getAsString("Name"));
			elementSendKeys(USERS_ADDUSER_EMAIL_EB, addUserjsonObject.getAsString("Email"));
			elementSendKeys(USERS_ADDUSER_SURNAME_EB, addUserjsonObject.getAsString("SureName"));
			elementSendKeys(USERS_ADDUSER_USERROLES_EB, addUserjsonObject.getAsString("Role"));
			elementClick(USERS_ADDUSER_SAVE_BT);

			verifyUserCreated(addUserjsonObject);
		}catch(Exception e) {
			testFailed("An exception occured and error message is ::"+e.getMessage());
		}
	}

	public void verifyUserCreated(JSONObject addUserjsonObject)
	{
		try {
			String Sucessmsg = waitElementVisibleGetTextForToaster(USERS_ADDUSER_TOASTER_DT,300);
			compareText(addUserjsonObject.getAsString("ToasterMsgSuccess"), Sucessmsg);
		}catch(Exception e) {
			testFailed("An exception occured and error message is ::"+e.getMessage());
		}
	}

	public void BackofficeEditSomeRole() {
		JSONObject roleObject = JsonReader.getJsonObject("VehiclesRoles");
		try {
			waitElementVisibleClick(ROLE_ICON,600);
			sleep(20000);

			if(!elementAvailability(ROLE_SEARCH_EB)) {
				waitElementVisibleClick(ROLE_FILTER_ST,300);
				}
			elementSendKeys(ROLE_SEARCH_EB, "test");
			elementClick(ROLE_SEARCHFIRSTVALUE_DT);
			waitElementVisibleClick(ROLE_EDIT_BT,300);
			sleep(3000);
			waitElementVisibleClick(ROLE_CREATE_ASSET_CB,600);
			elementClick(ROLE_SAVE_CHANGE_BT);
			verifyToastermessage(CREATE_TOASTER_MSG_TXT,roleObject,"RoleToasterMessage",50);
			waitElementVisibleClick(ROLE_EDIT_BT,300);
			sleep(3000);
			waitElementVisibleClick(ROLE_CREATE_ASSET_CB,600);

			elementClick(ROLE_SAVE_CHANGE_BT);

		} catch (Exception e) {
			testFailed("An exception occured and error message is ::"+e.getMessage());
		}
	}

	public void BackofficeEditVechileTypeInSomeVehicle()
	{
		JSONObject VehiclesjsonObject = JsonReader.getJsonObject("VehiclesRoles");		

		try {
			waitElementVisibleClick(VEHICLE_ICON, 300);
			sleep(10000);
			if(!elementAvailability(VEHICLE_SEARCH_EB)) {
				elementClick(VEHICLE_FILTER_ST);
				}
			elementSendKeys(VEHICLE_SEARCH_EB, "TEST");
			waitElementVisibleClick(VEHICLE_SEARCHFIRSTVALUE_DT,300);
			waitElementVisibleClick(VEHICLES_EDIT_DATA_BT,300);
			waitElementVisibleClick(VEHICLE_PTO,300);
			elementClick(VEHICLES_SAVE_CHANGES_BT);
			verifyToastermessage(VehiclesjsonObject,"VehicleToasterMessage",50);
			sleep(3000);
			waitElementVisibleClick(VEHICLES_EDIT_DATA_BT,300);
			waitElementVisibleClick(VEHICLE_PTO,300);
			elementClick(VEHICLES_SAVE_CHANGES_BT);
		} catch (Exception e) {
			testFailed("An exception occured and error message is ::"+e.getMessage());
		}

	}
	public void verifyToastermessage(JSONObject VehiclesjsonObject,String Jsondata, long timeoutSeconds) {
		String toasterText;
		try {
			toasterText = waitElementVisibleGetTextForToaster(CREATE_TOASTER_MSG_TXT,timeoutSeconds);
			compareText(VehiclesjsonObject.getAsString(Jsondata),toasterText);
		} catch (InterruptedException e) {

			testFailed("An exception occured and error message is ::"+e.getMessage());
		}
	}
	public int generateRandomNumber() {
		Random rnd = new Random();
		int randomNum = (int) (rnd.nextInt(9999));
		return randomNum;
	}
}