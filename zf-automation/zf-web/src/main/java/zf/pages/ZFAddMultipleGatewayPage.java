package zf.pages;
import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class ZFAddMultipleGatewayPage extends ElementManager {

	JsonReader jsonData=new JsonReader();
	ZFGatewayHomePage zfgatewayhomepage=new ZFGatewayHomePage();

	private static  By ADDMULTIPLEGATEWAY_RB						        =By.xpath("(//div[contains(@class,'ui-radiobutton-box ui-widget ui-state-default')])[2]");
	private static  By ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_TXT 	            =By.xpath("//span[text()='ENROLLMENT GROUP']");
	private static  By ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_TXT         =By.xpath("//div[@class='input-content-wrapper hw fl ng-star-inserted']");
	private static  By ADDMULTIPLEGATEWAY_UPLOADCA_TXT                      =By.xpath("//span[@class='upload-span']");
	private static  By ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_TXT          =By.xpath("//div[@class='fr gateway-details-file']");
	private static  By ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB 	        =By.xpath("(//div[@class='radio-wrapper']//span[contains(@class,'ui-radiobutton-icon ui-clickable')])[1]");
	private static  By ADDMULTIPLEGATEWAY_ENROLLMENTGROUPEXISTING_RB        =By.xpath("(//div[@class='radio-wrapper']//span[contains(@class,'ui-radiobutton-icon ui-clickable')])[2]");
	private static  By ADDMULTIPLEGATEWAY_UPLOADCAROOT_RB                   =By.xpath("//p-radiobutton[@label='Root']//div[contains(@class,'ui-radiobutton-box ui-widget ui-state-default')]");
	private static  By ADDMULTIPLEGATEWAY_UPLOADCAINTERMEDIATE_RB           =By.xpath("//p-radiobutton[@label='Intermediate']//div[contains(@class,'ui-radiobutton-box ui-widget ui-state-default')]");
	private static  By ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB          =By.xpath("//input[@class='fw ng-untouched ng-pristine ng-invalid']");
	private static  By ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT        =By.xpath("//div[@class='fr upload-icon sprite'][1]");
	private static  By ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT           =By.xpath("//input[@accept='.xls, .xlsx']");
	private static  By ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT                  =By.xpath("//button[@class='zf-button upload-btn fr']");
	private static  By ADDMULTIPLEGATEWAY_SUBMIT_BT					        =By.xpath("//button[@class='zf-button next fr primary ng-star-inserted']");
	private static  By ADDMULTIPLEGATEWAY_TOASTER_DT					    =By.xpath("//div[@class='toast-title']");
	private static  By ADDMULTIPLEGATEWAY_CAFILENAME_TXT			     	=By.xpath("//div[@class='cert-name fl']");
	private static  By ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_DD          =By.xpath("//span[@class='ui-dropdown-trigger-icon ui-clickable fa fa-chevron-down']");
	private static  By ADDMULTIPLEGATEWAY_DOWNLOADTEMPLATE_BT               =By.xpath("//button[text()='Download Template']");
	private static  By ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_TXT         =By.xpath("//label[text()='Select Enrollment Group']");
	private static String ADDMULTIPLEGATEWAY_SELECTENROLLGROUP_LIST	        ="//span[text()='{}']";

	public void validatUserInterface() {
		try {
			zfgatewayhomepage.clickAddGateway();
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_RB,500);
			elementAvailability(ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCA_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCAROOT_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCAINTERMEDIATE_RB );
			elementAvailability(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT);
			elementAvailability(ADDMULTIPLEGATEWAY_DOWNLOADTEMPLATE_BT );
			elementAvailability(ADDMULTIPLEGATEWAY_SUBMIT_BT );
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPEXISTING_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_DD );
			elementAvailability(ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_TXT );
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}

	public void validateLookAndFeelMultipleGateway() {
		try {
			zfgatewayhomepage.clickAddGateway();
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_RB,500);
			elementAvailability(ADDMULTIPLEGATEWAY_ENROLLMENTGROUP_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCA_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_TXT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCAROOT_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCAINTERMEDIATE_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT);
			elementAvailability(ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT );
			elementAvailability(ADDMULTIPLEGATEWAY_DOWNLOADTEMPLATE_BT );
			elementAvailability(ADDMULTIPLEGATEWAY_SUBMIT_BT );
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPEXISTING_RB);
			elementAvailability(ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_DD );
			elementAvailability(ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_TXT );
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}}
	public void addMultipleGatewayNewRoot() {
		try {
			zfgatewayhomepage.clickAddGateway();
			elementClickRadioButton(ADDMULTIPLEGATEWAY_RB);
			JSONObject AddMultipleGatewayjsonObject = JsonReader.getJsonObject("AddMultipleGatewayNewRoot");
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			elementSendKeys(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB,AddMultipleGatewayjsonObject.getAsString("CreateEnrollmentGroup"));
			elementClickRadioButton(ADDMULTIPLEGATEWAY_UPLOADCAROOT_RB);
			elementClick(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			uploadFile(System.getProperty("user.dir")+AddMultipleGatewayjsonObject.getAsString("UploadCA"));
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT,100);
			uploadFile(System.getProperty("user.dir")+AddMultipleGatewayjsonObject.getAsString("UploadGatewayDeatils"));
			waitElementEnabledClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT,300);
			verifyToastermessage(AddMultipleGatewayjsonObject,"ToasterMessageSuccess");
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}


	public void addMultipleGatewayWithInvalid() {
		try {
			JSONObject gatewayWithInvalidjsonObject = JsonReader.getJsonObject("AddMultipleGatewayWithInvalid");
			zfgatewayhomepage.clickAddGateway();
			sleep(3000);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_RB);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			elementSendKeys(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB,gatewayWithInvalidjsonObject.getAsString("CreateEnrollmentGroup"));
			elementClickRadioButton(ADDMULTIPLEGATEWAY_UPLOADCAROOT_RB);
			elementClick(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			uploadFile(System.getProperty("user.dir")+gatewayWithInvalidjsonObject.getAsString("UploadCA"));
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT,100);
			uploadFile(System.getProperty("user.dir")+gatewayWithInvalidjsonObject.getAsString("UploadGatewayDeatils"));
			waitElementEnabledClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT,300);
			verifyToastermessage(gatewayWithInvalidjsonObject,"ToasterMessageFailed");
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}
	public void addMultipleGatewayNewIntermediate() {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("AddMultipleGatewayNewIntermediate");
			zfgatewayhomepage.clickAddGateway();
			elementClickRadioButton(ADDMULTIPLEGATEWAY_RB);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			elementSendKeys(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB,jsonObject.getAsString("CreateEnrollmentGroup"));
			elementClickRadioButton(ADDMULTIPLEGATEWAY_UPLOADCAINTERMEDIATE_RB);
			elementClick(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			uploadFile(System.getProperty("user.dir")+jsonObject.getAsString("UploadCA"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT);
			uploadFile(System.getProperty("user.dir")+jsonObject.getAsString("UploadGatewayDeatils"));
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT,100);
			verifyToastermessage(jsonObject,"ToasterMessageSuccess");
			waitElementEnabledClick(ADDMULTIPLEGATEWAY_SUBMIT_BT,300);
			verifyToastermessage(jsonObject,"ToasterMessageSuccess");
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}

	public void addMultipleGatewayExisting() {

		try {
			JSONObject AddMultipleGatewayExistingjsonObject = JsonReader.getJsonObject("AddMultipleGatewayExisting");
			zfgatewayhomepage.clickAddGateway();
			elementClickRadioButton(ADDMULTIPLEGATEWAY_RB);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPEXISTING_RB);
			elementClick(ADDMULTIPLEGATEWAY_SELECTENROLLMENTGROUP_DD);
			elementClick(By.xpath(dynamicXpath(AddMultipleGatewayExistingjsonObject,ADDMULTIPLEGATEWAY_SELECTENROLLGROUP_LIST,"SelectEnrollmentGroup")));
			if(compareValue(elementGetText(ADDMULTIPLEGATEWAY_CAFILENAME_TXT),AddMultipleGatewayExistingjsonObject.getAsString("CAfilename"))){
				elementClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT);
				uploadFile(System.getProperty("user.dir")+AddMultipleGatewayExistingjsonObject.getAsString("UploadGatewayDetails"));
				waitElementVisibleClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAY_BT,100);
				verifyToastermessage(AddMultipleGatewayExistingjsonObject,"ToasterMessageSuccess");
				waitElementEnabledClick(ADDMULTIPLEGATEWAY_SUBMIT_BT,300);
				verifyToastermessage(AddMultipleGatewayExistingjsonObject,"ToasterMessageSuccess");
			}else{
				testFailed("CA file name get from webpage is not same as given"+jsonData.getJsonData("CAfilename"));
			}
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}

	public void addMultipleGatewayWithoutMandatoryFields() {

		try {
			JSONObject jsonObject = JsonReader.getJsonObject("addMultipleGatewayWithoutMandatoryFields");
			zfgatewayhomepage.clickAddGateway();
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_RB,300);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			addMultipleGatewayWithoutGatewayDetails(jsonObject);
			addMultipleGatewayWithoutCertificate(jsonObject);
			addMultipleGatewayWithoutEnrollmentGroupName(jsonObject);
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}

	public void addMultipleGatewayWithoutGatewayDetails(JSONObject jsonObject) {
		try {					
			elementSendKeys(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB,jsonObject.getAsString("CreateEnrollmentGroup"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			uploadFile(System.getProperty("user.dir")+jsonObject.getAsString("UploadCA"));
			submitButtonVisibility("when certificate and Group is added without gateway details");
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}
	public void addMultipleGatewayWithoutEnrollmentGroupName(JSONObject jsonObject) {
		try {
			refreashPage();
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_RB,500);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			elementClear(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB);
			elementClick(ADDMULTIPLEGATEWAY_UPLOADCAROOTCERTIFICATE_BT);
			uploadFile(System.getProperty("user.dir")+jsonObject.getAsString("UploadCA"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT);
			uploadFile(System.getProperty("user.dir")+jsonObject.getAsString("UploadGatewayDeatils"));
			submitButtonVisibility("when Gateway details and Group is added without certificate");
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}

	public void addMultipleGatewayWithoutCertificate(JSONObject jsonObject) {
		try {
			refreashPage();
			waitElementVisibleClick(ADDMULTIPLEGATEWAY_RB,500);
			elementClickRadioButton(ADDMULTIPLEGATEWAY_ENROLLMENTGROUPNEW_RB);
			waitElementToBeVisibleSendValue(ADDMULTIPLEGATEWAY_CREATEENROLLMENTGROUP_EB,300,jsonObject.getAsString("CreateEnrollmentGroup"));
			elementClick(ADDMULTIPLEGATEWAY_UPLOADGATEWAYDETAILS_BT);
			uploadFile(System.getProperty("user.dir")+jsonObject.getAsString("UploadGatewayDeatils"));
			submitButtonVisibility("when Gateway details and certificate are added without Group name");
		}catch(Exception e) {
			testFailed("An exception occured and error message is : "+e.getMessage());
		}
	}
	public void submitButtonVisibility(String message) {
		try {
			if(elementGetValue(ADDMULTIPLEGATEWAY_SUBMIT_BT,"disabled")!=null) {
				testPassed("SUBMIT button is disabled "+message);
			}else {
				testFailed("Submit button is enabled. Hence test case failed");
			}
		}
		catch(Exception e) {
			testFailed("An exception occured and error message is : "+e.getMessage());
		}
	}

	public	String dynamicXpath(JSONObject jsonObject,String xpath, String Jsonvalue) {
		return updateXpath(xpath,jsonObject.getAsString(Jsonvalue));
	}

	public void verifyToastermessage(JSONObject jsonObject,String Jsondata) {
		try {
			String toasterText = waitElementVisibleGetTextForToaster(ADDMULTIPLEGATEWAY_TOASTER_DT,200);
			compareText(jsonObject.getAsString(Jsondata),toasterText);
		}
		catch(Exception e) {
			testFailed("An exception occured and error message is : "+e.getMessage());
		}
	}

}
