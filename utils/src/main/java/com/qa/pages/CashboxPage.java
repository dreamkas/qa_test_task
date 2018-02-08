package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CashboxPage extends BasePage{

    public CashboxPage(WebDriver driver)  {
        super(driver);
    }

    @FindBy(className = "lk-devices__empty-text")
    private WebElement titleText;

    public String getTitleText(){
        return wait20Seconds.until(ExpectedConditions.visibilityOf(titleText)).getText();
    }

}
