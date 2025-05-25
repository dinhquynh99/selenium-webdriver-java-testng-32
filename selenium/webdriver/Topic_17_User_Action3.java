package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

public class Topic_17_User_Action3 {

    WebDriver driver;
    Actions action;
    // JavascripExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        action = new Actions(driver);
        //jsExecutor = (JavascripExecutor) driver;
    }

    @Test
    public void TC_01_Drag_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(3000);

        Assert.assertEquals(targetCircle.getText(),"You did great!");

        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");

    }

    @Test
    public void TC_02_Drag_Drop_HTML5() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        //String jqueryDragDropContent = getContentFile(projectPath + "/dragDrop/dragAndDrop.js");

        /* WebElement sourceSquare = driver.findElement(By.cssSelector("div#column-a"));
        WebElement targetSquare = driver.findElement(By.cssSelector("div#column-b"));

        action.dragAndDrop(sourceSquare, targetSquare).perform();
        Thread.sleep(3000);

        action.clickAndHold(sourceSquare).moveToElement(targetSquare).release().perform();
        Thread.sleep(3000);*/

        /*public String getContentFile(String filePath) throws IOException {
            Charset cs = Charset.forName("UTF-8");
            FileInputStream stream = new FileInputStream(filePath);
            try {
                Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[8192];
                int read;
                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    builder.append(buffer, 0, read);
                }
                return builder.toString();
            } finally {
                stream.close();
            }
        }
         */


    }

        @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
