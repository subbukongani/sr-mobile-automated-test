package com.automation.utils;

import com.automation.config.DriverConfig;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriverWait wait;

    public WaitHelper(AppiumDriver driver) {
        int waitTime = DriverConfig.getInstance("androidCapabilities.yaml").getExplicitWaitTime();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
