package config;

import org.aeonbits.owner.Config;

public interface EmulationDriverConfig  extends Config {

    @Key("platformName")
    @DefaultValue("android")
    String getPlatformName();

    @Key("platformVersion")
    @DefaultValue("11.0")
    String getPlatformVersion();

    @Key("device")
    @DefaultValue("Pixel4")
    String getDevice();

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
