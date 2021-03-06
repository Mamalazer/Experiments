package ru.bitelecom.selenide.internet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;
import static ru.bitelecom.selenide.internet.Urls.*;

public class Tests {

    @BeforeEach
    public void option() {
        Configuration.timeout = 10000; // установка задержки в милисекундах
        Configuration.browser = "chrome";
//        Configuration.browser = "firefox";
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true; //оптимизация мeтода setValue(), если перестанет работать, то можно
                                           // использовать sendKeys()
//        Configuration.headless = false; // браузер не будет отображаться при запуске тестов

        // Работает только в версии 5.5.0, не выше
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions"); // запускает браузер без расширений

        // Добавляем созданные настройки в браузер
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;

        // Используется для настроек в более поздних версиях selenide
//        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
//        WebDriver driver;
//        driver = new ChromeDriver(chromeOptions);
//        setWebDriver(driver);
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Add/Remove Elements")
    public void addRemoveElements() {
        open(addRemoveElements.url);
        $(By.xpath("//button[text()='Add Element']")).click();
        $(By.xpath("//button[text()='Delete']")).shouldBe(Condition.visible).click();
        boolean check = $(By.xpath("//button[text()='Delete']")).isDisplayed();
        Assertions.assertFalse(check, "Элемент не был удалён со страницы");
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Basic Auth")
    public void basicAuth() {
        open(basicAuth.url);
        $x("//p[contains(text(), 'Congratulations!')]")
                .shouldHave(Condition.text("Congratulations! You must have the proper credentials."));
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Checkboxes")
    public void checkBoxes() {

        SelenideElement firstCheckBox = $x("//br/preceding-sibling::input");
        SelenideElement secondCheckBox = $x("//br/following-sibling::input");

        open(checkBoxes.url);
        if (!firstCheckBox.is(Condition.selected)) {
            firstCheckBox.click();
            firstCheckBox.shouldBe(Condition.selected);
        }

        if (secondCheckBox.is(Condition.selected)) {
            secondCheckBox.click();
            secondCheckBox.shouldNotBe(Condition.selected);
        }

    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Context Menu")
    public void contextMenu() {
        open(contextMenu.url);
        $x("//div[@id='hot-spot']").contextClick();
        String text = switchTo().alert().getText();
        Assertions.assertEquals("You selected a context menu", text, "Проблема с алертом");
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Disappearing Elements")
    public void disappearingElements() {

        SelenideElement galleryButton = $x("//a[text()='Gallery']");
        SelenideElement aboutButton = $x("//a[text()='About']");
        open(disappearingElements.url);

        if (galleryButton.is(Condition.visible)) {
            galleryButton.click();
            $x("//h1[text()='Not Found']").shouldBe(Condition.visible);
        } else {
            aboutButton.click();
            $x("//h1[text()='Not Found']").shouldBe(Condition.visible);
        }
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Drag and Drop")
    public void dragAndDrop() {

        open(dragAndDrop.url);
        SelenideElement a = $x("//p[text()='Drag me to my target']/parent::div");
        SelenideElement b = $x("//p[text()='Drop here']/parent::div");
        SelenideElement check = $x("//div[@id='droppable']/p");

        actions().moveToElement(a).build().perform();
        actions().clickAndHold().moveToElement(b).release().build().perform();
        Assertions.assertEquals("Dropped!", check.getText(), "Элемент не перенесён");
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Dropdown List")
    public void dropdownList() {
        open(dropdownList.url);
        SelenideElement dropDown = $x("//select[@id='dropdown']");

        dropDown.selectOption(1);
        Assertions.assertEquals("Option 1", dropDown.getSelectedText());

        dropDown.selectOption("Option 2");
        Assertions.assertEquals("2", dropDown.getSelectedValue());

        dropDown.selectOptionByValue("1");
        Assertions.assertEquals("Option 1", dropDown.getSelectedText());
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Dynamic Controls")
    public void dynamicControls() {
        open(dynamicControls.url);
        SelenideElement checkBox = $x("//input[@type='checkbox']");
        SelenideElement removeButton = $x("//button[text()='Remove']");
        SelenideElement addButton = $x("//button[text()='Add']");
        SelenideElement textField = $x("//input[@type='text']");
        SelenideElement enableButton = $x("//button[text()='Enable']");
        SelenideElement disableButton = $x("//button[text()='Disable']");

        checkBox.shouldNotBe(Condition.selected);
        removeButton.click();
        checkBox.shouldNotBe(Condition.visible);
        addButton.click();
        checkBox.shouldBe(Condition.visible);
        checkBox.setSelected(true);
        Assertions.assertTrue(checkBox.is(Condition.selected), "Чек бокс не активирован");

        textField.shouldBe(Condition.disabled);
        enableButton.click();
        textField.shouldBe(Condition.enabled).setValue("Hello, dude");
        disableButton.click();
        textField.shouldBe(Condition.disabled);
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Entry Ad(modal window)")
    public void entryAd() {
        open(entryAd.url);
        $x("//p[text()='Close']").shouldBe(Condition.visible).click();
        $x("//a[@id='restart-ad']").shouldBe(Condition.visible).click();
        $x("//p[text()='Close']").shouldBe(Condition.visible).click();
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Download file")
    public void downloadFile() throws FileNotFoundException {
        open(downloadFile.url);
        $x("//a[contains(text(), 'test')]").download();
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Upload file")
    public void uploadFile() {
        open(uploadFile.url);
        File file = new File("C:\\Users\\drkuznetsov\\IdeaProjects\\bi_telecom\\Test.txt");
        $x("//input[@id='file-upload']").uploadFile(file);
        $x("//input[@type='submit']").click();
        $x("//h3[text()='File Uploaded!']").shouldBe(Condition.visible);
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Login Page positive")
    public void formAuthenticationPos() {

        open(formAuthentication.url);
        $x("//input[@name='username']").setValue("tomsmith");
        $x("//input[@name='password']").setValue("SuperSecretPassword!");
        $x("//button[@type='submit']").click();
        $x("//a[@class='button secondary radius']").shouldBe(Condition.visible).click();
        $x("//h2[text()='Login Page']").shouldHave(Condition.text("Login Page"));
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Login Page negative")
    public void formAuthenticationNeg() {

        open(formAuthentication.url);
        $x("//input[@name='username']").setValue("tomsmith");
        $x("//input[@name='password']").setValue("tomsmith");
        $x("//button[@type='submit']").click();
        String error = $x("//div[@id='flash']").getText();
        Assertions.assertTrue(error.contains("Your password is invalid!"),
                "Ошибка при авторизации не отображается");
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Nested Frames")
    public void nestedFrames() {
        open(frames.url);
        $x("//a[text()='Nested Frames']").click();
        switchTo().frame("frame-top");

        String leftFrame = switchTo().frame("frame-left").findElement(By.xpath("//body")).getText();
        Assertions.assertEquals(leftFrame, "LEFT", "Ошибка при переключении на левый frame");

        switchTo().parentFrame();

        String middleFrame = switchTo().frame("frame-middle").findElement(By.xpath("//body")).getText();
        Assertions.assertEquals(middleFrame, "MIDDLE", "Ошибка при переключении на средний frame");

        switchTo().parentFrame();

        String rightFrame = switchTo().frame("frame-right").findElement(By.xpath("//body")).getText();
        Assertions.assertEquals(rightFrame, "RIGHT", "Ошибка при переключении на правый frame");

        switchTo().window(0);

        String bottomFrame = switchTo().frame("frame-bottom").findElement(By.xpath("//body")).getText();
        Assertions.assertEquals(bottomFrame, "BOTTOM", "Ошибка при переключении на bottom frame");

    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("iFrame")
    public void iFrame() {
        open(frames.url);
        SelenideElement textField = $x("//body[@id='tinymce']/p");
        $x("//a[text()='iFrame']").click();
        switchTo().frame("mce_0_ifr");
        textField.clear();
        textField.sendKeys("Hello!");
        Assertions.assertEquals(textField.getText(), "Hello!", "Текст не отображается в текстовом поле");

        switchTo().window(0);

        $x("//button[@aria-label='Align center']").click();

    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Horizontal Slider")
    public void horizontalSlider() {
        open(horizontalSlider.url);
        SelenideElement slider = $x("//input[@type='range']");
        SelenideElement sliderRange = $x("//span[@id='range']");

        slider.setValue("2.5");
        Assertions.assertEquals(slider.attr("value"), sliderRange.getText(),
                "Слайдер не установлен в положение 2.5");

        actions().moveToElement(slider).build().perform();
        actions().clickAndHold().moveToElement(sliderRange).release().build().perform();
        Assertions.assertEquals(slider.attr("value"), sliderRange.getText(),
                "Слайдер не установлен в положение 5.0");

        slider.sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT);
        Assertions.assertEquals(slider.attr("value"), sliderRange.getText(),
                "Слайдер не установлен в положение 4.0");

    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Hovers")
    public void hovers() {
        open(hovers.url);
        $x("//h5[contains(text(), 'user1')]/ancestor::div[@class='figure']").hover();
        $x("//h5[contains(text(), 'user1')]").shouldBe(Condition.visible);
        $x("//a[text()='View profile']").shouldBe(Condition.visible).click();
        $x("//h1[text()='Not Found']").shouldBe(Condition.visible);
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Inputs")
    public void inputNumbers() {
        open(inputNumbers.url);
        SelenideElement field = $x("//input[@type='number']");
        field.setValue("99999999");
        field.sendKeys(Keys.ARROW_UP);
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("JQueryUI - Menu")
    public void jQuery() throws FileNotFoundException {
        open(jQuery.url);
        SelenideElement enabledButton = $x("//a[@id='ui-id-2']");
        SelenideElement downloadButton = $x("//a[@id='ui-id-4']");
        SelenideElement pdfLink = $x("//a[@id='ui-id-6']");

        enabledButton.shouldBe(Condition.visible);
        actions().moveToElement(enabledButton).build().perform();
        downloadButton.shouldBe(Condition.visible);
        actions().moveToElement(downloadButton).build().perform();
        pdfLink.shouldBe(Condition.visible).download();

    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("JavaScript Alerts")
    public void jsAlert() {
        open(jsAlert.url);
        SelenideElement jsAlert = $x("//button[text()='Click for JS Alert']");
        SelenideElement jsConfirm = $x("//button[text()='Click for JS Confirm']");
        SelenideElement jsPrompt = $x("//button[text()='Click for JS Prompt']");
        SelenideElement resultText = $x("//p[@id='result']");

        jsAlert.click();
        switchTo().alert().accept();
        Assertions.assertEquals(resultText.getText(), "You successfully clicked an alert",
                "jsAlert не был принят");

        jsConfirm.click();
        switchTo().alert().accept();
        Assertions.assertEquals(resultText.getText(), "You clicked: Ok",
                "jsConfirm не был принят");

        jsConfirm.click();
        switchTo().alert().dismiss();
        Assertions.assertEquals(resultText.getText(), "You clicked: Cancel",
                "jsConfirm не был отклонён");

        jsPrompt.click();
        Alert alert = switchTo().alert();
        alert.sendKeys("Hello, I'm alert");
        alert.accept();
        Assertions.assertEquals(resultText.getText(), "You entered: Hello, I'm alert",
                "Текст не был передан в jsPrompt");

        jsPrompt.click();
        alert = switchTo().alert();
        alert.dismiss();
        Assertions.assertEquals(resultText.getText(), "You entered: null",
                "jsPrompt не был отклонён");
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Opening a new window")
    public void multipleWindows() {
        open(multipleWindows.url);
        $x("//a[text()='Click Here']").click();
        switchTo().window(1);
        Assertions.assertEquals("New Window", $x("//h3[text()='New Window']").getText(),
                "Переход к новой вкладке не осуществлён");
    }

    @Test
    @Owner("DRKuznetsov")
    @Tag("Internet")
    @DisplayName("Notification Message")
    public void notificationMessage() {
        open(notificationMessage.url);
        SelenideElement button = $x("//a[text()='Click here']");
        SelenideElement infoStatus = $x("//div[@id='flash']");

        button.click();
        if (infoStatus.shouldBe(Condition.visible).getText().equals("Action successful\n×")) {
            Assertions.assertTrue(true);
        } else if (infoStatus.shouldBe(Condition.visible).getText().equals("Action unsuccesful, please try again\n×")) {
            Assertions.assertTrue(true);
        } else {
            Assertions.fail("Статус не отображается");
        }

    }

}
