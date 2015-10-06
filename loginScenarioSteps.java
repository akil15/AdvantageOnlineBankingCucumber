package com.aob.cucumber;

import junit.framework.Assert;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.RegExpProperty;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserDescription;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.Button;
import com.hp.lft.sdk.web.ButtonDescription;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;

import cucumber.api.java.en.Given;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class loginScenarioSteps {

    static Browser browser;
    static manageBrowser myManagedBrowser;
    BrowserDescription myDescription = new BrowserDescription();
    
    @Before
    public void setup() {
    	
    	try {
    		
			browser = myManagedBrowser.launchBrowserWithUrl("http://172.16.239.243:47001/advantage/", "CHROME");
			BrowserDescription myDescription = new BrowserDescription();
			myDescription.setOpenURL("http://172.16.239.243:47001/advantage");
		} catch (GeneralLeanFtException e) {

			e.printStackTrace();
		} catch (ReportException e) {

			e.printStackTrace();
		}   	
    }
    
    @After
    public void tearDown() {
 	
    	try {
			browser.close();
		} catch (GeneralLeanFtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
	@Given("^I am on the homepage on the Advantage Online Banking web application$")
	public void i_am_on_the_homepage_on_the_Advantage_Online_Banking_web_application() throws Throwable {
			
		Browser browser = BrowserFactory.attach(myDescription);
		
		try {
			
			Assert.assertEquals(browser.getOpenURL(), "http://172.16.239.243:47001/advantage");
			Reporter.reportEvent("Validate access to the AOB homepage", "Check that the browser was able to retrieve the provided URL", Status.Passed);
		} catch (GeneralLeanFtException e) {
			
	    	Reporter.reportEvent("Validate access to the AOB homepage", "Check that the browser was able to retrieve the provided URL", Status.Failed);
	    }
	    		
	}

	@When("^I enter my username and my password$")
	public void i_enter_my_username_and_my_password() throws Throwable {

			EditField usernameEditField = browser.describe(EditField.class, new EditFieldDescription.Builder()
			.type("text").tagName("INPUT").name("j_username").build());
			usernameEditField.setValue("jojo");
			
			EditField passwordEditField = browser.describe(EditField.class, new EditFieldDescription.Builder()
			.type("password").xpath("//INPUT[@id=\"password\"]").tagName("INPUT").name("j_password").build());
			passwordEditField.setValue("passw0RD");
	}

	@When("^I click on the login button$")
	public void i_click_on_the_login_button() throws Throwable {
		
		Button loginButton = browser.describe(Button.class, new ButtonDescription.Builder().buttonType("submit").tagName("INPUT").name("Login").build());
		loginButton.click();
	}

	@Then("^I should be able to see the Welcome Mylin Jojo message$")
	public void i_should_be_able_to_see_the_Welcome_Mylin_Jojo_message() throws ReportException, GeneralLeanFtException {
		
		WebElement welcomeMessage = browser.describe(WebElement.class, new WebElementDescription.Builder().className("welcome").tagName("DIV").innerText(new RegExpProperty("Welcome.*")).build());
		
		try {
			
			Assert.assertEquals(welcomeMessage.getInnerText(), "Welcome Milin Jojo!");
			Reporter.reportEvent("Succesful Login", "Verify that the user was able to log in using his credentials", Status.Passed);
		} catch (ReportException e) {
			
			e.printStackTrace();
			Reporter.reportEvent("Unsuccesfull Login", e.getMessage(), Status.Failed);
		} catch (GeneralLeanFtException e) {
			
			e.printStackTrace();
			Reporter.reportEvent("Unsuccesfull Login", e.getMessage(), Status.Warning);
		}
	}
}
