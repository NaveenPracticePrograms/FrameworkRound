package com.coding.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.coding.StepDefinitions.StepDefinition;

public class ExcelManager {

	public static HashMap<String, String> getUserDetails(String user) {
		LogManager log = new LogManager();
		HashMap<String, String> userDetails = new HashMap<String, String>();
		try {
			File file = new File(System.getProperty("user.dir") + "/src/test/resources/loginCredentials.xlsx");
			InputStream input = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sheet = wb.getSheet("login");
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				if (cell.toString().equals(user)) {
					userDetails.put("name", row.getCell(1).toString());
					userDetails.put("pass", row.getCell(2).toString());
					userDetails.put("status", row.getCell(3).toString());
					userDetails.put("expectedMessage", row.getCell(4).toString());
					log.logMessage(StepDefinition.getscenario(),
							"On login using Uname= " + row.getCell(1).toString() + " and Password= "
									+ row.getCell(2).toString() + " ,it should be a " + row.getCell(3).toString()
									+ " with message " + row.getCell(4).toString());

				}
			}

		} catch (Exception e) {
			log.logException(StepDefinition.getscenario(), e);
		}
		return userDetails;
	}
}
