package com.automationexercise.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automationexercise.utils.WaitUtils;

public class ProductsPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By allProductsHeading = By.xpath("//h2[normalize-space()='All Products']");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsHeading = By.xpath("//h2[normalize-space()='Searched Products']");
    // Scoped to product-image-wrapper so injected ad blocks in the grid are not picked up
    private final By productNames = By.cssSelector(".features_items .product-image-wrapper .productinfo p");
    private final By productPrices = By.cssSelector(".features_items .product-image-wrapper .productinfo h2");
    private final By addToCartButton = By.cssSelector(".features_items .product-image-wrapper .productinfo a.add-to-cart");
    private final By viewCartModalLink = By.cssSelector("#cartModal a[href='/view_cart']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void open() {
        wait.scrollAndClick(productsLink);
        wait.waitForVisible(allProductsHeading);
    }

    public void searchFor(String keyword) {
        wait.waitForVisible(searchInput).sendKeys(keyword);
        wait.scrollAndClick(searchButton);
    }

    public boolean isSearchResultsHeadingVisible() {
        return wait.waitForVisible(searchedProductsHeading).isDisplayed();
    }

    public List<String> visibleProductNames() {
        wait.waitForVisible(productNames);
        return driver.findElements(productNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String firstProductName() {
        return wait.waitForVisible(productNames).getText();
    }

    public String firstProductPrice() {
        return wait.waitForVisible(productPrices).getText();
    }

    public void addFirstProductToCart() {
        wait.scrollAndClick(addToCartButton);
    }

    // The "Added!" confirmation modal is loaded with AJAX after the add click
    public void goToCartFromModal() {
        wait.scrollAndClick(viewCartModalLink);
    }
}
