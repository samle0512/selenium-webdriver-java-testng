package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Checkbox_Radio {

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
    public void TC_01_Default_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        //sleepInSeconds(5);

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        By rearCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        //Chọn 2 checkboxes
        //Case 1: nếu app mở ra mà checkbox đã được chọn
        if(!driver.findElement(dualZoneCheckbox).isSelected())
        {
            driver.findElement(dualZoneCheckbox).click();
            sleepInSeconds(2);
        }
        //Case 2: nếu app mở ra mà checkbox chưa được chọn
        if(!driver.findElement(rearCheckbox).isSelected())
        {
            driver.findElement(rearCheckbox).click();
            sleepInSeconds(2);
        }

        //Verify checkbox đã được chọn
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(rearCheckbox).isSelected());
        //Bỏ chọn 2 checkboxes
        if(driver.findElement(dualZoneCheckbox).isSelected())
        {
            driver.findElement(dualZoneCheckbox).click();
            sleepInSeconds(2);
        }
        //Case 2: nếu app mở ra mà checkbox chưa được chọn
        if(driver.findElement(rearCheckbox).isSelected())
        {
            driver.findElement(rearCheckbox).click();
            sleepInSeconds(2);
        }

        //Verify checkbox đã được chọn
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(rearCheckbox).isSelected());

    }
    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        //Chỉ chọn chứ ko bỏ chọn được
       By twoPetrolRaido = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
       By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

       //Click chọn 1 trong 2 radio
        checkToElement(twoPetrolRaido);

        //Verify
        Assert.assertTrue(driver.findElement(twoPetrolRaido).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);
        Assert.assertFalse(driver.findElement(twoPetrolRaido).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
    }
    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //Chọn hết tất cả các checkbox trong màn hình đó
        for (WebElement checkbox:allCheckboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
                //sleepInSeconds(1);
            }
        }
        //Verify tất cả checkboxes
        for (WebElement checkbox:allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        //Chọn 1 checkbox/radio nào đó trong tất cả các checkbox/radio
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement checkbox:allCheckboxes){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(2);
            }
        }
        //Verify tất cả checkboxes
        for (WebElement checkbox:allCheckboxes){
            if (checkbox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }
            else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Custom_Radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

        //Case 1: Dùng thẻ input để click => thẻ input bị che bởi 1 element khác => FAILED

        //Case 2:
        // //Dùng thử div bên ngoài để click => PASSED
        //Dùng thẻ div để verify => FAILED
        //driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
        //sleepInSeconds(3);

        //Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).isSelected());
        //Case 3:
        // //Dùng thử div bên ngoài để click => PASSED
        //Dùng thẻ input để verify => PASSED
        //1 element mà cần define tới 2 locator thì sau này => Maintain mất nhiều thời gian hơn

//        driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
//        sleepInSeconds(3);
//
//        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());

        //Case 4:
        //Dùng input để click > JavascriptExecutor (JS)
        //Dùng input để verify
        //Chỉ cần 1 locator
        By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(registerRadio));
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(registerRadio).isSelected());
    }

    @Test
    public void TC_05_Custom_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");

        //Verify radio is not selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());


        //Click radio va verify is selected
        driver.findElement(canThoRadio).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

        driver.findElement(quangNamCheckbox).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"), "true");

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void checkToElement (By Xpatth){
        if(!driver.findElement(Xpatth).isSelected())
        {
            driver.findElement(Xpatth).click();
            sleepInSeconds(2);
        }
    }

    public void uncheckToElement (By Xpatth){
        if(driver.findElement(Xpatth).isSelected())
        {
            driver.findElement(Xpatth).click();
            sleepInSeconds(2);
        }
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
