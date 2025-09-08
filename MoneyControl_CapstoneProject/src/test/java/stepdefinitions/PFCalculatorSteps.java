package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

import base.BaseSetup;
import pages.PFCalculatorPage;
import utils.ExcelReader;

public class PFCalculatorSteps extends BaseSetup {
    PFCalculatorPage pfCalc;
    ExcelReader reader;
    @Given("the user is on the Money Control Page")
	public void the_user_is_on_the_MoneyControlPage() {
	    pfCalc=new PFCalculatorPage(getDriver());
	    String filepath="E:\\New folder\\MoneyControl_CapstoneProject\\src\\main\\resources\\testdata\\TestData.xlsx";
	    reader=new ExcelReader(filepath, "Sheet1");
	}
    @When("the user hovers over {string} and clicks on {string}")
    public void the_user_hovers_over_and_clicks_on(String menu, String subMenu) {
        pfCalc.hover_on_PersonalFinance();
        pfCalc.click_on_PFCalc();
    }

    @And("the user enters Age {string}")
    public void the_user_enters_age(String age) {
        pfCalc.enter_Age(age);
    }

    @And("the user enters Monthly Salary {string}")
    public void the_user_enters_monthly_salary(String salary) {
        pfCalc.enter_Salary(salary);
    }
    @And("the user entered Monthly Salary as Zero")
    public void the_user_enters_monthly_salary() {
        pfCalc.enter_Salary("0");
    }

    @And("the user enters Employee Contribution {string}")
    public void the_user_enters_employee_contribution(String empContri) {
        pfCalc.enter_YourContribution(empContri);
    }

    @And("the user enters Employer Contribution {string}")
    public void the_user_enters_employer_contribution(String employerContri) {
        pfCalc.enter_EmployerContribution(employerContri);
    }

    @And("the user enters Intended Years of Service {string}")
    public void the_user_enters_intended_years_of_service(String years) {
        pfCalc.enter_YearsOfService(years);
    }
    @And("the user enters Intended Years of Service same as Age {string}.")
    public void the_user_enters_intended_years_asage(String years) {
        pfCalc.enter_YearsOfService(years);
    }
    @And("the user clicks on Calculate")
    public void the_user_clicks_on_calculate() {
        pfCalc.click_CalculateBtn();
    }
    
    @Then("the user should see the accumulated PF value.")
    public void the_user_should_see_the_accumulated_pf_value() {
    		int  age = (int) Double.parseDouble(reader.getCellData(1, 0));
    		int salary = (int) Double.parseDouble(reader.getCellData(1, 1)); 
    		int empPFPercent = (int) Double.parseDouble(reader.getCellData(1, 2));
    		int employerPFPercent = (int) Double.parseDouble(reader.getCellData(1, 3));
    		int retireAge = (int) Double.parseDouble(reader.getCellData(1, 4)); 
    	    
    	    int years = retireAge - age;

    	    double empPF = salary * empPFPercent / 100.0;
    	    double employerPF = salary * employerPFPercent / 100.0;
    	    double totalPF = empPF + employerPF;

    	    int pfResult = (int) (totalPF * 12 * years); 

    	    String expected = String.format("Rs ", pfResult);
    	    String result = pfCalc.get_Result().replaceAll(",","");

    	    Assert.assertTrue(result.replaceAll("\\s","").contains(expected.replaceAll("\\s","")), 
    	        "Result should match expected PF. Expected: " + expected + ", but got: " + result);
    }
    @Then("the user should see the accumulated PF value contibution changed.")
    	public void the_user_should_see_the_accumulated_pf_value_changed() {
    	int age = (int) Double.parseDouble(reader.getCellData(2, 0));
    	int salary = (int) Double.parseDouble(reader.getCellData(2, 1)); 
    	int empPFPercent = (int) Double.parseDouble(reader.getCellData(2, 2));
    	int employerPFPercent = (int) Double.parseDouble(reader.getCellData(2, 3));
    	int retireAge = (int) Double.parseDouble(reader.getCellData(2, 4)); 
    
    	int years = retireAge - age;

    	double empPF = salary * empPFPercent / 100.0;
    	double employerPF = salary * employerPFPercent / 100.0;
    	double totalPF = empPF + employerPF;

    	int pfResult = (int) (totalPF * 12 * years); 

    	String expected = String.format("Rs ", pfResult);
    	String result = pfCalc.get_Result().replaceAll(",","");
    	
    	Assert.assertTrue(result.replaceAll("\\s","").contains(expected.replaceAll("\\s","")), 
        "Result should match expected PF. Expected: " + expected + ", but got: " + result);
}
    @Then("the user should see final result as zero.")
	public void the_user_should_see_the_accumulated_pf_value_zero() {
	int age = (int) Double.parseDouble(reader.getCellData(3, 0));
	int salary = (int) Double.parseDouble(reader.getCellData(3, 1)); 
	int empPFPercent = (int) Double.parseDouble(reader.getCellData(3, 2));
	int employerPFPercent = (int) Double.parseDouble(reader.getCellData(3, 3));
	int retireAge = (int) Double.parseDouble(reader.getCellData(3, 4)); 

	int years = retireAge - age;

	double empPF = salary * empPFPercent / 100.0;
	double employerPF = salary * employerPFPercent / 100.0;
	double totalPF = empPF + employerPF;

	int pfResult = (int) (totalPF * 12 * years); 

	String expected = String.format("Rs ", pfResult);
	String result = pfCalc.get_Result().replaceAll(",","");
	
	Assert.assertTrue(result.replaceAll("\\s","").contains(expected.replaceAll("\\s","")), 
    "Result should match expected PF. Expected: " + expected + ", but got: " + result);
}

    @Then("the user should see a Error Msg containing {string}")
    public void the_user_should_see_error_message(String expectedMsg) {
        String actualMsg = pfCalc.NegativeCase1();
        Assert.assertEquals(actualMsg, expectedMsg,
            "Error message mismatch! Expected: " + expectedMsg + " but got: " + actualMsg);
    }
}
