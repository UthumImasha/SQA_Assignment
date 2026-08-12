package com.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.ProductsPage;

public class TC04_AddToCartTest extends BaseTest {

    @Test(description = "TS12: Add a product to the cart and verify name, price, quantity and total")
    public void addProductToCart() {
        ProductsPage products = new ProductsPage(driver);
        products.open();

        String expectedName = products.firstProductName();
        String expectedPrice = products.firstProductPrice();

        products.addFirstProductToCart();
        products.goToCartFromModal();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.itemCount(), 1, "Cart should contain exactly one line");
        Assert.assertEquals(cart.nameOfItem(0), expectedName, "Cart item name should match the added product");
        Assert.assertEquals(cart.priceOfItem(0), expectedPrice, "Cart price should match the listing price");
        Assert.assertEquals(cart.quantityOfItem(0), "1", "Quantity should be 1");
        Assert.assertEquals(cart.totalOfItem(0), expectedPrice, "Line total should equal price for quantity 1");
    }
}
