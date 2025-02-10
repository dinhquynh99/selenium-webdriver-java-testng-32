package JavaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Assert {

    WebDriver driver;

    @Test
    public void verifyTestNG(){
        driver = new FirefoxDriver();

        driver.get("https://www.facebook.com/");


        // Trong java có nhiều thư viện để verify dữ liệu
        // Testng Framework (Unit/ Interation/ UI Automation Test)
        // JUnit 4/ JUnit 5/ TestNG/ Hamcrest/ AssertJ/ ...

        // Kiểu dữ liệu nhận vào là boolean (true/ False)
        // Khi mong muốn kiểu dữ liệu trả về là đúng thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and Share with the people in your life."));

        // Mong muốn trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));

        // Các hàm trả về kiểu dữ liệu boolean (sẽ dùng assert)
        // Quy tắc: bắt đầu tiền tố là isxxx
        // WebElement
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        // Mong đợi một đk giống với thực tế
        // Actual = Expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
        Assert.assertEquals(driver.findElement(By.id("")).getText(),"Create a new account");


        // Unit Test
        Object name = null;
        Assert.assertNull(name);

        name = "Testing";
        Assert.assertNotNull(name);

    }
}
