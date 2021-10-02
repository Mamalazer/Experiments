package ru.bitelecom.selenide.homework_1.pageObjects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class EkatalogMainPage {

    public EkatalogFilterPage EkatalogFilterPage(String searchWord) {
        
        //TODO аналогично FilterPage
        $(By.xpath("//input[contains(@name,'search')]")).setValue(searchWord); 
        $(By.xpath("//button[contains(@name,'search')]")).click();

        switchTo().window(0);

        return page(EkatalogFilterPage.class);
    }

}
