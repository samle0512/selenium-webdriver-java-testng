package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_26_Wait_05_Explicit_01_Knowledge {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    WebDriverWait explicitWait; //khai báo chưa khởi tạo

    @BeforeClass //pre-condition (khởi tạo dữ liệu/data test/page class/variable)
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Khởi tạo 1 explicit wait có tổng thời gian là 10s - polling là 0.5s mặc định
        //500 miliseconds = 0.5 second
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @Test
    public void TC_01_() {
        //Chờ cho 1 Alert presence trong HTML/DOM trước khi thao tác lên
         Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
         alert.accept();

         //Chờ cho element ko còn ở trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Chờ cho element có ở trong DOM, ko quan tâm trên UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Chờ cho 1 list element có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        //Chờ cho 1 đến n elements được hiển thị trên giao diện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));

        //Chờ cho element được phép click
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(""))));

        //Chờ cho page hiện tại có title như mong đợi
        explicitWait.until(ExpectedConditions.titleIs("Create New Customer"));
        driver.getTitle();

        //Kết hợp nhiều điều kiện
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //Kết hợp 1 trong 2 điều kiện - chỉ cần 1 trong 2 điều kiện đúng
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //Chờ cho 1 element có attribute chứa giá trị  mong đợi (tương đối)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire"));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "store here..."));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

        //Chờ cho 1 element có giá trị mong đợi (tuyệt đối)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

        //Chờ cho 1 element có giá trị khác null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("input#search")), "placeholder"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("input#search")),"namespaceURI", "http://www.w3.org/1999/xhtml"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("input#search")),"namespaceURI", "http://www.w3.org/1999/xhtml" ));

        //Chờ cho 1 element được selected thành công
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //Chờ cho số lượng items bằng một con số cố định
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("select[title='Sort By']>option"), 6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("select[title='Sort By']>option"), 6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("select[title='Sort By']>option"), 6));

        //Chờ cho window/tab là bao nhiêu (chờ cho cửa sổ chứ ko chờ cho page được load xong)
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.category-title>h1"),  "Mobile"));

        Pattern pattern = Pattern.compile("This is root of mobile", Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector("div.category-description"), pattern));

        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div.category-title>h1"), ""));
    }
    @Test
    public void TC_02_() {

    }
    @Test
    public void TC_03_() {

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
