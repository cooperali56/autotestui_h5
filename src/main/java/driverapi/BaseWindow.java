package driverapi;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *  windows封装操作类
 */
public class BaseWindow{

    /*
    声明driver接收Browser静态方法实例出来的driver
     */
    protected WebDriver driver;

    public BaseWindow() {
    }

    public BaseWindow(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 打开url地址
     *
     * @param url
     */
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * 获取当前窗口url
     *
     * @return
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * 刷新窗口
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * 获取窗口标题
     *
     * @return title
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * 关闭标签页或窗口
     */
    public void closeWindow() {
        driver.close();
    }

    /**
     * 切换新窗口判断是否关闭旧窗口
     *
     * @param select yesONyes
     */
    public void cutNewWindow(boolean select) {
        Set<String> allHandles = driver.getWindowHandles();
        List<String> listHandles = new ArrayList<>(allHandles);
        if (select) {
            closeWindow();
        }
        driver.switchTo().window(listHandles.get(listHandles.size() - 1));
    }

    /**
     * 切换窗口
     */
    public void returnUpWindow() {
        Set<String> allHandles = driver.getWindowHandles();
        List<String> listHandles = new ArrayList<>(allHandles);
        driver.switchTo().window(listHandles.get(0));
    }

    /**
     * 关闭浏览器
     */
    public void outBrowser() {
        driver.quit();
    }

    /**
     * 进frame
     *
     * @param webElement 元素
     */
    public void goFrame(WebElement webElement) {
        driver.switchTo().frame(webElement);
    }

    /**
     * 退出frame
     */
    public void outFrame() {
        driver.switchTo().defaultContent();
    }

    /**
     * 最大化窗口
     */
    public void maxWindow() {
        driver.manage().window().maximize();
    }

    /**
     * 明确知道浏览器弹窗
     *
     * @param select accept确定/dismiss取消
     */
    public void windowPopUp(boolean select) {
        Alert alert = driver.switchTo().alert();
        //System.out.println("有警告框弹出内容为:" + alert.getText());
        if (select) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    /**
     * 屏幕截图
     */
    public void screenCapture() {
        //相对路径
        File relative = new File("");
        String relativePath = relative.getAbsolutePath() + File.separator + "screen\\";
        // 截屏操作 图片已当前时间命名
        //转换时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd-HH-mm-ss");
        //获取当前时间
        String time = dateFormat.format(Calendar.getInstance().getTime());
        //执行屏幕截取
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(relativePath, time + ".png"));
        } catch (IOException e) {
            System.out.println("···截图失败");
        }
        System.out.println("···截图完成");
    }

    /**
     * 强制等待
     *
     * @param time 毫秒值
     */
    public void awaitCoerce(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐式等待
     */
    public void awaitHide() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
