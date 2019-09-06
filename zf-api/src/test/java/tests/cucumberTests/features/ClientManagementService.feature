@CMS

Feature: Verify CMS functionality
	
Scenario:Verify user is able to create client with same client id via CMS
	When Send get request to get the client services API
	Then Notice 200 response code and the client list is displayed under the preview section
	When Send POST request to the client services API
	Then Notice 200 response code and the result under the preview section
	When Send the get request for client
	Then Notice 200 response code and result under the preview section
	When Go to openshift and open cms, sms and smsmc services.
	Then Notcie that in all the services there is no string or exception throwing this exception	