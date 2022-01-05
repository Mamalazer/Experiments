package ru.bitelecom.selenium.com.automationpractice;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ru.bitelecom.selenium.com.automationpractice.Url.AUTOMATIONPRACTISE_COM;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@class='login']")
    WebElement signInButton;

    public MainPage() {
        driver.get(AUTOMATIONPRACTISE_COM);
        PageFactory.initElements(driver, this);
    }

    public LoginPage signIn() {
        signInButton.click();
        Assertions.assertEquals(driver.getTitle(), "Login - My Store");
        return new LoginPage();
    }
}
