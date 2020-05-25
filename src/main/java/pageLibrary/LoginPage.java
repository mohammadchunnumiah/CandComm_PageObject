package pageLibrary;

import commonAPI.TestBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//    public static final Logger log = Logger.getLogger(HomePage.class.getName());


    @FindBy(xpath = "//input[@id='Email']")
    WebElement userEmail;

    @FindBy(xpath = "//input[@name='Password']")
    WebElement userPassword;

    @FindBy(xpath = "//button[@name='button']")
    WebElement signInButton;

//NEWLY ADDED
    @FindBy(xpath = "//input[@id='candshowMore']")
    WebElement candidateLoadMore;

    @FindBy(xpath = "//a[@href='https://cfbrpqa.azurewebsites.net/Candidate/Index/154']")
    WebElement pickRegistration;


    public void CFBPortalLogin(String userId, String password) throws InterruptedException {
        userEmail.sendKeys(userId);
        userPassword.sendKeys(password);
        signInButton.click();

        Thread.sleep(5000);
//        NEWLY ADDED
        candidateLoadMore.click();
        Thread.sleep(2000);
        pickRegistration.click();
    }

}
