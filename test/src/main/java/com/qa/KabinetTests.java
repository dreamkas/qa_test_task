package com.qa;

import static com.qa.pages.BasePage.helpPause;

import com.qa.pages.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class KabinetTests extends BasicSeleniumTest {

    private WebDriver driver;
    private StartPage startPage;
    private LoginPage loginPage;
    private MainMenuPage mainMenuPage;
    private AddProductPage addProductPage;
    private ProfilePage profilePage;
    private CashboxPage cashboxPage;
    private Logger Log = Logger.getLogger(KabinetTests.class);

    @BeforeMethod
    public void beforeKabinet() {
        driver = getDriver();

        startPage = PageFactory.initElements(driver, StartPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainMenuPage = PageFactory.initElements(driver, MainMenuPage.class);
        addProductPage = PageFactory.initElements(driver, AddProductPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        cashboxPage = PageFactory.initElements(driver, CashboxPage.class);
    }

    @AfterMethod
    public void afterKabinet() {
        driver.manage().deleteAllCookies();  //передаю привет IE
        driver.close();
    }

    @Test
    public void KT01_wrongPass() throws Exception {
        Assert.assertEquals("Кабинет Дримкас", startPage.getTitle(), "Wrong title");
        startPage.clickEnterButton();
        Assert.assertTrue(loginPage.isLoginPage(), "This isn't login page");
        loginPage.auth(login, "wrong" + pass);
        softAssert.assertEquals(loginPage.getWrongLoginText(),"Указан неверный пароль", "Wrong message, or you logIn with wrong pass");
        softAssert.assertEquals(mainMenuPage.isMainPage(),false, "You are in kabinet now. This isn't expected");
        softAssert.assertAll();

    }

    @Test
    public void KT02_openLoginPage() throws Exception {
        Assert.assertEquals("Кабинет Дримкас", startPage.getTitle(), "Wrong title");
        startPage.clickEnterButton();
        Assert.assertTrue(loginPage.isLoginPage(), "This isn't login page");
        loginPage.auth(login, pass);
        Assert.assertEquals(mainMenuPage.isMainPage(),true, "You are not in kabinet now. This isn't expected");
        Assert.assertEquals(mainMenuPage.getUserName(), login, "Wrong name on page");

    }


    @Test
    public void KT03_addProduct() throws Exception {
        startPage.clickEnterButton();
        loginPage.auth(login, pass);
        mainMenuPage.openProducts();
        addProductPage.addProduct();
        String productName = "newProduct1";
        addProductPage.addProduct(code2, productName, "newArticle1", 287, "2");
        addProductPage.selectOptionNdsTen();
        addProductPage.clickAddButton();
        Assert.assertEquals(true, addProductPage.isProductPresent(1), " No product in products list");
    }


    @Test
    public void KT04_addProductNoNDS() throws Exception {
        startPage.clickEnterButton();
        loginPage.auth(login, pass);
        mainMenuPage.openProducts();
        addProductPage.addProduct();
        addProductPage.addProduct(code2, "newProduct2", "newArticle2", 587, "1");
        addProductPage.clickAddButton();
        Assert.assertEquals(true, addProductPage.isNdsErrorPresent(),"NDS error isn't present");
        addProductPage.clickCancelButton();
        Assert.assertEquals(false, addProductPage.isProductPresent(2),"Product added to product list"); //предполагаем что на странице будет только 1 товар, добаввленный в первом тесте
    }

    @Test
    public void KT05_changeProfileName() throws Exception {
        startPage.clickEnterButton();
        loginPage.auth(login, pass);
        mainMenuPage.openProducts();
        mainMenuPage.openUserProfile();
        String newName = "newNameForTester";
        profilePage.setNewName(newName);
        profilePage.setPass(pass);
        profilePage.clickUpdateButton();
        helpPause(2);
        Assert.assertEquals("Профиль обновлён",profilePage.getNotificationText(),"Wrong notification about profile update");
    }

    @Test
    public void KT06_checkNoCashbox() throws Exception {
        startPage.clickEnterButton();
        loginPage.auth(login, pass);
        mainMenuPage.openCashboxMenu();
        Assert.assertEquals(cashboxPage.getTitleText(),"Чтобы начать работу в Кабинете, подключите первую кассу", "Wrong text. Looks like where is no expected message. Maybe some cashbox in menu");

    }


}
