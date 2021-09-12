package ru.bitelecom.selenium.homework_1;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectResults {

    private WebDriver driver;
    private WebElement showAll;
    private List<WebElement> searchResultsPrice;
    private List<WebElement> searchResultsScreen;

    public PageObjectResults(WebDriver driver) {
        this.driver = driver;
        this.showAll = driver.findElement(By.xpath("//div[contains(@class,'more') and @jtype='click']"));
        this.searchResultsPrice = driver.findElements(By.xpath("//form[@id='list_form1']//span[contains(@id,'price')]"));
        this.searchResultsScreen = driver.findElements(By.xpath("//form[@id='list_form1']//div[contains(@title,'Экран')]"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void showAllResults() {
        showAll.click();
    }

    public boolean checkPrice(int price) {
        for (WebElement el : searchResultsPrice) {
            //System.out.println(el.getText());
            if (Integer.parseInt(el.getText().replace(" ", "")) > price) {
                Assertions.fail("Price over the " + price);
                return false;
            }
        }
        return true;
    }

    public boolean checkScreenDiag(Double minScreenDiag, Double maxScreenDiag) {
        for (WebElement el : searchResultsScreen) {
            //System.out.println(el.getText());
            if (Double.parseDouble(el.getText().substring(6, 8)) < minScreenDiag && Double.parseDouble(el.getText().substring(6, 8)) > maxScreenDiag) {
                Assertions.fail("Screen lower then " + minScreenDiag + " or bigger then " + maxScreenDiag);
                return false;
            }
        }
        return true;
    }

}
