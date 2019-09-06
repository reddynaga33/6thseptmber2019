package cucumberIntegrationTests.stepDefinitions;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.ElementManager;

public class LoginPage extends ElementManager{
	
	
	
	@Given("^User has slideshare \"([^\"]*)\" app$")
	public void user_has_slideshare_app(String arg1) {
		
		testPassed("user_has_slideshare_app");
	}

	@Given("^user has \"([^\"]*)\" username and password$")
	public void user_has_username_and_password(String arg1) {
		testPassed("user_has_username_and_password");
	}

	@When("^user enters credentials$")
	public void user_enters_credentials() {
		info("user_enters_credentials");
	}

	@When("^taps on \"([^\"]*)\" button$")
	public void taps_on_button(String arg1) {
		info("taps_on_button");
	}

	@Then("^\"([^\"]*)\" button should be visible$")
	public void button_should_be_visible(String arg1) {
		info("button_should_be_visible");
	}

	@Then("^user should be able to scroll$")
	public void user_should_be_able_to_scroll() {
		info("user_should_be_able_to_scroll");
	}

	@Then("^long press the search icon$")
	public void long_press_the_search_icon() {
		testFailed("long_press_the_search_icon");
	}
	
	

}
