package ru.zavrichko;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.zavrichko.helpers.AllureAttachments;

public class TestBase {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "http://pizzeria.skillbox.cc/";
        Configuration.startMaximized = true;
        Configuration.remote = System.getProperty("url");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("version");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;


    }

    @AfterEach
    public void addAttachments() {
        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();
        Selenide.closeWebDriver();

    }
}
