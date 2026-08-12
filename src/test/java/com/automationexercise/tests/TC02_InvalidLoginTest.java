package com.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.SignupLoginPage;
import com.automationexercise.utils.TestDataGenerator;

public class TC02_InvalidLoginTest extends BaseTest {

    @Test(description = "TS04: Login with an invalid email and password combination shows an error")
    public void loginWithInvalidCredentials() {
        HomePage home = new HomePage(driver);
        home.goToSignupLogin();

        SignupLoginPage loginPage = new SignupLoginPage(driver);
        // A freshly generated timestamped email can never belong to a registered account
        loginPage.login(TestDataGenerator.uniqueEmail(), "WrongPass@123");

        Assert.assertEquals(loginPage.loginErrorText(), "Your email or password is incorrect!",
                "Error message should be shown for invalid credentials");
    }
}
