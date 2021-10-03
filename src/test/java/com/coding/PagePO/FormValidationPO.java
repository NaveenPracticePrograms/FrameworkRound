package com.coding.PagePO;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class FormValidationPO {
	WebDriver driver;
	LogManager log = new LogManager();
	By usename = By.id("username");
	By password = By.id("password");
	By submit = By.xpath("//button[@type='submit']");
	By statusMessage = By.id("flash");
	By logout = By.xpath("//i[contains(text(),' Logout')]");

	public FormValidationPO(WebDriver driver) {
		this.driver = driver;
	}

	public void loginUser(HashMap<String, String> userDetails) {
		try {
			String uname = userDetails.get("name");
			String pass = userDetails.get("pass");
			driver.findElement(usename).sendKeys(uname);
			driver.findElement(password).sendKeys(pass);
			driver.findElement(submit).click();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
	}

	public String validateLoginAndReturnMessage(HashMap<String, String> userDetails) {
		String actualMessage = null;
		try {
			if (userDetails.get("status").equals("Success")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(logout)));
				actualMessage = driver.findElement(statusMessage).getText();
				driver.findElement(logout).click();
			} else
				actualMessage = driver.findElement(statusMessage).getText();
			actualMessage = actualMessage.replace("\n", "");
			actualMessage = actualMessage.substring(0, actualMessage.length() - 1);
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return actualMessage;
	}
}
