package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_21_Windown_Tab {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Lấy ra ID của tab/Window mà driver đang active tại page đó
        String githubWindowID = driver.getWindowHandle();
        System.out.println("Page ID =" + githubWindowID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // Click vào google link => nó sẽ bật lên 1 tab mới và tự nhảy qua
        // Nhưng về code Selenium là driver ko tự nhảy ra - nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(2000);

        System.out.println("Github Windown ID =" + driver.getWindowHandle());

        // Switch qua tab của google
        switchWindowByID(githubWindowID);
        Thread.sleep(2000);

        String googleWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium Webdriver");
        Thread.sleep(2000);

        // Switch về tab Git
        switchWindowByID(googleWindowID);
        Thread.sleep(2000);

        // Click vào facebook link => nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Facebook - log in or sign up");

        // Quay về Github
        switchToWindowByTitle("Selenium WebDriver");

        // Click vào Tiki link => nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Thread.sleep(2000);

        closeAllWindowWithoutParent(githubWindowID);

        // Sau khi chạy hết thì sẽ close hết các tab/windown ngoại trừ Github
        // Driver nó đang active/ đứng ở window/tab nào?

    }

    private void closeAllWindowWithoutParent(String githubWindowID) throws InterruptedException {
        // Lấy hết toàn bộ ID của Windown/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {
            if (!id.equals(githubWindowID)){
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }

        // Switch vào window duy nhất còn lại
        driver.switchTo().window(githubWindowID);
    }

    private void switchToWindowByTitle(String expectedPageTitle) {
        // Lấy hết toàn bộ ID của Windown/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {
            // Mỗi lần duyệt sẽ cho nó switch vào trc
            driver.switchTo().window(id);

            // Get ra title của window/tab hiện tại
            String pageTitle = driver.getTitle();

            // Kiểm tra title
            if (pageTitle.equals(expectedPageTitle)){
                break;
            }
        }
    }

    // Chỉ đúng vs 2 window/tab
    private void switchWindowByID(String windowID) {
        // Lấy ra hết tất cả các ID của windown/ tab hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng ID một
        for (String id : allWindowIDs) {
            // Kiểm tra điều kiện: nếu ID nào khác vs ID mong đợi thì switch qua
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // Click vào add to compare button at Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/" +
                "following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Sony Xperia has been added to comparison list.");

        // Click to add to compare button at Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/" +
                "following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Products Comparision List - Magento Commerce");

        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/catalog/product_compare/index/");

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Mobile");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.switchTo().alert().getText(),
                "Are you sure you would like to remove all products from your comparison?");
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The comparison list was cleared.");
    }

    @Test
    public void TC03_Cambride() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Login");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(
                "input[name='username']~span.gigya-error-msg-active")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("" +
                        "input[name='password']~span.gigya-error-msg-active")).getText(), "This field is required");

        driver.close();

        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("code");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button[title='Tìm kiếm']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(
                "div#cald4-1~div span.headword>span")).getText(),"code");
    }

    @Test
    public void TC04_Senenium_4x() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);

        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.switchTo().newWindow(WindowType.TAB)
                .get("https://live.techpanda.org/index.php/customer/account/");

        System.out.println("Driver ID = " + driver.toString());
        System.out.println("Window ID = " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        switchToWindowByTitle("Mobile");
        Thread.sleep(3000);

        // Click vào add to compare button at Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/" +
                "following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Sony Xperia has been added to comparison list.");

        // Click to add to compare button at Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/" +
                "following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Products Comparision List - Magento Commerce");

        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/catalog/product_compare/index/");

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        Thread.sleep(3000);

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
