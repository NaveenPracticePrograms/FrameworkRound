package com.coding.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import com.coding.StepDefinitions.*;
import io.cucumber.java.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotManager {
	WebDriver driver;
	Scenario scenario;
	LogManager log=new LogManager();
	public ScreenshotManager(WebDriver driver) {
		this.driver = driver;
	}

	public void getScenario() {
		this.scenario = StepDefinition.getscenario();
	}

	public void takeScreenshot() {
		try{
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		Date date=new Date();
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		getScenario();
		ImageIO.write(screenshot.getImage(), "png", new File(".\\test-output\\screenshots\\"+scenario.getName()+dateformat.format(date)+".png"));
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		ImageIO.write(screenshot.getImage(), "png", outputStream);
		scenario.attach(outputStream.toByteArray(), "image/png", scenario.getName());
		} catch (Exception e) {
			log.logException(scenario, e);
		}
		}

}
