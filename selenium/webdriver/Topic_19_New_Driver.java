package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_19_New_Driver {

    WebDriver userdriver;
    WebDriver admindriver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        userdriver = new FirefoxDriver();
        userdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(userdriver.toString());

        admindriver = new ChromeDriver();
        admindriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(admindriver.toString());
    }
    @Test
    public void TC_01_() {

    }
    @Test
    public void TC_02_() {

    }
    @Test
    public void TC_03_() {

    }
    @AfterClass
    public void afterClass() {
        userdriver.quit();
        admindriver.quit();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
