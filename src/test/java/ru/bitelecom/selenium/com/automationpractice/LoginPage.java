package ru.bitelecom.selenium.com.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ru.bitelecom.selenium.com.automationpractice.Url.LOGIN_PAGE;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='email_create']")
    WebElement emailField;

    @FindBy(xpath = "//button[@name='SubmitCreate']")
    WebElement createAccountButton;

    @FindBy(xpath = "//input[@name='email_create']/parent::div")
    WebElement emailValidation;

    public LoginPage() {
        driver.get(LOGIN_PAGE);
        PageFactory.initElements(driver, this);
    }

    public CreateAccountPage createAccount(String email) {
        emailField.click();
        emailField.sendKeys(email);
        createAccountButton.click();
        return new CreateAccountPage();
    }

}
