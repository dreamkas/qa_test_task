package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class Page {

    WebDriver driver;

    Page(WebDriver driver){
        this.driver = driver;
    }

}

