import driverapi.BaseElement;
import driverapi.Browser;
import page.Function;
import page.PageHome;
import page.PageLogin;
import page.PageSellData;
import tools.ExcelUtils;

public class test {

    public static void main(String[] args) {

//        实例窗口
        BaseElement windows = new BaseElement(Browser.h5Driver());

//        隐性等待
        windows.awaitHide();

//        实例测试页面
//        PageLogin pageLogin = new PageLogin(windows.getDriver(), ExcelUtils.getExcelCell("gmmh5test.xlsx", "login", 2, 5));


//        实例测试页面
//        login页面登录操作
//        pageLogin.openUrl("http://www.gmmsj.com/h5/my/index/index.html");
//        String loginResult = pageLogin.phoneLogin("18221949415", "201961");
//        System.out.println(loginResult);

//        pageLogin.accountPwdLogin("55967402","qqqwww");
//        pageLogin.resetPwdGJ("55967403","qqqwww");

        windows.openUrl("http://www.gmmsj.com/h5/selgame/index.html?type=sell");

//        Function function = new Function(windows.getDriver(), ExcelUtils.getExcelCell("gmmh5test.xlsx", "sell", 2, 5));
//        function.sellSelectTxz("冒险岛", "游戏币", "");

//        实例测试页面
//        PageSellData pageSell = new PageSellData(windows.getDriver(), ExcelUtils.getExcelCell("gmmh5test.xlsx", "sell", 2, 5));
//        pageSell.sellData();
//        String bookId = function.goodsIssue();

//        PageHome pageHome = new PageHome(windows.getDriver(), ExcelUtils.getExcelCell("gmmh5test.xlsx", "home", 2, 5));
//        pageHome.goodsList("冒险岛", "游戏币", bookId);


    }

}
