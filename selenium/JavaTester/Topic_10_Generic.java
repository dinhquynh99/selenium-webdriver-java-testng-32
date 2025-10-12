package JavaTester;

import java.io.File;
import java.util.ArrayList;

public class Topic_10_Generic {
    public static void main(String[] args) {
        // Non-Generic
        ArrayList student = new ArrayList<>();
        student.add("Nguyễn Văn Ký");
        student.add("25");
        student.add("9.5");
        student.add("true");

        // Generic
        ArrayList<String> std = new ArrayList<String>();
        std.add("Nguyễn Văn Ký");
    }
}
