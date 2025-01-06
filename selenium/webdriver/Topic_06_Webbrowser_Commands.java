package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_Webbrowser_Commands {
    // Các câu lệnh để thao tác vs Browser
    // driver.
    // giống như một cái class hoặc interface nào đâij diện cho tất cả các loại driver
    WebDriver driver;


    // nếu dùng cách này thì chỉ trình duyệt nào khai báo ms có thể dùng đc
    // nếu không khai báo mà nên dưới new sẽ bị lỗi
    FirefoxDriver ffDriver;

    ChromeDriver chDriver;

    EdgeDriver edgeDriver;


    // Các câu lệnh thao tác vs Elemnt
    // element.

    WebElement element;


    @BeforeClass
    public void beforeClass() {
        // Muốn dùng đc phải khởi tạo
        // Nếu không khởi tạo sẽ gặp lỗi : NullPointerException
        // 1 biến chưa đc khởi tạo nhưng đã đc gọi ra để dùng
        driver = new FirefoxDriver();

        // Selenium ver 3/2/1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        ffDriver = new FirefoxDriver();

        chDriver = new ChromeDriver();

    }

    @Test
    public void TC_01_() {
        // 1. Set trực tiếp vào
        driver.get("https://www.facebook.com/");

        // 2. Khai báo biến rồi gán vào
        // Nếu biến chỉ dùng duy nhất 1 lần thì ko nên tạo biến
        String homePageUrl = "https://www.facebook.com/";
        driver.get("homePageUrl" + homePageUrl);
        driver.get(homePageUrl);   // Dùng biến, biến ko nằm trong dấu ngoặc kép

        // Nếu như có 1 tab/windown thì tính năng tự quit
        // Nhiều hơn 1 thì nó sẽ đóng cái nó đang active
        driver.close();

        // Đóng browser (Không care có bao nhiêu tab/window)
        driver.quit();

        // 2 hàm này bị ảnh hưởng timeout của impliciwait
        // findElement/ findElements

        // Nó sẽ đi tìm vs loại By nào trả về element nếu như đc tìm thấy
        // Ko đc tìm thấy : Fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (Thao tác vs cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        // Nó sẽ đi tìm laoi By nào trả về 1 danh sách element nếu như đc tìm thấy (List WebElement)
        // ko đc tìm thấy - ko bị fail - trả về 1 list rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox]"));
        checkboxes.get(1).click();

        driver.findElement(By.cssSelector("button#login")).click();

        // Tại sao lại cần phải lấy dữ iệu ra để làm gì?
        String loginPageUrl = driver.getCurrentUrl();
        driver.getCurrentUrl();
        driver.getPageSource();
        driver.getTitle();
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Nếu chỉ dùng 1 lần thì ko khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        // Dùng lại nhiều lần (từ 2 lần trở lên)
        Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");

        // step 15

        // step 20
        driver.get(loginPageUrl);
        
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}

