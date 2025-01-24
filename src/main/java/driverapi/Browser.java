package driverapi;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动浏览器实例driver
 */
public class Browser {

    /**
     * 启动H5模式浏览器
     *
     * @return
     */
    public static WebDriver h5Driver() {
        System.out.println("ChromeH5页面启动中···");

//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--disable-infobars");
//        //不加载图片
//        /*
//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("profile.managed_default_content_settings.images", 2);
//        options.setExperimentalOption("prefs", prefs);
//        */
//        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
//        deviceMetrics.put("width", 414);
//        deviceMetrics.put("height", 736);
//        deviceMetrics.put("pixelRatio", 3.0);
//        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
//        mobileEmulation.put("deviceMetrics", deviceMetrics);
//        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) " +
//                "AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//        System.setProperty("webdriver.chrome.driver", "webdriver\\chromedriver.exe");

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        System.setProperty("webdriver.chrome.driver", "webdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
