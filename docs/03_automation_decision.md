# Automation Scenario Selection - AutomationExercise

## Selection Criteria

A scenario earns automation when it meets most of these:

1. High business impact (failure blocks registration, login or purchasing)
2. Needed repeatedly as a regression check, not just once
3. Deterministic outcome with a clear success or error message to assert
4. Technically feasible with Selenium (no captcha, no email verification, no visual judgement)
5. Benefits from generated test data (for example unique emails per run)

## Selected for Automation (5)

| ID | Scenario | Why it was selected |
|---|---|---|
| TS01 | Register a new user with valid details | The entry point for every customer. Long form with many fields makes manual regression slow and error prone, while automation with a generated unique email runs it identically every time. Ends by deleting the account, so it also cleans up after itself. |
| TS04 | Login with invalid credentials | Protects the account security path. Completely stable to automate: needs no pre-existing data, asserts an exact error message, and can never be broken by other testers on the shared database. |
| TS07 | Search for an existing product | Core discovery feature used on every visit. Deterministic result heading to assert, and easily extended later with more keywords through data-driven testing. |
| TS12 | Add a product to the cart | First step of the revenue path. Verifies name, price and quantity in the cart, which is exactly the kind of repetitive detail checking where humans make mistakes and scripts do not. |
| TS15 | Checkout and place an order | The highest value end-to-end flow: login, cart, address verification, simulated payment and order confirmation in one script. Catches integration breakage that single-page tests cannot see. |

Note: the valid login scenario (TS03) is not selected as a standalone script because TS15 performs a real login as its first step, so the positive login path is already exercised on every suite run. Selecting it separately would duplicate coverage inside the five-scenario limit.

## Not Selected (11)

| ID | Scenario | Why it was not selected |
|---|---|---|
| TS02 | Register with an already registered email | Needs a known pre-existing account, but the shared public database lets other testers delete it at any time, making the test unreliable. |
| TS03 | Login with valid credentials | Covered implicitly as the first step of the automated checkout flow (see note above). |
| TS05 | Logout | Trivial single click with low defect probability; session end is also implicitly verified when tests finish. |
| TS06 | Delete account | Runs as the cleanup step inside the registration script rather than as a scenario of its own. |
| TS08 | Search with no results | Low-risk negative variant of TS07; better added later as an extra data row of the same script than as one of the five slots. |
| TS09 | Filter by category | Navigation-only flow with weak assertions; product-to-category mapping is owned by the site and can change without notice. |
| TS10 | Filter by brand | Same reasoning as TS09 and the lowest priority scenario in the list. |
| TS11 | View product details | Largely a visual and content presentation check, which a human judges better than string assertions. |
| TS13 | Add to cart with custom quantity | Marginal extra coverage over TS12; a future data-driven extension of the same cart script. |
| TS14 | Remove product from cart | Medium value state manipulation; deferred because the five slots are reserved for the flows with direct revenue impact. |
| TS16 | Contact form with attachment | Automatable, but it is a Medium priority support flow with no revenue impact, so it loses the slot to the checkout flow. |
