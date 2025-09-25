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
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Topic_30_Wait_5_Explicit_Funtion {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_() {
        // Alert present
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Element visible (cho 1/ chp nhiều/ tham số là gì)
        WebElement emailTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        emailTextbox.sendKeys("Automation");

        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElement(By.cssSelector("input#email")),
                driver.findElement(By.cssSelector("input#password")),
                driver.findElement(By.cssSelector("input#name"))
        ));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElements(By.cssSelector("input[type=text]"))));

        // Element invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Element presence
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // Element staleness
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Element clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Element selected
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Element có số lượng bằng bao nhiêu (ít hơn/ bằng/ nhiều hơn)
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""),5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),5));

        // Kết hợp 2 đk (And/ Or/ Not)

        // cả 2 đk đều đúng
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

        // 1 trong 2 đúng
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

        // invisiblity
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));


        // Url/ Title/ Text
        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        explicitWait.until(ExpectedConditions.titleIs(""));
        explicitWait.until(ExpectedConditions.titleContains(""));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"Automation"));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")),"Automation"));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("")));

        // Attribute/ Dom property/ Frame
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"class","email"));

        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),"textContent","Hello Word"));
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"id","finish"));

        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("Facebook"));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("0"));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("1"));


    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
//
