package ru.bitelecom.selenide.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class OpenMainPage {

    public float getCourse(String nameMoney, String typeOperation){
        ElementsCollection trHeaders = $$(By.xpath("//*[@class='main-page-exchange main-page-info__card']//tbody/tr[contains(@class,'header')]/td"));
        ElementsCollection trRows = $$(By.xpath("//*[@class='main-page-exchange main-page-info__card']//tbody/tr[contains(@class,'row')]"));
        SelenideElement elemPrice = null;

        for(int i=0;i<trHeaders.size();++i){
            if(trHeaders.get(i).getText().equals(typeOperation)){
                elemPrice=trRows.find(Condition.matchText(nameMoney)).$$(By.xpath("./td")).get(i);
                break;
            }
        }

        return Float.valueOf(elemPrice.getText().replace(",","."));
    }
}
