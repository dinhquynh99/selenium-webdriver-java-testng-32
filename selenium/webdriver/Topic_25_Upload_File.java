package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_25_Upload_File {

    WebDriver driver;

    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String hoiAn = "Hoi An.jpg";
    String haGiang = "Ha Giang.jpg";
    String phuQuoc = "Phu Quoc.jpg";

    String hoiAnPath = uploadFolderPath + hoiAn;
    String haGiangPath = uploadFolderPath + haGiang;
    String phuQuocPath = uploadFolderPath + phuQuoc;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        }

    @Test
    public void TC_01_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên
        driver.findElement(inputBy).sendKeys(hoiAnPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(haGiangPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(phuQuocPath);
        Thread.sleep(2000);

        // Verify file dc load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + phuQuoc + "']")).isDisplayed());

        // click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify các file đc upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" + phuQuoc + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" + phuQuoc + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên - 1 lần nhiều file
        driver.findElement(inputBy).sendKeys(hoiAnPath + "\n" + haGiangPath + "\n" + phuQuocPath);
        Thread.sleep(2000);

        // Verify file dc load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + phuQuoc + "']")).isDisplayed());

        // click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify các file đc upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" + phuQuoc + "']")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
//
