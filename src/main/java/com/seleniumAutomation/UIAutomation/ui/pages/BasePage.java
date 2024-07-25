package com.seleniumAutomation.UIAutomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 7/20/2024
 */
@Slf4j
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clearAndSet(WebElement element , String value){
        element.clear();
        element.sendKeys(value);
    }

    public void scrollAndSet(WebElement element , String value){
        element.click();
        Actions action  = new Actions(driver);
        action.moveToElement(element).sendKeys(value).perform();
    }

    void scrollAndClick(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void switchToFrame(WebElement frameLocator) {
        driver.switchTo().frame(frameLocator);
    }

    public void switchToParentFrame() {
        driver.switchTo().defaultContent();
    }

}


