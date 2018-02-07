package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainMenuPage extends BasePage {

    public MainMenuPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='lk-page']/div/div[1]/md-menu/button/div[2]")
    private WebElement userButton;

    public String getUserName(){
        wait20Seconds.until(ExpectedConditions.visibilityOf(userButton));
        return userButton.getText();
    }

    @FindBy(xpath = "//*[@id=\"lk-page\"]/div/div[2]/md-sidenav/div[1]/a[3]/div/span")
    private WebElement productsPage;

    public void openProducts(){
       wait20Seconds.until(ExpectedConditions.elementToBeClickable(productsPage)).click();
    }



    @FindBy(xpath = "//*[@id=\"tour_products_fab\"]/md-fab-speed-dial/md-fab-trigger/button")
    private WebElement addProductButton;

    public void addProduct(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(addProductButton)).click();
    }

}
