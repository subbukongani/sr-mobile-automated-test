package com.automation.screens;

import com.automation.config.DriverConfig;
import com.automation.driver.DriverInstance;
import com.automation.utils.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginScreen {

    private static final Logger logger = LoggerFactory.getLogger(LoginScreen.class);

    private final WaitHelper waitHelper;
    OnboardingScreen onboardingScreen = new OnboardingScreen();

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in']")
    WebElement loginScreen;

    @AndroidFindBy(xpath = "//*[@resource-id='header']")
    WebElement headerSection;

    @AndroidFindBy(xpath = "//*[@resource-id='email_phone_input']")
    WebElement emailField;

    @AndroidFindBy(xpath = "//*[@resource-id='primary_button']")
    WebElement emailNext;

    @AndroidFindBy(xpath = "//*[@resource-id='password_input']")
    WebElement passwordField;

    @AndroidFindBy(xpath = "//*[@resource-id='manage_profiles_button']")
    WebElement manageProfilesButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='image' and @enabled='true']")
    WebElement profile;

    public LoginScreen() {
        if (onboardingScreen.loginButton.isDisplayed()) {
            onboardingScreen.clickOnLogin();
        }
        logger.debug("Initializing Login Screen");
        AppiumDriver driver = DriverInstance.getInstance().getDriver();
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        logger.info("Login Screen Initialized");
    }

    public void verifyLoginScreen() {
        logger.debug("Waiting for login screen to be displayed");
        waitHelper.waitForVisibility(loginScreen);
        assertThat("Login screen is not displayed", loginScreen.isDisplayed(), equalTo(true));
        logger.info("Login screen is displayed");
    }

    public void verifyHeaderSection(String expectedText) {
        logger.debug("Waiting for header section to be displayed");
        waitHelper.waitForVisibility(headerSection);
        assertThat(headerSection.isDisplayed(), equalTo(true));
        String actualText = headerSection.getText();
        assertThat(String.format("Expected Text: '%s' is not the same as Actual Text: '%s'", expectedText, actualText),
                actualText,
                equalTo(expectedText));
        logger.info("Verified that header section is displayed anthe text is matched");
    }

    /**
     * Logs in a user based on user type (free or premium).
     *
     * @param userType The type of user to log in (either "free" or "premium").
     */
    public void loginAsUser(String userType) {
        DriverConfig config = DriverConfig.getInstance("credentials.yaml");// Load credentials YAML
        Map<String, String> credentials = config.getUserCredentials(userType);

        if (!credentials.isEmpty()) {
            String email = credentials.get("email");
            String password = credentials.get("password");
            logger.info("Logging in with email: {}", email);
            emailField.sendKeys(email); // Enter username
            emailNext.click();
            passwordField.sendKeys(password);
            emailNext.click();
            logger.info("Next button clicked for email or phone: {}", email);
            assertThat("Manage Profiles screen is not displayed", manageProfilesButton.isDisplayed(), equalTo(true));
            profile.click();
        } else {
            logger.error("Unable to proceed next. No credentials found for user type: {}", userType);
        }
    }


}
