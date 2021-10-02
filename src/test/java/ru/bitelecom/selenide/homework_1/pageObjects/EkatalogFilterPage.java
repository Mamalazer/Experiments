package ru.bitelecom.selenide.homework_1.pageObjects;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

//Излишнее слово Ekatalog не нужно включать, а можно вынести в пакет ekatalog
// и потом имена классов уже будут: FilterPage, MainPage и т.д.
public class EkatalogFilterPage {
    //нужно выносить локаторы элементов, чтобы их повторно использовать и было более понятно для каких они элементов страницы
    //  $(By.xpath("") можно сократить $x("")
    public SelenideElement maxPriceElement = $x("//input[@id='maxPrice_']");

    public EkatalogSearchResults installFilters(String maxPrice) {
        sleep(5000); //TODO убрать, нужно вместо этого использовать неявное ожидание элемента.
        //т.к. не проверяется элемент через Selenide, то явное ожидание сейчас нет и поэтому он сразу пытается ввести в поле значение, которое еще не появилось.
        maxPrice.should(visible).setValue(maxPrice); // вот так будет ожидание его загрузки
        $(By.xpath("//label[@for='c16472']")).click(); //аналогично...
        $(By.xpath("//input[contains(@id,'submit')]")).click();
        switchTo().window(0);

        //TODO не хватает Assertions проверок: выполнено открытие нужной вкладки и что фильтры были применены
        assertEquals(maxPrice, maxPriceElement.getText(), "Введенное значение не совпадает, фильтр maxPrice не был установлен")
        
        return page(EkatalogSearchResults.class);
    }

}
