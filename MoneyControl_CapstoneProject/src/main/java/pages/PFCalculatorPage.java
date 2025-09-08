package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;
public class PFCalculatorPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	private ElementUtils utils;
	@FindBy(xpath="//a[@title='Personal Finance']//span[@class='arrow down']")
	private WebElement PersonalFinance;
	@FindBy(xpath="//a[@title='Provident Fund Calculator']")
	private WebElement PFCalc;
	@FindBy(xpath="//input[@id='id_your_age']")
	private WebElement Age;
	@FindBy(xpath="//input[@name='basic_salary_monthly']")
	private WebElement Salary;
	@FindBy(xpath="//input[@name='your_contribution']")
	private WebElement YourContri;
	@FindBy(xpath="//input[@name='employers_contribuion']")
	private WebElement EmpContri;
	@FindBy(xpath="//input[@name='intend_to_retire']")
	private WebElement YearsService;
	@FindBy(xpath="//td[text()='Please putin retire age correctly.']")
	private WebElement NegCase1;
	@FindBy(xpath="//img[@src='https://images.moneycontrol.com/images/wealth/calculate.gif']")
	private WebElement CalcBtn;
	@FindBy(xpath="//span[text()='You will have accumulated ']/span")
	private WebElement value;
	public PFCalculatorPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		utils=new ElementUtils(driver, 10);
	}
	public void hover_on_PersonalFinance() {
		utils.onlyHoverOverElement(PersonalFinance);
	}
	public void click_on_PFCalc() {
		utils.clickElement(PFCalc);
	}
	public void enter_Age(String age) {
		utils.typeText(Age,age);
	}
	public void enter_Salary(String salary) {
		utils.typeText(Salary, salary);
	}
	public void enter_YourContribution(String urContri) {
		utils.typeText(YourContri,urContri);
	}
	public void enter_EmployerContribution(String empContri) {
		utils.typeText(EmpContri, empContri);
	}
	public void enter_YearsOfService(String year) {
		utils.typeText(YearsService, year);
	}
	public String NegativeCase1() {
		return utils.getText(NegCase1);
	}
	public void click_CalculateBtn() {
		utils.clickElement(CalcBtn);
	}
	public String get_Result() {
		return utils.getText(value);
	}
}
