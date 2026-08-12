package com.automationexercise.utils;

public class TestDataGenerator {

    // Shared database rejects duplicate emails, so every run gets a fresh one
    public static String uniqueEmail() {
        return "user" + System.currentTimeMillis() + "@testmail.com";
    }

    public static String uniqueName() {
        return "TestUser" + (System.currentTimeMillis() % 100000);
    }
}
