package ru.bitelecom.selenide.homework_1.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.bitelecom.selenide.homework_1.pageObjects.FilterPage;
import ru.bitelecom.selenide.homework_1.pageObjects.MainPage;
import ru.bitelecom.selenide.homework_1.pageObjects.SearchResults;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Tests {

    @BeforeEach
    public void option(){
        //для того, чтобы постоянно не применять эти параметры в каждом тесте, то конфигурацию пред\before и пост\after условий можно вынести в базовый класс 
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
    @Owner("DRKuznetsov")
    @Tag("one")
    @DisplayName("Search in e-katalog")
    public void searchAndFilter(){

        open("https://www.e-katalog.ru/");
        $(By.xpath("//input[contains(@name,'search')]")).setValue("Смартфоны");
        $(By.xpath("//button[contains(@name,'search')]")).click();
        switchTo().window(0);

        sleep(5000);
        $(By.xpath("//input[@id='maxPrice_']")).setValue("20000");
        $(By.xpath("//label[@for='c16472']/parent::li")).click();
        $(By.xpath("//input[contains(@id,'submit')]")).click();
        switchTo().window(0);

        $(By.xpath("//div[contains(@class,'more') and @jtype='click']")).click();
        List<SelenideElement> resultsPrice = $$(By.xpath("//form[@id='list_form1']//span[contains(@id,'price')]"));
        List<SelenideElement> resultsScreen = $$(By.xpath("//form[@id='list_form1']//div[contains(@title,'Экран')]"));

        for (WebElement el : resultsPrice) {
            //System.out.println(el.getText());
            if (Integer.parseInt(el.getText().replace(" ", "")) > 20000) {
                Assertions.fail("Price over the 20000");
            }
        }

        for (WebElement el : resultsScreen) {
            //System.out.println(el.getText());
            if (Double.parseDouble(el.getText().substring(6, 8)) < 5.1 && Double.parseDouble(el.getText().substring(6, 8)) > 5.5) {
                Assertions.fail("Screen lower then 5.1 or bigger then 5.5");
            }
        }

    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("two")
    @DisplayName("Search in e-katalog ")
    public void searchAndFilterWithPO(){
        //TODO для того чтобы постоянно не объявлять экземпляны pageObject можно использовать цепочки вызовов, как в примере mailru. тогда через точку просто продолжается вызов следующего шага, но можно и так.
        MainPage ekatalogMainPage = open("https://www.e-katalog.ru/", MainPage.class);
        FilterPage ekatalogFilterPage = ekatalogMainPage.FilterPage("Смартфоны");
        SearchResults ekatalogSearchResults = ekatalogFilterPage.installFilters("20000"); // значения вводятся здесь, hardcode, а можно получать из config файла

        // на уровень теста (сюда) если PageObject то не выносятся Assert, а скрываются внутри шага.
        // Assertы сами описаны правильно
        Assertions.assertTrue(ekatalogSearchResults.checkPriceResults(20000), "Price bigger then installed");
        Assertions.assertTrue(ekatalogSearchResults.checkScreenResults(5.1, 5.5), "Size of the screen lower or bigger then installed");
    }

}
