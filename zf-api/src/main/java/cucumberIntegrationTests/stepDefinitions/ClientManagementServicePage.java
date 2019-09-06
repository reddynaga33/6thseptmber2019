package cucumberIntegrationTests.stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import zf.ClientManagementServiceSuite.pages.MiscPage;

public class ClientManagementServicePage extends MiscPage{
	Response responce ;
	String ClientID=null;
	
	@When("^Send get request to get the client services API$")
	public void send_get_request_to_get_the_client_services_API() throws Throwable {
	responce = VerifyUserIsAbleToCreateClientWithSameClientIdViaCMS();
	}

	@Then("^Notice (\\d+) response code and the client list is displayed under the preview section$")
	public void notice_response_code_and_the_client_list_is_displayed_under_the_preview_section(int arg1) throws Throwable {
		ValidateResponseCode200(responce);
	}
	
	@When("^Send POST request to the client services API$")
	public void send_POST_request_to_the_client_services_API() throws Throwable {
		responce = VerifyUserIsAbleToCreateClient();
	}
	@Then("^Notice (\\d+) response code and the result under the preview section$")
	public void notice_response_code_and_the_result_under_the_preview_section(int arg1) throws Throwable {
		ValidateResponseCode200(responce);	
		ClientID = responce.getBody().asString();
		info("The Create client response body is :"+responce.getBody().asString());
	}
	@When("^Send the get request for client$")
	public void send_the_get_request_for_client() throws Throwable {
		responce=VerifyUserIsAbleToVerifyGerSingleClientDetails(ClientID);
	}

	@Then("^Notice (\\d+) response code and result under the preview section$")
	public void notice_response_code_and_result_under_the_preview_section(int arg1) throws Throwable {
		ValidateResponseCode200(responce);
	}

	@When("^Go to openshift and open cms, sms and smsmc services\\.$")
	public void go_to_openshift_and_open_cms_sms_and_smsmc_services() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^Notcie that in all the services there is no string or exception throwing this exception$")
	public void notcie_that_in_all_the_services_there_is_no_string_or_exception_throwing_this_exception() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}
}
