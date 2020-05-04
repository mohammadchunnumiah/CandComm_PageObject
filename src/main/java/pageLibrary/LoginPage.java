package pageLibrary;

import commonAPI.TestBase1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase1 {
    WebDriver driver;

//    public static final Logger log = Logger.getLogger(HomePage.class.getName());

    @FindBy(xpath="//input[@id='Email']")
    WebElement userEmail;

    @FindBy(xpath="//input[@name='Password']")
    WebElement userPassword;

    @FindBy(xpath="////button[@name='button']")
    WebElement signInButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    public void CFBPortalLogin(String userId,String password){
        userEmail.sendKeys(userId);
        userPassword.sendKeys(password);
        signInButton.click();
    }

}
