package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {

        WebDriver driver;

        String projectPath = System.getProperty("user.dir");

        String osName = System.getProperty("os.name");

        @BeforeClass
        public void beforeClass() {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    @Test
    public void TC_01_Relative() {
    driver.get("https://demo.nopcommerce.com/login?ReturnUrl=%2Fvendor%2Fapply");

        // Login button
        By loginButtonBy = By. cssSelector("button.login");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        // Forgot Password link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        // Remember Me checkbox
        By rememberMyCheckboxBy = By.id("RememberMe");

        // Password textbox
        By passwordTextboxBy = By.cssSelector("input#Password");

        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)
                .toRightOf(rememberMyCheckboxBy)
                .toLeftOf(forgotPasswordElement)
                .below(passwordTextboxBy)
                .near(forgotPasswordElement));
        System.out.println(rememberMeTextElement.getText());

        List<WebElement> alllinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.println(alllinks.size());
    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
