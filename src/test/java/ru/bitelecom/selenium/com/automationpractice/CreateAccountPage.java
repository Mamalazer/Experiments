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

    @FindBy(xpath = "//input[@name='company']")
    WebElement companyField;

    @FindBy(xpath = "//input[@name='address1']")
    WebElement addressField_1;

    @FindBy(xpath = "//input[@name='address2']")
    WebElement addressField_2;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityField;

    @FindBy(xpath = "//select[@name='id_state']")
    WebElement stateField;

    @FindBy(xpath = "//input[@name='postcode']")
    WebElement postcodeField;

    @FindBy(xpath = "//select[@name='id_country']")
    WebElement countryField;

    @FindBy(xpath = "//textarea[@name='other']")
    WebElement additionalInformationField;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement homePhoneField;

    @FindBy(xpath = "//input[@name='phone_mobile']")
    WebElement mobilePhoneField;

    @FindBy(xpath = "//input[@name='alias']")
    WebElement aliasAddressField;

    @FindBy(xpath = "//button[@name='submitAccount']")
    WebElement submitButton;

    public CreateAccountPage() {
        driver.get(CREATE_ACCOUNT_PAGE);
        PageFactory.initElements(driver, this);
    }

    public AccountPage fillAccountInfo() {
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

        companyField.sendKeys("Company");
        addressField_1.sendKeys("48  Davids Lane");
        addressField_2.sendKeys("79");
        cityField.sendKeys("New York");

        Select state = new Select(stateField);
        state.selectByVisibleText("New York");

        postcodeField.sendKeys("44004");

        Select country = new Select(countryField);
        country.selectByVisibleText("United States");

        additionalInformationField.sendKeys("Hi guys, I'm Eminem");
        homePhoneField.sendKeys("55-49-93");
        mobilePhoneField.sendKeys("+79645707532");

        aliasAddressField.clear();
        aliasAddressField.sendKeys("Sweet home Alabama");
        submitButton.click();

        return new AccountPage();
    }
}
