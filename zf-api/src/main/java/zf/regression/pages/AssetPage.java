package zf.regression.pages;

import java.util.List;

import org.openqa.selenium.By;

import framework.DatabaseUtility;
import framework.EnvironmentManager;
import framework.ExtentReport;
import framework.JsonReader;
import framework.RestApiUtility;
import framework.TestLogger;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.api.pages.AssetApiPage;

public class AssetPage extends RestApiUtility {
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	JsonReader jsonData=new JsonReader();
	AssetApiPage assetapipage=new AssetApiPage();

	private static  By ASSETS_HOMEPAGE_LK   	 	                     =By.xpath("//a[@title='Assets']");	
	private static  By ASSETS_NO_RESULT_TXT   	 	                     =By.xpath("//div[text()='No assets found.']");
	private static  By ASSETS_CREATEASSETS_ASSET_COUNTRY_DD   	 	     =By.xpath("//p-dropdown[@name='country']");
	private static  By ASSETS_CREATEASSET_LOCATION_ADDRESS_RB   	     =By.xpath("(//span[contains(@class,'ui-radiobutton-icon ui-clickable')])[1]");
	private static  By ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB        =By.xpath("//textarea[@name='address']");
	private static  By ASSETS_CREATEASSETS_ASSET_CUSTOMER_DD   	 	     =By.xpath("//p-dropdown[@name='customer']");
	private static  By ASSETS_CREATEASSET_BUSINESSUNIT_EB                =By.xpath("//input[@name='business']");
	private static  By ASSETS_CREATEASSET_NEXT_BT                        =By.xpath("(//button[@class='zf-button primary fr'])[1]");
	private static  By ASSETS_CREATEASSET_ASSETNAME_EB   	 	         =By.xpath("//input[@name='name']");
	private static  By ASSETS_CREATEASSET_ASSETTYPE_DD                   =By.xpath("//p-dropdown[@name='type']");
	private static  By ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD         =By.xpath("//p-dropdown[@name='manufacturer']");
	private static  By ASSETS_CREATEASSET_MODEL_DD   	 	             =By.xpath("//p-dropdown[@name='model']");
	private static  By ASSETS_CREATEASSET_DETAILS_NEXT_BT                =By.xpath("(//button[@class='zf-button primary fr'])[2]");
	private static  By ASSETS_CREATEASSET_SUBMIT_BT                      =By.xpath("(//button[@class='zf-button primary fr'])[3]");
	private static  By ASSETS_CREATEASSET_LICENSEPLATE_VALUE_EB          =By.xpath("//input[@name='licensePlate']");
	private static  By ASSETS_CREATEASSET_VIN_VALUE_EB                   =By.xpath("//input[@name='vin']");
	private static  By ASSETS_CREATEASSET_TOASTER_MSG			   		 =By.xpath("//div[@class='toast-title']");
	private static  By ASSETS_CREATEASSET_TOASTER_MSG_TXT				 =By.xpath("//div[@class='ng-star-inserted']");
	private static  By ASSETSGATEWAY_LIST_FIRST_VALUE                           =By.xpath("(//div[@class='main-details fw'])[1]");
	private static String ASSETS_DROPDOWNLIST						     ="//span[text()='{}']";
	private static By ASSETS_MODELDROPDOWNLIST						 =By.xpath("//span[text()='Aspen']");
	private static String GATEWAY_DROPDOWNLIST						     ="//span[text()='{}']";
	private static  By ASSETS_ASSET_AREA_EB						     =By.xpath("//input[@name='area']");
	private static  By ASSETS_CREATEASSET_BT    	                     =By.xpath("//button[@class='fr primary zf-button']");
	private static  By ASSETS_MAP_TO_GATEWAY_ED    	                     =By.xpath("//input[@placeholder='Search']");
	private static  By ASSETS_MAP_TO_GATEWAY_TYPE_DD                     =By.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable fa fa-chevron-down'])[5]");
	private static  By ASSETS_MAP_TO_GATEWAY_SEARCH_ICON                 =By.xpath("//div[@class='search-icon sprite']");
	private static  By ASSETS_SELECT_TO_GATEWAY_BT                       =By.xpath("//button[text()=' Select Gateway ']");
	private static  By ASSETS_MAP_TO_GATEWAY_BT                          =By.xpath("//button[text()=' Map to Gateway ']");
	private static  By ASSETS_SEARCH_EB                        		     =By.xpath("//input[@placeholder='Search']");
	private static  By ASSETS_SEARCH_BT                        		     =By.xpath("//div[@class='search-icon sprite']");
	private static  By ASSETS_EXPAND_BT                        		     =By.xpath("//div[@class='go-to-icon sprite']");
	private static  By ASSETDETAILS_BASICDETAILSEDIT_BT                  =By.xpath("(//div[@class='fl edit-icon sprite'])[1]");
	private static  By ASSETDETAILS_ASSETDETAILSEDIT_BT                  =By.xpath("(//div[@class='fl edit-icon sprite'])[2]");
	private static  By ASSETDETAILS_ADDITOIONALDETAILSEDIT_BT            =By.xpath("(//div[@class='fl edit-icon sprite'])[3]");
	private static  By ASSETDETAILS_COUNTRYVALUE_DT           			 =By.xpath("(//div[@class='value ellipses'])[2]/span");
	private static  By ASSETDETAILS_AREA_DT           			 		 =By.xpath("(//div[@class='value ellipses'])[3]/span");
	private static  By ASSETDETAILS_CUSTOMER_DT           			     =By.xpath("(//div[@class='value ellipses'])[4]/span");
	private static  By ASSETDETAILS_BUSINESSUNIT_DT           			 =By.xpath("(//div[@class='value ellipses'])[5]/span");
	private static  By ASSETDETAILS_ASSETNAME_DT           			 	 =By.xpath("(//div[@class='value ellipses'])[6]/span");
	private static  By ASSETDETAILS_MANUFACTURER_DT           			 =By.xpath("(//div[@class='value ellipses'])[7]/span");
	private static  By ASSETDETAILS_MODULE_DT           			 	 =By.xpath("(//div[@class='value ellipses'])[8]/span");
	private static  By ASSET_SEARCHED_RESULT_TXT						 =By.xpath("//div[@class='roots-assets-list fh fw']//div[1]//div[2]//div[3]//span[1]");
	private static  By ASSET_LIST_BT									 =By.xpath("//div[@class='roots-assets-list fh fw']//div[1]//div[2]//div[1]");
	private static  By ASSET_EDIT_ASSET_BT								 =By.xpath("//div[@class='horizontal-split-extended fw']//div[@class='fl edit-icon sprite']");
	private static  By ASSET_ADDITIONAL_DETAILS_COMMENTS				 =By.xpath("//textarea[@name='comments']");
	private static  By ASSETS_ASSET_CUSTOMER_EB						     =By.xpath("//input[@name='customer']");
	private static  By ASSETS_ASSET_COMMETS_EB						     =By.xpath("//textarea[@name='comments']");
	private static By EXPAND_ARROW_BT		                        =By.xpath("(//i[@aria-label='Next slide'])[1]");
	
	public String createAssetstWithAllFeilds() {
		String AssetName=null;
		try {
			JSONObject createAssetObject = JsonReader.getJsonObject("CreateAssetstWithAllFeilds");
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			sleep(30000);
			waitElementVisibleClick(ASSETS_CREATEASSET_BT,800);
			sleep(10000);
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
			waitElementVisibleClick(ASSETS_SELECT_TO_GATEWAY_BT,300);
			elementSendKeys(ASSETS_MAP_TO_GATEWAY_ED,"Vivaldi");
			elementClick(ASSETS_MAP_TO_GATEWAY_TYPE_DD);
			waitElementVisibleClick(By.xpath(dynamicXpath(GATEWAY_DROPDOWNLIST,"GATEWAYType")),300);
			waitElementVisibleClick(ASSETS_MAP_TO_GATEWAY_SEARCH_ICON,300);
			waitElementVisibleClick(ASSETSGATEWAY_LIST_FIRST_VALUE,300);
			elementClick(ASSETS_MAP_TO_GATEWAY_BT);
//			elementSendKeys(ASSETS_CREATEASSET_LICENSEPLATE_VALUE_EB,createAssetObject.getAsString("MetaDatalicensePlate")+getRandomNumber());
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
			testFailed("An exception has occured while creating asset using UI: "+e.getMessage());
		}
		return AssetName;
	}

	public void  UpdateAssetstWithAllFeilds() {

	}


	public String createAssetstWithRestAPI(String servicename, String PayLoad) {
		String createAsset=null;
		try {
			createAsset = assetapipage.createAsset(servicename, PayLoad);
		}catch(Exception e) {
			testFailed("An exception has occured while creating asset using  RestAPI: "+e.getMessage());
		}
		return createAsset;

	}
	public String createAssetstWithRestAPIReturnAssetName(String servicename, String PayLoad) {
		JSONObject CreateAssetJson = null;
		Response AssetResponse = null;
		String AssetName=null;
		try {
			CreateAssetJson = JsonReader.getJsonObject(PayLoad);			
			CreateAssetJson.put("name",CreateAssetJson.get("name").toString()+getRandomNumber());
			CreateAssetJson.put("description",CreateAssetJson.get("description").toString() +  getRandomNumber());	
			AssetName=jsonData.getJsonData("name");
			info("Creating asset with:"+CreateAssetJson.toJSONString());
			AssetResponse =  CreateServices(servicename,CreateAssetJson);	
			if( AssetResponse!=null && AssetResponse.getStatusCode()==200) {
				testPassed("The created Asset id is : "+AssetResponse.getBody().asString());
			}
			else {
				testFailed("The created Asset response status code is : "+AssetResponse.getStatusCode());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Asset payload: CreateAsset and the message is :"+e.getMessage());
		}
		return AssetName;

	}
	public String assetCreationDBvalidation(String assetName,String queryDetails) {
		String assetId=null;
		String[] assetDetails=null;
		try {
			String SelectQuery = jsonObject.getAsString(queryDetails);	
			SelectQuery = SelectQuery.replaceFirst("tempValue", assetName);
			SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASE")),SelectQuery);
			assetDetails=SelectQueryResult.get(0).split(" ");
			assetId=assetDetails[0];
			info("Values from Database is : "+SelectQueryResult.toString()+" and the assetID details is :"+assetId);
			if(DatabaseUtility.selectQueryComparision(SelectQueryResult,assetName)){

				testPassed("Database contains the Asset Details : "+assetName );
			}else {
				testFailed("Database does not contains the Asset Details : "+assetName);
			}
		}catch(Exception e) {
			testFailed("An exception has generated while comparing API response value in the database : "+e.getMessage());
		}
		return assetId;
	}

	public void GetNewlyCreatedAssetDetails(String servicename,String assetID) {
		Response AssetDetailResponse=null;
		try{
			AssetDetailResponse=GetServices(servicename,assetID);

			if(AssetDetailResponse.getStatusCode()==200) {

				ExtentReport.testPassed("Assets detail Response is : "+AssetDetailResponse.getBody().asString());
			}else {
				ExtentReport.testFailed("Assets is not present in the response message is : "+AssetDetailResponse.getBody().asString());

			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Assets and the message is : "+e.getMessage());
		}
	}

	public	String dynamicXpath(String xpath, String Jsonvalue) {
		return updateXpath(xpath,jsonData.getJsonData(Jsonvalue));
	}

	public void verifyToastermessage(By ByType1, String Jsondata1,By ByType2, String Jsondata2,long timeoutSeconds) {
		try {

			String toasterMessage = waitElementVisibleGetTextForToaster(ByType1,timeoutSeconds);
			compareText(jsonData.getJsonData(Jsondata1),toasterMessage);
			//			String toasterMessageText = elementGetText(ByType2);
			//			compareText(jsonData.getJsonData(Jsondata2),toasterMessageText);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public String  UpdateExistingAssetst(String AssetstName) {
		String updatedAssetName=null;
		try {
			JSONObject UpdateAssetObject = JsonReader.getJsonObject("UpdateExistingAsset");
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			sleep(30000);
			elementSendKeys(ASSETS_SEARCH_EB,AssetstName);
			sleep(10000);
			waitElementVisibleClick(ASSETS_SEARCH_BT,300);
			sleep(10000);
			waitElementVisibleClick(ASSETS_EXPAND_BT,300);
			sleep(3000);
			waitElementVisibleClick(ASSETDETAILS_BASICDETAILSEDIT_BT,500);//Editing the Basic details 
			waitElementVisibleClick(ASSETS_CREATEASSETS_ASSET_COUNTRY_DD,600); 
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Country")),300);
			waitElementToBeVisibleSendValue(ASSETS_ASSET_AREA_EB,300,UpdateAssetObject.getAsString("Area"));
			elementClickRadioButton(ASSETS_CREATEASSET_LOCATION_ADDRESS_RB);
			elementSendKeys(ASSETS_CREATEASSET_LOCATION_DESCRIPTION_EB,UpdateAssetObject.getAsString("LocationAddress"));
			waitElementToBeVisibleSendValue(ASSETS_ASSET_CUSTOMER_EB,300,UpdateAssetObject.getAsString("Customer"));
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_BUSINESSUNIT_EB,300,UpdateAssetObject.getAsString("BusinessUnit"));
			elementClick(ASSETS_CREATEASSET_NEXT_BT);
			updatedAssetName=UpdateAssetObject.getAsString("AssetName")+getRandomNumber();
			elementSendKeys(ASSETS_CREATEASSET_ASSETNAME_EB,updatedAssetName);
			elementClick(ASSETS_CREATEASSETS_ASSET_MANUFACTURER_DD);
			waitElementVisibleClick(By.xpath(dynamicXpath(ASSETS_DROPDOWNLIST,"Manufacturer")),300);
			waitElementVisibleClick(ASSETS_CREATEASSET_MODEL_DD,300);
			waitElementVisibleClick(ASSETS_MODELDROPDOWNLIST,300);
			waitElementVisibleClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT,300);
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
			verifyToastermessage(ASSETS_CREATEASSET_TOASTER_MSG,"ToasterMessageSuccess",ASSETS_CREATEASSET_TOASTER_MSG_TXT,"ToasterMessageSuccessReason",250);

			// After editing checking in the UI 
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			sleep(12000);
			elementSendKeys(ASSETS_SEARCH_EB,updatedAssetName);
			waitElementVisibleClick(ASSETS_SEARCH_BT,300);
			sleep(12000);
			waitElementVisibleClick(ASSETS_EXPAND_BT,300);
			if(waitElementToBeVisible(ASSETDETAILS_COUNTRYVALUE_DT, 300)){
				compareText(UpdateAssetObject.getAsString("Country"), elementGetText(ASSETDETAILS_COUNTRYVALUE_DT));
				compareText(UpdateAssetObject.getAsString("Area"), elementGetText(ASSETDETAILS_AREA_DT));
				compareText(UpdateAssetObject.getAsString("Customer"), elementGetText(ASSETDETAILS_CUSTOMER_DT));
				compareText(UpdateAssetObject.getAsString("BusinessUnit"), elementGetText(ASSETDETAILS_BUSINESSUNIT_DT));
				compareText(updatedAssetName, elementGetText(ASSETDETAILS_ASSETNAME_DT));
				compareText(UpdateAssetObject.getAsString("Manufacturer"), elementGetText(ASSETDETAILS_MANUFACTURER_DT));
//				compareText(UpdateAssetObject.getAsString("Model"), elementGetText(ASSETDETAILS_MODULE_DT));
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
		return updatedAssetName;
	}
	public void VerifyTheAssetCreatedFromAPIisAvailableIntoBackofficeUI(String AssetName) {

		try {
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			waitElementToBeVisibleSendValue(ASSETS_SEARCH_EB,300,AssetName);
			waitElementVisibleClick(ASSETS_SEARCH_BT,300);
			if(waitElementVisibleGetText(ASSETS_NO_RESULT_TXT,300).equals("No assets found")) {
				testFailed("The searched asset is not present in list");
			}else {
			if(waitElementVisibleGetText(ASSET_SEARCHED_RESULT_TXT,300).equals(AssetName)) {
				info("The Created Asset is avialable in UI");
			}
			else {
				testFailed("The Created Asset is not avialable in UI");

			}
		}} catch (Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
	public void VerifyUserCanUpdateTheAssetCreatedFromAPIByUsingBackofficeUI(String AssetName) {
		JsonReader.getJsonObject("Asset");
		try {
			waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
			waitElementToBeVisibleSendValue(ASSETS_SEARCH_EB,300,AssetName);
			waitElementVisibleClick(ASSETS_SEARCH_BT,300);
			waitElementVisibleClick(ASSET_LIST_BT,300);
			waitElementVisibleClick(ASSET_EDIT_ASSET_BT,300);
			waitElementToBeVisibleSendValue(ASSETS_CREATEASSET_ASSETNAME_EB,300,jsonData.getJsonData("AssetName"));
			elementClick(ASSETS_CREATEASSET_DETAILS_NEXT_BT);
			elementSendKeys(ASSET_ADDITIONAL_DETAILS_COMMENTS,jsonData.getJsonData("AssetComments"));
			elementClick(ASSETS_CREATEASSET_SUBMIT_BT);
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}
	}
	public String VerifyUserCanUpdatedTheAssetCreatedFromUIByUsingAPIAndChangesReflectedOnBackofficeUI(String AssetID, String ServiceName) {
		Response PutResponse;
		JSONObject Payload = JsonReader.getJsonObject("AssetUpdate");
		String AssetName=jsonData.getJsonData("name");
		PutResponse=PutServices(ServiceName, Payload,AssetID);
		if(PutResponse.getStatusCode()==204) {
			testPassed("The Upated Asset Response is :" +PutResponse.getBody().asString());
		}
		else {
			testFailed("The Upated Asset Response is :" +PutResponse.getBody().asString());
		}
		return AssetName;
	}
	
	public String UpdateAssestByAPIReturnAssetID(String AssetID, String ServiceName) {
		Response PutResponse;
		JSONObject Payload = JsonReader.getJsonObject("AssetUpdate");
		String id=jsonData.getJsonData("id");
		Payload.put("id",AssetID);
		PutResponse=PutServices(ServiceName, Payload,AssetID);
		if(PutResponse.getStatusCode()==204) {
			testPassed("The Upated Asset Response is :" +PutResponse.getBody().asString());
		}
		else {
			testFailed("The Upated Asset Response is :" +PutResponse.getBody().asString());
		}
		return id;
	}
	public void VerifyTheUpdatedAssetByAPI(String Service,String id) {
		
		GetNewlyCreatedAssetDetails(Service, id);
		
	}
	
	public void deleteAssetInDataBase(String assetID) {
		try {
			
			JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
			String SelectQuery = jsonData.getJsonData("SelectAssetQueryUsingAssetId");
			SelectQuery = SelectQuery.replaceFirst("tempValue", assetID);
			Response deleteAssetResponse = null;		
			String assetId = null;
			info("Deleting the Asset using assetId : "+assetID);
			deleteAssetResponse =  DeleteServices("asset",assetID);
			if( deleteAssetResponse!=null && deleteAssetResponse.getStatusCode()==200) {
				testPassed("The deleteted Asset id "+assetId+"is deleted and  status code is "+deleteAssetResponse.getStatusCode());
			}
			else {
				info("The deleteted Asset "+assetId+" status code is "+deleteAssetResponse.getStatusCode());
//			
			}
		}catch(Exception e) {
			testFailed("An exception occured and the error message is : "+e.getMessage());
		}}
	
	
	

  
  public void clickCreateAssets() {    

        try {
             if(!elementDisplayed(ASSETS_HOMEPAGE_LK)) {
             elementClick(EXPAND_ARROW_BT);
             }
             waitElementVisibleClick(ASSETS_HOMEPAGE_LK,500); 
             sleep(20000);
             waitElementVisibleClick(ASSETS_CREATEASSET_BT,800);
             sleep(10000);
        } catch (Exception e) {
             TestLogger.fileInfo(e.getMessage());
        }
  }

}