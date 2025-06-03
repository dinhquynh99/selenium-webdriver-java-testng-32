package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    public void TC_02_kyna() throws InterruptedException {
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
