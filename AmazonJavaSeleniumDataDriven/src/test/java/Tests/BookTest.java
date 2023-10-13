package Tests;

import java.text.ParseException;

import org.testng.annotations.Test;

import core.BaseTest;
import dataProviders.BookDataProvider;
import pages.BookPage;

public class BookTest extends BaseTest {

	@Test(description = "3. Verify result list is paginated if there are more than 16 items", dataProviderClass = BookDataProvider.class, dataProvider = "book_data_for_Paginated")
	public void VerifyResultListPaginated(String department, String keyword, String language, int exptectednumber) {
		BookPage bookPage = new BookPage(getDriver());

		bookPage.SearchStep(department, keyword);
		bookPage.selectLanguage(language);

		bookPage.VerifyResultDisplayItemNumerOneachPageAsExpect(exptectednumber);
	}

	@Test(description = "4. Verify result list can be sorted on demand", dataProviderClass = BookDataProvider.class, dataProvider = "book_data_for_publication_date")
	public void VerifyResultSortByPublicationDate(String department, String keyword, String language)
			throws ParseException {
		BookPage bookPage = new BookPage(getDriver());

		bookPage.SearchStep(department, keyword);
		bookPage.selectLanguage(language);
		bookPage.selectSortBy("Publication Date");

		bookPage.VerifyResultSortByPublicationDate();
	}
}
