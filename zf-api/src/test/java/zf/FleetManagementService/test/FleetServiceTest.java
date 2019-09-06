package zf.FleetManagementService.test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import zf.pages.MicrosoftLoginPage;
import zf.regression.pages.AssetPage;
import zf.regression.pages.FleetPage;

public class FleetServiceTest  extends FleetPage{
	MicrosoftLoginPage microsoftlogin=new MicrosoftLoginPage();
	AssetPage assetPageObject=new AssetPage();
	Response response=null;
	String getSaga=null;
	
	
	@BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
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
	}

}
