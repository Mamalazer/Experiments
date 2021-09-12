package ru.bitelecom.selenium.homework_1;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


public class Steps {

    @Step
    @Description("Step 1. Search {searchWord} in e-catalog")
    public static void searchInECatalog(PageObjectMain obj, String searchWord) {
        obj.search(searchWord);

        if (obj.switchPage(searchWord)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error search of " + searchWord + " in e-catalog");
        }
    }

    @Step
    @Description("Step 2. Create filter")
    public static void installFilter(PageObjectFiltering obj, String searchWord) {
        obj.setMaxPrice("20000");
        obj.setDiag();
        obj.submitChoice();

        if (obj.switchPage(searchWord)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error filtering in e-catalog");
        }

    }

    @Step
    @Description("Step 3. Check results of search")
    public static void checkSearchResults(PageObjectResults obj, int price, Double minDiag, Double maxDiag) {
        obj.showAllResults();

        if (obj.checkPrice(price) && obj.checkScreenDiag(minDiag, maxDiag)) {
            Assertions.assertTrue(true);
        } else {
            CustomUtils.getScreen(obj.getDriver());
            Assertions.fail("Error in search results");
        }

    }

}
