package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_26_Wait_Status {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Visible() throws InterruptedException {
        driver.get("https://www.facebook.com/");

        // 1- Element có trên UI và trong cây HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }

    @Test
    public void TC_02_Invisible() throws InterruptedException {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Điều kiện mồi để cho confirm email xuất hiện trên UI
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("quynh@gmail.com");

        // 2- Element ko có trên UI nhưng vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // 3- Element không có trên UI và cũng ko có trong HTML
        System.out.println("Start wait:" + getDateTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
        System.out.println("End wait:" + getDateTimeNow());
    }

    @Test
    public void TC_03_Presence() throws InterruptedException {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email address textbox dc visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // 1- Element có trên UI và có trong cây HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

        // Điều kiện mồi để cho confirm email ko còn xuất hiện trên UI
        driver.findElement(By.cssSelector("input[name='reg_email__")).clear();

        // 2- Element ko có trên UI nhưng vẫn có trong HTML
        System.out.println("Start wait:" + getDateTimeNow());
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
        System.out.println("End wait:" + getDateTimeNow());
    }

    @Test
    public void TC_04_Stalenness() throws InterruptedException {
        driver.get("https://www.facebook.com/");

        // Click để mỏ popup ra
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email address textbox dc visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // Tại thời điểm này confirm email đang có trong HTML
        WebElement confirmEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

        // Click đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // 3- Element không có trên UI và cũng ko có trong HTML
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }

     private String getDateTimeNow(){
        return new Date().toString();
     }
}