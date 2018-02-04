package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page{

    @FindBy(linkText = "ВОЙТИ")
    private WebElement enter;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickEnter(){
        enter.click();
    }

}
