package com.whatever.android_app.pages;
import utility.MyLog;
import com.whatever.android_app.pages.BaseWidget;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.html.HTMLInputElement;

import java.util.List;


public class SearchWidget extends BaseWidget {



    // @FindBy(id = "login-email")
    @FindBy(xpath = "//android.view.View[3]/android.view.View[1]/android.view.View[0]/android.view.View{1}")
    private WebElement loginemalFld;


    @FindBy(id = "com.yahoo.mobile.client.android.flickr:id/activity_welcome_sign_button")
    private WebElement loginBtn;

    @FindBy(id = "com.yahoo.mobile.client.android.flickr:id/fragment_navigation_profile")
    private WebElement profileBtn;

    @FindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private WebElement skipBtn;

    @FindBy(id = "org.wikipedia:id/search_container")
    private WebElement searchContainer;

    @FindBy(xpath = ".//*[@text='Search Wikipedia']")
    private WebElement editSearchContainer;

    @FindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchWikipediaFld;


    @FindBy(xpath = "//*android.widget.TextView[@text=’Search Wikipedia′ and @index='1']")
    private WebElement searchFld;

    @FindAll(@FindBy(id = "page_list_item_container"))
    private List<WebElement> results;

    @FindBy(id = "search_results_empty")
    private WebElement emptyResults;

    @FindBy(id = "search_close_btn")
    private WebElement closeSearch;

    @FindBy(id = "com.afollestad.materialdialogssample:id/basic")
    private WebElement BasicBtn;

    @FindBy(xpath = "//*[@text='This app wants to access your location.']")
    private WebElement message;

    @FindBy(id = "com.tvptdigital.tripassist:id/ada_gdpr_consent_button_accept")
    private WebElement acceptBtn;

    @FindBy(id = "com.tvptdigital.tripassist:id/ada_gdpr_consent_button_reject")
    private WebElement rejectBtn;

    @FindBy(id = "com.tvptdigital.tripassist:id/idn_ui_landingPage_action_secondary")
    private WebElement login;

    @FindBy(id = "com.tvptdigital.tripassist:id/idnUserName")
    private WebElement userNameFld;

    @FindBy(id = "com.tvptdigital.tripassist:id/idnPassword")
    private WebElement passwordFld;

    @FindBy(id = "com.tvptdigital.tripassist:id/action_bar_root")
    private WebElement landingPage;

    @FindBy(id = "com.tvptdigital.tripassist:id/idn_login_LoginButton")
    private WebElement LOGINbtn;

    @FindBy(id = "com.trivago:id/activityPlatformSelectionConfirmButton")
    private WebElement confirmBtn;

    @FindBy(id = "com.trivago:id/fragmentHomeExpandedDealformSearchTextView")
    private WebElement searchTribagoHotels;

    @FindBy(id="com.android.packageinstaller:id/permission_allow_button")
    private WebElement allowBtn;

    @FindBy(id="com.trivago:id/viewLocationPromptAllowForegroundTextView")
    private WebElement accesslocationYesBtn;

    public SearchWidget(AppiumDriver driver) {
        super(driver);
    }


    public SearchWidget searchForTopic(String topic) {
        waitForElementToAppear(skipBtn);
        skipBtn.click();

        waitForElementToAppear(searchContainer);
        System.out.println("--------------------search for  " + topic);
        searchContainer.sendKeys(topic);
        waitForElementToAppear(results.get(0));
        return this;
    }

    public void closeSearch() {
        closeSearch.click();

    }


    public SearchWidget browseTopMenu() {
        loginBtn.click();
        waitForElementToAppear(profileBtn);
        profileBtn.click();
        return this;
    }

    public SearchWidget searchForInvalid(String invalid) {
        waitForElementToAppear(skipBtn);
        skipBtn.click();

/*        System.out.println("------------>>Search for searchContainer");
        waitForElementToAppear(searchContainer);*/

        System.out.println("------------>>Search for searchFld");
        waitForElementToAppear(searchFld);

        searchFld.click();
        waitForElementToAppear(searchWikipediaFld);

        System.out.println("--------------------write text  " + invalid);
        searchWikipediaFld.sendKeys(invalid);
        //8searchContainer.sendKeys(invalid);

        waitForElementToAppear(emptyResults);
        return this;
    }

    public int getNumberOfSearchResults() {
        System.out.println("Results: " + results.size());
        return results.size();
    }

    public boolean SearchResultContains(String text) {
        boolean found = false;
        for (WebElement element : results) {
            if (element.findElement(By.id("page_list_item_title")).getText().contains(text)) {
                found = true;
                break;
            }
        }
        return found;
    }


    public boolean searchResultsIsEmpty() {
        return emptyResults.isDisplayed();
    }

    public boolean getMessage() {
        return message.isDisplayed();
    }

    public void connect() {

        waitForElementToAppear(confirmBtn);
        confirmBtn.click();
        MyLog.print("Confirm btn OK");
        if(accesslocationYesBtn.isDisplayed()){accesslocationYesBtn.click();}
        if(allowBtn.isDisplayed()){allowBtn.click();}
        MyLog.print("Allow btn OK");
    }

    public void searchHotels(){
        connect();
        waitForElementToAppear(searchTribagoHotels);
        searchTribagoHotels.click();
    }

    public void account() {
        connect();

    }
}
