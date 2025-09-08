Feature: Login functionality for Money Control
  As a registered user
  I want to log into the application
  So that I can access my site for Calculators

  Background:
    Given the user is on the Money Control landing page

  @negative
  Scenario: Attempt login with unregistered Email Address
    When the user hovers over "Hello, Login" and clicks on Login
    And the user enters Email Address "primeborn01@gmail.com" and manually try to Get OTP
    Then the user should see an error message "Further instructions have been sent to your email address"

  @negative
  Scenario: Attempt login with invalid Mobile Number (less than 10 digits)
    When the user hovers over "Hello, Login" and clicks on Login
    And the user enters Mobile Number "781422616" and manually try to Get OTP
    Then the user should see an error message "Please enter a valid mobile number or email."
