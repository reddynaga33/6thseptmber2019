package zf.servicecatalog.pages;

import java.util.List;

import org.openqa.selenium.By;

import framework.DatabaseUtility;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class RegistrationPage extends ElementManager{
	JsonReader jsonReader=new JsonReader();
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;

	private static By REGISTER_BT                        =By.xpath("//span[text()='Register']");	
	private static By REGISTER_SELECTDIVISION_DD         =By.xpath("//label[text()='Select Division*']");
	private static By REGISTER_SELECTDIVISION_TXT        =By.xpath("(//span[@class='ng-tns-c14-5 ng-star-inserted'])[1]");
	private static By REGISTER_SELECTDIVISION_BT         =By.xpath("//div[@class='bt-forward-icon client-profile-sprite ng-star-inserted']");
	private static By REGISTER_EMAILID_EB                =By.xpath("//input[@name='clientEmail']");
	private static By REGISTER_MOBILENUMBER_EB           =By.xpath("//input[@name='clientMobile']");
	private static By REGISTER_STREET_EB                 =By.xpath("//input[@name='clientStreet']");
	private static By REGISTER_POSTCODE_EB               =By.xpath("//input[@name='clientPostCode']");
	private static By REGISTER_ADDINFO_EB                =By.xpath("//textarea[@name='clientFurtherInfo']");
	private static By REGISTER_WEBSITE_EB                =By.xpath("//input[@name='clientWebsite']");
	private static By REGISTER_SELECTCOUNTRY_DD          =By.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-caret-down'])[2]");
	private static By REGISTER_CONTINUE_BT               =By.xpath("//span[text()='Continue']");
	private static By REGISTER_SUCCESS                   =By.xpath("//span[text()='Registration Completed']");
	private static By REGISTER_FILEUPLOAD_BT             =By.xpath("//div[@class='bt-logo-upload-icon sprite-client']");
	private static By REGISTER_LOGIN_BT                  =By.xpath("(//span[text()='Login'])[2]");
	private static By REGISTER_TXT                       =By.xpath("//p-header[text()=' Register ']");
	private static By REGISTER_LOGIN_SUCCESS_TXT         =By.xpath("//p[text()='Your account is awaiting for approval by service catalog admin']");
	private static By REGISTER_CANCEL_BT                 =By.xpath("//span[text()='Cancel']");
	private static String REGISTER_SELECTCOUNTRY_LIST	 ="//span[text()='{}']";


	public String userRegistrationSuccess(String Payload) {
		JSONObject SCRegisterjsonObject = JsonReader.getJsonObject(Payload);
		String[] split = SCRegisterjsonObject.getAsString("Email").split("@");
		String EmailUsername = split[0];
		String Emaildotcom = split[1];
		String emailID=		EmailUsername+getRandomNumber(0,99999)+"@"+Emaildotcom;
		try {
			elementClick(REGISTER_BT);
			sleep(5000);
			waitElementVisibleClick(REGISTER_SELECTDIVISION_DD, 300);
			waitElementVisibleClick(REGISTER_SELECTDIVISION_TXT,300);
			waitElementVisibleClick(REGISTER_SELECTDIVISION_BT,300);
			elementSendKeys(REGISTER_EMAILID_EB, emailID);
			elementSendKeys(REGISTER_MOBILENUMBER_EB, SCRegisterjsonObject.getAsString("MobileNumber"));
			elementSendKeys(REGISTER_STREET_EB, SCRegisterjsonObject.getAsString("Street"));
			elementSendKeys(REGISTER_POSTCODE_EB, SCRegisterjsonObject.getAsString("Postalcode"));
			elementSendKeys(REGISTER_ADDINFO_EB, SCRegisterjsonObject.getAsString("AdditionalInfo"));
			elementSendKeys(REGISTER_WEBSITE_EB, SCRegisterjsonObject.getAsString("Website"));
			elementClick(REGISTER_SELECTCOUNTRY_DD);
			waitElementVisibleClick(By.xpath(dynamicXpath(REGISTER_SELECTCOUNTRY_LIST,SCRegisterjsonObject,"SelectCountry")),300);
			elementClick(REGISTER_FILEUPLOAD_BT);
			uploadFile(System.getProperty("user.dir")+SCRegisterjsonObject.getAsString("UploadCertificate"));
			elementClick(REGISTER_CONTINUE_BT);
			compareText(SCRegisterjsonObject.getAsString("RegistrationSuccess"), elementGetText(REGISTER_SUCCESS));
			waitElementVisibleClick(REGISTER_LOGIN_BT,300);
			compareText(SCRegisterjsonObject.getAsString("WaitingForApprovalMessage"), elementGetText(REGISTER_LOGIN_SUCCESS_TXT));
		}catch(Exception e) {
			testFailed("An exception has generated while registrations: "+e.getMessage());
		}
		return emailID;
	}

	public void userRegistrationCancel(String Payload) {
		JSONObject SCRegisterjsonObject = JsonReader.getJsonObject(Payload);
		String emailID=SCRegisterjsonObject.getAsString("Email")+getRandomNumber(0,999);
		try {
			elementClick(REGISTER_BT);
			sleep(5000);
			waitElementVisibleClick(REGISTER_SELECTDIVISION_DD, 300);
			waitElementVisibleClick(REGISTER_SELECTDIVISION_TXT,300);
			waitElementVisibleClick(REGISTER_SELECTDIVISION_BT,300);
			elementSendKeys(REGISTER_EMAILID_EB, emailID);
			elementSendKeys(REGISTER_MOBILENUMBER_EB, SCRegisterjsonObject.getAsString("MobileNumber"));
			elementSendKeys(REGISTER_STREET_EB, SCRegisterjsonObject.getAsString("Street"));
			elementSendKeys(REGISTER_POSTCODE_EB, SCRegisterjsonObject.getAsString("Postalcode"));
			elementSendKeys(REGISTER_ADDINFO_EB, SCRegisterjsonObject.getAsString("AdditionalInfo"));
			elementSendKeys(REGISTER_WEBSITE_EB, SCRegisterjsonObject.getAsString("Website"));
			elementClick(REGISTER_SELECTCOUNTRY_DD);
			waitElementVisibleClick(By.xpath(dynamicXpath(REGISTER_SELECTCOUNTRY_LIST,SCRegisterjsonObject,"SelectCountry")),300);
			elementClick(REGISTER_FILEUPLOAD_BT);
			uploadFile(System.getProperty("user.dir")+SCRegisterjsonObject.getAsString("UploadCertificate"));
			elementClick(REGISTER_CANCEL_BT);
			if(!(elementAvailability(REGISTER_TXT))) {
				testPassed("Registration request is cancelled");
			}else {
				testFailed("Registration request is not");
			}
		}catch(Exception e) {
			testFailed("An exception has generated while registrations: "+e.getMessage());
		}
	}

	public String RegistrationDBValidation(String RegistrationDetails, String registrationRequestEmailID) {
		String registrationRequestClientID=null;
		try {
			String SelectQuery = jsonObject.getAsString(RegistrationDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", registrationRequestEmailID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getdaedalusSqlServer(),jsonObject.getAsString("SQLSERVERDATABASESERVICECATALOGDAEDALUS")),SelectQuery);
			registrationRequestClientID=SelectQueryResult.get(0);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,registrationRequestEmailID)){
				testPassed("Database contains the Registration Details : "+registrationRequestEmailID );
			}else {
				testFailed("Database does not contains the Registration Details : "+registrationRequestEmailID);
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
		return registrationRequestClientID;
	}

	public void RegistrationStatusDBValidation(String RegistrationDetails, String registrationRequestEmailID) {

		try {
			String SelectQuery = jsonObject.getAsString(RegistrationDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", registrationRequestEmailID);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getdaedalusSqlServer(),jsonObject.getAsString("SQLSERVERDATABASESERVICECATALOGDAEDALUS")),SelectQuery);
			info("Values from Database is : "+SelectQueryResult.toString());
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,registrationRequestEmailID)){
				testPassed("Database contains the Registration Details : "+SelectQueryResult );
			}else {
				testFailed("Database does not contains the Registration Details : "+SelectQueryResult);
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
	}
}
