package ru.bitelecom.selenium.internet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

    @Test
    @DisplayName("Disappearing Elements")
    public void disappearingElements() {
        chromeDriver.get(disappearingElements.value);
        List<WebElement> elements = chromeDriver.findElements(By.xpath("//a[text()='Gallery']"));

        if (elements.size() > 0) {
            elements.stream()
                    .findFirst()
                    .get()
                    .click();

            Assertions.assertTrue(chromeDriver.findElement(By.xpath("//h1[text()='Not Found']")).isDisplayed(),
                    "Переход не осуществлён");
        }

    }

    @Test
    @DisplayName("Drag and Drop")
    public void dragAndDrop() {
        chromeDriver.get(dragAndDrop.value);
        Actions action = new Actions(chromeDriver);
        WebElement a = chromeDriver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement b = chromeDriver.findElement(By.xpath("//div[@id='droppable']"));
        WebElement check = chromeDriver.findElement(By.xpath("//div[@id='droppable']/p"));

        action.moveToElement(a).clickAndHold().moveToElement(b).release().build().perform();

        Assertions.assertEquals("Dropped!", check.getText(), "Элемент не перенесён");

    }

    @Test
    @DisplayName("Dropdown List")
    public void dropdownList() {
        chromeDriver.get(dropdownList.value);
        Select select = new Select(chromeDriver.findElement(By.id("dropdown")));
        List<WebElement> options = select.getOptions();

        select.selectByVisibleText("Option 1");
        Assertions.assertTrue(options.get(1).isSelected(), "Опция 1 не выбрана");

        select.selectByIndex(2);
        Assertions.assertTrue(options.get(2).isSelected(), "Опция 2 не выбрана");

        select.selectByValue("1");
        Assertions.assertTrue(options.get(1).isSelected(), "Опция 1 не выбрана");
    }

    @Test
    @DisplayName("Dynamic Controls")
    public void dynamicControls() {
        By checkBoxStart = By.xpath("//input[@label='blah']");
        By checkBoxFinish = By.xpath("//input[@id='checkbox']");

        chromeDriver.get(dynamicControls.value);
        List<WebElement> checkBox = chromeDriver.findElements(checkBoxStart);

        if (checkBox.size() > 0) {
            chromeDriver.findElement(checkBoxStart).click();
            Assertions.assertTrue(chromeDriver.findElement(checkBoxStart).isSelected(),
                    "Чек бокс не активирован");
            chromeDriver.findElement(By.xpath("//button[text()='Remove']")).click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(checkBoxStart));
            List<WebElement> check = chromeDriver.findElements(checkBoxStart);
            Assertions.assertTrue(check.size() == 0, "Кнопка \"Remove\" не сработала");
        } else {
            Assertions.fail("Чек бокс не обнаружен");
        }

        chromeDriver.findElement(By.xpath("//button[text()='Add']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxFinish)).click();
        Assertions.assertTrue(chromeDriver.findElement(checkBoxFinish).isSelected(),
                "Чек бокс не активирован");
    }

    @Test
    @DisplayName("Dynamic Controls 2")
    public void dynamicControls_2() {
        By textField = By.xpath("//input[@type='text']");
        By enableButton = By.xpath("//button[text()='Enable']");

        chromeDriver.get(dynamicControls.value);

        if (!chromeDriver.findElement(textField).isEnabled()) {

            chromeDriver.findElement(enableButton).click();
            wait.until(ExpectedConditions.elementToBeClickable(textField));
            Assertions.assertTrue(chromeDriver.findElement(textField).isEnabled());

            chromeDriver.findElement(textField).sendKeys("Hi guys, I'm Eminem!");

            chromeDriver.findElement(By.xpath("//button[text()='Disable']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(enableButton));
            Assertions.assertTrue(!chromeDriver.findElement(textField).isEnabled(), "Кнопка \"Disable\" не сработала");
        }
    }

}
