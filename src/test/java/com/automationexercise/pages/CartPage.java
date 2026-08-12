package com.automationexercise.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automationexercise.utils.WaitUtils;

public class CartPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By checkoutButton = By.cssSelector("a.check_out");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    private List<WebElement> rows() {
        wait.waitForVisible(cartRows);
        return driver.findElements(cartRows);
    }

    public int itemCount() {
        return rows().size();
    }

    public String nameOfItem(int index) {
        return rows().get(index).findElement(By.cssSelector(".cart_description h4 a")).getText();
    }

    public String priceOfItem(int index) {
        return rows().get(index).findElement(By.cssSelector(".cart_price p")).getText();
    }

    public String quantityOfItem(int index) {
        return rows().get(index).findElement(By.cssSelector(".cart_quantity button")).getText();
    }

    public String totalOfItem(int index) {
        return rows().get(index).findElement(By.cssSelector(".cart_total p")).getText();
    }

    public void proceedToCheckout() {
        wait.scrollAndClick(checkoutButton);
    }
}
