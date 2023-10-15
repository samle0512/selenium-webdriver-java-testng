package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Run_More_Browser {

    WebDriver driver;
    String osName = System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");
    @Test
    public void TC_01_Run_On_Firefox() {
        driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        driver.quit();
    }
    @Test
    public void TC_02_Run_On_Chrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        driver.quit();
    }
    @Test
    public void TC_03_Run_On_Safari() {
        if (osName.contains("Mac")) {
            driver = new SafariDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://www.facebook.com/");
            driver.quit();
        }
    }
    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
