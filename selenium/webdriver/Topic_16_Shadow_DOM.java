package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Shadow_DOM {

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
    public void TC_01_Shadow_In_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        sleepInSeconds(2);

        //driver đại diện cho real DOM (DOM bên ngoài)
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        //shadowRootContext đại diện cho cái shadow DOM bên trong (DOM 1)
        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText, "some text");

        WebElement checkboxShadow = shadowRootContext.findElement(By.cssSelector("input[type='checkbox']"));

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        //Đại diện cho nested shadow DOM 2
        WebElement nestedShadowHostElement = driver.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();

        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);
        Assert.assertEquals(nestedText, "nested text");

//        String nestedText = driver.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();

    }
    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");
        sleepInSeconds(5);

        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowHostRootContext = shadowHostElement.getShadowRoot();

        //Verify popup hien thi
        if (shadowHostRootContext.findElements(By.cssSelector("div.home-popup__content")).size()>0
                &&shadowHostRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()){
            //Click de close popup
            shadowHostRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSeconds(3);
        }
        //Ko hien thi hoac da bi dong thi qua step search du lieu
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iPhone 15 promax");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSeconds(3);

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
