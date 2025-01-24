package page;

import driverapi.BaseElement;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 页面基类：二次封装driver
 * 继承BaseElement实例出来可操作window和page
 * 页面共同功能
 */
public class BasePage extends BaseElement {

    //所有页面需加载excel定位值
    protected JSONObject jo;

    public BasePage() {
    }

    public BasePage(WebDriver driver, JSONObject jo) {
        super(driver);
        this.jo = jo;
    }

    /*
        元素共性操作-------------------------------
     */

    /**
     * 区分下json过来的by
     *
     * @param byValue
     * @return
     */
    public By getJsonKeyBy(String byValue) {
        byValue = jo.getString(byValue);
        if (byValue.contains("//")) {
            return By.xpath(byValue);
        } else {
            return By.id(byValue);
        }
    }

    /**
     * 元素集合中筛选可用可看元素
     *
     * @param by
     * @return
     */
    public WebElement findElementJs(By by) {
        WebElement element = null;
        List<WebElement> elements = findElements(by);
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).isEnabled() && elements.get(i).isEnabled()) {
                element = elements.get(i);
            }
        }
        return element;
    }

    /**
     * 处理excel传过来json的by定位值
     *
     * @param byKey
     * @return
     */
    public WebElement findElementJs(String byKey) {
        WebElement element = null;
        List<WebElement> elements = findElements(getJsonKeyBy(byKey));
        if (elements != null) {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).isDisplayed()) {
                    element = elements.get(i);
                }
            }
        }
        return element;
    }

    /**
     * 点击元素
     *
     * @param element
     */
    public void elementClick(WebElement element) {
        if (element != null) {
            element.click();
        } else {
            throw new NoSuchElementException("操作元素为空");
        }
    }

    /**
     * 元素输入
     *
     * @param element
     * @param value
     */
    public void elementInput(WebElement element, String value) {
        if (element != null) {
            element.sendKeys(value);
        } else {
            throw new NoSuchElementException("操作元素为空");
        }
    }

    /**
     * 返回元素文本值，元素为null则返回为"";
     *
     * @param element
     * @return
     */
    public String elementText(WebElement element) {
        if (element != null) {
            return element.getText();
        } else {
            return "";
        }
    }

    /**
     * 获取xpath定位值元素集合有效的tag
     *
     * @param value
     * @return
     */
    public WebElement allXpathGetElement(String xpath, String value) {
        List<WebElement> elementList = findElements(By.xpath(xpath));
        if (elementList != null) {
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getText().equals(value) || elementList.get(i).getAttribute("placeholder").contains(value)) {
                    return elementList.get(i);
                }
            }
        } else {
            System.out.println("为空");
        }
        return null;
    }

    /**
     * 正则获取指定范围值
     *
     * @param value   需要处理的值
     * @param regular 正则表达式 (?<=(book_id=)).*?(?=(&))
     * @return 返回的值
     */
    public String regularValue(String value, String regular) {
        Matcher matcher = Pattern.compile(regular).matcher(value);
        String value1 = null;
        if (matcher.find()) {
            value1 = matcher.group();
        }
        return value1;
    }




}
