package com.coding.Runner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/com/coding/features", 
				glue = "com.coding.StepDefinitions",		
				monochrome = true, plugin = {
						"pretty", "html:target/cucumber-html-report.html", "json:target/cucumber-reports/cucumber.json",
						"junit:target/cucumber-reports/cucumber.xml"
						})

public class TestRunner {
	@BeforeClass
	public static void setup() {
		File srcDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
		File targetDir = new File(System.getProperty("user.dir") + "/test-output/oldscreenshots");
		for (File f : targetDir.listFiles()) {
			f.delete();
		}
		File[] srcfiles = srcDir.listFiles();
		for (File f : srcfiles) {
			Path sourcePath = Paths.get(srcDir.getAbsoluteFile() + "\\" + f.getName());
			Path targetPath = Paths.get(targetDir.getAbsolutePath() + "\\" + f.getName());

			try {
				Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	
}
