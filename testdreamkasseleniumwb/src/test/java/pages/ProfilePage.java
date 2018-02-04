package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends Page {

    @FindBy(xpath = "/html//div[@id='lk-page']//div[@class='lk-app__content lk-view ng-scope']//form[@name='$ctrl.ProfileInfoForm']/div[1]/md-input-container/input[@name='Name']")
    WebElement name;

    @FindBy(xpath = "/html//div[@id='lk-page']//div[@class='lk-app__content lk-view ng-scope']//form[@name='$ctrl.ProfileInfoForm']//button[@type='submit']")
    WebElement accept;

    @FindBy(xpath = "/html//div[@id='lk-page']//div[@class='lk-app__content lk-view ng-scope']//form[@name='$ctrl.ProfileInfoForm']/div[5]/md-input-container/input[@name='ConfirmPassword']")
    WebElement passwordForChange;

    @FindBy(xpath = "/html//md-toast//div[@class='lk-toast__content']")
    WebElement notification;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void changeName(){
        name.clear();
        name.sendKeys("new name");
        passwordForChange.sendKeys("test_qa");
        accept.click();
    }

    public boolean checkSuccessChangeName(){
        return notification.isDisplayed();
    }

}
