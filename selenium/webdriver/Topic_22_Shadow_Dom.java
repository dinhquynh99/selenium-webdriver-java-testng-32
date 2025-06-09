package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_22_Shadow_Dom {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://automationfc.github.io/shadow-dom/");

        // Thuộc DOM cha bên ngoài
        driver.findElement(By.xpath("//a[text()='scroll.html']"));

        // Element cha chứa shadow Host thứ 1
        WebElement shadowHosParent = driver.findElement(By.cssSelector("div#shadow_host"));

        // Lấy ra Element Shadow Root
        SearchContext firstShadow = shadowHosParent.getShadowRoot();

        Assert.assertTrue(firstShadow.findElement(By.cssSelector("input[type='file']")).isDisplayed());

        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(), "some text");

        // Element cha chứa shadow Host thứ 2
        WebElement firstShadowHosParent = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));

        // Lấy ra Element Shadow Root
        SearchContext secondShadow = firstShadowHosParent.getShadowRoot();

        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");

        // Alert => bắt buộc phải switch qua ms thao tác đc
        // Frame/ Iframe/ Window => bắt buộc phải switch qua ms thao tác đc, muốn quay lại phải switch lại
        // Shadow chỉ work vs cssSelector

    }

    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");

        WebElement firstShadowHostElement = driver.findElement(By.xpath("//book-app"));
        SearchContext firstShadowHost = firstShadowHostElement.getShadowRoot();
        Thread.sleep(5000);

        WebElement secondShadowHostElement = firstShadowHost.findElement(By.cssSelector("book-input-decorator"));
        SearchContext secondShadowHost = secondShadowHostElement.getShadowRoot();
        Thread.sleep(3000);

        firstShadowHost.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(5000);

        secondShadowHost.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        WebElement thirdShadowHostElement = firstShadowHost.findElement(By.cssSelector("Book-explore"));
        SearchContext thirdShadowHost = thirdShadowHostElement.getShadowRoot();

        WebElement forthShadowHostElement = thirdShadowHost .findElement(By.cssSelector("ul>li:nth-of-type(4)>book-item"));
        SearchContext forthtShadowHost = forthShadowHostElement.getShadowRoot();
        Thread.sleep(3000);

        Assert.assertEquals(forthtShadowHost.findElement(By.cssSelector("h2.title")).getText(),"Harry Potter and the Sorcerer's Stone");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
