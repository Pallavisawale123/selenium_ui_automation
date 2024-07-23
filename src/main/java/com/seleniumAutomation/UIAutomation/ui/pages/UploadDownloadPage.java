package com.seleniumAutomation.UIAutomation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 7/23/2024
 */
public class UploadDownloadPage extends CommonPage {

    private WebDriver driver;


    public UploadDownloadPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.ID, using = "downloadButton")
    WebElement downloadButton;
    @FindBy(how = How.ID, using = "uploadFile")
    WebElement uploadInput;

    public void clickOnDownloadButton() {
        downloadButton.click();
    }

    public void setUploadFilePath(String path) {
        uploadInput.sendKeys(path);
    }

}
