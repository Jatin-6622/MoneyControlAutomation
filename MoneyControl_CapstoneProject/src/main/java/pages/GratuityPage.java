package pages;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class GratuityPage {
	private WebDriver driver;
	private ElementUtils utils;
	
	@FindBy(xpath="//a[@title='Personal Finance']//span[@class='arrow down']")
	private WebElement PersonalFinance;
	@FindBy(xpath="//a[@title='More Calculators']")
	private WebElement MoreCalc;
	@FindBy(xpath="//a[contains(@href,'gratuity-calculator.html')]")
	private WebElement GratuityCalc;
	@FindBy(xpath="//span[contains(@class,'openblock')]")
	private WebElement YesTag;
	@FindBy(xpath="//input[@id='salary']")
	private WebElement Salary;
	@FindBy(xpath="//input[@id='year']")
	private WebElement Years;
	@FindBy(xpath="//input[@id='month']")
	private WebElement Month;
	@FindBy(xpath="//a[@id='graduity_calc_btn']")
	private WebElement SubmitBtn;
	@FindBy(xpath="//span[@id='graduity_amt']")
	private WebElement FinalAmount;
	public GratuityPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		utils=new ElementUtils(driver, 10);
	}

	public void hover_on_PersonalFinance() {
		utils.onlyHoverOverElement(PersonalFinance);
	}
	
	public void click_MoreCalc() {
		utils.clickElement(MoreCalc);
	}
	public void click_GratuityCalc() {
	    utils.clickElement(GratuityCalc);

	    Set<String> windowHandles = driver.getWindowHandles();
	    String parentWindow = driver.getWindowHandle();

	    for (String handle : windowHandles) {
	        if (!handle.equals(parentWindow)) {
	            driver.switchTo().window(handle);
	            break;
	        }
	    }

	}
	public void click_YesTag() {
		utils.clickElement(YesTag);
	}
	public void enter_Salary(String salary) {
		utils.typeText(Salary, salary);
	}
	public void enter_Years(String years) {
		utils.typeText(Years, years);
		 
	}
	public void enter_Months(String month) {
		utils.typeText(Month, month);
	}
	public void click_Submit() {
		utils.clickElement(SubmitBtn);
	}
	public String getAlertText() {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String text = alert.getText();
	        alert.accept();
	        return text;
	    } catch (NoAlertPresentException e) {
	        return "";
	    }
	}
	public String gather_Result() {
		return utils.getText(FinalAmount);
	}
	
}
