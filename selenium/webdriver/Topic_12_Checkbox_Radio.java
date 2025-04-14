package webdriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Topic_12_Checkbox_Radio {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        // verify checkbox/ radio is enabled/disable
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        // verify checkbox/ radio is selected/ deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        // select to "Dual-zone air conditioning" checkbox
        By dualZoneAirBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Scroll xuống thêm 1 đoạn
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        // Nếu như chọn thì mới click
        if (!driver.findElement(dualZoneAirBy).isSelected()) {
            driver.findElement(dualZoneAirBy).click();
        }
        Assert.assertTrue(driver.findElement(dualZoneAirBy).isSelected());

        // Deselect to "Dual-zone air conditioning" checkbox (bỏ chọn)
        if (driver.findElement(dualZoneAirBy).isSelected()) {
            driver.findElement(dualZoneAirBy).click();
        }
        Assert.assertFalse(driver.findElement(dualZoneAirBy).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        By twopetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        if (!driver.findElement(twopetrol).isSelected()) {
            driver.findElement(twopetrol).click();
        }
        Assert.assertTrue(driver.findElement(twopetrol).isSelected());

    }

    @Test
    public void TC_02_Multiple() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Select all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));

        // click all checkboxes
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify all checkboxes selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // Deselect all checkboxes
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify all checkboxes deselected
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        // Select 1 in all + verify
        driver.findElement(By.cssSelector("input[value='Venereal Disease'")).click();
        driver.findElement(By.cssSelector("input[value='High Blood Pressure'")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Venereal Disease']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='High Blood Pressure']")).isSelected());

        // cách khác
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Heart Attack")) {
                checkbox.click();
            }
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Heart Attack']")).isSelected());

    }

    @Test
    public void TC_03_Ubunto() {
        driver.get("http://login.ubuntu.com");

        // Thẻ input: dùng để click
        // Dùng để verify: isSelected()

        // 1. Dùng thẻ input để click -> lỗi
        // Click register button

        // By newUserRadio = By.cssSelector("input#id_new_user");
        // driver.findElement(newUserRadio).click();
        // Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

        // 2. Dùng thẻ khác input để click -> pass
        // Dùng thẻ đó để verify -> fail
        // isSelected() -> Dùng cho thẻ input/option

        // By newUserRadio = By.cssSelector("label.new-user");
        // driver.findElement(newUserRadio).click();
        // Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        // 3.  Dùng thẻ khác input để click -> pass
        // Dùng thẻ input để verify -> pass
        // By newUserRadioLabel = By.cssSelector("input#id_new_user");
        // By newUserRadioInput = By.cssSelector("label.new-user");
        // driver.findElement(newUserRadioLabel).click();
        // Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        // 4. Dùng duy nhất thẻ input để click/ verify dùng JS Executor
        By newUserRadioInput = By.cssSelector("input#id_new_user");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
    }

    @Test
    public void TC_04_Docs() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(5000);

        By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
        driver.findElement(hcmRadio).click();
        //Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='true'")).isDisplayed());
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        By quangNoodleCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");

        // check
        if (driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked").equals("false")) {
            driver.findElement(quangNoodleCheckbox).click();
        }
        Assert.assertEquals(driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked"), "true");

        // uncheck
        if (driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked").equals("true")) {
            driver.findElement(quangNoodleCheckbox).click();
        }
        Assert.assertEquals(driver.findElement(quangNoodleCheckbox).getAttribute("aria-checked"), "false");

    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}