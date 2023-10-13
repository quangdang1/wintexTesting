package pages;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.LocatorType;
import utils.WebObject;

public class BookPage extends GeneralPage {

	private WebObject englishItem = new WebObject(LocatorType.XPATH, "//li[@aria-label='English']//label");
	private WebObject searchResults = new WebObject(LocatorType.XPATH,
			"//div[starts-with(@cel_widget_id,'MAIN-SEARCH_RESULTS')]");
	private WebObject publicationDates = new WebObject(LocatorType.XPATH,
			"//div[starts-with(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//span[@class='a-size-base a-color-secondary a-text-normal']");
	private WebObject sortBySelect = new WebObject(LocatorType.ID, "s-result-sort-select");

	public BookPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void selectLanguage(String language) {
		if(language.equals("English")) {
			clickElement(englishItem);
		}
	}

	public void selectSortBy(String sortType) {
		selectItemByTextInSelectElement(sortBySelect, sortType);
	}

	public void VerifyResultDisplayItemNumerOneachPageAsExpect(final int expectedNumber) {
		loopPage(new Verify() {
			@Override
			public void verify() {
				Assert.assertEquals(searchResults.getWebElements(driver).size(), expectedNumber);
			}
		});
	}

	public void VerifyResultSortByPublicationDate() throws ParseException {
		waitElementDisplay(nextButton);
		final Date maxDate = new SimpleDateFormat("MMM dd, yyyy")
				.parse(publicationDates.getWebElements(driver).get(0).getText());

		loopPage(new Verify() {
			@Override
			public void verify() {
				Date copyMaxDate = maxDate;
				List<WebElement> publicationDateElements = publicationDates.getWebElements(driver);

				for (WebElement e : publicationDateElements) {

					try {
						Date date = new SimpleDateFormat("MMM dd, yyyy").parse(e.getText());
						if (date.after(copyMaxDate)) {
							Assert.fail(MessageFormat.format(
									"Sorting by pubblication date is incorrect. item {0} should be above {1}", date,
									copyMaxDate));
						} else {
							copyMaxDate = date;
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
