package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        Color loginColor = Color.getColor(loginBackgroundColor);
        Assert.assertNull(loginColor.asHex().toUpperCase(),"#000000")

        // chỉ cần 1 dòng code
        // Assert.assertEquals(Color.getColor(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dao@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

        // Mong đợi nó là enabled
        Assert.assertTrue(driver.findElement(loginButton)).isEnabled();

        Assert.assertNull(Color.getColor(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
