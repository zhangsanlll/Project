package tests;

import common.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.io.IOException;
import java.time.Duration;

//登录界面自动化测试
public class LoginPage extends Utils {
    public static String url = "http://47.108.82.170:8080/blog_login.html";
    public LoginPage(){
        super(url);
    }
    /*
    检查页面是否加载成功
    * */
    public void loginTestRight(){
        //通过查看页面元素是否存在来检查页面加载成功
        driver.findElement(By.cssSelector("body > div.nav > span"));
        //登录输入框
        driver.findElement(By.cssSelector("body > div.container-login > div"));

    }

    //检查登录功能---成功登录
    public void LoginSuccess() throws IOException {
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();

        driver.findElement(By.cssSelector("#username")).sendKeys("zhangsan");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");

        driver.findElement(By.cssSelector("#submit")).click();
        //点击登录查看是否登录成功
        driver.findElement(By.cssSelector("body > div.container > div.left > div > div:nth-child(4) > span:nth-child(1)"));
        //页面标题来检查是否登录成功
        String expect = driver.getTitle();
        assert expect.equals("博客列表");
        getScreenShot(getClass().getName());
        //若是登录成功则会跳转到另外一个页面，所以需要回退页面
        driver.navigate().back();
        driver.navigate().refresh();
    }

    /*检查登录功能--登录失败
    * */
    public void LoginFail() throws IOException {
        //有两种方法可以确保输入框没有文字，一种使通过clear(),另一种是刷新页面
        //此处采用第二种
        //driver.navigate().refresh();

        driver.findElement(By.cssSelector("#username")).sendKeys("zhangsan");
        driver.findElement(By.cssSelector("#password")).sendKeys("zhangsan");
        //点击登录查看是否登录成功
        driver.findElement(By.cssSelector("#submit")).click();
        getScreenShot(getClass().getName());
        Alert alert = driver.switchTo().alert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        alert.accept();

        String res = driver.findElement(By.cssSelector("body")).getText();
        assert res.equals("用户名或密码错误！");
    }

}
