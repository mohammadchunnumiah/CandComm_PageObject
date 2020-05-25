package testExecution;

import commonAPI.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageLibrary.CandidatePage;
import pageLibrary.LoginPage;

import java.util.concurrent.TimeUnit;

public class Verify_CandidatePage extends TestBase {
    LoginPage lp;
    CandidatePage cp;
    @BeforeMethod
    public void setUp() throws Exception {
        init();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    @Test
    public void candidateLogin() throws InterruptedException {
        test = extentReports.createTest("Successfully logged in");
        lp = new LoginPage(driver);
        log.info("Starting verifying Login functionalities");
        test = extentReports.createTest("Successfully logged in");

        // Credentials
//      loginPage.CFBPortalLogin("portalcfb1@mailinator.com", "Bangladesh2$");
        lp.CFBPortalLogin(OR.getProperty("useremail"), OR.getProperty("password"));

        Thread.sleep(5000);
        cp.candidateInfo();



    }
}
