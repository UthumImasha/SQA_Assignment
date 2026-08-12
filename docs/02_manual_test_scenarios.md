# Manual Test Scenarios - AutomationExercise

16 test scenarios covering the main user flows. Priorities reflect business impact: anything on the registration, login or purchase path is High. Referenced screenshots are in `docs/screenshots/`.

| ID | Test Scenario | Expected Result | Priority | Screenshot |
|---|---|---|---|---|
| TS01 | Register a new user with valid details and a unique email | Account is created and the user is logged in automatically. Header shows "Logged in as (username)" | High | login_signup.png |
| TS02 | Attempt to register with an email that is already registered | Signup is rejected with the message "Email Address already exist!" | High | login_signup.png |
| TS03 | Login with valid email and password | User is logged in. Header shows "Logged in as (username)" | High | login_signup.png |
| TS04 | Login with an invalid email and password combination | Login fails with the message "Your email or password is incorrect!" | High | login_signup.png |
| TS05 | Logout from a logged in session | User is logged out and redirected to the login page | Medium | |
| TS06 | Delete the logged in user's account | "Account Deleted!" confirmation page is shown and the account can no longer log in | Medium | |
| TS07 | Search for a product using an existing product name | Matching products appear under the "Searched Products" heading and all results relate to the keyword | High | products.png |
| TS08 | Search for a product that does not exist | No products are listed and the page shows no error | Medium | products.png |
| TS09 | Filter products by a category (for example Women > Dress) | Only products of the selected category are displayed | Medium | homepage.png |
| TS10 | Filter products by a brand from the brands sidebar | Only products of the selected brand are displayed | Low | homepage.png |
| TS11 | Open a product's detail page | Product name, category, price, availability, condition and brand are displayed | Medium | product_detail.png |
| TS12 | Add a product to the cart from the products listing | "Added!" modal appears and the cart contains the product with the correct name, price and quantity 1 | High | cart.png |
| TS13 | Set quantity on the product detail page and add to cart | Cart line shows the product with the chosen quantity and the correct line total | Medium | product_detail.png |
| TS14 | Remove a product from the cart | The line disappears and the cart updates (empty cart message if it was the last item) | Medium | cart.png |
| TS15 | Checkout and place an order as a logged in user with a filled cart | Delivery and billing address match the registration data, simulated payment is accepted and "Congratulations! Your order has been confirmed!" is shown | High | |
| TS16 | Submit the contact form with all fields and a file attachment | Browser confirmation dialog appears, and after accepting it the page shows "Success! Your details have been submitted successfully." | Medium | contact_us.png |

## Priority Summary

- High (7): TS01, TS02, TS03, TS04, TS07, TS12, TS15 - registration, login and the purchase path carry direct business impact
- Medium (8): TS05, TS06, TS08, TS09, TS11, TS13, TS14, TS16 - supporting flows and negative paths
- Low (1): TS10 - cosmetic browsing convenience with low failure impact
