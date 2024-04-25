package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_28_Wait_07_Explicit_03 {

    WebDriver driver;

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String name1= "Test1.jpeg";
    String name2= "Test2.jpeg";
    String name3= "Test3.jpeg";

    String name1Path = projectPath + File.separator + "uploadFiles" + File.separator + name1;
    String name2Path = projectPath + File.separator + "uploadFiles" + File.separator + name2;
    String name3Path = projectPath + File.separator + "uploadFiles" + File.separator + name3;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By selectedDate = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        Assert.assertEquals(driver.findElement(selectedDate).getText(), "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='12']")).click();

        //Wait cho loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        Assert.assertEquals(driver.findElement(selectedDate).getText(), "Friday, April 12, 2024");

    }
    @Test
    public void TC_02_Upload_Files() {
        driver.get("https://gofile.io/?t=uploadFiles");

        //Wait + Verify Spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        //Wait + click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink button"))).click();

        //Wait + Verify Spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //upload file
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(name1Path + "\n" + name2Path + "\n" + name3Path);

        //Wait + Verify Spinner icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //Wait cho cả 3 progress bar biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar")))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        //Verify button Download có tại từng hình được upload lên
       Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
               "//span[text()='" + name1 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

       Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()='" + name2 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

       Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()='" + name3 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

       //Verify button Play có tại từng hình được upload lên
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()='" + name1 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()='" + name2 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()='" + name3 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
