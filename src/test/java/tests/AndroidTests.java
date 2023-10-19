package tests;

import helpers.Steps;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AndroidTests extends TestBase {

    @Test
    @Tag("browserstack")
    void successfulSearchTest() {
        Steps step = new Steps();

        step.stepBack();
        step.typeSearch("Appium");

        step.checkContentFound();
    }

    @Test
    @Tag("browserstack")
    void openArticleTest() {
        Steps step = new Steps();

        step.stepBack();
        step.typeSearch("Selenide");
        step.openArticle("Selenidera");

        step.checkErrorMessage();
    }

    @Test
    void onboardingScteenTest() {
        Steps step = new Steps();

        step.clickButtonContinue();
        step.checkSecondScreenOpened();

        step.clickButtonContinue();
        step.checkThirdScreenOpened();

        step.clickButtonContinue();
        step.checkFourthScreenOpened();

        step.clickFirstPageControl();
        step.checkFirstScreenOpened();
    }
}
