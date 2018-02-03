package instatricks.autopost.liker;

import instatricks.util.ClassName;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AutoLiker {

    private static final Logger LOGGER = Logger.getLogger(ClassName.getCurrentClassName());


    public static void likePhotos(int photoAmount, WebDriver driver, String page) {
        driver.get(page);
        List<WebElement> posts = driver.findElements(By.className("_e3il2"));

        for (WebElement post : posts
             ) {
            like(post, driver);
        }
    }

    private static void like(WebElement post, WebDriver driver) {
        post.click();
        driver.findElement(By.cssSelector("._eszkz")).click();
        LOGGER.info("Liked " + post.getText());
        driver.navigate().back();
    }

}