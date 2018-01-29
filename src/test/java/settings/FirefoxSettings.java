package settings;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxSettings {

    protected WebDriver driver;
    protected String userEmail = "test_qa@gmail.com";
    protected String userPassword = "test_qa";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://kabinet.dreamkas.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
