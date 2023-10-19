package config.browserstackconfig;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${deviceHost}.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("platformName")
    @DefaultValue("android")
    String getPlatformName();

    @Key("device")
    @DefaultValue("Samsung Galaxy S22 Ultra")
    String getDevice();

    @Key("os_version")
    @DefaultValue("")
    String getVersion();

    @Key("app")
    @DefaultValue("bs://sample.app")
    String getApp();

    @Key("deviceHost")
    @DefaultValue("emulation")
    String getDeviceHost();

    @Key("projectName")
    @DefaultValue("First Java Project")
    String getProject();

    @Key("build")
    @DefaultValue("browserstack-build-2")
    String getBuild();

    @Key("testName")
    @DefaultValue("android_test")
    String getTestName();
}
