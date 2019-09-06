package com.openmatics.testinstrumentation.utils.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface ISeleniumService {

    public WebDriver getDriver();

    public WebDriverWait getWait(int timeOutInSeconds, int sleepInMillis);

    public void setDimension(int width, int height);

    public void Close();

    //getUrlServer()

    //waitForReady()

    //tryCreateDriver()

}
