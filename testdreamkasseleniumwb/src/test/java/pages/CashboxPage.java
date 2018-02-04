package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CashboxPage extends Page {

    @FindBy(xpath = "/html//div[@id='lk-page']//div[@class='lk-app__content lk-view ng-scope']//div[@class='lk-devices ng-scope']/div[@class='lk-devices__empty ng-scope']//button[@type='button']")
    private WebElement connectedNewCashBoxButton;

    public CashboxPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkButtonAddNewCashBoxIsDisplayed(){
        return connectedNewCashBoxButton.isDisplayed();
    }

}
