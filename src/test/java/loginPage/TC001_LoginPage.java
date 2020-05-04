package loginPage;

import com.aventstack.extentreports.Status;
import commonAPI.TestBase1;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageLibrary.LoginPage;

import java.io.IOException;


public class TC001_LoginPage extends TestBase1 {
    public static final Logger log = Logger.getLogger("TC001_LoginPage");
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws Exception {
        initial();
    }
    @Test
    public void candidateLogin(){
        test = extentReports.createTest("Login Successful");
        loginPage = new LoginPage(driver);
        log.info("Starting verifying Login functionalities");
        test = extentReports.createTest("Login Successful");
        loginPage.CFBPortalLogin("portalcfb1@mailinator.com","Bangladesh2$");
    }


//    @AfterClass
//    public void brClose(){
//        browserClose();
//    }

}
