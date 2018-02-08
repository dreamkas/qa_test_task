package com.qa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class BasicTest {


    public static boolean isStringContainsInList(String searchString, List<String> strings) {  //можно переписать и по другому, используя 2 return.
        boolean isFound = false;
        for (String s : strings) {
            if (s.contains(searchString))
                isFound = true;
        }
        return isFound;
    }

    public static Date convertStringToDate(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(dateFormat);          //"d MMMM yyyy г."
        Date date = format.parse(dateString);
        return date;
    }
}
