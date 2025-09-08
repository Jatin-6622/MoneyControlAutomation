package stepdefinitions;

import org.testng.Assert;

import base.BaseSetup;
import io.cucumber.java.en.*;
import pages.LoginPage;

public class LoginSteps extends BaseSetup{
	LoginPage login;
	
	@Given("the user is on the Money Control landing page")
	public void the_user_is_on_the_money_control_landing_page() {
	    login=new LoginPage(getDriver());
	}
	@When("the user hovers over {string} and clicks on Login")
	public void the_user_hovers_over_and_clicks_on_login(String string) {
	   login.hover_On_HelloLogin();
	   login.click_on_LogIn();
	}
	@When("the user enters Mobile Number {string} and manually clicks Get OTP")
	public void the_user_enters_mobile_number_and_clicks_get_otp(String string) {
			login.enter_MobileorEmail(string);
	    
	}
	@When("the user enters Email Address {string} and manually clicks Get OTP")
	public void the_user_enters_email_address_and_clicks_get_otp(String string) {
			login.enter_MobileorEmail(string);
	}
	@When("the user waits and enters OTP manually")
	public void the_user_waits_and_enters_otp_manually() throws InterruptedException {
		Thread.sleep(45000);
	}

	@When("on entering OTP it auto connects user to the main page.")
	public void the_user_clicks_proceed() {
		System.out.println("This method will be automatically handled by web page");
	}

	@Then("the user should see his name appended with {string} on the top-right corner")
	public void the_user_should_see_his_name_appended_with_on_the_top_right_corner(String string) {
	   boolean status=login.check_UserDisplay(string);
	   Assert.assertTrue(status);
	}
	
	 @And("the user enters Email Address {string} and manually try to Get OTP")
	    public void the_user_enters_email_address_and_clicks_on_get_otp(String email) {
	        login.enter_MobileorEmail(email);
	    }

	 @And("the user enters Mobile Number {string} and manually try to Get OTP")
	    public void the_user_enters_mobile_number_and_clicks_on_get_otp(String number) {
	        login.enter_MobileorEmail(number);
	    }

	 @Then("the user should see an error message {string}")
	    public void the_user_should_see_an_error_message(String expectedMsg) {
	        String actualMsg = login.get_ErrorMsg();
	        Assert.assertEquals(actualMsg, expectedMsg,
	                "Error message did not match. Expected: " + expectedMsg + " but got: " + actualMsg);
	    }
}
