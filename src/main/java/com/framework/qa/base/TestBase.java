package com.framework.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.framework.qa.utils.TestUtil;
import com.framework.qa.utils.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ChromeOptions coptions;
	public static FirefoxOptions foptions;
	public static EdgeOptions moptions;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fin = new FileInputStream("src/main/java/com/framwork/qa/config/config.properties");
			prop.load(fin);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialisation() {
		String browserName = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		boolean headlessFlag = Boolean.parseBoolean(prop.getProperty("headless"));
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();			
			coptions = new ChromeOptions();
			coptions.setHeadless(headlessFlag);
			driver = new ChromeDriver(coptions);
			
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			foptions = new FirefoxOptions();
			foptions.setHeadless(headlessFlag);
			driver = new FirefoxDriver(foptions);
		}else if(browserName.equals("medge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();	
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_TIME_OUT, TimeUnit.SECONDS);

		driver.get(URL);

	}

}
