package com.coding.PagePO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class JsAlertPO {
	WebDriver driver;
	WebDriverWait wait;
	LogManager log = new LogManager();
	By jsConfirm = By.xpath("//button[contains(text(),'Click for JS Confirm')]");
	By jsCancelAlertMessage = By.xpath("//*[@id='result']");

	public JsAlertPO(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public boolean clickOnJSConfirm() {
		// TODO Auto-generated method stub
		try {
			driver.findElement(jsConfirm).click();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		if (wait.until(ExpectedConditions.alertIsPresent()) == null)
			return false;
		else
			return true;
	}

	public String cancelAlert() {
		String message = null;
		try {
			driver.switchTo().alert().dismiss();
			message = driver.findElement(jsCancelAlertMessage).getText();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return message;

	}

}
