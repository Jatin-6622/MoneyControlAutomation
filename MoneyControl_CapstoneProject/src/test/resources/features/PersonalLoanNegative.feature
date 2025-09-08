Feature: Personal Loan EMI Calculator functionality
  As a user
  I want to calculate my EMI for a personal loan
  So that I can plan my repayments better

  Background:
    Given the user is on the Money Control Site
    When the user hovers over "Personal Finance" and click on "EMI Calculator"
    
  @negative
  Scenario Outline: EMI calculation with invalid loan details
   When the user disables the ads shown in EMI Calculator
    Then the user enters Loan Amount "<loanAmount>"
    And the user enters Interest Rate "<interest>"
    And the user enters Loan Tenure "<tenure>"
    And the user selects "<tenureType>" option
    Then for <loanAmount> error in results is shown containing most of values as Zero

    Examples:
      | loanAmount | interest | tenure | tenureType |
      | 0          | 10       | 5      | Years      |
      | 200000     | 0       | 10     | Years      |
      | 300000     | 12       | 0      | Months     |
