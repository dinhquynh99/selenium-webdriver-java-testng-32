package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
