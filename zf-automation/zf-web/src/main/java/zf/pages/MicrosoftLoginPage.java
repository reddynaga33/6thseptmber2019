package zf.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class MicrosoftLoginPage extends ElementManager{
	JsonReader jsonReader=new JsonReader();

	private static By MICROSOFTLOGIN_EMAILUSERNAME_EB         =By.xpath("//input[@type='email']");	
	private static By MICROSOFTLOGIN_EMAILPASSWORD_EB         =By.id("i0118");
	private static By MICROSOFTLOGIN_NEXT_BT                  =By.id("idSIButton9");
	private static By MICROSOFTLOGIN_STAYSIGNIN_BT            =By.xpath("//input[@id='idBtn_Back']");
	private static By MICROSOFTLOGIN_SC_MAILID_EB		  	  =By.xpath("//input[@id='userNameInput']");
	private static By MICROSOFTLOGIN_SC_MAILPASSWORD_EB		  =By.xpath("//input[@id='passwordInput']");
	private static By MICROSOFTLOGIN_SC_NEXT_BT				  =By.xpath("//span[@id='submitButton']");


	public void microsoftLogin(String userName, String password) {
		try {
			elementSendKeys(MICROSOFTLOGIN_EMAILUSERNAME_EB,userName);
			elementClick(MICROSOFTLOGIN_NEXT_BT);
			if(elementDisplayed(MICROSOFTLOGIN_EMAILPASSWORD_EB)) {
				elementSendKeyWithActions(MICROSOFTLOGIN_EMAILPASSWORD_EB,passwordDecript(password));
				elementClick(MICROSOFTLOGIN_NEXT_BT);
				waitElementVisibleClick(MICROSOFTLOGIN_STAYSIGNIN_BT,300);
			}else if(elementDisplayed(MICROSOFTLOGIN_SC_MAILPASSWORD_EB)) {
				elementSendKeyWithActions(MICROSOFTLOGIN_SC_MAILPASSWORD_EB,passwordDecript(password));
				elementSendKeys(MICROSOFTLOGIN_SC_MAILID_EB,userName);
				elementClick(MICROSOFTLOGIN_SC_NEXT_BT);
				elementClick(MICROSOFTLOGIN_STAYSIGNIN_BT);
			}
			sleep(13000);
		} catch (Exception e) {
			testFailed("An exception occured while login to the application"+e.getMessage());
		}
	}

	public void validatePortalPageTitle() {
		sleep(8000);
		JSONObject validatePageTitleObject = JsonReader.getJsonObject("validatePageTitle");
		String title = getTitile();
		compareText(validatePageTitleObject.getAsString("PortalTitle"), title);
		TestLogger.appInfo("Page title : "+title);
	}

	public void validatePageTitle() {
		sleep(15000);
		JSONObject validatePageTitleObject = JsonReader.getJsonObject("validatePageTitle");
		String title = getTitile();
		compareText(validatePageTitleObject.getAsString("GatewayPageTitle"), title);
		TestLogger.appInfo("Page title : "+title);
	}

	public void microsoftLoginSC(String userName, String password) {
		try {
			elementSendKeys(MICROSOFTLOGIN_EMAILUSERNAME_EB,userName);
			elementClick(MICROSOFTLOGIN_NEXT_BT);
			if(waitElementToBeVisible(MICROSOFTLOGIN_SC_MAILPASSWORD_EB, 200)) {
				elementSendKeyWithActions(MICROSOFTLOGIN_SC_MAILPASSWORD_EB,"Sumanyv@123");
				waitElementVisibleClick(MICROSOFTLOGIN_SC_NEXT_BT,100);
				elementClick(MICROSOFTLOGIN_STAYSIGNIN_BT);
				sleep(9000);
			}
		} catch (Exception e) {
			testFailed("An exception occured while login to the application"+e.getMessage());
		}
	}
}