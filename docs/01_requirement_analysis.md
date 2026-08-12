# Requirement Analysis - AutomationExercise

**Application URL:** https://automationexercise.com
**Analysed on:** 12 August 2026, desktop Chrome

## 1. Application Overview

AutomationExercise is a demo e-commerce web application that presents itself as a "Full-Fledged practice website for Automation Engineers". It behaves like a typical online clothing store for women, men and kids, with the standard flows of a real shop: user registration and login, product browsing and search, a shopping cart, a checkout with a simulated payment step, and a contact form. Because the site is intentionally built for QA practice, it is safe to create test accounts and place test orders on it.

## 2. Objectives of This Analysis

- Understand the main user flows before designing any test scenarios
- Identify which features are stable and testable, and which ones carry automation risk
- Collect the information needed to justify automation candidates later in the project

## 3. Target Users

| User type | What they can do |
|---|---|
| Guest visitor | Browse and search products, view product details, add items to cart, subscribe to the newsletter, send a contact message |
| Registered customer | Everything a guest can do, plus login/logout, checkout and place orders, download an invoice, delete their own account |

There is no admin-facing UI exposed, so administration is out of scope for testing.

## 4. Key Features Observed

| Feature area | Details |
|---|---|
| Authentication | Signup starts with name and email, then a full account information form (title, password, date of birth, address, mobile number). Login uses email and password. Logout and account deletion are available from the header. There is no email verification step. |
| Product catalog | 34 products with name and price (Rs.). Category sidebar (Women, Men, Kids with subcategories) and brand sidebar (Polo, H&M, Madame, Mast & Harbour, Babyhug and others). Product detail page shows availability, condition and brand. |
| Search | Search box on the Products page. Results appear under a "Searched Products" heading. |
| Cart | Items can be added from the listing or the detail page (quantity can be set on the detail page). The cart page lists each line with price, quantity and total. Items can be removed. Checkout prompts guests to login or register. |
| Checkout and payment | Address review (delivery and billing), optional order comment, then a payment form (name on card, card number, CVC, expiry). Payment is simulated and always succeeds. Order confirmation offers an invoice download. |
| Contact form | Name, email, subject, message and an optional file upload. Submitting shows a JavaScript confirmation dialog and then a success message. |
| Newsletter subscription | Email field in the footer of every page with a success alert on submit. |
| Extra pages | A Test Cases reference page, an API endpoint list for API testing practice, and external video tutorials. |

## 5. Assumptions

- The site remains available and unchanged for the duration of the assignment
- Payment is simulated, so no real transaction can happen
- Creating and deleting test accounts is acceptable use, since the site exists for practice
- Registered emails persist in a shared database, so every test run must use a fresh unique email
- Testing is done in English on desktop Chrome

## 6. Risks and Constraints

| Risk | Impact on testing | Mitigation |
|---|---|---|
| Third party ads load over the page and can sit on top of buttons | Automated clicks can be intercepted and fail | Scroll the target into view before clicking; interact with elements directly rather than by position |
| Shared public database that other testers modify constantly | Data created today may be changed or deleted by someone else | Each test creates its own account and data instead of relying on existing records |
| Free demo site with no SLA, occasionally slow | Fixed waits become flaky | Use explicit waits with reasonable timeouts instead of sleeps |
| Duplicate email registration is rejected | A reused email breaks the signup test | Generate a unique timestamped email for every run |
| Temporary downtime is possible | Whole suite fails for environmental reasons | Rerun later; document the dependency on site availability |

## 7. Observations Relevant to Automation

- Most interactive elements expose stable `data-qa` attributes (for example `data-qa="login-email"`), which allows a reliable locator strategy
- Adding to cart opens an AJAX modal ("Added!"), so the framework needs explicit waits around it
- Forms rely on HTML5 `required` validation for empty-field cases, which limits how much server-side validation can be observed
- The footer subscription box and its success alert exist on every page, so it can be tested from anywhere
- The site layout has not changed for years (footer still says 2021), which lowers the risk of locators breaking mid-project
