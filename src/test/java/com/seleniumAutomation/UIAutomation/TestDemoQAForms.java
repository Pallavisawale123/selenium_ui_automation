package com.seleniumAutomation.UIAutomation;

import com.seleniumAutomation.UIAutomation.base.TestListeners;
import com.seleniumAutomation.UIAutomation.base.TestRetry;
import com.seleniumAutomation.UIAutomation.ui.driver.DriverManager;
import com.seleniumAutomation.UIAutomation.ui.helpers.AssertHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/26/2024
 */
@Slf4j
@Listeners(TestListeners.class)
public class TestDemoQAForms {

    DriverManager driverManager = new DriverManager();
    WebDriver driver;

    @BeforeMethod
    void createBrowser() {
        driver = driverManager.getDriver();
    }

    /**
     * Method to verify chrome launched with expected URL or not
     */
    @Test(description = "verify new driver launched",retryAnalyzer = TestRetry.class)
    void verifyDriverLaunched() {
        driver.get("https://demoqa.com/automation-practice-form");
        AssertHelper.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/automation-practice-for", "Verified new driver got launched.");
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
