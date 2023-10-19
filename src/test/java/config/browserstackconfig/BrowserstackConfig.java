package config.browserstackconfig;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${deviceHost}.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("platformName")
    @DefaultValue("android")
    String getPlatformName();

    @Key("platformVersion")
    @DefaultValue("11.0")
    String getPlatformVersion();

    @Key("device")
    @DefaultValue("Pixel4")
    String getDevice();

    @Key("os_version")
    @DefaultValue("")
    String getOsVersion();

    @Key("app")
    @DefaultValue("")
    String getApp();

    @Key("appVersion")
    @DefaultValue("app-alpha-universal-release.apk")
    String getAppVersion();

    @Key("appUrl")
    @DefaultValue("https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/")
    String getAppUrl();

    @Key("appPath")
    @DefaultValue("src/test/resources/apps/")
    String getAppPath();

    @Key("appPackage")
    @DefaultValue("org.wikipedia.alpha")
    String getAppPackage();

    @Key("appActivity")
    @DefaultValue("org.wikipedia.main.MainActivity")
    String getAppActivity();

    @Key("deviceHost")
    @DefaultValue("emulation")
    String getDeviceHost();
}
