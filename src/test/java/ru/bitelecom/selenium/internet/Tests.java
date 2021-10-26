package ru.bitelecom.selenium.internet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ru.bitelecom.selenium.internet.Urls.*;

public class Tests extends WebDriverSettings {

    @Test
    @DisplayName("Add/Remove Elements")
    public void addRemoveElements() {
        chromeDriver.get(addRemoveElements.value);
        chromeDriver.findElement(By.xpath("//button[text()='Add Element']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']"))).click();
        List<WebElement> deleteButton = chromeDriver.findElements(By.xpath("//button[text()='Delete']"));
        Assertions.assertEquals(0, deleteButton.size(), "Кнопка Delete осталась на странице");
    }

    @Test
    @DisplayName("Basic authorisation")
    public void basicAuth() {
        chromeDriver.get(basicAuth.value);
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='example']/p"))).getText();
        Assertions.assertEquals(text, "Congratulations! You must have the proper credentials.", "Ошибка при авторизации");
    }

    @Test
    @DisplayName("Checkboxes")
    public void checkBoxes() {
        chromeDriver.get(checkBoxes.value);
        WebElement firstCheckBox = chromeDriver.findElement(By.xpath("//br/preceding-sibling::input"));
        WebElement secondCheckBox = chromeDriver.findElement(By.xpath("//br/following-sibling::input"));

        if (!firstCheckBox.isSelected()) {
            firstCheckBox.click();
            Assertions.assertTrue(firstCheckBox.isSelected(), "Чек бокс не активирован");
        }

        if (secondCheckBox.isSelected()) {
            secondCheckBox.click();
            Assertions.assertFalse(secondCheckBox.isSelected(), "Чек бокс не дезактивирован");
        }
    }

    @Test
    @DisplayName("Context menu")
    public void contextMenu() {
        chromeDriver.get(contextMenu.value);
        WebElement button = chromeDriver.findElement(By.xpath("//div[@id='hot-spot']"));
        Actions action = new Actions(chromeDriver);
        action.contextClick(button).build().perform();
        String alert = wait.until(ExpectedConditions.alertIsPresent()).getText();
        if (alert.equals("You selected a context menu")) {
            chromeDriver.switchTo().alert().accept();
        } else {
            Assertions.fail("Правая кнопка мыши не сработала");
        }

    }

}
