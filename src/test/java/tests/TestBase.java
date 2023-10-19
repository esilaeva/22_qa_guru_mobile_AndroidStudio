package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.EmulationDriverConfig;
import config.browserstackconfig.BrowserstackConfig;
import drivers.BrowserstackDriver;
import drivers.EmulationDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class TestBase {

    static EmulationDriverConfig emulationConfig = ConfigFactory.create(EmulationDriverConfig.class, System.getProperties());
    static BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            Configuration.browser = EmulationDriver.class.getName();
        else if (browserstackConfig.getDeviceHost().equals("browserstack"))
            Configuration.browser = BrowserstackDriver.class.getName();

        Configuration.browserSize = null;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = "";
        if (emulationConfig.getDeviceHost().equals("emulation")) {
            Attach.screenshotAs("Last screenshot");
        } else if (browserstackConfig.getDeviceHost().equals("browserstack")) {
            sessionId = Selenide.sessionId().toString();
        }
        Attach.pageSource();
        closeWebDriver();
        if (browserstackConfig.getDeviceHost().equals("browserstack"))
            Attach.addVideo(sessionId);
    }
}