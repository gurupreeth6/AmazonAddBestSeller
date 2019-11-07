package testCases;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseUrl= readConfig.getApplicationURL();
    public String title = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
    public WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br) throws ClassNotFoundException, MalformedURLException {


        if(br.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
            driver = new ChromeDriver();
        }
        if(br.equals("Firefox")){
            System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
            driver=new FirefoxDriver();
        }


        logger = (Logger) LogManager.getLogger(this.getClass().getName());
        driver.get(baseUrl);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreenshot(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
        FileUtils.copyFile(source,target);
        logger.info("Screenshot taken");
    }
}
