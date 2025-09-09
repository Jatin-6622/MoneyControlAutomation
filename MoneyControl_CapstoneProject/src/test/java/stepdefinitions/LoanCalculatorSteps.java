package stepdefinitions;

import org.testng.Assert;

import base.BaseSetup;
import io.cucumber.java.en.*;
import pages.PersonalLoanPage;
import utils.ExcelReader;

public class LoanCalculatorSteps extends BaseSetup{
	PersonalLoanPage LoanPage;
	ExcelReader reader;
	@Given("the user is on the Money Control Site")
	public void the_user_is_on_the_money_control_site() {
	   LoanPage=new PersonalLoanPage(getDriver());
	   reader=new ExcelReader("C://Users//JATIN SHARMA//git//CapstoneProject-MoneyControl//MoneyControl_CapstoneProject//src//main//resources//testdata//TestData.xlsx","Sheet2");
	}
	@When("the user disables the ads shown in EMI Calculator")
	public void the_users_click_on_ads_toRemove() {
		LoanPage.click_AdsRemove();
	}
	@Then("the user hovers over {string} and click on {string}")
	public void the_user_hovers_over_and_clicks_on(String menu, String subMenu) {
        LoanPage.hover_on_PersonalFinance();
        LoanPage.click_EMICalc();
    }

	@And("the user enters Loan Amount {string}")
	public void the_user_enters_loan_amount(String string) {
	    LoanPage.enter_LoanAmount(string);
	}

	@And("the user enters Interest Rate {string}")
	public void the_user_enters_interest_rate(String string) {
	   LoanPage.enter_Interest(string);
	}

	@And("the user enters Loan Tenure {string}")
	public void the_user_enters_loan_tenure(String string) {
	    LoanPage.enter_Tenure(string);
	}

	@And("the user selects {string} option")
	public void the_user_selects_option(String string) {
	    if(string.equals("Years")) {
	    	LoanPage.select_YearsRadio();
	    }
	    else {
	    	LoanPage.select_MonthsRadio();
	    }
	}
	@Then("for {int} error in results is shown containing most of values as Zero")
	public void for_error_in_results_is_shown_containing_most_of_values_as_zero(Integer int1) {
	    String loanAmount = int1.toString();
	    int row = reader.findRowByValue(0, loanAmount);
	    String principalStr = reader.getCellData(row, 0);
	    double principalDouble = (principalStr == null || principalStr.isEmpty()) ? 0 : Double.parseDouble(principalStr);
	    int principal = (int) Math.round(principalDouble);

	    String rateStr = reader.getCellData(row, 1);
	    double rate = (rateStr == null || rateStr.isEmpty()) ? 0 : Double.parseDouble(rateStr);

	    String tenureStr = reader.getCellData(row, 2);
	    double tenureDouble = (tenureStr == null || tenureStr.isEmpty()) ? 0 : Double.parseDouble(tenureStr);
	    int tenure = (int) Math.round(tenureDouble);

	    String type = reader.getCellData(row, 3);

	    int expected;
	    if (principal == 0 || tenure == 0) {
	        expected = 0;
	    } else if (rate == 0) {
	        expected = principal;
	    } else {
	        expected = PersonalLoanPage.calculateTotalPayable(principal, rate, tenure, type);
	    }

	    String actualText = LoanPage.get_AmountPayable().replaceAll("[^0-9.]", ""); 
	    double actualDouble = (actualText == null || actualText.isEmpty()) ? 0 : Double.parseDouble(actualText);
	    int actual = (int) Math.round(actualDouble);

	    Assert.assertEquals(actual, expected,
	        "Loan calculation mismatch! Expected: " + expected + " but got: " + actual);
	}


	@Then("the user should see the total Amount Payable displayed")
	public void the_user_should_see_the_total_amount_payable_displayed() {
	    double principalDouble = Double.parseDouble(reader.getCellData(1, 0));
	    int principal = (int) Math.round(principalDouble);

	    double rate = Double.parseDouble(reader.getCellData(1, 1));

	    double tenureDouble = Double.parseDouble(reader.getCellData(1, 2));
	    int tenure = (int) Math.round(tenureDouble);

	    String type = reader.getCellData(1, 3);

	    int expected = PersonalLoanPage.calculateTotalPayable(principal, rate, tenure, type);

	    String actualText = LoanPage.get_AmountPayable().replaceAll("[^0-9.]", ""); 
	    double actualDouble = Double.parseDouble(actualText); 
	    int actual = (int) Math.round(actualDouble);

	    Assert.assertEquals(actual, expected,
	        "Loan calculation mismatch! Expected: " + expected + " but got: " + actual);
	}

	@Then("the user should see the total Amount Payable display")
	public void the_user_should_see_the_total_amount_payable_display() {
		double principalDouble = Double.parseDouble(reader.getCellData(2, 0));
	    int principal = (int) Math.round(principalDouble);

	    double rate = Double.parseDouble(reader.getCellData(2, 1));

	    double tenureDouble = Double.parseDouble(reader.getCellData(2, 2));
	    int tenure = (int) Math.round(tenureDouble);

	    String type = reader.getCellData(2, 3);

	    int expected = PersonalLoanPage.calculateTotalPayable(principal, rate, tenure, type);

	    String actualText = LoanPage.get_AmountPayable().replaceAll("[^0-9.]", ""); 
	    double actualDouble = Double.parseDouble(actualText); 
	    int actual = (int) Math.round(actualDouble);

	    Assert.assertEquals(actual, expected,
	        "Loan calculation mismatch! Expected: " + expected + " but got: " + actual);}
}
