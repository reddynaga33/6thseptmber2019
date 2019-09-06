package com.openmatics.testinstrumentation.utils.testng;

import org.testng.ITestResult;
import org.testng.Reporter;

public class ResultUtil {

    public static void setResult(int status, Exception e){
        Reporter.getCurrentTestResult().setStatus(status);
        Reporter.getCurrentTestResult().setThrowable(e);
    }

    public static void setResultSkip(Exception e){
        Reporter.getCurrentTestResult().setStatus(ITestResult.SKIP);
        Reporter.getCurrentTestResult().setThrowable(e);
    }

    public static void setResultFail(Exception e){
        Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
        Reporter.getCurrentTestResult().setThrowable(e);
    }

    public static void setResultSuccess(){
        Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
    }

}
