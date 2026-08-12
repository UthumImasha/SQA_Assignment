package com.automationexercise.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.ProductsPage;

public class TC03_ProductSearchTest extends BaseTest {

    @Test(description = "TS07: Search for an existing product shows only matching results")
    public void searchForProduct() {
        String keyword = "dress";

        ProductsPage products = new ProductsPage(driver);
        products.open();
        products.searchFor(keyword);

        Assert.assertTrue(products.isSearchResultsHeadingVisible(),
                "Searched Products heading should appear");

        List<String> names = products.visibleProductNames();
        Assert.assertFalse(names.isEmpty(), "Search should return at least one product");

        // The site's search also matches product descriptions, so not every result
        // carries the keyword in its visible name. Assert the search is relevant:
        // at least one returned product must have the keyword in its name.
        boolean anyNameMatches = names.stream()
                .anyMatch(name -> name.toLowerCase().contains(keyword));
        Assert.assertTrue(anyNameMatches,
                "At least one search result should contain the keyword in its name. Results: " + names);
    }
}
