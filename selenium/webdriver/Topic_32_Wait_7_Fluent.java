package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_32_Wait_7_Fluent {

    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        }

    @Test
    public void TC_01_Elemnet_Found() {
        // Mặc định thời gian tìm lại element (polling/ interval time): 500ms = 0.5s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Mặc định thời gian tìm lại element (polling/ interval time): 500ms = 0.5s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Custom thời gian tìm lại element (polling/ interval time)
        // 100ms tìm lại 1 lần = 1 giây sẽ tìm lại 10 lần
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(100));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(1));

        fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver WebDriver) {
                return driver.findElement(By.cssSelector("")).getText();
            }
        });

    }


    @AfterClass
    public void afterClass() {

            driver.quit();
    }

    private String getDateTimeNow(){
        return new Date().toString();
    }
}
//
