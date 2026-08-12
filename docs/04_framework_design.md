# Framework Design - Selenium + TestNG + Page Object Model

## 1. Architecture Overview

The framework separates WHAT is tested (test classes) from HOW pages are operated (page objects) and from cross-cutting plumbing (base, utils, listeners). Tests read like the manual scenarios; only page objects know locators.

```
                 +--------------------------+
                 |  tests                   |   TC01..TC05, one class per
                 |  (flow + assertions)     |   selected scenario
                 +------------+-------------+
                              | calls
                 +------------v-------------+
                 |  pages                   |   one class per page,
                 |  (locators + actions)    |   locators kept private
                 +------------+-------------+
                              | drives
                 +------------v-------------+
                 |  Selenium WebDriver      |   Chrome via DriverFactory
                 +------------+-------------+
                              |
                    automationexercise.com

  supporting packages used by all layers:
  base (driver lifecycle)  utils (config, waits, data, screenshots)  listeners (failure hooks)
```

## 2. Project Structure

```
SQA_Assignment/
├── pom.xml
├── src/test/java/com/automationexercise/
│   ├── base/
│   │   ├── DriverFactory.java
│   │   └── BaseTest.java
│   ├── pages/
│   │   ├── HomePage.java
│   │   ├── SignupLoginPage.java
│   │   ├── SignupDetailsPage.java
│   │   ├── AccountPage.java
│   │   ├── ProductsPage.java
│   │   ├── CartPage.java
│   │   ├── CheckoutPage.java
│   │   └── PaymentPage.java
│   ├── tests/
│   │   ├── TC01_RegistrationTest.java
│   │   ├── TC02_InvalidLoginTest.java
│   │   ├── TC03_ProductSearchTest.java
│   │   ├── TC04_AddToCartTest.java
│   │   └── TC05_CheckoutTest.java
│   ├── utils/
│   │   ├── ConfigReader.java
│   │   ├── WaitUtils.java
│   │   ├── ScreenshotUtil.java
│   │   └── TestDataGenerator.java
│   └── listeners/
│       └── TestListener.java
└── src/test/resources/
    ├── config.properties
    └── testng.xml
```

Everything lives under `src/test/java` because this project is a test framework; there is no production code to place in `src/main`.

## 3. Package Explanations

### base
- **DriverFactory** creates and configures the ChromeDriver (window size, notification blocking, optional headless mode from config). Selenium Manager downloads the matching driver binary automatically, so no manual driver management is needed.
- **BaseTest** is the parent of every test class. Its `@BeforeMethod` starts a fresh browser and opens the base URL; `@AfterMethod` quits the browser. A fresh browser per test keeps tests independent: no test can leak cookies, sessions or cart state into the next one.

### pages
One class per page of the application, following the Page Object Model. Each class holds its locators as private `By` fields and exposes public methods named after user actions (`loginWith(email, password)`, `searchFor(product)`, `addFirstProductToCart()`). Tests never touch a locator directly. When the UI changes, only the one page class is edited and every test using it is fixed at once. Locator priority: `data-qa` attributes first (added by the site specifically for testers), then `id`, then stable CSS selectors. Layout-based XPath is avoided because it breaks on cosmetic changes.

### tests
One TestNG class per selected scenario (TC01 to TC05). A test method contains only the flow and its assertions, so it reads like the manual scenario it automates. Assertions use TestNG's `Assert` with clear failure messages.

### utils
- **ConfigReader** loads `config.properties` once and serves values (base URL, browser, timeout seconds, headless flag). Changing the environment or timeouts requires no code change.
- **WaitUtils** wraps `WebDriverWait` with explicit waits (visibility, clickability). The framework contains no `Thread.sleep`, because fixed sleeps are either too slow or too short; explicit waits poll until the condition is met or a timeout fails the test.
- **ScreenshotUtil** captures a PNG into `screenshots/` with a test name and timestamp.
- **TestDataGenerator** builds unique run data, most importantly a timestamped email (`user<epochMillis>@testmail.com`) so registration never collides with existing accounts on the shared database.

### listeners
- **TestListener** implements TestNG's `ITestListener`. On test failure it calls ScreenshotUtil, so every failure leaves a screenshot as evidence next to the stack trace. It is registered in `testng.xml`, keeping test code free of reporting concerns.

### resources
- **config.properties** externalizes environment settings.
- **testng.xml** defines the suite and the execution order of the five test classes, and registers the listener. Running `mvn test` executes this suite through the Surefire plugin.

## 4. Key Design Decisions

| Decision | Reason |
|---|---|
| Fresh browser per test method | Test independence; one failure cannot cascade into others |
| Explicit waits only, no sleeps | Stable on a demo site with variable response times |
| `data-qa` first locator strategy | Provided by the site for testers, survives styling changes |
| Config in properties file | Base URL and timeouts changeable without touching code |
| Unique generated email per run | Shared public database rejects duplicate registrations |
| Screenshot on failure via listener | Debugging evidence without cluttering test logic |
