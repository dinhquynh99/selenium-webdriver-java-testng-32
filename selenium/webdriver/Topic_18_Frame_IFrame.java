package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_18_Frame_IFrame {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_IFrame_FormSite() throws InterruptedException {
        // Trang HTML A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(3000);

        // Switch qua ỉframe
        // Index: Page hiện tạo có nhiều ỉframe/frame
        // Frame/iframe đầu tiên sẽ có index = 0 và tăng dần lên
        // Khi thêm ms/update lại/ xoá đi sẽ thay đổi indẽ của các frame/iframe
        // driver.switchTo().frame(0);

        // id hơcj name (page có iframe/frame có id/name)
        // driver.switchTo().frame("frame-one85593366");

        // WebElement (có thể cover cả 2 cách trên)
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));


        // Element thuộc trang HTML B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        Thread.sleep(3000);

        // Từ B quay lại A
        driver.switchTo().defaultContent();

        // Driver đã quay lại A rồi
        driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();

        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

    }

    @Test
    public void TC_02_Iframe_ToiDiCodeDao() {
        driver.get("https://toidicodedao.com/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        By followerText = By.xpath("//a[@title ='Tôi đi code dạo']/parent::div/following-sibling::div[text()]");
        Assert.assertEquals(driver.findElement(followerText).getText(),"400,643 followers");

    }

    @Test
    public void TC_03_Iframe_ToiDiCodeDao() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(),"Customer ID/IPIN (Password) is invalid. Please try again.");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
