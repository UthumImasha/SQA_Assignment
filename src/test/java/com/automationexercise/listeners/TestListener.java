package com.automationexercise.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationexercise.base.BaseTest;
import com.automationexercise.utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (instance instanceof BaseTest test && test.getDriver() != null) {
            ScreenshotUtil.capture(test.getDriver(), result.getName());
        }
    }
}
