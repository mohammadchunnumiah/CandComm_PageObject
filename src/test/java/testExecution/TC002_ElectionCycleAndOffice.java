package testExecution;

import commonAPI.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageLibrary.ElectionCycleAndOffice;
import pageLibrary.LoginPage;

public class TC002_ElectionCycleAndOffice extends TestBase {
    public static final Logger log = Logger.getLogger("TC01_ElectionCycleAndOffice");
    LoginPage loginPage;
    ElectionCycleAndOffice electionCycleAndOffice;

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @Test
    public void pickElectionCycleAndOffice() throws InterruptedException {
        loginPage = new LoginPage(driver);
//        loginPage.CFBPortalLogin("","");
        test = extentReports.createTest("== Login successfully ==");
        loginPage.CFBPortalLogin(OR.getProperty("useremail"), OR.getProperty("password"));
        Thread.sleep(3000);

        test = extentReports.createTest("Selection registration");
        electionCycleAndOffice.pickRegistration();

        test = extentReports.createTest("=== Office South selected ===");
        electionCycleAndOffice.electionCycle("Test automation");

    }


    }
