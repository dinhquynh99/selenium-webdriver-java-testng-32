package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_19_Fixed_Popup {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[contains(text(), 'Đăng nhập')]")).click();

        By loginPopup = By.xpath ("//div[@class='auth-form']");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),"Tài khoản không tồn tại!");

        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_02_kyna() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By loginPopup = By.cssSelector("div#k-popup-account-login-mb>div.modal-dialog");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc.vn");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Tiki () throws InterruptedException {
        driver.get("https://tiki.vn/");

        // Popup 01 - Marketing
        // Hiển thị cố định khi mở site ra
        // Khi đóng lại thì ko còn trong HTML nữa
        // Khi refresh lại page thì lại hiển thị ra

        By marketingPopup = By.cssSelector("div#VIP_BUNDLE");

        // Kiểm tra hiển thị => Hiển thị cố định khi mở site ra
        Assert.assertTrue(driver.findElement(marketingPopup).isDisplayed());

        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
        Thread.sleep(3000);

        // Kiểm tra hiển thị => Khi đóng lại thì ko còn trong HTML nữa
        // Vì element ko có trong HTML nên hàm findElemnt sẽ ko tìm thấy => Đánh fail ngay ở step này
        //Assert.assertFalse(driver.findElement(marketingPopup).isDisplayed());
        Assert.assertEquals(driver.findElements(marketingPopup).size(),0);
    }

    @Test
    public void TC_04_Facebook_Not_In_HTML () throws InterruptedException {
        driver.get("https://www.facebook.com/");
        // hiện tại trang đã thay đổi ko còn popup nữa nên chạy sẽ fail => chỉ mang tính chất tham khảo

        // findElement -> click()
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        By sigupPopupBy = By.xpath("//div[@class='registration_redesign']/parent::div/parent::div");

        // Kiểm tra hiển thị
        Assert.assertTrue(driver.findElement(sigupPopupBy).isDisplayed());

        // Close đi
        driver.findElement(By.xpath("//div[@class='registration_redesign']/parent::div/preceding-sibling::img")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElements(sigupPopupBy).size(),0);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
