package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_23_JavascriptExecutor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("https://demo.nopcommerce.com/login?returnURL=%2F");

    }

    @Test
    public void TC_01_() throws InterruptedException {

        // Click vào 1 element mà đnag bị che/ ẩn bằng WebElement click();
        // driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='desktops']")).click();

        // Click vào 1 element bằng jsExecutor nó ko quan tâm ẩn/ hiện
        // Sai hành vi của end user => Giả lập hành vi của end user đang thao tác trên application
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops']")));
        Thread.sleep(5000);

        // Lấy ra domain
        // System.out.println(jsExecutor.executeScript("return document.domain;"));

        // Lấy ra 1 WebElement
        // WebElement emailTextbox = (WebElement) jsExecutor.executeScript("return document.querySelector('input#Email');");
        // Thread.sleep(3000);

        // emailTextbox.sendKeys("automationfc.vn@gmail.com");
        // Thread.sleep(3000);

        // WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));

        // String loginUrl = (String) jsExecutor.executeScript("return document.URL;");

        // List<WebElement> emailTypeTextbox = (List<WebElement>) jsExecutor.executeScript("return document.querySelectAll(\"input[type='email']\");");

        // Scroll tới 1 element nào đó thì sao

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
