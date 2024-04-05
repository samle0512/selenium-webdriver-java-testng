package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Topic_015_Popup_01 {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Fixed_Popup_In_DOM() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);

        By LoginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");

        //Kiểm tra login popup đang hiển thị
        graphql.Assert.assertTrue(driver.findElement(LoginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div input#account-input"))
                .sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div input#password-input"))
                .sendKeys("automationfc");

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div button.btn-login-v1")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div div.error-login-panel")).getText(), "Tên tài khoản không tồn tại");

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div button.close")).click();
        sleepInSeconds(2);

        //Kiểm tra login popup không hiển thị
        Assert.assertFalse(driver.findElement(LoginPopup).isDisplayed());
    }
    @Test
    public void TC_02_Fixed_Popup_In_DOM() {
        driver.get("https://skills.kynaenglish.vn/");

        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb  div.modal-dialog")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
        sleepInSeconds(3);

        //Kiểm tra popup không còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb  div.modal-dialog")).isDisplayed());
    }
    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM_01() {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span[1]")).getText(), "Mật khẩu không được để trống");

        //Đóng popup
        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepInSeconds(2);

        //Khi cái popup đóng lại thì html không còn trong DOM nữa
        //Không nên dùng
        //Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
    }
    @Test
    public void TC_04_Fixed_Popup_Not_In_DOM_02() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);
    }

    @Test
    public void TC_05_Right_Click() {

    }

    @Test
    public void TC_06_Drag_And_Drop_HTML4() {


    }
    @Test
    public void TC_07_Drag_And_Drop_HTML5_CSS() throws IOException {


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
