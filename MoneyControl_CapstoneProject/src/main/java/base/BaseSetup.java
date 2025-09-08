package base;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import driversManager.DriverFactory;
import utils.ConfigReader;
public class BaseSetup {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private Properties prop;
    private ConfigReader configReader;
    public void setUp() {
        configReader = new ConfigReader();
        prop = configReader.initProperties();
        String browser = prop.getProperty("browser", "chrome"); 
        String url = prop.getProperty("url", "https://www.moneycontrol.com/");
        WebDriver webDriver = DriverFactory.getDriver(browser);
        if (webDriver == null) {
            throw new RuntimeException("WebDriver is not initialized. Check DriverFactory.");
        }
        driver.set(webDriver);
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
