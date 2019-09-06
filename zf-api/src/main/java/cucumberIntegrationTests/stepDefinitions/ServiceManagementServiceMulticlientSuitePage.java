package cucumberIntegrationTests.stepDefinitions;

import cucumber.api.java.en.*;
import io.restassured.response.Response;
import zf.ServiceManagementServiceMulticlientSuite.pages.IServiceDesciptorServiceNewPage;

public class ServiceManagementServiceMulticlientSuitePage extends IServiceDesciptorServiceNewPage {
	Response response ;
	String getSaga=null;; 

	@When("^Verify user is able to get the details of single client$")
	public void verify_user_is_able_to_get_the_details_of_single_client() throws Throwable {
		response= VerifyUserIsAbleToGetTheDetailsOfSingleClient("testsd");
	}

	@Then("^Notice (\\d+) response code$")
	public void notice_response_code(int arg) throws Throwable {
		ValidateResponseCode200(response);
	}

	@Then("^Notice the result under the preview section$")
	public void notice_the_result_under_the_preview_section() throws Throwable {
		ResponsePerview(response,"testsd");
	}

	
	@When("^Verify User Is Able To Send The Get Request$")
	public void verify_User_Is_Able_To_Send_The_Get_Request() throws Throwable {
		response = VerifyUserIsAbleToGetTheServices();
	}

	@Then("^Verify The Result Under Preview Section$")
	public void verify_The_Result_Under_Preview_Section() throws Throwable {
		ValidateResponseCode200(response);
		ResponsePerview(response,"testsd");
	}
	
	@When("^Verify User Is Able To Send The Put Request$")
	public void verify_User_Is_Able_To_Send_The_Put_Request() throws Throwable {
		response=VerifyServiceDescriptorIsCreated("testsd");
	}

	@When("^Send the get request to check the service descriptor got created$")
	public void send_the_get_request_to_check_the_service_descriptor_got_created() throws Throwable {
		
		 response = VerifyServiceDescripterCreation(getSaga);
	}
	
	@Then("^Notice the result under the preview section for Saga extract$")
	public void notice_the_result_under_the_preview_section_for_Saga_extract() throws Throwable {
		getSaga= GetSagaResponse(response);

	}
	@Then("^Notice response code (\\d+)$")
	public void notice_response_code(long arg) throws Throwable {
		ValidateResponseCode202(response);

	}
}
