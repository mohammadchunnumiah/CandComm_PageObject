package pageLibrary;

import commonAPI.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CFB_PortalHomePage extends TestBase {
    WebDriver driver;

    //Initializing the Page Objects
    public CFB_PortalHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@id='candshowMore']")
    WebElement candidateLoadMore;

    @FindBy(xpath = "//a[@href='https://cfbrpqa.azurewebsites.net/Candidate/Index/154']")
    WebElement pickRegistration;

    public void CFBPortalHomePage() {
        candidateLoadMore.click();
        pickRegistration.click();
    }



}
