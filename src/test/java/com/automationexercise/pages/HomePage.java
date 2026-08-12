package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationexercise.utils.WaitUtils;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By siteLogo = By.cssSelector(".logo img");
    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By loggedInAs = By.xpath("//a[contains(., 'Logged in as')]");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By logoutLink = By.cssSelector("a[href='/logout']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public boolean isLoaded() {
        return wait.waitForVisible(siteLogo).isDisplayed();
    }

    public void goToSignupLogin() {
        wait.scrollAndClick(signupLoginLink);
    }

    public String loggedInUsername() {
        return wait.waitForVisible(loggedInAs).getText().replace("Logged in as", "").trim();
    }

    public void deleteAccount() {
        wait.scrollAndClick(deleteAccountLink);
    }

    public void logout() {
        wait.scrollAndClick(logoutLink);
    }
}
