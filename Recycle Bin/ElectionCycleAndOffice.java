package pageLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElectionCycleAndOffice {

    @FindBy(xpath = "//label[@for='2021']")
    WebElement electionCycle;

    @FindBy(xpath = "//select[@id='Office']")
    WebElement officeDropdown;

    @FindBy(xpath = "//*[@id='PartyRegistration']")
    WebElement partyReg;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    WebElement saveAndContinue;

    //Initializing the Page Objects
    public ElectionCycleAndOffice(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //Actions
    public void Candidate(String pickOffice, String partyName) {
        electionCycle.click();
        officeDropdown.clear();
        officeDropdown.sendKeys(pickOffice);
        partyReg.clear();
        partyReg.sendKeys(partyName);
        saveAndContinue.click();

    }

}
