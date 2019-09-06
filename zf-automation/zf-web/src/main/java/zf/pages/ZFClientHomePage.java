package zf.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;

public class ZFClientHomePage extends ElementManager{

	ZFAddClientPage AddSub = new ZFAddClientPage();
	JsonReader jsonData=new JsonReader();

	private static By CLIENTS_ICON			      			=By.xpath("//a[@title='Clients']");
	private static By CLIENTS_MANAGECLIENTHEADER          	=By.xpath("//div[@class='manage-client-header fw']/div[@class='fl title']");
	private static By CLIENTS_CLIENTDETAILEXPANDARROW_DD    =By.xpath("(//div[@class='client-table-row-wrapper']/div[contains(@class,'client-table-row')]/div[contains(@class,'row no-gutter')]/div[contains(@class,'width-4')]/div/span[contains(@class,'arrow')])[1]");
	private static By CLIENTS_ADDSUBCLIENT_BT          		=By.xpath("//div[@class='fw table-row-additioanl-details open']/descendant::span[contains(text(),'Add Sub-Client')]/parent::button");
	private static By CLIENTS_MOREDETAILS_BT			   	=By.xpath("//div[@class='fw table-row-additioanl-details open']/descendant::span[contains(text(),'More Details')]/parent::button");
	private static By CLIENTS_CLIENTROWGENERIC_ROW			=By.xpath("//div[@class='client-table-row-wrapper']/div[contains(@class,'client-table-row')]");
	private static By CLIENTS_CLIENT_COL				    =By.xpath("//div[@class='title-client']");
	private static By CLIENTS_AREA_COL					 	=By.xpath("//div[contains(text(),'Area')]");
	private static By CLIENTS_COUNTRY_COL				 	=By.xpath("//div[contains(text(),'Country')]");
	private static By CLIENTS_TELEPHONE_COL					=By.xpath("//div[contains(text(),'Telephone')]");
	private static By CLIENTS_CREATEDDATETIME_COL	  	    =By.xpath("//div[contains(text(),'Created Date & Time')]");
	private static By CLIENTS_CLIENTSTATUS_COL  	  		=By.xpath("//div[contains(text(),'Client Status')]");
	private static By CLIENTS_CLIENTNAME_TXT				=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[1]");
	private static By CLIENTS_CLIENTEMAIL_TXT				=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[2]");
	private static By CLIENTS_CLIENTSTREET_TXT				=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[4]");
	private static By CLIENTS_CLIENTSTREET_TXTNUMBER		=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[5]");
	private static By CLIENTS_CLIENTCOUNTRY_TXT	  			=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[6]");
	private static By CLIENTS_CLIENTFAX_TXT				 	=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[7]");
	private static By CLIENTS_CLIENTWEBSITE_TXT				=By.xpath("(//more-details-popup/descendant::div[@class='content-wrapper']/div[@class='content']/p)[8]");
	private static By CLIENTS_CANCEL_BT				        =By.xpath("//button[@class='but2 secondary-type2']");
	private static By ZFLOGO					  		    =By.xpath("//div[@class='zf-logo']");
	private static By ZFPAGEHEADER				  		    =By.xpath("//span[text()='ZF IoT Platform']");
	private static By ZFUSERIMAGE_ICON			  		    =By.xpath("//div[contains(@class,'user-picture')]");
	private static By ZFUSEREMAIL				  		    =By.xpath("//div[contains(@class,'user-name')]");
	private static By ZFNOTIFICATION_ICON		    		=By.xpath("//i[contains(@class,'notification-icon')]");
	private static By CLIENTS_EDITCLIENT_BT		  		    =By.xpath("//span[contains(text(),'Edit Client')]");
	private static By CLIENTS_CONTACTDETAILS_LABEL		    =By.xpath("//div[contains(text(),'Contact details')]");
	private static By CLIENTS_ADDRESS_LABEL		  	    	=By.xpath("//div[contains(text(),'Address')]");
	private static By CLIENTS_OTHER_LABEL		  		    =By.xpath("//div[contains(text(),'Other')]");
	private static By CLIENTS_CONTACTDETAILSBOX_TB		    =By.xpath("(//div[@class='fw table-row-additioanl-details open']/descendant::div[@class='details-box-title' and contains(text(),'Contact details')]/parent::div/div[@class='details-box'])");
	private static By CLIENTS_ADDRESSBOX_TB				    =By.xpath("(//div[@class='fw table-row-additioanl-details open']/descendant::div[@class='details-box-title' and contains(text(),'Address')]/parent::div/div[@class='details-box'])");
	private static By CLIENTS_OTHERBOX_TB			        =By.xpath("(//div[@class='fw table-row-additioanl-details open']/descendant::div[@class='details-box-title' and contains(text(),'Other')]/parent::div/div[@class='details-box'])");
	private static By EXPAND_ARROW_BT		                =By.xpath("(//i[@aria-label='Next slide'])[1]");

	public void addSubClientWithMandatoryDetails()
	{
		navigateToClientPage();
		waitElementVisibleClick(CLIENTS_ADDSUBCLIENT_BT, 300);
		AddSub.addClientWithMandatoryInputs();
	}
	public void AddSubClient() throws InterruptedException {
		navigateToClientPage();
		AddSub.addSubClient();
	}

	public void manageCleitntsStaticValidation()
	{
		JsonReader.getJsonObject("ManageClientHeader");
		try {
			sleep(10000);
			if(!elementDisplayed(CLIENTS_ICON)) {
				for(int count=0;count<5;count++) {
					elementClickWithActions(EXPAND_ARROW_BT);
				}
				waitElementVisibleClick(CLIENTS_ICON, 300);
				sleep(3000);
			}
			waitElementToBeVisible(CLIENTS_CLIENT_COL,300);
			compareValue(jsonData.getJsonData("title"),elementGetText(CLIENTS_MANAGECLIENTHEADER));
			if(elementCount(CLIENTS_CLIENTROWGENERIC_ROW)>0){
				compareValue(elementGetText(CLIENTS_CLIENT_COL),jsonData.getJsonData("Client"));
				compareValue(elementGetText(CLIENTS_AREA_COL),jsonData.getJsonData("Area"));
				compareValue(elementGetText(CLIENTS_COUNTRY_COL),jsonData.getJsonData("Country"));
				compareValue(elementGetText(CLIENTS_TELEPHONE_COL),jsonData.getJsonData("Telephone"));
				compareValue(elementGetText(CLIENTS_CREATEDDATETIME_COL),jsonData.getJsonData("CreatedDateTime"));
				compareValue(elementGetText(CLIENTS_CLIENTSTATUS_COL),jsonData.getJsonData("ClientStatus"));
			}
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void clientMoreDetailsStaticValidation()
	{
		JsonReader.getJsonObject("ClientMoreDetails");
		try {
			navigateToClientPage();
			waitElementVisibleClick(CLIENTS_MOREDETAILS_BT,300);
			compareText(elementGetText(CLIENTS_CLIENTNAME_TXT), jsonData.getJsonData("clientName"));
			compareText(elementGetText(CLIENTS_CLIENTEMAIL_TXT), jsonData.getJsonData("email"));
			compareValue(elementGetText(CLIENTS_CLIENTSTREET_TXT), jsonData.getJsonData("street"));
			compareValue(elementGetText(CLIENTS_CLIENTSTREET_TXTNUMBER), jsonData.getJsonData("streetNumber"));
			compareValue(elementGetText(CLIENTS_CLIENTCOUNTRY_TXT), jsonData.getJsonData("CLIENTS_COUNTRY_COL"));
			compareValue(elementGetText(CLIENTS_CLIENTFAX_TXT), jsonData.getJsonData("fax"));
			compareValue(elementGetText(CLIENTS_CLIENTWEBSITE_TXT), jsonData.getJsonData("WebSite"));
			waitElementVisibleClick(CLIENTS_CANCEL_BT, 300);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}


	public void verifyAdminUserAbleToViewClientManagementPage()
	{
		JsonReader.getJsonObject("CLIENTS_MANAGECLIENTHEADER");
		try {
			navigateToClientPage();
			elementAvailability(ZFLOGO);
			elementAvailability(ZFPAGEHEADER);
			elementAvailability(ZFUSERIMAGE_ICON);
			compareText(elementGetText(ZFUSEREMAIL),EnvironmentManager.getAdminUserName());
			elementAvailability(ZFNOTIFICATION_ICON);
			jsonData.getJsonData("title").equals(elementGetText(CLIENTS_MANAGECLIENTHEADER));
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}


	public void  verifyUserInterfaceOfClientDetailsInManageClientpage()
	{
		JsonReader.getJsonObject("CLIENTS_MANAGECLIENTHEADER");
		try {
			navigateToClientPage();
			elementAvailability(CLIENTS_ADDSUBCLIENT_BT);
			elementAvailability(CLIENTS_EDITCLIENT_BT);
			elementAvailability(CLIENTS_MOREDETAILS_BT);
			compareText(elementGetText(CLIENTS_CONTACTDETAILS_LABEL), jsonData.getJsonData("Contactdetails"));
			compareText(elementGetText(CLIENTS_ADDRESS_LABEL), jsonData.getJsonData("Address"));
			compareText(elementGetText(CLIENTS_OTHER_LABEL), jsonData.getJsonData("Other"));
			elementAvailability(CLIENTS_CONTACTDETAILSBOX_TB);
			elementAvailability(CLIENTS_ADDRESSBOX_TB);
			elementAvailability(CLIENTS_OTHERBOX_TB);

		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void verifyCreateNewSubClientValidation()
	{
		navigateToClientPage();
		waitElementVisibleClick(CLIENTS_ADDSUBCLIENT_BT, 300);
		AddSub.createNewSubClientNameValidation();
	}

	public void verifyCreateNewSubClientEmailValidation()
	{
		navigateToClientPage();
		waitElementVisibleClick(CLIENTS_ADDSUBCLIENT_BT, 300);
		AddSub.createNewSubClientEmailValidation();
	}

	public void verifyCreateNewSubClientWithWrongTelephoneFormat()
	{
		navigateToClientPage();
		waitElementVisibleClick(CLIENTS_ADDSUBCLIENT_BT, 300);
		AddSub.CreateNewSubClientWithWrongTelephoneFormat();
	}
	public void navigateToClientPage() {
		try {
			sleep(10000);
			if(!elementDisplayed(CLIENTS_ICON)) {
				for(int count=0;count<5;count++) {
					elementClick(EXPAND_ARROW_BT);
				}
				waitElementVisibleClick(CLIENTS_ICON, 300);
				sleep(3000);
			}
			waitElementVisibleClick(CLIENTS_CLIENTDETAILEXPANDARROW_DD, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}