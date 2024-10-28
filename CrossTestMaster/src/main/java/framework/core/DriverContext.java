package framework.core;

import framework.config.Settings;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class DriverContext {

    private static final ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<AndroidDriver> remoteAndroidDriverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<IOSDriver> remoteIOSDriverThreadLocal = new ThreadLocal<>();


    static void setRemoteWebDriverThreadLocal(RemoteWebDriver driverThreadLocal) {
        remoteWebDriverThreadLocal.set(driverThreadLocal);
    }

    public static RemoteWebDriver getRemoteWebDriver() {
        DriverType driverType = Settings.DRIVER_TYPE;

        switch (driverType) {
            case API:
                log.info("For API Test driver not initialized");
                return null;

            default:
                throw new IllegalArgumentException("Unsupported driver type: " + driverType);
        }
    }

}
