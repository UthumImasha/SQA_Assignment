# AutomationExercise - Selenium Test Automation Framework

Automated UI testing project for [automationexercise.com](https://automationexercise.com), built for the UCSC QA Automation practical assignment. Five selected scenarios are automated with Java, Selenium WebDriver, TestNG and the Page Object Model.

## Tech Stack

- Java 17
- Selenium WebDriver 4.33.0 (Selenium Manager handles the browser driver automatically)
- TestNG 7.11.0
- Maven with the Surefire plugin
- Page Object Model (POM) design pattern

## Prerequisites

- JDK 17 or newer
- Maven 3.9+
- Google Chrome (any recent version; no chromedriver download needed)

## How to Run

```
git clone git@github.com:UthumImasha/SQA_Assignment.git
cd SQA_Assignment
mvn test
```

The suite opens a visible Chrome window by default. To run headless, set `headless=true` in `src/test/resources/config.properties`. Base URL and wait timeout live in the same file.

## What Gets Tested

| Test | Scenario |
|---|---|
| TC01 | User registration end to end, with account cleanup |
| TC02 | Login with invalid credentials shows the correct error |
| TC03 | Product search returns relevant results |
| TC04 | Add to cart verifies name, price, quantity and total |
| TC05 | Full checkout: register, cart, address verification, payment, order confirmation |

## Project Structure

```
src/test/java/com/automationexercise/
├── base/        DriverFactory, BaseTest (browser lifecycle per test)
├── pages/       One page object per site page (locators + actions)
├── tests/       TC01 to TC05, one class per automated scenario
├── utils/       Config reader, explicit waits, screenshots, test data
└── listeners/   Automatic screenshot capture on test failure
src/test/resources/
├── config.properties
└── testng.xml
```

## Reports and Evidence

- TestNG HTML report: `test-output/` after a run
- Failure screenshots: `screenshots/` (captured automatically by the listener)
- Project documentation: [`docs/`](docs/)
  - [Requirement analysis](docs/01_requirement_analysis.md)
  - [Manual test scenarios](docs/02_manual_test_scenarios.md)
  - [Automation decision](docs/03_automation_decision.md)
  - [Framework design](docs/04_framework_design.md)
  - [Debugging challenge](docs/05_debugging_challenge.md)
- [AI usage declaration](AI_USAGE.md)
