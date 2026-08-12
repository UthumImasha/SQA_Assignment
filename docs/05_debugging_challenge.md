# Debugging Challenge - Synchronization Defect

## 1. The Intentional Failure

To practise root cause analysis, a defect was introduced into `ProductsPage.goToCartFromModal()`, the method every cart flow uses to move from the "Added!" confirmation modal to the cart page. The explicit wait was replaced with a direct lookup and click:

```java
// defective version
public void goToCartFromModal() {
    driver.findElement(viewCartModalLink).click();
}

// correct version
public void goToCartFromModal() {
    wait.scrollAndClick(viewCartModalLink);   // waits for elementToBeClickable
}
```

This mirrors one of the most common real-world Selenium mistakes: interacting with an element that is rendered or revealed by AJAX without waiting for it.

## 2. Observed Symptoms

Running `mvn test` produced two failures out of five tests:

```
TC01_RegistrationTest:  PASS
TC02_InvalidLoginTest:  PASS
TC03_ProductSearchTest: PASS
TC04_AddToCartTest:     FAIL
TC05_CheckoutTest:      FAIL

org.openqa.selenium.ElementNotInteractableException:
element not interactable
  (Session info: chrome=150.0.7871.130)
Driver info: org.openqa.selenium.chrome.ChromeDriver
Command: [..., clickElement ...]
```

The failure screenshots captured automatically by the TestNG listener are in `docs/screenshots/debug_tc04_failure.png` and `debug_tc05_failure.png`: both show the products page with the confirmation modal not yet open.

## 3. Debugging Process

1. **Read both stack traces.** Two different tests failed with the identical exception at the identical frame: `ProductsPage.goToCartFromModal`. When multiple tests fail at one shared method, the defect is almost certainly in the shared page object, not in either test.
2. **Check the failure screenshots.** The listener's screenshots showed the product grid with no modal visible, meaning the click happened before the modal appeared on screen.
3. **Reproduce in isolation.** Running TC04 alone reproduced the failure consistently, ruling out cross-test interference.
4. **Inspect the page in DevTools.** The `#cartModal` element is present in the HTML from the initial page load, but hidden. It only becomes visible when the add-to-cart AJAX response triggers the modal fade-in.
5. **Interpret the exception type.** This is the key clue. Selenium threw `ElementNotInteractableException`, not `NoSuchElementException`. The element was FOUND (it exists in the DOM) but could not be clicked (it was still invisible). `findElement` succeeds immediately and has no patience; the code raced the AJAX response and lost.

## 4. Root Cause

A synchronization defect: present in the DOM is not the same as visible and interactable. The View Cart link exists on page load but is only revealed after an asynchronous response. A direct `findElement().click()` executes before that happens. Any element rendered or revealed by AJAX must be waited for with an explicit condition that describes the required state, in this case `elementToBeClickable`.

## 5. The Fix

Restore the explicit wait through the framework's `WaitUtils.scrollAndClick()`, which waits for clickability and scrolls the element into the viewport centre before clicking. Rerun result:

```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## 6. A Real Failure Diagnosed the Same Way

While the intentional defect was live, an unplanned environmental failure also appeared: TC01 failed with `ElementClickInterceptedException` because a Google ad iframe was overlaying the newsletter checkbox on the signup form:

```
element click intercepted: Element <input type="checkbox" id="newsletter" ...>
is not clickable at point (295, 680). Other element would receive the click:
<iframe id="aswift_2" ... src="https://googleads.g.doubleclick.net/..." title="Advertisement">
```

Screenshot: `docs/screenshots/debug_ad_overlay.png`. The exception text named the exact ad iframe, and the fix was to route the signup form clicks through the same `scrollAndClick()` helper. One discipline, two different failure classes prevented: AJAX races and overlay interception.

## 7. Lessons Learned

- The exception type maps to the element lifecycle: `NoSuchElement` means not in the DOM, `ElementNotInteractable` means in the DOM but not usable, `ElementClickIntercepted` means usable but covered. Reading the type correctly cuts debugging time sharply.
- Failure clustering localizes defects: two tests failing at one shared page-object method points straight at that method. This is a maintenance benefit of the Page Object Model.
- Automatic failure screenshots turn a stack trace into a visual diagnosis in seconds.
