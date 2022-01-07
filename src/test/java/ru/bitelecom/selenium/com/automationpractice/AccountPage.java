package ru.bitelecom.selenium.com.automationpractice;

import org.openqa.selenium.support.PageFactory;

import static ru.bitelecom.selenium.com.automationpractice.Url.ACCOUNT_PAGE;

public class AccountPage extends BasePage {

    public AccountPage() {
        driver.get(ACCOUNT_PAGE);
        PageFactory.initElements(driver, this);
    }

}
