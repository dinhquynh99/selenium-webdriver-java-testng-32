package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_20_Random_Popup {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Javacodegeeks() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        By newsletterPopupBy = By.xpath("//div[@data-title='Newletter Free eBooks'" +
                "and not(contains(@style,'display::none'))]");

        // Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(newsletterPopupBy).size() > 0
                && driver.findElements(newsletterPopupBy).get(0).isDisplayed()) {
            System.out.println("___GO TO IF___");
            driver.findElement(By.cssSelector("//div[@data-title='Newletter Free eBooks'" +
                    "and not(contains(@style,'display::none'))]//a[contains(@onclick,'lepopup_close')]")).click();
            Thread.sleep(2000);
        }

        // Ko hiển thị thì action tiếp
        System.out.println("___IGNORE IF___");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");
        driver.findElement(By.cssSelector("form#search span.tie-search-icon")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("header>h1.page-title")).isDisplayed());

    }

    @Test
    public void TC_02_DeHieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        By contentPopup = By.cssSelector("div.modal-content");

        // Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(contentPopup).size() > 0
                && driver.findElements(contentPopup).get(0).isDisplayed()) {
            System.out.println("___GO TO IF___");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
            Thread.sleep(2000);
        }

        // Ko hiển thị thì action tiếp
        System.out.println("___IGNORE IF___");
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khoá học Lập dự toán M&E");
        driver.findElement(By.cssSelector("button.header-search")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"),"Khoá học Lập dự toán M&E");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
