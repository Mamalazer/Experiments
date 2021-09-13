package ru.bitelecom.selenide.homework_1.pageObjects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class EkatalogFilterPage {

    public EkatalogSearchResults installFilters(String maxPrice) {
        sleep(5000);
        $(By.xpath("//input[@id='maxPrice_']")).setValue(maxPrice);
        $(By.xpath("//label[@for='c16472']")).click();
        $(By.xpath("//input[contains(@id,'submit')]")).click();
        switchTo().window(0);
        return page(EkatalogSearchResults.class);
    }

}
