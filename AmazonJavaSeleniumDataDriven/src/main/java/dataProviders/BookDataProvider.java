package dataProviders;

import org.testng.annotations.DataProvider;

public class BookDataProvider {
	@DataProvider(name = "book_data_for_Paginated")
	public Object[][] bookData() throws Exception {
		//Department - keyword - Language - ExpectedNumberItem
		Object[][] objects = new Object[][] { { "Books", "apple", "English", 16 } };
		return objects;
	}
	
	@DataProvider(name = "book_data_for_publication_date")
	public Object[][] bookDataForPublicationDate() throws Exception {
		//Department - keyword - Language 
		Object[][] objects = new Object[][] { { "Books", "apple", "English" } };
		return objects;
	}
}
