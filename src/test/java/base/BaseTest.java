package base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BaseTest {
    protected static WebDriver driver;
    protected static Actions actions;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;

    @BeforeScenario
    public void setup(){
        driver = new ChromeDriver(chromeOptions());
        actions = new Actions(driver);
    }


    public ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-fullscreen");
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }

    @AfterScenario
    public void tearDown() {
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    ConcurrentMap<String, Object> elementMapList = new ConcurrentHashMap<>();
    public void saveValue(String key, String value) {
        elementMapList.put(key, value);
    }
    public String getValue(String key) {
        return elementMapList.get(key).toString();
    }
}
