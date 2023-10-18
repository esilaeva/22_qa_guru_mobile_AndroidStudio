package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.xpath;

public class AndroidTests extends TestBase {

    @Test
    void successfulSearchTest() {
        back();
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void openArticleTest() {
        back();
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenide");
        });

        step("Open article", () -> {
            $(xpath("//*[@text='Selenidera']")).click();
        });

        step("Checking for error text", () -> {
            $(id("org.wikipedia.alpha:id/view_page_header_image")).shouldBe(visible);
        });
    }

    @Test
    void onboardingScteenTest() {
        step("Click on the button 'Continue'", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Verify that second screen was opened", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
        });

        step("Click on the button 'Continue'", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Verify that third screen was opened", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        });

        step("Click on the button 'Continue'", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Verify that fourth screen was opened", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
        });

        step("Click on the button 'Continue'", () -> {
            $(id("org.wikipedia.alpha:id/view_onboarding_page_indicator"))
                    .$(className("android.widget.LinearLayout"))
                    .click();
        });
        step("Verify that first screen was opened", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
    }
}
