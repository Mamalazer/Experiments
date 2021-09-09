package ru.bitelecom.selenide.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.bitelecom.selenide.pageObjects.GoogleMainPage;
import ru.bitelecom.selenide.pageObjects.GoogleSearchResult;
import ru.bitelecom.selenide.pageObjects.OpenMainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests {

    @BeforeEach
    public void option(){
        Configuration.timeout = 6000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

    }

    @Test
    @Owner("Pushkin")
    @Tag("one")
    @DisplayName("Первый тест на Selenide")
    public void firstSelenide(){
        open("https://www.google.ru/");
        $(By.name("q")).setValue("Открытие Википедия").pressEnter();
        ElementsCollection resultSearch = $$(By.xpath("//div[@class='g']"));
        System.out.println(resultSearch);
        SelenideElement elem = resultSearch.find(text("Открытие (банк) - — Википедия"));
        System.out.println("_______");
        System.out.println(elem.getText());
        SelenideElement elemOtkr = $(By.xpath("//div[@class='g']")).shouldHave(text("ПАО Банк «Финансовая корпорация Открытие» (работает под брендом"));
        elemOtkr.$(By.xpath(".//a[@href]")).click();
        switchTo().window(1);
        sleep(5000);
        closeWebDriver();
    }

    @Test
    @Owner("Tolstoy")
    @Tag("two")
    @DisplayName("Проверка курса валют на сайте Открытия")
    public void otkrSelenide(){
        GoogleMainPage googlePage = open("https://www.google.ru/", GoogleMainPage.class);
        GoogleSearchResult googleSearchResult = googlePage.search("Открытие банк");
        OpenMainPage openPage = googleSearchResult.goLinkByName("Банк Открытие: Частным клиентам");
        System.out.println(openPage.getCourse("USD","Банк покупает"));
        Assertions.assertTrue(
                openPage.getCourse("USD","Банк покупает")
                        <
                        openPage.getCourse("USD","Банк продает"),
                "Курс покупки USD не меньше курса продажи"
        );
    }

}
