package com.seleniumAutomation.UIAutomation.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/26/2024
 */
public class TestRetry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
