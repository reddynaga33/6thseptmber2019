package zf.MulticlientSampleServiceSuite.pages;

import framework.EnvironmentManager;
import framework.JsonReader;
import framework.RestApiUtility;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import zf.platform.insprint.pages.CPF3049Page;

public class GlobalInfoServicePage extends RestApiUtility{
	CPF3049Page cpf3049page=new CPF3049Page();
	public void VerifyUserIsAbleToAssignAndUnassignTheClientsToTheSampleServicesUsingCUSRequest() {
		cpf3049page.globalInfoClients();

	}
	public void VerifyUuserIsAbleToDeleteTheGreetingsOfNonExistingClient(String servicename) {

		Response NonExistingClientDeleteGreeting=null;
		NonExistingClientDeleteGreeting=DeleteServices(servicename);
		if(NonExistingClientDeleteGreeting.getStatusCode()==404 &&
				NonExistingClientDeleteGreeting.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("The Response of Deleting of Greeting is:"+NonExistingClientDeleteGreeting.getBody().asString());
		}else {
			testFailed("The Response of Deleting of Greeting is:"+NonExistingClientDeleteGreeting.getBody().asString());
		}
	}
	public void VerifyUserAbleToDeleteTheGreetingsOfNoClient() {
		Response NoClientDeleteGreeting=null;
		NoClientDeleteGreeting=DeleteServices("NoClientDeleteGreeting");
		info("The Response Body is :"+NoClientDeleteGreeting.getBody().asString());
		if(NoClientDeleteGreeting.getStatusCode()==404) {
			testPassed("The Response of Deleting of Greeting is:"+NoClientDeleteGreeting.jsonPath().getJsonObject("message"));
		}else {
			testFailed("The Response of Deleting of Greeting is:"+NoClientDeleteGreeting.jsonPath().getJsonObject("message"));
		}
	}
	public void VerifyUserIsAbleToDeleteTheMessageSendToClientWithWrongClientAndCorrectGreetingId(String GreetingId) {
		
		Response wrongClientDeleteGreeting=null;
		wrongClientDeleteGreeting=DeleteServices("DeleteGreetingWithCorrectClientWrongGreeting",GreetingId);
		if(wrongClientDeleteGreeting.getStatusCode()==404 &&
				wrongClientDeleteGreeting.jsonPath().getJsonObject("error").equals("Client not found")) {
			testPassed("The Response of Deleting of Greeting is:"+wrongClientDeleteGreeting.getBody().asString());
		}else {
			testFailed("The Response of Deleting of Greeting is:"+wrongClientDeleteGreeting.getBody().asString());

		}

	}
	public String VerifyUserIsAbleToCreateGreeting() {
		String GreetingId=null;
		Response CreateGreeting=null;
		JSONObject ClienGreetinJson=null;
		ClienGreetinJson=JsonReader.getJsonObject("GreetingsLog");
		CreateGreeting=PostServices("CreateGreeting",ClienGreetinJson);
		GreetingId=CreateGreeting.jsonPath().getJsonObject("id");
		if(CreateGreeting.getStatusCode()==200) {
			info("The Greeting is succesfully created for client");
		}else {
			info("The Greeting is not created for client");

		}
		return GreetingId;
		
		
	}

}