package stepdefinitions;

import org.testng.Assert;

import base.BaseSetup;
import io.cucumber.java.en.*;
import pages.GratuityPage;
import utils.ExcelReader;

public class GratuitySteps extends BaseSetup{
	GratuityPage Gratuity;
	ExcelReader reader;
	@Given("the user is on the Money Control site")
	public void the_user_is_on_the_money_control_site() {
		Gratuity=new GratuityPage(getDriver());
		   String filepath="C:\\Users\\JATIN SHARMA\\git\\CapstoneProject-MoneyControl\\MoneyControl_CapstoneProject\\src\\main\\resources\\testdata\\TestData.xlsx";
		    reader=new ExcelReader(filepath, "Sheet3");
	}

	@When("the user hovers over {string} and clicked on {string}")
	public void the_user_hovers_over_and_clicked_on(String string, String string2) {
	    Gratuity.hover_on_PersonalFinance();
	    Gratuity.click_MoreCalc();
	}

	@When("the user clicks on {string}")
	public void the_user_clicks_on(String string) {

		Gratuity.click_GratuityCalc();
	}

	@When("the user clicks {string} tag for eligibility")
	public void the_user_clicks_tag_for_eligibility(String string) {
	   Gratuity.click_YesTag();
	}

	@When("the user enters Salary {string}")
	public void the_user_enters_salary(String string) {
	    Gratuity.enter_Salary(string);
	}

	@When("the user enters Years {string}")
	public void the_user_enters_years(String string) {
		Gratuity.enter_Years(string);
	}

	@When("the user enters Months {string}")
	public void the_user_enters_months(String string) {
		Gratuity.enter_Months(string);
	}

	@When("the user clicks Submit Button")
	public void the_user_clicks_submit_button() throws InterruptedException {
	   Gratuity.click_Submit();
	}
	@Then("the user should see the Alert containing message {string}")
	public void the_user_should_see_the_calculated_gratuity_alert(String expectedAlert) {
	    String actual = Gratuity.getAlertText();
	    Assert.assertEquals(actual, expectedAlert);
	}

	@Then("the user should see the calculated gratuity amount {string}")
	public void the_user_should_see_the_calculated_gratuity_amount(String salaryValue) {
	    int row = reader.findRowByValue(0, salaryValue.replaceAll(" ",""));
	    if (row == -1) {
	        throw new RuntimeException("Salary " + salaryValue + " not found in Excel!");
	    }
	    String salaryStr = reader.getCellData(row, 0);
	    String yearsStr = reader.getCellData(row, 1);
	    String monthsStr = reader.getCellData(row, 2);

	    double salary = salaryStr.isEmpty() ? 0 : Double.parseDouble(salaryStr);
	    int years = yearsStr.isEmpty() ? 0 : (int) Math.round(Double.parseDouble(yearsStr));
	    int months = monthsStr.isEmpty() ? 0 : (int) Math.round(Double.parseDouble(monthsStr));
	    double totalYears = years;
	    if (months >6) totalYears += 1;
	    if (years < 5) totalYears = 0;
	    double gratuity = (salary * 15 / 26) * totalYears;
	    gratuity = Math.round(gratuity * 100.0) / 100.0;
	    
	    String actualText = Gratuity.gather_Result().replaceAll("[^0-9.]", "");
	    double actualDouble = actualText.isEmpty() ? 0 : Double.parseDouble(actualText);
	    actualDouble = Math.round(actualDouble * 100.0) / 100.0;
	    int expectedInt = (int) Math.round(gratuity);
	    int actualInt  = (int) Math.round(actualDouble);
	    Assert.assertEquals(actualInt, expectedInt, "Gratuity calculation mismatch!");
	}


}
