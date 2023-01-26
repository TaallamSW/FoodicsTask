package tests;

import org.testng.annotations.Test;
import pages.GoogleHomePage;
import pages.SearchResultPage;

public class SearchGoogleTest extends BaseTest{

    @Test
    public void searchGoogleTest1(){
        GoogleHomePage googleHomePage=new GoogleHomePage(driver);
        SearchResultPage searchResultPage=googleHomePage.enterTextInSearchBar("Tennis");
        searchResultPage.enterTextInSearchBar("football");
        softAssert.assertTrue(searchResultPage.isNumberOfResultsElementVisible(),"Number of results element is not visible after searching");
        searchResultPage.switchToResultPageByNumber("2");
        int numberOfResultsPageTwo=searchResultPage.numberOfResults();
        searchResultPage.switchToResultPageByNumber("3");
        int numberOfResultsPageThree=searchResultPage.numberOfResults();
        softAssert.assertEquals(numberOfResultsPageTwo,numberOfResultsPageThree,"Number of results on page two and three are different");
        softAssert.assertTrue(searchResultPage.isSearchSuggestionUnique(),"Search suggestions are not unique");
        softAssert.assertAll();
    }




}
