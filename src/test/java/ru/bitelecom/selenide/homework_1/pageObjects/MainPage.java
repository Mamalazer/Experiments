package ru.bitelecom.selenide.homework_1.pageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public FilterPage FilterPage(String searchWord) {

        $(By.xpath("//input[contains(@name,'search')]")).should(Condition.visible).setValue(searchWord);
        $(By.xpath("//button[contains(@name,'search')]")).should(Condition.visible).click();

        switchTo().window(0);

        return page(FilterPage.class);
    }

}
