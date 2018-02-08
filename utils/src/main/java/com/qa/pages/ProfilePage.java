package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form/div[1]/md-input-container/input")
    private WebElement nameInpit;

    @FindBy(xpath = "//form/div[5]/md-input-container/input")
    private WebElement passInpit;

    @FindBy(xpath = "//form/div[6]/button")
    private WebElement updateButton;

    public void setNewName(String nameStr){
        insertIntoInput(nameInpit,nameStr);
    }

    public void setPass(String passStr){
        insertIntoInput(passInpit,passStr);
    }

    public void clickUpdateButton(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }

    @FindBy(xpath = "//md-toast/div/div/span")
    private WebElement notification;

    public String getNotificationText(){
        return wait20Seconds.until(ExpectedConditions.visibilityOf(notification)).getText();
    }

}
