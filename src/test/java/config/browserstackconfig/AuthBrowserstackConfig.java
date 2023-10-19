package config.browserstackconfig;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthBrowserstackConfig extends Config {

    @Key("userName")
    @DefaultValue("ilananull_TMCs07")
    String getUserName();

    @Key("accessKey")
    @DefaultValue("y3sGJ2SGa4wB5615Xycy")
    String getAccessKey();

    @Key("remoteUrl")
    @DefaultValue("https://hub.browserstack.com/wd/hub")
    String getRemoteUrl();
}
