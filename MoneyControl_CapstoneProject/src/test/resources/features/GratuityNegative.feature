Feature: Gratuity Calculator functionality
  As a user
  I want to calculate my gratuity
  So that I can know my retirement benefits

  Background:
    Given the user is on the Money Control site
    When the user hovers over "Personal Finance" and clicked on "More Calculators"
    And the user clicks on "Gratuity Calculator"

  @negative
  Scenario Outline: Adding alphabet in Salary with numbers
    When the user clicks "Yes" tag for eligibility
    And the user enters Salary "<salary>"
    And the user enters Years "<years>"
    And the user enters Months "<months>"
    And the user clicks Submit Button
    Then the user should see the Alert containing message "Please enter salary"

    Examples:
      | salary  | years | months |
      | 5000e   | 6     | 4      |

  @negative
  Scenario Outline: Total No. of years less than 5 years
    When the user clicks "Yes" tag for eligibility
    And the user enters Salary "<salary>"
    And the user enters Years "<years>"
    And the user enters Months "<months>"
    And the user clicks Submit Button
    Then the user should see the Alert containing message "Year should not less than 5"

    Examples:
      | salary  | years | months |
      | 35000   | 4     | 3      |

  @negative
  Scenario Outline: Leaving Years field empty
    When the user clicks "Yes" tag for eligibility
    And the user enters Salary "<salary>"
    And the user enters Years "<years>"
    And the user enters Months "<months>"
    And the user clicks Submit Button
    Then the user should see the Alert containing message "Please enter Year"  

    Examples:
      | salary  | years | months |
      | 20000   |       | 5      |

    