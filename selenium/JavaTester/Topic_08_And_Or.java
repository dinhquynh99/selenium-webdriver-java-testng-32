package JavaTester;

import org.testng.Assert;

public class Topic_08_And_Or {
    public static void main(String[] args) {
        String contactInformation = "Testing Automation\n" +
                "atomationfc1562024@gmail.com\n" +
                "Change Password";

        Assert.assertTrue(contactInformation.contains("Testing Automation")
                && contactInformation.contains("atomationfc1562024@gmail.com"));
    }
}
