package pages;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;
public class LoginPage {
	private WebDriver driver;
	private ElementUtils utils;
	WebDriverWait wait; 
	@FindBy(xpath = "//a[text()=' Hello, Login']")
	private WebElement HelloLogin;
	@FindBy(xpath = "//a[@title='Log In']")
	private WebElement LogIn;
	@FindBy(xpath = "//input[@name='email_mirror']")
	private WebElement EmailMobile;
	@FindBy(xpath = "//span[@class='usr_nm']")
	private WebElement UserDisplay;
	@FindBy(xpath = "//div[@id='email_mirror-error']")
	private WebElement ErrorMsg;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		utils=new ElementUtils(driver,10);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));}
	public void hover_On_HelloLogin() {
		utils.hoverOverElement(HelloLogin);
	}
	public void click_on_LogIn() {
		utils.clickElement(LogIn);
	}
	public void enter_MobileorEmail(String number){
		driver.switchTo().frame("myframe");
		utils.typeText(EmailMobile, number);}
	public String get_ErrorMsg() {
		return utils.getText(ErrorMsg);}
	public boolean check_UserDisplay(String user) {
		utils.switchToDefaultContent();
		String msg=utils.getText(UserDisplay);
		if(msg.equals(user)) {
			return true;
		}
		else {
			return false;
		}
	}
}
