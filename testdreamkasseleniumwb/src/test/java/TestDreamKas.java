import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestDreamKas {

    private static WebDriver driver;
    private static HomePage homePage;
    private static LoginPage loginPage;
    private static KabinetPage kabinetPage;
    private static GoodsPage goodsPage;
    private static CashboxPage cashboxPage;
    private static ProfilePage profilePage;

    @BeforeClass
    public static void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage = PageFactory.initElements(driver, HomePage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        kabinetPage = PageFactory.initElements(driver, KabinetPage.class);
        goodsPage = PageFactory.initElements(driver, GoodsPage.class);
        cashboxPage = PageFactory.initElements(driver, CashboxPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
    }

    @BeforeClass
    public static void authorization() {
        driver.navigate().to("https://kabinet.dreamkas.ru");

        homePage.clickEnter();

        loginPage.inputLogin();
        loginPage.inputPassword();
        loginPage.clickEnter();
    }

    @Before
    public void start() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().to("https://kabinet.dreamkas.ru/app/");
    }

    @Test
    public void testAddNewGoods() throws InterruptedException {
        kabinetPage.clickGoods();

        goodsPage.clickAddGoodsButton();
        goodsPage.inputBarcode();
        goodsPage.inputNameGoods();
        goodsPage.inputPriceGoods();
        goodsPage.inputVendorCode();
        goodsPage.selectTypeGoods();
        goodsPage.clickNdsType();
        goodsPage.selectNds118();
        goodsPage.addGoods();
        assertEquals(true, goodsPage.isAddedNewGoods());
    }

    @Test
    public void testAddNewGoodsWithoutNds() throws InterruptedException {
        kabinetPage.clickGoods();

        goodsPage.clickAddGoodsButton();
        goodsPage.inputBarcode();
        goodsPage.inputNameGoods();
        goodsPage.inputPriceGoods();
        goodsPage.inputVendorCode();
        goodsPage.selectTypeGoods();
        goodsPage.addGoods();
        boolean isNotAdded = goodsPage.checkImposibleAddWithoutNds();
        assertEquals(true, isNotAdded);
    }

    @Test
    public void testEmptyCashBox() {
        kabinetPage.clickCashbox();
        boolean isNotCashBox = cashboxPage.checkButtonAddNewCashBoxIsDisplayed();
        assertEquals(true, isNotCashBox);
    }

    @Test
    public void testChangeNameInProfile() {
        kabinetPage.clickMenu();
        kabinetPage.clickProfile();

        profilePage.changeName();
        boolean isSuccessChangeName = profilePage.checkSuccessChangeName();
        assertEquals(true, isSuccessChangeName);
    }


    @AfterClass
    public static void stop() {
        driver.quit();
        driver = null;
    }


}
