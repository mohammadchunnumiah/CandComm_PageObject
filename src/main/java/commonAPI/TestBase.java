package commonAPI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TestBase {
    //Global variabls
    public final Logger log = Logger.getLogger(TestBase.class.getName());
    public WebDriver dr;
    public EventFiringWebDriver driver;
    public Properties OR;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    // Code to read value from properties file
    public void loadProperties() throws IOException {
        OR = new Properties();
        File file = new File(System.getProperty("user.dir") + "//src//main//java//config//config.properties");
        FileInputStream f = new FileInputStream(file);
        OR.load(f);
    }

    //Initialize values to be called
    public void init() throws IOException {
        loadProperties();
        selectBrowser(OR.getProperty("browser"));
        getUrl(OR.getProperty("url")); //https://cfbappportalqa.azurewebsites.net
        String log4jConfigPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfigPath);
    }

    // Initializing browser
    public void selectBrowser(String browser) {
        if (browser.equals("chrome") || browser.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/drivers/chromedriver_83.exe");
            log.info("Creating the object of " + browser);
            dr = new ChromeDriver();
            driver = new EventFiringWebDriver(dr);
            //eventListener= new WebEventListener();
            //driver.register(eventListener);
        } else if (browser.equals("firefox") || browser.equals("FIREFOX")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/drivers/geckodriver.exe");
            log.info("Creating the object of " + browser);
            dr = new ChromeDriver();
            dr = new ChromeDriver();
            driver = new EventFiringWebDriver(dr);
            //eventListener= new WebEventListener();
            //driver.register(eventListener);
        } else if (browser.equals("ie") || browser.equals("IE")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/");
            log.info("Creating the object of " + browser);
            dr = new ChromeDriver();
            dr = new ChromeDriver();
            driver = new EventFiringWebDriver(dr);
            //eventListener= new WebEventListener();
            //driver.register(eventListener);
        }
    }
    
   /* static{
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        extent = new ExtentReports(System.getProperty("user.dir")+"//src//main//java//extentReport//report_"+formater.format(calendar.getTime())+".html",false);

    }*/


    // Calling url from repository
    public void getUrl(String url) {
        log.info("Navigating to " + url);
        dr.get(url);
        dr.manage().window().maximize();
        dr.manage().deleteAllCookies();
    }

  /*  public String[][] getData(String excelName, String sheetName) {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\excelData\\" + excelName;
        excel = new ExcelReader(path);
        String[][] data = excel.getDataFromSheet(sheetName, excelName);
        return data;
    }*/

    // Synchronization the scripts
    public void waitForElement(int timeOutInSeconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(dr, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Handling windows
    public Iterator<String> getAllWindows() {
        Set<String> window = dr.getWindowHandles();
        Iterator<String> itr = window.iterator();
        return itr;
    }

    // To create log file
    public void log(String data) {
        log.info(data);
        Reporter.log(data);
    }

    // Generate extendReport only pass/fail without screenshot and log
    @BeforeTest
    public void setExtentReports() {
//        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/reports/AutomationReport.html");
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/reports/AutomationReport.html");

        htmlReporter.config().setDocumentTitle("Automation Report"); //title of the report
        htmlReporter.config().setReportName("Functional Report"); //name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        // to create html Report
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        // Environmental information
        extentReports.setSystemInfo("Host Name", "LocalHost");
        extentReports.setSystemInfo("OS", "WINDOWS 10");
        extentReports.setSystemInfo("Tester Name", "Miah");
        extentReports.setSystemInfo("Browser", "Chrome");

    }
    
    @AfterTest
    public void endReport() {
        extentReports.flush();
    }

    //In this test i will verify every method will pass, fail or skip and based on the status i will update the result into the report
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test is failed" + result.getName()); //to add name in the extent report
            test.log(Status.FAIL, "Test is failed" + result.getThrowable()); //to add error/exception to the report

            //if the status is failed, we need to take screenshot
            String screenShot = TestBase.getScreenShot(driver, result.getName());
            test.addScreenCaptureFromPath(screenShot); //adding screen shot

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test is skipped" + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test is passed" + result.getName());
        }
        driver.quit();
    }

    // to get screenshot on the failed test
    public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException {
        String dataName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        //after execution you could see a folder under src directory
        String destination = System.getProperty("user.dir") + "/ScreenShotReport/" + screenShotName + dataName + ".png";
        File fileDestination = new File(destination);
        FileUtils.copyFile(source, fileDestination);
        return destination;
    }
}
