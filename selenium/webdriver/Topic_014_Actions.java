package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_014_Actions {

    WebDriver driver;

    Actions actions;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        javascriptExecutor = (JavascriptExecutor) driver;
    }
    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        actions.moveToElement(ageTextbox).perform();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");

    }
    @Test
    public void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com/");

        actions.moveToElement((driver.findElement(By.xpath("//span[@class='icon_menu']")))).perform();
        sleepInSeconds(2);

        actions.moveToElement(driver.findElement(By.xpath(
                "//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//div[contains(@class, 'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        sleepInSeconds(2);

        //Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
    }
    @Test
    public void TC_03_Click_And_Hold() {

        driver.get("https://automationfc.github.io/jquery-selectable/");

        String osName = System.getProperty("os.name");
        Keys keys;

        if (osName.startsWith("Windowns"))
        {
            keys = Keys.CONTROL;
        }
        else {
            keys= Keys.COMMAND;
        }

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));

        //Chọn từ 1-15
//        actions.clickAndHold(allNumbers.get(0)) //click lên số 1 và giữ chuột
//                .pause(2000)
//                .moveToElement(allNumbers.get(14)) //di chuột trái đến số 15
//                .pause(2000)
//                .release() //nhả chuột trái ra
//                .perform(); //Execute tất cả các actions trên
//
//        List<String> allNumberTextExpected = new ArrayList<String>();
//        allNumberTextExpected.add("1");
//        allNumberTextExpected.add("2");
//        allNumberTextExpected.add("3");
//        allNumberTextExpected.add("5");
//        allNumberTextExpected.add("6");
//        allNumberTextExpected.add("7");
//        allNumberTextExpected.add("9");
//        allNumberTextExpected.add("10");
//        allNumberTextExpected.add("11");
//        allNumberTextExpected.add("13");
//        allNumberTextExpected.add("14");
//        allNumberTextExpected.add("15");
//
//        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-selected"));
//        Assert.assertEquals(allNumbersSelected.size(), 12);
//
//        List<String> allNumberTextActual = new ArrayList<String>();
//        for (WebElement element: allNumbersSelected) {
//            allNumberTextActual.add(element.getText());
//        }
//
//        Assert.assertEquals(allNumberTextExpected, allNumberTextActual);
        //Chọn từ 1-12 theo đủ hàng/cột
        actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).release().perform();

        //Chọn từ 13-15
        actions.keyDown(keys).perform();//nhấn phím ctrl xuống (chưa nhả ra)

        actions.click(allNumbers.get(12))
                .click(allNumbers.get(13))
                .click(allNumbers.get(14))
                .perform();
        sleepInSeconds(2);
    }
    @Test
    public void TC_04_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        //Trên Firefox, cần scroll tới element rồi mới double click
        if (driver.toString().contains("firefox")){
            //scrollIntoView (true) kéo mép trên của element lên phía trên cùng của viewport
            //scrollIntoView (false) kéo mép dưới xuống phía dưới cùng của viewport
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
            sleepInSeconds(3);
        }

       actions.doubleClick(doubleClickButton).perform();
       sleepInSeconds(2);

       Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

    }

    @Test
    public void TC_05_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        //Chưa click chuột phải thì nó đang ko hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSeconds (3);

        //Mới click chuột phải -> các element sẽ được visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        //Sau khi hover, được cập nhật lại class của element này
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

        //Click lên Paste
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        driver.switchTo().alert().accept();
        sleepInSeconds (3);

        //Kiểm tra paste khôgn còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
    }

    @Test
    public void TC_06_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(smallCircle, bigCircle).perform();
        sleepInSeconds(2);

        Assert.assertEquals(bigCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString (bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");

    }
    @Test
    public void TC_07_Drag_And_Drop_HTML5_CSS() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

        String projectPath = System.getProperty("user.dir");

        String dragAndDropFilePath = projectPath + "dragAndDrop/drag_and_drop_helpers.js";

        String jsContentFile = getContentFile(dragAndDropFilePath);

        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        //Thực thi đoạn lệnh JS
        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");

    }
    @Test
    public void TC_08_Drag_And_Drop_HTML5_Xpath() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        dragAndDropHTML5ByXpath ("//div[@id='column-a']", "//div[@id='column-b']");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        dragAndDropHTML5ByXpath ("//div[@id='column-a']", "//div[@id='column-b']");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
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
