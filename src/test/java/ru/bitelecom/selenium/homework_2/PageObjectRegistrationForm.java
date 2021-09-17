package ru.bitelecom.selenium.homework_2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageObjectRegistrationForm {

    private WebDriver driver;
    private Actions action;

    private WebElement firstName;
    private WebElement lastName;
    private WebElement birthDay;
    private WebElement birthMonth;
    private WebElement birthYear;
    private WebElement maleRadioButton;
    private WebElement femaleRadioButton;
    private WebElement mailDomen;
    private WebElement accountName;
    private WebElement passwordGenerator;
    private WebElement submitButton;

    public PageObjectRegistrationForm(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(this.driver);
        this.driver.get("https://account.mail.ru/signup?from=vk");
        this.firstName = driver.findElement(By.xpath("//input[@data-test-id='first-name']"));
        this.lastName = driver.findElement(By.xpath("//input[@data-test-id='last-name']"));
        this.birthDay = driver.findElement(By.xpath("//div[@class='select-0-2-78 daySelect-0-2-79']"));
        this.birthMonth = driver.findElement(By.xpath("//div[@class='base-0-2-57']"));
        this.birthYear = driver.findElement(By.xpath("//div[@class='select-0-2-78 yearSelect-0-2-80']"));
        this.maleRadioButton = driver.findElement(By.xpath("//input[@class='input-0-2-101' and @value='male']/parent::div"));
        this.femaleRadioButton = driver.findElement(By.xpath("//input[@class='input-0-2-101' and @value='female']/parent::div"));
        this.mailDomen = driver.findElement(By.xpath("//div[@data-test-id='account__select']"));
        this.accountName = driver.findElement(By.xpath("//input[@data-test-id='account__input']"));
        this.passwordGenerator = driver.findElement(By.xpath("//a[@data-test-id='generate-password']"));
        this.submitButton = driver.findElement(By.xpath("//form/button[@type='submit']"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean setFirstName(String firstName) {
        driver.findElement(By.xpath("//input[@data-test-id='first-name']")).sendKeys(firstName);
        if (!driver.findElement(By.xpath("//input[@data-test-id='first-name']")).getAttribute("value")
                .equals(firstName)) {
            return false;
        }
        return true;
    }

    public boolean setLastName(String lastName) {
        driver.findElement(By.xpath("//input[@data-test-id='last-name']")).sendKeys(lastName);
        if (!driver.findElement(By.xpath("//input[@data-test-id='last-name']")).getAttribute("value")
                .equals(lastName)) {
            return false;
        }
        return true;
    }

    public boolean setBirthDay(int day) {

        if (day < 1 || day > 31) {
            System.out.println("Invalid date " + day);
            return false;
        }

        birthDay.click();
//        String html = driver.findElement(By.xpath("//div[@data-test-id='select-option-wrapper']")).getAttribute("innerHTML");
//        System.out.println(html);
//        driver.findElement(By.xpath("//div[@data-test-id='select-option-wrapper']//span[text()='20']")).click();

        for (int i = 1; i < day; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        action.sendKeys(Keys.ENTER).build().perform();

        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'birth-date__day-value:')]")).getText()
                .equals(String.valueOf(day))) {
            return false;
        }
        return true;
    }

    public boolean setBirthDay(String day) {
        birthDay.click();
        driver.findElement(By.xpath("//div[@data-test-id='select-option-wrapper']//span[text()='" + day + "']")).click();

        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'birth-date__day-value:')]")).getText()
                .equals(day)) {
            return false;
        }
        return true;
    }

    public boolean setBirthMonth(int orderNumOfMonth) {
        String[] months = new String[] {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
                                        "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        if (orderNumOfMonth < 1 || orderNumOfMonth > 12) {
            System.out.println("Invalid month " + orderNumOfMonth);
            return false;
        }

        birthMonth.click();

        for (int i = 1; i < orderNumOfMonth; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        action.sendKeys(Keys.ENTER).build().perform();

        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'birth-date__month-value:')]")).getText()
                .equals(months[orderNumOfMonth - 1])) {
            return false;
        }
        return true;

    }

    public boolean setBirthMonth(String month) {
        birthMonth.click();
        driver.findElement(By.xpath("//div[@data-test-id='select-option-wrapper']//span[text()='" + month + "']")).click();
        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'birth-date__month-value:')]")).getText()
                .equals(month)) {
            return false;
        }
        return true;
    }

    public boolean setBirthYear(int birthdayYear, int actualYear) {
        int iterate = actualYear - birthdayYear;
        birthYear.click();

        for (int i = 0; i < iterate; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        action.sendKeys(Keys.ENTER).build().perform();

        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'birth-date__year-value:')]")).getText()
                .equals(String.valueOf(birthdayYear))) {
            return false;
        }
        return true;
    }

    public boolean setBirthYear(String birthdayYear) {
        birthYear.click();
        driver.findElement(By.xpath("//div[@data-test-id='select-option-wrapper']//span[text()='" + birthdayYear + "']")).click();

        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'birth-date__year-value:')]")).getText()
                .equals(String.valueOf(birthdayYear))) {
            return false;
        }
        return true;
    }

    public boolean setMaleRadioButton() {
        maleRadioButton.click();
        if (!driver.findElement(By.xpath("//div[@data-test-id='gender-form-field-inner']")).getAttribute("data-test-value")
                .equals("male")) {
            return false;
        }
        return true;
    }

    public boolean setFemaleRadioButton() {
        femaleRadioButton.click();
        if (!driver.findElement(By.xpath("//div[@data-test-id='gender-form-field-inner']")).getAttribute("data-test-value")
                .equals("female")) {
            return false;
        }
        return true;
    }

    public boolean setMailDomen(String domen) {
        mailDomen.click();
        driver.findElement(By.xpath("//div[@data-test-id='select-option-wrapper']//span[text()='" + domen + "']")).click();

        if (!driver.findElement(By.xpath("//span[contains(@data-test-id, 'account__select-value:')]")).getText()
                .equals(domen)) {
            return false;
        }
        return true;
    }

    public boolean setAccountName(String name) {
        accountName.sendKeys(name);
        if (!driver.findElement(By.xpath("//input[@name='username']")).getAttribute("value").equals(name.toLowerCase())) {
            return false;
        }
        return true;
    }

    public boolean generatePassword() {
        passwordGenerator.click();
        if (!driver.findElement(By.xpath("//input[@name='password']")).getAttribute("value").matches(".+")) {
            return false;
        }
        return true;
    }

    public boolean submitRegistration() {
        submitButton.click();

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        if (driver.findElements(By.xpath("//p[text()='Укажите код с картинки']")).isEmpty()) {
            return false;
        }
        return true;
    }

}
