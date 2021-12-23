package ru.bitelecom.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSettings {

    WebDriver chromeDriver;
    WebDriverWait wait;

    @BeforeEach
    public void installSettings() {
        //System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
//        WebDriverManager.chromedriver().setup(); // Если работаем с библиотекой WebDriverManager, которая загружает веб драйвер
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 10);

        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeBrowser() {
        chromeDriver.quit(); //закрывает браузер
        chromeDriver.close(); //закрывает chromeDriver
    }

    //    @BeforeEach
    private void startTestInDocker() {
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setVersion("80.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        try {
            chromeDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    }

    //    @AfterEach
    private void endTestInDocker() {
        if (chromeDriver != null) {
            chromeDriver.quit();
            chromeDriver = null;
        }
    }


}
