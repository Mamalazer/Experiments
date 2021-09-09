package ru.bitelecom.selenide.pageObjects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleMainPage {

    public GoogleSearchResult search(String query){
        $(By.name("q")).setValue(query).pressEnter();
        return page(GoogleSearchResult.class);
    }

}
