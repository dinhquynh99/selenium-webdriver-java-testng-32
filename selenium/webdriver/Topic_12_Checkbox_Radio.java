package webdriver;

import graphql.Assert;
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

public class Topic_12_Checkbox_Radio {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
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
            if (!checkbox.isSelected()){
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
        for (WebElement checkbox : checkboxes){
            Assert.assertFalse(checkbox.isSelected());
        }

        // Select 1 in all + verify
        driver.findElement(By.cssSelector("input[value='Venereal Disease'")).click();
        driver.findElement(By.cssSelector("input[value='High Blood Pressure'")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Venereal Disease']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='High Blood Pressure']")).isSelected());

        // cách khác
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Heart Attack")){
                checkbox.click();
            }
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Heart Attack']")).isSelected());

    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
//
