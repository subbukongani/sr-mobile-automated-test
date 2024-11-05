package com.automation.driver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.automation.config.DriverConfig;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DriverGenerator is responsible for creating and configuring the AppiumDriver instance.
 */
public class DriverGenerator {
    private final DriverConfig config;

    public DriverGenerator(String yamlFilePath) {
        this.config = new DriverConfig(yamlFilePath);  // Load capabilities from YAML
    }

    /**
     * Creates an instance of AppiumDriver with the desired capabilities.
     *
     * @return AppiumDriver<WebElement> instance
     */
    public AppiumDriver createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Populate DesiredCapabilities with W3C-compatible format
        capabilities.setCapability("platformName", config.getProperty("platformName"));
        capabilities.setCapability("appium:deviceName", config.getProperty("deviceName"));
        capabilities.setCapability("appium:automationName", config.getProperty("automationName"));
        capabilities.setCapability("appium:appPackage", config.getProperty("appPackage"));
        capabilities.setCapability("appium:appActivity", config.getProperty("appActivity"));
        capabilities.setCapability("appium:app", config.getProperty("app"));
        capabilities.setCapability("appium:noReset", config.getProperty("noReset"));
        capabilities.setCapability("appium:fullReset", config.getProperty("fullReset"));

        String appiumServerURL;
        try {
            // Ensure the appiumServerURL is a string and not null
            appiumServerURL = (String) config.getProperty("appiumServerURL");
            if (appiumServerURL == null || appiumServerURL.isEmpty()) {
                throw new MalformedURLException("Appium server URL is missing or empty in the configuration.");
            }
            return new AppiumDriver(new URL(appiumServerURL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL specified in configuration", e);
        } catch (ClassCastException e) {
            throw new RuntimeException("Expected a String for Appium server URL, but got something else.", e);
        }
    }
}
