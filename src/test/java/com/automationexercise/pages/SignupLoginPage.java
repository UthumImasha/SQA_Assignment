package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationexercise.utils.WaitUtils;

public class SignupLoginPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By signupName = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmail = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");

    private final By loginEmail = By.cssSelector("input[data-qa='login-email']");
    private final By loginPassword = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By loginError = By.cssSelector("form[action='/login'] p");

    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void startSignup(String name, String email) {
        wait.waitForVisible(signupName).sendKeys(name);
        driver.findElement(signupEmail).sendKeys(email);
        wait.scrollAndClick(signupButton);
    }

    public void login(String email, String password) {
        wait.waitForVisible(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(password);
        wait.scrollAndClick(loginButton);
    }

    public String loginErrorText() {
        return wait.waitForVisible(loginError).getText();
    }
}
