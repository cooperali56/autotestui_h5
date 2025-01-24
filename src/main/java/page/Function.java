package page;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.*;

import java.util.List;

/**
 * 一些小功能实现;页面之间转换
 */
public class Function extends BasePage {

    public Function() {
    }

    public Function(WebDriver driver, JSONObject jo) {
        super(driver, jo);
    }

    private final Logger logger = Logger.getLogger(Function.class);

    /**
     * 短信
     */
    public void sms() {
        if (allXpathGetElement("//h1", "短信验证").isDisplayed()) {
            elementClick(allXpathGetElement("//button[text()='发送验证码']", "发送验证码"));
            elementInput(allXpathGetElement("//input", "请输入验证码"), "201961");
            elementClick(allXpathGetElement("//button[text()='确定']", "确定"));
            awaitCoerce(1000);
        }
    }

    /**
     * 出售前操作选择游戏类型和通行证
     *
     * @param gameName
     * @param gameType
     * @param txz
     */
    public void sellSelectTxz(String gameName, String gameType, String txz) {
        elementInput(allXpathGetElement("//input", "请输入游戏名"), gameName);
        //检查输入内容是否有误
        if (findElementJs("确认游戏") == null) {
            throw new NoSuchElementException("你输入的什么鬼gmm查不到啊" + gameName);
        } else {
            elementClick(findElementJs("确认游戏"));
        }
        if (findElement(By.xpath("//*[text()='" + gameType + "']")) == null) {
            throw new NoSuchElementException(gameName + "这个类型在" + gameName + "里面没有");
        } else {
            elementClick(findElement(By.xpath("//*[text()='" + gameType + "']")));
        }
        //提示
        elementClick(findElementJs("继续上架"));
        awaitCoerce(2000);
        //短信验证
        sms();
        //通信证选择
        //有明确通信证
        if (txz != "") {
            elementClick(findElement(By.xpath("//*[text()='" + txz + "']")));
            elementClick(findElementJs("确定"));
            if (isBy(getJsonKeyBy("hint"))) {
                System.out.println(findElementJs("hint").getText() + txz);
            }
            //未明确通信证
        } else {
            List<WebElement> txzAcc = findElements(getJsonKeyBy("accountlist"));
            for (int i = 0; i < txzAcc.size(); i++) {
                elementClick(txzAcc.get(i));
                elementClick(findElementJs("确定"));
                //检查通信证账号是否能上架
                if (isBy(getJsonKeyBy("hint"))) {
                    System.out.println(findElementJs("hint").getText() + txzAcc.get(i).getText());
                    awaitCoerce(3000);
                    continue;
                } else {
                    break;
                }
            }
        }
        //处理角色
        String title = elementText(findElement(By.xpath("//h1")));
        if (title.contains("角色")) {
            List<WebElement> rolesList = findElements(getJsonKeyBy("roleslist"));
            if (rolesList != null) {
                for (int i = 0; i < rolesList.size(); i++) {
                    logger.info("出售角色名称；" + elementText(rolesList.get(i)));
                    elementClick(rolesList.get(i));
                    if (findElementJs("确认角色").isEnabled()) {
                        elementClick(findElementJs("确认角色"));
                        break;
                    }
                }
            } else {
                if (findElementJs("确认角色").isEnabled()) {
                    logger.info(findElement(By.id("dnNoRole")).getText() + "但能上架");
                    elementClick(findElementJs("确认角色"));
                } else {
                    throw new ElementClickInterceptedException("上架该通信证需要获取角色详细数据");
                }
            }
        }
    }

    /**
     * 发布并返回发布结果
     * @return
     */
    public String goodsIssue() {
        //点击发布
        elementClick(findElementJs("发布"));
        if (isBy(getJsonKeyBy("确认发布"))) {
            List<WebElement> elements = findElements(getJsonKeyBy("确认发布"));
            for (int i = 0; i < elements.size(); i++) {
                System.out.println(elements.get(i).getText());
            }
        }
        //确认发布
        elementClick(findElementJs(By.xpath("//*[text()='确定']")));
        //有可能出现风控的处理
        awaitCoerce(2000);
        logger.info(elementText(findElement(By.xpath("//h3"))));
        return regularValue(getUrl(),"(?<=(book_id=)).*?(?=(&))");
    }


    public void buySelectGoods(String gameName, String gameType) {

    }

}
