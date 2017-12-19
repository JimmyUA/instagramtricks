package instatricks.autopost;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class InstagramPoster {


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "D:/selenium/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.instagram.com/");
        sleep(1000);
        driver.findElement(By.linkText("Увійдіть")).click();
        sleep(1000);

       driver.findElement(By.name("username")).sendKeys("jimmy18_88@mail.ru");
       driver.findElement(By.name("password")).sendKeys("Vika_Ruban");
       driver.findElement(By.tagName("button")).click();
        sleep(1000);

       driver.findElement(By.className("coreSpriteDesktopNavProfile")).click();
//
//
//        driver.findElement(By.linkText("Нравится")).click();

    }
}
