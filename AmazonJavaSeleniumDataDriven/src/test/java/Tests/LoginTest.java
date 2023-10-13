package Tests;

import org.testng.annotations.Test;

import core.BaseTest;
import dataProviders.LoginDataProvider;
import pages.GeneralPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test(description = "1. Verify functionality of login with invalid account.", dataProviderClass = LoginDataProvider.class, dataProvider = "invalid_account_data")
	public void VerifyUserCanNotLoginWithInvalidAccount(String email, String expectedResult) {
		GeneralPage generalPage = new GeneralPage(getDriver());
		LoginPage loginPage = new LoginPage(getDriver());

		generalPage.ClickSignInButton();
		loginPage.EnterEmail(email);
		loginPage.ClickContinuebutton();

		loginPage.VerifyErrorMessageCorrect(expectedResult);
	}

	@Test(description = "2. Verify user can login to amazon with a valid account.", dataProviderClass = LoginDataProvider.class, dataProvider = "valid_login_data")
	public void VerifyUserCanLoginWithValidAccount(String email, String password, String expectedResult) {
		LoginPage loginPage = new LoginPage(getDriver());
		GeneralPage generalPage = new GeneralPage(getDriver());

		generalPage.ClickSignInButton();
		loginPage.EnterEmail(email);
		loginPage.ClickContinuebutton();
		loginPage.EnterPassword(password);
		loginPage.ClickSignInButton();

		generalPage.VerifyAccountDisplayCorrect(expectedResult);
	}
}
