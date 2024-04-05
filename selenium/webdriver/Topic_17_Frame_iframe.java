package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_Frame_iframe {

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
    public void TC_01_Form_Site() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(3);

        //Iframe Element
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        //Switch vào frame trước khi thao tác với element bên trong
        driver.switchTo().frame(formIframe);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);

        //Thao tác với element ngoài iframe
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }
    @Test
    public void TC_02_Kyna_English() {
        driver.get("https://skills.kynaenglish.vn/");

        //switch vào iframe chứa fanpage
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));

        //Verify follower number
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "179K followers");

        //Switch về trag default/trang parent chứa iframe
        driver.switchTo().defaultContent();

        //switch vào iframe WeChat
        driver.switchTo().frame("cs_chat_iframe");

        driver.findElement(By.cssSelector("div.button_bar")).click();

        //Input thong tin
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Tran");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0901000000");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Đăng ký khoá học");
        sleepInSeconds(3);

        //Switch về default content
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input.live-search-bar")).sendKeys("Java");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input.search-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")), "Lập trình Java trong 4 tuần");
    }
    @Test
    public void TC_03_Frame_HDFC_Bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationtest");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("a.login-btn")).click();

        sleepInSeconds(3);
        //switch to default content
        driver.switchTo().defaultContent();
        //Input password
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345678");
        sleepInSeconds(5);
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
