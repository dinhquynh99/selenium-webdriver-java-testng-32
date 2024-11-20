package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
            driver.findElement(By.)
    }

    @Test
    public void TC_02_Class() {

    }

    @Test
    public void TC_03_Name() {

    }

    @Test
    public void TC_04_Tagname() {

    }

    @Test
    public void TC_05_LinkText() {

    }

    @Test
    public void TC_06_Partial_LinkText() {

    }

    @Test
    public void TC_07_Css() {

    }

    @Test
    public void TC_08_XPath() {

    }


    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
//



