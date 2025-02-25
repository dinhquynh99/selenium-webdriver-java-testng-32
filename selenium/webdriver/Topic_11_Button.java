package webdriver;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Topic_11_Button {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        // isEnabled: nếu Element mà nó enabled thì trả về true/ disable trả về false
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        String loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        Color loginColor = Color.fromString(loginBackgroundColor);
        Assert.assertEquals(loginColor.asHex().toUpperCase(), "#000000");

        // chỉ cần 1 dòng code
        // Assert.assertEquals(Color.getColor(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

        // Là tổng hợp của các dòng sau
        // WebElement loginButtonElement = driver.findElement(loginButton);
        // String loginButtonRGBA = loginButtonElement.getCssValue("background-color") = > để get RGBA
        // Color loginButtonColor = Color.fromString(loginButtonRGBA);
        // String loginButtonHexa = loginButtonColor.asHex();  =>lấy ra màu hex #c9F9f9
        // String LoginButtonHexUppercase = loginButtonHexa.toUpperCase();


        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dao@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

        // Mong đợi nó là enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

        driver.findElement(loginButton).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(), "Số điện thoại/Email hoặc mật khẩu sai!");

    }

    @Test
    public void TC_02_() {
        driver.get("https://play.goconsensus.com/u5d5156fd");

        Assert.assertFalse(driver.findElement(By.cssSelector("button[data-testid='lead form continue']")).isEnabled());

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
