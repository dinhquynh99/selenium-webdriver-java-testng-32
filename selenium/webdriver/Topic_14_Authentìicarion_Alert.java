package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_14_Authentìicarion_Alert {

    WebDriver driver;
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    @Test
    public void TC_01_Authentification_URL() {
        // http/ https:// + username + : + password + @ URL
        driver.get("https://" +username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_02_Authentication_Navigate() {
        driver.get("https://the-internet.herokuapp.com/");

        String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        driver.get(getAuthenticationUrl(basicAuthLink, username, password));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    public String getAuthenticationUrl(String link, String username, String password){
        String[] linkArray = link.split("//");
        return linkArray[0] + "//" + username + ":" + password + "@" + linkArray[1];

    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
//
