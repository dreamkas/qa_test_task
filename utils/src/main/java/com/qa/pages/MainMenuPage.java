package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainMenuPage extends BasePage {

    public MainMenuPage(WebDriver driver) {
        super(driver);
    }

    private String userButtonPath = "//*[@id='lk-page']/div/div[1]/md-menu/button/div[2]";
    @FindBy(xpath = "//*[@id='lk-page']/div/div[1]/md-menu/button/div[2]")
    private WebElement userButton;

    public String getUserName(){
        wait20Seconds.until(ExpectedConditions.visibilityOf(userButton));
        return userButton.getText();
    }

    public boolean isMainPage(){
        return isWebElementPresent(userButtonPath);
    }

    @FindBy(xpath = "//md-menu-item/a")
    private WebElement profileButton;

    public void openUserProfile(){
        wait20Seconds.until(ExpectedConditions.visibilityOf(userButton)).click();
        wait20Seconds.until(ExpectedConditions.visibilityOf(profileButton)).click();
    }

    @FindBy(xpath = "//*[@id=\"lk-page\"]/div/div[2]/md-sidenav/div[1]/a[3]/div/span")
    private WebElement productsPage;

    public void openProducts(){
       wait20Seconds.until(ExpectedConditions.elementToBeClickable(productsPage)).click();
    }

    @FindBy(xpath = "//md-sidenav/div[1]/a[2]/div/span")
    private WebElement cashboxMenu;

    public void openCashboxMenu(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(cashboxMenu)).click();
    }






}
