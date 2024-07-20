package com.seleniumAutomation.UIAutomation.ui.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 7/20/2024
 */

@Slf4j
@Component
public class AssertHelper {

    public static void assertNotNull(boolean condition, String message) {
        try {
            Assert.assertNotNull(condition);
        } catch (AssertionError error) {
            String errorMsg = "assert true failure";
            log.error("assert true failure: {}", message);
            Assert.fail(errorMsg);
        }
    }

    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition);
            log.info("assert true success");
        } catch (AssertionError error) {
            String errorMsg = "assert true failure";
            log.error("assert true failure: {}", message);
            Assert.fail(errorMsg);
        }
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        try {
            Assert.assertEquals(expected, actual);
            log.info("assert equals success expected : {}, found: {}", expected, actual);
        } catch (AssertionError error) {
            String errorMsg = String.format("assert true failure expected: %s, actual: %s",
                    expected, actual);
            log.error("assert true failure: {}", message);
            Assert.fail(errorMsg);
        }
    }

    public static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition);
            log.info("assert false success");
        } catch (AssertionError error) {
            String errorMsg = "assert false failure";
            log.error("assert false failure: {}", message);
            Assert.fail(errorMsg);
        }
    }
}

