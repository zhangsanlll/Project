package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;

/*
* 创建驱动对象*/
public class Utils {
    public static WebDriver driver;
    public static WebDriver createDriver(){
        if(driver == null){
            //打开edge浏览器驱动
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            //允许访问所有链接
            options.addArguments("--remote-allow-origins=*");

            driver = new EdgeDriver(options);
            //隐式等待
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }
        return  driver;
    }

    //构造函数
    public Utils(String url){
        //调用driver 对象
        driver = createDriver();
        //访问url
        driver.get(url);
    }

    //屏幕截图
    public  void getScreenShot(String str) throws IOException {
        //     ./src/test/image/
        //                     /2024-07-17/
        //                                /test01-17453010.png
        //                                /test02-17453020.png
        //                     /2024-07-18/
        //                                /test01-17453030.png
        //                                /test02-17453034.png
        //屏幕截图
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sim2 = new SimpleDateFormat("HHmmssSS");
        String dirTime = sim1.format(System.currentTimeMillis());
        String fileTime = sim2.format(System.currentTimeMillis());
        //./src/test/image/2024-07-17/test01-17453020.png
        String filename = "./src/test/image/"+dirTime+"/"+str+"-"+fileTime+".png";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //srcFile 放到指定位置
        FileUtils.copyFile(srcFile,new File(filename));
    }

}
