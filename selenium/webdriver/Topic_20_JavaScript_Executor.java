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

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_20_JavaScript_Executor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Ép kiểu tường minh. Ép từ kiểu dữ liệu này qua kiểu khác
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Tech_Panda() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(3);

        String techPandaDomain = (String) executeForBrowser( "return document.domain");
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");

        String homepageURL = (String) executeForBrowser("return document.URL");
        Assert.assertEquals(homepageURL, "http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button[@class='button btn-cart']");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button[@class='button btn-cart']");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String customerServiceTitle = (String) executeForBrowser( "return document.title");
        Assert.assertEquals(customerServiceTitle, "Customer Service");

        scrollToBottomPage();

        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", getEmailAddress());

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSeconds(5);

        Assert.assertEquals(executeForBrowser( "return document.domain"), "facebook.com");

    }
    @Test
    public void TC_02_Register_Account() {
        driver.get("https://sieuthimaymocthietbi.com/account/register");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"), "Please fill out this field.");

        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"), "Please fill out this field.");

        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Testing");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("a@b@");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please enter an email address.");

    }
    @Test
    public void TC_03_Create_An_Account() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(3);

        hightlightElement("//div[@id='header-account']/div/ul/li/a[text()='My Account']");
        clickToElementByJS("//div[@id='header-account']/div/ul/li/a[text()='My Account']");

        hightlightElement("//a[@title='Create an Account']");
        clickToElementByJS("//a[@title='Create an Account']");


        hightlightElement("//input[@id='firstname']");
        sendkeyToElementByJS("//input[@id='firstname']", "Automation");

        hightlightElement("//input[@id='lastname']");
        sendkeyToElementByJS("//input[@id='lastname']", "Testing");

        hightlightElement("//input[@id='email_address']");
        sendkeyToElementByJS("//input[@id='email_address']", getEmailAddress());

        hightlightElement("//input[@id='password']");
        sendkeyToElementByJS("//input[@id='password']", "123456");

        hightlightElement("//input[@id='confirmation']");
        sendkeyToElementByJS("//input[@id='confirmation']", "123456");

        hightlightElement("//button[@title='Register']");
        clickToElementByJS("//button[@title='Register']");
        sleepInSeconds(3);

        Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
        sleepInSeconds(3);

        hightlightElement("//a[@title='Log Out']");
        clickToElementByJS("//a[@title='Log Out']");
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
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public String getEmailAddress(){
        Random rand = new Random();
        return "kevinlamp" + rand.nextInt(9999) + "@gmail.com";
    }
}
