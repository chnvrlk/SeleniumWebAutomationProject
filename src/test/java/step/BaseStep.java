package step;

import base.BaseTest;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BaseStep extends BaseTest
{


    WebElement findElementWaitUntil(String element, String locator){

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement element1 = null;

        if (locator.contains("id")){
            element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
            logger.info(element+" element found");
        }
        else if (locator.contains("css")){
            element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
            logger.info(element+" element found");
        }
        else if (locator.contains("xpath")){
            element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            logger.info(element+" element found");
        }
        else if (locator.contains("name")){
            element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
            logger.info(element+" element found");
        }
        else {
            System.out.println("Invalid locator entered");
        }
        return element1;
    }

    @Step("Go to <url>")
    public void goToUrl(String url){
        driver.get(url);
        logger.info("Went to "+url);
    }

    @Step("Click on element <element> with locator <locator>")
    public void findElementByLocator(String element,String locator){
       findElementWaitUntil(element,locator).click();
    }


    @Step("Clear element <element> and print <text> with locator <locator>")
    public void clearAndSendKey(String element,String text,String locator){
        findElementWaitUntil(element,locator).clear();
        findElementWaitUntil(element,locator).sendKeys(text);
        logger.info(element+" cleared "+text+" value entered");
    }

    @Step("write text <text> value to <element> with locator <locator>")
    public void sendKeyElement(String text,String element,String locator){
        findElementWaitUntil(element,locator).sendKeys(text);
        logger.info(element+ " element "+text+" value entered");
    }

    @Step("<second> Wait seconds")
    public void wait(int second) throws InterruptedException {
        Thread.sleep(second*1000);
        logger.info("Waited " + second + " seconds");
    }

    @Step("Wait above element <element> with <locator>")
    public void hover(String element,String locator) {
        WebElement hoverElementById = findElementWaitUntil(element,locator);
        hoverElement(hoverElementById);
        logger.info(element+" waited above element");
    }

    private void hoverElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    @Step("Save <element> 's text value with <name> <locator>")
    public void saveElementText(String element,String name,String locator){
        String savedText = findElementWaitUntil(element,locator).getText();
        saveValue(name,savedText);
        System.out.println("Saved text: "+ savedText);
    }

    @Step("Compare the value saved with the <name> to the value of <element> <locator>")
    public void compareElementText(String name,String element,String locator){

        String comparedText = findElementWaitUntil(element,locator).getText();
        String secondText = getValue(name);
        Assert.assertEquals(comparedText,secondText);
        System.out.println("Ilk elementin texti: " +comparedText + "\n" +"ikinci elementin texti: "+ secondText);

    }


    @Step("Check element visible <element> <locator>")
    public void checkElementDisplayed(String element,String locator){
        Assert.assertTrue(findElementWaitUntil(element,locator).isDisplayed());
        logger.info(element+" element found");
    }

    @Step("Focus the popup")
    public void focusPopup() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            logger.info("Focused the popup");
        }
    }

    @Step("Focus on window <number>")//Start one window
    public void focusTabWithNumber(int number) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(number - 1));
        logger.info("Focused on window "+ number);
    }

    @Step("Focus on last window")
    public void focusChromeLastTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        logger.info("Focused on last window");
    }

    @Step("New window opens")
    public void openChromeNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    @Step("Accept the alert popup")
    public void acceptChromeAlertPopup() {
        driver.switchTo().alert().accept();
    }

    @Step("Scroll up to <element> with <locator>")
    public void scrollToElement(String element,String locator){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement thisElement = findElementWaitUntil(element,locator);
        jse.executeScript("arguments[0].scrollIntoView()",thisElement);
        logger.info("Scrolled up to "+element);
    }

    @Step({"Scroll to page's top"})
    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
        logger.info("Scrolled to page's top");
    }

    @Step("Change page's zoom value <value>%")
    public void zoomOutChrome(String value) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("document.body.style.zoom = '" + value + "%'");
    }

    @Step("Back to previous page")
    public void backToOldPage() {
        driver.navigate().back();
    }

    @Step("Select random element <element> from element list with <locator>")
    public void clickRandomElement(String element,String locator) {
        if (locator.contains("id")){
            List<WebElement> elements = driver.findElements(By.id(element));
            Random random = new Random();
            int index = random.nextInt(elements.size());
            elements.get(index).click();
        }
        else if (locator.contains("css")){
            List<WebElement> elements = driver.findElements(By.cssSelector(element));
            Random random = new Random();
            int index = random.nextInt(elements.size());
            elements.get(index).click();
        }
        else if (locator.contains("xpath")){
            List<WebElement> elements = driver.findElements(By.xpath(element));
            Random random = new Random();
            int index = random.nextInt(elements.size());
            elements.get(index).click();
        }
        else if (locator.contains("name")){
            List<WebElement> elements = driver.findElements(By.name(element));
            Random random = new Random();
            int index = random.nextInt(elements.size());
            elements.get(index).click();
        }
        else{
            System.out.println("Invalid locator entered");
        }
    }

    @Step("Select random element <element> from combobox element with locator <locator>")
    public void clickRandomComboboxElement(String element,String locator) {

        WebElement comboBoxElement;
        comboBoxElement = findElementWaitUntil(element,locator);
        Select comboBox= new Select(comboBoxElement);
        int randomIndex = new Random().nextInt(comboBox.getOptions().size());
        comboBox.selectByIndex(randomIndex);
    }

    @Step("Click random place on page")
    public void clickRandomPlace() throws AWTException {

        Robot robot = new Robot();
        robot.mouseMove(50,50);
        actions.click().build().perform();
        robot.mouseMove(500,250);
        actions.click().build().perform();
        logger.info("Clicked random place on page");
    }

    public void javascriptClicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }


    @Step("Click element <element> with Javascript and locator <locator>")
    public void clickByJavascript(String element,String locator) {
        WebElement jsElement = findElementWaitUntil(element, locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", jsElement);
        logger.info("click element with javascript");
    }
    //JS kodu düzenlenecek
}
