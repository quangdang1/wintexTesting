package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import core.BasePage;
import utils.LocatorType;
import utils.WebObject;

public class GeneralPage extends BasePage {
	
	private WebObject signInButton = new WebObject(LocatorType.ID, "nav-link-accountList");

	private WebObject helloNameText = new WebObject(LocatorType.XPATH, "//a[@id='nav-link-accountList']/div/span");

	private WebObject searchDeparmentSelect = new WebObject(LocatorType.ID, "searchDropdownBox");

	private WebObject searchBox = new WebObject(LocatorType.ID, "twotabsearchtextbox");

	private WebObject searchSubmitButton = new WebObject(LocatorType.ID, "nav-search-submit-button");
	
	protected WebObject nextButton = new WebObject(LocatorType.XPATH,
			"//div[@role='navigation']//*[contains(text(),'Next')]");
	private WebObject lastPageNumber = new WebObject(LocatorType.XPATH,
			"//*[text()='...']//parent::span//following-sibling::span");

	public GeneralPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void ClickSignInButton() {
		clickElement(signInButton);
	}

	public void SearchStep(String deparment, String keyword) {
		selectItemByTextInSelectElement(searchDeparmentSelect,deparment);

		enterText(searchBox, keyword);
		clickElement(searchSubmitButton);
	}
	
	public void loopPage(Verify func) {
		int lastPapeNum = Integer.parseInt(getText(lastPageNumber));
		for (int i = 0; i < lastPapeNum; i++) {
			waitElementDisplay(nextButton);
			func.verify();
			if (i < lastPapeNum - 1) {
				clickElement(nextButton);
			}
		}
	}

	@FunctionalInterface
	interface Verify {
		void verify();
	}

	public void VerifyAccountDisplayCorrect(String text) {
		Assert.assertEquals(getText(helloNameText), text);
	}

}
