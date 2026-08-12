package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationexercise.utils.WaitUtils;

public class PaymentPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By nameOnCard = By.cssSelector("input[data-qa='name-on-card']");
    private final By cardNumber = By.cssSelector("input[data-qa='card-number']");
    private final By cvc = By.cssSelector("input[data-qa='cvc']");
    private final By expiryMonth = By.cssSelector("input[data-qa='expiry-month']");
    private final By expiryYear = By.cssSelector("input[data-qa='expiry-year']");
    private final By payButton = By.cssSelector("button[data-qa='pay-button']");
    private final By orderPlacedHeading = By.cssSelector("h2[data-qa='order-placed']");
    private final By confirmationMessage = By.xpath("//h2[@data-qa='order-placed']/following-sibling::p");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // Payment is simulated by the site, any card details are accepted
    public void payWith(String name, String number, String cvcCode, String month, String year) {
        wait.waitForVisible(nameOnCard).sendKeys(name);
        driver.findElement(cardNumber).sendKeys(number);
        driver.findElement(cvc).sendKeys(cvcCode);
        driver.findElement(expiryMonth).sendKeys(month);
        driver.findElement(expiryYear).sendKeys(year);
        wait.scrollAndClick(payButton);
    }

    public String orderPlacedText() {
        return wait.waitForVisible(orderPlacedHeading).getText();
    }

    public String confirmationText() {
        return wait.waitForVisible(confirmationMessage).getText();
    }
}
