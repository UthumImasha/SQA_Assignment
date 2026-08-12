package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationexercise.utils.WaitUtils;

public class CheckoutPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By deliveryAddress = By.id("address_delivery");
    private final By commentArea = By.cssSelector("textarea[name='message']");
    private final By placeOrderButton = By.cssSelector("a[href='/payment']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public String deliveryAddressText() {
        return wait.waitForVisible(deliveryAddress).getText();
    }

    public void writeComment(String comment) {
        wait.waitForVisible(commentArea).sendKeys(comment);
    }

    public void placeOrder() {
        wait.scrollAndClick(placeOrderButton);
    }
}
