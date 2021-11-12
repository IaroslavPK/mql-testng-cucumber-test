package utils;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


@Slf4j
public class CustomDriverProvider implements WebDriverProvider {

    public final static String VERSION_LATEST = "latest";

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
         return WebDriverManager.chromedriver().capabilities(getChromeDriverOptions(capabilities)).create();
    }

    private ChromeOptions getChromeDriverOptions(DesiredCapabilities capabilities) {
        log.info("---------------Chrome Driver---------------------");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, "0.93");
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }

}