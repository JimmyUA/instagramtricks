package instatricks.autopost;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverInitializer {
    public static WebDriver getDriver(){
        System.setProperty("webdriver.gecko.driver", "D:/selenium/geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("test-type");
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-web-security");
        firefoxOptions.addArguments("--allow-running-insecure-content");

        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);

        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        return driver;
    }
}
