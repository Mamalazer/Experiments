package ru.bitelecom.selenium.homework_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PageObjectMain {

    private WebDriver driver;
    private WebElement searchField;
    private WebElement searchButton;

    public PageObjectMain(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.e-katalog.ru/");
        this.searchField = driver.findElement(By.xpath("//input[contains(@name,'search')]"));
        this.searchButton = driver.findElement(By.xpath("//button[contains(@name,'search')]"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void search(String searchWord) {
        searchButton.click();
        searchField.sendKeys(searchWord);
        searchButton.click();
    }

    public boolean switchPage(String namePage) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        for (String tab : tabs) {
            driver.switchTo().window(tab);
            return true;
        }
        return false;
    }
}


