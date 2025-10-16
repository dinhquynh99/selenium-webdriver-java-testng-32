package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_07_Multiple_Enviroment {

    WebDriver driver;
    String serverURL;

    @Parameters({"serverName", "browserName"})
    @BeforeClass
    public void beforeClass(String serverName, String browserName) {

        switch (serverName) {
            case "Dev":
                serverURL= "https://www.dev.fahasa.com";
                break;
            case "Test":
                serverURL= "https://www.test.fahasa.com";
                break;
            case "Live":
                serverURL= "https://www.fahasa.com";
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "Edge":
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }
    }

    @Test
    public void TC_01_Fahasa() {
        driver.get(serverURL + "/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        // isEnabled: nếu Element mà nó enabled thì trả về true/ disable trả về false
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).
                getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654888");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

        // Mong đợi nó là enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).
                getCssValue("background-color")).asHex().toUpperCase(), "#000000");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
