@SMSMC

Feature: Verify SMSMC functionality

Scenario:Verify Service Descriptor is created
	When Verify User Is Able To Send The Put Request
	Then Notice response code 202
	Then Notice the result under the preview section for Saga extract	
	When Send the get request to check the service descriptor got created
	Then Notice 200 response code
	Then Notice the result under the preview section
	
Scenario:Verify user is able to get the details of single client
	When Verify user is able to get the details of single client
	Then Notice 200 response code
	Then Notice the result under the preview section	
	
Scenario:Verify User Is Able To Get The Services
	When Verify User Is Able To Send The Get Request
	Then Notice 200 response code
	Then Verify The Result Under Preview Section
	
