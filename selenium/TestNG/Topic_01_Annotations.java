package TestNG;

import org.testng.annotations.*;

public class Topic_01_Annotations {

    @BeforeClass(groups = "test")
    public void method01() {
        System.out.println("BeforeClass");

    }

    @BeforeGroups(groups = "test")
    public void method02() {
        System.out.println("BeforeGroups");
    }

    @BeforeMethod(groups = "test")
    public void method03() {
        System.out.println("BeforeMethod");
    }

    @BeforeSuite(groups = "test")
    public void method04() {
        System.out.println("BeforeSuite");
    }

    @BeforeTest(groups = "test")
    public void method05() {
        System.out.println("BeforeTest");
    }

    @Test(groups = "test")
    public void method06() {
        System.out.println("Test01");
    }

    @Test(groups = "test")
    public void method07() {
        System.out.println("Test02");
    }

    @AfterTest(groups = "test")
    public void method08() {
        System.out.println("AfterTest");
    }

    @AfterSuite(groups = "test")
    public void method09() {
        System.out.println("AfterSuite");
    }

    @AfterMethod(groups = "test")
    public void method10() {
        System.out.println("AfterMethod");
    }

    @AfterGroups(groups = "test")
    public void method11() {
        System.out.println("AfterGroups");
    }

    @AfterClass(groups = "test")
    public void method12() {
        System.out.println("AfterClass");
    }

}
