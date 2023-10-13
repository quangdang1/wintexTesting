package dataProviders;


import org.testng.annotations.DataProvider;



public class LoginDataProvider {
	
	@DataProvider(name = "invalid_account_data")
	public Object[][] inValidlAccountData() throws Exception {
		//email - errormessage
		Object[][] objects = new Object[][] {
				{ "dtnquang91abcde@gmail.com", "We cannot find an account with that email address" } };
		return objects;
	}

	@DataProvider(name = "valid_login_data")
	public Object[][] validLoginData() throws Exception {
		//email - password - expected text
		Object[][] objects = new Object[][] { { "dtnquang91@gmail.com", "dtkTj29000g", "Hello, Quang" }};
		return objects;
	}

}
