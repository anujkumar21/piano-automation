package com.piano.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.util.Properties;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static java.io.File.separator;
import static java.lang.System.getProperty;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class BaseTest {
    protected final static String CONFIG_FILE = "config.properties";
    public static Properties prop;
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
    DesiredCapabilities capabilities;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        prop = PropertyFilereader.getProperty(CONFIG_FILE);
        try {
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(PLATFORM_NAME, prop.get("PLATFORM_NAME"));
            capabilities.setCapability(DEVICE_NAME, prop.get("DEVICE_NAME"));
            capabilities.setCapability(PLATFORM_VERSION, prop.get("PLATFORM_VERSION"));
            capabilities.setCapability(NO_RESET, true);
            capabilities.setCapability(APP, getProperty("user.dir") + separator + prop.get("APP"));
            driver = new AndroidDriver<MobileElement>(new URL((String) prop.get("URL")), capabilities);
        } catch (Exception ex) {
            System.out.println("Exception");
            ex.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
