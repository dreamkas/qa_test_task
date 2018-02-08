package com.qa;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;

public class WebDriverEventListenerImplementation extends AbstractWebDriverEventListener {

    private String resultsPath;
    private String testName;
    private int screenNum;

    private static Logger Log = Logger.getLogger(WebDriverEventListenerImplementation.class);

    public WebDriverEventListenerImplementation(String resPath, String testN)
    {
        super();
        resultsPath=resPath;
        testName =testN;
        Log.info("In ecent Listener construct end");
        screenNum=0;
    }

    @Override
    public void onException(Throwable e , WebDriver driver)
    {
        Log.info("On exception method started. Take a screen of browser...");
        EventFiringWebDriver screenshotDriver = new EventFiringWebDriver(driver);
        File scrFile =  screenshotDriver.getScreenshotAs(OutputType.FILE);
        Log.info("Screen source "+ scrFile.getAbsolutePath() +" Screen go to "+ resultsPath+"\\"+ testName+ "_"+ screenNum +"_screen.png");
        try {
            FileUtils.copyFile(new File(scrFile.getAbsolutePath()), new File(resultsPath + "/" + testName + "_" + screenNum++ + "_screen.png"));
        } catch (IOException ex){
            Log.error("Some provlems then try to copy screenshot \n" + ex);
        }

    }

}
