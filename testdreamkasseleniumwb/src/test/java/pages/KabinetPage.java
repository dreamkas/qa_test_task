package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KabinetPage extends Page {

    @FindBy(linkText = "Товары")
    private WebElement goods;

    @FindBy(linkText = "Кассы")
    private WebElement cashbox;

    @FindBy(linkText = "Профиль")
    private WebElement profile;

    @FindBy(xpath = "//div[@id='lk-page']//md-menu/button[@class='md-button md-ink-ripple']/div[@class='lk-app__header-menu-label ng-binding ng-scope']")
    private WebElement menu;

    public KabinetPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickGoods() throws InterruptedException {
        goods.click();
        goods.click();
    }

    public void clickCashbox(){
        cashbox.click();
        cashbox.click();
    }

    public void clickMenu(){
        menu.click();
    }

    public void clickProfile(){
        profile.click();
    }



}
