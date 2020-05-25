package pageLibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CandidatePage {


    @FindBy(xpath = "//*[@id='rdActions']/ul/li[2]/img")
    WebElement candidatePage;

    @FindBy(xpath = "//*[@id='FirstName']")
    WebElement firstName;
    @FindBy(xpath = "//*[@id='LastName']")
    WebElement lastName;
    @FindBy(xpath = "//*[@id='MiddleInitial']")
    WebElement middleInitial;


    public void candidateInfo(){

        candidatePage.click();
        firstName.clear();
        firstName.sendKeys("Mohammad");
        lastName.clear();
        lastName.sendKeys("Miah");
        middleInitial.clear();
        middleInitial.sendKeys("C");



    }


}
