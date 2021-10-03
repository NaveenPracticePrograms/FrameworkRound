package com.coding.PagePO;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class HomePagePO {
	WebDriver driver;
	LogManager log = new LogManager();
	String url = "http://the-internet.herokuapp.com/";
	By homePageHeader = By.xpath("//h1[@class='heading']");

	By formLogin = By.linkText("Form Authentication");
	By loginPageHeader = By.xpath("//h2");

	By dynamicLoading = By.linkText("Dynamic Loading");
	By dynamicPageHeader = By.xpath("//h3");

	By multipleWindow = By.linkText("Multiple Windows");
	By multiplePageHeader = By.xpath("//h3");

	By dragAndDrop = By.linkText("Drag and Drop");
	By dragAndDropHeader = By.xpath("//h3");

	By frames = By.linkText("Frames");
	By framesHeader = By.xpath("//h3");

	By jsAlert = By.linkText("JavaScript Alerts");
	By jsAlertHeader = By.xpath("//h3");

	public HomePagePO(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void goToHomePage() {
		driver.navigate().to(url);
	}

	public boolean launchAndValidateHeader() {
		String actualHeader = null;
		try {
			driver.get(url);
			actualHeader = driver.findElement(homePageHeader).getText();
			log.logMessage(StepDefinition.getscenario(), "Status message on login is " + actualHeader);
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		if (actualHeader.equals("Welcome to the-internet"))
			return true;
		else
			return false;
	}

	public boolean clickandValidate(String testAction) {
		boolean clickStatus = false;
		try {
			switch (testAction) {
			case ("Form Authentication"): {
				driver.findElement(formLogin).click();
				String actualHeader = driver.findElement(loginPageHeader).getText();
				log.logMessage(StepDefinition.getscenario(),
						"Header message on clicking Form_authentication Link " + actualHeader);
				if (actualHeader.equals("Login Page"))
					clickStatus = true;
				break;
			}
			case ("Dynamic Loading"): {
				driver.findElement(dynamicLoading).click();
				String actualHeader = driver.findElement(dynamicPageHeader).getText();
				log.logMessage(StepDefinition.getscenario(),
						"Header message on clicking Dynamic Loading Page " + actualHeader);
				if (actualHeader.contains("Dynamically Loaded Page Elements"))
					clickStatus = true;
				break;
			}
			case ("Multiple Windows"): {
				driver.findElement(multipleWindow).click();
				String actualHeader = driver.findElement(multiplePageHeader).getText();
				log.logMessage(StepDefinition.getscenario(),
						"Header message on clicking Multiple Window Page is " + actualHeader);
				if (actualHeader.contains("Opening a new window"))
					clickStatus = true;
				break;
			}
			case ("Drag and Drop"): {
				driver.findElement(dragAndDrop).click();
				String actualHeader = driver.findElement(dragAndDropHeader).getText();
				log.logMessage(StepDefinition.getscenario(),
						"Header message on clicking Drag and Drop Page is " + actualHeader);
				if (actualHeader.contains("Drag and Drop"))
					clickStatus = true;
				break;
			}
			case ("Frames"): {
				driver.findElement(frames).click();
				String actualHeader = driver.findElement(framesHeader).getText();
				log.logMessage(StepDefinition.getscenario(), "Header message on clicking Frames is " + framesHeader);
				if (actualHeader.contains("Frames"))
					clickStatus = true;
				break;
			}
			case ("JavaScript Alerts"): {
				driver.findElement(jsAlert).click();
				String actualHeader = driver.findElement(jsAlertHeader).getText();
				log.logMessage(StepDefinition.getscenario(),
						"Header message on clicking JavaScript Alerts Page is " + jsAlertHeader);
				if (actualHeader.contains("JavaScript Alerts"))
					clickStatus = true;
				break;
			}
			}
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return clickStatus;
	}

}
