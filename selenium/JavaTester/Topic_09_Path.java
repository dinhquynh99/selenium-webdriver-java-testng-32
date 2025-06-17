package JavaTester;

import org.testng.Assert;

import java.io.File;

public class Topic_09_Path {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        String osName = System.getProperty("os.name");
        System.out.println(osName);

        String seperator = File.separator;
        System.out.println(seperator);
    }
}
