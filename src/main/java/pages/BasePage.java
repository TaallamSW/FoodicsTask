package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    private final long timeOut = 40;

    /********************************* Base Page Locators ************************************/

    private By searchTextField=By.xpath("//input[@aria-label='Search' or @aria-label='بحث' ]");

    /********************************* Page Constructor **************************************/

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /********************************* Base Page functions ***********************************/

    public SearchResultPage enterTextInSearchBar(String text){
        enterTextInWebElementAndThenPressEnter(searchTextField,driver,text);
        return new SearchResultPage(driver);
    }

    /********************************* General functions *************************************/

    public void waitUntilWebElementIsVisible(By element, WebDriver driver){
        WebDriverWait wait=new WebDriverWait(driver,timeOut);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public  void clickOnWebElement(By element, WebDriver driver){
        waitUntilWebElementIsVisible(element,driver);
        driver.findElement(element).click();
    }

    public void enterTextInWebElement(By element,WebDriver driver,String text){
        waitUntilWebElementIsVisible(element,driver);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public void enterTextInWebElementAndThenPressEnter(By element,WebDriver driver,String text){
        waitUntilWebElementIsVisible(element,driver);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    public String getWebElementText(By element,WebDriver driver) {
        waitUntilWebElementIsVisible(element, driver);
        return driver.findElement(element).getText();
    }

    public  boolean isWebElementVisible(By element,WebDriver driver){
        try {
            WebDriverWait wait=new WebDriverWait(driver,timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void scrollToWebElement(By element,WebDriver driver){
            waitUntilWebElementIsVisible(element, driver);
            WebElement webElement = driver.findElement(element);
            Actions action = new Actions(driver);
            action.moveToElement(webElement).perform();
    }

}
