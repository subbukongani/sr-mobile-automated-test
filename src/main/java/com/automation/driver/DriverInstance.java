package com.automation.driver;

import io.appium.java_client.AppiumDriver;


/**
 * DriverInstance manages a single instance of AppiumDriver for the test execution lifecycle.
 */
public class DriverInstance {
    private static DriverInstance instance;
    private AppiumDriver driver;  // Specify type here
    private final DriverGenerator driverGenerator;

    // Private constructor for singleton pattern
    private DriverInstance(String yamlFilePath) {
        this.driverGenerator = new DriverGenerator(yamlFilePath);
        this.driver = driverGenerator.createDriver(); // Initialize the driver here
    }

    /**
     * Get the singleton instance of DriverInstance.
     *
     * @param yamlFilePath the path to the YAML configuration file
     * @return DriverInstance
     */
    public static DriverInstance getInstance(String yamlFilePath) {
        if (instance == null) {
            instance = new DriverInstance(yamlFilePath);
        }
        return instance;
    }

    /**
     * Get the singleton instance with a default YAML configuration.
     *
     * @return DriverInstance
     */
    public static DriverInstance getInstance() {
        return getInstance("androidCapabilities.yaml"); // Default capabilities file
    }

    /**
     * Gets the AppiumDriver instance.
     *
     * @return AppiumDriver<MobileElement> instance
     */
    public AppiumDriver getDriver() {
        return driver; // Driver is initialized in the constructor
    }

    /**
     * Quits the driver and sets it to null.
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
