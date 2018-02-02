package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.Date;

public class ProfilePage {


    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    By nameLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/form/div[1]/md-input-container/input");
    By updateButtonLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/form/div[6]/button");
    By passwordLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/form/div[5]/md-input-container/input");
    By profileNameLocator = By.xpath("html/body/div[1]/div/md-sidenav/div[1]/div[1]");

    Date uniqDate = new Date();
    String uniqProfileName = "na" + uniqDate.getTime() + "me";

    public void editName() {

        WebElement name = driver.findElement(nameLocator);
        name.click();
        name.clear();
        name.sendKeys(uniqProfileName);

        WebElement password = driver.findElement(passwordLocator);
        password.click();
        password.clear();
        password.sendKeys("test_qa");

    }

    public void clickUpdateButton() throws InterruptedException {
        driver.findElement(updateButtonLocator).click();
        Thread.sleep(1000);
    }

    public void assertEditingName() {
        Assert.assertEquals("Name wasn't edit", uniqProfileName, driver.findElement(profileNameLocator).getText());
    }

}
