package framework.core;

import com.google.common.collect.ImmutableMap;
import framework.config.Settings;
import framework.toolkit.Cucumber;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

@Slf4j
public class WebElementHelper {




    public static void MarkScenarioAsFail(String text) {
        Cucumber.SCENARIO_RUNNING.log(text);
        Assert.fail(text);
    }

    public static void MarkScenarioAsFail(String text,boolean attachPageSource) {
        Cucumber.SCENARIO_RUNNING.log(text);
        if(attachPageSource){
            Cucumber.SCENARIO_RUNNING.attach(ReturnPageSource(),"text/xml", "pageSource.xml");
        }
        Assert.fail(text);
    }



       public static String ReturnPageSource() {
        String pageSource = Objects.requireNonNull(DriverContext.getRemoteWebDriver()).getPageSource();
        return pageSource;
    }



}


