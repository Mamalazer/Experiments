package ru.bitelecom.selenium.com.automationpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.bitelecom.selenium.WebDriverSettings;

import static ru.bitelecom.selenium.com.automationpractice.TestData.EMAIL;

public class Tests extends WebDriverSettings {

    @Test
    @Tag("Smoke")
    @DisplayName("Positive registration")
    public void first() {
        MainPage mainPage = new MainPage();
        mainPage.signIn().createAccount(EMAIL).fillAccountInfo();
    }

}
