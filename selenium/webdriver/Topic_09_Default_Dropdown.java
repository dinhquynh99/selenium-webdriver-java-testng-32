package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Default_Dropdown {

    WebDriver driver;

    Select select;

    Actions actions;

    WebDriverWait expliciWait;

    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // Nếu class đó nhận driver làn tham số thì ms khai báo ở đây
        actions = new Actions(driver);
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Facebook_Signup() {
        driver.get("https://www.facebook.com/reg/");

        // dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        // select.deselectByIndex(20);

        // 1-Index
        // Index thay đổi vị trí
        // Đọc code có biết nó là tỉnh nào ko? => chạy fail -> reproduce lại -> 20 -> manual test

        // select.deselectByValue("9433");
        // 2-Value
        // Option ko bắt buộc phải có attribute value
        // Đọc code có biết nó là tỉnh nào ko? => chạy fail -> reproduce lại -> 20 -> manual test
        // thêm một số bước để biết nó là cái gì/ ở đâu

        // select.deselectByVisibleText("thành phố Hà Nội");
        // 3-Text
        // Giống vs end user chọn
        // ko bị trùng dữ liệu/ ko để trống các dữ liệu
        // ko thay đổi text nếu đổi vị trí

        // chọn 1 item
        select.selectByVisibleText("21");

        // Chọn xong verify lại đã chọn thành công hay chưa
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "21");

        // Verify cái dropdown có phải là multiple select hay ko?
        // nếu là multipal -> trả về là true
        // nếu là single -> trả về là false
        Assert.assertFalse(select.isMultiple());

        // verify tổng số lượng item trong dropdown là bn item?
        Assert.assertEquals(select.getOptions().size(),31);

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Jun");

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("2006");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"2006");

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}
