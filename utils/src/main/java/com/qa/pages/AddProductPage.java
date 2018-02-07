package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AddProductPage extends BasePage {

    public AddProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"fl-input-10\"]")
    private WebElement code;

    @FindBy(xpath = "//*[@id=\"input_12\"]")
    private WebElement name;

    @FindBy(xpath = "//*[@id=\"input_13\"]")
    private WebElement cost;

    @FindBy(xpath = "//*[@id=\"input_14\"]")
    private WebElement article;

    @FindBy(xpath = "//*[@id=\"input_19\"]")
    private WebElement pieces;

    @FindBy(xpath = "//form/div[2]/div[5]/div/div/md-input-container")
    private WebElement inputMenu;

    @FindBy(xpath = "//form/div[2]/div[5]/div/div/md-input-container/md-select/md-select-value/span[2]")
    private WebElement selectMenu;

    @FindBy(xpath = "//body/div/md-select-menu/md-content/md-option[1]")
    private WebElement withoutNDS;

    @FindBy(xpath = "//body/div/md-select-menu/md-content/md-option[3]")
    private WebElement tenNDS;

    @FindBy(xpath = "//body/div/md-select-menu/md-content/md-option[4]")
    private WebElement NDS18;

    public void selectOptionNdsTen(){
        inputMenu.click();
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(tenNDS)).click();
    }



}

