package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Slower");

        selectItemInCustomDropdown("span#number-button","ul#number-menu>li>div","10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"Slower");

        selectItemInCustomDropdown("span#salutation-button","ul#salutation-menu>li>div","Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Slower");

    }

    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");

    }

    @Test
    public void TC_03_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInCustomDropdown("input.search", "div.item>span", "Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Albania");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Argentina");

    }

    private void selectItemInCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {
        // Hành vi (behavior) để thao tác lên dropdown
        // 1. chờ cho dropdown có thể thao tác lên đc (clickable)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();

        // 2. click vào element nào để xổ nó ra cái dropdown nào
        // Driver.findElement(By.cssSelector(parentCss)).click();
        // Có thể viết chung lên 1.
        Thread.sleep(2000);

        // 3. Chờ cho tất cả các item đc load ra (present)
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        // 4. Tìm cái item nào đúng vs mong đợi
        // không cần nữa vì đã gộp ở bc 3
        //List<WebElement> allItems = driver.findElements(By.cssSelector(childCss));

        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                // 5. Click tên item đó
                item.click();
                break;
            }
        }

    }

    private void enterItemInCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {
        // 1. chờ cho dropdown có thể nhập đc (visible)
        // 2. sendkey vào dropdown
        WebElement dropdownTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
        dropdownTextbox.clear();
        dropdownTextbox.sendKeys(textItem);
        Thread.sleep(2000);

        // 3. Chờ cho tất cả các item đc load ra (present)
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        // 4. Tìm cái item nào đúng vs mong đợi
        // không cần nữa vì đã gộp ở bc 3
        //List<WebElement> allItems = driver.findElements(By.cssSelector(childCss));

        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                // 5. Click tên item đó
                item.click();
                break;
            }
        }

    }

    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}

