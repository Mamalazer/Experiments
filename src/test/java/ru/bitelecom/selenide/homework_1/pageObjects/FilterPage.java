package ru.bitelecom.selenide.homework_1.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class FilterPage {

    public SelenideElement maxPriceElement = $x("//input[@id='maxPrice_']");

    public SearchResults installFilters(String maxPrice) {

        $(By.xpath("//label[@for='c16472']/parent::li")).should(Condition.visible).click();
        $(By.xpath("//input[contains(@id,'submit')]")).should(Condition.visible).click();
        switchTo().window(0);

        assertEquals(maxPrice, maxPriceElement.getText(), "Введенное значение не совпадает, фильтр maxPrice не был установлен");
        
        return page(SearchResults.class);
    }

}
