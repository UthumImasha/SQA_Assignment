package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.automationexercise.utils.WaitUtils;

public class SignupDetailsPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By titleMr = By.id("id_gender1");
    private final By passwordField = By.cssSelector("input[data-qa='password']");
    private final By daySelect = By.cssSelector("select[data-qa='days']");
    private final By monthSelect = By.cssSelector("select[data-qa='months']");
    private final By yearSelect = By.cssSelector("select[data-qa='years']");
    private final By newsletterCheckbox = By.id("newsletter");
    private final By offersCheckbox = By.id("optin");
    private final By firstName = By.cssSelector("input[data-qa='first_name']");
    private final By lastName = By.cssSelector("input[data-qa='last_name']");
    private final By company = By.cssSelector("input[data-qa='company']");
    private final By address = By.cssSelector("input[data-qa='address']");
    private final By country = By.cssSelector("select[data-qa='country']");
    private final By state = By.cssSelector("input[data-qa='state']");
    private final By city = By.cssSelector("input[data-qa='city']");
    private final By zipcode = By.cssSelector("input[data-qa='zipcode']");
    private final By mobileNumber = By.cssSelector("input[data-qa='mobile_number']");
    private final By createAccountBtn = By.cssSelector("button[data-qa='create-account']");

    public SignupDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // Fills the account information and address form with fixed test data,
    // only the password varies per run
    public void completeRegistration(String password) {
        wait.waitForVisible(titleMr).click();
        driver.findElement(passwordField).sendKeys(password);
        new Select(driver.findElement(daySelect)).selectByValue("15");
        new Select(driver.findElement(monthSelect)).selectByVisibleText("June");
        new Select(driver.findElement(yearSelect)).selectByValue("1998");
        driver.findElement(newsletterCheckbox).click();
        driver.findElement(offersCheckbox).click();
        driver.findElement(firstName).sendKeys("Test");
        driver.findElement(lastName).sendKeys("User");
        driver.findElement(company).sendKeys("UCSC");
        driver.findElement(address).sendKeys("123 Test Street");
        new Select(driver.findElement(country)).selectByVisibleText("Canada");
        driver.findElement(state).sendKeys("Ontario");
        driver.findElement(city).sendKeys("Toronto");
        driver.findElement(zipcode).sendKeys("M5V2T6");
        driver.findElement(mobileNumber).sendKeys("0771234567");
        wait.scrollAndClick(createAccountBtn);
    }
}
