package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_23_Wait_02_Find_Element {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.facebook.com/");
    }
    @Test
    public void TC_01_FindElement() {
        //Case 1: element được tìm thấy chỉ có 1 và trong thời gian timeout
        //Sẽ ko cần chờ hết timeout
        //Tìm thấy sẽ trả về webelement
        //Qua next step
        //driver.findElement(By.cssSelector("input#email"));

        //Case 2: element được tìm thấy nhưng nhiều hơn 1
        //Sẽ ko cần chờ hết timeout
        //Tìm thấy sẽ trả về 1 Webelement
        //Lấy element đầu tiên được tìm thấy dù có cả n node
        //driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("automation@gmail.com");

        //Case 3: Element ko được tìm thấy -> QUAN TRỌNG NHẤT
        //Cần chờ hết timeout (10s)
        //Trong thời gian 10s này, cứ mỗi nửa giây sẽ tìm lại 1 lần
        //Nếu tìm lại mà thấy thì cũng trả về element rồi qua next step (giống 2 cases trên)
        //Nếu tìm lại hết timeout mà vẫn ko thấy thì đánh fail TCs này luôn và throw exception
        //Các step còn lại ko chạy nữa
        System.out.println("Start time:" + getDatetimeNow());
        driver.findElement(By.cssSelector("input[name='reg_email__']"));
        System.out.println("End time:" + getDatetimeNow());

    }
    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;

        //Case 1: element được tìm thấy chỉ có 1 và trong thời gian timeout
        //Ko cần chờ hết timeout 10s
        //Trả về 1 list  element chứa đúng 1 element
//        System.out.println("Start time:" + getDatetimeNow());
//        elementList = driver.findElements(By.cssSelector("input#email"));
//        System.out.println("List have" + elementList.size());
//        System.out.println("End time:" + getDatetimeNow());

        //Case 2: element được tìm thấy nhưng nhiều hơn 1
        //Ko cần chờ hết timeout 10s
        //Trả về 1 list  element chứa đúng nhiều element
//        System.out.println("Start time:" + getDatetimeNow());
//        elementList = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
//        System.out.println("List have" + elementList.size());
//        System.out.println("End time:" + getDatetimeNow());

        //Case 3: Element ko được tìm thấy -> QUAN TRỌNG NHẤT
        //Chờ hết timeout 10s
        //Mỗi nửa giây cũng tìm lại 1 lần
        //Trong time tìm lại mà thấy elements thì cũng trả về list chứa các elements đó
        //Nếu hết timeout mà vẫn ko tìm thấy element nào -> trả về 1 cái list rỗng (empty) và không đánh failed TCs
        //Qua next step
        System.out.println("Start time:" + getDatetimeNow());
        elementList = driver.findElements(By.cssSelector("input[name='reg_email__']"));
        System.out.println("List have" + elementList.size());
        System.out.println("End time:" + getDatetimeNow());

    }
    @Test
    public void TC_03_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public String getDatetimeNow() {
        Date date = new Date();
        return date.toString();
    }
}
