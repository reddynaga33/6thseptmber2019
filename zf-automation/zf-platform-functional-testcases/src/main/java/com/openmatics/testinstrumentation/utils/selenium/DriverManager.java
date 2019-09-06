package com.openmatics.testinstrumentation.utils.selenium;

import java.io.File;
import com.google.common.base.Strings;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;

public class DriverManager
{

    public static String CHROME_WEBDRIVER_NAME = "chromedriver";
    public static String FIREFOX_WEBDRIVER_NAME = "geckodriver";


    public static WebDriver getChromeDriver(String remote_url) throws MalformedURLException
    {
        System.out.println("Remote chrome driver was created");
        return new RemoteWebDriver(new java.net.URL(remote_url), getChromeCapabilities());
    }

    public static WebDriver getLocalChromeDriver(File webDriverfile) // maybe parameters with folder with drivers ?
    {
        System.out.println("Localy chrome driver will be created from location: " + webDriverfile.getPath());
        ChromeDriverService service = new ChromeDriverService.Builder()
            .usingDriverExecutable(webDriverfile)
            .usingAnyFreePort()
            .build();

        ChromeOptions options = new ChromeOptions();
        options.merge(getChromeCapabilities());
        return new ChromeDriver(service, options);
    }


    private static DesiredCapabilities getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        // set some options
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setCapability(ChromeOptions.CAPABILITY, options);
        dc.setCapability("commandTimeout", 300); // 300 is default max 600 seconds
        dc.setCapability("idleTimeout", 90); // 90 is default max is 1000
        dc.setCapability("timeoutInSeconds", 300);
        dc.setCapability("takesScreenshot", true);
        return dc;
    }

    public static WebDriver getFireFoxDriver(String remote_url) throws MalformedURLException
    {
        System.out.println("Remote firefox driver will be created");
        return new RemoteWebDriver(new java.net.URL(remote_url), getFirefoxCapabilities());

    }

    public static WebDriver getLocalFirefoxDriver(File webDriverfile) {
        System.out.println("Localy firefox driver will be created from location: " + webDriverfile.getPath());
        GeckoDriverService service = new GeckoDriverService.Builder()
                .usingDriverExecutable(webDriverfile)
                .usingAnyFreePort()
                .build();

        FirefoxOptions options = new FirefoxOptions();
        options.merge(getFirefoxCapabilities());
        return new FirefoxDriver(service, options);
    }


    private static DesiredCapabilities getFirefoxCapabilities() {
        //# Use Profile or Options firefox ?
        //FirefoxProfile fp = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // set something on the profile...
        DesiredCapabilities dc = DesiredCapabilities.firefox();
        //dc.setCapability(FirefoxDriver.PROFILE, fp);
        dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS,firefoxOptions);
        return dc;
    }

}
