Feature: Personal Loan EMI Calculator functionality
  As a user
  I want to calculate my EMI for a personal loan
  So that I can plan my repayments better

  Background:
    Given the user is on the Money Control Site
    When the user hovers over "Personal Finance" and click on "EMI Calculator"

  @positive
  Scenario Outline: Successful EMI calculation with valid loan details
    When the user disables the ads shown in EMI Calculator
    Then the user enters Loan Amount "<loanAmount>"
    And the user enters Interest Rate "<interest>"
    And the user enters Loan Tenure "<tenure>"
    And the user selects "<tenureType>" option
    Then the user should see the total Amount Payable displayed

    Examples:
      | loanAmount | interest | tenure | tenureType |
      | 500000     | 10       | 5      | Years      |

	@positive
  	Scenario Outline: Successful EMI calculation with valid loan details
    	When the user disables the ads shown in EMI Calculator
    	Then the user enters Loan Amount "<loanAmount>"
    	And the user enters Interest Rate "<interest>"
    	And the user enters Loan Tenure "<tenure>"
    	And the user selects "<tenureType>" option
    	Then the user should see the total Amount Payable display

    Examples:
      | loanAmount | interest | tenure | tenureType |
      | 300000     | 12       | 60     | Months     |
  