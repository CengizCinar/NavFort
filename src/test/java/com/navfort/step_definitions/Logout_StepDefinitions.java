package com.navfort.step_definitions;

import com.navfort.pages.BasePage;
import com.navfort.pages.LoginPage;
import com.navfort.utilities.BrowserUtils;
import com.navfort.utilities.ConfigurationReader;
import com.navfort.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


public class Logout_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();

    @When("{string} is logged in")
    public void is_logged_in(String userType) {
        Driver.getDriver().get(ConfigurationReader.getProperty("loginPage"));
        BrowserUtils.waitForClickablility(loginPage.usernameInput,5);
        if (userType.equalsIgnoreCase("driver")){
            loginPage.login("user21", "UserUser123");
        }else if (userType.equalsIgnoreCase("sales manager")) {
            loginPage.login("salesmanager101", "UserUser123");
        }else if (userType.equalsIgnoreCase("store manager")) {
            loginPage.login("storemanager54", "UserUser123");
        }
    }
    @When("User clicks on the logout button")
    public void user_clicks_on_the_logout_button() {
        BrowserUtils.waitFor(10);
        basePage.username.click();
        BrowserUtils.waitForVisibility(basePage.logoutButton, 5);
         basePage.logoutButton.click();
    }
    @When("User should be logged out")
    public void user_should_be_logged_out() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(ConfigurationReader.getProperty("loginPage")));
    }
    @When("User clicks on the back button")
    public void user_clicks_on_the_back_button() {
        Driver.getDriver().navigate().back();
    }
    @Then("User should not be able to access the homepage")
    public void user_should_not_be_able_to_access_the_homepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(ConfigurationReader.getProperty("loginPage")));
    }
    @When("User is away from keyboard for {int} minutes")
    public void user_is_away_from_keyboard_for_minutes(Integer int1) {
      BrowserUtils.waitForVisibility(basePage.username, 10);
        BrowserUtils.sleep(int1*60);
    }


}
