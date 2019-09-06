package zf.servicecatalog.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class ApprovalAandRejectionOfClientsPage extends ElementManager{ /*On hold Service catalog team has to develop it*/
	JsonReader jsonData=new JsonReader();

	private static By MANAGE_CLIENTS_ICON				=By.xpath("//span[contains(text(),'Clients')]");
	private static By MANAGE_CLIENT_PENDING_BT			=By.xpath("(//div[@class='row fw cursor-pointer'])[1]//div/div//button[@class='zf-sc-button pending primary']");
	private static By MANAGE_CLIENT_APPROVE_BT			=By.xpath("//button[@class='but1 approval-btn secondary-type2 ng-star-inserted']");
	private static By MANAGE_CLIENT_REJECT_BT			=By.xpath("//button[@class='but2 reject-btn secondary-type2']");
	private static By MANGE_CLIENT_STREET				=By.xpath("(//div[@class='title-wrapper'])[1]");
	private static By MANGE_CLIENT_NUMBER				=By.xpath("(//p[@class='content-value'])[2]");
	private static By MANGE_CLIENT_ADDITIONAL_INFO_LB	=By.xpath("(//p[@class='content-value'])[3]");
	private static By MANGE_CLIENT_POSTAL_CODE_LB		=By.xpath("(//p[@class='content-value'])[4]");
	private static By MANGE_CLIENT_COUNTRY_LB			=By.xpath("(//p[@class='content-value'])[5]");
	private static By MANGE_CLIENT_DIVISION_NAME_LB		=By.xpath("(//p[@class='content-value'])[6]");
	private static By MANAGE_CLIENT_APPROVED_TXT		=By.xpath("//div[@class='reject-txt']");
	private static By MANAGE_CLIENT_EXPAND_WINDOW_CLICK =By.xpath("//i[@class='sprite-client fr resizer collapsed']");
	private static By MANAGE_CLIENT_RESIZED_WINOW_CLICK =By.xpath("//i[@class='sprite-client fr resizer expanded']");
	private static By MANAGE_CLIENT_CLOSE_BT			=By.xpath("//span[@class='ui-button-text ui-clickable']");
	private static By MANAGE_CLIENT_ACTIVE_LB			=By.xpath("//div[@class='active'][1]");
	private static By MANAGE_CLIENT_REJECT_SCREEN_LB    =By.xpath("//div[@class='ui-float-label fw']");
	private static By MANAGE_CLIENT_REJECT_EB			=By.xpath("//textarea[@id='rejectDesc']");
	private static By MANAGE_CLIENT_REJECT_SEND_BT		=By.xpath("//span[contains(text(),'Send')]");
	private static By MANAGE_CLIENT_REJECT_LB			=By.xpath("//div[@class='inactive'][1]");
	private static By MANAGE_CLIENT_CANCEL_MARK			=By.xpath("//i[@class='sprite-client fr close-btn']");
	private static By MANAGE_CLIENT_LB					=By.xpath("//div[@class='fl title']");
	private static By MANAGE_CLIENT_CBOX_LB				=By.xpath("//div[contains(text(),'C.BOX')]");
	private static By MANAGE_CLIENT_CLIENT_LOGO_LB		=By.xpath("//div[contains(text(),'CLIENT LOGO')]");
	private static By MANAGE_CLIENT_CLIENT_NAME_LB		=By.xpath("//div[contains(text(),'CLIENT NAME')]");
	private static By MANAGE_CLIENT_AREA_LB				=By.xpath("//div[contains(text(),'AREA')]");
	private static By MANAGE_CLIENT_COUNTRY_LB			=By.xpath("//div[contains(text(),'COUNTRY')]");
	private static By MANAGE_CLIENT_MOBILE_NUMBER_LB	=By.xpath("//div[contains(text(),'MOBILE NUMBER')]");
	private static By MANAGE_CLIENT_CREATED_DATE_LB		=By.xpath("//div[contains(text(),'CREATED DATE')]");
	private static By MANAGE_CLIENT_STATUS_LB			=By.xpath("//div[contains(text(),'STATUS')]");
	private static By MANAGE_CLIENT_ACTIONS_LB			=By.xpath("//div[contains(text(),'ACTIONS')]");
	private static By MANAGE_CLIENT_ZF_LOGO				=By.xpath("//div[@class='logo fl sprite']");
	private static By MANAGE_CLIENT_SERVICE_CATALOG_LB	=By.xpath("//h4[@class='ellipses']");
	private static By MANAGE_CLIENT_SCO_LB				=By.xpath("//div[@class='user-name fw ellipses']");
	private static By MANAGE_CLIENT_PROFILE_IMG			=By.xpath("//div[@class='fr fh profile-icon']");
	private static By MANAGE_CLIENT_ACTIONS_ICON		=By.xpath("(//div[@class='action-dots sprite-client'])[1]");
	private static By MANAGE_CLIENT_ACTIONS_VIEW_DETAILS=By.xpath("//div[@class='actn-item']");
	private static By MANAGE_CLIENT_ACTIONS_CANCEL_BT	=By.xpath("//span[contains(text(),'Cancel')]");
	String MANAGE_CLIENT_MOBBILE_POSTPATH               =" ')]";
	String MANAGE_CLIENT_MOBBILE_LB	                    ="(//div[@class='row fw cursor-pointer'])[2]//div/div[contains(text(),' ";

	public void SCOApprovingClientRequestedByUser() {
		JSONObject jsonObject = JsonReader.getJsonObject("");
		elementClick(MANAGE_CLIENTS_ICON);
		ManageClientPageValidation();
		boolean elementDisplayed = elementDisplayed(By.xpath(createXpathString(MANAGE_CLIENT_MOBBILE_LB, "08884046456", MANAGE_CLIENT_MOBBILE_POSTPATH)));
		if(elementDisplayed) {
			elementClick(MANAGE_CLIENT_PENDING_BT);
			compareValue(elementGetText(MANGE_CLIENT_STREET),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_NUMBER),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_ADDITIONAL_INFO_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_POSTAL_CODE_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_COUNTRY_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_DIVISION_NAME_LB),jsonObject.getAsString(""));
			elementClick(MANAGE_CLIENT_APPROVE_BT);
			elementDisplayed(MANAGE_CLIENT_APPROVED_TXT);
			elementClick(MANAGE_CLIENT_EXPAND_WINDOW_CLICK);
			elementClick(MANAGE_CLIENT_RESIZED_WINOW_CLICK);
			elementClick(MANAGE_CLIENT_CLOSE_BT);
			compareText(elementGetText(MANAGE_CLIENT_ACTIVE_LB), "Active");
		}
	}

	public void SCORejectingClientRequestedByUser() {

		JSONObject jsonObject = JsonReader.getJsonObject("");
		elementClick(MANAGE_CLIENTS_ICON);
		ManageClientPageValidation();
		boolean elementDisplayed = elementDisplayed(By.xpath(createXpathString(MANAGE_CLIENT_MOBBILE_LB, "08884046456", MANAGE_CLIENT_MOBBILE_POSTPATH)));
		if(elementDisplayed) {
			elementClick(MANAGE_CLIENT_PENDING_BT);
			compareValue(elementGetText(MANGE_CLIENT_STREET),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_NUMBER),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_ADDITIONAL_INFO_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_POSTAL_CODE_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_COUNTRY_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_DIVISION_NAME_LB),jsonObject.getAsString(""));
			elementClick(MANAGE_CLIENT_REJECT_BT);
			elementDisplayed(MANAGE_CLIENT_REJECT_SCREEN_LB);
			elementSendKeys(MANAGE_CLIENT_REJECT_EB, "Rejected");
			elementClick(MANAGE_CLIENT_REJECT_SEND_BT);
			elementClick(MANAGE_CLIENT_REJECT_LB);

			compareText(elementGetText(MANAGE_CLIENT_REJECT_LB), "Rejected");
		}
	}

	public void ServiceCatalogOwnerCancelingTheRejectOfTheClient() {
		JSONObject jsonObject = JsonReader.getJsonObject("");
		elementClick(MANAGE_CLIENTS_ICON);
		ManageClientPageValidation();
		boolean elementDisplayed = elementDisplayed(By.xpath(createXpathString(MANAGE_CLIENT_MOBBILE_LB, "08884046456", MANAGE_CLIENT_MOBBILE_POSTPATH)));
		if(elementDisplayed) {
			elementClick(MANAGE_CLIENT_PENDING_BT);
			compareValue(elementGetText(MANGE_CLIENT_STREET),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_NUMBER),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_ADDITIONAL_INFO_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_POSTAL_CODE_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_COUNTRY_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_DIVISION_NAME_LB),jsonObject.getAsString(""));

			elementClick(MANAGE_CLIENT_REJECT_BT);
			elementDisplayed(MANAGE_CLIENT_REJECT_SCREEN_LB);
			elementSendKeys(MANAGE_CLIENT_REJECT_EB, "Rejected");
			elementClick(MANAGE_CLIENT_CANCEL_MARK);
		}
	}

	public void	SCORejectingTheClientRequestedByUserWithoutAddingNotes() {

		JSONObject jsonObject = JsonReader.getJsonObject("");
		elementClick(MANAGE_CLIENTS_ICON);
		ManageClientPageValidation();
		boolean elementDisplayed = elementDisplayed(By.xpath(createXpathString(MANAGE_CLIENT_MOBBILE_LB, "08884046456", MANAGE_CLIENT_MOBBILE_POSTPATH)));
		if(elementDisplayed) {
			elementClick(MANAGE_CLIENT_PENDING_BT);
			compareValue(elementGetText(MANGE_CLIENT_STREET),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_NUMBER),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_ADDITIONAL_INFO_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_POSTAL_CODE_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_COUNTRY_LB),jsonObject.getAsString(""));
			compareValue(elementGetText(MANGE_CLIENT_DIVISION_NAME_LB),jsonObject.getAsString(""));
			elementClick(MANAGE_CLIENT_REJECT_BT);
			elementDisplayed(MANAGE_CLIENT_REJECT_SCREEN_LB);
			elementClick(MANAGE_CLIENT_REJECT_SEND_BT);
		}
	}
	public void ViewingTheDetailsOfClient() {
		elementClick(MANAGE_CLIENTS_ICON);
		ManageClientPageValidation();
		elementClick(MANAGE_CLIENT_ACTIONS_ICON);
		elementClick(MANAGE_CLIENT_ACTIONS_VIEW_DETAILS);
		elementClick(MANAGE_CLIENT_ACTIONS_CANCEL_BT);

	}

	public void ManageClientPageValidation() {
		elementDisplayed(MANAGE_CLIENT_ZF_LOGO);
		elementDisplayed(MANAGE_CLIENT_SERVICE_CATALOG_LB);
		elementDisplayed(MANAGE_CLIENT_SCO_LB);
		elementDisplayed(MANAGE_CLIENT_PROFILE_IMG);
		elementDisplayed(MANAGE_CLIENT_LB);
		elementDisplayed(MANAGE_CLIENT_CBOX_LB);
		elementDisplayed(MANAGE_CLIENT_CLIENT_LOGO_LB);
		elementDisplayed(MANAGE_CLIENT_CLIENT_NAME_LB);
		elementDisplayed(MANAGE_CLIENT_AREA_LB);
		elementDisplayed(MANAGE_CLIENT_COUNTRY_LB);
		elementDisplayed(MANAGE_CLIENT_MOBILE_NUMBER_LB);
		elementDisplayed(MANAGE_CLIENT_CREATED_DATE_LB);
		elementDisplayed(MANAGE_CLIENT_STATUS_LB);
		elementDisplayed(MANAGE_CLIENT_ACTIONS_LB);
	}
}