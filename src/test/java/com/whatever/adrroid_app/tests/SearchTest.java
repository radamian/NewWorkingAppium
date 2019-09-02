package com.whatever.adrroid_app.tests;


import com.whatever.android_app.pages.SearchWidget;


import org.testng.annotations.Test;


public class SearchTest extends BaseScenario {

    public SearchWidget searchWidget;

    @Test(priority = 2)
    public void search_topic_should_return_relevant_article() throws Exception {
/*        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchForTopic(SEARCH_TERM);
        System.out.println(SEARCH_TERM);

        Assert.assertTrue(searchWidget.getNumberOfSearchResults() > 0);
        Assert.assertTrue(searchWidget.SearchResultContains(SEARCH_TERM));

        ArticlePage articlePage = searchWidget.selectFirstResult();
        Assert.assertTrue(articlePage.getArticleTitle().contains(SEARCH_TERM));*/
    }

    @Test(priority = 1)
    public void browse() {
//        SearchWidget searchWidget = new SearchWidget(driver);

        searchWidget = new SearchWidget(driver);

        searchWidget.connect();


    }
}