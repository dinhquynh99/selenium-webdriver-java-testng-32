package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_User_Action2 {

    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        action = new Actions(driver);
        // cách 1 để dùng ctr hay command
        if (osName.startsWith("Windowns")){
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        // cách 2 để dùng ctr hay command
        keys = osName.startsWith("Windowns") ? Keys.CONTROL : Keys.COMMAND;
    }

    @Test
    public void TC_01_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Luôn lấy thằng đầu tiên dù có nhiều locator
        //driver.findElement(By.cssSelector("ol#selecable>li"));

        List<WebElement> allNumber =  driver.findElements(By.cssSelector("ol#selectable>li"));

        action.clickAndHold(allNumber.get(0)) // Click vào số 1 và giữ chuột
                .moveToElement(allNumber.get(3))  //Di chuột tới số 4
                .release()  // Nhả chuột trái ra - kết thúc cho sự kiện clickAndHold()
                .perform();  // Thực thi câu lệnh trên (nếu ko có ko thực thi)
        Thread.sleep(2000);

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);

    }

    @Test
    public void TC_02_Click_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 20);

        // Nhấn phím ctr trc nhưng ko nhả ra
        action.keyDown(Keys.COMMAND).perform();

        // 1 4 8 11 14 16 18
        action.click(allNumber.get(0))
                .click(allNumber.get(3))
                .click(allNumber.get(7))
                .click(allNumber.get(10))
                .click(allNumber.get(13))
                .click(allNumber.get(15))
                .click(allNumber.get(17))
                .pause(Duration.ofSeconds(3))
                .perform();

        // Nhả ctr ra
        action.keyUp(Keys.COMMAND).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 7);

    }

    @Test
    public void TC_03_Double_Click() {
        driver.get("https://automationfc.io/basic-form/index.html");

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guy!");
    }

    @Test
    public void TC_04_Right_Click() throws InterruptedException {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

       // Click chuột phải vào button
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        Thread.sleep(3000);

        By quitContextBy = By.cssSelector("li.context-menu-icon-quit");

        Assert.assertTrue(driver.findElement(quitContextBy).isDisplayed());

        // Hover mouse
        action.moveToElement(driver.findElement(quitContextBy)).perform();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        // Click quit
        action.click(driver.findElement(quitContextBy)).perform();
        Thread.sleep(3000);

        driver.switchTo().alert().accept();

        Assert.assertFalse(driver.findElement(quitContextBy).isDisplayed());
    }


        @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
