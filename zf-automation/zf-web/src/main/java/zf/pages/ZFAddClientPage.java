package zf.pages;
import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class ZFAddClientPage extends ElementManager {
	JsonReader jsonData=new JsonReader();
	
	private static By CLIENTS_CONTINUEBUTTONCLIENTDETAILSSECTION_BT = By.xpath("//div[@class='tab1-content']//span[contains(text(),'Continue')]");
	private static By CLIENTS_ASSIGNADMININVITEBUTTON_BT			= By.xpath("//div[@role='dialog']/descendant::div[@class='tab2-content']/descendant::button[contains(text(),'Invite')]");
	private static By CLIENTS_CONTINUEBUTTONASSIGNADMIN_BT			= By.xpath("//div[@class='tab2-content']//span[contains(text(),'Continue')]");
	private static By CLIENTS_ADDSUBCLIENT_BT          		        = By.xpath("//div[@class='fw table-row-additioanl-details open']/descendant::span[contains(text(),'Add Sub-Client')]/parent::button");
	private static By CLIENTS_EMAILINPUTASSIGNADMIN_TB 				= By.xpath("//input[@placeholder='Enter E-mail ID']");
	private static By CLIENTS_CREATEBUTTONPREVIEWSECTION_BT 		= By.xpath("//div[@role='dialog']/descendant::div[@class='tab3-content']/descendant::button/descendant::span[contains(text(),'Create')]");
	private static By CLIENTS_PREVIEWCLIENTNAME_TB					= By.xpath("//p[@class='bold-text']");
	private static By CLIENTS_PREVIEWCLIENTEMAIL_TB					= By.xpath("(//div[@class='content-wrapper'])[1]/div[@class='content']/p[2]");
	private static By CLIENTS_PREVIEWCLIENTSTREET_TBNUMBER_TB		= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[1]");
	private static By CLIENTS_PREVIEWCLIENTSTREET_TB				= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[2]");
	private static By CLIENTS_PREVIEWCLIENTCOUNTRY_TB				= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[3]");
	private static By CLIENTS_PREVIEWCLIENTPOSTCODE_TB				= By.xpath("(//div[@class='content-wrapper'])[2]/div[@class='content']/p[4]");
	private static By CLIENTS_CREATECLIENTCREATETOASTER				= By.xpath("//div[@class='toast-title']");
	private static By ADDSUBCLIENT_CLIENTNAME_EB					= By.xpath("//input[@name='clientName']");
	private static By ADDSUBCLIENT_CLIENTEMAIL_EB					= By.xpath("//input[@name='clientEmail']");
	private static By ADDSUBCLIENT_CLIENTSTREET_EB					= By.xpath("//input[@name='clientStreet']");
	private static By ADDSUBCLIENT_CLIENTSTREETNUMBER_EB			= By.xpath("//input[@name='clientStreetNumber']");
	private static By ADDSUBCLIENT_CLIENTTELEPHONE_EB				= By.xpath("//input[@name='telephone']");
	private static By ADDSUBCLIENT_CLIENTINFORMATION_EB				= By.xpath("//*[@id='clientFurtherInfo']");
	private static By ADDSUBCLIENT_CLIENTPOSTCODE_EB				= By.xpath("//input[@name='clientPostcode']");
	private static By ADDSUBCLIENT_CLIENTWEBSITE_EB					= By.xpath("//input[@name='website']");
	private static By ADDSUBCLIENT_CLIENTCOUNTRY_EB					= By.xpath("//input[@name='clientCountry']");
	private static By ADDSUBCLIENT_CLIENTFAX_EB						= By.xpath("//input[@name='fax']");
	private static By CLIENT_NAME_ERROR_MSG_LT						= By.xpath("//div[contains(@class,'client-name-wrapper')]/descendant::span[contains(@class,'error')]");
	private static By ADDSUBCLIENT_CANCEL_BT						= By.xpath("//span[contains(text(),'Cancel')]");
	private static By CLIENT_EMAIL_ERROR_MSG_LT 					= By.xpath("//div[contains(@class,'email-wrapper')]/descendant::span[contains(@class,'error')]");

	public String addSubClient(){
		String clientName =null;
		try {
			JSONObject clientjsonObject = JsonReader.getJsonObject("ClientDetails");
			clientName = clientjsonObject.getAsString("clientName")+getRandomNumber();
			waitElementVisibleClick(CLIENTS_ADDSUBCLIENT_BT, 300);
			waitElementToBeVisibleSendValue(ADDSUBCLIENT_CLIENTNAME_EB,300,clientName);
			elementSendKeys(ADDSUBCLIENT_CLIENTEMAIL_EB,clientjsonObject.getAsString("email"));
			elementSendKeys(ADDSUBCLIENT_CLIENTSTREET_EB,clientjsonObject.getAsString("street"));
			elementSendKeys(ADDSUBCLIENT_CLIENTSTREETNUMBER_EB,clientjsonObject.getAsString("streetNumber"));
			elementSendKeys(ADDSUBCLIENT_CLIENTTELEPHONE_EB,clientjsonObject.getAsString("telephone"));
			elementSendKeys(ADDSUBCLIENT_CLIENTINFORMATION_EB,clientjsonObject.getAsString("information"));
			elementSendKeys(ADDSUBCLIENT_CLIENTPOSTCODE_EB,clientjsonObject.getAsString("postCode"));
			elementSendKeys(ADDSUBCLIENT_CLIENTWEBSITE_EB,clientjsonObject.getAsString("webSite"));
			elementSendKeys(ADDSUBCLIENT_CLIENTCOUNTRY_EB,clientjsonObject.getAsString("country"));
			elementSendKeys(ADDSUBCLIENT_CLIENTFAX_EB,clientjsonObject.getAsString("fax"));
			elementClick(CLIENTS_CONTINUEBUTTONCLIENTDETAILSSECTION_BT);
			waitElementToBeVisibleSendValue(CLIENTS_EMAILINPUTASSIGNADMIN_TB, 300, clientjsonObject.getAsString("email"));
			elementClick(CLIENTS_ASSIGNADMININVITEBUTTON_BT);
			elementClick(CLIENTS_CONTINUEBUTTONASSIGNADMIN_BT);
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTNAME_TB), clientName);
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTEMAIL_TB), clientjsonObject.getAsString("email"));
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTSTREET_TBNUMBER_TB), clientjsonObject.getAsString("streetNumber"));
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTSTREET_TB), clientjsonObject.getAsString("street"));
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTCOUNTRY_TB), clientjsonObject.getAsString("country"));
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTPOSTCODE_TB), clientjsonObject.getAsString("postCode"));
			elementClick(CLIENTS_CREATEBUTTONPREVIEWSECTION_BT);
			verifyToastermessage(clientjsonObject.getAsString("ToasterMsg"));
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
		return clientName;
	}

	public void addClientWithMandatoryInputs()
	{
		try {
			JSONObject clientDetailObject = JsonReader.getJsonObject("ClientDetails");
			waitElementToBeVisibleSendValue(ADDSUBCLIENT_CLIENTNAME_EB,300,clientDetailObject.getAsString("clientName"));
			elementClick(CLIENTS_CONTINUEBUTTONCLIENTDETAILSSECTION_BT);
			waitElementToBeVisibleSendValue(CLIENTS_EMAILINPUTASSIGNADMIN_TB, 300, clientDetailObject.getAsString("email"));
			elementClick(CLIENTS_ASSIGNADMININVITEBUTTON_BT);
			elementClick(CLIENTS_CONTINUEBUTTONASSIGNADMIN_BT);
			compareText(elementGetText(CLIENTS_PREVIEWCLIENTNAME_TB), clientDetailObject.getAsString("clientName"));
			elementClick(CLIENTS_CREATEBUTTONPREVIEWSECTION_BT);
			verifyToastermessage(clientDetailObject.getAsString("ToasterMsg"));
		} catch (InterruptedException e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void createNewSubClientNameValidation()
	{
		JSONObject ClientDetailsObject = JsonReader.getJsonObject("ClientDetails");
		try {
			elementSendKeys(ADDSUBCLIENT_CLIENTNAME_EB, ClientDetailsObject.getAsString("clientNameEmpty"));
			compareText(elementGetText(CLIENT_NAME_ERROR_MSG_LT), ClientDetailsObject.getAsString("clientNameErrorMsg"));
			elementSendKeys(ADDSUBCLIENT_CLIENTNAME_EB,ClientDetailsObject.getAsString("clientNameSpecialChar"));
			compareText(elementGetText(CLIENT_NAME_ERROR_MSG_LT), ClientDetailsObject.getAsString("clientNameErrorMsg"));
			elementSendKeys(ADDSUBCLIENT_CLIENTNAME_EB,ClientDetailsObject.getAsString("clientNameWithAbove32Char"));
			elementClick(ADDSUBCLIENT_CANCEL_BT);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void createNewSubClientEmailValidation()
	{
		JSONObject  ClientDetailsObject = JsonReader.getJsonObject("ClientDetails");
		elementSendKeys(ADDSUBCLIENT_CLIENTEMAIL_EB,ClientDetailsObject.getAsString("clientEmptyEmail"));
		compareText(elementGetText(CLIENT_EMAIL_ERROR_MSG_LT), ClientDetailsObject.getAsString("clientEmailErrorMsg"));
		elementSendKeys(ADDSUBCLIENT_CLIENTEMAIL_EB,ClientDetailsObject.getAsString("clientInvalidEmail"));
		compareText(elementGetText(CLIENT_EMAIL_ERROR_MSG_LT), ClientDetailsObject.getAsString("clientEmailErrorMsg"));
		elementClick(ADDSUBCLIENT_CANCEL_BT);
	}

	public void CreateNewSubClientWithWrongTelephoneFormat()
	{
		JSONObject ClientDetailsObject = JsonReader.getJsonObject("ClientDetails");
		elementSendKeys(ADDSUBCLIENT_CLIENTTELEPHONE_EB,ClientDetailsObject.getAsString("clientTelephoneEmpty"));
		compareText(elementGetText(ADDSUBCLIENT_CLIENTTELEPHONE_EB), ClientDetailsObject.getAsString("clientTelephoneEmpty"));
		elementSendKeys(ADDSUBCLIENT_CLIENTTELEPHONE_EB,ClientDetailsObject.getAsString("clientTelephoneSpecialChar"));
		compareText(elementGetText(ADDSUBCLIENT_CLIENTTELEPHONE_EB), ClientDetailsObject.getAsString("clientTelephoneEmpty"));
		elementClick(ADDSUBCLIENT_CANCEL_BT);
	}

	public void verifyToastermessage(String Jsondata) {
		try {
			String toasterText = waitElementVisibleGetTextForToaster(CLIENTS_CREATECLIENTCREATETOASTER,100);
			compareText(jsonData.getJsonData(Jsondata),toasterText);
		} catch (InterruptedException e) {

			TestLogger.appInfo(e.getMessage());
		}
	}
}