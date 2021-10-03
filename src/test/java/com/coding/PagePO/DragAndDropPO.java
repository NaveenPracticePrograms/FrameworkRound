package com.coding.PagePO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.coding.StepDefinitions.StepDefinition;
import com.coding.utils.LogManager;

public class DragAndDropPO {
	WebDriver driver;
	LogManager log=new LogManager();
	By FirstBox = By.xpath("//div[@id='column-a']");
	By SecBox = By.xpath("//div[@id='column-b']");
	String jsSimulateTrigger="function createEvent(typeOfEvent) {\n" +"var event =document.createEvent(\"CustomEvent\");\n" +"event.initCustomEvent(typeOfEvent,true, true, null);\n" +"event.dataTransfer = {\n" +"data: {},\n" +"setData: function (key, value) {\n" +"this.data[key] = value;\n" +"},\n" +"getData: function (key) {\n" +"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n" +"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n" +"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" +"}\n" +"if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" +"} else if (element.fireEvent) {\n" +"element.fireEvent(\"on\" + event.type, event);\n" +"}\n" +"}\n" +"\n" +"function simulateHTML5DragAndDrop(element, destination) {\n" +"var dragStartEvent =createEvent('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n" +"var dropEvent = createEvent('drop');\n" +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" +"var dragEndEvent = createEvent('dragend');\n" +"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" +"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n" +"simulateHTML5DragAndDrop(source,destination);";
	public DragAndDropPO(WebDriver driver) {
		this.driver = driver;
	}

	public void boxAtoboxB() {
		try{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jsSimulateTrigger,driver.findElement(FirstBox), driver.findElement(SecBox));
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		}

	public String getvalueOf(String string) {
		String boxValue = null;
		try{
		if (string.equals("FirstBox"))
			boxValue=driver.findElement(FirstBox).getText();
		if (string.equals("SecBox"))
			boxValue=driver.findElement(SecBox).getText();
		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return boxValue;
	}

}
