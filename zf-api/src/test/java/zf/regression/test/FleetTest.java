package zf.regression.test;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.DriverManager;
import framework.EnvironmentManager;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.pages.MicrosoftLoginPage;
import zf.regression.pages.AssetPage;
import zf.regression.pages.FleetPage;

public class FleetTest extends FleetPage{

	String FleetId=null;
	String FleetName=null;
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	AssetPage assetPageObject=new AssetPage();

	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
	}


@Test
	public void C64401CreateNewFleetWithVehicle() {
		String NewlyCreatedFleetName = addNewFleet();
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(NewlyCreatedFleetName,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64436CreateNewFleetWithoutVehicle() {
		String createNewFleetWithoutVehicle = createNewFleetWithoutVehicle();
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createNewFleetWithoutVehicle,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64437CreateNewFleetWithMandatoryData() {
		String createNewFleetWithMandatoryData = createNewFleetWithMandatoryData();
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createNewFleetWithMandatoryData,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64439CreateNewFleetWithAllData() {
		String createNewFleetWithAllData = createNewFleetWithAllData();
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createNewFleetWithAllData,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);

	}

@Test
	public void C64440getCreatedFleetDetails()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		String createFleetIDWithAPI = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		getCreatedFleetDetails("Fleet",createFleetIDWithAPI);
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createFleetIDWithAPI);
	}

@Test
	public void C64441SearchFleetDetails()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		validateFleetInUI(createFleetNameWithAPI);
		deleteFleet(createFleetNameWithAPI);
		validateFleetIsNotInUI(createFleetNameWithAPI);

	}

@Test
	public void C64442ClearSearchFleetDetails()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		validateFleetInUI(createFleetNameWithAPI);
		ClearFilter();
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64444verifySearchFleetDetails()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		validateFleetInUI(createFleetNameWithAPI);
		verifyFleetDetails(createFleetNameWithAPI);
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64445EditFleetDetails()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		String editNewFleet = editNewFleet(createFleetNameWithAPI);
		validateFleetInUI(editNewFleet);
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(editNewFleet,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);

	}


@Test
	public void C64451EditFleetDetails()  {
		String createFleetIDWithAPI = createFleetWithAPIResponse("CreateFleet","CreateFleetPayload");
		String updateFleetWithAPI = UpdateFleetWithAPI("UpdateFleet","UpdateFleetPayload",createFleetIDWithAPI);
		getCreatedFleetDetails("Fleet",createFleetIDWithAPI);
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createFleetIDWithAPI,"SelectFleetDetailsWithID");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createFleetIDWithAPI);
	}

@Test
	public void C64452VerifyUserCanDeleteTheFleet() {

		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		VerifyUserCanDeleteTheFleet(createFleetNameWithAPI);
		DriverManager.closeAllBrowser();
		DriverManager.getDriver(EnvironmentManager.getBrowserName(),EnvironmentManager.getIRISUrl());
		microsoftlogin.microsoftLogin(EnvironmentManager.getAdminUserName(),EnvironmentManager.getAdminPassword());
		SearchForFleet(createFleetNameWithAPI);
		DriverManager.closeAllBrowser();	
		}

@Test
	public void C64456VerifyTheDeletedVehicleByUsingfleetsAPI() {
		String createFleetIDWithAPI = createFleetWithAPIResponse("CreateFleet","CreateFleetPayload");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createFleetIDWithAPI);
		VerifyTheDeletedVehicleByUsingfleetsAPI(createFleetIDWithAPI);
		DriverManager.closeAllBrowser();}

@Test
	public void C64458createFleetInAPICheckInUI()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		clikcOnFleetTab();
		validateFleetInUI(createFleetNameWithAPI);
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64459UpdateFleetInAPICheckInUI()  {
		String createFleetIDWithAPI = createFleetWithAPIResponse("CreateFleet","CreateFleetPayload");
		String updateFleetNameWithAPI = UpdateFleetWithAPI("UpdateFleet","UpdateFleetPayload",createFleetIDWithAPI);
		clikcOnFleetTab();
		validateFleetInUI(updateFleetNameWithAPI);
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(updateFleetNameWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64460createInUIAndUpdateFleetInAPI()  {
		String createFleetNameInUI=addNewFleet();
		String	createFleetIDWithAPI = ValidateCreatedFleetInDB(createFleetNameInUI,"SelectFleetDetailsWithName");
		String updateFleetWithAPI = UpdateFleetWithAPI("UpdateFleet","UpdateFleetPayload",createFleetIDWithAPI);
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(updateFleetWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64461createInAPIAndDeleteFleetInUI()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		deleteFleet(createFleetNameWithAPI);
	}


@Test
	public void C64464VerifyFleetCreatedFromUICanBeDeletedFromAPI() {
		FleetName=addNewFleet();
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(FleetName,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);

	}

@Test
	public void C64465VerifyCreatedFleetIntoDB() {
		FleetName=addNewFleet();
		//		VerifyUserCanDeleteTheFleet(FleetName);
		FleetId=ValidateCreatedFleetInDB(FleetName, "SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(FleetId);
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		String validateCreatedFleetInDB = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(validateCreatedFleetInDB);
	}

@Test
	public void C64467UpdateFleetInUIAndAPI()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		String editNewFleet = editNewFleet(createFleetNameWithAPI);
		String createFleetIDWithAPI = ValidateCreatedFleetInDB(editNewFleet,"SelectFleetDetailsWithName");
		String updateFleetWithAPI = UpdateFleetWithAPI("UpdateFleet","UpdateFleetPayload",createFleetIDWithAPI);
		ValidateCreatedFleetInDB(updateFleetWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createFleetIDWithAPI);
	}

@Test
	public void C64518DeleteFleetInUIAndUpdateInAPI()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","CreateFleetPayload");
		String createFleetIDWithAPI = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		deleteFleet(createFleetNameWithAPI);
		UpdateWithAPIForDeletedFleet("UpdateFleet","UpdateFleetPayload",createFleetIDWithAPI);
	}


	@Test
	public void C63214CreateNewfleetForAClient()  {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","C63214CreateNewfleetForAClient");
		String createdFleetID = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		Response fleetDetails = getFleetDetails("Fleet",createdFleetID);
		validateGetFleetDetails(fleetDetails, createdFleetID);
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createdFleetID);
	}

	@Test
	public void C63210GetfleetDetailsForAClient()  {
		Response fleetDetailsForAClient = getFleetDetailsForAClient("Fleet");
		validateGetFleetDetailsForAClient(fleetDetailsForAClient);
	}

	@Test
	public void C63217AddAssetTOAFleetForAClient()  {
		Response createFleetNameWithAPI = createFleetWithAPIWithResponse("CreateFleet","C63214CreateNewfleetForAClient");
		String createdFleetID = validateresponse(createFleetNameWithAPI);
		String assetID = assetPageObject.createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		assignAssetToFleet("AssignAssetToFleet",assetID,createdFleetID,"C63217AddAssetTOAFleetForAClient");
		getFleetAssetDetailsForAClient("VerifyAssignedAsset","true",createdFleetID,assetID);
		validateFleetAssetInDB(createdFleetID,assetID,"validateFleetAssetInDB");
				assetPageObject.deleteAssetInDataBase(assetID);
	}

	
	@Test
	public void C63211GetfleetDetailsWithAssetIDForAClient()  {
		C63217AddAssetTOAFleetForAClient();
	}
	

@Test
	public void C63220ValidateDeletedFleetDetailsWithAssetIDForAClient()  {
		Response createFleetNameWithAPI = createFleetWithAPIWithResponse("CreateFleet","C63214CreateNewfleetForAClient");
		String createdFleetID = validateresponse(createFleetNameWithAPI);
		String assetID = assetPageObject.createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		assignAssetToFleet("AssignAssetToFleet",assetID,createdFleetID,"C63217AddAssetTOAFleetForAClient");
		getFleetAssetDetailsForAClient("VerifyAssignedAsset","true",createdFleetID,assetID);
		validateFleetAssetInDB(createdFleetID,assetID,"validateFleetAssetInDB");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createdFleetID);
			ValidateDeletedFleetAssetDetailsForAClient("VerifyAssignedAsset","true",createdFleetID,assetID);
			assetPageObject.deleteAssetInDataBase(assetID);
	}
	
@Test
	public void C63221GetFleetAndAssociatedAssetForAClient()  {
		Response createFleetNameWithAPI = createFleetWithAPIWithResponse("CreateFleet","C63214CreateNewfleetForAClient");
		String createdFleetID = validateresponse(createFleetNameWithAPI);
		String assetID = assetPageObject.createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		assignAssetToFleet("AssignAssetToFleet",assetID,createdFleetID,"C63217AddAssetTOAFleetForAClient");
		Response fleetDetails = getCreatedFleetDetails("Fleet",createdFleetID);
		validateAssetDetailsForAFleet(fleetDetails,assetID);
				assetPageObject.deleteAssetInDataBase(assetID);
	}
	
	
@Test
	public void C63222UpdateAssetForAFleetClient()  {
		Response createFleetNameWithAPI = createFleetWithAPIWithResponse("CreateFleet","C63214CreateNewfleetForAClient");
		String createdFleetID = validateresponse(createFleetNameWithAPI);
		String assetID = assetPageObject.createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
		assignAssetToFleet("AssignAssetToFleet",assetID,createdFleetID,"C63217AddAssetTOAFleetForAClient");
		Response fleetDetails = getCreatedFleetDetails("Fleet",createdFleetID);
		String updatefleetName= updatefleetDetails("UpdateFleet",createdFleetID,fleetDetails.asString());
		ValidateCreatedFleetInDB(updatefleetName,"SelectFleetDetailsWithName");
		assetPageObject.deleteAssetInDataBase(assetID);
	}
	
@Test
public void C63223GetAssetForAFleetClient()  {
	Response createFleetNameWithAPI = createFleetWithAPIWithResponse("CreateFleet","C63214CreateNewfleetForAClient");
	String createdFleetID = validateresponse(createFleetNameWithAPI);
	String assetID = assetPageObject.createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
	assignAssetToFleet("AssignAssetToFleet",assetID,createdFleetID,"C63217AddAssetTOAFleetForAClient");
	Response fleetDetails = getCreatedFleetDetails("AssociatedAssetForFleet",createdFleetID);
	validateFleetAssetInDB(createdFleetID,assetID,"validateFleetAssetInDB");
	assetPageObject.deleteAssetInDataBase(assetID);
}
	
		
	@AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
		DriverManager.closeAllBrowser();
	}
}