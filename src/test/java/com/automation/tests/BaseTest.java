package com.automation.tests;

import com.automation.driver.DriverInstance;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * BaseTest serves as the base class for all test classes.
 * It initializes the Appium driver and cleans it up after tests.
 */
public class BaseTest {
    // Logger for logging test actions
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    // Holds the instance of the Appium driver
    protected AppiumDriver driver;

    // Instance of DriverInstance to manage driver lifecycle
    protected DriverInstance driverInstance;

    @BeforeClass
    public void setUp() {
        logger.info("Initializing DriverInstance with configuration from 'androidCapabilities.yaml'...");

        // Initialize DriverInstance with YAML configuration file
        driverInstance = DriverInstance.getInstance("androidCapabilities.yaml");

        // Retrieve the Appium driver and store it in the driver variable
        driver = driverInstance.getDriver();

        // Log the successful driver initialization
        if (driver != null) {
            logger.info("Appium driver initialized successfully.");
        } else {
            logger.error("Failed to initialize the Appium driver.");
            throw new RuntimeException("Appium driver initialization failed.");
        }
    }

    @AfterClass
    public void tearDown() {
        logger.info("Quitting the Appium driver...");

        // Quit the driver after tests to clean up resources
        if (driverInstance != null) {
            driverInstance.quitDriver();
            logger.info("Appium driver quit successfully.");
        } else {
            logger.warn("DriverInstance is null. Cannot quit driver.");
        }
    }
}
