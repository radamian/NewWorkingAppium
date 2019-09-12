package com.whatever.adrroid_app.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import com.whatever.android_app.pages.SearchWidget;

public class NewScenario extends BaseScenario{

    public SearchWidget searchWidget;

    @Test
    @Description("Descriere test nou")
    @Feature("Iar un Feature")
    @Epic("Acum un Epic")
    @Story("Story")
    public void newTest(){
        searchWidget = new SearchWidget(driver);

        searchWidget.account();


    }
}
