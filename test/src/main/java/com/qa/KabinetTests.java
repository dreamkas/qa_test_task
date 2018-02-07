package com.qa;

import static com.qa.pages.BasePage.helpPause;

import com.qa.pages.AddProductPage;
import com.qa.pages.LoginPage;
import com.qa.pages.MainMenuPage;
import com.qa.pages.StartPage;
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
    private Logger Log = Logger.getLogger(KabinetTests.class);

    @BeforeMethod
    public void beforeKabinet(){
        driver=getDriver();

        startPage = PageFactory.initElements(driver,StartPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        mainMenuPage = PageFactory.initElements(driver,MainMenuPage.class);
        addProductPage = PageFactory.initElements(driver,AddProductPage.class);
    }

    @AfterMethod
    public void afterKabinet(){
        driver.manage().deleteAllCookies();  //передаю привет IE
        driver.close();
    }


    @Test
    public void openLoginPage() throws Exception{
        Assert.assertEquals("Кабинет Дримкас",startPage.getTitle(),"Wrong title");
        startPage.clickEnterButton();
        Assert.assertTrue(loginPage.isLoginPage(), "This isn't login page");
        loginPage.auth(login,pass);
        Assert.assertEquals(mainMenuPage.getUserName(),login);

        //SECOND TEST
        mainMenuPage.openProducts();
        mainMenuPage.addProduct();
        helpPause(5);
       // scrollDown();
        addProductPage.selectOptionNdsTen();
        helpPause(10);


    }




}
