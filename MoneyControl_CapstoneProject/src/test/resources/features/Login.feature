Feature: Login functionality for Money Control
  As a registered user
  I want to log into the application
  So that I can access my site for Calculators

  Background:
    Given the user is on the Money Control landing page

  @positive
  Scenario: Successful login with valid credentials using Phone Number.
    When the user hovers over "Hello, Login" and clicks on Login
    And the user enters Mobile Number "7814226168" and manually clicks Get OTP
    And the user waits and enters OTP manually
    And on entering OTP it auto connects user to the main page.
    Then the user should see his name appended with "Hi Rott" on the top-right corner
  @positive
    Scenario: Successful login with valid credentials using Email Address.
    When the user hovers over "Hello, Login" and clicks on Login
    And the user enters Email Address "rot9on1on1@gmail.com" and manually clicks Get OTP
    And the user waits and enters OTP manually
    And on entering OTP it auto connects user to the main page.
    Then the user should see his name appended with "Hi Rott" on the top-right corner
