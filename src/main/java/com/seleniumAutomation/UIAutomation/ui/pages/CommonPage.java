package com.seleniumAutomation.UIAutomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 7/21/2024
 */
@Slf4j
public class CommonPage extends BasePage {

    WebDriver driver;

    public CommonPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(), \"Forms\")]")
    WebElement formsLink;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Practice Form')]")
    WebElement practiceFormLink;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), \"Upload and Download\")]")
    WebElement uploadDownloadLink;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), \"Browser Windows\")]")
    WebElement browserWindowsLink;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), \"Frames\")]")
    WebElement framesLink;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), \"Alerts\")]")
    WebElement alertsLink;

    public void clickFormsLink() {
        scrollAndClick(formsLink);
    }


    public void clickPracticeFormsLink() {
        try {
            practiceFormLink.click();
        } catch (ElementNotInteractableException exception) {
            log.error("got exception: {}", exception.getMessage());
            formsLink.click();
            practiceFormLink.click();
        }

    }

    public void clickUploadDownloadLink() {
        uploadDownloadLink.click();
    }

    public void clickOnBrowserWindowsLink() {
        browserWindowsLink.click();
    }

    public void clickOnFramesLink() {
        framesLink.click();
    }

    public void clickOnAlertsLink() {
        alertsLink.click();
    }


}
