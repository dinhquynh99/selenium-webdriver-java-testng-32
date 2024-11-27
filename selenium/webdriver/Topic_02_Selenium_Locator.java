package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.xpath.XPathExpression;
import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

        WebDriver driver;

        String projectPath = System.getProperty("user.dir");

        String osName = System.getProperty("os.name");

        @BeforeClass
        public void beforeClass() {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://demo.nopcommerce.com/register");
        }

    //Selenium version : 1.X/ 2.X/ 3.X/ 4.X
    // 8 loại locator
    // id/ Class/ Name = Trùng vs 3 loại attribute cuar HTML
    // LinkText/ Partial LinkText : HTML link (thẻ a)
    // Tagname : HTML Tagname
    // Css/ XPath

    // Selenium version 4.x - GUI
    // Class - Relative Locator
    // above/ bellow/ near/ lesfof/ righof

    //testNG : order testcase theo Alphabet (0-9 A-Z)
    // HTML Element : <Tagname attribute_name_1='attribute_value' attribute_name_2='attribute_value'...>
    // by la một class của selenium

    @Test
    public void TC_01_ID() {
            // Tìm element có ì là FirstName
             driver.findElement(By.id("FirstName")).sendKeys("Keane");
             System.out.println(driver.findElement(By.id("FirstName")));
    }

    @Test
    public void TC_02_Class() {
            driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
            driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_Tagname() {
            driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
            driver.findElement(By.linkText("Shipping & returns"));
    }
    // linkText sẽ lấy toàn bộ => độ chính xác cao

    @Test
    public void TC_06_Partial_LinkText() {
            driver.findElement(By.partialLinkText("for vendor"));
    }
    // Partial_LinkText lấy 1 phần text nhưng độ chính xác ko cao

    @Test
    public void TC_07_Css() {
            // CSS vs ID
            driver.findElement(By.cssSelector("input[id='FirstName']"));
            driver.findElement(By.cssSelector("input#FirstName"));
            driver.findElement(By.cssSelector("#FirstName"));

            // CSS vs Class
            driver.findElement(By.cssSelector("div[class='page-title']"));
            driver.findElement(By.cssSelector("div.page-title"));
            driver.findElement(By.cssSelector(".page-title"));

            // CSS vs Name
            driver.findElement(By.cssSelector("input[name='FirstName']"));

            // Css vs tagname
            driver.findElement(By.cssSelector("input"));

            // Css vs link
            driver.findElement(By.cssSelector("a[href='/customer/addrssed']"));

            // Css vs partial Link
            driver.findElement(By.cssSelector("a[href*='addrssed']"));
            // driver.findElement(By.cssSelector("a[href^='addrssed']");
            // driver.findElement(By.cssSelector("a[href$='ddrssed']");
    }

    @Test
    public void TC_08_XPath() {
            // Xpath vs ID
            driver.findElement(By.xpath("//input[@id='FirstName']"));

            // Xpath vs Class
            driver.findElement(By.xpath("//div[@class='page-title']"));

            // Xpath vs Name
            driver.findElement(By.xpath("//input[@name='FirstName']"));

            // Xpath vs tagname
            driver.findElement(By.xpath("//input"));

            // Xpath vs link
            driver.findElement(By.xpath("//a[@href='/customer/addrssed']"));
            driver.findElement(By.xpath("//a[@text()='Addrssed']"));

            // Xpath vs partial Link
            driver.findElement(By.xpath("a[contain(@href,'addrssed')]"));
            driver.findElement(By.xpath("a[contain(text(),'addrssed')]"));
    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}



