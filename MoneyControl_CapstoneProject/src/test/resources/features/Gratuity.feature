Feature: Gratuity Calculator functionality
  As a user
  I want to calculate my gratuity
  So that I can know my retirement benefits

  Background:
    Given the user is on the Money Control site
    When the user hovers over "Personal Finance" and clicked on "More Calculators"
    And the user clicks on "Gratuity Calculator"

  @positive
  Scenario Outline: Calculate gratuity with different salary and tenure
    When the user clicks "Yes" tag for eligibility
    And the user enters Salary "<salary>"
    And the user enters Years "<years>"
    And the user enters Months "<months>"
    And the user clicks Submit Button
    Then the user should see the calculated gratuity amount for "<salary>"

    Examples:
      | salary   | years | months |
      | 50000    | 5     | 6      |
      | 100000   | 15    | 3      |
@positive
  Scenario Outline: Calculate gratuity with different salary in Decimal values
    When the user clicks "Yes" tag for eligibility
    And the user enters Salary "<salary>"
    And the user enters Years "<years>"
    And the user enters Months "<months>"
    And the user clicks Submit Button
    Then the user should see the calculated gratuity amount
    
     Examples:
      | salary   | years | months |
      | 7500.567    | 10    | 0      |