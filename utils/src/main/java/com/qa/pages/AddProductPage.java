package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;

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

    @FindBy(xpath = "//body/div/md-select-menu/md-content/md-option[1]")
    private WebElement withoutNDS;

    @FindBy(xpath = "//body/div/md-select-menu/md-content/md-option[3]")
    private WebElement tenNDS;

    @FindBy(xpath = "//body/div/md-select-menu/md-content/md-option[4]")
    private WebElement NDS18;

    @FindBy(xpath = "//form/div[2]/div[6]/button[2]")
    private WebElement addButton;

    @FindBy(xpath = "//form/div[2]/div[6]/button[1]")
    private WebElement cancelButton;

    @FindBy(xpath = "//*[@id=\"tour_products_fab\"]/md-fab-speed-dial/md-fab-trigger/button")
    private WebElement addProductButton;


    private static final String ndsErrorMesagePath ="//form/div[2]/div[5]/div/div/md-input-container/div[2]/div";
    private static final String productPath ="//react-component/div/div/div[2]/div/div[";
                                                //react-component/div/div/div[2]/div/div/
    public boolean isNdsErrorPresent(){
        return isWebElementPresent(ndsErrorMesagePath);
    }

    public boolean isProductPresent(int productNum){
        return isWebElementPresent(productPath+productNum+"]");
    }

    public void selectOptionNdsTen(){
        inputMenu.click();
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(tenNDS)).click();
    }

    public void addProduct(String addCode, String addName, String addArticle, int addCost, String addPieces) throws SkipException {
        insertIntoInput(code,addCode);
        insertIntoInput(name,addName);
        insertIntoInput(cost,Integer.toString(addCost));
        insertIntoInput(article, addArticle);
        insertIntoInput(pieces,addPieces);
    }

    public void clickCancelButton(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public void clickAddButton(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }


    public void addProduct(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(addProductButton)).click();
    }




}

