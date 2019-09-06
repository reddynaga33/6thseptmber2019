
@FleetManagmentService
Feature: Verify Fleet Management functionality


@AddAssetToFleetForAClient
  Scenario: C63217Verify user is able to add the asset to a fleet of particular client
    Given Create An Asset
    When Send the PUT request to add an Asset To Fleet
    Then verify the response code for put
    And Verify the assetID is added to the Fleet by sending a get request
    And Validate the data from database
    
@GetDetailsOfFleet
    Scenario:C63210Verify user is able to get the fleet of particular client
    When Send the get request to get fleet details
    Then Verify the response code for get request
    
   @CreateNewFleet
    Scenario:C63214Verify user is able to create new fleet for a particular client
    When Send a Post request to create a fleet and verify the response
    And verify the fleet details with a get request
    Then verify fleet is present in the schema