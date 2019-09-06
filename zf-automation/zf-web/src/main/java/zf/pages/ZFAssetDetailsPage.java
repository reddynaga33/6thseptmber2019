package zf.pages;

import org.openqa.selenium.By;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class ZFAssetDetailsPage extends ElementManager{

	ZFAssetPage zfAssetPage=new ZFAssetPage();
	JsonReader jsonData=new JsonReader();
	private static By ASSETSDETAILS_BASICDETAILS_EDIT_BT            =By.xpath("(//div[@class='fl edit-icon sprite'])[1]");
	private static By ASSETSDETAILS_ASSETDETAILS_EDIT_BT            =By.xpath("(//div[@class='fl edit-icon sprite'])[2]");
	private static By ASSETSDETAILS_ADDCHILDASSET_BT                =By.xpath("//i[@class='fa fa-plus']");
	private static By ASSETSDETAILS_TOASTER_MSG		   	        	=By.xpath("//div[@class='toast-title']");
	private static By ASSETSDETAILS_TOASTER_MSG_TXT		        	=By.xpath("//div[@class='ng-star-inserted']");
	private static By ASSETSDETAILS_ACTIONS_BT		         	    =By.xpath("//button[@class='zf-button primary action ng-star-inserted']");
	private static By ASSETSDETAILS_ACTIONS_EDITDISABLE_BT          =By.xpath("//div[@class='edit-icon sprite fl disable']");
	private static By ASSETSDETAILS_BASICDETAILSLABEL_TXT			=By.xpath("//span[contains(text(),'Basic Details')]");
	private static By ASSETSDETAILS_ASSETDETAILLABEL_TXT			=By.xpath("(//span[@class='fl'][contains(text(),'Asset Details')])");
	private static By ASSETSDETAILS_ADDEDASSET_BT                   =By.xpath( "//span[@class='ui-treenode-icon default root ng-star-inserted']");
	private static  By ASSETS_HOMEPAGE_lK   	 	                =By.xpath("//a[@title='Assets']");	
	private static By ASSETS_LIST_FIRST_EXPANDARROW_BT              =By.xpath("(//div[@class='go-to-icon sprite'])[1]");
	private static By EXPAND_ARROW_BT		                        =By.xpath("(//i[@aria-label='Next slide'])[1]");
	private static By EXISTING_ASSET_COUNTRY_TXT		            =By.xpath("(//div[@class='value ellipses'])[2]");
	private static By EXISTING_ASSET_AREA_TXT		            =By.xpath("(//div[@class='value ellipses'])[3]");
	private static By EXISTING_ASSET_CUSTOMER_TXT		            =By.xpath("(//div[@class='value ellipses'])[4]");
	private static By EXISTING_ASSET_BUNIT_TXT		            =By.xpath("(//div[@class='value ellipses'])[5]");
	private static By EXISTING_ASSET_NAME_TXT		            =By.xpath("(//div[@class='value ellipses'])[6]");
	private static By EXISTING_ASSET_MANUFACTURE_TXT		            =By.xpath("(//div[@class='value ellipses'])[7]");
	private static By EXISTING_ASSET_MODEL_TXT		            =By.xpath("(//div[@class='value ellipses'])[8]");

	public void OperatorHasNoAccessToEditAssetDetails() {
		try {
			sleep(8000);
			navigateToAssetPage();
			sleep(30000);
			waitElementVisibleClick(ASSETS_LIST_FIRST_EXPANDARROW_BT,600);
			waitElementVisibleClick(ASSETSDETAILS_BASICDETAILS_EDIT_BT,300);
			verifyToastermessage(ASSETSDETAILS_TOASTER_MSG,"ToasterMessageFailed",ASSETSDETAILS_TOASTER_MSG_TXT,"ToasterMessageFailedReason");
			elementClick(ASSETSDETAILS_ASSETDETAILS_EDIT_BT);
			verifyToastermessage(ASSETSDETAILS_TOASTER_MSG,"ToasterMessageFailed",ASSETSDETAILS_TOASTER_MSG_TXT,"ToasterMessageFailedReason");
		}catch(Exception e){
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}

	public void OperatorHasNoAccessToCreateChildAssets() {
		try {
			JsonReader.getJsonObject("TCO3OperatorHasNoAccess");
			navigateToAssetPage();
			sleep(30000);
			waitElementVisibleClick(ASSETS_LIST_FIRST_EXPANDARROW_BT,600);
			waitElementVisibleClick(ASSETSDETAILS_ADDCHILDASSET_BT,600);
			verifyToastermessage(ASSETSDETAILS_TOASTER_MSG,"ToasterMessageFailed",ASSETSDETAILS_TOASTER_MSG_TXT,"ToasterMessageFailedReason");
		}catch(Exception e){
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}

	public void OperatorHasNoAccessToEditActions() {
		try {
			JsonReader.getJsonObject("TCO3OperatorHasNoAccess");
			navigateToAssetPage();
			waitElementVisibleClick(ASSETS_LIST_FIRST_EXPANDARROW_BT,600);
			sleep(15000);
			elementClick(ASSETSDETAILS_ACTIONS_BT);
			if(elementAvailabileAndDisabled(ASSETSDETAILS_ACTIONS_EDITDISABLE_BT)){
				testPassed("Action button is disabled for editing the asset");
			}else {
				testFailed("Action button is enabled for editing");
			}

		}catch(Exception e){
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}

	public void operatorAccessToViewAssetDetails()
	{
		try {	
			navigateToAssetPage();
			sleep(20000);
			JsonReader.getJsonObject("TCO3OperatorHasNoAccess");
			waitElementVisibleClick(ASSETS_LIST_FIRST_EXPANDARROW_BT,600);
			elementAvailability(ASSETSDETAILS_BASICDETAILSLABEL_TXT);
			elementAvailability(ASSETSDETAILS_ASSETDETAILLABEL_TXT);

		}catch(Exception e){
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}


	public void viewExistingAssetDetails() {
		try {
			String assetID = zfAssetPage.createAssetstWithAllFeilds();
			JSONObject ExistingAssetjsonObject = JsonReader.getJsonObject("TCO6CreateAssetstWithAllFeilds");
			zfAssetPage.selectAssetDetails(assetID);
			waitElementVisibleClick(ASSETSDETAILS_ADDEDASSET_BT,600);
			sleep(5000);
			compareText(elementGetText(EXISTING_ASSET_COUNTRY_TXT),ExistingAssetjsonObject.getAsString("Country"));
			compareText(elementGetText(EXISTING_ASSET_AREA_TXT),ExistingAssetjsonObject.getAsString("Area"));
			compareText(elementGetText(EXISTING_ASSET_CUSTOMER_TXT),ExistingAssetjsonObject.getAsString("Customer"));
			compareText(elementGetText(EXISTING_ASSET_BUNIT_TXT),ExistingAssetjsonObject.getAsString("BusinessUnit"));
			compareTextIgnoreCase(elementGetText(EXISTING_ASSET_NAME_TXT),assetID);
			compareText(elementGetText(EXISTING_ASSET_MANUFACTURE_TXT),ExistingAssetjsonObject.getAsString("Manufacturer"));
			compareText(elementGetText(EXISTING_ASSET_MODEL_TXT),ExistingAssetjsonObject.getAsString("Model"));

		}catch(Exception e){
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}
	public void verifyToastermessage(By ByType, String Jsondata,By ByType1, String Jsondata1) {
		try {
			String toasterText = waitElementVisibleGetTextForToaster(ByType,60);
			compareText(jsonData.getJsonData(Jsondata),toasterText);
			String toasterText1 = elementGetText(ByType1);
			compareText(jsonData.getJsonData(Jsondata1),toasterText1);
		}catch (Exception e) {
			testFailed("An exception occured and error message is :"+e.getMessage());
		}
	}


	public void navigateToAssetPage() {
		try {
			if(!elementDisplayed(ASSETS_HOMEPAGE_lK)) {
				for(int count=0;count<4;count++) {
					elementClickWithActions(EXPAND_ARROW_BT);
					if(elementDisplayed(ASSETS_HOMEPAGE_lK)) {break;}
					;}}
			waitElementVisibleClick(ASSETS_HOMEPAGE_lK, 300);
			sleep(3000);

		}catch(Exception e){
			testFailed("An exception occured and error message is: "+e.getMessage());
		}
	}
}
