package JavaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Topic_01_Data_Type {
    // Kiểu dữ liệu trong Java - 2 loại

    // 1 - kiểu dữ liệu nguyên thuỷ (Primitive Type) : 8 loại
    // số nguyên : byte - shore - int - long
    // Không có phần thập phân: nhân viên trong 1 cty/ học sinh trong 1 lớp/ trường...

    byte bNumber = 40;

    short sNumber = 3000;

    int iNumber = 15635658;

    long lNumber = 234343400;

    // số thực : float - double
    // có phần thập phân: đếm số/ lương...

    float fNumber = 9.99f;

    double dNumber = 35.800789d;

    // ký tự : char
    // Đại diện cho 1 ký tự (không đc dùng nháy đôi)

    char c='M';

    // Logic : boolean

    boolean status = true;


    // 2 - Kiểu dữ liệu tham chiếu (Reference Type)
    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();

    Select select = new Select(firefoxDriver.findElement(By.className("")));

    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();

    // Interface

    WebDriver driver;

    // Object

    Object name = "Automation FC";

    // Array (Kiểu nguyên thủy/ tham chiếu)
    int [] studentAge = {15, 20, 8};
    String [] studentName = {"Automation", "Testing"};

    // Collection: List/ Set/ Queue

    List<String> studenAddress = new ArrayList<String>();

    List<String> studenCity = new LinkedList<>();

    // String - chuỗi ký tự

    String fullName ="Nguyen Van A";

    // Khai báo biến để lưu trữ 1 loại dữ liệu nào đí
    // Access Modifier: Phạm vi truy cập (Public/ private/ protected/ default)
    // Kiểu dữ liệu
    // Tên biến
    // Giá trị của biến - gán vào vs phép =
    // Nếu như không gán: dữ liệu mặc định = 0
}
