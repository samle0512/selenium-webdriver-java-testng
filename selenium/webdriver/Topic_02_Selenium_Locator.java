package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.get("https://demo.nopcommerce.com/register");
        driver.get("https://automationfc.github.io/basic-form/");
    }
    @Test
    public void TC_01_ID() {
        //Tim element co id la FirstName
        driver.findElement(By.id("FirstName")).sendKeys("Keane");
    }
    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }
    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        //Độ chính xác cao vì tuyệt đối
        driver.findElement(By.linkText("Digital downloads"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
        //Độ chính xác cao vì chỉ lấy tương đối (1 phần ở đầu hoặc ở giữa...)
        driver.findElement(By.partialLinkText("downloads"));
    }

    @Test
    public void TC_07_Css() {
        //Css với ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#id='FirstName'"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Css với class
        driver.findElement(By.cssSelector("div[class="));

        //Css voi name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        //Css voi tagname
        driver.findElement(By.cssSelector("input"));

        //Css voi Link
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));

        //Css voi partial link
        driver.findElement(By.cssSelector("a[href*='addresses']"));
        //driver.findElement(By.cssSelector("a[href^='addresses']"));
        //driver.findElement(By.cssSelector("a[href$='addresses']"));
    }
    @Test
    public void TC_08_XPath() {
        //XPath với ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //XPath với class
        driver.findElement(By.xpath("//div[@class="));

        //XPath voi name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //XPath voi tagname
        driver.findElement(By.xpath("//input"));

        //XPath voi Link
        driver.findElement(By.xpath("//[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        //XPath voi partial link
        driver.findElement(By.xpath("//a[contains([@href, 'addresses']"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses']"));
    }
    @Test
    public void TC_09_XPath() {
        String concatText = driver.findElement(By.xpath("//span[@class='concat']")).getText();
        Assert.assertEquals (concatText, "Hello \"John\", What's happened?");

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
