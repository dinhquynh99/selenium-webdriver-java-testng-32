package webdriver;

import com.google.common.base.Function;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_32_Wait_7_Fluent_Practice {

    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

    //@Test
    public void TC_01_Elemnet_Found() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

//        Assert.assertTrue(fluentWait.until(new Function<WebDriver, Boolean>() {
//            @Override
//            public Boolean apply(WebDriver WebDriver) {
//                return  WebDriver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
//            }
//        }));

        String helloText = fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver WebDriver) {
                return  WebDriver.findElement(By.cssSelector("div#finish>h4")).getText();
            }
        });

        Assert.assertEquals(helloText, "Hello World!");

        // Yêu cầu của bài toán là gì => ra đc điều kiện cần lấy
        // Viết hàm để sửa lại các hàm findElement/ click/ getText/ isDisplay vs polling time ms
    private WebElement getElement(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        FluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator));
            }
        });
    }

        private WebElement getElement(String cssLocator){
            FluentWait<WebDriver> fluentWait = new FluentWait(driver);

            FluentWait.withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .until(new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver driver) {
                            return driver.findElement(By.cssSelector(cssLocator));
                        }
                    }).click();
        }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    private String getDateTimeNow() {
        return new Date().toString();
    }
}