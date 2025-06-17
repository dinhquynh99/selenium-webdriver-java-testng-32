package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_24_JavascriptExecutor_P2 {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    Random random;
    String email;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        email = "automation" + new Random().nextInt(9999) + "@gmail.com";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Techpanda() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'https://live.techpanda.org/'");
        explicitWait.until(ExpectedConditions.urlToBe("https://live.techpanda.org/"));

        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

        String homePageURL = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homePageURL, "https://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement
                (By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));


        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(3000);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("input#newsletter")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "')", driver.findElement(By.cssSelector("input#newsletter")));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[title='Subscribe']")));
        Thread.sleep(3000);

        String subcriptionText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertEquals(subcriptionText, "Thank you for your subcription.");

        jsExecutor.executeScript("window.location = 'https://www.facebool.com/'");
        Thread.sleep(3000);
    }

    @Test
    public void TC_02_Techpanda() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
