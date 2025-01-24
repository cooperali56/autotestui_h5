package driverapi;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * element封装操作类
 * 继承BaseWindow
 */
public class BaseElement extends BaseWindow {

    public BaseElement() {
    }

    public BaseElement(WebDriver driver) {
        super(driver);
    }

    /**
     * 判断by定位值是否存在
     *
     * @param by 定位值
     * @return
     */
    public boolean isBy(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * by返回页面元素
     *
     * @param by
     * @return
     */
    public WebElement findElement(By by) {
        if (isBy(by)) {
            return driver.findElement(by);
        } else {
            return null;
        }
    }

    /**
     * 返回元素集合
     *
     * @param by
     * @return
     */
    public List<WebElement> findElements(By by) {
        if (isBy(by)) {
            return driver.findElements(by);
        } else {
            return null;
        }
    }

    /**
     * 元素截图
     *
     * @param element 元素值
     */
    public void screenElement(WebElement element) {
        //相对路径
        File relative = new File("");
        String relativePath = relative.getAbsolutePath() + File.separator + "screen\\";
        //转换时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd-HH-mm-ss");
        //获取当前时间
        String time = dateFormat.format(Calendar.getInstance().getTime());
        File scrFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(relativePath, time + ".png"));
            //FileUtils.copyFile(scrFile, new File(relativePath, "./image.png"));
        } catch (IOException e) {
            System.out.println("···元素失败");
        }
        System.out.println("···元素完成");
    }

    /**
     * 显示等待
     *
     * @param by   定位值
     * @param time 分钟
     */
    public void awaitShow(By by, int time) {
        /*
        页面元素是否在页面上可用和可被单击	    elementToBeClickable(By locator)
        页面元素处于被选中状态	            elementToBeSelected(WebElement element)
        页面元素在页面中存在	            presenceOfElementLocated(By locator)
        在页面元素中是否包含特定的文本	    textToBePresentInElement(By locator)
        页面元素值　	                    textToBePresentInElementValue(Bylocator locator, String text)
        标题	                            titleContains(String title)
         */
        try {
//            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            System.out.println("···此by定位值等待超时:" + by);
        }

    }

    /**
     * 右移元素
     *
     * @param element
     */
    public void elementSlide(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).clickAndHold(element);
        actions.dragAndDropBy(element, 200, 0).perform();
        actions.release();
    }

    /**
     * 滚动条滚动至对应元素
     *
     * @param element
     */
    public void rollElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void rollTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //向上拉到顶
        js.executeScript("window.scrollTo(0,1)");
    }

}
