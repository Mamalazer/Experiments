package ru.bitelecom.selenium.com.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static ru.bitelecom.selenium.com.automationpractice.Url.CREATE_ACCOUNT_PAGE;

public class CreateAccountPage extends BasePage {

    @FindBy(xpath = "//input[@name='id_gender' and @value='1']")
    WebElement maleRadioButton;

    @FindBy(xpath = "//input[@name='id_gender' and @value='2']")
    WebElement femaleRadioButton;

    @FindBy(xpath = "//input[@name='customer_firstname']")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@name='customer_lastname']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@name='passwd']")
    WebElement passwordField;

    @FindBy(xpath = "//select[@name='days']")
    WebElement birthDayDropDown;

    @FindBy(xpath = "//select[@name='months']")
    WebElement birthMonthDropDown;

    @FindBy(xpath = "//select[@name='years']")
    WebElement birthYearDropDown;



    public CreateAccountPage() {
        driver.get(CREATE_ACCOUNT_PAGE);
        PageFactory.initElements(driver, this);
    }

    public void fillAccountInfo() {
        maleRadioButton.click();
        firstNameField.sendKeys("Dan");
        lastNameField.sendKeys("Smith");
        passwordField.sendKeys("140593");

        Select birthDay = new Select(birthDayDropDown);
        birthDay.selectByValue("14");

        Select birthMonth = new Select(birthMonthDropDown);
        birthMonth.selectByValue("5");

        Select birthYear = new Select(birthYearDropDown);
        birthYear.selectByValue("1993");
    }
}
