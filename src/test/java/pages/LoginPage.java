package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    By emailInputLocator = By.xpath("html/body/div[1]/div/div[1]/div/div[2]/div/form/md-input-container[1]/input");
    By passwordInputLocator = By.xpath("html/body/div[1]/div/div[1]/div/div[2]/div/form/md-input-container[2]/input");
    By submitButtonLocator = By.xpath("html/body/div[1]/div/div[1]/div/div[2]/div/form/div[1]/button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeEmail(String email) {
        WebElement login = driver.findElement(emailInputLocator);
        login.click();
        login.clear();
        login.sendKeys(email);
    }

    public void typePassword(String password) {
        WebElement login = driver.findElement(passwordInputLocator);
        login.click();
        login.clear();
        login.sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public void login(String email, String password) throws InterruptedException {
        typeEmail(email);
        typePassword(password);
        clickSubmitButton();
    }

}
