package ru.bitelecom.selenide.homework_1.pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EkatalogSearchResults {

    public boolean checkPriceResults(int price) {
        $(By.xpath("//div[contains(@class,'more') and @jtype='click']")).click();
        List<SelenideElement> resultsPrice = $$(By.xpath("//form[@id='list_form1']//span[contains(@id,'price')]"));

        for (WebElement el : resultsPrice) {
            //System.out.println(el.getText());
            if (Integer.parseInt(el.getText().replace(" ", "")) > price) {
                return false;
            }
        }

        return true;

    }

    public boolean checkScreenResults(double minDiag, double maxDiag) {
        List<SelenideElement> resultsScreen = $$(By.xpath("//form[@id='list_form1']//div[contains(@title,'Экран')]"));

        for (WebElement el : resultsScreen) {
            //System.out.println(el.getText());
            if (Double.parseDouble(el.getText().substring(6, 8)) < minDiag && Double.parseDouble(el.getText().substring(6, 8)) > maxDiag) {
                return false;
            }
        }

        return true;

    }

}
