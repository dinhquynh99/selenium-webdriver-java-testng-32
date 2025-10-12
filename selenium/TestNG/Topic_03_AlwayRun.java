package TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_AlwayRun {
    WebDriver driver;

    @BeforeClass
    public void initBrowserDriver(){
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org/");
        driver.manage().window().maximize();

        Assert.assertEquals(driver.getTitle(),"Home Page");
    }

    @Test
    public void TC_01(){
        System.out.println("Testcase 01");

    }

    @Test
    public void TC_02(){
        System.out.println("Testcase 02");
    }

    @AfterClass(alwaysRun = true)
    public void cleanBrowserDriver(){
        driver.quit();
    }

}