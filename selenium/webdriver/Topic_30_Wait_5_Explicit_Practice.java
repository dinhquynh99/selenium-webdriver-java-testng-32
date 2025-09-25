package webdriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_30_Wait_5_Explicit_Practice {

    WebDriver driver;
    WebDriverWait explicitWait;

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
    }

    @Test
    public void TC_01_() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Chờ loading icon invisible
        // 1. Chờ cho step trước hoàn thành -> rồi mới qua step sau (ko quan tâm cái step sau làm gì)
        // Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

        // Chờ cho text là visible
        // 2. Sau khi step trước được bắt đầu -> nó sẽ chờ cho 1 đối tượng của step sau được xuất hiện
        // ko quan tâm step trước đã hoàn thành xong hay chưa
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).
                getText(),"Hello World!");

    }

    @Test
    public void TC_02_Ajax() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Chờ trong 30s để cho date picke được hiển thị
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ctl100_ContentPlaceholder1_Panel1")));

        // Wait cho text dc xuất hiện trong 30s
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),
                "No Selected Dates to display.")));

        // Wait cho element được click và sau đó sẽ click vào
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='18']"))).click();

        // Wait cho loading icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div:not([style='display:none;'])>div.raDiv"))));

        // Wait cho text được cập nhật lên trang
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),
                "Thursday, September 18, 2025")));
    }

    @Test
    public void TC_03_Upload() {
        driver.get("https:/gofile.io/home");

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

        // chờ cho tất cả các loading ở trên trang hiện tại biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.animate-spin"))));

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")))
                .sendKeys(hoiAnPath + "\n" + haGiangPath + "\n" + phuQuocPath);

        // Chờ cho tất cả các upload progress bar biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.file-progressbar"))));

        String uploadUrl = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).getDomProperty("href");
        driver.get(uploadUrl);

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.animate-spin"))));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class,'item_open') and normalize-space(text())='Hoi An.jpg']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class,'item_open') and normalize-space(text())='Ha Giang.jpg']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class,'item_open') and normalize-space(text())='Phu Quoc.jpg']")));
    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
