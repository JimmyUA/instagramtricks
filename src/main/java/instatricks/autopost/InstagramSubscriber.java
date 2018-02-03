package instatricks.autopost;

import instatricks.autopost.liker.AutoLiker;
import instatricks.autopost.pagevalidate.PageValidatableData;
import instatricks.util.ClassName;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.List;

import static java.lang.Thread.sleep;

public class InstagramSubscriber {

    private static final String FOLLOW_BUTTON_SELECTOR = "._r9b8f";
    private static WebDriver driver;
    private static int newFollowed = 0;

    private static final Logger LOGGER = Logger.getLogger(ClassName.getCurrentClassName());


    public static void main(String[] args) throws InterruptedException {

        driver = DriverInitializer.getDriver();


        driver.get("https://www.instagram.com/");
        logIn();

//        goToProfile();

        driver.get("https://www.instagram.com/alya_tours/");


        String myProfileURL = driver.getCurrentUrl();

        int followersAmount = getFollowersAmount(driver);
        goToFollowers();




//        walkOnMyFolowers(myProfileURL, followersAmount);
        walkOnAnotherAccountFolowers(myProfileURL, followersAmount);
        LOGGER.warn("On current session " + newFollowed);


    }

    private static void walkOnAnotherAccountFolowers(String myProfileURL, int followersAmount) {
        for (int i = 0; i < followersAmount; i++) {
            try {
                List<WebElement> offeredPeople;

                int buttonNumber = i + 1;
                offeredPeople = driver.findElements(By.
                        cssSelector("li._6e4x5:nth-child(" + buttonNumber + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)"));

                offeredPeople.get(0).click();
                PageValidatableData validatableData = new PageValidatableData();
                sleep(1000);
                setupValibatable(validatableData);
                if (PageValidator.validate(validatableData)){
                    subscribe();
                }
                goWalkToOfferedAccounts(0, true);
            }catch (ArrayIndexOutOfBoundsException ae){
                driver.get(myProfileURL);
                goToFollowers();
                WebElement body = driver.findElement(By.tagName("body"));
                scrollWithOffset(body, 20, 20);

            }
            catch (Exception e){
                LOGGER.error(e.getMessage());
                driver.get(myProfileURL);
                goToFollowers();
                continue;
            }

        }
    }

    private static void walkOnMyFolowers(String myProfileURL, int followersAmount) {
        for (int i = 0; i < followersAmount; i++) {
            try {
                List<WebElement> offeredPeople;

                int buttonNumber = i + 1;
                offeredPeople = driver.findElements(By.
                        cssSelector("li._6e4x5:nth-child(" + buttonNumber + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)"));

                offeredPeople.get(0).click();
                AutoLiker.likePhotos(5, driver, driver.getCurrentUrl());
                PageValidatableData validatableData = new PageValidatableData();
                setupValibatable(validatableData);
                if (PageValidator.validate(validatableData)){
                    subscribe();
                }
                goToFollowedAccounts();
                goWalkToOfferedAccounts(0, true);
            }catch (ArrayIndexOutOfBoundsException ae){
                driver.get(myProfileURL);
                goToFollowers();
                WebElement body = driver.findElement(By.tagName("body"));
                scrollWithOffset(body, 20, 20);

            }
            catch (Exception e){
                LOGGER.error(e.getMessage());
                driver.get(myProfileURL);
                goToFollowers();
                continue;
            }

        }
    }

    private static int getFollowersAmount(WebDriver driver) {
        String followersAmount = driver.findElement(By.cssSelector("li._bnq48:nth-child(2) > a:nth-child(1)")).getText();

        try {
            int result = Integer.parseInt(followersAmount);
            System.out.println(result);
            return result;
        } catch (NumberFormatException e) {
            return 5000;
        }
    }

    private static void goToFollowers() {
        driver.findElement(By.cssSelector("li._bnq48:nth-child(2) > a:nth-child(1)")).click();
    }

    private static void goToProfile() {
        driver.findElement(By.cssSelector(".coreSpriteDesktopNavProfile")).click();
    }

    private static void goWalkToOfferedAccounts(int counter, boolean isDialogue) {
        if (counter > 100) {
            return;
        }
        List<WebElement> offeredPeople;

        if (isDialogue) {
            int buttonNumber = counter + 1;
            offeredPeople = driver.findElements(By.
                    cssSelector("li._6e4x5:nth-child(" + buttonNumber + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)"));
        } else {
            offeredPeople = driver.findElements(By.
                    className("notranslate"));
        }
        walkTroughAccounts(offeredPeople, counter);
    }

    private static void walkTroughAccounts(List<WebElement> offeredPeople, int counter) {

        driver.switchTo().activeElement();

        WebElement webElement = offeredPeople.get(0);
        webElement.click();

        PageValidatableData validatableData = new PageValidatableData();
        setupValibatable(validatableData);

        if (PageValidator.validate(validatableData)) {

            subscribe();
            newFollowed++;
            goToFollowedAccounts();
            goWalkToOfferedAccounts(0, true);
            driver.navigate().back();

        } else {
            LOGGER.info(validatableData.getErrorMessage());
            driver.navigate().back();
            goWalkToOfferedAccounts(++counter, true);
        }

    }

    private static void goToFollowedAccounts() {
        driver.findElement(By.cssSelector("li._bnq48:nth-child(3) > a:nth-child(1)")).click();
    }

    private static void goToExplorePeoplePage() {
        driver.findElement(By.cssSelector("._3f3gc")).click();
    }

    private static void goToInterestingPage() {
        WebElement link = driver.findElement(By.className("coreSpriteDesktopNavExplore"));
        link.click();
    }

    private static void setupValibatable(PageValidatableData validatableData) {

        WebElement followersElement = null;
        WebElement descriptionElement = null;
        WebElement followedOrNotElement = null;
        try {
            followersElement = driver.findElement(By.cssSelector(" li._bnq48:nth-child(2) > a:nth-child(1) > span:nth-child(1)"));
            descriptionElement = driver.findElement(By.cssSelector("._kc4z2"));
            followedOrNotElement = driver.findElement(By.cssSelector("._r9b8f"));
        } catch (Exception e) {
            LOGGER.error("Description is not found \n" + e);
            try {
                descriptionElement = driver.findElement(By.cssSelector("._tb97a > span:nth-child(1) > span:nth-child(1)"));

            } catch (Exception ex) {
                LOGGER.error("Description is not found \n" + ex);
            }

        }
        String additionalDescription = "";
        try {
            additionalDescription = driver.findElement(By.cssSelector("._tb97a > span:nth-child(2) > span:nth-child(1)")).getText();
        } catch (Exception exc) {
            LOGGER.error(exc.getMessage());
        }

        String followers = "";
        String description = "";
        String followedOrNot = "";

        try {
            if (followersElement != null) {
                followers = followersElement.getText();
            }

            if (descriptionElement != null) {
                description = descriptionElement.getText() + " " + additionalDescription;
            }

            if (followedOrNotElement != null) {
                followedOrNot = followedOrNotElement.getText();
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        System.out.println(description);

        validatableData.setFollowers(followers);
        validatableData.setDescription(description);
        validatableData.setFollowedOrNot(followedOrNot);
    }

    private static void logIn() throws InterruptedException {
        sleep(1000);
        final WebElement loginUkr;
        try {
            loginUkr = driver.findElement(By.linkText("Увійдіть"));
            loginUkr.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.linkText("Log in")).click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        sleep(1000);

        driver.findElement(By.name("username")).sendKeys("jimmy18_88@mail.ru");
        driver.findElement(By.name("password")).sendKeys("Vika_Ruban");
        driver.findElement(By.tagName("button")).click();
        sleep(1000);
    }

    public static void scrollWithOffset(WebElement webElement, int x, int y) {

        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        ((JavascriptExecutor)driver).executeScript(code, webElement, x, y);

    }

    private static void subscribe() {
        LOGGER.info("Subscribing to \033[1m" + driver.findElement(By.cssSelector("._rf3jb")).getText());
        driver.findElement(By.cssSelector(FOLLOW_BUTTON_SELECTOR)).click();
    }


}
