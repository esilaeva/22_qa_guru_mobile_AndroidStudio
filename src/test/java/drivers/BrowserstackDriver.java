package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.browserstackconfig.AuthBrowserstackConfig;
import config.browserstackconfig.BrowserstackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class BrowserstackDriver implements WebDriverProvider {

    static BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    static AuthBrowserstackConfig authConfig = ConfigFactory.create(AuthBrowserstackConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();

        mutableCapabilities.setCapability("browserstack.user", authConfig.getUserName());
        mutableCapabilities.setCapability("browserstack.key", authConfig.getAccessKey());

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
//
        options
//                .setAutomationName(ANDROID_UIAUTOMATOR2)
//                .setPlatformName(ANDROID)
//                .setPlatformVersion("12.0")
//                .setDeviceName("Samsung Galaxy S22 Ultra")
//                .setApp("bs://sample.app")
//                .setAppPackage("org.wikipedia.alpha")
//                .setAppActivity("org.wikipedia.main.MainActivity");

                .setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(config.getPlatformName())
                .setPlatformVersion(config.getPlatformVersion())
                .setDeviceName(config.getDevice())
                .setApp(config.getApp())
                .setAppPackage(config.getAppPackage())
                .setAppActivity(config.getAppActivity());
//
//        return new AndroidDriver(getAppiumServerUrl(), options);

        try {
            return new RemoteWebDriver(
//                    new URL(authConfig.getRemoteUrl()), mutableCapabilities);
                    new URL("https://hub.browserstack.com/wd/hub"), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

//    private String getAppPath() {
//        String appVersion = config.getAppVersion();
//
//        String appUrl = config.getAppUrl() + appVersion;
//        String appPath = config.getAppPath() + appVersion;
//
//        File app = new File(appPath);
//        if (!app.exists()) {
//            try (InputStream in = new URL(appUrl).openStream()) {
//                copyInputStreamToFile(in, app);
//            } catch (IOException e) {
//                throw new AssertionError("Failed to download application", e);
//            }
//        }
//        return app.getAbsolutePath();
//    }
}
