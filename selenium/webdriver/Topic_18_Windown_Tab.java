package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_18_Windown_Tab {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        //Switch qua trang gg
        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Automation Test");
        sleepInSeconds(3);

        //Switch về lại basic form
        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);
        //Switch qua facebook
        switchToWindowByTitle("Facebook – log in or sign up");

        driver.findElement(By.cssSelector("input#email")).sendKeys("sam@gmail.com");
        sleepInSeconds(3);

        switchToWindowByTitle("Selenium WebDriver");
    }
    @Test
    public void TC_02_Tech_Panda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(), "COMPARE PRODUCTS");
        driver.close();

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSeconds(3);

        driver.switchTo().alert().accept();
    }
    @Test
    public void TC_03_Selenium_Version_4() {
        driver.get("http://live.techpanda.org/index.php/");
        System.out.println("DriverID Techpanda =" + driver.toString());

        //new một tab mới
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.facebook.com/");
        System.out.println("DriverID Facebook =" + driver.toString());

        driver.findElement(By.cssSelector("input#email")).sendKeys("sam@gmail.com");
        sleepInSeconds(3);

        switchToWindowByTitle("Home page");
        driver.getTitle();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void switchToWindowByID(String expectedID)  {
        Set<String> allIDs = driver.getWindowHandles();

        for(String id:allIDs){
            if (!id.equals(expectedID)){
                driver.switchTo().window(id);
            }
            }

    }
    public void switchToWindowByTitle (String expectedTitle){
        Set<String> allIDs = driver.getWindowHandles();

        for (String id:allIDs){
            driver.switchTo().window(id);

            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)){
                break;
            }
        }
    }
    public void closeWindownWithoutParent(String parentID){
        Set<String> allIDs = driver.getWindowHandles();

        for (String id:allIDs){

            if (!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
