package pageLibrary;

import commonAPI.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ElectionCycleAndOffice extends TestBase {
    WebDriver driver;

    //Initializing the Page Objects
    public ElectionCycleAndOffice(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='https://cfbrpqa.azurewebsites.net/Candidate/Index/1009']")
    WebElement selectExistingReg;

    @FindBy(xpath = "//label[@for='2021']")
    WebElement electionCycle;

    @FindBy(xpath = "//select[@id='Office']")
    WebElement officeDropdown;

    @FindBy(xpath = "//*[@id='PartyRegistration']")
    WebElement partyReg;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    WebElement saveAndContinue;

    public void pickRegistration(){
        selectExistingReg.click();
    }
    //Actions
    public void electionCycle(String partyName) {
        electionCycle.click();
        officeDropdown.clear();

        //WebElement elements = driver.findElement(By.id("animals"));
        Select se = new Select(officeDropdown);
        se.selectByVisibleText("Mayor");

        //officeDropdown.sendKeys(pickOffice);
        partyReg.clear();
        partyReg.sendKeys(partyName);
        saveAndContinue.click();

    }

}
