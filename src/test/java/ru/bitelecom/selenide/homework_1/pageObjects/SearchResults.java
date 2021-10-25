package ru.bitelecom.selenide.homework_1.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResults {

    public boolean checkPriceResults(int price) {
        $(By.xpath("//div[contains(@class,'more') and @jtype='click']")).click();

        ElementsCollection resultsPrice = $$x("//form[@id='list_form1']//span[contains(@id,'price')]");
        
        
        //если нужно обрезать пустые символы вначале и в конце, то есть функция trim
        //el.getText().trim()

        for (WebElement el : resultsPrice) {
            /*
            if (Integer.parseInt(el.getText().replace(" ", "")) > price) {
                return false;
            } */
            // можно заменить тогда на ассерт 
            assertTrue( Integer.parseInt(el.getText().trim()) > price, "Стоимость больше чем максимально возможная");
        }
        //тогда ничего возвращать не нужно или вернуть PageObject на котором будет следующий шаг.
        return true;

    }


    
    public boolean checkScreenResults(double minDiag, double maxDiag) {
        ElementsCollection resultsScreen = $$x("//form[@id='list_form1']//div[contains(@title,'Экран')]");

        for (WebElement el : resultsScreen) {
            if (Double.parseDouble(el.getText().substring(6, 8)) < minDiag && Double.parseDouble(el.getText().substring(6, 8)) > maxDiag) {
                return false;
            }
        }

        return true;

    }

}
