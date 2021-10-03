package com.coding.PagePO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class FramesPO {
	WebDriver driver;
	LogManager log = new LogManager();
	By iframe = By.linkText("iFrame");
	By iframeHeader = By.xpath("//h3");
	By newframe = By.xpath("//*[@id='mce_0_ifr']");
	By textarea = By.xpath("//*[@id='tinymce']");
	By boldButton = By.xpath("//button[@title='Bold']");

	public FramesPO(WebDriver driver) {
		this.driver = driver;

	}

	public boolean clickonIFrameAndValidateHeader() {
		boolean status = false;
		try {
			driver.findElement(iframe).click();
			System.out.println(driver.findElement(iframeHeader).getText());
			if (driver.findElement(iframeHeader).getText().equals("An iFrame containing the TinyMCE WYSIWYG Editor"))
				status = true;
			else
				status = false;
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return status;
	}

	public void clearAndEnterNewText() {
		try {
			driver.switchTo().frame(driver.findElement(newframe));
			driver.findElement(textarea).click();
			driver.findElement(textarea).clear();
			driver.findElement(textarea).sendKeys("Hello World");
			log.logMessage(StepDefinition.getscenario(), "The New Text Entered is " + "Hello World");
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
	}

	public boolean makeTextBold() {
		boolean status = false;
		try {
			driver.findElement(textarea).sendKeys(Keys.CONTROL + "A");
			driver.switchTo().defaultContent();
			driver.findElement(boldButton).click();
			driver.switchTo().frame(driver.findElement(newframe));
			driver.findElement(textarea).click();
			if (driver.findElements(By.tagName("strong")).size() > 0) {
				log.logMessage(StepDefinition.getscenario(), "the entered text is in bold");
				status = true;
			}
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return status;
	}

}
