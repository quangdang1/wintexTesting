package core;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.Constants;

import org.testng.annotations.AfterTest;

public class BaseTest {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return this.driver.get();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("---Before Test");
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Reports");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Quang Lang Thang");
	}

	@BeforeMethod
	@Parameters(value = { "browserName" })
	public void beforeMethod(String browserName, Method testMethod) {
		System.out.println("---Before Method");
		logger = extent.createTest(testMethod.getName());
		setupDriver(browserName);
		getDriver().get(Constants.URL);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("---After Method");
		if (result.getStatus() == ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " - Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " - Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " - Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
		}

		getDriver().quit();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("---After Test");
		extent.flush();

	}

	public void setupDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers" + File.separator + "chromedriver");
			ChromeDriver driver = new ChromeDriver();
			setDriver(driver);

		}
	}

}