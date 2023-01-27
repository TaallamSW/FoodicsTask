package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchResultPage extends BasePage {

    /********************************* Search Result Page Locators ************************************/

    private By numberOfResultsElement=By.xpath("//div[@id='result-stats']");
    private By resultsElements=By.xpath("//div/a/h3");
    private By searchSuggestionsElements=By.xpath("((//a//b)//parent::*)//parent::div");

    /********************************* Search Result Page Constructor ************************************/

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    /********************************* Search Result Page Functions ************************************/

    public boolean isNumberOfResultsElementVisible(){
        return isWebElementVisible(numberOfResultsElement);
    }

    public void switchToResultPageByNumber(String pageNum){
        By page=By.xpath("//a[@aria-label='Page "+pageNum+"']");
        scrollToWebElement(page);
        clickOnWebElement(page);
    }

    public int numberOfResults(){
        waitUntilWebElementIsVisible(resultsElements);
        return driver.findElements(resultsElements).size();
    }

    public boolean isSearchSuggestionUnique(){
        waitUntilWebElementIsVisible(searchSuggestionsElements);
        List<WebElement> suggestions=driver.findElements(searchSuggestionsElements);
        int occurrences=0;
        for(int i=0;i<suggestions.size();i++){
            for(int j=0;j<suggestions.size();j++){
                if(suggestions.get(i).getText().equals(suggestions.get(j).getText())){
                    occurrences++;
                    if(occurrences>1){
                        return false;
                    }
                }

            }
            occurrences=0;

        }
        return true;
    }
}
