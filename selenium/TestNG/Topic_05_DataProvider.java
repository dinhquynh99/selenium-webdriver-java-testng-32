package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_05_DataProvider {
    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, employeeID, passportNumber, passportComment;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @DataProvider(name = "employee")
    public Object[][] employeeData() {
        return new Object[][]{
                {"Petery", "Crouchy", "petery" + new Random().nextInt(9999) + "@hotmail.com", "Testing111y###"},
                {"John", "Terry", "john" + new Random().nextInt(9999) + "@hotmail.com", "Testing111###"},
                {"Luka", "Modric", "luka" + new Random().nextInt(9999) + "@hotmail.com", "Testing111###"},
        };
    }

    @Test(dataProvider = "employee")
    public void TC_02_OrangeHRM(String firstName, String lastName, String emailAddress, String password) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // Wait for all loading icon disappear
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify Dashboard page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Click to PIM Link
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify PIM page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        // Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // Create new Employee
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(2000);


        // Verify success message displayed
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());

        // loading icon at add employee page
        Assert.assertTrue(isLoadingIconDisappear());

        // loading personal detail page
        Assert.assertTrue(isLoadingIconDisappear());


        // Personal detail page
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);

        // logout
        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    private Boolean isLoadingIconDisappear() {
        try {
            // Check if the loading icon disappears by waiting for it to no longer be visible
            WebElement loadingIcon = driver.findElement(By.cssSelector("your-loading-icon-selector"));
            return !loadingIcon.isDisplayed();
        } catch (NoSuchElementException e) {
            // If no element is found, assume that the icon has disappeared
            return true;
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}