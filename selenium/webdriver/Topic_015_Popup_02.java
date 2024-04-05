package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_015_Popup_02 {

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
    public void TC_01_Random_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(15);

        By newsleterpopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        //Nếu hiển thị popup thì close nó đi
        if (driver.findElements(newsleterpopup).size()>0 && driver.findElements(newsleterpopup).get(0).isDisplayed()){
            System.out.println("Popup hiển thị");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
            sleepInSeconds(3);
        }
        //Nếu ko hiển thị thì vào search dữ liệu
        //Nhập vào field search dữ liệu
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");

        //Click search
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSeconds(3);

        //Verify
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());

    }
    @Test
    public void TC_02_Random_In_DOM() {
        driver.get("https://vnk.edu.vn/");

        findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Lịch khai giảng tháng 03']")).isDisplayed());

    }
    @Test
    public void TC_03_Not_In_Dom() {
        driver.get("https://dehieu.vn/");

        By marketingpopup = By.cssSelector("div.modal-content");

        if (driver.findElements(marketingpopup).size()>0 && driver.findElements(marketingpopup).get(0).isDisplayed()){
            System.out.println("Popup hiển thị");

            int heightBrowser = driver.manage().window().getSize().getHeight();
            System.out.println("Heigt Browser =" + heightBrowser);
            if (heightBrowser<1920){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
            } else {
                driver.findElement(By.cssSelector("button.close")).click();
            }
        }

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement findElement (By locator){
        //Nếu popup xuất hiện thì nó sẽ close đi
        if (driver.findElement(By.cssSelector("div.tve-leads-conversion-object")).isDisplayed()){
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSeconds(3);
            System.out.println("Popup hiển thị");
        }
        return driver.findElement(locator);

    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
