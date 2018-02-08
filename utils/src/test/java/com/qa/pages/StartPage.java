package com.qa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

    public StartPage (WebDriver driver){
        super(driver);
    }

    @FindBy(css = "head > title")
    private WebElement title;

    public String getTitle(){
        wait20Seconds.until(ExpectedConditions.visibilityOf(enterButton));
        return title.getAttribute("text");
    }

    //По хорошему - на текст лучше не завязываться, ибо мультиязычность
    @FindBy(linkText = "ВОЙТИ")
    private WebElement enterButton;

    public void clickEnterButton(){
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(enterButton)).click();
    }





}
