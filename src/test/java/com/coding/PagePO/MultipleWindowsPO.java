package com.coding.PagePO;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class MultipleWindowsPO {
	WebDriver driver;
	By clickHere = By.linkText("Click Here");
	String parentWindow;
	LogManager log = new LogManager();

	public MultipleWindowsPO(WebDriver driver) {
		this.driver = driver;
	}

	public Set<String> clickAndReturnWindowHandles() {
		Set<String> allWindowHandles = null;
		try {
			driver.findElement(clickHere).click();
			parentWindow = driver.getWindowHandle();
			allWindowHandles = driver.getWindowHandles();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return allWindowHandles;
	}

	public String getURLofNewWindow(Set<String> mulWindows) {

		try {
			String parentWindowURL = driver.getCurrentUrl();
			log.logMessage(StepDefinition.getscenario(), "URL of the Parent Window is " + parentWindowURL);
			for (String window : mulWindows) {
				driver.switchTo().window(window);
			}
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		String childWindowURL = driver.getCurrentUrl();
		return childWindowURL;
	}

	public void closetheNewWindow(String urlOfNewWindow) {

		if (driver.getCurrentUrl().equals(urlOfNewWindow))
			driver.close();
		driver.switchTo().window(parentWindow);

	}

}
