package ru.bitelecom.selenide.internet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.*;
import static ru.bitelecom.selenide.internet.Urls.addRemoveElements;

public class Tests {

    @BeforeEach
    public void option() {
        Configuration.timeout = 6000; // установка задержки в милисекундах
//        Configuration.browser = "chrome";
        Configuration.browser = "firefox";
        Configuration.startMaximized = true;

        // Работает только в версии 5.5.0, не выше
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions"); // запускает браузер без расширений

        // Добавляем созданные настройки в браузер
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;

        // Используется для настроек в более поздних версиях selenide
//        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
//        WebDriver driver;
//        driver = new ChromeDriver(chromeOptions);
//        setWebDriver(driver);
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("one")
    @DisplayName("Search in e-katalog")
    public void addRemoveElements() throws InterruptedException {
        open(addRemoveElements.url);
        $(By.xpath("//button[text()='Add Element']")).click();
        $(By.xpath("//button[text()='Delete']")).shouldBe(Condition.visible).click();
        boolean check = $(By.xpath("//button[text()='Delete']")).isDisplayed();
        Assertions.assertFalse(check, "Элемент не был удалён со страницы");
    }

}
