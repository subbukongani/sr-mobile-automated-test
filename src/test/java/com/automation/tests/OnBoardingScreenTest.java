package com.automation.tests;

import com.automation.screens.OnboardingScreen;
import org.testng.annotations.Test;

public class OnBoardingScreenTest {

    @Test
    public void onBoardingTest() {
        String expectedFreeTrailText = "EXPLORE FREE TRIAL";
        String expectedMainText = "All your favorite anime. All in one place.";
        String expectedCreateAccountText = "or Create Account";

        OnboardingScreen onBoardingScreen = new OnboardingScreen();
        onBoardingScreen.isOnboardingLogoDisplayed();
        onBoardingScreen.isLoginButtonDisplayed();
        onBoardingScreen.verifyFreeTrailText(expectedFreeTrailText);
        onBoardingScreen.verifyOnBoardingMainText(expectedMainText);
        onBoardingScreen.verifyCreateAccountButton(expectedCreateAccountText);
    }
}
