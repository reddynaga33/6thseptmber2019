package zf.iris.pages;

import org.openqa.selenium.By;

import framework.JsonReader;
import net.minidev.json.JSONObject;
import zf.pages.ZFAssetPage;

public class AssetCreateNewAssetPage extends ZFAssetPage {

	private static  By ASSETS_HOMEPAGE_lK   	 	                     =By.xpath("//a[@title='Assets']");	
	private static  By ASSETS_CREATEASSETS_ASSET_COUNTRY_DD   	 	     =By.xpath("//p-dropdown[@name='country']");
	private static  By ASSETS_CREATEASSET_LOCATION_ADDRESS_RB   	     =By.xpath("(//span[contains(@class,'ui-radiobutton-icon ui-clickable')])[1]");
	private static  By ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB        =By.xpath("//textarea[@name='address']");
	private static  By ASSETS_CREATEASSET_BUSINESSUNIT_EB                =By.xpath("//input[@name='business']");
	private static  By ASSETS_CREATEASSET_NEXT_BT                        =By.xpath("(//button[@class='zf-button primary fr'])[1]");
	private static  By ASSETS_CREATEASSET_ASSETNAME_EB   	 	         =By.xpath("//input[@name='name']");
	private static  By ASSETS_CREATEASSET_ASSETTYPE_DD                   =By.xpath("//p-dropdown[@name='type']");
	private static  By ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD         =By.xpath("//p-dropdown[@name='manufacturer']");
	private static  By ASSETS_CREATEASSET_MODEL_DD   	 	             =By.xpath("//p-dropdown[@name='model']");
	private static  By ASSETS_CREATEASSET_DETAILS_NEXT_BT                =By.xpath("(//button[@class='zf-button primary fr'])[2]");
	private static  By ASSETS_CREATEASSET_SUBMIT_BT                      =By.xpath("(//button[@class='zf-button primary fr'])[3]");
	private static  By ASSETS_CREATEASSET_VIN_VALUE_EB                   =By.xpath("//input[@name='vin']");
	private static  By ASSETS_CREATEASSET_TOASTER_MSG			   		 =By.xpath("//div[@class='toast-title']");
	private static  By ASSETS_CREATEASSET_TOASTER_MSG_TXT				 =By.xpath("//div[@class='ng-star-inserted']");
	private static String ASSETS_DROPDOWNLIST						     ="//span[text()='{}']";
	private static  By ASSETS_ASSET_AREA_EB						         =By.xpath("//input[@name='area']");
	private static  By ASSETS_ASSET_CUSTOMER_EB						     =By.xpath("//input[@name='customer']");
	private static  By ASSETS_ASSET_COMMETS_EB						     =By.xpath("//textarea[@name='comments']");
	private static  By ASSETS_CREATEASSET_BT    	                     =By.xpath("//button[@class='fr primary zf-button']");
	private static By EXPAND_ARROW_BT		               				 =By.xpath("(//i[@aria-label='Next slide'])[1]");

	public String AssetCreateNewAsset() {
		String AssetName =null;
		try {

			JSONObject createAssetObject = JsonReader.getJsonObject("TCO6CreateAssetstWithAllFeilds");
			if(!elementAvailability(ASSETS_HOMEPAGE_lK)) {
				elementClick(EXPAND_ARROW_BT);
				elementClick(EXPAND_ARROW_BT);

			}
			waitElementVisibleClick(ASSETS_HOMEPAGE_lK,500); 

			sleep(27000);
			waitElementVisibleClick(ASSETS_CREATEASSET_BT,800);
			sleep(12000);
			//NEW UI
			waitElementVisibleClick(ASSETS_CREATEASSET_ASSETTYPE_DD,300);
			elementClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"AssetType")));
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")),300);
			waitElementToBeVisibleSendValue(ASSETS_ASSET_AREA_EB,300,createAssetObject.getAsString("Area"));//new UI
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,createAssetObject.getAsString("LocationAddress"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,createAssetObject.getAsString("Customer"));//new UI
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_EB,300,createAssetObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			AssetName=createAssetObject.getAsString("AssetName")+getRandomNumber();
			elementSendKeys(ASSETS_CREATEASSET_VIN_VALUE_EB,createAssetObject.getAsString("MetaDatavin")+getRandomNumber(1,9999));
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,AssetName);
			elementClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")),300);
			waitElementVisibleClick(ASSETS_CREATEASSET_MODEL_DD,300);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Model")),300);
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			waitElementToBeVisibleSendValue(ASSETS_ASSET_COMMETS_EB,300,createAssetObject.getAsString("Description"));//new UI
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",250);
		}catch(Exception e) {
			testFailed("An exception occured  and the error message is : "+e.getMessage());
		}
		return AssetName;
	}

}