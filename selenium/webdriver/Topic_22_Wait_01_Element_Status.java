package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_22_Wait_01_Element_Status {

    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
    }
    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("automation@gmail.com");
        sleepInSeconds(3);
        //Điều kiện 1: element có xuất hiện trên UI và có trong DOM
        //Tại thời điểm này thì reconfirm email đang visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }
    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);
        //Điều kiện 2: Element ko có trên UI nhưng có trong cây HTML
        //Tại thời điểm này thì reconfirm email đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

        //Điều kiện 3: Element ko có trên UI và cũng ko có trong cây HTML
    }
    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        //Điều kiện 3: Element ko có trên UI và cũng ko có trong cây HTML
        //Tại thời điểm này thì reconfirm email đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        //KO dùng được cách này, vì NOT in DOM -> tìm ko ra element
        //Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }
    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("automation@gmail.com");
        sleepInSeconds(3);

        //Điều kiện 1: element có xuất hiện trên UI và có trong DOM
        //Tại thời điểm này thì reconfirm email đang presence (có trong cây HTML, ko quan tâm hiển thị hay ko)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);

        //Điều kiện 2: Element ko có trên UI nhưng có trong cây HTML
        //Tại thời điểm này thì reconfirm email đang presence (có trong cây HTML, ko quan tâm hiển thị hay ko)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));
    }
    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        //Tại thời điểm này, element reconfirm email có xuất hiện trong cây HTML
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);

        //Điều kiện 3: Element ko có trên UI và cũng ko có trong cây HTML
        //Tại thời điểm này thì reconfirm email đang invisible
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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
