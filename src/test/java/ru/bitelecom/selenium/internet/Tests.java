package ru.bitelecom.selenium.internet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ru.bitelecom.selenium.internet.Urls.addRemoveElements;

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

}
