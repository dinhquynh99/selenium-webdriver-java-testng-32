package webdriver;

import graphql.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Webbrowser_Commands {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    }

    @Test
    public void TC_01_Element() {
        // Dùng để xoá dữ liệu trong 1 field đã nhập (editable)
        // Textbox/ TextArea/ Dropdown (Editable)
        // Thường đc sử dụng trc hàm senkeys
        driver.findElement(By.id("")).clear();

        // Dùng để nhập liệu vào các field trên
        driver.findElement(By.id("")).sendKeys("");

        // Dùng để click lên element
        driver.findElement(By.id("")).click();

        // Tìm từ node cha vào node con
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname"));
        driver.findElement(By.id("form-validate")).findElements(By.cssSelector("input"));

        driver.findElement(By.cssSelector("form#form-validate input#firstname"));

        // Trả về 1 element khớp vs đk
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        // Trả về nhiều element khớp vs đk
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));


        // Dùng để verìy 1 checkbox/ radio/ dropdown đã đc chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/custom)
        Select select = new Select(driver.findElement(By.id("")));

        // Dùng để verify một element bất kỳ xem có hiển thị ko
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify một element có được thao tác lên hay không (ko phải read-only)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        // HTML Element
        // <input> type="text" id="firstname" name="firstname" value=""
        // title="First Name" maxlength ="255" class="input-text required-entry">
        driver.findElement(By.id("")).getAttribute("title");
        driver.findElement(By.id("")).getAttribute("type");
        driver.findElement(By.id("")).getAttribute("id");

        // Tab Accesibility/ Properties trong element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURL");
        driver.findElement(By.id("")).getDomProperty("outerHTML");

        // Tab styles trong elements
        // font/ size/ color/ backgroud/ ...
        driver.findElement(By.id("")).getCssValue("backgroud-color");
        // rgb (46, 138, 184)
        driver.findElement(By.id("")).getCssValue("font-size");
        driver.findElement(By.id("")).getCssValue("text-transform");

        // Vị trí của element so vs độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        // Location + Size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        // Location
        Point namePoint = nameTextboxRect.getPoint();

        // Size
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        // shadow Element (JavascripExcutor)
        driver.findElement(By.id("")).getShadowRoot();

        // từ id/ class/ name/ css/ xpath có thể truy ra ngược lại tagname HTML
        driver.findElement(By.cssSelector("#firstname")).getTagName();
        driver.findElement(By.id("firstname")).getTagName();
        driver.findElement(By.className("form-instructions")).getTagName();
        driver.findElement(By.xpath("//*[@class='form-líst']")).getTagName();

        driver.findElement(By.cssSelector("address.copyright")).getText();

        // Chụp hình bị lỗi và lưu dưới dạng nào
        // BYTE
        // FILE (lưu thành 1 hình có kích thước ở trong ổ cứng: .png/ .jpg/...)
        // BASE64 (hình dạng text)
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64);

        // Form (element nào là thẻ form hoặc nằm trong thẻ form)
        // hành vi giống phím enter
        // Register/ Login/ Search/...
        driver.findElement(By.id("")).submit();

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
//
