package com.seleniumAutomation.UIAutomation.ui.driver;

import com.seleniumAutomation.UIAutomation.ui.Reader.ConfigReader;
import com.seleniumAutomation.UIAutomation.ui.enums.DriverType;
import com.seleniumAutomation.UIAutomation.ui.enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/30/2024
 */
@Slf4j
public class DriverManager {

    private WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    private DriverType driverType = (DriverType.valueOf(configReader.getProperty("src/test/resources/application.properties", "ui.driver.type")));
    private EnvironmentType envType = EnvironmentType.valueOf(configReader.getProperty("src/test/resources/application.properties", "ui.driver.env"));
    private long implicitWait = Long.parseLong(configReader.getProperty("src/test/resources/application.properties", "ui.driver.implicit-wait"));
    private String screenshotPath = configReader.getProperty("src/test/resources/application.properties", "ui.driver.screenshot.path");
    private boolean headlessEnabled = Boolean.parseBoolean(configReader.getProperty("src/test/resources/application.properties", " ui.driver.screenshot.path"));
    private String hubURL = configReader.getProperty("src/test/resources/application.properties", "ui.driver.hub");
    EventListener eventListener = new EventListener();

    /**
     * Method to get web driver instance
     *
     * @return web driver
     */
    public WebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (envType) {
            case REMOTE:
                driver = createRemoteDriver();
                break;
            case LOCAL:
                driver = createLocalDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        switch (driverType) {
            case CHROME:
                //WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--diable--notifications");
                chromeOptions.addArguments("force-device-scale-factor=0.80");
                String downloadDir = "C:\\Users\\Dreams\\OneDrive\\Documents\\UI Automation\\selenium\\UIAutomation\\UIAutomation\\target";
                HashMap prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
                prefs.put("safebrowsing.enabled", "false");
                chromeOptions.setExperimentalOption("prefs", prefs);
                try {
                    driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case FIREFOX:

                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //default 0 seconds, 0.5 seconds
        EventFiringDecorator eventFiringDecorator = new EventFiringDecorator(eventListener);
        driver = eventFiringDecorator.decorate(driver);
        return driver;
    }

    private WebDriver createLocalDriver() {

        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--diable--notifications");
                chromeOptions.addArguments("force-device-scale-factor=0.80");
                String downloadDir = "C:\\Installations\\temp\\pro-automation\\target";
                HashMap prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
                prefs.put("safebrowsing.enabled", "false");
                chromeOptions.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:

                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //default 0 seconds, 0.5 seconds
        EventFiringDecorator eventFiringDecorator = new EventFiringDecorator(eventListener);
        driver = eventFiringDecorator.decorate(driver);
        return driver;
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }

    /**
     * Method to take screenshot
     *
     * @param testCaseName
     */
    public void takeScreenshot(String testCaseName) {

        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        String fileWithPath = screenshotPath +
                testCaseName.trim().replaceAll(" ", "_") + ".png";

        File DestFile = new File(fileWithPath);

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException exception) {
            log.error("can not capture screenshot: {}", exception.getMessage());
        }
    }
}