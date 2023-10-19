package helpers;

import config.EmulationDriverConfig;
import config.browserstackconfig.BrowserstackConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static org.openqa.selenium.By.xpath;

public class Steps {

    static EmulationDriverConfig emulationConfig = ConfigFactory.create(EmulationDriverConfig.class, System.getProperties());
    static BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @Step("Step back")
    public void stepBack() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            back();
    }

    @Step("Type search")
    public void typeSearch(String value) {
        $(accessibilityId("Search Wikipedia")).click();
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(value);
    }

    @Step("Verify content found")
    public void checkContentFound() {
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .shouldHave(sizeGreaterThan(0));
    }

    @Step("Open article")
    public void openArticle(String value) {
        $(xpath("//*[@text='" + value + "']")).click();
    }

    @Step("Checking for error message")
    public void checkErrorMessage() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/view_page_header_image")).shouldBe(visible);
        else
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible);
    }

    @Step("Click on the button 'Continue'")
    public void clickButtonContinue() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    }

    @Step("Verify: second screen was opened")
    public void checkSecondScreenOpened() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
    }

    @Step("Verify: third screen was opened")
    public void checkThirdScreenOpened() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
    }

    @Step("Verify: fourth screen was opened")
    public void checkFourthScreenOpened() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
    }

    @Step("Verify: first screen was opened")
    public void checkFirstScreenOpened() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia"));
    }

    @Step("Click on First page control")
    public void clickFirstPageControl() {
        if (emulationConfig.getDeviceHost().equals("emulation"))
            $(id("org.wikipedia.alpha:id/view_onboarding_page_indicator"))
                    .$(className("android.widget.LinearLayout")).click();
    }
}
