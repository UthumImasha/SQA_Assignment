package com.automationexercise.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) {
        try {
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path dir = Path.of("screenshots");
            Files.createDirectories(dir);
            Path target = dir.resolve(testName + "_" + stamp + ".png");
            File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(shot.toPath(), target);
            System.out.println("Screenshot saved: " + target);
            return target.toString();
        } catch (Exception e) {
            System.err.println("Could not capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
