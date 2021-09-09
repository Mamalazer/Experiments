package ru.bitelecom.selenide.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchResult {

    public ElementsCollection results(){
        return $$(By.xpath("//div[@class='g']"));
    }

    public OpenMainPage goLinkByName (String linkName){
        SelenideElement resultLink = $$(By.xpath("//div[@class='g']")).find(Condition.matchText(linkName));

        resultLink.$(By.xpath(".//a[@href]")).click();

        switchTo().window(1);
        //Assertions.assertTrue(title().contains(linkName),"Не удалось переключится на вкладку: "+linkName);

        return page(OpenMainPage.class);
    }

}
