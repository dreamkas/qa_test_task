package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DevicesPage {


    private WebDriver driver;

    public DevicesPage(WebDriver driver) {
        this.driver = driver;
    }

    By userLoginLocator = By.xpath("html/body/div[1]/div/md-sidenav/div[1]/div[2]");
    By goodsButtonLocator = By.xpath("html/body/div[1]/div/md-sidenav/div[2]/a[3]");
    By profileButtonLocator = By.xpath("html/body/div[1]/div/md-sidenav/div[2]/a[6]");
    By addFirstCashboxTextLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/div/div/h3");

    public void clickGoodsButton() {
        driver.findElement(goodsButtonLocator).click();
    }

    public void clickProfileButton() {
        driver.findElement(profileButtonLocator).click();
    }


    public void assertUserLogin() {
        Assert.assertEquals("Wrong user login", "test_qa@gmail.com", driver.findElement(userLoginLocator).getText());
    }

    public void assertThatNoCashbox() {
        Assert.assertEquals("Cashbox exist", "Чтобы начать работу в Кабинете, подключите первую кассу",
                driver.findElement(addFirstCashboxTextLocator).getText());
    }

}
