package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
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

    static BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        if (config.getDeviceHost().equals("emulation"))
            Configuration.browser = EmulationDriver.class.getName();
        else if (config.getDeviceHost().equals("browserstack"))
            Configuration.browser = BrowserstackDriver.class.getName();
//        else if(config.getDeviceHost().equals("android"))
//            Configuration.browser = LocalDriver.class.getName();
//        else if(config.getDeviceHost().equals("ios"))
//            Configuration.browser = BrowserstackDriver.class.getName();
//        Configuration.browserSize = null;

        Configuration.browserSize = null;
//         Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();

    }
}