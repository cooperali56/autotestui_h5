package page;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

/**
 * 登录页面
 * 页面下的相关操作；国内手机号登录、非大陆登录、注册、重置密码、注销等。
 */
public class PageLogin extends BasePage {

    public PageLogin() {
    }

    public PageLogin(WebDriver driver, JSONObject jo) {
        super(driver, jo);
    }

    /**
     * 手机登陆
     *
     * @param phoneNumber
     * @param noteCode
     * @return
     */
    public String phoneLogin(String phoneNumber, String noteCode) {
        goFrame(findElementJs("lgIframe"));
        elementInput(findElementJs("账号"), phoneNumber);
        try {
            elementClick(findElementJs("get短信"));
            awaitCoerce(1000);
            elementSlide(findElementJs("滑动验证"));
        } catch (Exception e) {
        }
        elementInput(findElementJs("短信or密码"), noteCode);
        elementClick(findElementJs("登录"));
        awaitCoerce(2000);
        String hint = elementText(findElementJs("提示"));
        outFrame();
        return hint;
    }

    /**
     * 账号密码登录
     *
     * @param account
     * @param pwd
     * @return
     */
    public String accountPwdLogin(String account, String pwd) {
        goFrame(findElementJs("lgIframe"));
        elementClick(findElementJs("非大陆"));
        elementClick(findElementJs("xg"));
        elementInput(findElementJs("账号"), account);
        elementInput(findElementJs("短信or密码"), pwd);
        elementClick(findElementJs("登录"));
        try {
            elementSlide(findElementJs("滑动验证"));
        } catch (Exception e) {
        }
        String hint = elementText(findElementJs("提示"));
        outFrame();
        return hint;
    }

    /**
     * 手机重置密码
     *
     * @param acc
     * @param pwd
     * @return
     */
    public String resetPwd(String acc, String pwd) {
        goFrame(findElementJs("lgIframe"));
        elementClick(findElementJs("重置"));
        elementInput(findElementJs("账号"), acc);
        elementClick(findElementJs("下一步"));
        String hint = elementText(findElementJs("提示"));
        try {
            elementClick(findElementJs("get短信"));
            elementSlide(findElementJs("滑动验证"));
        } catch (Exception e) {
        }
        elementInput(findElementJs("短信码"), "201961");
        elementInput(findElementJs("短信or密码"), pwd);
        elementClick(findElementJs("重置登录"));
        hint = elementText(findElementJs("提示"));
        return hint;
    }


    /**
     * 账号密码重置
     * @param acc
     * @param pwd
     * @return
     */
    public String resetPwdGJ(String acc, String pwd) {
        goFrame(findElementJs("lgIframe"));
        elementClick(findElementJs("重置"));
        elementClick(findElementJs("非大陆"));
        elementClick(findElementJs("xg"));
        elementInput(findElementJs("账号"), acc);
        elementClick(findElementJs("下一步"));
        try {
            elementClick(findElementJs("get短信"));
            elementSlide(findElementJs("滑动验证"));
        } catch (Exception e) {
        }
        String hint = elementText(findElementJs("提示"));
        elementInput(findElementJs("短信码"), "201961");
        elementInput(findElementJs("短信or密码"), pwd);
        hint = elementText(findElementJs("提示"));
        return hint;
    }

}
