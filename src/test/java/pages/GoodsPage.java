package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoodsPage {

    private WebDriver driver;

    public GoodsPage(WebDriver driver) {
        this.driver = driver;
    }

    By createGoodsButtonLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/div[2]/md-fab-speed-dial/md-fab-trigger/button");
    By barcodeLocator = By.xpath("html/body/div[6]/md-dialog/form/div[1]/div[2]/div/div[1]/div/md-autocomplete/md-autocomplete-wrap/md-input-container/input");
    By titleLocator = By.xpath("html/body/div[6]/md-dialog/form/div[1]/div[2]/div/div[2]/md-input-container/input");
    By priceLocator = By.xpath("html/body/div[6]/md-dialog/form/div[2]/div[2]/div/div[2]/md-input-container/input");
    By vendorcodeLocator = By.xpath("html/body/div[6]/md-dialog/form/div[2]/div[3]/div/div[2]/md-input-container/input");
    By countLocator = By.xpath("html/body/div[6]/md-dialog/form/div[2]/div[4]/div/div[3]/md-input-container[1]/input");
    By ndsLocator = By.xpath("html/body/div[6]/md-dialog/form/div[2]/div[5]/div/div/md-input-container/md-select");
    By ndsPopup10Locator = By.xpath("html/body/div[7]/md-select-menu/md-content/md-option[3]");
    By addButtonLocator = By.xpath("html/body/div[6]/md-dialog/form/div[2]/div[6]/button[2]");
    By createdGoodsNameLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/div[1]/react-component/div/div/div[2]/div/div/div[2]");
    By warningNdsLocator = By.xpath("html/body/div[6]/md-dialog/form/div[2]/div[5]/div/div/md-input-container/div[2]/div");
    By goodsMenuButtonLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/div[1]/react-component/div/div[1]/div/div[2]/div/button");
    By goodsRemoveButtonLocator = By.xpath("html/body/div[1]/div/div/div[2]/div[2]/div[1]/react-component/div/div[1]/div/div[2]/div/ul/li[3]/div");
    By goodsRemovePopupYesButtonLocator = By.xpath("html/body/div[6]/md-dialog/div[2]/button[2]");


    public void clickCreateGoodsButton() {
        driver.findElement(createGoodsButtonLocator).click();
    }

    public void addGoods() {

        WebElement barcode = driver.findElement(barcodeLocator);
        barcode.click();
        barcode.clear();
        barcode.sendKeys("12345");

        WebElement title = driver.findElement(titleLocator);
        title.click();
        title.clear();
        title.sendKeys("Товар1");

        WebElement price = driver.findElement(priceLocator);
        price.click();
        price.clear();
        price.sendKeys("100");

        WebElement vendorcode = driver.findElement(vendorcodeLocator);
        vendorcode.click();
        vendorcode.clear();
        vendorcode.sendKeys("01010");

        WebElement count = driver.findElement(countLocator);
        count.click();
        count.clear();
        count.sendKeys("1");

        WebElement nds = driver.findElement(ndsLocator);
        nds.click();
        WebElement ndsPopup10 = driver.findElement(ndsPopup10Locator);
        ndsPopup10.click();

        driver.findElement(addButtonLocator).click();
    }


    public void addGoodsWithoutNds() {

        WebElement barcode = driver.findElement(barcodeLocator);
        barcode.click();
        barcode.clear();
        barcode.sendKeys("12345");

        WebElement title = driver.findElement(titleLocator);
        title.click();
        title.clear();
        title.sendKeys("Товар1");

        WebElement price = driver.findElement(priceLocator);
        price.click();
        price.clear();
        price.sendKeys("100");

        WebElement vendorcode = driver.findElement(vendorcodeLocator);
        vendorcode.click();
        vendorcode.clear();
        vendorcode.sendKeys("01010");

        WebElement count = driver.findElement(countLocator);
        count.click();
        count.clear();
        count.sendKeys("1");

        driver.findElement(addButtonLocator).click();

    }


    public void assertCreatedGoodsName() {
        Assert.assertEquals("Wrong goods name", "Товар1", driver.findElement(createdGoodsNameLocator).getText());
    }

    public void removeCreatedGoods() {
        driver.findElement(goodsMenuButtonLocator).click();
        driver.findElement(goodsRemoveButtonLocator).click();
        driver.findElement(goodsRemovePopupYesButtonLocator).click();


    }


    public void assertWarningNds() {
        Assert.assertEquals("Warning nds fail", "Заполните это поле", driver.findElement(warningNdsLocator).getText());
    }


}
