package com.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.AccountPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.SignupDetailsPage;
import com.automationexercise.pages.SignupLoginPage;
import com.automationexercise.utils.TestDataGenerator;

public class TC01_RegistrationTest extends BaseTest {

    @Test(description = "TS01: Register a new user with valid details, verify login state, then delete the account")
    public void registerNewUser() {
        String name = TestDataGenerator.uniqueName();
        String email = TestDataGenerator.uniqueEmail();
        String password = "Test@1234";

        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isLoaded(), "Home page should be visible");

        home.goToSignupLogin();
        new SignupLoginPage(driver).startSignup(name, email);
        new SignupDetailsPage(driver).completeRegistration(password);

        AccountPage account = new AccountPage(driver);
        Assert.assertTrue(account.accountCreatedText().equalsIgnoreCase("Account Created!"),
                "Account creation confirmation should appear");
        account.clickContinue();

        Assert.assertEquals(home.loggedInUsername(), name,
                "Header should show the newly registered username");

        home.deleteAccount();
        Assert.assertTrue(account.accountDeletedText().equalsIgnoreCase("Account Deleted!"),
                "Account deletion confirmation should appear");
        account.clickContinue();
    }
}
