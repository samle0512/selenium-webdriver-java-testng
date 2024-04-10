package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_21_Upload_File {

    WebDriver driver;
    String osName = System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");
    String name1= "Test1.jpeg";
    String name2= "Test2.jpeg";
    String name3= "Test3.jpeg";

    String name1Path = projectPath + File.separator + "uploadFiles" + File.separator + name1;
    String name2Path = projectPath + File.separator + "uploadFiles" + File.separator + name2;
    String name3Path = projectPath + File.separator + "uploadFiles" + File.separator + name3;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");

        driver.findElement(uploadFile).sendKeys(name1Path);
        sleepInSeconds(2);

        driver.findElement(uploadFile).sendKeys(name2Path);
        sleepInSeconds(2);

        driver.findElement(uploadFile).sendKeys(name3Path);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name3 + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (WebElement button:startButtons){
            button.click();
            sleepInSeconds(3);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name3 + "']")).isDisplayed());
    }
    @Test
    public void TC_02_Multiple_Files() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");

        driver.findElement(uploadFile).sendKeys(name1Path + "\n" + name2Path + "\n" + name3Path);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name3 + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (WebElement button:startButtons){
            button.click();
            sleepInSeconds(3);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name3 + "']")).isDisplayed());


    }
    @Test
    public void TC_03_() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFiles = By.xpath("//input[@type='file']");
        driver.findElement(uploadFiles).sendKeys(name1Path + "\n" + name2Path);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + name2 + "']")).isDisplayed());

        List<WebElement> uploadButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (WebElement button:uploadButtons){
            button.click();
            sleepInSeconds(3);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + name2 + "']")).isDisplayed());
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
