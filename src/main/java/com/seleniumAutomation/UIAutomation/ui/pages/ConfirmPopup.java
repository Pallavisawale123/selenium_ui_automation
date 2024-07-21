package com.seleniumAutomation.UIAutomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
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
public class ConfirmPopup extends BasePage {
    private WebDriver driver;

    public ConfirmPopup(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[contains(text(), \"Thanks for submitting the form\")]")
    WebElement confirmationMessage;

    @FindBy(how = How.ID, using = "closeLargeModal")
    WebElement closeButton;

    public boolean isConfirmMessageVisible() {
        return confirmationMessage.isDisplayed();
    }

    public void clickCloseButton () {
        scrollAndClick(closeButton);
    }

    public String getAttributeValue(String labelName) {
        String valueLocator = String.format("//tbody//tr//td[contains(text(), \"%s\")]//following-sibling::td",
                labelName);
        WebElement value = driver.findElement(By.xpath(valueLocator));
        return value.getText().trim();
    }

}
