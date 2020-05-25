package pageLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CFBPortalHomePage {
    WebDriver driver;

    public pageName(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

//demo code here
    @FindBy(xpath="")
    WebElement xxxx;

    @FindBy(xpath="")
    WebElement xxxx;



    public void pageNamexxxx(String userId,String password){
        userEmail.sendKeys(userId);
        userPassword.sendKeys(password);
        signInButton.click();
    }
}
