package com.seleniumAutomation.UIAutomation;

import com.seleniumAutomation.UIAutomation.ui.Reader.ExcelUtil;
import com.seleniumAutomation.UIAutomation.ui.constant.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/26/2024
 */
@SpringBootTest(classes = UiAutomationApplication.class)
@Slf4j

public class TestDataProvider {

    /**
     * @return
     */
    @DataProvider(name = "users")
    public Object[][] getUsers() {
        return new Object[][]{
                {"pallavi", "psawale", "Welcome"},
                {"pooja", "pkhanampure", "Welcome pooja"},
        };
    }


    @Test(dataProvider = "users", description = "get the data ")
    void userLogin(String userName, String password, String welcomeMessage) {
        log.info("username {}, password {} and {}", userName, password, welcomeMessage);
    }

    /**
     * test data provider using excel reader
     *
     * @return
     * @throws IOException
     */
    @DataProvider(name = "excelData")
    public static Object[][] getExcelData() throws IOException {
        String sheetName = "Sheet1";
        return ExcelUtil.getExcelData(Constants.EXCEL_FILE_PATH, sheetName);
    }

    @Test(dataProvider = "excelData")
    public void testWithExcelData(String username, String password) {
        log.info("Username {} and Password {} :" + username, password);
    }
}
