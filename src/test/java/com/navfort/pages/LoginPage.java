package com.navfort.pages;

import com.navfort.utilities.BrowserUtils;
import com.navfort.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "prependedInput")
	public WebElement usernameInput;

	@FindBy(id = "prependedInput2")
	public WebElement passwordInput;

	@FindBy(id = "_submit")
	public WebElement submitButton;

	@FindBy(css = "div.alert")
	public WebElement warningMessage;

	@FindBy(linkText = "Forgot your password?")
	public WebElement forgotPasswordLink;

	@FindBy(css = "[class='custom-checkbox__icon']")
	public WebElement rememberMeCheckbox;



	public void login(String username, String password) {

		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();
	}

	public String errorMessageText(String field) {

		String message = null;

		try {
			if (field.equals("username")) {
				JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
				message = (String) js.executeScript("return arguments[0].validationMessage;", usernameInput);
			} else if (field.equals("password")) {
				JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
				message = (String) js.executeScript("return arguments[0].validationMessage;", passwordInput);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(message);
		return message;

	}


}
