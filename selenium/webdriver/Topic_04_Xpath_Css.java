package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_Css {

        WebDriver driver;
        String projectPath = System.getProperty("user.dir");
        String osName = System.getProperty("os.name");

        @BeforeClass
        public void beforeClass() {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

    @Test
    public void Register_01_Empty_Data() {
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");

            //Action
            driver.findElement(By.id("txtFirstname")).clear();
            driver.findElement(By.id("txtEmail")).clear();
            driver.findElement(By.id("txtCEmail")).clear();
            driver.findElement(By.id("txtPassword")).clear();
            driver.findElement(By.id("txtCPassword")).clear();
            driver.findElement(By.id("txtPhone")).clear();
            driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

            //String emailAddressErrorMessage = driver.findElement(By.id("txtFirstname-error")).getText();
            //Assert.assertEquals(emailAddressErrorMessage, expected:"Vui lòng nhập họ tên");
            //Verify
            Assert.assertEquals(driver.findElement(By.id("txtFirtname-error")).getText(),"Vui lòng nhập họ tên");
            Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
            Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lai địa chỉ email");
            Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
            Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
            Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void Register_02_Invalid_Email_Address() {
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");

            //Action
            driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
            driver.findElement(By.id("txtEmail")).sendKeys("123@123@123");
            driver.findElement(By.id("txtCEmail")).sendKeys("123@123@123");
            driver.findElement(By.id("txtPassword")).sendKeys("123456");
            driver.findElement(By.id("txtCPassword")).sendKeys("123456");
            driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
            driver.findElement(By.xpath( "//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

            //Verify
            Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
            Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }

    @Test
    public void Register_04_Invalid_Password() {
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");


    }

    @Test
    public void Register_05_Incorrect_Confirm_Password() {
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");


            //Password less than 6 chars
            driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
            driver.findElement(By.id("txtEmail")).sendKeys("joe@biden.com");
            driver.findElement(By.id("txtCEmail")).sendKeys("joe@biden.com"");
            driver.findElement(By.id("txtPassword")).sendKeys("123"");
            driver.findElement(By.id("txtCPassword")).sendKeys("123");
            driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
            driver.findElement(By.xpath( "//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

            //Verify
            Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
            Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_06_Invalid_Phonenumber() {
           driver.get("https://alada.vn/tai-khoan/dang-ky.html");


           //Phonenumber less than 10 chars
           driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
           driver.findElement(By.id("txtEmail")).sendKeys("joe@biden.com");
           driver.findElement(By.id("txtCEmail")).sendKeys("joe@biden.com"");
           driver.findElement(By.id("txtPassword")).sendKeys("123456");
           driver.findElement(By.id("txtCPassword")).sendKeys("123456");
           driver.findElement(By.id("txtPhone")).sendKeys("098765432");
           driver.findElement(By.xpath( "//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

           //Verify
           Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

           //Phone more than 11 chas
           driver.findElement(By.id("txtPhone")).clear();
           driver.findElement(By.id("txtPhone")).sendKeys("09876543213");
           driver.findElement(By.xpath( "//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

           //Verify
           Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

           //Phone # Phone Center number
           driver.findElement(By.id("txtPhone")).clear();
           driver.findElement(By.id("txtPhone")).sendKeys("12345678910");
           driver.findElement(By.xpath( "//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

           //Verify
           Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
