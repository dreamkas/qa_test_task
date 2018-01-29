package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By signInButtonLocator = By.xpath("html/body/div[1]/div[1]/div/a[2]/span");


    public void clickSignInButton() {
        driver.findElement(signInButtonLocator).click();
    }


}
