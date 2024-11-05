package com.automation.screens;

import com.automation.driver.DriverInstance;
import com.automation.utils.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OnboardingScreen {
    private static final Logger logger = LoggerFactory.getLogger(OnboardingScreen.class);

    private final WaitHelper waitHelper;

    @AndroidFindBy(id = "com.crunchyroll.crunchyroid:id/onboarding_logo")
    WebElement onBoardingLogo;

    @AndroidFindBy(id = "com.crunchyroll.crunchyroid:id/onboarding_log_in")
    WebElement loginButton;

    @AndroidFindBy(id = "com.crunchyroll.crunchyroid:id/onboarding_explore_free_trial_text_view")
    WebElement freeTrailButton;

    @AndroidFindBy(id = "com.crunchyroll.crunchyroid:id/onboarding_main_text")
    WebElement onBoardingMainText;

    @AndroidFindBy(id = "com.crunchyroll.crunchyroid:id/onboarding_create_account")
    WebElement createAccount;

    public OnboardingScreen() {
        logger.info("Initializing OnboardingScreen...");
        AppiumDriver driver = DriverInstance.getInstance().getDriver();
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        logger.info("OnboardingScreen initialized.");
    }

    /**
     * ************************************************************************************
     * *********************** Verification Methods ***************************************
     * ************************************************************************************
     */
    /**
     * Checks if the onboarding logo is displayed on the screen.
     * true if the onboarding logo is visible, false otherwise.
     */
    public void isOnboardingLogoDisplayed() {
        logger.debug("Waiting for onboarding logo to be displayed...");
        waitHelper.waitForVisibility(onBoardingLogo);
        boolean isDisplayed = onBoardingLogo.isDisplayed();
        assertThat("Onboarding Logo is not displaye", isDisplayed, equalTo(true));
        logger.info("Onboarding logo displayed status: {}", true);
    }

    /**
     * Checks if the login button is displayed and enabled.
     */
    public void isLoginButtonDisplayed() {
        logger.debug("Waiting for login button to be displayed...");
        waitHelper.waitForVisibility(loginButton);
        boolean isDisplayed = loginButton.isDisplayed();
        boolean isEnabled = loginButton.isEnabled();
        logger.info("Login button displayed: {}, enabled: {}", isDisplayed, isEnabled);
        assertThat("Login button should be displayed", isDisplayed, equalTo(true));
        assertThat("Login button should be enabled", isEnabled, equalTo(true));
    }

    public void verifyFreeTrailText(String expectedText) {
        logger.debug("waiting for free trail button to be displayed");
        waitHelper.waitForVisibility(freeTrailButton);
        assertThat(freeTrailButton.isDisplayed(), equalTo(true));
        String actualText = freeTrailButton.getText();
        assertThat("Expected Text " + expectedText + " is not same as actual text : " + actualText, actualText.equalsIgnoreCase(expectedText));
        logger.info(" Free Trail text is matched with expected text");
    }

    public void verifyOnBoardingMainText(String expectedText) {
        logger.debug("waiting for onboarding main text to be displayed");
        waitHelper.waitForVisibility(onBoardingMainText);
        assertThat(onBoardingMainText.isDisplayed(), equalTo(true));
        String actualText = onBoardingMainText.getText();
        assertThat("Expected Text " + expectedText + " is not same as actual text : " + actualText, actualText.equalsIgnoreCase(expectedText));
        logger.info(" Onboarding Main text is matched with expected text");
    }

    /**
     * Checks if the login button is displayed and enabled.
     */
    public void verifyCreateAccountButton(String expectedText) {
        logger.debug("Waiting for create account button to be displayed...");
        waitHelper.waitForVisibility(createAccount);
        boolean isDisplayed = createAccount.isDisplayed();
        boolean isEnabled = createAccount.isEnabled();
        String actualText = createAccount.getText();
        assertThat("Expected Text " + expectedText + " is not same as actual text : " + actualText, actualText.equalsIgnoreCase(expectedText));
        logger.info("Create Account button displayed and text matched: {}, enabled: {}", isDisplayed, isEnabled);
        assertThat("Login button should be displayed", isDisplayed, equalTo(true));
        assertThat("Login button should be enabled", isEnabled, equalTo(true));
    }

    /**
     * ************************************************************************************
     * ******************************* Task Methods ***************************************
     * ************************************************************************************
     */

    public void clickOnLogin() {
        logger.debug("Waiting for Login button to be displayed");
        waitHelper.waitForClickable(loginButton);
        loginButton.click();
        logger.info("Clicked on Login");
    }
}
