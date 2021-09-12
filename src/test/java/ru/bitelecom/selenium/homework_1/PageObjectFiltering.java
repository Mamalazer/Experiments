package ru.bitelecom.selenium.homework_1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PageObjectFiltering {

    private WebDriver driver;
    private WebElement maxPriceField;
    private WebElement diagCheckBox;
    private WebElement submitChoice;

    public PageObjectFiltering(WebDriver driver) {
        this.driver = driver;
        this.maxPriceField = driver.findElement(By.xpath("//input[@id='maxPrice_']"));
        this.diagCheckBox = driver.findElement(By.xpath("//label[@for='c16472']"));
        this.submitChoice = driver.findElement(By.xpath("//input[contains(@id,'submit')]"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setMaxPrice(String maxPrice) {
        maxPriceField.sendKeys(maxPrice);
    }

    public void setDiag() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", diagCheckBox);
    }

    public void submitChoice() {
        submitChoice.click();
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
