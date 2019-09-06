package zf.iris.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;
import zf.pages.ZFAssetDetailsPage;
import zf.pages.ZFAssetPage;

public class ZFRBACPage extends ElementManager {
	JsonReader jsonData=new JsonReader();
	ZFAssetPage zfAssetPage=new ZFAssetPage();
	RealTimeDataPage RTD =new RealTimeDataPage();
	ZFAssetDetailsPage zfAssetObject=new ZFAssetDetailsPage();

	private static By ASSETS_LIST_FIRST_EXPANDARROW_BT          =By.xpath("(//div[@class='go-to-icon sprite'])[1]");
	private static By PORTAL_DASHBOARD_ICON                     =By.xpath("//div[text()='DASHBOARD']");
	private static By SETTINGS_ICON                             =By.xpath("//div[text()='Settings']");
	private static By SETTING_THRESHOILD_LK                     =By.xpath("//span[text()='Thresholds']");
	private static By USER_ICON   	 	                        =By.xpath("//a[@title='Users']");
	private static By ROLE_ICON   	 	                        =By.xpath("//a[@title='Roles']");
	private static By USER_FILTER_ST  				            =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static By USER_SEARCH_EB  				            =By.xpath("//input[@placeholder='Search']");
	private static By USER_SEARCHFIRSTVALUE_DT  	            =By.xpath("(//span[@class='name'])[1]");
	private static By USER_EDITUSER_BT   			            =By.xpath("//button[@class='button button-primary edit-button']");
	private static By USER_SAVE_BT             	           		=By.xpath("//span[text()='Save']");
	private static By USER_ROLE_DELETE_EB                       =By.xpath("//span[@class='close ui-select-match-close']");
	private static By ASSETSDETAILS_BASICDETAILS_EDIT_BT        =By.xpath("(//div[@class='fl edit-icon sprite'])[1]");
	private static By ASSETSDETAILS_TOASTER_MSG		   	      	=By.xpath("//div[@class='toast-title']");
	private static By ASSETSDETAILS_TOASTER_MSG_TXT		       	=By.xpath("//div[@class='ng-star-inserted']");
	private static By ASSETSDETAILS_ASSETDETAILS_EDIT_BT        =By.xpath("(//div[@class='fl edit-icon sprite'])[2]");
	private static By SETTING_THRESHOLD_DISABLE_SAVE_BT         =By.xpath("(//button[@disabled='disabled'])[4]");
	private static By SETTING_THRESHOLD_PROGRESS_BT             =By.xpath("(//div[@class='noUi-connect'])[1]");
	private static By EDIT_BT_DISABLED                          =By.xpath("(//button['disable'])[3]");
	private static By USERROLE_EDIT_BT_DISABLED                 =By.xpath("(//button['disable'])[2]");


	public void privilegeToChangeThresholds() {
		try {
			JsonReader.getJsonObject("privilegeToChangeThresholds");
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_DASHBOARD_ICON,300);
			waitElementVisibleClick(SETTINGS_ICON,300);
			waitElementVisibleClick(SETTING_THRESHOILD_LK,300);		
			elementClickWithActions(SETTING_THRESHOLD_PROGRESS_BT);
			if(!(elementDisplayed(SETTING_THRESHOLD_DISABLE_SAVE_BT))) {
				testPassed("Save button is enabled after editing ");
			}else {
				testFailed("Save button is enabled After editing ");
			}
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void noPrivilegeToEditUser() {
		try {
			JsonReader.getJsonObject("noPrivilegeToEditUser");
			waitElementVisibleClick(USER_ICON,300);
			sleep(3000);
			if(!elementAvailability(USER_SEARCH_EB)) {
				waitElementVisibleClick(USER_FILTER_ST,300);}
			elementSendKeys(USER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(USER_SEARCHFIRSTVALUE_DT);
			sleep(3000);
			if(!elementAvailabileAndDisabled(EDIT_BT_DISABLED)) {
				testPassed("User is not having privilage to edit user");
			}
			else {testFailed("Edit button is enabled and user is having privilage to edit user");}
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void noPrivilegeToEditUserRole() {
		try {
			JsonReader.getJsonObject("noPrivilegeToEditUserRole");
			waitElementVisibleClick(ROLE_ICON,300);
			waitElementVisibleClick(USER_SEARCHFIRSTVALUE_DT,300);
			sleep(5000);
			if(!elementAvailabileAndDisabled(USERROLE_EDIT_BT_DISABLED)) {
				testPassed("User is not having privilage to edit user role");
			}
			else {testFailed("Edit button is enabled and user is having privilage to edit user role");}
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}


	public void editUserWithNoRole() {
		try {
			JsonReader.getJsonObject("noPrivilegeToChangeThresholds");
			waitElementVisibleClick(USER_ICON,300);
			waitElementVisibleClick(USER_FILTER_ST,300);
			elementSendKeys(USER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(USER_SEARCHFIRSTVALUE_DT);
			waitElementVisibleClick(USER_EDITUSER_BT,300);
			if(elementDisplayed(USER_ROLE_DELETE_EB)){
				elementClick(USER_ROLE_DELETE_EB);
				elementClick(USER_SAVE_BT);
			}
		}catch(Exception e) {
			testFailed("An exception occured and error message is : "+e.getMessage());
		}

	}

	public void noPrivilegeToEditAssets() {

		try {JsonReader.getJsonObject("noPrivilegeToEditAssets");
		zfAssetObject.navigateToAssetPage();
		sleep(30000);
		waitElementVisibleClick(ASSETS_LIST_FIRST_EXPANDARROW_BT,600);
		waitElementVisibleClick(ASSETSDETAILS_BASICDETAILS_EDIT_BT,300);
		verifyToastermessage(ASSETSDETAILS_TOASTER_MSG,"ToasterMessageFailed",ASSETSDETAILS_TOASTER_MSG_TXT,"ToasterMessageFailedReason");
		elementClick(ASSETSDETAILS_ASSETDETAILS_EDIT_BT);
		verifyToastermessage(ASSETSDETAILS_TOASTER_MSG,"ToasterMessageFailed",ASSETSDETAILS_TOASTER_MSG_TXT,"ToasterMessageFailedReason");
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	
	
	public void noPrivilegeToChangeThresholds() {
		try {
			JsonReader.getJsonObject("noPrivilegeToChangeThresholds");
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_DASHBOARD_ICON,300);
			waitElementVisibleClick(SETTINGS_ICON,300);
			if(!(elementDisplayed(SETTING_THRESHOILD_LK))){
				testPassed("User has no permission to change threshold");
			}else {
				testFailed("User has permission to change threshold");
			}
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
		
	}

	public void verifyToastermessage(By ByType, String Jsondata,By ByType1, String Jsondata1) {
		try {

			String toasterText = waitElementVisibleGetTextForToaster(ByType,200);
			String toasterText1 = elementGetText(ByType1);
			compareText(jsonData.getJsonData(Jsondata),toasterText);
			compareText(jsonData.getJsonData(Jsondata1),toasterText1);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
		
	}


}
