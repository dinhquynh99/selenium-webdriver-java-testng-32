package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Relative_Locator {

        WebDriver driver;

        String projectPath = System.getProperty("user.dir");

        String osName = System.getProperty("os.name");

        @BeforeClass
        public void beforeClass() {}

    @Test
    public void TC_01_Relative() {
    driver.get("https://demo.nopcommerce.com/login?ReturnUrl=%2Fvendor%2Fapply");

        // Login button
        By loginButtonBy = By. cssSelector("button.login");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        RelativeLocator.with(By.tagName("label")).above(loginButtonElement);

        // Remember Me checkbox
        By rememberMyCheckboxBy = By.id("RememberMe");

        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)
                .toRightOf(rememberMyCheckboxBy));
        System.out.println(rememberMeTextElement.getText());
    }

    @AfterClass
    public void afterClass() {

            driver.quit();
    }
}
