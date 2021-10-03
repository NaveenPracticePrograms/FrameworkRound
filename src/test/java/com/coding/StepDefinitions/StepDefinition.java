package com.coding.StepDefinitions;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.coding.PagePO.DragAndDropPO;
import com.coding.PagePO.DynamicLoadingPO;
import com.coding.PagePO.FormValidationPO;
import com.coding.PagePO.FramesPO;
import com.coding.PagePO.HomePagePO;
import com.coding.PagePO.JsAlertPO;
import com.coding.PagePO.MultipleWindowsPO;
import com.coding.utils.DriverManager;
import com.coding.utils.ExcelManager;
import com.coding.utils.LogManager;
import com.coding.utils.ScreenshotManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	public static Scenario scenario;
	Set<String> mulWindows;
	String urlOfNewWindow, urlOfParentWindow, firstBoxValue, secBoxValue;
	WebDriver driver;
	HomePagePO homePage;
	FormValidationPO login;
	DynamicLoadingPO dynamicPage;
	ScreenshotManager scrsht;
	HashMap<String, String> userDetails;
	MultipleWindowsPO multipleWindow;
	DragAndDropPO dragAndDrop;
	FramesPO framesPage;
	JsAlertPO jsAlertPage;
	LogManager log;

	@Before
	public void Setup(Scenario scenario) {
		driver = DriverManager.setDriver("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		homePage = new HomePagePO(driver);
		login = new FormValidationPO(driver);
		dynamicPage = new DynamicLoadingPO(driver);
		scrsht = new ScreenshotManager(driver);
		multipleWindow = new MultipleWindowsPO(driver);
		dragAndDrop = new DragAndDropPO(driver);
		framesPage = new FramesPO(driver);
		jsAlertPage = new JsAlertPO(driver);
		StepDefinition.scenario = scenario;
		log = new LogManager();
		log.logScenario();
	}

	public static Scenario getscenario() {
		return scenario;
	}

	@Given("User launches the application and clicks on {string}")
	public void user_launches_the_application_and_clicks_on_something(String testAction) {

		boolean actuallaunchstatus = homePage.launchAndValidateHeader();
		Assert.assertTrue(actuallaunchstatus, "Application Launch Failure");
		log.logMessage(scenario, "Appication launched successfully");
		boolean actualteststatus = homePage.clickandValidate(testAction);
		System.out.println("actual sttatus is " + actualteststatus);
		Assert.assertTrue(actualteststatus, testAction + " click Failed, Validate");
		log.logMessage(scenario, testAction + " link is clicked from HomePage");

	}

	@When("User provides the username and password for {string}")
	public void user_provides_the_username_and_password_for(String userType) {

		log.logMessage(scenario, "Entering user name and password for " + userType);
		userDetails = ExcelManager.getUserDetails(userType);
		login.loginUser(userDetails);

	}

	@Then("for valid credendtial login successfully else fail with an expected error")
	public void for_valid_credendtial_login_successfully_else_fail_with_an_expected_error() {

		String actualStatusMessage = login.validateLoginAndReturnMessage(userDetails);
		System.out.println("message " + actualStatusMessage.trim() + " complted");
		Assert.assertEquals(actualStatusMessage.trim(), userDetails.get("expectedMessage"));
		log.logMessage(scenario, "Expected Message after Login= " + userDetails.get("expectedMessage")
				+ "Actual Status after Login= " + actualStatusMessage.trim());

	}

	@When("User selects the element and clicks on start")
	public void user_selects_the_element_and_clicks_on_start() {
		try {
			log.logMessage(scenario, "Element2 is clicked and now clicking on start Button in Dynamic Loading Page");
			dynamicPage.selectElementAndClickStart();
			scrsht.takeScreenshot();
		} catch (Exception e) {
			log.logException(scenario, e);
		}
	}

	@Then("Validate the progress bar and once completed validate the final message")
	public void validate_the_progress_bar_and_once_completed_validate_the_final_message() {

		log.logMessage(scenario, "on clicking start, progress bar is loading as expected");
		String actualStatusMessage = dynamicPage.validateProgressBarAndGetStatus();
		Assert.assertEquals(actualStatusMessage, dynamicPage.expectedStatusMessage);
		log.logMessage(scenario, "Progress bar completed with message as " + actualStatusMessage);

	}

	@When("User clicks on the link on multiple page window")
	public void user_clicks_on_the_link() {

		urlOfParentWindow = driver.getCurrentUrl();
		log.logMessage(scenario, "Clicking on 'Click Here' link on the Mulitple Window Page screen");
		mulWindows = multipleWindow.clickAndReturnWindowHandles();
		log.logMessage(scenario, mulWindows.size() - 1 + " extra windows opened in addition to the parent window");

	}

	@Then("User should log the URL of the newly opened tab")
	public void user_should_log_the_url_of_the_newly_opened_tab() {

		urlOfNewWindow = multipleWindow.getURLofNewWindow(mulWindows);
		log.logMessage(scenario, "URL of the newly Opened Child Window is " + urlOfNewWindow);

	}

	@And("User close the newly opened tab")
	public void user_close_the_newly_opened_tab() {

		multipleWindow.closetheNewWindow(urlOfNewWindow);
		Set<String> currentWindows = driver.getWindowHandles();
		Assert.assertEquals(currentWindows.size(), 1,
				"More than One(parent) Window is availabel hence failing the case");
		log.logMessage(scenario, "Child window is closed");

	}

	@And("User logs and validates the title of the current page")
	public void user_logs_and_validates_the_title_of_the_current_page() {

		Assert.assertEquals(driver.getCurrentUrl(), urlOfParentWindow,
				"Current URL is not the Parent window URL hence failing the case");
		log.logMessage(scenario, "Current(Parent) Page URL is " + urlOfParentWindow
				+ " and title of the current Page is " + driver.getTitle());

	}

	@When("User Should drag box A and drops it on box b")
	public void user_should_drag_box_a_and_drops_it_on_box_b() {

		firstBoxValue = dragAndDrop.getvalueOf("FirstBox");
		secBoxValue = dragAndDrop.getvalueOf("SecBox");
		log.logMessage(scenario, "Before moving Box A to Box B, Value in first Box is " + firstBoxValue
				+ " & Value in sec Box is " + secBoxValue);
		dragAndDrop.boxAtoboxB();

	}

	@Then("Validate whether the box drop is success and take a screenshot")
	public void validate_whether_the_box_drop_is_success() {
		try {
			String newFirstBoxValue = dragAndDrop.getvalueOf("FirstBox");
			String newSecBoxValue = dragAndDrop.getvalueOf("SecBox");
			Assert.assertEquals(firstBoxValue, newSecBoxValue);
			Assert.assertEquals(secBoxValue, newFirstBoxValue);

			log.logMessage(scenario, "After moving Box A to Box B, Value in first Box is " + newFirstBoxValue
					+ " & Value in sec Box is " + newSecBoxValue);
			scrsht.takeScreenshot();
		} catch (Exception e) {
			log.logException(scenario, e);
		}
	}

	@When("User clicks on iFrame")
	public void user_clicks_on_i_frame() {

		boolean status = framesPage.clickonIFrameAndValidateHeader();
		Assert.assertTrue(status, "iFrame link is clicked however the header on the screen is not Matching");

	}

	@Then("User should be able to clear the predefined text and enter some text")
	public void user_should_be_able_to_clear_the_predefined_text_and_enter_some_text() {

		framesPage.clearAndEnterNewText();

	}

	@Then("User should be able to make the newly entered text in bold and take a screenshot")
	public void user_should_be_able_to_make_the_newly_entered_text_in_bold_and_take_a_screenshot() {
		try {
			boolean actualstatusifbold = framesPage.makeTextBold();
			Assert.assertTrue(actualstatusifbold, "New Entered text is not changed to Bold");
			scrsht.takeScreenshot();
		} catch (Exception e) {
			log.logException(scenario, e);
		}
	}

	@When("User Should clicks on js confirm link")
	public void user_should_clicks_on_js_confirm_link() {

		boolean isAleartPresent = jsAlertPage.clickOnJSConfirm();
		Assert.assertTrue(isAleartPresent, "Alert is not present");
		log.logMessage(scenario, "Got an Alert Box on clicking on js confirm link");

	}

	@Then("User should cancel the alert opened and validate the alert cancelled message")
	public void user_should_cancel_the_alert_opened_and_validate_the_alert_cancelled_message() {

		String alertCancelMessage = jsAlertPage.cancelAlert();
		Assert.assertEquals(alertCancelMessage, "You clicked: Cancel",
				"Expected Alert Message:You clicked: Cancel, Actual Message:" + alertCancelMessage);
		log.logMessage(scenario, "Message on cancelling the Alert= " + alertCancelMessage);

	}

	@After
	public void tearDown() {
		try {
			if (scenario.isFailed())
				scrsht.takeScreenshot();
			driver.quit();
		} catch (Exception e) {
			log.logException(scenario, e);
		}
	}
}
