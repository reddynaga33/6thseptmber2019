package zf.pages;

import java.util.Random;
import org.openqa.selenium.By;
import framework.DriverManager;
import framework.ElementManager;
import framework.ExtentReport;
import framework.JsonReader;
import framework.TestLogger;
import net.minidev.json.JSONObject;

public class ZFAssetPage extends ElementManager{
	JsonReader jsonData=new JsonReader();

	private static By CREATE_ASSETS_PREVIOUS_BT                          =By.xpath("(//button[text()=' Previous '])[2]");
	private static By ASSETS_MAP_TO_GATEWAY_TYPE_DD                      =By.xpath("//label[text()='Gateway']");
	private static By ASSETS_ASSET_AREA_EB						         =By.xpath("//input[@name='area']");
	private static By ASSETS_ASSET_CUSTOMER_EB						     =By.xpath("//input[@name='customer']");
	private static By ASSETS_ASSET_COMMETS_EB						     =By.xpath("//textarea[@name='comments']");
	private static By ASSETS_HOMEPAGE_LK   	 	                     	 =By.xpath("//a[@title='Assets']");	
	private static By ASSETS_ZFLOGO_IMG   	 	                 	     =By.xpath("//div[@class='zf-logo']");
	private static By ASSETS_SEARCH_EB   	 	                    	 =By.xpath("//input[@class='fh fl ng-untouched ng-pristine ng-valid']");
	private static By ASSETS_SEARCH_BT   	 	                    	 =By.xpath("//div[@class='search-icon sprite']");
	private static By ASSETS_COUNTRY_ST   	 	                 	     =By.xpath("(//span[@class='fl'])[1]");
	private static By ASSETS_COUNTRY_DD   	 	                 	     =By.xpath("//span[@class='ui-multiselect-trigger-icon ui-clickable pi pi-caret-down']");
	private static By ASSETS_CZECHREPUBLIC_CB   	 	                 =By.xpath("(//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default'])[2]");
	private static By ASSETS_AREA_ST   	 	                 	    	 =By.xpath("//div[@class='area-cntnr fl fh']");
	private static By ASSETS_AREA_EB   	 	                 	    	 =By.xpath("//input[@placeholder='Area']");
	private static By ASSETS_CUSTOMER_EB   	 	                     	 =By.xpath("//input[@placeholder='Customer']");
	private static By ASSETS_BUSSINESSUNIT_EB   	 	                 =By.xpath("//input[@placeholder='Business Unit']");
	private static By ASSETS_FILTER_BT   	 	            		     =By.xpath("//div[@class='filter-icon sprite']");
	private static By ASSETS_SORTBY_ST   	 	            		     =By.xpath("//span[text()='Sort By']");
	private static By ASSETS_ASSETTYPE_ST   	 	            	     =By.xpath("//span[text()='Asset Type']");
	private static By ASSETS_MANUFACTURER_ST    	            	     =By.xpath("//span[text()='Manufacturer/Brand']");
	private static By ASSETS_DATE_ST    	            			     =By.xpath("//span[text()='Date']");
	private static By ASSETS_CLEARALL_LK    	            		     =By.xpath("//span[text()='Clear All']");
	private static By ASSETS_CREATEASSET_BT    	                    	 =By.xpath("//button[@class='fr primary zf-button']");
	private static By ASSETS_SEARCHVALUE_DT    	                     	 =By.xpath("//div[@class='asset-name ellipses fw']/span");
	private static By ASSETS_ADVANCEDSEARCH_BT    	                     =By.xpath("//div[@class='filter-icon sprite']");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_ASSETTYPE_DD    	     =By.xpath("//label[@class='ng-tns-c12-8 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_BASICASSETTYPEVALUE_ST  =By.xpath("//span[text()=\"BASIC_ASSET_TYPE\"]");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_BRANDVALUE_ST    		 =By.xpath("//span[text()=\"TRW\"]");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_MANUFACTURER_DD    	 =By.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable fa fa-chevron-down'])[2]");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_MANUFACTURERVALUE_ST    =By.xpath("//span[text()='Fiat']");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_DATEFROM_EB    	 	 =By.xpath("//input[@name='minDate']");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_DATEFROM_DT    	     =By.xpath("//div[contains(@class,'datepicker')][1]//td[contains(@class,'ui-datepicker-today')]");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_DATETO_EB    			 =By.xpath("//input[@name='maxDate']");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_DATETO_DT    	 		 =By.xpath("//div[contains(@class,'datepicker')][1]//td[contains(@class,'ui-datepicker-today')]/a");
	private static By ASSETS_ADVANCEDSEARCHPOPUP_APPLY_BT    	 		 =By.xpath("//button[@class='fr primary apply-btn zf-button ']");
	private static By ASSETS_COUNTRYVALUE_DT   	 	                     =By.xpath("//div[@title='Czech Republic']");
	private static By ASSETS_AREAVALUE_DT   	 	                     =By.xpath("//div[@title='Brno']");
	private static By ASSETS_NOASSETSFOUND_DT   	 	                 =By.xpath("//div[text()=\"No assets found.\"]");
	private static By ASSETS_CREATEASSETS_ASSET_COUNTRY_DD   	 	     =By.xpath("//p-dropdown[@name='country']");
	private static By ASSETS_CREATEASSET_LOCATION_ADDRESS_RB   	         =By.xpath("(//span[contains(@class,'ui-radiobutton-icon ui-clickable')])[1]");
	private static By ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB         =By.xpath("//textarea[@name='address']");
	private static By ASSETS_CREATEASSET_BUSINESSUNIT_DD                 =By.xpath("//input[@name='business']");
	private static By ASSETS_CREATEASSET_NEXT_BT                         =By.xpath("(//button[@class='zf-button primary fr'])[1]");
	private static By ASSETS_CREATEASSET_ASSETNAME_EB   	 	         =By.xpath("//input[@name='name']");
	private static By ASSETS_CREATEASSET_ASSETTYPE_DD                    =By.xpath("//p-dropdown[@name='type']");
	private static By ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD          =By.xpath("//p-dropdown[@name='manufacturer']");
	private static By ASSETS_CREATEASSET_MODEL_DD   	 	             =By.xpath("//p-dropdown[@name='model']");
	private static By ASSETS_CREATEASSET_DETAILS_NEXT_BT                 =By.xpath("(//button[@class='zf-button primary fr'])[2]");
	private static By ASSETS_CREATEASSET_MAPTOGATEWAY_BT                 =By.xpath("//button[@class='zf-button primary map-gateway-btn']");
	private static By ASSETS_CREATEASSET_MAPTOGATEWAY_FIRSTRAW_LT        =By.xpath("//div[@class='main-details fw']");
	private static By ASSETS_CREATEASSET_MAPTOGATEWAY_MAP_BT             =By.xpath("//button[@class='zf-button fr map-btn ng-star-inserted']");
	private static By ASSETS_CREATEASSET_DESCRIPTION_EB                  =By.xpath("//textarea[@name='comments']");
	private static By ASSETS_CREATEASSET_SUBMIT_BT                       =By.xpath("(//button[@class='zf-button primary fr'])[3]");
	private static By ASSETS_CREATEASSET_METADATAKEY_EB                  =By.xpath("//table[@class='meta-wh meta-data-table']/tbody/tr[1]//input[@type='text'][1]");
	private static By ASSETS_CREATEASSET_METADATAKEY_VALUE_EB            =By.xpath("//table[@class='meta-wh meta-data-table']/tbody/tr[1]//input[@type='text'][2]");
	private static By ASSETS_CREATEASSET_VIN_VALUE_EB                    =By.xpath("//input[@name='vin']");
	private static By ASSETS_CREATEASSET_UPDATEDVIN_VALUE_EB             =By.xpath("//input[@name='vin']");
	private static By ASSETS_CREATEASSET_TOASTER_MSG			   		 =By.xpath("//div[@class='toast-title']");
	private static By ASSETS_CREATEASSET_TOASTER_MSG_TXT				 =By.xpath("//div[@class='ng-star-inserted']");
	private static By ASSETS_CREATE_ACTION_TOASTER_MSG_TXT				 =By.xpath("//div[text()='Action updated']");
	private static By ASSETS_CREATEASSET_ACTION_TOASTER_MSG_TXT		     =By.xpath("//div[text()='Asset added successfully']");
	private static By ASSETS_ASSETSDETAILS_BASICDETAILS_EDIT_BT          =By.xpath("(//div[@class='fl edit-icon sprite'])[1]");
	private static By ASSETSGATEWAY_LIST_FIRST_VALUE                     =By.xpath("(//div[@class='main-details fw'])[1]");
	private static By ASSETS_LIST_FIRST_VALUE                            =By.xpath("(//span[@class='uppercase'])[1]");
	private static By ASSETS_LIST_FIRST_EXPANDARROW_BT                   =By.xpath("(//div[@class='go-to-icon sprite'])[1]");
	private static By ASSETS_ACTION_BT                                   =By.xpath("//button[@class='zf-button primary action ng-star-inserted']");
	private static By ASSETS_ACTION_EDIT_BT                              =By.xpath("//div[@class='edit-icon sprite fl']");
	private static By ASSETS_ACTION_RESETALL_BT                          =By.xpath("//button[@class='zf-button fl']");
	private static By ASSETS_ACTION_APPLY_BT                             =By.xpath("//button[@class='zf-button fr']");
	private static By ASSETS_CREATEASSET_SETACTIONS_BT               	 =By.xpath("//button[text()=' Set Actions ']");
	private static By ASSETS_CREATEASSET_SETACTIONS_DEACTIVATE_CB        =By.xpath("(//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default'])[1]");
	private static By ASSETS_CREATEASSET_SETACTIONS_ACTIVATE_CB          =By.xpath("(//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default'])[2]");
	private static By ASSETS_CREATEASSET_SETACTIONS_APPLY_BT           	 =By.xpath("//button[text()=\"Apply\"]");
	private static By ASSETS_ASSETOPERATOR_TOASTERMESSAGE				 =By.xpath("//div[@class='ng-star-inserted']");
	private static By ASSETS_ASSETSDETAILS_ADDCHILDASSET_BT              =By.xpath("//i[@class='fa fa-plus']");
	private static By ASSETS_ASSETSDETAILS_ADDEDASSET_BT                 =By.xpath( "//span[@class='ui-treenode-icon default root ng-star-inserted']");
	private static By ASSETS_ASSETSORTBY_DD							     =By.xpath("//p-dropdown[@optionlabel='category']/div[contains(@class,'ui-dropdown')]");
	private static By ASSETS_ASSET_AREA_DD								 =By.xpath("//p-dropdown/descendant::span[text()='Area']");
	private static By ASSETS_ASSET_BUSINESSUNIT_DD						 =By.xpath("//p-dropdown/descendant::span[text()='Business Unit']");
	private static By ASSETS_ASSET_COUNTRY_DD							 =By.xpath("//p-dropdown/descendant::span[text()='Country']");
	private static By ASSETS_ASSET_CUSTOMER_DD							 =By.xpath("//p-dropdown/descendant::span[text()='Customer']");
	private static By ASSETS_ASSET_MANUFACTURER_DD						 =By.xpath("//p-dropdown/descendant::span[text()='Manufacturer']");
	private static By ASSETS_ASSET_TYPE_DD								 =By.xpath("//p-dropdown/descendant::span[text()='Type']");
	private static By ASSETS_MAP_TO_GATEWAY_ED    	                     =By.xpath("//input[@placeholder='Search']");
	private static By ASSETS_MAP_TO_GATEWAY_SEARCH_ICON                  =By.xpath("//div[@class='search-icon sprite']");
	private static By ASSETS_SELECT_TO_GATEWAY_BT                        =By.xpath("//button[text()=' Select Gateway ']");
	private static By ASSETS_MAP_TO_GATEWAY_BT                           =By.xpath("//button[text()=' Map to Gateway ']");
	private static By ASSETS_CREATEASSET_BUSINESSUNIT_EB                 =By.xpath("//input[@name='business']");
	private static By EXPAND_ARROW_BT		               				 =By.xpath("(//i[@aria-label='Next slide'])[1]");
	private static By ASSET_METADATA_PLUS_BT							 =By.xpath("//div[@class='add-icon sprite']");
	private static By ASSET_CREATEASSET_ASSET_TYPE_DD					 =By.xpath("//p-dropdown[@name='type']");
	private static By ASSETS_ASSET_LICENSEPLATE_LABLE					 =By.xpath("//span[text()='License Plate']");
	private static By ASSETS_ASSET_PROULSIONTYPE_LABLE					 =By.xpath("//span[text()='Fuel Type/Propulsion Type']");
	private static By ASSETS_ASSET_TRANSMISSION_LABLE					 =By.xpath("//span[text()='Transmission']");
	private static By ASSETS_ASSET_MODEL_LABLE							 =By.xpath("//span[text()='Model']");
	private static By ASSETS_LIST_FIRST_VALUE_TEXT						 =By.xpath("(//div[@class='fl fh col-heading cell ellipses gatewayName'])[1]");
	private static By ASSETS_CREATEASSETS_ASSET_MANUFACTURER_EB          =By.xpath("//input[@name='manufacturerText']");
	private static By ASSETS_CREATEASSET_MODEL_EB   	 	             =By.xpath("//input[@name='model']");
	private static By ASSETS_COUNTRY_SEARCH_EB							 =By.xpath("//input[@class='ui-inputtext ui-widget ui-state-default ui-corner-all']");
	private static By ASSETS_DISPLACEMENT_EB							 =By.xpath("//input[@name='displacement']");
	private static By ASSETS_MAINTANENCE_EB							     =By.xpath("//input[@name='maintenanceInterval']");
	private static By ASSETS_FUELTANK_EB							     =By.xpath("//input[@name='fuelTankVolume']");
	private static By ASSETS_MILEAGE_EB							         =By.xpath("//input[@name='initialMileage']");
	private static String GATEWAY_DROPDOWNLIST						     ="//span[text()='{}']";
	private static String ASSETS_COUNTRYLIST							 ="//label[text()='{}']";	
	private static String ASSETS_DROPDOWNLIST						     ="//span[text()='{}']";

	public void clickCreateAssets() {	
		try {
			sleep(20000);
			if(!elementAvailability(ASSETS_HOMEPAGE_LK)) {
				for(int count=0;count<4;count++) {
					elementClickWithActions(EXPAND_ARROW_BT);
				}
			}
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			sleep(30000);
			waitElementVisibleClick(ASSETS_CREATEASSET_BT,800);
			sleep(12000);
		} catch (Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}

	}


	public void assetPageValidation() {/*TestRail#C48954,TestRail#C48965,TestRail#C48955*/
		try {;
		navigateToAssetPage();
		elementAvailability(ASSETS_ZFLOGO_IMG);
		sleep(10000);
		elementAvailability(ASSETS_SEARCH_EB);
		elementAvailability(ASSETS_SEARCH_BT);
		elementAvailability(ASSETS_COUNTRY_ST);
		elementAvailability(ASSETS_AREA_ST);
		elementAvailability(ASSETS_CUSTOMER_EB);
		elementAvailability(ASSETS_BUSSINESSUNIT_EB);
		elementAvailability(ASSETS_SORTBY_ST);
		elementAvailability(ASSETS_ASSETTYPE_ST);
		elementAvailability(ASSETS_MANUFACTURER_ST);
		elementAvailability(ASSETS_DATE_ST);
		elementAvailability(ASSETS_CLEARALL_LK);
		elementAvailability(ASSETS_CREATEASSET_BT);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void assetSearch(String AssetName) {
		try {;
		JSONObject jsonObject = JsonReader.getJsonObject("TCO2AssetSearch");
		navigateToAssetPage();
		waitElementToBeVisibleSendValue(ASSETS_SEARCH_EB,800, AssetName);
		waitElementVisibleClick(ASSETS_COUNTRY_DD,600); 
		waitElementToBeVisibleSendValue(ASSETS_COUNTRY_SEARCH_EB, 100, jsonObject.getAsString("Country"));
		elementClick(By.xpath(dynamicXpath(ASSETS_COUNTRYLIST,"Country")));
		elementClick(ASSETS_COUNTRY_DD);
		elementSendKeys(ASSETS_AREA_EB, jsonObject.getAsString("Area"));
		elementSendKeys(ASSETS_CUSTOMER_EB, jsonObject.getAsString("customer"));
		elementClick(ASSETS_SEARCH_BT);
		compareContainsText( waitElementVisibleGetText(ASSETS_SEARCHVALUE_DT,600),jsonObject.getAsString("AssetName"));
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void assetAdvancedSearch() {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("TCO3AssetAdvacnedSearch");
			navigateToAssetPage();
			waitElementVisibleClick(ASSETS_ADVANCEDSEARCH_BT,800);
			waitElementVisibleClick(ASSETS_ADVANCEDSEARCHPOPUP_ASSETTYPE_DD,800);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_BASICASSETTYPEVALUE_ST);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_MANUFACTURER_DD);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_MANUFACTURERVALUE_ST);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_DATEFROM_EB);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_DATEFROM_DT);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_DATETO_EB);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_DATETO_DT);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_APPLY_BT);
			compareText(jsonObject.getAsString("BasicAssestType"), elementGetText(ASSETS_ADVANCEDSEARCHPOPUP_BASICASSETTYPEVALUE_ST));
			compareText(jsonObject.getAsString("Manufacturer"), elementGetText(ASSETS_ADVANCEDSEARCHPOPUP_MANUFACTURERVALUE_ST));
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}


	public void clearAllFilter() {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("TCO4clearAllFilter");
			navigateToAssetPage();
			waitElementToBeVisibleSendValue(ASSETS_SEARCH_EB,800, jsonObject.getAsString("AssetName"));
			waitElementVisibleClick(ASSETS_COUNTRY_DD,500);
			elementClick(ASSETS_CZECHREPUBLIC_CB);
			waitElementVisibleClick(ASSETS_COUNTRY_DD,500);
			elementSendKeys(ASSETS_AREA_EB, jsonObject.getAsString("Area"));
			elementSendKeys(ASSETS_CUSTOMER_EB, jsonObject.getAsString("customer"));
			elementSendKeys(ASSETS_BUSSINESSUNIT_EB,jsonObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_FILTER_BT);
			elementClick(ASSETS_ADVANCEDSEARCHPOPUP_APPLY_BT);
			elementClick(ASSETS_CLEARALL_LK);
			checkfiltercleared(ASSETS_AREAVALUE_DT);
			checkfiltercleared(ASSETS_COUNTRYVALUE_DT);
			checkfiltercleared(ASSETS_ADVANCEDSEARCHPOPUP_BASICASSETTYPEVALUE_ST);
			checkfiltercleared(ASSETS_ADVANCEDSEARCHPOPUP_BRANDVALUE_ST);
			checkfiltercleared(ASSETS_ADVANCEDSEARCHPOPUP_MANUFACTURERVALUE_ST);

		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void assetSearchWithInvalidKey() {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("TCO5AssetSearchWithInvalidKey");
			navigateToAssetPage();
			waitElementToBeVisibleSendValue(ASSETS_SEARCH_EB,600, jsonObject.getAsString("AssetName"));
			waitElementVisibleClick(ASSETS_COUNTRY_DD,300);
			elementClick(ASSETS_CZECHREPUBLIC_CB);
			waitElementVisibleClick(ASSETS_COUNTRY_DD,100);
			elementSendKeys(ASSETS_AREA_EB, jsonObject.getAsString("Area"));
			elementSendKeys(ASSETS_CUSTOMER_EB,jsonObject.getAsString("customer"));
			elementSendKeys(ASSETS_BUSSINESSUNIT_EB,jsonObject.getAsString("BusinessUnit"));
			waitElementVisibleClick(ASSETS_SEARCH_BT,600);
			compareText(jsonObject.getAsString("Noresultvalue"), waitElementVisibleGetText(ASSETS_NOASSETSFOUND_DT,600));
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void checkfiltercleared(By byType) {
		try {
			if(DriverManager.getDriverInstance().findElement(byType).isDisplayed())
			{
				ExtentReport.testFailed("Element is  diaplayed after the clicking clear all "+byType.toString());
			} 

		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}finally{
			ExtentReport.testPassed("Element is not diaplayed after the clicking clear all "+byType.toString());
		}

	}

	public String createAssetstWithAllFeilds() {
		String AssetName =null;
		try {
			clickCreateAssets();
			JSONObject createAssetObject = JsonReader.getJsonObject("TCO6CreateAssetstWithAllFeilds");
			waitElementVisibleClick(ASSETS_CREATEASSET_ASSETTYPE_DD,300);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"AssetType")),300);
			sleep(3000);
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_AREA_EB,300,createAssetObject.getAsString("Area"));//new UI
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,createAssetObject.getAsString("LocationAddress"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,createAssetObject.getAsString("Customer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_EB,300,createAssetObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			AssetName=createAssetObject.getAsString("AssetName")+getRandomNumber();
			waitElementVisibleClick(ASSETS_SELECT_TO_GATEWAY_BT,300);
			elementSendKeys(ASSETS_MAP_TO_GATEWAY_ED,"Vivaldi");
			elementClick(ASSETS_MAP_TO_GATEWAY_SEARCH_ICON);
			waitElementVisibleClick(ASSETSGATEWAY_LIST_FIRST_VALUE,300);
			elementClick(ASSETS_MAP_TO_GATEWAY_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_VIN_VALUE_EB,300,createAssetObject.getAsString("MetaDatavin")+getRandomNumber(1,9999));
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,AssetName);
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD,300);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")),300);
			waitElementVisibleClick(ASSETS_CREATEASSET_MODEL_DD,300);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Model")),300);
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_ASSET_COMMETS_EB,300,createAssetObject.getAsString("Description"));//new UI
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",50);
		}catch(Exception e) {
			testFailed("An exception occured  and the error message is : "+e.getMessage());
		}
		return AssetName;
	}

	public void createAssetstWithAllAutopopulatedMetadataKeys() {
		try {
			clickCreateAssets();
			JSONObject MetadatajsonObject = JsonReader.getJsonObject("createAssetstWithAllAutopopulatedMetadataKeys");
			waitElementVisibleClick(ASSET_CREATEASSET_ASSET_TYPE_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"PassengerCar")));
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,MetadatajsonObject.getAsString("Area"));
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,MetadatajsonObject.getAsString("LocationAddress"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,MetadatajsonObject.getAsString("Customer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_EB,300,MetadatajsonObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,MetadatajsonObject.getAsString("AssetName")+getRandomNumber(1, 9999));
			elementClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")));
			waitElementVisibleClick(ASSETS_CREATEASSET_MODEL_DD,300);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Model")),300);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_VIN_VALUE_EB,300,MetadatajsonObject.getAsString("MetaDatavin")+getRandomNumber(1,9999));
			waitElementToBeVisibleSendValue(ASSETS_DISPLACEMENT_EB, 300,MetadatajsonObject.getAsString("MetaDataDisplacement"));
			elementSendKeys(ASSETS_MAINTANENCE_EB, MetadatajsonObject.getAsString("MetaDatamaintain"));
			elementSendKeys(ASSETS_FUELTANK_EB, MetadatajsonObject.getAsString("MetaDatamaintain"));
			waitElementToBeVisibleSendValue(ASSETS_MILEAGE_EB, 300,MetadatajsonObject.getAsString("MetaDatamaintain"));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_DESCRIPTION_EB,300,(MetadatajsonObject.getAsString("Description")+generateRandomNumber()));
			elementClick(ASSET_METADATA_PLUS_BT);
			elementSendKeys(ASSETS_CREATEASSET_METADATAKEY_EB, MetadatajsonObject.getAsString("MetaDatalicensePlate"));
			elementSendKeys(ASSETS_CREATEASSET_METADATAKEY_VALUE_EB, MetadatajsonObject.getAsString("MetaDatalicensePlate"));
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",300);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void createAssetstWithMandatoryAutopopulatedMetadataKeys() {
		try {
			clickCreateAssets();
			JSONObject jsonObject = JsonReader.getJsonObject("createAssetstWithMandatoryAutopopulatedMetadataKeys");
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,jsonObject.getAsString("AssetName")+getRandomNumber(1,9999));
			elementClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")));
			elementClick(ASSETS_CREATEASSET_MODEL_DD);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Model")));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_DESCRIPTION_EB,500,(jsonObject.getAsString("Description")+generateRandomNumber()));
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",300);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}



	public void verifyVehicleAssetstWithMandatoryAutopopulatedMetadataKeys() {
		try {
			JSONObject jsonObject = JsonReader.getJsonObject("verifyVehicleAssetstWithMandatoryAutopopulatedMetadataKeys");
			clickCreateAssets();
			waitElementVisibleClick(ASSET_CREATEASSET_ASSET_TYPE_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"PassengerCar")));
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,jsonObject.getAsString("AssetName")+generateRandomNumber());
			compareText(elementGetValue(ASSETS_ASSET_LICENSEPLATE_LABLE,"value"),jsonObject.getAsString("MetaDatalicensePlate"));
			compareText(elementGetValue(ASSETS_ASSET_PROULSIONTYPE_LABLE,"value"),jsonObject.getAsString("MetaDataPropulsionType"));
			compareText(elementGetValue(ASSETS_ASSET_TRANSMISSION_LABLE,"value"),jsonObject.getAsString("Transmission"));
			compareText(elementGetValue(ASSETS_ASSET_MODEL_LABLE,"value"),jsonObject.getAsString("Model"));
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void fieldValidationsMandatoryFieldVin() {
		try {
			clickCreateAssets();
			JSONObject jsonObject = JsonReader.getJsonObject("fieldValidationsMandatoryFieldVin");
			waitElementVisibleClick(ASSET_CREATEASSET_ASSET_TYPE_DD,600); 
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"AssetType")),300);
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,500); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,jsonObject.getAsString("LocationAddress"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,jsonObject.getAsString("Customer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_EB,300,jsonObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,jsonObject.getAsString("AssetName")+generateRandomNumber());
			elementSendKeys(ASSETS_CREATEASSET_VIN_VALUE_EB,(jsonObject.getAsString("MetaDatavin")+generateRandomNumber()));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageFailed",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageFailedReason",300);
			sleep(3000);
			elementClick(CREATE_ASSETS_PREVIOUS_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_UPDATEDVIN_VALUE_EB,300,(jsonObject.getAsString("MetaDatavin1")+generateRandomNumber()));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementVisibleClick(ASSETS_CREATEASSET_SUBMIT_BT,300);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageFailed",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageFailedReason",300);
			sleep(3000);
			elementClick(CREATE_ASSETS_PREVIOUS_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_UPDATEDVIN_VALUE_EB,300,(jsonObject.getAsString("MetaDatavin2")+generateRandomNumber()));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementVisibleClick(ASSETS_CREATEASSET_SUBMIT_BT,300);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",300);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}
	public void createAssetWithoutMandatoryInput() {
		try {
			clickCreateAssets();
			JSONObject jsonObject = JsonReader.getJsonObject("createAssetWithoutMandatoryInput");
			submitButtonVisibility(ASSETS_CREATEASSET_NEXT_BT," when Address in not entered");
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,300,jsonObject.getAsString("LocationAddress"));
			elementAvailability(ASSETS_CREATEASSET_NEXT_BT);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}
	public void executeActionWithoutParamater() {
		try {
			JsonReader.getJsonObject("executeActionWithoutParamater");
			String AssetID = createAssetstWithAllFeilds();
			selectAssetDetails(AssetID);
			waitElementVisibleClick(ASSETS_ACTION_BT,500);
			elementClick(ASSETS_ACTION_EDIT_BT);
			elementClick(ASSETS_ACTION_RESETALL_BT);
			waitElementVisibleClick(ASSETS_ACTION_APPLY_BT,500);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATE_ACTION_TOASTER_MSG_TXT,"ToasterMessageSuccessReasonAction",50);
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	public void executeActionWithParamater() {
		try {
			String assetID = createAssetstWithAllFeilds();
			JsonReader.getJsonObject("executeActionWithParamater");
			selectAssetDetails(assetID);
			elementClick(ASSETS_ACTION_BT);
			elementClick(ASSETS_ACTION_EDIT_BT);
			elementClick(ASSETS_ACTION_RESETALL_BT);
			elementClick(ASSETS_CREATEASSET_SETACTIONS_ACTIVATE_CB);
			waitElementVisibleClick(ASSETS_ACTION_APPLY_BT,500);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATE_ACTION_TOASTER_MSG_TXT,"ToasterMessageSuccessReasonAction",200);
		}catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}
	public void submitButtonVisibility(By byType,String message) {
		try {
			if(elementGetValue(byType,"disabled")!=null) {
				testPassed("SUBMIT button is disabled "+message);
			}
			else {
				testFailed("Message failed");
			}
		}
		catch(Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void CreateAssetstWithActions() {
		try {
			clickCreateAssets();
			JSONObject jsonObject = JsonReader.getJsonObject("TCO7CreateAssetstWithActions");
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,500); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			String AssetName=jsonObject.getAsString("AssetName")+getRandomNumber(0, 9999);
			elementClick(ASSETS_SELECT_TO_GATEWAY_BT);
			elementSendKeys(ASSETS_MAP_TO_GATEWAY_ED,"Bach-box");
			elementClick(ASSETS_MAP_TO_GATEWAY_TYPE_DD);
			elementClick(By.xpath(dynamicXpath(GATEWAY_DROPDOWNLIST,"GATEWAYType")));
			elementClick(ASSETS_MAP_TO_GATEWAY_SEARCH_ICON);
			waitElementVisibleClick(ASSETSGATEWAY_LIST_FIRST_VALUE,300);
			elementClick(ASSETS_MAP_TO_GATEWAY_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,AssetName);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_EB,300,jsonObject.getAsString("Manufacturer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_MODEL_EB,300,jsonObject.getAsString("Model"));//new UI
			elementClick(ASSETS_CREATEASSET_SETACTIONS_BT);
			setActions(jsonObject.getAsString("Action"));// Actions functionality has not been implemented
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_ASSET_COMMETS_EB,300,jsonObject.getAsString("Description"));//new UI
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATE_ACTION_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",50);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_ACTION_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",250);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void editAssetstActions() {
		try {
			clickCreateAssets();
			JSONObject jsonObject = JsonReader.getJsonObject("TCO9EditAssetstActions");
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,jsonData.getJsonData("LocationAddress"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,jsonData.getJsonData("Customer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_DD,300,jsonData.getJsonData("BusinessUnit"));//new UI
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,jsonData.getJsonData("AssetName"));
			elementClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")));
			elementClick(ASSETS_CREATEASSET_MODEL_DD);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Model")));
			elementClick(ASSETS_CREATEASSET_MAPTOGATEWAY_BT);
			elementClick(ASSETS_CREATEASSET_MAPTOGATEWAY_FIRSTRAW_LT);
			elementClick(ASSETS_CREATEASSET_MAPTOGATEWAY_MAP_BT);
			elementClick(ASSETS_CREATEASSET_SETACTIONS_BT);
			setActions(jsonData.getJsonData("Action"));  
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_DESCRIPTION_EB,300,jsonData.getJsonData("Description"));
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATE_ACTION_TOASTER_MSG_TXT,"ToasterMessageSuccessReasonAction",10);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_ACTION_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",200);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void editExistAssetDetails() {
		try {
			String AssetName = createAssetstWithAllFeilds();
			JSONObject jsonObject = JsonReader.getJsonObject("TCO8editExistAssetDetails");
			selectAssetDetails(AssetName);
			sleep(6000);
			waitElementVisibleClick(ASSETS_ASSETSDETAILS_BASICDETAILS_EDIT_BT,600);
			sleep(9000);
			elementClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD); 
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")));
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,jsonData.getJsonData("LocationAddress"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",300);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void createChildAssetWithAllFeilds() {
		try {
			createAssetstWithAllFeilds();
			JSONObject jsonObject = JsonReader.getJsonObject("CreateChildAssettWithAllFeilds");
			elementClick(ASSETS_ASSETSDETAILS_ADDEDASSET_BT);
			waitElementVisibleClick(ASSETS_ASSETSDETAILS_ADDCHILDASSET_BT,600);
			sleep(4000);
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,800); 
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")),500);
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,jsonObject.getAsString("Customer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_EB,300,jsonObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,jsonObject.getAsString("AssetName")+getRandomNumber(1,9999));
			elementClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")));
			elementClick(ASSETS_CREATEASSET_MODEL_DD);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Model")),300);
			waitElementVisibleClick(ASSETS_CREATEASSET_MAPTOGATEWAY_BT,300);
			elementClick(ASSETS_CREATEASSET_MAPTOGATEWAY_FIRSTRAW_LT);
			waitElementVisibleClick(ASSETS_CREATEASSET_MAPTOGATEWAY_MAP_BT,300);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_VIN_VALUE_EB,300,jsonObject.getAsString("MetaDatavin")+getRandomNumber(1,9999));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_DESCRIPTION_EB,300,jsonObject.getAsString("Description"));
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",80);

		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void verifyAssetActionsUI() {
		JSONObject jsonObject = JsonReader.getJsonObject("TCO8editExistAssetDetails");
		try {
			clickCreateAssets();
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,800);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")),500);
			elementSendKeys(ASSETS_ASSET_AREA_EB,jsonObject.getAsString("Area"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,jsonData.getJsonData("AssetName"));
			elementClick(ASSETS_SELECT_TO_GATEWAY_BT);
			elementSendKeys(ASSETS_MAP_TO_GATEWAY_ED,"Bach-box");
			elementClickWithActions(ASSETS_MAP_TO_GATEWAY_TYPE_DD);
			elementClick(By.xpath(dynamicXpath(GATEWAY_DROPDOWNLIST,"GATEWAYType")));
			elementClick(ASSETS_MAP_TO_GATEWAY_SEARCH_ICON);
			waitElementVisibleClick(ASSETS_LIST_FIRST_VALUE_TEXT,300);
			elementClick(ASSETS_MAP_TO_GATEWAY_BT);
			elementAvailability(ASSETS_CREATEASSET_SETACTIONS_BT);
			elementClick(ASSETS_CREATEASSET_SETACTIONS_BT);
			elementAvailability(ASSETS_CREATEASSET_SETACTIONS_DEACTIVATE_CB);
			elementAvailability(ASSETS_CREATEASSET_SETACTIONS_ACTIVATE_CB);
			elementAvailability(ASSETS_CREATEASSET_SETACTIONS_APPLY_BT);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public int generateRandomNumber() {
		Random rnd = new Random();
		int randomNum = (int) (rnd.nextInt(9999));
		return randomNum;
	}

	public void verifyToastermessage(By ByType1, String Jsondata1,By ByType2, String Jsondata2,long timeoutSeconds) {
		try {

			String toasterMessage = waitElementVisibleGetTextForToaster(ByType1,timeoutSeconds);
			compareText(jsonData.getJsonData(Jsondata1),toasterMessage);
			String toasterMessageText = elementGetText(ByType2);
			compareText(jsonData.getJsonData(Jsondata2),toasterMessageText);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public	String dynamicXpath(String xpath, String Jsonvalue) {
		return updateXpath(xpath,jsonData.getJsonData(Jsonvalue));
	}

	public void selectAssetDetails(String jsonObject){

		try {
			navigateToAssetPage();
			waitElementToBeVisibleSendValue(ASSETS_SEARCH_EB,500, jsonObject);
			waitElementVisibleClick(ASSETS_SEARCH_BT,500);
			sleep(10000);
			if(waitElementVisibleGetText(ASSETS_LIST_FIRST_VALUE,500).equalsIgnoreCase(jsonObject)){
				sleep(1000);
				waitElementVisibleClick(ASSETS_LIST_FIRST_EXPANDARROW_BT,600);
				sleep(3000);
			}else {
				testFailed(jsonObject +" is not displayed in search result");
			}
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}

	public void setActions(String jsonObject) {
		try {
			if(jsonObject.equalsIgnoreCase("activate")) {
				waitElementVisibleClick(ASSETS_CREATEASSET_SETACTIONS_DEACTIVATE_CB,600);
			}else {
				waitElementVisibleClick(ASSETS_CREATEASSET_SETACTIONS_ACTIVATE_CB,600);
			}
			elementClick(ASSETS_CREATEASSET_SETACTIONS_APPLY_BT);
		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
	}


	public void operatorHasNoAccessToCreateAsset()
	{
		JsonReader.getJsonObject("OperatorHasNoAccessToAddGateway");
		try {sleep(10000);
		navigateToAssetPage();
		waitElementVisibleClick(ASSETS_CREATEASSET_BT,1000);
		verifyToastermessage("ToasterMessageFailedReason");
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		} 
	}

	public void userWithNoPrivilegeCannotAccessAssetsPage()
	{
		JsonReader.getJsonObject("TCO3OperatorHasNoAccess");
		refreashPage();	
		verifyToastermessage("ToasterMessageFailedReason");
	}

	public void SortAssetsInAssetsHomepage()
	{
		try {
			navigateToAssetPage();
			assetSortingDropDownNavigation(ASSETS_ASSET_BUSINESSUNIT_DD);
			assetSortingDropDownNavigation(ASSETS_ASSET_COUNTRY_DD);
			assetSortingDropDownNavigation(ASSETS_ASSET_CUSTOMER_DD);
			assetSortingDropDownNavigation(ASSETS_ASSET_MANUFACTURER_DD);
			assetSortingDropDownNavigation(ASSETS_ASSET_TYPE_DD);
			assetSortingDropDownNavigation(ASSETS_ASSET_AREA_DD);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void assetSortingDropDownNavigation(By Dropdownvalue)
	{
		try {
			waitElementVisibleClick(ASSETS_ASSETSORTBY_DD,300);
			waitElementVisibleClick(Dropdownvalue,100);
			sleep(10000);
		} catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void verifyToastermessage(String Jsondata) {

		String toasterText;
		try {
			toasterText = waitElementVisibleGetTextForToaster(ASSETS_ASSETOPERATOR_TOASTERMESSAGE,60);
			compareText(toasterText,jsonData.getJsonData(Jsondata));
		} catch (InterruptedException e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void navigateToAssetPage() {
		try {
			sleep(10000);
			if(!elementAvailability(ASSETS_HOMEPAGE_LK)) {
				for(int count=0;count<4;count++) {
					elementClickWithActions(EXPAND_ARROW_BT);
				}
			}
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			sleep(15000);

		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
	}
}
