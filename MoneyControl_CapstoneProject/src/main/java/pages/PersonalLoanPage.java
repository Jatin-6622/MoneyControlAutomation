package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class PersonalLoanPage {
	private WebDriver driver;
	private ElementUtils utils;
	
	@FindBy(xpath="//a[@title='Personal Finance']//span[@class='arrow down']")
	private WebElement PersonalFinance;
	@FindBy(xpath="//a[@href='https://www.moneycontrol.com/personal-finance/loans/personal-loan/calculator']")
	private WebElement LoanCalc;
	@FindBy(xpath="//button[@class='cbmcclose close_btn']//*[name()='svg'][2]/*[name()='g'][1]/*[name()='line'][2]")
	private WebElement AdsElement;
	@FindBy(xpath="//input[@id='amount1']")
	private WebElement LoanAmount;
	@FindBy(xpath="//input[@id='amount2']")
	private WebElement Interest;
	
	@FindBy(xpath="//input[@id='amount3']")
	private WebElement TenureF1;
	@FindBy(xpath="//span[text()='Years']")
	private WebElement TenureF2Y;
	@FindBy(xpath="//span[text()='Months']")
	private WebElement TenureF2M;
	@FindBy(xpath="//span[@id='tap']")
	private WebElement AmountPayable;
	
	public PersonalLoanPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
		utils=new ElementUtils(driver, 10);
	}
	public void hover_on_PersonalFinance() {
		utils.onlyHoverOverElement(PersonalFinance);
	}
	public void click_EMICalc() {
		utils.clickElement(LoanCalc);
	}
	public void enter_LoanAmount(String amount) {
		utils.typeText(LoanAmount,amount);
	}
	public void enter_Interest(String inter) {
		utils.typeText(Interest, inter);
	}
	public void enter_Tenure(String tenure) {
		utils.typeText(TenureF1, tenure);
	}
	public void select_YearsRadio() {
		utils.clickElement(TenureF2Y);
	}
	public void select_MonthsRadio() {
		utils.clickElement(TenureF2M);
	}
	public void click_AdsRemove() {
		utils.clickElement(AdsElement);
	}
	public String get_AmountPayable() {
		return utils.getText(AmountPayable);
	}
	public static int calculateTotalPayable(int principal, double annualRate, int tenure, String type) {
	    int totalMonths;
	    if (type.equalsIgnoreCase("Months")) {
	        totalMonths = tenure;
	    } else {
	        totalMonths = tenure * 12; // years to months
	    }

	    double monthlyRate = annualRate / 12 / 100;

	    double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, totalMonths))
	                 / (Math.pow(1 + monthlyRate, totalMonths) - 1);

	    double totalPayable = emi * totalMonths;

	    return (int) Math.round(totalPayable);
	}

}
