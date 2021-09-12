package ru.bitelecom.selenium.homework_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests extends WebDriverSettings {

    @Test
    @DisplayName("Search in e-katalog")
    public void firstTest() {

        PageObjectMain pageObjectMain = new PageObjectMain(chromeDriver);
        Steps.searchInECatalog(pageObjectMain, "Смартфоны");

        PageObjectFiltering pageObjectFiltering = new PageObjectFiltering(chromeDriver);
        Steps.installFilter(pageObjectFiltering, "телефоны");

        PageObjectResults pageObjectResults = new PageObjectResults(chromeDriver);
        Steps.checkSearchResults(pageObjectResults, 20000, 5.1, 5.5);

    }
}
