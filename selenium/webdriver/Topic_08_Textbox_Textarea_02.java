package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_08_Textbox_Textarea_02 {

    WebDriver driver;

    String educationText = "Automation Tesing\nwith\nSenenium Java";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {

        driver.get("https://automationfc.github.io/basic-form/");

        driver.findElement(By.cssSelector("textarea#edu")).sendKeys(educationText);

    }

    @Test
    public void TC_02_() {
        // Wait for all loading icon disappear
        //Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
        //        invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner")))));

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
