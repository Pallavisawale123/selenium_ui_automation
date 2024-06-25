package com.seleniumAutomation.UIAutomation.Reader;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/24/2024
 */
@Component
public class ConfigReader {
//    private  Properties properties;

    public String getProperty(String filePath ,String key) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
