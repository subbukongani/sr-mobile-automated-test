package com.automation.config;

import org.yaml.snakeyaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * DriverConfig is a singleton class responsible for loading and providing
 * configuration properties from a YAML file.
 * It supports reading driver configuration settings for Appium.
 */
public class DriverConfig {
    private static final Logger logger = LoggerFactory.getLogger(DriverConfig.class);

    // Store instances by YAML file paths
    private static final Map<String, DriverConfig> instances = new HashMap<>();

    // Map to hold configuration properties loaded from the YAML file
    private final Map<String, Object> properties;

    /**
     * Private constructor to enforce singleton pattern and load config.
     *
     * @param yamlFilePath Path to the YAML configuration file.
     */
    public DriverConfig(String yamlFilePath) {
        properties = new HashMap<>();
        loadConfig(yamlFilePath);
    }

    /**
     * Provides access to the singleton instance of DriverConfig.
     * If the instance does not exist for the given file, it will be created.
     *
     * @param yamlFilePath Path to the YAML configuration file.
     * @return The singleton instance of DriverConfig for the specified file.
     */
    public static DriverConfig getInstance(String yamlFilePath) {
        return instances.computeIfAbsent(yamlFilePath, DriverConfig::new);
    }

    /**
     * Loads configuration properties from the specified YAML file.
     *
     * @param yamlFilePath Path to the YAML configuration file.
     */
    private void loadConfig(String yamlFilePath) {
        logger.info("Loading configuration from file: {}", yamlFilePath);
        Yaml yaml = new Yaml();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(yamlFilePath)) {
            if (input == null) {
                logger.error("Configuration file not found: {}", yamlFilePath);
                throw new RuntimeException("Could not find configuration file: " + yamlFilePath);
            }
            // Load properties into the map
            properties.putAll(yaml.load(input));
            logger.info("Configuration loaded successfully from file: {}", yamlFilePath);
        } catch (Exception e) {
            logger.error("Failed to load configuration from YAML file: {}", yamlFilePath, e);
            throw new RuntimeException("Failed to load configuration from YAML file", e);
        }
    }

    /**
     * Retrieves a property value by key from the loaded properties.
     *
     * @param key The property key to look up.
     * @return The value associated with the specified key.
     */
    public Object getProperty(String key) {
        Object value = properties.get(key);
        if (value != null) {
            logger.debug("Property retrieved: {} = {}", key, value);
        } else {
            logger.warn("Property not found for key: {}", key);
        }
        return value;
    }

    /**
     * Gets the explicit wait time for the driver.
     * If the value is not set in the configuration, a default of 15 seconds is returned.
     *
     * @return The explicit wait time in seconds.
     */
    public int getExplicitWaitTime() {
        int waitTime = Integer.parseInt(properties.getOrDefault("explicitWaitTime", "15").toString());
        logger.debug("Explicit wait time retrieved: {} seconds", waitTime);
        return waitTime;
    }

    /**
     * Retrieves user credentials from the loaded properties.
     *
     * @param userType The type of user (e.g., "free" or "premium").
     * @return A map containing the username and password for the specified user type, or an empty map if not found.
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getUserCredentials(String userType) {
        Map<String, String> credentials = new HashMap<>();
        Map<String, Object> users = (Map<String, Object>) properties.get("users");

        if (users != null && users.containsKey(userType)) {
            Map<String, String> userDetails = (Map<String, String>) users.get(userType);
            if (userDetails != null) {
                credentials.put("email", userDetails.getOrDefault("email", ""));
                credentials.put("password", userDetails.getOrDefault("password", ""));
                logger.debug("Retrieved credentials for {}: {}", userType, credentials);
            } else {
                logger.warn("User details not found for user type: {}", userType);
            }
        } else {
            logger.warn("No credentials found for user type: {}", userType);
        }
        return credentials;
    }


}
