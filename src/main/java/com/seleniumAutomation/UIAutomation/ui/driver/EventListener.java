package com.seleniumAutomation.UIAutomation.ui.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.springframework.stereotype.Component;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/26/2024
 */
@Component
@Slf4j
public class EventListener implements WebDriverListener {
    @Override
    public void beforeGet(WebDriver driver, String url) {
        log.info("opening url: {}", url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        log.info("opened url: {}", url);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        // wait (locator)
        log.info("finding element: {}", locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.info("after finding element: {}", locator);
    }

    @Override
    public void beforeClose(WebDriver driver) {
        log.info("closing browser instance");
    }

    @Override
    public void afterClose(WebDriver driver) {
        log.info("closed browser instance");
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        log.info("closing all browsers");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        log.info("closed all browsers");
    }

    @Override
    public void beforeClick(WebElement element) {
        log.info("clicking on element {}", element.toString());
    }

    @Override
    public void afterClick(WebElement element) {
        log.info("clicked on element {}", element.toString());
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        // wait (element)
        log.info("setting value of {}, to {}", element.toString(), keysToSend);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("after setting value of {}, to {}", element.toString(), keysToSend);
        // check after enter (element, keysToSend)
    }
}
