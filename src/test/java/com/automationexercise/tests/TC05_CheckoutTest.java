package com.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.AccountPage;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.CheckoutPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.PaymentPage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.pages.SignupDetailsPage;
import com.automationexercise.pages.SignupLoginPage;
import com.automationexercise.utils.TestDataGenerator;

public class TC05_CheckoutTest extends BaseTest {

    @Test(description = "TS15: Register, fill the cart, verify checkout address and place an order end to end")
    public void placeOrderEndToEnd() {
        String name = TestDataGenerator.uniqueName();
        String email = TestDataGenerator.uniqueEmail();
        String password = "Test@1234";

        // Register a fresh user, which also exercises the valid login state
        HomePage home = new HomePage(driver);
        home.goToSignupLogin();
        new SignupLoginPage(driver).startSignup(name, email);
        new SignupDetailsPage(driver).completeRegistration(password);
        AccountPage account = new AccountPage(driver);
        Assert.assertTrue(account.accountCreatedText().equalsIgnoreCase("Account Created!"),
                "Account should be created before checkout");
        account.clickContinue();

        // Fill the cart
        ProductsPage products = new ProductsPage(driver);
        products.open();
        products.addFirstProductToCart();
        products.goToCartFromModal();

        // Checkout: the delivery address must match the data used at registration
        new CartPage(driver).proceedToCheckout();
        CheckoutPage checkout = new CheckoutPage(driver);
        String delivery = checkout.deliveryAddressText();
        Assert.assertTrue(delivery.contains("Test User"),
                "Delivery address should carry the registered name. Was:\n" + delivery);
        Assert.assertTrue(delivery.contains("123 Test Street"),
                "Delivery address should carry the registered street. Was:\n" + delivery);
        checkout.writeComment("Automated test order");
        checkout.placeOrder();

        // Simulated payment
        PaymentPage payment = new PaymentPage(driver);
        payment.payWith("Test User", "4111111111111111", "311", "06", "2028");
        Assert.assertTrue(payment.orderPlacedText().equalsIgnoreCase("Order Placed!"),
                "Order placed heading should appear");
        Assert.assertTrue(payment.confirmationText().contains("Congratulations"),
                "Order confirmation message should appear");

        // Cleanup: remove the account created for this run
        home.deleteAccount();
        Assert.assertTrue(account.accountDeletedText().equalsIgnoreCase("Account Deleted!"),
                "Cleanup should delete the test account");
        account.clickContinue();
    }
}
