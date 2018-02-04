package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoodsPage extends Page {

    @FindBy(xpath = "//div[@id='tour_products_fab']/md-fab-speed-dial/md-fab-trigger//md-icon[@role='img']")
    private WebElement addNewGoodsButton;

    @FindBy(xpath = "/html//md-dialog[@role='dialog']/form[@name='ProductForm']//md-autocomplete/md-autocomplete-wrap/md-input-container/input[@role='combobox']")
    private WebElement barcode;

    @FindBy(xpath = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']/div[@class='lk-product__section lk-product__section--header']/div[@class='lk-product__loading lk-product__loading--ready']/div[@class='lk-product__block-fields']/div[2]/md-input-container/input[@name='name']")
    private WebElement nameGoods;

    @FindBy(xpath = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']/div[@class='lk-product__loading lk-product__loading--ready']/div[2]/div[@class='lk-product__block-fields']/div[@class='lk-product__fields-row']/md-input-container/input[@name='price']")
    private WebElement price;

    @FindBy(xpath = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']/div[@class='lk-product__loading lk-product__loading--ready']/div[3]/div[@class='lk-product__block-fields']/div[@class='lk-product__fields-row']/md-input-container/input[@name='VendorCodes']")
    private WebElement vendorCode;

    @FindBy(xpath = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']//md-radio-group[@role='radiogroup']/md-radio-button[1]")
    private WebElement typeGoodsPiece;

    @FindBy(xpath = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']/div[@class='lk-product__loading lk-product__loading--ready']/div[4]/div[@class='lk-product__block-fields']/div[3]/md-input-container[1]/input[@name='quantity']")
    private WebElement countGoods;

    @FindBy(css = ".lk-product__select.ng-invalid-required")
    private WebElement ndsSelectType;

    @FindBy(css = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']//md-input-container[@class='lk-product__field lk-product__field--select lk-product__field--width_33 md-input-invalid']//div[@class='md-input-message-animation ng-scope']")
    private WebElement textAboutIsImpossibleAdd;

    @FindBy(xpath = "//body/div[7]/md-select-menu/md-content/md-option[6]")
    private WebElement nds118;

    @FindBy(xpath = "/html//md-dialog[@role='dialog']/form[@name='ProductForm']//button[@type='submit']")
    private WebElement addGoods;

    @FindBy(xpath = "/html//div[@id='lk-page']//div[@class='lk-app__content lk-view ng-scope']//lk-search-box[@value='$ctrl.defaultQuery']/md-input-container/input[@name='query']")
    private WebElement searchField;

    @FindBy(xpath = "/html//div[@id='lk-page']//div[@class='lk-app__content lk-view ng-scope']/div[@class='lk-app__content-body ng-scope']//div[@class='ng-scope']/react-component[@name='K_SEARCH_GOODS']/div[@class='k-goods-search']//div[@class='infinite-scroll-component']//div[@class='k-product-list__item-name']")
    private WebElement resultSearch;

    @FindBy(xpath = "/html//div[@class='md-dialog-container ng-scope']/md-dialog[@role='dialog']/form[@name='ProductForm']//div[@class='lk-product__block-controls']/div[2]/div[2]/md-icon[@role='button']")
    private WebElement deleteButton;

    @FindBy(xpath = "/html/body/div[7]/md-dialog[@role='dialog']/div[@class='lk-modal__actions lk-modal__actions--without-errors']/button[2]")
    private WebElement confirmationOfremoval;

    public GoodsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickAddGoodsButton() {
        addNewGoodsButton.click();
    }

    public void inputBarcode() {
        barcode.sendKeys("23232");
    }

    public void inputNameGoods() {
        nameGoods.sendKeys("Телефон");
    }

    public void inputPriceGoods() {
        price.sendKeys("2222");
    }

    public void inputVendorCode() {
        vendorCode.sendKeys("2");
    }

    public void selectTypeGoods() {
        typeGoodsPiece.click();
    }

    public void clickNdsType() {
        ndsSelectType.click();
    }

    public void selectNds118() {
        nds118.click();
    }

    public void addGoods() {
        addGoods.click();
    }

    public boolean checkImposibleAddWithoutNds() {
        return addGoods.isDisplayed();
    }

    public boolean isAddedNewGoods() throws InterruptedException {
        searchField.sendKeys("23232");
        boolean result = resultSearch.isDisplayed();
        if (result) {
            resultSearch.click();
            deleteButton.click();
            Thread.sleep(2222);
            confirmationOfremoval.click();
            return true;
        } else return false;
    }

}
