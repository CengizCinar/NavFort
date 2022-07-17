package com.navfort.step_definitions;

import com.navfort.pages.BasePage;
import com.navfort.pages.ForgotPasswordPage;
import com.navfort.pages.HomePage;
import com.navfort.pages.LoginPage;
import com.navfort.utilities.BrowserUtils;
import com.navfort.utilities.ConfigurationReader;
import com.navfort.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    BasePage basePage = new BasePage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

    @Given("User is on login page")
    public void user_is_on_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("loginPage"));
    }


    @Then("Drivers should lands on Quick Launchpad Page")
    public void drivers_should_lands_on_quick_launchpad_page() {
        String expected = "Quick Launchpad";
        BrowserUtils.waitForVisibility(homePage.pageName, 5);
        Assert.assertEquals(expected, homePage.pageName.getText());
    }

    @When("User enters valid {string} and {string}")
    public void sales_manager_enters_valid_and(String username, String password) {
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.submitButton.click();
    }


    @Then("User should lands on Dashboard Page")
    public void user_should_lands_on_dashboard_page() {
        String expected = "Dashboard";
        BrowserUtils.waitForVisibility(homePage.pageName, 10);
        Assert.assertEquals(expected, homePage.pageName.getText());
    }

    @When("User enters invalid {string} and {string}")
    public void user_enters_invalid_and(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("User should see warning message {string}")
    public void user_should_see_warning_message(String warningMessage) {
        BrowserUtils.waitForVisibility(loginPage.warningMessage, 10);
        Assert.assertTrue(loginPage.warningMessage.isDisplayed());
        Assert.assertTrue(loginPage.warningMessage.getAttribute("innerText").equals(warningMessage));
    }

    @When("User enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        loginPage.login(username, password);
    }


    @Then("User should see directive message {string} in {string}")
    public void userShouldSeeDirectiveMessageIn(String errorMessage, String field) {
        Assert.assertEquals(errorMessage, loginPage.errorMessageText(field));
    }

    @When("User acts {string}")
    public void user_acts(String unauthenticatedSteps) {

        if (unauthenticatedSteps.equals("copy paste homepage url after logout")) {
            loginPage.usernameInput.sendKeys("user21");
            loginPage.passwordInput.sendKeys("UserUser123");
            loginPage.submitButton.click();

            basePage.username.click();
            basePage.logoutButton.click();

            Driver.getDriver().get("https://qa.navfort.com/");
        } else if (unauthenticatedSteps.equals("after logout press the go back button")) {
            loginPage.usernameInput.sendKeys("user21");
            loginPage.passwordInput.sendKeys("UserUser123");
            loginPage.submitButton.click();

            basePage.username.click();
            basePage.logoutButton.click();

            Driver.getDriver().navigate().back();

        }

    }

    @Then("User should be on Login Page")
    public void userShouldBeOnLoginPage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(ConfigurationReader.getProperty("loginPage")));
    }

    @When("User enters any {string} in password field")
    public void user_enters_any_in_password_field(String anyPassword) {
        loginPage.passwordInput.sendKeys(anyPassword);
    }

    @Then("User should see bullet sign in password field")
    public void user_should_see_bullet_sign_in_password_field() {
        Assert.assertTrue(loginPage.passwordInput.getAttribute("type").equals("password"));
    }

    @When("User clicks Forgot your password? link")
    public void user_clicks_forgot_your_password_link() {
        loginPage.forgotPasswordLink.click();
    }

    @Then("User is on {string} page")
    public void user_is_on_page(String pageTitle) {
        Assert.assertEquals(pageTitle, Driver.getDriver().getTitle());
    }

    @When("User enters valid {string}")
    public void user_enters_valid(String username) {
        forgotPasswordPage.usernameInput.sendKeys(username);
        forgotPasswordPage.submitButton.click();
    }

    @Then("User should be able to change their password")
    public void user_should_be_able_to_change_their_password() {
        Assert.assertFalse(forgotPasswordPage.warningMessage.isDisplayed());
    }

    @When("User clicks Remember me on this computer checkbox")
    public void user_clicks_remember_me_on_this_computer_checkbox() {
        loginPage.rememberMeCheckbox.click();
    }

    @When("User closes the browser and opens it again")
    public void user_closes_the_browser_and_opens_it_again() {
        Set<Cookie> cookie = Driver.getDriver().manage().getCookies();

        for (Cookie c : cookie ) {
            if (c.getExpiry()==null) {
                Driver.getDriver().manage().deleteCookie(c);
            }
        }

        Driver.getDriver().get(ConfigurationReader.getProperty("homePage"));

        BrowserUtils.waitFor(2);
    }

    @Then("User should be able to login on this computer")
    public void user_should_be_able_to_login_on_this_computer() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("https://qa.navfort.com/"));
    }

    @When("User enters {string} press ENTER key and enters {string} and press ENTER key")
    public void user_enters_and_press_key(String username, String password) {


        loginPage.usernameInput.sendKeys(username + Keys.ENTER + password + Keys.ENTER);

    }

    @Then("User should click on Login button")
    public void user_should_click_on_login_button() {

        //Driver.getDriver().findElement(By.tagName("body")).sendKeys(Keys.ENTER);
        WebElement currentElement = Driver.getDriver().switchTo().activeElement();
        currentElement.sendKeys(Keys.ENTER);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("https://qa.navfort.com/"));
    }

    @Then("User should see their {string} in the top right corner")
    public void user_should_see_their_username_in_the_top_right_corner(String username) {

        BrowserUtils.waitForVisibility(basePage.username, 10);
        Assert.assertTrue(basePage.username.getAttribute("text").contains(username));
    }
}


