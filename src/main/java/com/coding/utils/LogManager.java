package com.coding.utils;

import org.apache.log4j.Logger;
import io.cucumber.java.Scenario;

public class LogManager {
	Logger log = Logger.getLogger(LogManager.class);
	

	public void logMessage(Scenario scenario, String message) {
		log.info(message);
		scenario.log(message);
	}

	public void logException(Scenario scenario, Exception e) {
		log.error("Failed with an Exception- " + e.getClass().getCanonicalName() + "\n " , e);
		scenario.log("Failed with an Exception- <b>" + e.getClass().getCanonicalName() + "</b>\n <b>Check application.log File for stacktrace <b>");
	}

	public void logError(Scenario scenario, String errorMessage) {
		log.error(errorMessage);
		scenario.log(errorMessage);
	}

	public void logScenario() {
		log.info("-------------------------------------------Started-------------------------------------------");
		
	}
}
