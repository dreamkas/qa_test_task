package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;


public class BasePage {

    private static final Logger Log = Logger.getLogger(BasePage.class);

    protected WebDriver driver;
    protected WebDriverWait wait20Seconds;
    protected WebDriverWait wait40Seconds;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        wait40Seconds = new WebDriverWait(this.driver, 40);
        wait20Seconds = new WebDriverWait(this.driver, 20);
    }


    public void clearInputField(WebElement inputField) {
        wait20Seconds.until(ExpectedConditions.elementToBeClickable(inputField));
        inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        inputField.clear();
    }

    public void insertIntoInput(WebElement input, String value)throws Exception
    {
        String valueCheck ="";
        int timeout = 31;
        do {
            helpPause(2);
            clearInputField(input);
            input.sendKeys(value);
            valueCheck = input.getAttribute("value");
            Log.info("value in input now = " + valueCheck);
            timeout = timeout - 1;
        }
        while ((!valueCheck.equals(value)) && (timeout>0));
        if (timeout==0)
        {
            throw new SkipException("Can't insert valid value into input-box");
        }
    }

    public static void helpPause(int sec) {
        Log.info("Pause for " + sec + " seconds...");
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            Log.error(e);
        }
    }



}
