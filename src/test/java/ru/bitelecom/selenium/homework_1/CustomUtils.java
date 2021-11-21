package ru.bitelecom.selenium.homework_1;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomUtils {

    @Attachment
    public static byte[] getScreen(WebDriver chromeDriver) {

        File screenshot = ((TakesScreenshot)chromeDriver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Attachment
    public static byte[] getScreen(WebDriver chromeDriver, WebElement element) {

        Actions actions = new Actions(chromeDriver);            // Переходим к конкретному элементу, чтобы сделать скрин
        actions.moveToElement(element).build().perform();

        File screenshot = ((TakesScreenshot)chromeDriver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
