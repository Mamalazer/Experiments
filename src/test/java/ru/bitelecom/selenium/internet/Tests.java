package ru.bitelecom.selenium.internet;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
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

    @Test
    @DisplayName("Entry Ad")
    public void entryAd() {
        chromeDriver.get(entryAd.value);
        WebElement modal = chromeDriver.findElement(By.xpath("//div[@id='modal']"));

        if (wait.until(ExpectedConditions.visibilityOf(modal)).isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Close']"))).click();
            chromeDriver.findElement(By.xpath("//a[@id='restart-ad']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Close']"))).click();
        }
    }

    @Test
    @DisplayName("Download file")
    public void downloadFile() {
        chromeDriver.get(downloadFile.value);

        String link = chromeDriver.findElement(By.xpath("//a[text()='200x200.png']")).getAttribute("href");
        //Set file to save
        File fileToSave = new File("C:\\Users\\drkuznetsov\\IdeaProjects\\bi_telecom\\200x200.png");

        //Download file using default org.apache.http client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(link);
        HttpResponse response = null;

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Save file on disk
        try {
            copyInputStreamToFile(response.getEntity().getContent(), fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Upload file")
    public void uploadFile() {
        chromeDriver.get(uploadFile.value);

        chromeDriver.findElement(By.xpath("//input[@id='file-upload']"))
                .sendKeys("C:\\Users\\drkuznetsov\\IdeaProjects\\bi_telecom\\Test.txt");
        chromeDriver.findElement(By.xpath("//input[@id='file-submit']")).click();

        Assertions.assertEquals(chromeDriver.findElement(By.xpath("//h3[text()='File Uploaded!']")).getText(),
                "File Uploaded!" , "Файл не загружен");
    }

    @Test
    @DisplayName("Positive Form Authentication")
    public void formAuthenticationPoz() {
        chromeDriver.get(formAuthentication.value);

        chromeDriver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");
        chromeDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword!");
        chromeDriver.findElement(By.xpath("//button[@type='submit']")).click();

        String mainPage = chromeDriver.getWindowHandle();
        Set<String> handles = chromeDriver.getWindowHandles();
        for (String handle : handles) {
            chromeDriver.switchTo().window(handle);
        }

        chromeDriver.findElement(By.xpath("//a[@class='button secondary radius']")).click();
        chromeDriver.switchTo().window(mainPage);

        boolean check = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='radius']")))
                .isDisplayed();
        Assertions.assertTrue(check, "Error in logout");

    }

    @Test
    @DisplayName("Negative Form Authentication")
    public void formAuthenticationNeg() {
        chromeDriver.get(formAuthentication.value);

        chromeDriver.findElement(By.xpath("//input[@name='username']")).sendKeys("dansmith");
        chromeDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword!");
        chromeDriver.findElement(By.xpath("//button[@type='submit']")).click();

        boolean check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flash error']")))
                .isDisplayed();
        Assertions.assertTrue(check, "Auth with wrong credentials completed");
    }

    @Test
    @DisplayName("Nested frames")
    public void nestedFrames() {
        chromeDriver.get(frames.value);

        chromeDriver.findElement(By.xpath("//a[text()='Nested Frames']")).click();

        chromeDriver.switchTo().frame("frame-top");
        chromeDriver.switchTo().frame("frame-left");
        Assertions.assertEquals(chromeDriver.findElement(By.xpath(".//html/body")).getText(), "LEFT", "frame LEFT не выбран");

        Set<String> handles = chromeDriver.getWindowHandles();
        for (String handle : handles) {
            chromeDriver.switchTo().window(handle);
        }

        chromeDriver.switchTo().frame("frame-top");
        chromeDriver.switchTo().frame("frame-middle");
        Assertions.assertEquals(chromeDriver.findElement(By.xpath(".//html/body")).getText(), "MIDDLE", "frame MIDDLE не выбран");

        for (String handle : handles) {
            chromeDriver.switchTo().window(handle);
        }

        chromeDriver.switchTo().frame("frame-top");
        chromeDriver.switchTo().frame("frame-right");
        Assertions.assertEquals(chromeDriver.findElement(By.xpath(".//html/body")).getText(), "RIGHT", "frame RIGHT не выбран");

    }

    @Test
    @DisplayName("iFrame")
    public void iFrame() {
        chromeDriver.get(frames.value);

        chromeDriver.findElement(By.xpath("//a[text()='iFrame']")).click();

        chromeDriver.switchTo().frame("mce_0_ifr");
        chromeDriver.findElement(By.xpath("//body[@id='tinymce']/p")).clear();
        chromeDriver.findElement(By.xpath("//body[@id='tinymce']/p")).sendKeys("Hello dude!");

        Set<String> handles = chromeDriver.getWindowHandles();
        for (String handle : handles) {
            chromeDriver.switchTo().window(handle);
        }

        chromeDriver.findElement(By.xpath("//button[@title='Align center']")).click();

        chromeDriver.switchTo().frame("mce_0_ifr");
        chromeDriver.findElement(By.xpath("//body[@id='tinymce']/p")).clear();

    }

}
