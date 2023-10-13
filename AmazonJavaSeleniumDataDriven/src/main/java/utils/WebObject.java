package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebObject {
	public final LocatorType locatorType;
	public final String locatorValue;

	public WebObject(LocatorType locatorType, String locatorValue) {
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
	}

	public By getLocator() {
		if (locatorType == LocatorType.XPATH) {
			return By.xpath(locatorValue);
		} else if (locatorType == LocatorType.ID) {
			return By.id(locatorValue);
		}
		return null;
	}

	public WebElement getWebElement(WebDriver driver) {
		if (locatorType == LocatorType.XPATH) {
			return driver.findElement(By.xpath(locatorValue));
		} else if (locatorType == LocatorType.ID) {
			return driver.findElement(By.id(locatorValue));
		}
		return null;
	}

	public List<WebElement> getWebElements(WebDriver driver) {
		if (locatorType == LocatorType.XPATH) {
			return driver.findElements(By.xpath(locatorValue));
		} else if (locatorType == LocatorType.ID) {
			return driver.findElements(By.id(locatorValue));
		}
		return null;
	}
}
