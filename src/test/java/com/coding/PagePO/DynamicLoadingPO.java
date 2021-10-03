package com.coding.PagePO;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class DynamicLoadingPO {
	WebDriver driver;
	LogManager log = new LogManager();
	
	public String expectedStatusMessage = "Hello World!";
	By element2 = By.linkText("Example 2: Element rendered after the fact");
	By start = By.tagName("button");
	By loadingComplete = By.xpath("//div[@id='finish']");
	By message = By.xpath("//div[@id='finish']/h4");

	public DynamicLoadingPO(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void selectElementAndClickStart() {
		try{
		driver.findElement(element2).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(start))).click();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
	}

	public String validateProgressBarAndGetStatus() {
		String finalMessage = null;
		try{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(loadingComplete)));
		finalMessage = driver.findElement(message).getText();
		return finalMessage;
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return finalMessage;
	}

}
