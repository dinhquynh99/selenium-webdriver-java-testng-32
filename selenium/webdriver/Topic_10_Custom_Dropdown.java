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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // Hành vi (behavior) để thao tác lên dropdown
        // 1. chờ cho dropdown có thể thao tác lên đc (clickable)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span#speed-button>span.ui-selectmenu-icon")));

        // 2. click vào element nào để xổ nó ra cái dropdown nào
        driver.findElement(By.cssSelector("span#speed-button")).click();
        Thread.sleep(2000);

        // 3. Chờ cho tất cả các item đc load ra (present)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu>li>div")));

        // 4. Tìm cái item nào đúng vs mong đợi
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#speed-menu>li>div"));
        // 5 items

        for (WebElement item: allItems){
            if (item.getText().equals("Faster")){
                // 5. Click tên item đó
                item.click();
                break;
            }
        }

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
//
