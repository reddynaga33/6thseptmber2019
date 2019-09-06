package zf.TauriIRIS.pages;

import org.openqa.selenium.By;

import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;
import zf.pages.ZFAssetPage;

public class TauriZFRBACPage extends ElementManager {
	JsonReader jsonData=new JsonReader();
	ZFAssetPage zfAssetPage=new ZFAssetPage();

	private static By PORTAL_DASHBOARD_ICON                     =By.xpath("//div[text()='DASHBOARD']");
	private static By SETTINGS_ICON                             =By.xpath("//div[text()='Settings']");
	private static By SETTING_THRESHOILD_LK                     =By.xpath("//span[text()='Thresholds']");
	private static  By USER_ICON   	 	                        =By.xpath("//a[@title='Users']");
	private static  By USER_FILTER_ST  				            =By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static  By USER_SEARCH_EB  				            =By.xpath("//input[@placeholder='Search']");
	private static  By USER_SEARCHFIRSTVALUE_DT  	            =By.xpath("(//div[@class='item-content'])[1]");
	private static  By USER_EDITUSER_BT   			            =By.xpath("//button[@class='button button-primary edit-button']");
	private static  By USER_VEHICLEEDIT_RIGHTS_CB	            =By.xpath("(//label[@class='checkbox-desc ng-binding'])[1]");
	private static  By USER_SAVE_BT             	            =By.xpath("//span[text()='Save']");
	private static  By USER_TOASTER_MSG             	        =By.xpath("//div[@class='ng-binding toast-title']");
	private static  By USER_TOASTER_MSG_TXT             	    =By.xpath("//div[@class='ng-binding ng-scope']");
	private static  By USER_ROLE_EB              	            =By.xpath("//div[@title='User roles']");
	private static  By USER_ROLE_DELETE_EB                      =By.xpath("//span[@class='close ui-select-match-close']");
	private static  By ASSETSDETAILS_BASICDETAILS_EDIT_BT       =By.xpath("(//div[@class='fl edit-icon sprite'])[1]");
	private static  By ASSETSDETAILS_TOASTER_MSG		   	    =By.xpath("//div[@class='toast-title']");
	private static  By ASSETSDETAILS_TOASTER_MSG_TXT		    =By.xpath("//div[@class='ng-star-inserted']");
	private static  By ASSETSDETAILS_ASSETDETAILS_EDIT_BT       =By.xpath("(//div[@class='fl edit-icon sprite'])[2]");
	private static  By SETTING_THRESHOLD_DISABLE_SAVE_BT        =By.xpath("(//button[@disabled='disabled'])[4]");
	private static  By SETTING_THRESHOLD_PROGRESS_BT          	=By.xpath("(//div[@class='noUi-connect'])[1]");

	TauriRealTimeDataPage RTD =new TauriRealTimeDataPage();
	public void privilegeToChangeThresholds() {
		try {
			JsonReader.getJsonObject("privilegeToChangeThresholds");
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
			RTD.ClientHomePagePortal();
			waitElementVisibleClick(PORTAL_DASHBOARD_ICON,300);
			waitElementVisibleClick(SETTINGS_ICON,300);
			waitElementVisibleClick(SETTING_THRESHOILD_LK,300);		
			if(elementAvailability(SETTING_THRESHOLD_DISABLE_SAVE_BT)) {
				elementClickWithActions(SETTING_THRESHOLD_PROGRESS_BT);
				if(!(elementDisplayed(SETTING_THRESHOLD_DISABLE_SAVE_BT))) {
					testPassed("Save button is enabled after editing ");
				}else {
					testFailed("Save button is enabled After editing ");
				}
			}else {
				testFailed("Save button is enabled before editing ");}
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void noPrivilegeToEditUser() {
		try {
			JsonReader.getJsonObject("noPrivilegeToEditUser");
			waitElementVisibleClick(USER_ICON,300);
			waitElementVisibleClick(USER_FILTER_ST,500);
			elementSendKeys(USER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(USER_SEARCHFIRSTVALUE_DT);
			elementClick(USER_EDITUSER_BT);
			elementClick(USER_VEHICLEEDIT_RIGHTS_CB);
			elementClick(USER_SAVE_BT);
			verifyToastermessage(USER_TOASTER_MSG,"ToasterMsgFailed",USER_TOASTER_MSG_TXT,"ToasterMsgFailedReason");
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}}

	public void noPrivilegeToEditUserRole() {
		try {
			JsonReader.getJsonObject("noPrivilegeToEditUserRole");
			waitElementVisibleClick(USER_ICON,300);
			waitElementVisibleClick(USER_FILTER_ST,300);
			elementSendKeys(USER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(USER_SEARCHFIRSTVALUE_DT);
			elementClick(USER_EDITUSER_BT);
			elementClick(USER_ROLE_EB);
			elementSendKeys(USER_ROLE_EB, jsonData.getJsonData("UserRole"));
			elementClick(USER_SAVE_BT);
			verifyToastermessage(USER_TOASTER_MSG,"ToasterMsgFailed",USER_TOASTER_MSG_TXT,"ToasterMsgFailedReason");
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}}


	public void editUserWithNoRole() {
		try {
			JsonReader.getJsonObject("noPrivilegeToChangeThresholds");
			waitElementVisibleClick(USER_ICON,300);
			waitElementVisibleClick(USER_FILTER_ST,300);
			elementSendKeys(USER_SEARCH_EB, jsonData.getJsonData("Name"));
			elementClick(USER_SEARCHFIRSTVALUE_DT);
			elementClick(USER_EDITUSER_BT);
			if(elementDisplayed(USER_ROLE_DELETE_EB)){
				elementClick(USER_ROLE_DELETE_EB);
				elementClick(USER_SAVE_BT);
			}
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}

	}

	public void noPrivilegeToEditAssets() {
		try {
			JsonReader.getJsonObject("noPrivilegeToEditAssets");
			zfAssetPage.selectAssetDetails("noPrivilegeToEditAssets");
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
			navigateToPortalUrl(EnvironmentManager.getPortalUrl());
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
		}}


}
