package com.openmatics.testinstrumentation.utils.selenium;

import com.openmatics.testinstrumentation.utils.net.HttpRequest;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;

import java.io.File;

public class SeleniumChromeService implements ISeleniumService {

    private WebDriver driver = null;

    public SeleniumChromeService(String remoteUrl) throws Exception {
        driver = DriverManager.getChromeDriver(remoteUrl);
        System.out.println("New instance of chrome remote driver was created: " + remoteUrl);
    }

    public SeleniumChromeService(File file) throws Exception {
        driver = DriverManager.getLocalChromeDriver(file);
        System.out.println("New instance of chrome local driver was created: " + file.getPath());
    }

    @Override
    public void setDimension(int width, int height){
        driver.manage().window().setSize(new Dimension(width, height));
    }

    @Override
    public void Close(){
        if (driver != null){
            System.out.println("Destroy driver call driver.quite()");
            driver.close();
            driver.quit();

        } else {
            System.out.println("Driver is null");
        }
    }

    @Override
    public WebDriver getDriver(){

        return driver;
    }

    @Override
    public WebDriverWait getWait(int timeOutInSeconds, int sleepInMillis){

        return new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
    }

    public static SeleniumChromeService getSeleniumChromeServiceWithAvailableRemoteDriver(String remoteUrl, int timeOutSec) throws Exception
    {
        SeleniumChromeService service = null;
        int waitStep = 5000;
        Calendar timeEnd;
        timeEnd = Calendar.getInstance();
        System.out.println("Start time for time out is: " + timeEnd.getTime());
        timeEnd.add(Calendar.SECOND, timeOutSec);
        System.out.println("Time will be over at: " + timeEnd.getTime() + " !");

        // wait for available seleinum driver;
        boolean isServer = false;
        while (Calendar.getInstance().before(timeEnd))
        {
            HttpResponse response = null;
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().build())
            {
                System.out.println("Try call selenium server");
                HttpUriRequest request = new HttpGet(System.getProperty("remoteUrl"));
                HttpRequest omRequest = new HttpRequest(request);
                response = new HttpResponse(httpClient.execute(request), omRequest);
            }
            catch(Exception ex){
                // empty
            }
            if (response != null && response.getStatus() == 200) {
                isServer = true;
                break;
            }
            System.out.println("Waiting for availability selenium server, timeout over occurs: " + timeEnd.getTime());
            Thread.sleep(waitStep);
        }

        // wait for first web driver instance
        while (isServer && Calendar.getInstance().before(timeEnd))
        {
            WebDriver driver = null;
            try {
                System.out.println("Try create instance of web driver");
                driver =  DriverManager.getChromeDriver(remoteUrl);
            }
            catch(Exception ex){
                // empty
            }
            finally {
                if (driver != null ){
                    driver.close();
                    driver.quit();
                    return new SeleniumChromeService(remoteUrl);
                }
            }
            System.out.println("Waiting for availability selenium web driver, timeout over occurs: " + timeEnd.getTime());
            Thread.sleep(waitStep);
        }
        throw new RuntimeException(String.format("Selenium remote server %s is not available or can't create web driver in timeoout %s.", remoteUrl, String.valueOf(timeOutSec)));
    }

}
