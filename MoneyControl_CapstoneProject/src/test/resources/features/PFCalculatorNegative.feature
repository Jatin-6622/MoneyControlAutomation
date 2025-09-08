  Feature: Provident Fund Calculator functionality
  As a user
  I want to calculate my Provident Fund
  So that I can plan for retirement

   Background:
    Given the user is on the Money Control Page
    When the user hovers over "Personal Finance" and clicks on "Provident Fund Calculator"

  @negative
  Scenario: Attempt PF calculation, Entering Salary as Zero
    When the user enters Age "24"
    And the user entered Monthly Salary as Zero
    And the user enters Employee Contribution "12"
    And the user enters Employer Contribution "15"
    And the user enters Intended Years of Service "34"
    And the user clicks on Calculate 
    Then the user should see final result as zero.
  
   @negative
  Scenario: Attempt PF calculation, Entering Intended years same as Age
    When the user enters Age "24"
    And the user entered Monthly Salary as Zero
    And the user enters Employee Contribution "12"
    And the user enters Employer Contribution "18"
    And the user enters Intended Years of Service same as Age "24".
    And the user clicks on Calculate
    Then the user should see a Error Msg containing "Please putin retire age correctly."