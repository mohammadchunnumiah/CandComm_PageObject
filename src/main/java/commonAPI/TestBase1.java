package commonAPI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestBase1 {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest test;
    public final Logger log = Logger.getLogger(TestBase1.class.getName());
    public Properties Repository = new Properties();
    public FileInputStream FI;
    public WebDriver driver;

    public void loadProperties() throws Exception {
        Repository =new Properties();
        FileInputStream FI = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
        Repository.load(FI);
    }
    public void initial() throws Exception {
        loadProperties();
        selectBrowser(Repository.getProperty("browser"));
        getUrl(Repository.getProperty("url"));
        setExtentReports();
        String log4jConfigPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfigPath);
    }

    public WebDriver  selectBrowser(String browser) {
        if (browser.equals("chrome")||browser.equals("CHROME")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\drivers\\chromedriver_81.exe");
            System.setProperty("webdriver.chrome.silentOutput","true");//it's prevent to showing unnecessary logs from the browser site in the console
            driver = new ChromeDriver();
            return driver;
        }else if (browser.equals("firefox")||browser.equals("FIREFOX")){
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\drivers\\geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");//stop to showing unnecessary logs in the console
            driver = new FirefoxDriver();
            return driver;
        }
        return null;
    }

    public void setExtentReports(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/extentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");//title of the report
        htmlReporter.config().setReportName("Functional Report");//name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        extentReports.setSystemInfo("Host Name","LocalHost");
        extentReports.setSystemInfo("OS","Windows 10");
        extentReports.setSystemInfo("Tester Name", "Miah");
        extentReports.setSystemInfo("Browser", "Chrome");

    }

    public void endReport(){
        extentReports.flush();
    }


    public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException {
        String dataName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        //after execution you could see a folder under src directory
        String destination = System.getProperty("user.dir")+"/ScreenShotReport/"+ screenShotName + dataName + ".png";
        File fileDestination = new File(destination);
        FileUtils.copyFile(source,fileDestination);
        return destination;
    }

    public void getUrl(String url){
        log.info("Navigating to "+url);
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
    }

    public void log(String data){
        log.info(data);
        Reporter.log(data);
    }

    public void browserClose(){
        driver.quit();
    }

}
