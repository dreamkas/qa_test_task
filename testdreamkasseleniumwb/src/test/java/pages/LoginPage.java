package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(xpath = "/html//div[@id='lk-page']//form[@name='$ctrl.LoginForm']/md-input-container[1]/input[@name='Login']")
    private WebElement login;

    @FindBy(xpath = "/html//div[@id='lk-page']//form[@name='$ctrl.LoginForm']/md-input-container[2]/input[@name='Password']")
    private WebElement password;

    @FindBy(xpath = "/html//div[@id='lk-page']/div[@class='lk-page__dialog-wrap ng-scope']//form[@name='$ctrl.LoginForm']//button[@type='submit']")
    private WebElement enter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void inputLogin(){
        login.sendKeys("test_qa@gmail.com");
    }

    public void inputPassword(){
        password.sendKeys("test_qa");
    }

    public void clickEnter() {
        enter.click();

    }
}
