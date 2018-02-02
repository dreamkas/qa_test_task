package tests;

import org.junit.Test;
import pages.*;
import settings.FirefoxSettings;


public class DreamkasTest extends FirefoxSettings {


    public void login() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSignInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
    }


    @Test
    public void loginTest() throws InterruptedException { // Проверить авторизацию в Кабинете
        login();
        DevicesPage devicesPage = new DevicesPage(driver);
        devicesPage.assertUserLogin();

    }


    @Test
    public void createGoods() throws InterruptedException { // //Создать товар, заполнив основные поля
        login();
        DevicesPage devicesPage = new DevicesPage(driver);
        devicesPage.clickGoodsButton();
        GoodsPage goodsPage = new GoodsPage(driver);
        goodsPage.clickCreateGoodsButton();
        goodsPage.addGoods();
        goodsPage.assertCreatedGoodsName();
        goodsPage.removeCreatedGoods();
    }

    @Test
    public void createGoodsWithoutNds() throws InterruptedException { // //Создать товар, не заполняя НДС
        login();
        DevicesPage devicesPage = new DevicesPage(driver);
        devicesPage.clickGoodsButton();
        GoodsPage goodsPage = new GoodsPage(driver);
        goodsPage.clickCreateGoodsButton();
        goodsPage.addGoodsWithoutNds();
        goodsPage.assertWarningNds();
    }

    @Test
    public void editProfileName() throws InterruptedException { // //Изменить имя в Профиле
        login();
        DevicesPage devicesPage = new DevicesPage(driver);
        devicesPage.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.editName();
        profilePage.clickUpdateButton();

        profilePage.assertEditingName();
    }

    @Test
    public void checkThatNoCashbox() throws InterruptedException { //Проверить, что никаких касс в Вашем Кабинете нет
        login();
        DevicesPage devicesPage = new DevicesPage(driver);
        devicesPage.assertThatNoCashbox();
    }


}
