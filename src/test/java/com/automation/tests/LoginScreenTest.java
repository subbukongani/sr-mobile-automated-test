package com.automation.tests;

import com.automation.screens.LoginScreen;
import org.testng.annotations.Test;

public class LoginScreenTest extends BaseTest{

    @Test
    public void loginScreenVerificationTest() {

        String expectedHeaderSectionText = "Classic anime jams, epic movies, and endless shows. They’re all here!";
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.verifyLoginScreen();
        loginScreen.verifyHeaderSection(expectedHeaderSectionText);
        loginScreen.loginAsUser("premium");
    }
}
