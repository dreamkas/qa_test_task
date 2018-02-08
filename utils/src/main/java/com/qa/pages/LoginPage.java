package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='input_0']")
    private WebElement loginInput;

    @FindBy(xpath = "//*[@id='input_1']")
    private WebElement passInput;

    @FindBy(xpath = "//*[@id='lk-page']/div/div[1]/div/div[2]/div/form/div[1]/button")
    private WebElement loginButton;

    public boolean isLoginPage() {

        return loginButton.isDisplayed();
    }

    public void auth(String login, String pass) throws Exception {
        insertIntoInput(loginInput, login);
        insertIntoInput(passInput, pass);
        loginButton.click();
    }

    @FindBy(xpath = "//md-input-container[3]/div/div")
    private WebElement wrongLoginText;

    public String getWrongLoginText(){
        return wait20Seconds.until(ExpectedConditions.visibilityOf(wrongLoginText)).getText();
    }


}
