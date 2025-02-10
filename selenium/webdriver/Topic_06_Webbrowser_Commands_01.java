package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_Webbrowser_Commands_01 {
    // Các câu lệnh để thao tác vs Browser
    // driver.
    // giống như một cái class hoặc interface nào đại diện cho tất cả các loại driver
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

        // Nó sẽ đi tìm loại By nào trả về 1 danh sách element nếu như đc tìm thấy (List WebElement)
        // ko đc tìm thấy - ko bị fail - trả về 1 list rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox]"));
        checkboxes.get(1).click();

        driver.findElement(By.cssSelector("button#login")).click();

        // Tại sao lại cần phải lấy dữ iệu ra để làm gì?
        // Dùng để lấy ra Url của màn hình/page hiện tại đang dùng
        String loginPageUrl = driver.getCurrentUrl();
        driver.getCurrentUrl();

        // Lấy ra page source HTML/ CSS/ JS của page hiện tại
        // Verìy một cách tương đối
        driver.getPageSource();
        driver.getCurrentUrl().contains("The Apple and Google Play logos");
        Assert.assertTrue(driver.getCurrentUrl().contains("The Apple and Google Play logos"));
        // Assert.assertEquals(driver.getCurrentUrl(),"The Apple and Google Play logos");
        // Không dùng assertEquals vì đây là verify tương đối

        // Lấy ra title của pgae hiện tại
        driver.getTitle();

        // lấy ra id của cửa sổ/tab hiện tại
        // Handle Windown/ Tab
        // system.out.println("Windown/ Tab ID = " + driver.getWindownHandle());
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Cookies - Framework
        driver.manage().getCookies();

        // Get ra những log ở dev tool - Framework
        driver.manage().logs().get(LogType.DRIVER);

        // Apply cho việc tìm element (FindElement/ FindElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        // Chờ chp một page đc load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trc khi dùng vs thư viện JavascripExecutor
        // Inject một đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selenium 4 ms có nhưng thường ko dùng
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // chayj full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        // Test responsive (resolution)
        driver.manage().window().setSize(new Dimension(1366,768));

        driver.manage().window().getSize();

        // Set cho browser ở vị trí nào so với độ phân giải màn hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác vs history của web page (back/forward)
        driver.navigate().to("https://www.facebook.com/");
        //driver.navigate().to(new URL("https://www.facebook.com/"));

        driver.get("https://www.facebook.com/");

        // Alert/ Windown (Tab)/ Frame (1Frame)
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // Lấy ra ID của cửa sổ/ tab hiện tại
        // Handle window/ tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iframe)
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("39493494");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chưa frame trc đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();



        // Nếu chỉ dùng 1 lần thì ko khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"Ç");

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

