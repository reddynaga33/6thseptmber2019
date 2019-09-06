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
import net.minidev.json.JSONUtil;
import net.minidev.json.parser.JSONParser;

public class FleetPage extends RestApiUtility{
	JSONObject jsonObject = JsonReader.getJsonObject(EnvironmentManager.getSqlServer().trim());
	DatabaseUtility databaseutility=new DatabaseUtility();
	List<String> SelectQueryResult = null;
	JsonReader jsonReader=new JsonReader();
	private static By FLEETTAB_ICON                                    = By.xpath("//a[@title='Fleets']");
	private static By FLEET_CREATENEWFLEETBUTTON_BT                    = By.xpath("//span[text()='Create New Fleet']");
	private static By FLEET_BASICDATAFLEETNAME_EB                      = By.xpath("(//input[@type='text'])[2]");
	private static By FLEET_BASICDATAINFOTEXT_EB                       = By.xpath("//textarea[@formcontrolname='description']");
	private static By FLEET_NEXTSTEP_BT                                = By.xpath("//span[text()='Next Step']");
	private static By FLEET_ASSIGNVEHICLESSEARCH_EB                    = By.xpath("//input[@placeholder='Search for available vehicles']");
	private static By FLEET_ASSIGNVEHICLELOADER_BT                     = By.xpath("(//i[@class='om-icon-plus-circle ng-star-inserted'])[1]");
	private static By FLEET_CREATEFLEET_BT                             = By.xpath("//span[text()='Create Fleet']");
	private static By FLEET_FILTEREXPANDROW_BT                         = By.xpath("//i[@class='om-icon-filter-down ng-trigger ng-trigger-iconRotation']");
	private static By FLEET_FILTERSEARCH_EB                            = By.xpath("//input[@placeholder='Search']");
	private static By FLEET_FLEETELEMENTLIST_LT                        = By.xpath("//span[@class='name ng-binding']");
	private static By FLEET_FLEETEDITDATA_BT                           = By.xpath("//span[text()='Edit Fleet']");
	private static By FLEET_NEWFLEETNAME_EB                            = By.xpath("(//input[@type='text'])[2]");
	private static By FLEET_NEWFLEETDESCRIPTION_EB                     = By.xpath("//textarea[@name='newFleetDescription']");
	private static By FLEET_DELETEFLEET_BT                             = By.xpath("//span[text()='Delete Fleet']");
	private static By FLEET_CLICKYES_BT                                = By.xpath("//span[contains(text(),'Yes')]");
	private static By FLEET_FIRSTFLEET_TXT                             = By.xpath("(//div[@class='fleet'])[1]");
	private static By FLEET_NORESULTFOUND_TXT                          = By.xpath("//div[text()='No results found']");
	private static By FLEET_FIRSTFLEETRESULT_TXT                       = By.xpath("(//span[@class='name'])[1]");
	private static By FLEET_SEARCH_CLICK_LINK						   = By.xpath("//span[@class='name ng-binding']");		
	private static By FLEET_SEARCH_CLEAR_BT						       = By.xpath("//span[@class='clear-btn']");
	private static By FLEET_SEARCH_ITEM_LT						       = By.xpath("//span[@class='name']");
	private static By FLEET_NAME_TXT   						           = By.xpath("(//div[@class='col'])[1]");
	private static By FLEET_CREATE_TOASTER_MSG_TXT				 	   = By.xpath("//div[@class='toast-title']");
	private static By FLEET_SEARCHED_DISPLAY_AREA_TXT				   = By.xpath("//div[@class='client-name ng-binding']");
	private static By FLEET_UPDATE_SAVE_BT				               = By.xpath("//span[text()='Save Changes']");
	private static By DEFAULTFLEET_SIZE_TXT   				                   = By.xpath("(//span[@class='fleet-size'])[1]");
	public String FleetName=null;


	/*
	 * Method to click Fleet Tab
	 */
	public void clikcOnFleetTab() 
	{try {
		waitElementVisibleClick(FLEETTAB_ICON,300);
		sleep(15000);
	}catch(Exception e) {
		testFailed("An exception occured"+e.getMessage());
	}}

	/* 
	 * Method to use for clicking Create New Fleet Button
	 */

	public void clickOnCreateNewFleetIcon() 
	{try {
		sleep(8000);
		waitElementVisibleClick(FLEET_CREATENEWFLEETBUTTON_BT, 300);
	}catch(Exception e) {
		testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
	}	}


	// Adding a New Fleet 

	public String addNewFleetwithDefaultVehicleAdded()
	{
		clikcOnFleetTab();
		clickOnCreateNewFleetIcon();
		// To Read the Fleet Data From Json File
		JSONObject FleetjsonObject = JsonReader.getJsonObject("Fleet");
		try {
			//  Fleet Basic Data
			FleetName=FleetjsonObject.getAsString("fleetname")+getRandomNumber(1,99999);
			elementSendKeys(FLEET_BASICDATAFLEETNAME_EB, FleetName);
			elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, FleetjsonObject.getAsString("infotext"));
			elementClick(FLEET_NEXTSTEP_BT);
			// Assigning The Vehicles
			sleep(8000);
//			waitElementToBeVisibleSendValue(FLEET_ASSIGNVEHICLESSEARCH_EB, 300,FleetjsonObject.getAsString("vehicle"));
			waitElementVisibleClick(FLEET_ASSIGNVEHICLELOADER_BT,300);
			sleep(8000);
			elementClick(FLEET_NEXTSTEP_BT);
			//For Creating Fleet 
			sleep(8000);
			elementClick(FLEET_CREATEFLEET_BT);
			verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,FleetjsonObject,"ToasterMessageSuccessReason",50);
		}catch(Exception e)
		{
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return FleetName;
	}

	
	public int getDefaultFleetDetails() {
		int count=0;
		try {
			validateFleetInUI("Default fleet");
		String elementGetText = elementGetText(DEFAULTFLEET_SIZE_TXT);
			count=Integer.parseInt(elementGetText);
			info("The default fleet contain "+count+" vehicles.");
		}catch(Exception e) {
			testFailed("An exception occured"+e.getMessage());
		}
		return count;
	}
public void campareDefaultfleetsize(int assign,int unassign) {
	try{if(assign<unassign) {
		testPassed("After deleteing the fleet, the default vehicle count is increated");
	}else {
		testFailed("After deleteing the fleet, the default vehicle count is not increated");
	}}catch(Exception e) {
		testFailed("An exception occured and the error message is "+e.getMessage());
	}
}
	public String addNewFleet()
	{
		clikcOnFleetTab();
		clickOnCreateNewFleetIcon();
		// To Read the Fleet Data From Json File
		JSONObject FleetjsonObject = JsonReader.getJsonObject("Fleet");
		try {
			//  Fleet Basic Data
			FleetName=FleetjsonObject.getAsString("fleetname")+getRandomNumber(1,99999);
			elementSendKeys(FLEET_BASICDATAFLEETNAME_EB, FleetName);
			elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, FleetjsonObject.getAsString("infotext"));
			elementClick(FLEET_NEXTSTEP_BT);
			// Assigning The Vehicles
			sleep(8000);
			waitElementToBeVisibleSendValue(FLEET_ASSIGNVEHICLESSEARCH_EB, 300,FleetjsonObject.getAsString("vehicle"));
			waitElementVisibleClick(FLEET_ASSIGNVEHICLELOADER_BT,300);
			sleep(8000);
			elementClick(FLEET_NEXTSTEP_BT);
			//For Creating Fleet 
			sleep(8000);
			elementClick(FLEET_CREATEFLEET_BT);
			verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,FleetjsonObject,"ToasterMessageSuccessReason",50);
		}catch(Exception e)
		{
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return FleetName;
	}

	public String createNewFleetWithAllData()
	{
		clikcOnFleetTab();
		clickOnCreateNewFleetIcon();
		// To Read the Fleet Data From Json File
		JSONObject FleetjsonObject = JsonReader.getJsonObject("Fleet");
		try {
			FleetName=FleetjsonObject.getAsString("fleetname")+getRandomNumber();
			elementSendKeys(FLEET_BASICDATAFLEETNAME_EB, FleetName);
			elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, FleetjsonObject.getAsString("infotext"));
			elementClick(FLEET_NEXTSTEP_BT);
			waitElementToBeVisibleSendValue(FLEET_ASSIGNVEHICLESSEARCH_EB, 300,FleetjsonObject.getAsString("vehicle"));
			waitElementVisibleClick(FLEET_ASSIGNVEHICLELOADER_BT,300);
			waitElementVisibleClick(FLEET_NEXTSTEP_BT,300);
			waitElementVisibleClick(FLEET_CREATEFLEET_BT,300);
			verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,FleetjsonObject,"ToasterMessageSuccessReason",50);
		}catch(Exception e)
		{
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return FleetName;
	}

	public String createNewFleetWithoutVehicle()
	{
		clikcOnFleetTab();
		clickOnCreateNewFleetIcon();
		try {JSONObject fleetjsonObject = JsonReader.getJsonObject("Fleet");
		FleetName=fleetjsonObject.getAsString("fleetname")+getRandomNumber();
		waitElementToBeVisibleSendValue(FLEET_BASICDATAFLEETNAME_EB,300, FleetName);
		elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, fleetjsonObject.getAsString("infotext"));
		waitElementVisibleClick(FLEET_NEXTSTEP_BT,300);
		sleep(9000);
		waitElementVisibleClick(FLEET_NEXTSTEP_BT,300);
		sleep(10000);
		waitElementVisibleClick(FLEET_CREATEFLEET_BT,300);
		verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,fleetjsonObject,"ToasterMessageSuccessReason",100);
		}catch(Exception e)
		{
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return FleetName;
	}


	public String createNewFleetWithMandatoryData()
	{
		clikcOnFleetTab();
		clickOnCreateNewFleetIcon();
		try {
			JSONObject fleetjsonObject = JsonReader.getJsonObject("Fleet");
			FleetName=fleetjsonObject.getAsString("fleetname")+getRandomNumber();
			waitElementToBeVisibleSendValue(FLEET_BASICDATAFLEETNAME_EB,300, FleetName);
			waitElementVisibleClick(FLEET_NEXTSTEP_BT,300);
			sleep(8000);
			waitElementVisibleClick(FLEET_NEXTSTEP_BT,300);
			sleep(8000);			
			waitElementVisibleClick(FLEET_CREATEFLEET_BT,300);
			verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,fleetjsonObject,"ToasterMessageSuccessReason",50);
		}catch(Exception e)
		{
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return FleetName;
	}


	public String editNewFleet(String editFleetName)
	{String fleetNewName=null;
	try {
		clikcOnFleetTab();
		sleep(20000);
		JSONObject fleetjsonObject = JsonReader.getJsonObject("EditFleet");
		if(!elementAvailability(FLEET_FILTERSEARCH_EB)) {
			waitElementVisibleClick(FLEET_FILTEREXPANDROW_BT, 300);}
		waitElementToBeVisibleSendValue(FLEET_FILTERSEARCH_EB, 300, editFleetName);
		String elementtext=elementGetText(FLEET_FIRSTFLEETRESULT_TXT);
		if(elementtext.equals(editFleetName))
		{
			waitElementVisibleClick(FLEET_FIRSTFLEETRESULT_TXT,300);
			waitElementVisibleClick(FLEET_FLEETEDITDATA_BT,300);
			fleetNewName = fleetjsonObject.getAsString("fleetnamenew")+getRandomNumber(1,9999);
			waitElementToBeVisibleSendValue(FLEET_NEWFLEETNAME_EB,300,fleetNewName);
			elementSendKeys(FLEET_BASICDATAINFOTEXT_EB, fleetjsonObject.getAsString("infotextnew"));
			elementClick(FLEET_NEXTSTEP_BT);
			sleep(8000);
			waitElementVisibleClick(FLEET_ASSIGNVEHICLELOADER_BT,300);
		
			waitElementVisibleClick(FLEET_NEXTSTEP_BT,300);
			sleep(8000);
			waitElementVisibleClick(FLEET_UPDATE_SAVE_BT,300);
			verifyToastermessage(FLEET_CREATE_TOASTER_MSG_TXT,fleetjsonObject,"ToasterMessageSuccessReason",50);
		}
	}catch(Exception e) {
		testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
	}
	return fleetNewName;
	}

	public void deleteFleet()
	{
		clikcOnFleetTab();
		elementClick(FLEET_FILTEREXPANDROW_BT);
		elementSendKeys(FLEET_FILTERSEARCH_EB, jsonReader.getJsonData("fleetnamenew"));
		sleep(5000);
		String elementtext=elementGetText(FLEET_FLEETELEMENTLIST_LT);
		if(elementtext.equals(jsonReader.getJsonData("fleetnamenew")))
		{
			waitElementVisibleClick(FLEET_DELETEFLEET_BT,300);
			elementClick(FLEET_CLICKYES_BT);
		}
	}
	//Verify user can clear the search field 3rd june 2019
	public void verifyClearFleetSearchField() {
		JsonReader.getJsonObject("Fleet");
		clikcOnFleetTab();
		waitElementVisibleClick(FLEET_FILTEREXPANDROW_BT, 300);
		elementSendKeys(FLEET_FILTERSEARCH_EB, jsonReader.getJsonData("fleetnamenew"));	
		info("The enter value is : "+elementGetText(FLEET_FLEETELEMENTLIST_LT));
		elementClear(FLEET_FILTERSEARCH_EB);
		info(elementGetText(FLEET_FLEETELEMENTLIST_LT)+"The entered value is cleard");

	}
	//Verify user can search the fleet  3rd june 2019
	public void verifyFleetIsPresent() 
	{
		JsonReader.getJsonObject("Fleet");
		clikcOnFleetTab();
		waitElementVisibleClick(FLEET_FILTEREXPANDROW_BT, 300);
		elementSendKeys(FLEET_FILTERSEARCH_EB, jsonReader.getJsonData("fleetnamenew"));	
		info("The enter value is : "+elementGetText(FLEET_FLEETELEMENTLIST_LT));
		sleep(5000);
		compareText(elementGetText(FLEET_FIRSTFLEET_TXT),jsonReader.getJsonData("fleetnamenew"));
		elementClear(FLEET_FILTERSEARCH_EB);
		//		elementSendKeys(FLEET_FILTERSEARCH_EB, jsonReader.getJsonData("nonExistingFleetname"));			
		//		compareText(elementGetText(FLEET_NORESULTFOUND_TXT),jsonReader.getJsonData("nOnExistingFieldOutput"));
	}

	public String  createFleetWithAPI(String servicename, String Payload) {

		JSONObject CreateFleetJson = null;
		String FleetId =null;
		Response FleettResponse = null;
		String FleetName=null;
		try {
			CreateFleetJson = JsonReader.getJsonObject(Payload);			
			CreateFleetJson.put("name",CreateFleetJson.get("name").toString()+getRandomNumber(1,99999));
			CreateFleetJson.put("description",CreateFleetJson.get("description").toString() +  getRandomNumber());	
			FleetName=CreateFleetJson.get("name").toString();
			FleettResponse = CreateServices(servicename, CreateFleetJson);
			if( FleettResponse!=null && FleettResponse.getStatusCode()==200) {
				FleetId = FleettResponse.getBody().asString();	
				testPassed("The created Fleet id is : "+FleetId);
			}
			else {
				testFailed("Fleet is not created and the response status code is : "+FleettResponse.getStatusCode()+" and the response is : "+FleettResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());

		}
		return FleetName;		
	}

	public void validateFleetInUI(String FleetName) {
		try {
			clikcOnFleetTab();
			if(!elementAvailability(FLEET_FILTERSEARCH_EB)) {
				waitElementVisibleClick(FLEET_FILTEREXPANDROW_BT, 300);}

			waitElementToBeVisibleSendValue(FLEET_FILTERSEARCH_EB,300,FleetName);
			sleep(15000);
			compareText(elementGetText(FLEET_FIRSTFLEETRESULT_TXT),FleetName);
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload: CreateAsset and the message is :"+e.getMessage());
		}
	}

	public void validateFleetIsNotInUI(String FleetName) {
		try {
			clikcOnFleetTab();
			if(!elementAvailability(FLEET_FILTERSEARCH_EB)) {
				waitElementVisibleClick(FLEET_FILTEREXPANDROW_BT, 300);}

			waitElementToBeVisibleSendValue(FLEET_FILTERSEARCH_EB,300,FleetName);
			sleep(10000);
			compareText(elementGetText(FLEET_NORESULTFOUND_TXT),"No results found");
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload: CreateAsset and the message is :"+e.getMessage());
		}
	}


	public String createFleetWithAPIResponse(String servicename, String Payload) {

		JSONObject CreateFleetJson = null;
		String FleetId =null;
		Response FleettResponse = null;
		try {
			CreateFleetJson = JsonReader.getJsonObject(Payload);			
			CreateFleetJson.put("name",CreateFleetJson.get("name").toString()+getRandomNumber());
			CreateFleetJson.put("description",CreateFleetJson.get("description").toString() +  getRandomNumber());	
			FleettResponse = CreateServices(servicename, CreateFleetJson);
			if( FleettResponse!=null && FleettResponse.getStatusCode()==200) {
				FleetId = FleettResponse.getBody().asString();	
				testPassed("The created Fleet id is : "+FleetId);
			}
			else {
				testFailed("Fleet is not created and the response status code is : "+FleettResponse.getStatusCode()+" and the response is : "+FleettResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return FleetId;		
	}


	public String UpdateWithAPIForDeletedFleet(String servicename, String Payload, String FleetID) {
		JSONObject UpdateFleetJson = null;
		Response updateFleettResponse = null;
		String UpdateFleetname=null;
		String UpdatedFleetId=null;
		try {
			UpdateFleetJson = JsonReader.getJsonObject(Payload);
			UpdateFleetJson.put("name",UpdateFleetJson.get("name").toString()+getRandomNumber());
			UpdateFleetname=UpdateFleetJson.get("name").toString();
			updateFleettResponse=PutServices(servicename, UpdateFleetJson, FleetID);
			if(updateFleettResponse.getStatusCode()==404) {
				UpdatedFleetId = updateFleettResponse.getBody().asString();	
				testPassed("The created Fleet id is : "+UpdatedFleetId);
			}
			else {
				testFailed("Fleet is not created and the response status code is : "+updateFleettResponse.getStatusCode()+" and the response is : "+updateFleettResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return UpdateFleetname;
	}
	public String UpdateFleetWithAPI(String servicename, String Payload, String FleetID) {
		JSONObject UpdateFleetJson = null;
		Response updateFleettResponse = null;
		String UpdateFleetname=null;
		String UpdatedFleetId=null;
		try {
			UpdateFleetJson = JsonReader.getJsonObject(Payload);
			UpdateFleetJson.put("name",UpdateFleetJson.get("name").toString()+getRandomNumber());
			UpdateFleetname=UpdateFleetJson.get("name").toString();
			updateFleettResponse=PutServices(servicename, UpdateFleetJson, FleetID);
			if(updateFleettResponse.getStatusCode()==204) {
				UpdatedFleetId = updateFleettResponse.getBody().asString();	
				testPassed("The created Fleet id is : "+UpdatedFleetId);
			}
			else {
				testFailed("Fleet is not created and the response status code is : "+updateFleettResponse.getStatusCode()+" and the response is : "+updateFleettResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
		return UpdateFleetname;
	}

	public String VerifyUserCanDeleteTheFleet(String FleetName) {

		try {
			//			addNewFleet();
			deleteFleet(FleetName);
		} catch (Exception e) {
			testFailed("An exception has occured while delete the fleet and the message is :"+e.getMessage());
		}
		return FleetName;
	}
	public void deleteFleet(String FleetName)
	{
		try{clikcOnFleetTab();
		sleep(30000);
		refreashPage();
		sleep(20000);
		if(!elementAvailability(FLEET_FILTERSEARCH_EB)) {
			waitElementVisibleClick(FLEET_FILTEREXPANDROW_BT, 300);}
		waitElementToBeVisibleSendValue(FLEET_FILTERSEARCH_EB,300,FleetName);
		sleep(10000);
		String fleetnameFromUI=elementGetText(FLEET_FIRSTFLEETRESULT_TXT);
		if(fleetnameFromUI.equals(FleetName))
		{sleep(30000);
		elementClick(FLEET_DELETEFLEET_BT);
		elementClick(FLEET_CLICKYES_BT);
		info("Fleet is deleted from UI");
		}
		else{
			testFailed("The Fleet :"+ FleetName + " is available in UI");
		}}catch(Exception e) {

			testFailed("An exception has occured while delete the fleet and the message is :"+e.getMessage());
		}

	}
	public void VerifyTheDeletedVehicleByUsingfleetsAPI(String id) {
		Response FleetVehicleResponse=null;
		try {
			FleetVehicleResponse = GetServices("Fleet",id);
			if( FleetVehicleResponse!=null && FleetVehicleResponse.getStatusCode()==404) {
				testPassed("The created Fleet :"+ id + " is not available in DB");
				info("The API reposne body is :"+ FleetVehicleResponse.getBody().asString());
			}
			else {
				testFailed("The created Fleet :"+ id + " is available in DB");
				info("The API reposne body is :"+ FleetVehicleResponse.getBody().asString());
			}
		}catch(Exception e) {
			info(e.getMessage());

		}
	}
	public String ValidateCreatedFleetInDB(String createFleetNameInUI, String queryDetails) {
		String CreatedFleetID=null;
		String[] fleetDetails=null;
		try {String SelectQuery = jsonObject.getAsString(queryDetails);	
		SelectQuery = SelectQuery.replaceFirst("tempValue", createFleetNameInUI);
		SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
		fleetDetails=SelectQueryResult.get(0).split(" ");
		CreatedFleetID=fleetDetails[0];
		info("Values from Database is : "+SelectQueryResult.toString()+" and the FleetID details is :"+CreatedFleetID);

		if(DatabaseUtility.selectQueryComparision(SelectQueryResult,createFleetNameInUI)){

			testPassed("Database contains the Fleet name : "+createFleetNameInUI );
		}else {
			testFailed("Database does not contains the FleetName : "+createFleetNameInUI);
		}


		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload: CreateAsset and the message is :"+e.getMessage());
		}
		return CreatedFleetID;
	}

	public String ValidateDeletedFleetInDB(String createFleetNameInUI, String queryDetails) {
		String CreatedFleetID=null;
		String[] fleetDetails=null;
		try {String SelectQuery = jsonObject.getAsString(queryDetails);	
		SelectQuery = SelectQuery.replaceFirst("tempValue", createFleetNameInUI);
		SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
		fleetDetails=SelectQueryResult.get(0).split(" ");
		CreatedFleetID=fleetDetails[0];
		info("Values from Database is : "+SelectQueryResult.toString()+" and the FleetID details is :"+CreatedFleetID);

		if(!(DatabaseUtility.selectQueryComparision(SelectQueryResult,createFleetNameInUI))){

			testPassed("Database does not contains the Fleet name : "+createFleetNameInUI );
		}else {
			testFailed("Database contains the FleetName : "+createFleetNameInUI);
		}


		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload  and the message is :"+e.getMessage());
		}
		return CreatedFleetID;

	}

	public Response getCreatedFleetDetails(String servicename,String serviceID) {

		Response FleetDetailResponse=null;
		try{
			FleetDetailResponse=GetServices(servicename,serviceID);
			if(FleetDetailResponse.getStatusCode()==200) {
			
				testPassed("Fleet detail Response is : "+FleetDetailResponse.getBody().asString());
			}else {
				testFailed("Fleet is not present in the response message is : "+FleetDetailResponse.getBody().asString());
			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}return FleetDetailResponse;
	}

	public void ClearFilter() {
		try {
			int fleetListWithFilter= elementCount(FLEET_SEARCH_ITEM_LT);
			waitElementVisibleClick(FLEET_SEARCH_CLEAR_BT, 300);
			int fleetListWithoutFilter = elementCount(FLEET_SEARCH_ITEM_LT);
			if(fleetListWithFilter<fleetListWithoutFilter) {
				testPassed("Clear filter is working and the count before clearing is : "+fleetListWithFilter+" and after clear is : "+fleetListWithoutFilter);
			}else {
				testFailed("Clear filter is not working and the count before clearing is : "+fleetListWithFilter+" and after clear is : "+fleetListWithoutFilter);
			}}catch(Exception e) {
				testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
			}
	}

	public void verifyFleetDetails(String createFleetNameWithAPI) {
		try {
			waitElementVisibleClick(FLEET_FIRSTFLEETRESULT_TXT, 300);
			sleep(20000);

			compareText(elementGetText(FLEET_NAME_TXT),createFleetNameWithAPI);
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
	}
	public void VerifyFleetCreatedFromUICanBeDeletedFromAPI(String FleetId) {
		Response FleetResponse = null;
		try {
			FleetResponse = DeleteServices("Fleet",FleetId);
			if(FleetResponse.getStatusCode()==204) {
				testPassed("The created Fleet"+ FleetId +" is deleted through API");
				info("----------------TEST PASS-------------------");
			}
			else {
				testFailed("The created Fleet"+ FleetId +" is not deleted through API");
			}
		}catch(Exception e) {
			testFailed("An excpetion occured and the error message is:"+e.getMessage());
		}
	}

	public void verifyToastermessage(By ByType,JSONObject obj, String Jsondata,long timeoutSeconds) {
		try {

			String toasterMessage = waitElementVisibleGetTextForToaster(ByType,timeoutSeconds);
			compareText(obj.getAsString(Jsondata),toasterMessage);
		}catch (Exception e) {
			TestLogger.appInfo(e.getMessage());
		}
	}

	public void SearchForFleet(String FleetName) {
		clikcOnFleetTab();
		sleep(30000);
		elementClick(FLEET_FILTEREXPANDROW_BT);
		elementSendKeys(FLEET_FILTERSEARCH_EB, FleetName);
		sleep(3000);
		//		String elementtext=elementGetText(FLEET_SEARCHED_DISPLAY_AREA_TXT);
		compareText(elementGetText(FLEET_NORESULTFOUND_TXT),"No results found");
	}

	public Response getFleetDetails(String servicename,String createdFleetID){
		Response FleetDetailResponse=null;
		try{
			FleetDetailResponse=GetServices(servicename);
				}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
		return FleetDetailResponse;}
	public Response validateGetFleetDetails(Response FleetDetailResponse,String createdFleetID){
		
		try{
			if(FleetDetailResponse.getStatusCode()==200 && FleetDetailResponse.jsonPath().getJsonObject("id").toString().contains(createdFleetID)) {

				testPassed("Fleet detail Response is : "+FleetDetailResponse.getBody().asString());
			}else {
				testFailed("Fleet is not present in the response message is : "+FleetDetailResponse.getBody().asString());

			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
		return FleetDetailResponse;}


	public void validateAssetDetailsForAFleet(Response fleetDetails,String assetID) {
		try {
			if(fleetDetails.jsonPath().getJsonObject("assetsList").toString().contains(assetID)) {
				testPassed("Fleet detail Response is : "+fleetDetails.getBody().asString());
			}else {
				testFailed("Asset is not present in the response message is : "+fleetDetails.getBody().asString());
			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and assetdetails and  message is : "+e.getMessage());
		}
	}

	public Response getFleetDetailsForAClient(String servicename) {
		Response FleetDetailResponse=null;
		try{
			FleetDetailResponse=GetServices(servicename);
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
		return FleetDetailResponse;
	}

	public Response validateGetFleetDetailsForAClient(Response FleetDetailResponse) {
			try{
					if(FleetDetailResponse.getStatusCode()==200){

				testPassed("Fleet detail Response is : "+FleetDetailResponse.getBody().asString());
			}else {
				testFailed("Fleet is not present in the response message is : "+FleetDetailResponse.getBody().asString());
			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
		return FleetDetailResponse;
	}

	public void assignAssetToFleet(String servicename,String assetID, String fleetID,String Payload) {
		JSONObject UpdateFleetJson = null;
		Response updateFleettResponse = null;
		String UpdateFleetname=null;
		String UpdatedFleetId=null;
		String payloaddata=null;
		try {
			UpdateFleetJson = JsonReader.getJsonObject(Payload);
			payloaddata=UpdateFleetJson.toString();
			payloaddata=payloaddata.replace("1221", fleetID);
			payloaddata=payloaddata.replace("9c1a5824-c643-4a7a-9e75-cef4670aab59", assetID);
			JSONParser parser = new JSONParser();
			UpdateFleetJson=(JSONObject)parser.parse(payloaddata);

			updateFleettResponse=PutServices(servicename, UpdateFleetJson);
			if(updateFleettResponse.getStatusCode()==204) {
				UpdatedFleetId = updateFleettResponse.getBody().asString();	
				testPassed("The created Fleet id is : "+UpdatedFleetId);
			}
			else {
				testFailed("Fleet is not created and the response status code is : "+updateFleettResponse.getStatusCode()+" and the response is : "+updateFleettResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());
		}
	}

	public void getFleetAssetDetailsForAClient(String servicename, String paramValue, String fleetID,String assetID) {
		Response FleetDetailResponse=null;
		try{
			FleetDetailResponse=GetServices(servicename, paramValue);

			if(FleetDetailResponse.getStatusCode()==200){
				if(FleetDetailResponse.jsonPath().getJsonObject("id").toString().contains(fleetID)&&FleetDetailResponse.jsonPath().getJsonObject("assetsList").toString().contains(assetID))
					testPassed("Fleet detail Response is : "+FleetDetailResponse.getBody().asString());
			}else {
				testFailed("Fleet is not present in the response message is : "+FleetDetailResponse.getBody().asString());

			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
	}



	public String validateFleetAssetInDB(String fleetID,String AssetID, String queryDetails) {
		String CreatedFleetID=null;
		String[] fleetDetails=null;
		try {String SelectQuery = jsonObject.getAsString(queryDetails);	
		SelectQuery = SelectQuery.replaceFirst("tempValue", fleetID);
		SelectQueryResult = databaseutility.getSelectQueryResult(databaseutility.getConnection(EnvironmentManager.getSqlServer(),jsonObject.getAsString("SQLSERVERDATABASEClient72d")),SelectQuery);
		info("Values from Database is : "+SelectQueryResult.toString()+" and the FleetID details is :"+AssetID);
		if(DatabaseUtility.selectQueryComparision(SelectQueryResult,AssetID)){

			testPassed("Database contains the Fleet name : "+AssetID );
		}else {
			testFailed("Database does not contains the FleetName : "+AssetID);
		}


		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload: CreateAsset and the message is :"+e.getMessage());
		}
		return CreatedFleetID;
	}

	public Response  createFleetWithAPIWithResponse(String servicename, String Payload) {

		JSONObject CreateFleetJson = null;
		Response FleettResponse = null;
		try {
			CreateFleetJson = JsonReader.getJsonObject(Payload);			
			CreateFleetJson.put("name",CreateFleetJson.get("name").toString()+getRandomNumber(1,99999));
			CreateFleetJson.put("description",CreateFleetJson.get("description").toString() +  getRandomNumber());	
			FleetName=CreateFleetJson.get("name").toString();
			sleep(3000);
			FleettResponse = CreateServices(servicename, CreateFleetJson);

		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());

		}
		return FleettResponse;		

	}

	public String validateresponse(Response FleettResponse ) {
		String FleetId =null;
		try {
			if( FleettResponse!=null && FleettResponse.getStatusCode()==200) {
				FleetId = FleettResponse.getBody().asString();	
				testPassed("The created Fleet id is : "+FleetId);
			}
			else {
				testFailed("Fleet is not created and the response status code is : "+FleettResponse.getStatusCode()+" and the response is : "+FleettResponse.getBody().asString());
			}
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());

		}
		return FleetId;
	}

	public void ValidateDeletedFleetAssetDetailsForAClient(String servicename, String paramValue, String fleetID,String assetID) {
		Response FleetDetailResponse=null;
		try{
			FleetDetailResponse=GetServices(servicename, paramValue);

			if(FleetDetailResponse.getStatusCode()==200){
				if(!(FleetDetailResponse.jsonPath().getJsonObject("id").toString().contains(fleetID)&&FleetDetailResponse.jsonPath().getJsonObject("assetsList").toString().contains(assetID)))
					testPassed("Fleet detail Response is : "+FleetDetailResponse.getBody().asString());
			}else {
				testFailed("Fleet is not present in the response message is : "+FleetDetailResponse.getBody().asString());

			}	
		}catch(Exception e) {
			testFailed("An Exception occured while getting Fleet and the message is : "+e.getMessage());
		}
	}
	
	public String updatefleetDetails(String servicename,String createdFleetID,String fleetdetails) {
		Response updateFleettResponse=null;
		String fleetName=null;
		JSONObject UpdateFleetJson=null;
		try {	JSONParser parser = new JSONParser();
		UpdateFleetJson=(JSONObject)parser.parse(fleetdetails);
		
			UpdateFleetJson.put("name",UpdateFleetJson.get("name").toString()+getRandomNumber(1,99));
			UpdateFleetJson.put("id",createdFleetID);
			fleetName=UpdateFleetJson.get("name").toString();
			updateFleettResponse=PutServices(servicename, UpdateFleetJson,createdFleetID);
		}catch(Exception e) {
			testFailed("An exception has occured while Creating Fleet payload and the message is :"+e.getMessage());

		}
		return fleetName;
	}
}
