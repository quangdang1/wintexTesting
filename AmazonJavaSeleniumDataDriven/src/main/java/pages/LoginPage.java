package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import core.BasePage;
import utils.LocatorType;
import utils.WebObject;

public class LoginPage extends BasePage {

	private WebObject emailAddressbox = new WebObject(LocatorType.ID, "ap_email");

	private WebObject continueButton = new WebObject(LocatorType.ID, "continue");
	
	private WebObject passwordBox = new WebObject(LocatorType.ID, "ap_password");
	
	private WebObject SignInButton = new WebObject(LocatorType.ID, "signInSubmit");
	
	private WebObject errorMesgText = new WebObject(LocatorType.XPATH, "//*[@id='auth-error-message-box']//span");
	

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void EnterEmail(String text) {
		enterText(emailAddressbox, text);
	}

	public void ClickContinuebutton() {
		clickElement(continueButton);
	}
	
	public void EnterPassword(String pass) {
		enterText(passwordBox,pass);
	}
	
	public void ClickSignInButton() {
		clickElement(SignInButton);
	}
	
	
	public void VerifyErrorMessageCorrect(String text) {
		Assert.assertEquals(getText(errorMesgText),text);
	}
	
}
