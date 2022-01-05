package ru.bitelecom.selenium.com.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static void setWebDriverWait(WebDriverWait webDriverWait) {
        wait = webDriverWait;
    }

}
