package page;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 上架页面；H5或IOS目前上架不支持点券通用
 * 详细的后续这里可以按不同类型来区分，并且验证该页功能的异常流
 */
public class PageSellData extends BasePage {

    public PageSellData() {
    }

    public PageSellData(WebDriver driver, JSONObject jo) {
        super(driver, jo);
    }

    private final Logger logger = Logger.getLogger(PageSellData.class);

    /**
     * 这里是通过点击发布按钮触发提示再输入对应的必填元素，后续待做
     */
    public void issueSellData(){
        Random random = new Random();
        do {
            elementClick(findElementJs("发布"));
            if (elementText(findElementJs("提示")).contains("区服信息")){
                //区服
                elementClick(findElementJs("区"));
                awaitCoerce(500);
                try {
                    List<WebElement> qu = findElements(getJsonKeyBy("服"));
                    elementClick(qu.get(random.nextInt(qu.size())));
                } catch (NullPointerException e) {
                }
                try {
                    List<WebElement> qu1 = findElements(getJsonKeyBy("二服"));
                    elementClick(qu1.get(random.nextInt(qu1.size())));
                } catch (NullPointerException e) {
                }
            }
            if (elementText(findElementJs("提示")).contains("正确标题")){

            }
        }while (true);

    }

    /**
     * 填写出售信息
     * 之后需要新增字段的在这里加就好了
     */
    public void sellData() {
        //随机
        Random random = new Random();
        //区服
        elementClick(findElementJs("区"));
        awaitCoerce(500);
        try {
            List<WebElement> qu = findElements(getJsonKeyBy("服"));
            elementClick(qu.get(random.nextInt(qu.size())));
        } catch (NullPointerException e) {
        }
        try {
            List<WebElement> qu1 = findElements(getJsonKeyBy("二服"));
            elementClick(qu1.get(random.nextInt(qu1.size())));
        } catch (NullPointerException e) {
        }
        //等级
        try {
            elementClick(findElementJs("等级"));
            awaitCoerce(500);
            List<WebElement> deng = findElements(getJsonKeyBy("二复选"));
            elementClick(deng.get(random.nextInt(deng.size())));
            List<WebElement> deng1 = findElements(getJsonKeyBy("三复选"));
            elementClick(deng1.get(random.nextInt(deng1.size())));
        } catch (Exception e) {
        }
        //职业
        try {
            elementClick(findElementJs("职业"));
            awaitCoerce(500);
            List<WebElement> zhi = findElements(getJsonKeyBy("二复选"));
            elementClick(zhi.get(random.nextInt(zhi.size())));
            List<WebElement> zhi1 = findElements(getJsonKeyBy("三复选"));
            elementClick(zhi1.get(random.nextInt(zhi1.size())));
        } catch (Exception e) {
        }
        //性别
        try {
            elementClick(findElementJs("性别"));
            awaitCoerce(500);
            List<WebElement> gender = findElements(getJsonKeyBy("二复选"));
            elementClick(gender.get(random.nextInt(gender.size())));
            List<WebElement> gender1 = findElements(getJsonKeyBy("二复复"));
            elementClick(gender1.get(random.nextInt(gender1.size())));
        } catch (Exception e) {
        }
        //所有需要输入的
        List<WebElement> inputElements = findElements(By.xpath("//input | //textarea"));
        for (int i = 0; i < inputElements.size(); i++) {
            if (inputElements.get(i).getAttribute("placeholder").contains("商品标题")) {
                elementInput(inputElements.get(i), "好东西出了来看看啊");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("价格")) {
                elementInput(inputElements.get(i), "55");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("二级密码")) {
                elementInput(inputElements.get(i), "5126");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("暗号")) {
                elementInput(inputElements.get(i), "098123");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("装备名称")) {
                elementInput(inputElements.get(i), "奥利给");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("装备件数")) {
                elementInput(inputElements.get(i), "8");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("角色名称")) {
                elementInput(inputElements.get(i), "帅的一批");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("存放处")) {
                elementInput(inputElements.get(i), "仓库");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("描述")) {
                elementInput(inputElements.get(i), "这个东东买着么点钱不在乎，就是玩儿");
            }
            if (inputElements.get(i).getAttribute("placeholder").contains("数量")) {
                elementInput(inputElements.get(i), "100");
            }
        }
        //保障
        awaitCoerce(1000);
        if (isBy(getJsonKeyBy("保障"))) {
            rollElement(findElementJs("保障"));
            elementClick(findElementJs("保障"));
            elementClick(findElementJs("投保"));
            elementClick(findElementJs(By.xpath("//*[text()='确定']")));
        }
        //图片
        awaitCoerce(1000);
        if (isBy(getJsonKeyBy("传图"))) {
            rollTop();
            File relative = new File("");
            List<String> images = new ArrayList<>();
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\lzg.jpg");
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\lzg1.jpg");
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\chd.jpg");
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\6464.png");
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\man.jpeg");
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\wuwu.jpg");
            images.add(relative.getAbsolutePath() + File.separator + "testImage\\gif666.gif");
            findElement(getJsonKeyBy("传图")).sendKeys(images.get(random.nextInt(images.size())));
            awaitCoerce(2000);
        }

    }

}
