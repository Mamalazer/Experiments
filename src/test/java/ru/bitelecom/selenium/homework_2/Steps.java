package ru.bitelecom.selenium.homework_2;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class Steps {

    @Step
    @Description("Step 1. Set first name {firstName}")
    public static void setFirstName(PageObjectRegistrationForm obj, String firstName) {
        if (obj.setFirstName(firstName)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting first name");
        }
    }

    @Step
    @Description("Step 2. Set last name {lastName}")
    public static void setLastName(PageObjectRegistrationForm obj, String lastName) {
        if (obj.setLastName(lastName)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting last name");
        }
    }

    @Step
    @Description("Step 3. Set day birthday {day}")
    public static void setBirthDay(PageObjectRegistrationForm obj, int day) {
        if (obj.setBirthDay(day)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting day birthday");
        }
    }

    @Step
    @Description("Step 4. Set month of birthday")
    public static void setBirthMonth(PageObjectRegistrationForm obj, int orderNumOfMonth) {
        if (obj.setBirthMonth(orderNumOfMonth)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting month of birthday");
        }
    }

    @Step
    @Description("Step 5. Set year of birthday {birthdayYear}")
    public static void setBirthYear(PageObjectRegistrationForm obj, int birthdayYear, int actualYear) {
        if (obj.setBirthYear(birthdayYear, actualYear)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting year of birthday");
        }
    }

    @Step
    @Description("Step 6. Set gender {gender}")
    public static void setGender(PageObjectRegistrationForm obj, String gender) {
        if (gender.equals("male")) {
            if (obj.setMaleRadioButton()) {
                Assertions.assertTrue(true);
            } else {
                CustomUtils.getScreen(obj.getDriver());
                Assertions.fail("Error in setting male radio button");
            }
        } else if (gender.equals("female")) {
            if (obj.setFemaleRadioButton()) {
                Assertions.assertTrue(true);
            } else {
                CustomUtils.getScreen(obj.getDriver());
                Assertions.fail("Error in setting female radio button");
            }
        } else {
            Assertions.fail("Incorrect gender " + gender);
        }
    }

    @Step
    @Description("Step 7. Set mail domain {domen}")
    public static void setMailDomain(PageObjectRegistrationForm obj, String domen) {
        if (obj.setMailDomen(domen)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting domen " + domen);
        }
    }

    @Step
    @Description("Step 8. Set account name for mail {accountName}")
    public static void setAccountName(PageObjectRegistrationForm obj, String accountName) {
        if (obj.setAccountName(accountName)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in setting account name");
        }
    }

    @Step
    @Description("Step 9. Generating password")
    public static void generatePassword(PageObjectRegistrationForm obj) {
        if (obj.generatePassword()) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in generating password");
        }
    }

    @Step
    @Description("Step 10. Submit registration form")
    public static void submitRegistration(PageObjectRegistrationForm obj) {
        if (obj.submitRegistration()) {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in registration submit");
        }
    }

}
