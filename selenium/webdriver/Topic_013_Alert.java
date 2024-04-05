package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_013_Alert {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSeconds(2);

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

    }
    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSeconds(2);

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
    }
    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSeconds(2);

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text = "Selenium Webdriver";
        alert.sendKeys(text);
        sleepInSeconds(3);

        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
    }
    @Test
    public void TC_04_Authentication_PassToURL() {

        String username ="admin";
        String password ="admin";

        //Cach 1: truyền thẳng user/pass vào URL
        driver.get("https://" + username + ":"+ password + "@the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials')]")).isDisplayed());

        //Cach 2: Từ page A thao tác lên 1 element sẽ qua page B (Cần thao tác với Authentication alert trước)

        driver.get("https://the-internet.herokuapp.com/");
        String authenLinkURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get("authenLinkURL");
    }
    @Test
    public void TC_05_Authentication_Selenium_V4() {

        //Cach 1: truyền thẳng user/pass vào URL
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials')]")).isDisplayed());

        //Cach 2: chạy trên windows (autoIT)
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
