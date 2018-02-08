package com.qa;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;

import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BasicSeleniumTest extends BasicTest {


    private static final Logger Log = Logger.getLogger(BasicSeleniumTest.class);

    public static final String resultsPath = "..\\results";
    public static final String resourcesPath = "..\\resources";
    public static final String appURL = "https://kabinet.dreamkas.ru/";
    public static final String browserType = "Chrome";
    public static final String login = "test_qa@gmail.com";
    public static final String pass = "test_qa";
    public static final String code1 = "4607009520032";
    public static final String code2 = "4607009520025";

    public SoftAssert softAssert;
    private WebDriver driver;  //дабы совсем не потерять ссылку на драйвер, в случае собственного косяка - у каждого набора Selenium тестов своя переменая со ссылькой на драйвер.
    private String testName;

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(String browserType, String appURL) {
        WebDriver tempDriver = null;

        if (browserType.equals("Firefox")) {
            tempDriver = initFireFoxDriver(appURL);
        } else if (browserType.equals("Chrome")) {
            tempDriver = initChromeDriver(appURL);
        } else if (browserType.equals("IE")) {
            tempDriver = initIeDriver(appURL);
        }


        WebDriverEventListenerImplementation onExceptionListener = null;
        try {
            FileUtils.forceMkdir(new File(resultsPath));
            Log.info("Listener results path is " + new File(resultsPath).getCanonicalPath());
            onExceptionListener = new WebDriverEventListenerImplementation(new File(resultsPath).getCanonicalPath(), testName);
        } catch (IOException e) {
            Log.info("Can't create Result Directory");
        }
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(tempDriver);
        eventDriver.register(onExceptionListener);
        driver = eventDriver;
    }

    private static WebDriver initFireFoxDriver(String appURL) {
        try {


            System.setProperty("webdriver.gecko.driver", new File(resourcesPath).getCanonicalPath() + "\\geckodriver.exe");
            System.out.println("Launching Firefox browser");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to(appURL);
            return driver;
        } catch (IOException e) {
            throw new SkipException("Fail init driver");
        }
    }


    private static WebDriver initChromeDriver(String appURL) {
        try {

            System.setProperty("webdriver.chrome.driver", new File(resourcesPath).getCanonicalPath() + "\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            options.addArguments("start-maximized");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to(appURL);
            return driver;
        } catch (IOException e) {
            throw new SkipException("Fail init driver");
        }
    }

    private static WebDriver initIeDriver(String appURL) {
        try {
            Log.info("Internet Explorer is selected");
            System.setProperty("webdriver.ie.driver", new File(resourcesPath).getCanonicalPath() + "\\IEDriverServer.exe");
            InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                    .withLogLevel(InternetExplorerDriverLogLevel.TRACE)
                    .withLogFile(new File("c:/temp/iedriver.log"))
                    .build();
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

            WebDriver driver = new InternetExplorerDriver(service, capabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(80, TimeUnit.SECONDS);
            driver.navigate().to(appURL);
            driver.manage().deleteAllCookies();
            driver.navigate().to(appURL);
            return driver;
        } catch (Exception e) {
            Log.error(e);
            throw new SkipException("Fail init driver");
        }
    }


    public void initializeDriver() {
        try {
            Log.info("Hey, i am here. appurl = " + appURL + "   and browser=" + browserType);
            setDriver(browserType, appURL);
        } catch (Exception e) {
            System.out.println("Error " + e.getStackTrace());
        }
    }

    @BeforeMethod
    public void BeforeMethod(Method method, Object[] testData) throws Exception {
        Log.info(" paths is " + new File(resourcesPath).getCanonicalPath() + "\t" + new File(resultsPath).getCanonicalPath());
        testName = method.getName();
        Log.info("method name " + testName);
        softAssert = new SoftAssert();
        initializeDriver();
    }


    protected void scrollDown() {
        EventFiringWebDriver remoteDriver = (EventFiringWebDriver) driver;
        remoteDriver.executeScript("$(window).scrollTop($(document).height())");
    }

    protected void scrollTop() {
        EventFiringWebDriver remoteDriver = (EventFiringWebDriver) driver;
        remoteDriver.executeScript("$(window).scrollTop(0)");
    }
}
