package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_00_Sell_In {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait expliciWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_Check_Dropdows_Value() {
        driver.get("https://www.chototxe.org/");
        sleepInSeconds(1);

        WebElement branddropdwn = driver.findElement(By.cssSelector("select[name='brand']"));
        WebElement modeldropdwn = driver.findElement(By.cssSelector("select[name='model']"));
        WebElement ododropdwn = driver.findElement(By.cssSelector("select[name='odo']"));
        WebElement mfydropdwn = driver.findElement(By.cssSelector("select[name='mfyear']"));

        //Check states of dropdowns by default
        Assert.assertTrue(branddropdwn.isEnabled());
        Assert.assertFalse(modeldropdwn.isEnabled());
        Assert.assertTrue(ododropdwn.isEnabled());
        Assert.assertTrue(mfydropdwn.isEnabled());

        //Verify dropdowns are single selection
        Select selectbrand = new Select(branddropdwn);
        Select selectmodel = new Select(modeldropdwn);
        Select selectodo = new Select(ododropdwn);
        Select selectmfy = new Select(mfydropdwn);


        //Select value from dropdown
        selectbrand.selectByVisibleText("Honda");
        //model enable if brand is selected
        Assert.assertTrue(modeldropdwn.isEnabled());
        selectmodel.selectByVisibleText("City");
        selectodo.selectByVisibleText("0 - 10000");
        selectmfy.selectByVisibleText("2022");

    }
    @Test
    public void TC_02_Empty_Required_Fields() {
        driver.get("https://www.chototxe.org/");

        driver.findElement(By.cssSelector("span.checkbox")).click();

        driver.findElement(By.xpath("//button[text()='Gửi thông tin']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='seller_phone']/parent::div/following-sibling::p")).getText(), "Bạn không nên để trống trường này");
        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='brand']/parent::div/following-sibling::p")).getText(), "Bạn không nên để trống trường này");
        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='model']/parent::div/following-sibling::p")).getText(), "Bạn không nên để trống trường này");
        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='odo']/parent::div/following-sibling::p")).getText(), "Bạn không nên để trống trường này");
        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='mfyear']/parent::div/following-sibling::p")).getText(), "Bạn không nên để trống trường này");
    }
    @Test
    public void TC_03_Invalid_Phone_Number() {
        driver.get("https://www.chototxe.org/");
        sleepInSeconds(3);

        //Case 1: Not number
        driver.findElement(By.xpath("//input[@id='seller_phone']")).sendKeys("abctest");

        // Dropdown
        Select branddropdwn = new Select(driver.findElement(By.cssSelector("select[name='brand']")));
        Select modeldropdwn = new Select(driver.findElement(By.cssSelector("select[name='model']")));
        Select ododropdwn = new Select(driver.findElement(By.cssSelector("select[name='odo']")));
        Select mfydropdwn = new Select(driver.findElement(By.cssSelector("select[name='mfyear']")));

        //Select value for dropdown
        branddropdwn.selectByVisibleText("Honda");
        modeldropdwn.selectByVisibleText("City");
        ododropdwn.selectByVisibleText("0 - 10000");
        mfydropdwn.selectByVisibleText("2021");

        driver.findElement(By.cssSelector("span.checkbox")).click();

        driver.findElement(By.xpath("//button[text()='Gửi thông tin']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='seller_phone']/parent::div/following-sibling::p")).getText(), "Số điện thoại không hợp lệ");

        //Case 2: Not 10 numbers
        driver.findElement(By.xpath("//input[@id='seller_phone']")).clear();
        driver.findElement(By.xpath("//input[@id='seller_phone']")).sendKeys("12345");

        //Select value for dropdown
        branddropdwn.selectByVisibleText("Kia");
        modeldropdwn.selectByVisibleText("K3");
        ododropdwn.selectByVisibleText("10000 - 30000");
        mfydropdwn.selectByVisibleText("2022");

        driver.findElement(By.xpath("//button[text()='Gửi thông tin']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='seller_phone']/parent::div/following-sibling::p")).getText(), "Số điện thoại không hợp lệ");

        //Case 3: Invalid format phone number
        driver.findElement(By.xpath("//input[@id='seller_phone']")).clear();
        driver.findElement(By.xpath("//input[@id='seller_phone']")).sendKeys("0123456789");

        //Select value for dropdown
        branddropdwn.selectByVisibleText("Mazda");
        modeldropdwn.selectByVisibleText("2");
        ododropdwn.selectByVisibleText("30000 - 50000");
        mfydropdwn.selectByVisibleText("2023");

        driver.findElement(By.xpath("//button[text()='Gửi thông tin']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='seller_phone']/parent::div/following-sibling::p")).getText(), "Số điện thoại không hợp lệ");

    }
    @Test
    public void TC_04_Success() {
        driver.get("https://www.chototxe.org/");
        sleepInSeconds(3);

        //Case 1: Not number
        driver.findElement(By.xpath("//input[@id='seller_phone']")).sendKeys("0901111111");

        // Dropdown
        Select branddropdwn = new Select(driver.findElement(By.cssSelector("select[name='brand']")));
        Select modeldropdwn = new Select(driver.findElement(By.cssSelector("select[name='model']")));
        Select ododropdwn = new Select(driver.findElement(By.cssSelector("select[name='odo']")));
        Select mfydropdwn = new Select(driver.findElement(By.cssSelector("select[name='mfyear']")));

        //Select value for dropdown
        branddropdwn.selectByVisibleText("Honda");
        modeldropdwn.selectByVisibleText("City");
        ododropdwn.selectByVisibleText("0 - 10000");
        mfydropdwn.selectByVisibleText("2021");

        driver.findElement(By.cssSelector("span.checkbox")).click();

        driver.findElement(By.xpath("//button[text()='Gửi thông tin']")).click();
        sleepInSeconds(2);
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
