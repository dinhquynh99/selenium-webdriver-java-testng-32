package JavaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến được khai báo ở ngoài hàm => phạm vi là class
    // Biến Global (toàn cục)
    // Có thể dungf cho tất cả các hàm trong 1 class

    WebDriver driver;

    String homePageUrl;  // Mới chỉ đang khái báo

    String fullName = "Automation FC";  // Khai báo + khởi tạo (Initial)

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_() {
        // Các biến được khai báo ở trong 1 hàm/block code => Phạm vi cục bô (local)
        // Chỉ dùng trong cái hàm mà nó được khai báo/Block code được sinh ra
        String homePageUrl = "https://www.facebook.com/";

        // Trong 1 hàm nếu như có 2 biến cùng tên (Global/local0 thì sẽ ưu tiên lấy biến local dùng
        // 1 biến local nếu như gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        // Biến local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (complie code)
        driver.get(homePageUrl);

        // Nếu trong 1 hàn có 2 biến cùng tên (global/local) mà muốn lấy biến global ra để dùng thì
        // Dùng từ khoá this
        // Biến global chưa khởi tạo mà gọi ra dùng thì ko báo lỗi ở level (complie code)
        // Level runtime  sẽ bị lỗi
        driver.get(this.homePageUrl);

    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }

    @Test
    public void TC_04_() {

    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
