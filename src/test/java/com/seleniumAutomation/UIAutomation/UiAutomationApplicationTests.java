package com.seleniumAutomation.UIAutomation;

import com.seleniumAutomation.UIAutomation.ui.Reader.ConfigReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest(classes= UiAutomationApplication.class)
@Slf4j
class UiAutomationApplicationTests {


	ConfigReader configReader =new ConfigReader();

	@BeforeMethod
	void contextLoads() {
		String fileName = configReader.getProperty("src/test/resources/application.properties","base.url");
		System.out.println(fileName + "Demo test");
	}

}
