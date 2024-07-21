package com.seleniumAutomation.UIAutomation;

import com.seleniumAutomation.UIAutomation.base.TestListeners;
import com.seleniumAutomation.UIAutomation.base.TestRetry;
import com.seleniumAutomation.UIAutomation.ui.Reader.ConfigReader;
import com.seleniumAutomation.UIAutomation.ui.driver.DriverManager;
import com.seleniumAutomation.UIAutomation.ui.helpers.AssertHelper;
import com.seleniumAutomation.UIAutomation.ui.pages.ConfirmPopup;
import com.seleniumAutomation.UIAutomation.ui.pages.FormPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.HashMap;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/26/2024
 */
@Slf4j
@Listeners(TestListeners.class)
public class TestDemoQAForms {

    ConfigReader configReader = new ConfigReader();
    DriverManager driverManager = new DriverManager();
    WebDriver driver;
    private String baseURL = configReader.getProperty("src/test/resources/application.properties", "base.url");

    @BeforeMethod
    void createBrowser() {
        driver = driverManager.getDriver();
    }


    /**
     * Method to verify chrome launched with expected URL or not
     */
    @Test(priority = 0, description = "verify new driver launched", retryAnalyzer = TestRetry.class)
    void verifyDriverLaunched() {
        driver.get(baseURL);
        AssertHelper.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/text-box", "Verified new driver got launched.");
    }


    @Test(priority = 1, description = "submit the form with mandatory fields")
    void testFormWithMandatoryFields() {
        HashMap<String, String> userDetails = new HashMap<>();
        userDetails.put("fname", "pallavi");
        userDetails.put("lname", "sawale");
        userDetails.put("email", "psawale@gmail.com");
        userDetails.put("mobile", "7757909090");
        userDetails.put("gender", "Female");


        FormPage formPage = new FormPage(driver);
        formPage.clickFormsLink();
        formPage.clickPracticeFormsLink();

        AssertHelper.assertTrue(formPage.isWelcomeMessageDisplayed(), "welcome message not visible");

        formPage.fillFormWithMandatoryFields(userDetails.get("fname"), userDetails.get("lname"),
                userDetails.get("mobile"), userDetails.get("gender"), userDetails.get("email"));

        ConfirmPopup confirmPopup = new ConfirmPopup(driver);

        AssertHelper.assertTrue(confirmPopup.isConfirmMessageVisible(), "confirmation message not visible");

        AssertHelper.assertEquals(String.format("%s %s", userDetails.get("fname"), userDetails.get("lname")),
                confirmPopup.getAttributeValue("Student Name"), "student name mismatch");
        AssertHelper.assertEquals(userDetails.get("email"), confirmPopup.getAttributeValue("Student Email"),
                "email mismatch");
        AssertHelper.assertEquals(userDetails.get("gender"), confirmPopup.getAttributeValue("Gender"),
                "gender mismatch");
        AssertHelper.assertEquals("7757909090", confirmPopup.getAttributeValue("Mobile"),
                "mobile mismatch");

        confirmPopup.clickCloseButton();
    }


    /**
     * Method to take screenshot for failed TCS
     *
     * @param testResult
     */
    @AfterMethod
    void takeScreenshotForFailedTestCase(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            driverManager.takeScreenshot(testResult.getMethod().getMethodName());
        }
    }

    /**
     * Method to close driver instances after test suite
     */
    @AfterSuite
    void closeDriver() {
        driverManager.closeBrowser();
    }

}
