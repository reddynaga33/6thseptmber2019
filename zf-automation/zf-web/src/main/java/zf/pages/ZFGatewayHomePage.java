package zf.pages;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.ElementManager;
import framework.EnvironmentManager;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class ZFGatewayHomePage extends ElementManager{

	JsonReader jsonData=new JsonReader();

	private static By GATEWAY_ADDGATEWAY_BT                     		=By.xpath("//button[contains(text(),'Add Gateway')]");
	private static By GATEWAY_ICON                              		=By.xpath("//a[@title='Gateway']");
	private static By GATEWAY_TOASTER_MSG		   	            		=By.xpath("//div[@class='toast-title']");
	private static By GATEWAY_TOASTER_MSG_TXT		          			=By.xpath("//div[@class='ng-star-inserted']");
	private static By GATEWAY_SEARCH_EB		                			=By.xpath("//input[@placeholder='Search']");
	private static By GATEWAY_SEARCH_ICON		            			=By.xpath("//div[@class='search-icon sprite']");
	private static By GATEWAY_TABLE_MOREINFO_DD                			=By.xpath("(//div[@class='main-details fw'])[1]/div[@class='fl fh col-heading cell ellipses more']");
	private static By GATEWAY_ENABLE_BT                     			=By.xpath("(//button[@class='zf-button fl enable ng-star-inserted'])[1]");
	private static By GATEWAY_DISABLE_BT                    			=By.xpath("//button[@class='zf-button fl disable ng-star-inserted']");
	private static By GATEWAY_STATUS_CM                     			=By.xpath("//div[@class='fl fh col-heading cell ellipses status']");
	private static By GATEWAY_GENERICROW		                		=By.xpath("//div[@class='fw gateway ng-star-inserted']");
	private static By GATEWAY_SEARCH_DD                         		=By.xpath("//label[contains(@class,'ui-multiselect-label ui-corner-all')]");
	private static By GATEWAY_SEARCH_BT		                    		=By.xpath("//div[contains(@class,'search-icon sprite')]");
	private static By GATEWAY_SEARCHSELECTIONCHECK              		=By.xpath("//div[@class='table-body  fw']//div[@title='Commissioned']");
	private static By GATEWAY_MOREINFO_ROWS	                    		=By.xpath("//div[contains(@class,'expander sprite')]");
	private static By GATEWAY_NAME_ROWS			                		=By.xpath("(//div[@class='fl fh col-heading cell ellipses gatewayName']//span[1])");
	private static By GATEWAY_MANAGESOFTWARE_BT	                		=By.xpath("//button[contains(text(),'Manage Software')]");
	private static By GATEWAY_MANAGESOFTWARE_NAME               		=By.xpath("//div[@class='gateway-name fl ']//span[1]");
	private static By GATEWAY_PRECOMMISION_CB	               			=By.xpath("//label[(text()='Pre-Commissioned')]");
	private static By GATEWAY_COMMISION_CB	                    		=By.xpath("(//span[@class='ui-chkbox-icon ui-clickable pi pi-check'])[1]");
	private static By GATEWAY_DECOMMISION_CB	               			=By.xpath("//label[(text()='Decommissioned')]");
	private static By GATEWAY_PRECOMMISION_TXT	                		=By.xpath("//label[text()='Pre-Commissioned']");
	private static By GATEWAY_COMMISION_TXT	                    		=By.xpath("//label[text()='Commissioned']");
	private static By GATEWAY_DECOMMISION_TXT	               			=By.xpath("//label[text()='Decommissioned']");
	private static By GATEWAY_EDIT_BT		                    		=By.xpath("//div[@class='expanded-details-wrapper fw open']//button[@type='button'][contains(text(),'Edit')]");
	private static By GATEWAY_OPERATORDISABLE_BT	            		=By.xpath("//div[@class='expanded-details-wrapper fw open']//button[@type='button'][contains(text(),'Disable')]");
	private static By GATEWAY_OPERATORDECOMISSION_BT            		=By.xpath("//div[@class='expanded-details-wrapper fw open']//button[@type='button'][contains(text(),'Decommission')]");
	private static By GATEWAY_NOGATEWAY_TXT                    			=By.xpath("//div[text()=' No gateways. ']");
	private static By GATEWAY_ADDSOFTWARE_BT				   		 	=By.xpath("//button[@type='button']");
	private static By GATEWAY_LABEL_SOFTWARENAME			    		=By.xpath("(//span[@class='fw'][contains(text(),'Software Name')])");
	private static By GATEWAY_LABEL_VERSION								=By.xpath("(//span[@class='fw'][contains(text(),'Version')])");
	private static By GATEWAY_LABEL_UPLOADPACKAGE						=By.xpath("(//span[@class='fw'][contains(text(),'Upload Package')])");
	private static By GATEWAY_LABEL_SCHEDULEDINSTALLATION				=By.xpath("//span[contains(text(),'Schedule Installation')]");
	private static By GATEWAY_DETAILS_LABEL								=By.xpath("//div[@class='expanded-details-wrapper fw open']//span[contains(text(),'Gateway Details')]");
	private static By GATEWAY_DETAILS_MODEL_LABEL						=By.xpath("(//div[@class='expanded-details-wrapper fw open']//label[contains(text(),'Model')])");
	private static By GATEWAY_DETAILS_MACID_LABEL						=By.xpath("//div[@class='expanded-details-wrapper fw open']//label[contains(text(),'MAC ID')]");
	private static By GATEWAY_DETAILS_SIMNUMBER_LABEL					=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Sim Number')])");
	private static By GATEWAY_DETAILS_SERIALNUMBER_LABEL				=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Serial Number')])");
	private static By GATEWAY_DETAILS_CERTIFICATE_LABEL					=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Gateway Certificate')])");
	private static By GATEWAY_DETAILS_ENROLLMENTGROUP_LABEL		 		=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Enrollment Group')])");
	private static By GATEWAY_CUSTOMERDETAILS_LABEL						=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//span[contains(text(),'Customer Details')])");
	private static By GATEWAY_CUSTOMERDETAILS_BUSINESSUNIT_LABEL		=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Business Unit')])");
	private static By GATEWAY_CUSTOMERDETAILS_OPERATOR_LABEL			=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Operator')])");
	private static By GATEWAY_ADDITIONALDETAILS_LABEL					=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//span[contains(text(),'Additional Details')])");
	private static By GATEWAY_ADDITIONALDETAILS_DESCRIPTION_LABEL		=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Description')])");
	private static By GATEWAY_ADDITIONALDETAILS_METADATA_LABEL			=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//label[contains(text(),'Metadata')])");
	private static By GATEWAY_GATEWAYLIFECYCLE_LABEL					=By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//span[contains(text(),'Gateway Lifecycle')])");
	private static By GATEWAY_GATEWAYLIFECYCLE_PRECOMMISSIONED_LABEL    =By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//span[contains(text(),'Pre-Commissioned')])");
	private static By GATEWAY_GATEWAYLIFECYCLE_COMMISSIONED_LABEL	    =By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//div[contains(@class,'fl fh status commissioned completed')]//div[contains(@class,'status-name')])");
	private static By GATEWAY_GATEWAYLIFECYCLE_DECOMMISSIONED_LABEL     =By.xpath("(//div[contains(@class,'expanded-details-wrapper fw open')]//span[contains(text(),'Decommissioned')])");
	private static  By ADDSINGLEGATEWAY_GATEWAYNAME_EB					=By.xpath("//input[@name='gatewayName']");
	private static  By ADDSINGLEGATEWAY_TYPE_DD							=By.xpath("//p-dropdown[@optionlabel='gatewayType']");
	private static  By ADDSINGLEGATEWAY_MODEL_DD						=By.xpath("//p-dropdown[@optionlabel='gatewayModel']");
	private static  By ADDSINGLEGATEWAY_SERIALNUMBER_EB					=By.xpath("//input[@name='gatewaysNo']");
	private static  By ADDSINGLEGATEWAY_SIMNUMBER_EB					=By.xpath("//input[@name='gatewaysimNo']");
	private static  By ADDSINGLEGATEWAY_NEXT_BT							=By.xpath("//button[@class='zf-button next fr primary ng-star-inserted' and text()='Next']");
	private static  By ADDSINGLEGATEWAY_CUSTOMERNAME_ED					=By.xpath("//input[@name='customer']");
	private static  By ADDSINGLEGATEWAY_CUSTOMERCOUNTRY_DD				=By.xpath("//p-dropdown[@name='selectCountry']");
	private static  By ADDSINGLEGATEWAY_CUSTOMERAREA_DD					=By.xpath("//p-dropdown[@name='selectedCity']");
	private static  By ADDSINGLEGATEWAY_BUSINESSUNIT_EB					=By.xpath("//input[@name='businessLine']");
	private static  By ADDSINGLEGATEWAY_OPERATOR_EB						=By.xpath("//input[@name='operator']");
	private static  By ADDSINGLEGATEWAY_CUSTOMERDETAIL_NEXT_BT	    	=By.xpath("//button[@class='zf-button next fr primary' and text()='Next']");
	private static  By ADDSINGLEGATEWAY_ADDITIONALDETAIL_DESCRIPTION_EB	=By.xpath("//textarea[@name='description']");
	private static  By ADDSINGLEGATEWAY_ADDITIONALDETAIL_FINISH_BT		=By.xpath("//button[text()='Finish']");
	private static  By ADDSINGLEGATEWAY_TOASTER_DT						=By.xpath("//div[@class='toast-title']");
	private static By GATEWAY_SHOW_GATEWAY_PRECOMM_CB					=By.xpath("//li[1]/label[contains(text(),'Pre-Commissioned')]");
	private static By GATEWAY_SHOW_GATEWAY_COMM_CB						=By.xpath("//li[2]/label[contains(text(),'Commissioned')]");
	private static By GATEWAY_SHOW_GATEWAY_DECOMM_CB					=By.xpath("//li[3]/label[contains(text(),'Decommissioned')]");
	private static By GATEWAY_VERIFY_GATEWAY_CATAGORIES_DEF_DD    		=By.xpath("//app-gateways-list/div/div[1]/div[3]/div[3]/p-multiselect/div/div[2]/label");
	private static By GATEWAY_SORT_GATEWAY_DD							=By.xpath("//li[1]/span[contains(text(),'Gateway')]");
	private static By GATEWAY_SORT_CUSTOMER_DD							=By.xpath("//li[2]/span[contains(text(),'Customer')]");
	private static By GATEWAY_SORT_TYPE_DD								=By.xpath("//li[3]/span[contains(text(),'Type')]");
	private static By GATEWAY_SORT_COUNTRY_DD							=By.xpath("//li[4]/span[contains(text(),'Country')]");
	private static By GATEWAY_SORT_AREA_DD								=By.xpath("//li[5]/span[contains(text(),'Area')]");
	private static By GATEWAY_SORT_STATUS_DD							=By.xpath("//li[6]/span[contains(text(),'Status')]");
	private static By GATEWAY_ALL_COLUMNS								=By.xpath("//app-gateways-list/div/div[2]/div[2]/div[1]/div/span");
	private static By GATEWAY_MORE_INFO_DD			                    =By.xpath("(//div[@class='main-details fw'])[1]/div[@class='fl fh col-heading cell ellipses more']");
	private static By GATEWAY_EDIT_GATEWAY_BT			                =By.xpath("//app-gateways-list/div/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[5]/button[3]");
	private static By GATEWAY_DROPDOWN		                            =By.xpath("//div/div[1]/div[3]/div[3]/p-multiselect/div/div[3]");
	private static By GATEWAY_CATEGORY_DD	                            =By.xpath("//div[@class='ui-dropdown-trigger ui-state-default ui-corner-right']");
	private static By GATEWAY_GATEWAYNAMESORT_ICON	                    =By.xpath("(//div[@class='sort-icon-wrap fl'])[1]");
	private static By GATEWAY_GATEWAYNAMESORTED_LT	                    =By.xpath("//div[@class='main-details fw']/div[1]");
	private static By ADDSOFTWARE_SOFTWARENAME_EB					    =By.xpath("//input[@name='newSoftwareSoftwareName']");
	private static By ADDSOFTWARE_UPLOADICON_BT							=By.xpath("//div[@class='upload-icon']");
	private static By ADDSOFTWARE_UPLOADINSTALL_BT						=By.xpath("//button[@class='schedule-button fl zf-button']");
	private static By ADDSOFTWARE_TOASTER								=By.xpath("//div[contains(text(),'is blank')]");
	private static By ADDSOFTWARE_UPLOADPACKAGE_BOX						=By.xpath("//input[@name='newSoftwareFileName']");
	private static By GATEWAY_STATUS_DD			                   		=By.xpath("//label[@class='ui-multiselect-label ui-corner-all']");
	private static By GATEWAY_SEARCH_BUTTON			   					=By.xpath("//gateway/app-gateways-list/div/div[1]/div[1]/div");
	private static By GATEWAY_CLEARDATE_BT 								=By.xpath("//div[@class=\"fr close-btn sprite\"]"); 
	private static By GATEWAY_SHOW_TXT		                   			=By.xpath("//div[@class='fl fh info']");
	private static By GATEWAY_CUSTOMERCOLUMN_TXT		                =By.xpath("//div[@class='fl fh col-heading cell ellipses customerName']");
	private static By GATEWAY_TYPECOLUMN_TXT		                   	=By.xpath("//div[@class='fl fh col-heading cell ellipses gatewayType']");
	private static By GATEWAY_AREACOLUMN_TXT		                   	=By.xpath("//div[@class='fl fh col-heading cell ellipses area']");
	private static By GATEWAY_STATUSCOLUMN_TXT		                   	=By.xpath("//div[@class='fl fh col-heading cell ellipses status']");
	private static By GATEWAY_COUNTRYCOLUMN_TXT		                   	=By.xpath("//div[@class='fl fh col-heading cell ellipses country']");
	private static By GATEWAY_FROMDATE_TB 								=By.xpath("//input[@placeholder='From']");
	private static By GATEWAY_TODATE_TB 								=By.xpath("//input[@placeholder=\"To\"]");
	private static By GATEWAY_CLEAR_TXT						     		=By.xpath("//p-calendar[@class='zf-datepicker fr ng-tns-c11-2 ng-valid ng-touched ng-dirty']");
	private static By GATEWAY_TESTDATE1_TXT								=By.xpath("//div[contains(@class,'datepicker')][1]//td[contains(@class,'ui-datepicker-today')]/preceding-sibling::td[1]/a");
	private static By GATEWAY_TESTDATEFROM_TXT							=By.xpath("//div[contains(@class,'datepicker')][1]//td[contains(@class,'ui-datepicker-today')]");
	private static By GATEWAY_TESTDATE2_TXT								=By.xpath("//div[contains(@class,'datepicker')][1]//td[contains(@class,'ui-datepicker-today')]");
	private static By GATEWAY_LASTUPDATED_DT							=By.xpath( "(//div[@class=\"fl fh col-heading cell ellipses lastUpdatedTime\"])[1]");
	private static By GATEWAYNAME_FIRESTLIST_TXT						=By.xpath("(//div[@class='fl fh col-heading cell ellipses gatewayName'])[1]//span");
	private static By EXPAND_ARROW_BT		                            =By.xpath("(//i[@aria-label='Next slide'])[1]");
	private static String ADDSINGLEGATEWAY_TYPEDROPDOWNLIST			    ="//span[text()='{}']";


	public void clickAddGateway() {
		try {
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_ADDGATEWAY_BT,800);
			sleep(3000);
		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}}

	public void verifyManageSoftwareInterface()
	{
		try {navigateToGatewayPage();
		waitElementVisibleClick(GATEWAY_SEARCH_DD,500);
		waitElementVisibleClick(GATEWAY_PRECOMMISION_CB,800);
		elementClick(GATEWAY_SEARCH_BT);
		if(elementAvailability(GATEWAY_SEARCHSELECTIONCHECK))
		{
			if(elementCount(GATEWAY_GENERICROW)>0)
			{
				String gatewaytext=elementGetText(GATEWAY_NAME_ROWS);
				elementClick(GATEWAY_MOREINFO_ROWS);
				if(elementDisplayed(GATEWAY_ENABLE_BT)) {
					waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
				}
				waitElementVisibleClick(GATEWAY_MANAGESOFTWARE_BT,2000);
				compareText(elementGetText(GATEWAY_MANAGESOFTWARE_NAME),"Gateway : "+gatewaytext);
			}
		}
		} catch (Exception e) {		
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void verifyOperatorHasNoAccessToEditGateway()
	{
		JSONObject NoAccessToAddjsonObject = JsonReader.getJsonObject("OperatorHasNoAccessToAddGateway");
		try {navigateToGatewayPage();
		waitElementVisibleClick(GATEWAY_SEARCH_DD,500);
		waitElementVisibleClick(GATEWAY_PRECOMMISION_CB,300);
		elementClick(GATEWAY_SEARCH_BT);
		if(elementCount(GATEWAY_GENERICROW)>0){
			elementClick(GATEWAY_MOREINFO_ROWS);
			elementClick(GATEWAY_EDIT_BT);
			verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,NoAccessToAddjsonObject,"ToasterMessageFailedReason",50);
		}
		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}


	public void verifyOperatorHasNoAccessToDisableGateway()
	{		
		JSONObject AddjsonObject = JsonReader.getJsonObject("OperatorHasNoAccessToAddGateway");
		try {navigateToGatewayPage();
		waitElementVisibleClick(GATEWAY_SEARCH_DD,500);
		waitElementVisibleClick(GATEWAY_PRECOMMISION_CB,300);
		elementClick(GATEWAY_SEARCH_BT);
		if(elementCount(GATEWAY_GENERICROW)>0){
			waitElementVisibleClick(GATEWAY_MOREINFO_ROWS,200);
			elementClick(GATEWAY_OPERATORDISABLE_BT);
			verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,AddjsonObject,"ToasterMessageFailedReason",50);
		}
		}catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void verifyOperatorHasNoAccessToDecommissionGateway()
	{
		try {
			JSONObject NoAccessToDecommissionjsonObject = JsonReader.getJsonObject("OperatorHasNoAccessToAddGateway");
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_SEARCH_DD,200);
			waitElementVisibleClick(GATEWAY_PRECOMMISION_CB,200);
			elementClick(GATEWAY_SEARCH_BT);
			if(elementAvailability(GATEWAY_SEARCHSELECTIONCHECK)){
				if(elementCount(GATEWAY_GENERICROW)>0){
					elementClick(GATEWAY_MOREINFO_ROWS);
					elementClick(GATEWAY_OPERATORDECOMISSION_BT);
					verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,NoAccessToDecommissionjsonObject,"ToasterMessageFailedReason",50);
				}
			}else {
				testFailed("No gateway found");
			}
		} catch (Exception e) {		
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void operatorHasNoAccessToAddGateway() {

		try {
			JSONObject NoAccessjsonObject = JsonReader.getJsonObject("OperatorHasNoAccessToAddGateway");
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_ADDGATEWAY_BT,800);
			verifyToastermessage(GATEWAY_TOASTER_MSG,NoAccessjsonObject,"ToasterMessageFailed",50);
			sleep(2000);
			elementClick(GATEWAY_ADDGATEWAY_BT);
			verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,NoAccessjsonObject,"ToasterMessageFailedReason",50);
		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void disableAndEnableGateway() {
		try {
			JSONObject DisablejsonObject = JsonReader.getJsonObject("DisableAndEnableGateway");
			navigateToGatewayPage();
			elementClick(GATEWAY_TABLE_MOREINFO_DD);
			if(elementDisplayed(GATEWAY_ENABLE_BT)) {
				waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
				verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,DisablejsonObject,"ToasterMessageEnabled",100);
				waitElementVisibleClick(GATEWAY_DISABLE_BT,200);
				verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,DisablejsonObject,"ToasterMessageDisabled",100);
				waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
			}else if(elementDisplayed(GATEWAY_DISABLE_BT)) {
				elementClick(GATEWAY_DISABLE_BT);
				verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,DisablejsonObject,"ToasterMessageDisabled",100);
				elementClick(GATEWAY_ENABLE_BT);
				verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,DisablejsonObject,"ToasterMessageEnabled",100);
			}else {

				testFailed("Enable /Disable button is not displayed");
			}
		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void verifyUserInterfaceOfGatewayAddSoftware()
	{
		try {
			verifyCommissionedFilterSearch();
			if(elementAvailability(GATEWAY_SEARCHSELECTIONCHECK)){
				if(elementCount(GATEWAY_GENERICROW)>0){
					elementClick(GATEWAY_MOREINFO_ROWS);
					if(elementDisplayed(GATEWAY_ENABLE_BT)) {
						waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
					}
					waitElementVisibleClick(GATEWAY_MANAGESOFTWARE_BT,1000);
					waitElementVisibleClick(GATEWAY_ADDSOFTWARE_BT,300);
					elementAvailability(GATEWAY_LABEL_SOFTWARENAME);
					elementAvailability(GATEWAY_LABEL_VERSION);
					elementAvailability(GATEWAY_LABEL_UPLOADPACKAGE);
					elementAvailability(GATEWAY_LABEL_SCHEDULEDINSTALLATION);
				}
			}else {
				testFailed("No gateway found");
			}
		} catch (Exception e) {		
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void operatorHasAccessToViewGateway()
	{
		JSONObject DetailsjsonObject = JsonReader.getJsonObject("GatewayDetails");
		try {
			verifyCommissionedFilterSearch();
			if(elementCount(GATEWAY_GENERICROW)>0){
				elementClick(GATEWAY_MOREINFO_ROWS);
				compareText(elementGetText(GATEWAY_DETAILS_LABEL),DetailsjsonObject.getAsString("GatewayDetails"));
				compareText(elementGetText(GATEWAY_DETAILS_MODEL_LABEL), DetailsjsonObject.getAsString("Model"));
				compareText(elementGetText(GATEWAY_DETAILS_MACID_LABEL), DetailsjsonObject.getAsString("MACID"));
				compareText(elementGetText(GATEWAY_DETAILS_SIMNUMBER_LABEL), DetailsjsonObject.getAsString("SimNumber"));
				compareText(elementGetText(GATEWAY_DETAILS_SERIALNUMBER_LABEL), DetailsjsonObject.getAsString("SerialNumber"));
				compareText(elementGetText(GATEWAY_DETAILS_CERTIFICATE_LABEL), DetailsjsonObject.getAsString("GatewayCertificate"));
				compareText(elementGetText(GATEWAY_DETAILS_ENROLLMENTGROUP_LABEL), DetailsjsonObject.getAsString("EnrollmentGroup"));
				compareText(elementGetText(GATEWAY_CUSTOMERDETAILS_LABEL), DetailsjsonObject.getAsString("CustomerDetails"));
				compareText(elementGetText(GATEWAY_CUSTOMERDETAILS_BUSINESSUNIT_LABEL), DetailsjsonObject.getAsString("BusinessUnit"));
				compareText(elementGetText(GATEWAY_CUSTOMERDETAILS_OPERATOR_LABEL), DetailsjsonObject.getAsString("Operator"));
				compareText(elementGetText(GATEWAY_ADDITIONALDETAILS_LABEL), DetailsjsonObject.getAsString("AdditionalDetails"));
				compareText(elementGetText(GATEWAY_ADDITIONALDETAILS_DESCRIPTION_LABEL), DetailsjsonObject.getAsString("Description"));
				compareText(elementGetText(GATEWAY_ADDITIONALDETAILS_METADATA_LABEL), DetailsjsonObject.getAsString("Metadata"));
				compareText(elementGetText(GATEWAY_GATEWAYLIFECYCLE_LABEL), DetailsjsonObject.getAsString("GatewayLifecycle"));
				compareText(elementGetText(GATEWAY_GATEWAYLIFECYCLE_PRECOMMISSIONED_LABEL), DetailsjsonObject.getAsString("PreCommissioned"));
				compareText(elementGetText(GATEWAY_GATEWAYLIFECYCLE_COMMISSIONED_LABEL), DetailsjsonObject.getAsString("Commissioned"));
				compareText(elementGetText(GATEWAY_GATEWAYLIFECYCLE_DECOMMISSIONED_LABEL),DetailsjsonObject.getAsString("Decommissioned"));
			}
		}catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void UserWithNoPrivilegeCannotAccessGatewayPage()
	{
		navigateToPortalUrl(EnvironmentManager.getUrl());
		refreashPage();
		JSONObject OperatorjsonObject = JsonReader.getJsonObject("TCO3OperatorHasNoAccess");
		verifyToastermessage(GATEWAY_TOASTER_MSG_TXT,OperatorjsonObject,"ToasterMessageFailedReason",20);
	}
	public void verifyDefaultGatewayStatus()
	{ 
		try {
			JSONObject gatewaystatusjsonObject = JsonReader.getJsonObject("VerifyDefaultGatewayStatus");
			navigateToGatewayPage();
			compareText(elementGetText(GATEWAY_STATUS_DD),gatewaystatusjsonObject.getAsString("ShowSearchValue"));
		}
		catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		} finally {
		}
	}

	public void verifyCommissionedFilterSearch()
	{
		try {
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_SEARCH_DD,200);
			waitElementVisibleClick(GATEWAY_PRECOMMISION_CB,300);
			elementClick(GATEWAY_SEARCH_BT);
			sleep(3000);
		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void validatePreCommissionedGatewayStatus()
	{
		try {
			JSONObject PreStatusjsonObject = JsonReader.getJsonObject("validatePreCommissionedGatewayStatus");
			navigateToGatewayPage();
			elementClick(GATEWAY_SEARCH_DD);
			elementClick(GATEWAY_PRECOMMISION_CB);	
			elementClick(GATEWAY_COMMISION_CB);	
			elementClick(GATEWAY_SEARCH_DD);
			compareText(elementGetText(GATEWAY_STATUS_DD),PreStatusjsonObject.getAsString("SearchValue"));	
			elementClick(GATEWAY_SEARCH_DD);
			elementClick(GATEWAY_PRECOMMISION_CB);	
			compareText(elementGetText(GATEWAY_STATUS_DD),PreStatusjsonObject.getAsString("SearchValueAfterPrecommisionselect"));
			waitElementVisibleClick(GATEWAY_SEARCH_ICON, 300);
			sleep(3000);
			validateCoumns(GATEWAY_STATUS_CM, "ShowSearchValueAfterPrecommisionselect");

		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void validateCommissionedGatewayStatus()
	{
		try {
			JSONObject CommissionedjsonObject = JsonReader.getJsonObject("validateCommissionedGatewayStatus");
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_SEARCH_DD,200);
			waitElementVisibleClick(GATEWAY_PRECOMMISION_CB,300);
			compareText(elementGetText(GATEWAY_STATUS_DD),CommissionedjsonObject.getAsString("SearchValueAfterCommisionselect"));
			elementClick(GATEWAY_SEARCH_BT);
			sleep(3000);
			validateCoumns(GATEWAY_STATUS_CM, "SearchValueAfterCommisionselect");
		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
	public void validateDecommissionedGatewayStatus()
	{
		try {
			JSONObject DeStatusjsonObject = JsonReader.getJsonObject("validateDecommissionedGatewayStatus");
			navigateToGatewayPage();
			elementClick(GATEWAY_SEARCH_DD);
			elementClick(GATEWAY_PRECOMMISION_CB);	
			elementClick(GATEWAY_COMMISION_CB);	
			elementClick(GATEWAY_DECOMMISION_CB);
			compareText(elementGetText(GATEWAY_STATUS_DD),DeStatusjsonObject.getAsString("SearchValueAfterDecommisionselect"));
			waitElementVisibleClick(GATEWAY_SEARCH_ICON, 300);
			sleep(3000);
			validateCoumns(GATEWAY_NOGATEWAY_TXT, "ShowSearchValueAfterDecommisionselect");

		} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}

	public void validateCoumns(By ByType, String Jsondata) {
		try {
			if(elementDisplayed(GATEWAY_NOGATEWAY_TXT)) {
				testPassed("No Gateway record present");
			}else {
				List<WebElement> elementList = elementListReturn(GATEWAY_STATUS_CM);	
				for(int i=0;i<elementList.size();i++) {
					compareText(getElementValue(elementList.get(i),"title"),jsonData.getJsonData(Jsondata));
				}
			}
		}catch(Exception e) {
			testFailed("An exception occured: "+e.getMessage());
		}
	}

	public void verifyClearSelectedOptionsInGatewayStatusSearchDropdown()
	{
		try {				
			JSONObject searchjsonObject = JsonReader.getJsonObject("verifyClearSelectedOptionsInGatewayStatusSearchDropdown");
			navigateToGatewayPage();
			elementClick(GATEWAY_SEARCH_DD);
			elementClick(GATEWAY_PRECOMMISION_CB);	
			elementClick(GATEWAY_COMMISION_CB);		
			elementClick(GATEWAY_SEARCH_DD);			
			compareText(elementGetText(GATEWAY_STATUS_DD),searchjsonObject.getAsString("SearchValue"));		
		}catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void verifySelectAllOptionInGatewayStatusSearchDropdown()
	{
		try {
			JSONObject allOptionjsonObject = JsonReader.getJsonObject("verifySelectAllOptionInGatewayStatusSearchDropdown");
			navigateToGatewayPage();
			elementClick(GATEWAY_SEARCH_DD);
			elementClick(GATEWAY_PRECOMMISION_CB);	
			elementClick(GATEWAY_COMMISION_CB);	
			elementClick(GATEWAY_SEARCH_DD);
			compareText(elementGetText(GATEWAY_STATUS_DD),allOptionjsonObject.getAsString("SearchValue1"));	
			elementClick(GATEWAY_SEARCH_DD);
			elementClick(GATEWAY_PRECOMMISION_CB);	
			elementClick(GATEWAY_COMMISION_TXT);	
			elementClick(GATEWAY_DECOMMISION_CB);
			compareText(elementGetText(GATEWAY_STATUS_DD),allOptionjsonObject.getAsString("SearchValueAfterSearch"));	
		} 
		catch(Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		} finally {
		}
	}

	public void verifyClearDatesFromDatePicker(){
		try {                             
			navigateToGatewayPage();
			elementClick(GATEWAY_FROMDATE_TB);
			elementClick(GATEWAY_TESTDATEFROM_TXT);
			elementClick(GATEWAY_TODATE_TB);
			elementClick(GATEWAY_TESTDATE2_TXT);
			elementClick(GATEWAY_CLEARDATE_BT);  
			if(elementDisplayed(GATEWAY_CLEAR_TXT)) {
				testPassed("Date value is cleared from th field");
			}else {
				testFailed("Date value is not cleared from th field");
			}
		}		catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void verifySearchOptionsSearchDropdown(){
		try {
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_CATEGORY_DD,300);
			JSONObject searchOptionDDjsonObject = JsonReader.getJsonObject("verifySearchOptionsSearchDropdown");
			compareText(elementGetText(GATEWAY_SORT_GATEWAY_DD),searchOptionDDjsonObject.getAsString("GatewayActual1"));
			compareText(elementGetText(GATEWAY_SORT_CUSTOMER_DD),searchOptionDDjsonObject.getAsString("GatewayActual2"));
			compareText(elementGetText(GATEWAY_SORT_TYPE_DD),searchOptionDDjsonObject.getAsString("GatewayActual3"));
			compareText(elementGetText(GATEWAY_SORT_COUNTRY_DD),searchOptionDDjsonObject.getAsString("GatewayActual4"));
			compareText(elementGetText(GATEWAY_SORT_AREA_DD),searchOptionDDjsonObject.getAsString("GatewayActual5"));
			compareText(elementGetText(GATEWAY_SORT_STATUS_DD),searchOptionDDjsonObject.getAsString("GatewayActual6"));
		} 
		catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void	ManageSoftwareUploadAndInstallInvalidSoftwareName()
	{
		JSONObject addSoftwarejsonObject = JsonReader.getJsonObject("AddSoftware");
		try {
			verifyCommissionedFilterSearch();
			sleep(3000);
			if(elementCount(GATEWAY_GENERICROW)>0){
				elementClick(GATEWAY_MOREINFO_ROWS);
				if(elementDisplayed(GATEWAY_ENABLE_BT)) {
					waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
				}
				waitElementVisibleClick(GATEWAY_MANAGESOFTWARE_BT,2000);
				waitElementVisibleClick(GATEWAY_ADDSOFTWARE_BT,3000);
				elementSendKeyWithActions(ADDSOFTWARE_SOFTWARENAME_EB, addSoftwarejsonObject.getAsString("SoftwareNameInvalid"));
				elementClick(ADDSOFTWARE_UPLOADICON_BT);
				uploadFile(System.getProperty("user.dir")+addSoftwarejsonObject.getAsString("UploadPackagePath"));
				elementClick(ADDSOFTWARE_UPLOADINSTALL_BT);
				verifyToastermessage(ADDSOFTWARE_TOASTER,addSoftwarejsonObject,"UploadErrorMsg",60);
			}

		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void ManageSoftwareUploadAndInstallInvalidSoftwarePackage()
	{
		JSONObject addSoftwarejsonObject = JsonReader.getJsonObject("AddSoftware");
		try {
			verifyCommissionedFilterSearch();
			sleep(3000);
			if(elementCount(GATEWAY_GENERICROW)>0){
				elementClick(GATEWAY_MOREINFO_ROWS);
				if(elementDisplayed(GATEWAY_ENABLE_BT)) {
					waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
				}
				waitElementVisibleClick(GATEWAY_MANAGESOFTWARE_BT,2000);
				waitElementVisibleClick(GATEWAY_ADDSOFTWARE_BT,3000);
				elementSendKeyWithActions(ADDSOFTWARE_SOFTWARENAME_EB, addSoftwarejsonObject.getAsString("SoftwareNameInvalid"));
				elementClick(ADDSOFTWARE_UPLOADICON_BT);
				uploadFile(System.getProperty("user.dir")+addSoftwarejsonObject.getAsString("UploadInvalidPackagePath"));
				if(elementGetText(ADDSOFTWARE_UPLOADPACKAGE_BOX).contentEquals(addSoftwarejsonObject.getAsString("PackageName"))){
					TestLogger.appInfo("Uploaded Files is Valid");
				}else{
					TestLogger.appInfo("Uploaded File is InValid");
				}
			}
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void ManageSoftwareAddSoftwareUploadInstallValidUserInputs()
	{
		JSONObject AddjsonObject = JsonReader.getJsonObject("AddSoftware");
		try {
			verifyCommissionedFilterSearch();
			if(elementCount(GATEWAY_GENERICROW)>0){
				elementClick(GATEWAY_MOREINFO_ROWS);
				if(elementDisplayed(GATEWAY_ENABLE_BT)) {
					waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
				}
				waitElementVisibleClick(GATEWAY_MANAGESOFTWARE_BT,2000);
				waitElementVisibleClick(GATEWAY_ADDSOFTWARE_BT,3000);
				elementSendKeyWithActions(ADDSOFTWARE_SOFTWARENAME_EB,AddjsonObject.getAsString("SoftwareName"));
				elementClick(ADDSOFTWARE_UPLOADICON_BT);
				uploadFile(System.getProperty("user.dir")+AddjsonObject.getAsString("UploadPackagePath"));
				elementClick(ADDSOFTWARE_UPLOADINSTALL_BT);
				if(waitElementVisibleGetTextForToaster(GATEWAY_TOASTER_MSG_TXT,300).contains(AddjsonObject.getAsString("UploadDeviceNotFoundError"))){
					testPassed("Software is uploaded with error message");
				}else {
					testFailed("Software is not uploaded properly");
				}				
			}
		}catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void verifyToastermessage(By ByType,JSONObject jsonobj, String Jsondata,long timeoutSeconds) {
		try {
			compareText(jsonobj.getAsString(Jsondata),waitElementVisibleGetTextForToaster(ByType,timeoutSeconds));

		}catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void verifySearchGatewayDateRange() {
		try {
			navigateToGatewayPage();
			elementClick(GATEWAY_FROMDATE_TB);
			String fromDate = elementGetText(GATEWAY_TESTDATE1_TXT);
			elementClick(GATEWAY_TESTDATE1_TXT);
			elementClick(GATEWAY_TODATE_TB);
			String ToDate = elementGetText(GATEWAY_TESTDATE2_TXT);
			elementClick(GATEWAY_TESTDATE2_TXT);
			elementClick(GATEWAY_SEARCH_ICON);
			String lastupdatevalue = elementGetText(GATEWAY_LASTUPDATED_DT);
			String lastupdatedate = lastupdatevalue.substring(2, 4);
			if(fromDate.equals(lastupdatedate) ||  (ToDate.equals(lastupdatedate))) {
				testPassed("Expected date value from  "+fromDate +" and "+ToDate+"      Actual date value is : "+lastupdatevalue);
			}else{
				testFailed("Expected date value from  "+fromDate +" and "+ToDate+"      Actual date value is : "+lastupdatevalue);
			}
		}
		catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void editSingleGateway() {

		try {
			JSONObject jsonObject = JsonReader.getJsonObject("EditSingleGateway");
			clickEditGateway();
			waitElementToBeVisibleSendValue(ADDSINGLEGATEWAY_GATEWAYNAME_EB,200,jsonObject.getAsString("GatewayName"));
			waitElementVisibleClick(ADDSINGLEGATEWAY_TYPE_DD,300);
			elementClick(By.xpath(dynamicXpath(ADDSINGLEGATEWAY_TYPEDROPDOWNLIST,"GatewayType")));
			elementClick(ADDSINGLEGATEWAY_MODEL_DD);
			elementClick(By.xpath(dynamicXpath(ADDSINGLEGATEWAY_TYPEDROPDOWNLIST,"Model")));
			waitElementVisibleClick(ADDSINGLEGATEWAY_SERIALNUMBER_EB, 300);
			elementSendKeys(ADDSINGLEGATEWAY_SERIALNUMBER_EB,jsonObject.getAsString("SerialNumber"));
			elementSendKeys(ADDSINGLEGATEWAY_SIMNUMBER_EB,jsonObject.getAsString("SIMNumber"));
			elementClick(ADDSINGLEGATEWAY_NEXT_BT);
			elementSendKeys(ADDSINGLEGATEWAY_CUSTOMERNAME_ED,jsonObject.getAsString("CustomerName"));
			elementClick(ADDSINGLEGATEWAY_CUSTOMERCOUNTRY_DD);
			elementClick(By.xpath(dynamicXpath(ADDSINGLEGATEWAY_TYPEDROPDOWNLIST,"CustomerCountry")));
			elementClick(ADDSINGLEGATEWAY_CUSTOMERAREA_DD);
			elementClick(By.xpath(dynamicXpath(ADDSINGLEGATEWAY_TYPEDROPDOWNLIST,"CustomerArea")));
			elementSendKeys(ADDSINGLEGATEWAY_BUSINESSUNIT_EB,jsonObject.getAsString("BusinessUnit"));
			elementSendKeys(ADDSINGLEGATEWAY_OPERATOR_EB,jsonObject.getAsString("Operator"));
			elementClick(ADDSINGLEGATEWAY_CUSTOMERDETAIL_NEXT_BT);
			elementSendKeyWithActions(ADDSINGLEGATEWAY_ADDITIONALDETAIL_DESCRIPTION_EB,jsonObject.getAsString("Description"));
			elementClick(ADDSINGLEGATEWAY_ADDITIONALDETAIL_FINISH_BT);
			verifyToastermessage(ADDSINGLEGATEWAY_TOASTER_DT,jsonObject,"ToasterMsgSuccess",300);
			waitElementVisibleClick(GATEWAY_ICON,300);
			elementSendKeys(GATEWAY_SEARCH_EB,jsonObject.getAsString("GatewayName"));
			elementClick(GATEWAY_SEARCH_ICON);
			if(compareValue(elementGetText(GATEWAYNAME_FIRESTLIST_TXT), jsonObject.getAsString("GatewayName"))) {
				testPassed("Edit Gateway functionality is validated"+jsonObject.getAsString("GatewayName"));
			}else{
				testFailed("Edit Gateway functionality is not validated"+jsonObject.getAsString("GatewayName"));
			}
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}


	public void showDropDownGateway() {

		try {
			JSONObject ShowDropdownGatewayjObject = JsonReader.getJsonObject("ShowDropdownGateway");
			searchItemWithGatewayCategory(GATEWAY_SORT_CUSTOMER_DD,ShowDropdownGatewayjObject,"CustomerName");
			List<String> customerName = elementsGetText(GATEWAY_CUSTOMERCOLUMN_TXT);
			for(int i=0;i<customerName.size();i++) {
				compareTextIgnoreCase(customerName.get(i), ShowDropdownGatewayjObject.getAsString("CustomerName"));
			}
			searchItemWithGatewayCategory(GATEWAY_SORT_TYPE_DD,ShowDropdownGatewayjObject,"GatewayType");	
			List<String> customerType = elementsGetText(GATEWAY_TYPECOLUMN_TXT);
			for(int i=0;i<customerType.size();i++) {
				compareTextIgnoreCase(customerType.get(i), ShowDropdownGatewayjObject.getAsString("CustomerType"));
			}
			searchItemWithGatewayCategory(GATEWAY_SORT_AREA_DD,ShowDropdownGatewayjObject,"CustomerArea");	
			List<String> customerArea = elementsGetText(GATEWAY_AREACOLUMN_TXT);
			for(int i=0;i<customerArea.size();i++) {
				compareTextIgnoreCase(customerArea.get(i), ShowDropdownGatewayjObject.getAsString("CustomerArea"));
			}
			searchItemWithGatewayCategory(GATEWAY_SORT_COUNTRY_DD,ShowDropdownGatewayjObject,"CustomerCountry");	
			List<String> customerCountry = elementsGetText(GATEWAY_COUNTRYCOLUMN_TXT);
			for(int i=0;i<customerCountry.size();i++) {
				compareTextIgnoreCase(customerCountry.get(i), ShowDropdownGatewayjObject.getAsString("CustomerCountry"));
			}
			searchItemWithGatewayCategory(GATEWAY_SORT_STATUS_DD,ShowDropdownGatewayjObject,"status");	
			List<String> customerStatus = elementsGetText(GATEWAY_STATUSCOLUMN_TXT);
			for(int i=0;i<customerStatus.size();i++) {
				compareTextIgnoreCase(customerStatus.get(i), ShowDropdownGatewayjObject.getAsString("status"));
			}
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void getSortCategories() {
		try {
			List<String> beforeSort = getSortCategory();
			List<String> afterSort = elementsGetText(GATEWAY_GATEWAYNAMESORTED_LT);
			Collections.sort(beforeSort);
			beforeSort.forEach(x->{
				afterSort.forEach(y -> {
					if(x.equals(y) )
						compareTextIgnoreCase(x,y);		
				});
			});
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}		
	}

	public void verifyGatewayColumn() {
		try {
			JSONObject ColjsonObject = JsonReader.getJsonObject("ColumnVerification");
			navigateToGatewayPage();
			List<String> ColumnName = elementsGetText(GATEWAY_ALL_COLUMNS);
			List<String> expected=new ArrayList<String>();
			for(int i=0;i<ColumnName.size();i++) {
				String listvaue = ColjsonObject.getAsString("GatewayColumn"+i);
				expected.add(listvaue);
				compareTextIgnoreCase(ColumnName.get(i),expected.get(i));
			}
			elementAvailability(GATEWAY_SEARCH_EB,"GATEWAY_SEARCH_EB");
			elementAvailability(GATEWAY_SEARCH_DD,"GATEWAY_SEARCH_DD");
			waitElementVisibleClick(GATEWAY_SEARCH_DD,300);
			elementAvailability(GATEWAY_DECOMMISION_TXT,"GATEWAY_DECOMMISION_TXT");
			elementAvailability(GATEWAY_COMMISION_TXT,"GATEWAY_COMMISION_TXT");
			elementAvailability(GATEWAY_PRECOMMISION_TXT,"GATEWAY_PRECOMMISION_TXT");
			elementAvailability(GATEWAY_SEARCH_BT,"GATEWAYSEARCH_BT");
			elementAvailability(GATEWAY_CLEARDATE_BT,"GATEWAY_CLEARDATE_BT");  
			elementAvailability(GATEWAY_CATEGORY_DD,"GATEWAY_CATEGORY_DD");
			elementAvailability(GATEWAY_ADDGATEWAY_BT,"GATEWAY_ADDGATEWAY_BT");
			elementAvailability(GATEWAY_SHOW_TXT,"GATEWAY_SHOW_TXT");
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public void verifyGatewayLifeCycle() {
		try {
			sleep(2000);
			clickGatewayLifeCycle();
			JSONObject GatewayLifeCyclejsonObject = JsonReader.getJsonObject("GatewayLifeCycle");
			elementClick(GATEWAY_SHOW_GATEWAY_COMM_CB);
			elementClick(GATEWAY_SEARCH_BUTTON);
			String ActualTextComm = elementGetText(GATEWAY_VERIFY_GATEWAY_CATAGORIES_DEF_DD);
			compareText(ActualTextComm, GatewayLifeCyclejsonObject.getAsString("ShowGateway1"));
			sleep(2000);
			clickGatewayLifeCycle();
			elementClick(GATEWAY_SHOW_GATEWAY_COMM_CB);
			elementClick(GATEWAY_SHOW_GATEWAY_PRECOMM_CB);
			elementClick(GATEWAY_SEARCH_BUTTON);
			String ActualTextComm1 = elementGetText(GATEWAY_VERIFY_GATEWAY_CATAGORIES_DEF_DD);
			compareText(ActualTextComm1, GatewayLifeCyclejsonObject.getAsString("ShowGateway2"));
			clickGatewayLifeCycle();
			elementClick(GATEWAY_SHOW_GATEWAY_COMM_CB);
			elementClick(GATEWAY_SHOW_GATEWAY_DECOMM_CB);
			elementClick(GATEWAY_SEARCH_BUTTON);
			String ActualTextComm2 = elementGetText(GATEWAY_VERIFY_GATEWAY_CATAGORIES_DEF_DD);
			compareText(ActualTextComm2, GatewayLifeCyclejsonObject.getAsString("ShowGateway3"));
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}	

	public void clickEditGateway() {
		try {
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_STATUS_DD, 300);
			waitElementVisibleClick(GATEWAY_PRECOMMISION_CB, 300);
			waitElementVisibleClick(GATEWAY_SEARCH_ICON, 300);
			sleep(10000);
			waitElementVisibleClick(GATEWAY_MORE_INFO_DD,300);
			if(elementDisplayed(GATEWAY_ENABLE_BT)) {
				waitElementVisibleClick(GATEWAY_ENABLE_BT,300);
			}
			waitElementVisibleClick(GATEWAY_EDIT_GATEWAY_BT,300);
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

	public ZFAddSingleGatewayPage searchItemWithGatewayCategory(By byType, JSONObject jsonObject, String testdata) {
		try {
			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_CATEGORY_DD,300);
			elementClick(byType);
			elementSendKeys(GATEWAY_SEARCH_EB,jsonObject.getAsString(testdata));
			waitElementVisibleClick(GATEWAY_SEARCH_ICON,300);
			sleep(3000);
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
		return new ZFAddSingleGatewayPage();
	}


	public List<String> getSortCategory()
	{
		List<String> beforesort = null;
		try {			
			navigateToGatewayPage();
			beforesort = elementsGetText(GATEWAY_GATEWAYNAMESORTED_LT);
			waitElementVisibleClick(GATEWAY_GATEWAYNAMESORT_ICON,300);
			elementClick(GATEWAY_GATEWAYNAMESORT_ICON);
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
		return beforesort;
	}


	public ZFAddSingleGatewayPage clickGatewayLifeCycle() {
		try {
			navigateToGatewayPage();
			sleep(2500);
			waitElementVisibleClick(GATEWAY_DROPDOWN,500);
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
		return new ZFAddSingleGatewayPage();
	}


	public ZFAddSingleGatewayPage getColumnVerification() {

		try {

			navigateToGatewayPage();
			waitElementVisibleClick(GATEWAY_DROPDOWN,300);
		} catch (Exception e) {
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
		return new ZFAddSingleGatewayPage();
	}

	public	String dynamicXpath(String xpath, String Jsonvalue) {
		return updateXpath(xpath,jsonData.getJsonData(Jsonvalue));
	}

	public void navigateToGatewayPage() {
		try {
			if(elementDisplayed(GATEWAY_ICON)) {
				waitElementVisibleClick(GATEWAY_ICON, 300);
				sleep(3000);}
			else {
				elementClick(EXPAND_ARROW_BT);
				elementClick(EXPAND_ARROW_BT);
				waitElementVisibleClick(GATEWAY_ICON, 300);
				sleep(3000);
			}
		}catch(Exception e){
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}

}
