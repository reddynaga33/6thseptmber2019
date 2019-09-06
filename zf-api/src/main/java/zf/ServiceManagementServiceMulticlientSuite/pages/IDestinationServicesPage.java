package zf.ServiceManagementServiceMulticlientSuite.pages;

import framework.RestApiUtility;
import io.restassured.response.Response;

public class IDestinationServicesPage extends RestApiUtility {
	//v 26th june C62231	Verify user is able to get the Destination with enum valuse as xxx 		
	public void VerifyUserAbleToGetDestinationEnum(String servicename)
	{
		Response getDestinationAPIworkingResponse = GetServices(servicename,"xxx");
		if(getDestinationAPIworkingResponse.getStatusCode()==400) {
			testPassed("Response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
			testFailed("Get Property detail is : "+getDestinationAPIworkingResponse);

		}
	}	
	//v 26th june C62229 Verify user is able to get the Event_hub Destination	
	public void VerifyUserAbleToGetEventHubDestination(String servicename)
	{
		Response getDestinationAPIworkingResponse = GetServices(servicename,"event_hub");
		if(getDestinationAPIworkingResponse.getStatusCode()==200) {
			testPassed("Response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
			testFailed("Get Property detail is : "+getDestinationAPIworkingResponse);

		}
	}
	//v 26th june C62228-Verify user is able to get the topic destination
	public void VerifyUserAbleToGetTopicDestination(String servicename)
	{
		Response getDestinationAPIworkingResponse = GetServices(servicename,"topic");
		if(getDestinationAPIworkingResponse.getStatusCode()==200) {
			testPassed("Response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
			testFailed("Get Property detail is : "+getDestinationAPIworkingResponse);

		}
	}
	//v 26th june C62133 Destinations API isn't working	
	public void getDestinationAPIworking(String servicename)
	{
		Response getDestinationAPIworkingResponse = GetServices("getDestination");
		if(getDestinationAPIworkingResponse.getStatusCode()==200) {
			testPassed("Response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
		}
		else {
			info("Get Property response detail is : "+getDestinationAPIworkingResponse.getBody().asString());
			testFailed("Get Property detail is : "+getDestinationAPIworkingResponse);

		}
	}
	
	public Response getQueueDestinations(String servicename) 
	{
		Response GetQueueDestinationsResponse = GetServices(servicename);
		if(GetQueueDestinationsResponse.getStatusCode()==200) {
			testPassed("Get Queue Destinations detail is : "+GetQueueDestinationsResponse.getBody().asString());
		}
		else {
			info("GetQueue Destinations response status code is : "+GetQueueDestinationsResponse.getStatusCode());
			testFailed("Get Queue Destinations detail is : "+GetQueueDestinationsResponse.getBody().asString());
		}
		return GetQueueDestinationsResponse;
	}
	
	
	public Response getDestinations(String servicename) 
	{
		Response GetDestinationsResponse = GetServices(servicename);
		if(GetDestinationsResponse.getStatusCode()==200) {
			testPassed("Get Destinations detail is : "+GetDestinationsResponse.getBody().asString());
		}
		else {
			info("Get Destinations response status code is : "+GetDestinationsResponse.getStatusCode());
			testFailed("Get Destinations detail is : "+GetDestinationsResponse.getBody().asString());
		}
		return GetDestinationsResponse;
	}
}
