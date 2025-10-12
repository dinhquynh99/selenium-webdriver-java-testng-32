package TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Priority {

    @Test(priority = 1)
    public void CreateNewAccount (){
        System.out.println("Create account");

    }

    @Test(priority = 2)
    public void EditAccount () {
        System.out.println("Edit account");
    }

    @Test(enabled = false, description = "don't deploy")
    public void DeleteAccount () {
        System.out.println("Delete account");
    }

    //@Test
    public void CreateMultipleAccount () {
        System.out.println("Create multiple account");
    }
}