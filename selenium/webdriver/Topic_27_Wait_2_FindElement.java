package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_27_Wait_2_FindElement {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10) );

        }

    @Test
    public void TC_01_FindElement() {
        driver.get("https://demo.nopcommerce.com");
        // Nếu như tìm element có duy nhất 1 cái
        // Không cần chờ hết tổng thời gian là 10s
        // Chuyển qua action tiếp theo luôn
        System.out.println("Start wait:" + getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("End wait:" + getDateTimeNow());

        // Nếu như tìm element có nhiều cái
        // Không cần chờ hết tổng thời gian là 10s
        // Nó luôn lấy element đầu tiên để thao tác
        System.out.println("Start wait:" + getDateTimeNow());
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("dam@gmail.com");
        System.out.println("End wait:" + getDateTimeNow());

        // Nếu như tìm element mà ko thấy
        // Cố gắng tìm đi tìm lại cứ 0.5s một lần
        // Nếu nửa chừng tìm thấy thì ko cần đi hết thời gian còn lại
        // Nêú hết time mà ko thấy thì show ra exception: NoSuchEle và đánh fail testcasr tại vị trí code
        // Nó ko chạy tiếp step tiếp theo còn lại nữa
        System.out.println("Start wait:" + getDateTimeNow());
        driver.findElement(By.cssSelector("input#testing"));
        System.out.println("End wait:" + getDateTimeNow());

    }

    @Test
    public void TC_02_FindElements() {
        driver.get("https://demo.nopcommerce.com");
        List<WebElement> elementList = null;

        // Nếu như tìm element có duy nhất 1 cái
        System.out.println("Start wait:" + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#small-searchterms"));
        System.out.println("Tổng số lượng element trong list:" + elementList.size());
        System.out.println("End wait:" + getDateTimeNow());

        // Nếu như tìm element có nhiều cái
        System.out.println("Start wait:" + getDateTimeNow());
        elementList = driver.findElements(By.xpath("//a[@href]"));
        System.out.println("Tổng số lượng element trong list:" + elementList.size());
        System.out.println("End wait:" + getDateTimeNow());
        for (WebElement element : elementList){
            System.out.println(element.getDomProperty("href"));
        }

        // Nếu như tìm element mà ko thấy
        System.out.println("Start wait:" + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println("End wait:" + getDateTimeNow());

        // Chờ hết timeout
        // Nếu hết timeout tìm thấy thì ko đánh fail
        // Trả về một list rỗng = 0
    }

    @AfterClass
    public void afterClass() {
            driver.quit();
    }

    private String getDateTimeNow() {
        return new Date().toString();
    }
}

