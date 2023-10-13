package core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Constants;
import utils.WebObject;

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickElement(WebObject weObject) {
		waitElementClickable(weObject);
		WebElement element = weObject.getWebElement(driver);	
		moveToElement(element);
		element.click();
	}

	public void enterText(WebObject weObject, String text) {
		waitElementDisplay(weObject);		
		WebElement element = weObject.getWebElement(driver);
		moveToElement(element);
		element.sendKeys(text);
	}

	public String getText(WebObject weObject) {
		waitElementDisplay(weObject);
		WebElement element = weObject.getWebElement(driver);		
		return element.getText().trim();
	}
	
	public void selectItemByTextInSelectElement(WebObject weObject,String itemText) {
		waitElementExist(weObject);
		Select select=new Select(weObject.getWebElement(driver));
		select.selectByVisibleText(itemText);
		
	}
	
	public void waitElementDisplay(WebObject weObject) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIME_OUT));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(weObject.getLocator()));

		} catch (Exception e) {
			throw e;
		}
	}
	
	public void waitElementExist(WebObject weObject) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIME_OUT));
		try {
			wait.until(ExpectedConditions.
					presenceOfElementLocated(weObject.getLocator()));

		} catch (Exception e) {
			throw e;
		}
	}
	
	public void waitElementClickable(WebObject weObject) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIME_OUT));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(weObject.getLocator()));

		} catch (Exception e) {
			throw e;
		}
	}

	private void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

}
