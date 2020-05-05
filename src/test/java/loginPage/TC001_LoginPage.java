package loginPage;

import commonAPI.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import pageLibrary.LoginPage;


public class TC001_LoginPage extends TestBase {
    public static final Logger log = Logger.getLogger("TC001_LoginPage");
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @Test
    public void candidateLogin() {
        test = extentReports.createTest("Successfully logged in");
        loginPage = new LoginPage(driver);
        log.info("Starting verifying Login functionalities");
        test = extentReports.createTest("Successfully logged in");
        // Credentials
        loginPage.CFBPortalLogin("portalcfb1@mailinator.com", "Bangladesh2$");
    }


//    @AfterClass
//    public void brClose(){
//        browserClose();
//    }

}
