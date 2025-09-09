Feature: Provident Fund Calculator functionality
  As a user
  I want to calculate my Provident Fund
  So that I can plan for retirement

  Background:
    Given the user is on the Money Control Page
    When the user hovers over "Personal Finance" and clicks on "Provident Fund Calculator"

  
  @positive
  Scenario: Successful PF calculation with valid details
    When the user enters Age "25"
    And the user enters Monthly Salary "30000"
    And the user enters Employee Contribution "12"
    And the user enters Employer Contribution "12"
    And the user enters Intended Years of Service "35"
    And the user clicks on Calculate
    Then the user should see the accumulated PF value.

  @positive
  Scenario: Successful PF calculation with changing contribution%
    When the user enters Age "25"
    And the user enters Monthly Salary "30000"
    And the user enters Employee Contribution "19"
    And the user enters Employer Contribution "15"
    And the user enters Intended Years of Service "35"
    And the user clicks on Calculate
    Then the user should see the accumulated PF value contibution changed.
    
    
    
    
