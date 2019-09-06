package com.openmatics.testinstrumentation.utils.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.InvalidElementStateException;

// instade of use method e.g. wait.until(ExpectedConditions.numberOfElementsToBe(userSpanSelector, 1));
@Deprecated()
public class ElementWaiter {

    public static void waitOnElementPresent(WebDriver driver, By  selectBy, int elementCount, int millisTimeout) throws InvalidElementStateException, InterruptedException{
        int time = 0;
        int interval = 100;

        while(time < millisTimeout){
            if ( driver.findElements(selectBy).size() == elementCount ) return;
            Thread.sleep(interval);
            time += interval;
        }
        throw new org.openqa.selenium.InvalidElementStateException("In collection did not reached require count element");
    }
}
