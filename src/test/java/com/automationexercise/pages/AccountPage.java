package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationexercise.utils.WaitUtils;

// Confirmation pages shown after creating or deleting an account
public class AccountPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By accountCreatedMsg = By.cssSelector("h2[data-qa='account-created']");
    private final By accountDeletedMsg = By.cssSelector("h2[data-qa='account-deleted']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public String accountCreatedText() {
        return wait.waitForVisible(accountCreatedMsg).getText();
    }

    public String accountDeletedText() {
        return wait.waitForVisible(accountDeletedMsg).getText();
    }

    public void clickContinue() {
        wait.scrollAndClick(continueButton);
    }
}
