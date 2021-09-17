package ru.bitelecom.selenium.homework_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests extends WebDriverSettings {

    @Test
    @DisplayName("Positive registration in mail.ru")
    public void firstTest() {

        PageObjectRegistrationForm pageObjectRegistrationForm = new PageObjectRegistrationForm(chromeDriver);

        Steps.setFirstName(pageObjectRegistrationForm, "TestFirstName");
        Steps.setLastName(pageObjectRegistrationForm, "TestLastName");
//        Steps.setBirthDay(pageObjectRegistrationForm, 14);
        Steps.setBirthDay(pageObjectRegistrationForm, "14");
//        Steps.setBirthMonth(pageObjectRegistrationForm, 5);
        Steps.setBirthMonth(pageObjectRegistrationForm, "Май");
//        Steps.setBirthYear(pageObjectRegistrationForm, 1993, 2021);
        Steps.setBirthYear(pageObjectRegistrationForm, "1993");
        Steps.setGender(pageObjectRegistrationForm, "female");
        Steps.setMailDomain(pageObjectRegistrationForm, "@list.ru");
        Steps.setAccountName(pageObjectRegistrationForm, "TestNameTestLastName");
        Steps.generatePassword(pageObjectRegistrationForm);
        Steps.submitRegistration(pageObjectRegistrationForm);

    }

}
