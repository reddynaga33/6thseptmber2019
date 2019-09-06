package cucumberIntegrationTests.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import zf.regression.pages.AssetPage;
import zf.regression.pages.FleetPage;

public class FleetManagementService extends FleetPage {

	AssetPage assetPageObject=new AssetPage();
	String assetID =null;
	String createFleetNameWithAPI =null;
	 Response createFleetResponse=null;
	String createdFleetID=null;
	Response fleetDetailsForAClient =null;
	Response fleetDetails =null;
	String updatefleetName=null;
	@Given("^Create An Asset$")
	public void create_An_Asset() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
		assetID = assetPageObject.createAssetstWithRestAPI("asset","CreateAssetWithRestAPI");
	}

	@When("^Send the PUT request to add an Asset To Fleet$")
	public void send_the_PUT_request_to_add_an_Asset_To_Fleet() throws Throwable {
		 createFleetResponse = createFleetWithAPIWithResponse("CreateFleet","C63214CreateNewfleetForAClient");
	}

	@Then("^verify the response code for put$")
	public void verify_the_response_code_for_put() throws Throwable {
		createdFleetID=validateresponse(createFleetResponse);
	}
	@Then("^Verify the assetID is added to the Fleet by sending a get request$")
	public void verify_the_assetID_is_added_to_the_Fleet_by_sending_a_get_request() throws Throwable {
		assignAssetToFleet("AssignAssetToFleet",assetID,createdFleetID,"C63217AddAssetTOAFleetForAClient");
		getFleetAssetDetailsForAClient("VerifyAssignedAsset","true",createdFleetID,assetID);
		info("--------         TEST PASS         -----------");
	}

	@Then("^Validate the data from database$")
	public void validate_the_data_from_database() throws Throwable {
		validateFleetAssetInDB(createdFleetID,assetID,"validateFleetAssetInDB");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createdFleetID);
	
	}

	
	@When("^Send the get request to get fleet details$")
	public void send_the_get_request_to_get_fleet_details() throws Throwable {
		 fleetDetailsForAClient = getFleetDetailsForAClient("Fleet");
		
	}

	@Then("^Verify the response code for get request$")
	public void verify_the_response_code_for_get_request() throws Throwable {
		validateGetFleetDetailsForAClient(fleetDetailsForAClient);
		info("--------         TEST PASS         -----------");
	}

	@When("^Send a Post request to create a fleet and verify the response$")
	public void send_a_Post_request_to_create_a_fleet_and_verify_the_response() throws Throwable {
		String createFleetNameWithAPI = createFleetWithAPI("CreateFleet","C63214CreateNewfleetForAClient");
		 ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
	}

	@When("^verify the fleet details with a get request$")
	public void verify_the_fleet_details_with_a_get_request() throws Throwable {
		Response fleetDetails = getFleetDetails("Fleet",createdFleetID);
		validateGetFleetDetails(fleetDetails, createdFleetID);
	}

	@Then("^verify fleet is present in the schema$")
	public void verify_fleet_is_present_in_the_schema() throws Throwable {
		String createdFleetID = ValidateCreatedFleetInDB(createFleetNameWithAPI,"SelectFleetDetailsWithName");
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createdFleetID);
	}
	
	
	@Then("^verify the response code$")
	public void verify_the_response_code() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^Send a delete request to delete the fleet$")
	public void send_a_delete_request_to_delete_the_fleet() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		VerifyFleetCreatedFromUICanBeDeletedFromAPI(createdFleetID);
	}

	@Then("^Validate the deleted fleet is not present in the list$")
	public void validate_the_deleted_fleet_is_not_present_in_the_list() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ValidateDeletedFleetAssetDetailsForAClient("VerifyAssignedAsset","true",createdFleetID,assetID);
		assetPageObject.deleteAssetInDataBase(assetID);
	}

	@When("^get the FleetDetails and associated asset$")
	public void get_the_FleetDetails_and_associated_asset() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	fleetDetails = getCreatedFleetDetails("Fleet",createdFleetID);
		validateAssetDetailsForAFleet(fleetDetails,assetID);
				assetPageObject.deleteAssetInDataBase(assetID);
	}

	@Then("^Validate the asset details$")
	public void validate_the_asset_details() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		validateAssetDetailsForAFleet(fleetDetails,assetID);
		assetPageObject.deleteAssetInDataBase(assetID);
	}

	@When("^Update the asset details for the fleet$")
	public void update_the_asset_details_for_the_fleet() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		updatefleetName= updatefleetDetails("UpdateFleet",createdFleetID,fleetDetails.asString());
		ValidateCreatedFleetInDB(updatefleetName,"SelectFleetDetailsWithName");
		assetPageObject.deleteAssetInDataBase(assetID);
	}

	@Then("^validate the updated asset details$")
	public void validate_the_updated_asset_details() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ValidateCreatedFleetInDB(updatefleetName,"SelectFleetDetailsWithName");
		assetPageObject.deleteAssetInDataBase(assetID);
	}
}
