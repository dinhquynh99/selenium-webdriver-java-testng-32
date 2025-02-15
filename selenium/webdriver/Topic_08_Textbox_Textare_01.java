package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Textbox_Textare_01 {

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
    public void Login_01_Emty_Email_And_Password() {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");


    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("123@345.678");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.net");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void Login_04_Incorect_Email_Or_Password() {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.net");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/loginPost/");

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

    }

    @Test
    public void Login_05_Success() {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        // 1. Đăng ký trc bằng tay 1 tài khoản email rồi dùng nó login cho case này
        // khi hệ thống reset dữ liệu thì phải đăng ký lại
        // DB đã bị xoá data
        // Qua 1 môi trường mới (dev/ Testing/ stg/ prod...)

        // 2. Sẽ dùng tính năng Registẻ trước - email cố định ko thay đổi
        // Chức năng register cũng auto luôn
        // Email khi dùng đăng ký lại fix cứng (1 lần)

        // 3. Sẽ dùng tính năng Registẻ trước - email cố định ko thay đổi (random)
        // Dùng luôn chạy cho tất cả các case


        // Đăng ký một tài khoản trc
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(3);

        String firstName = "Automation", lastName = "FC", emailAddress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastName;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/paret::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(3);

        // Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);

        driver.findElement(By.cssSelector("button#send2")).click();

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/paret::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Verify account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lasttname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);
    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "automation" + rand.nextInt(9999) + "gmail.net";
    }
}

