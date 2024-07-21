package com.seleniumAutomation.UIAutomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 7/1/2024
 */

@Slf4j
public class FormPage extends CommonPage{
    WebDriver driver;

    public FormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    @FindBy(how = How.XPATH, using ="//*[contains(text(), \"Student Registration Form\")]" )
    WebElement formMessageHolder;

    @FindBy(how = How.XPATH, using = "//*[@id='firstName']")
    WebElement firstNameInput;

    @FindBy(how = How.ID, using = "lastName")
    WebElement lastNameInput;

    @FindBy(how = How.ID, using = "userEmail")
    WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//*[@id='gender-radio-2']")
    List<WebElement> genderRadio;

    @FindBy(how = How.XPATH, using = "//*[@id='userNumber']")
    WebElement mobileInput;

    @FindBy(how = How.XPATH, using = "//*[@id='submit']")
    WebElement submitButton;

    public void setFirstNameInput(String value) {
        scrollAndSet(firstNameInput, value);
    }

    public boolean isWelcomeMessageDisplayed() {
        return formMessageHolder.isDisplayed();
    }

    public void setLastNameInput(String value) {
        scrollAndSet(lastNameInput, value);
    }

    public void setMobileInput(String value) {
        scrollAndSet(mobileInput, value);
    }

    public void setEmailInput(String value) {
        scrollAndSet(emailInput, value);
    }

    public void selectGender(String value) {
        for (WebElement gender: genderRadio) {
            if (gender.getAttribute("value").trim().equalsIgnoreCase(value)) {
                scrollAndClick(gender);
            }
        }
    }

    public void clickSubmit(){
        scrollAndClick(submitButton);
    }

    public void fillFormWithMandatoryFields(String fname, String lname, String mobile, String gender,
                                            String email) {
        setFirstNameInput(fname);
        setLastNameInput(lname);
        setMobileInput(mobile);
        setEmailInput(email);
        selectGender(gender);
        clickSubmit();
    }

    public void fillFormWithMandatoryFieldsJS(String fname, String lname, String mobile) {
        setFirstNameInputJS(fname);
        setLastNameInputJS(lname);
        setMobileInputJS(mobile);
        clickSubmitJS();
    }

    private void clickSubmitJS() {
        clickByJS(submitButton);
    }

    public void clickByJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public void setValueByJS(WebElement element, String value){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='"+ value +"';", element);
    }

    private void setMobileInputJS(String mobile) {
        setValueByJS(mobileInput, mobile);
    }

    private void setLastNameInputJS(String lname) {
        setValueByJS(lastNameInput, lname);
    }

    private void setFirstNameInputJS(String fname) {
        setValueByJS(firstNameInput, fname);
    }
}