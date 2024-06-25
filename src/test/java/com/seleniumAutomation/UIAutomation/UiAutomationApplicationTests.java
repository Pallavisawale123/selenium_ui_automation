package com.seleniumAutomation.UIAutomation;

import com.seleniumAutomation.UIAutomation.Reader.ConfigReader;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes= UiAutomationApplication.class)
class UiAutomationApplicationTests {


	ConfigReader configReader =new ConfigReader();

	@Test
	void contextLoads() {
		String ss = configReader.getProperty("src/test/resources/application.properties","base.url");
		System.out.println("********************"+ss);
	}


}
