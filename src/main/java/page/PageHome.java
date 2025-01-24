package page;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * 首页
 */
public class PageHome extends BasePage {

    public PageHome() {
    }

    public PageHome(WebDriver driver, JSONObject jo) {
        super(driver, jo);
    }

    private final Logger logger = Logger.getLogger(PageHome.class);

    /**
     * 从首页到选择到商品列表再到指定商品详情
     *
     * @param gameName
     * @param gameType
     * @param bookId
     */
    public void goodsList(String gameName, String gameType, String bookId) {
        //H5首页选择类型
        elementClick(findElement(By.xpath("//*[text()='" + gameType + "']")));
        //始终打开初始化页面
        openUrl("http://www.gmmsj.com/h5/selgame/index.html");
        //输入游戏名
        elementInput(allXpathGetElement("//input", "请输入"), gameName);
        //确认
        elementClick(findElement(By.xpath("//*[text()='" + gameName + "']")));
        awaitCoerce(2000);
        //有bookId情况下
        if (bookId != "") {
            elementClick(findElement(By.xpath("//*[@book_id='" + bookId + "']")));
        } else {
            //随机进入商品详情
            List<WebElement> goods = findElements(getJsonKeyBy("商品列表"));
            Random random = new Random();
            elementClick(goods.get(random.nextInt(goods.size())));
        }
        awaitCoerce(2000);
    }

}
