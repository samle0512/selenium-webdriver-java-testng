package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Button {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Ego_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        Assert.assertTrue(registerButton.isEnabled());

        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB is:" + registerBackgroundRGB);

        //Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);

        //String registerBackgroundHexa = registerBackgroundColour.asHex();
        //System.out.println("Background color Hexa is:" + registerBackgroundHexa);

        Assert.assertEquals(Color.fromString (registerButton.getCssValue("background-color")).asHex().toLowerCase(), "#ef5a00");
    }
    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);

        WebElement LoginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        //Verify Disable
        Assert.assertFalse(LoginButton.isEnabled());

        //Verify backgroud
        //System.out.println(LoginButton.getCssValue("background"));
        Assert.assertEquals(Color.fromString (LoginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        //Nhap email/Pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("sam@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(3);

        Assert.assertTrue(LoginButton.isEnabled());
        Assert.assertEquals(Color.fromString (LoginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
    }
    @Test
    public void TC_03_() {

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

