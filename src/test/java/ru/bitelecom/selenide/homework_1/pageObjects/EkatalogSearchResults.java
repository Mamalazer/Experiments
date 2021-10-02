package ru.bitelecom.selenide.homework_1.pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EkatalogSearchResults {


// этот метод не является шагом и ничего не проверяет в теста, а как служебный метод библиотеки Java.
// поэтому если если у него локаторы, а вместо этого принимать их в виде аргументов, то можно вынести в Utils.

// Но если хочется сделать это шагом теста, тогда нужно определить локаторы вне метода, а также добавить assertions
    public boolean checkPriceResults(int price) {
        $(By.xpath("//div[contains(@class,'more') and @jtype='click']")).click();

        //Вместо списка List можно использовать готовый cписок public ElementsCollection 
        // List<SelenideElement> resultsPrice = $$(By.xpath("//form[@id='list_form1']//span[contains(@id,'price')]"));

        public ElementsCollection resultsPrice $$x("//form[@id='list_form1']//span[contains(@id,'price')]");
        
        
        //если нужно обрезать пустые символы вначале и в конце, то есть функция trim
        //el.getText().trim()

        for (WebElement el : resultsPrice) {
            //System.out.println(el.getText());  //TODO закомментированыый нерабочий код нужно удалять при чистовом коммите.
            /*
            if (Integer.parseInt(el.getText().replace(" ", "")) > price) {
                return false;
            } */
            // можно заменить тогда на ассерт 
            assertTrue( Integer.parseInt(el.getText().trim()) > price, "Стоимость больше чем максимально возможная"
        }
        //тогда ничего возвращать не нужно или вернуть PageObject на котором будет следующий шаг.
        return true;

    }


    
    public boolean checkScreenResults(double minDiag, double maxDiag) {
        List<SelenideElement> resultsScreen = $$(By.xpath("//form[@id='list_form1']//div[contains(@title,'Экран')]"));

        for (WebElement el : resultsScreen) {
            //System.out.println(el.getText());
            if (Double.parseDouble(el.getText().substring(6, 8)) < minDiag && Double.parseDouble(el.getText().substring(6, 8)) > maxDiag) {
                return false;
            }
        }

        return true;

    }

}
