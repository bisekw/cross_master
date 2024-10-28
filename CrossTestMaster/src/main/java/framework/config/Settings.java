package framework.config;

import framework.core.DriverType;

import java.util.Collection;

public class Settings {

    public static String GRID_APPIUM_ANDROID;
    public static String TESTED_APP_URL;
    public static boolean IS_REMOTE_RUN;
    public static DriverType DRIVER_TYPE;
    public static String GRID_URL ="http://localhost:4444/wd/hub";
    public static String GRID_LOCAL = "http://localhost:4444/wd/hub";
    public static String GRID_REMOTE = "http://localhost:4444/wd/hub";
    public static String GRID_APPIUM_IOS;
    public static String SCENARIO_NAME;
    public static String RUNNER_TYPE;
    public static String CUCUMBER_RUNNER_TAG;
    public static Collection<String> CUCUMBER_SCENARIO_TAGS;

    public static String RP_ENDPOINT;
    public static String RP_USERNAME;
    public static String RP_UUID;
    public static String RP_LAUNCH;
    public static String RP_PROJECT;
    public static String RP_ATTRIBUTES;


}
