package framework.steps;

import framework.config.Settings;
import framework.core.DriverContext;
import framework.core.WebElementHelper;
import framework.toolkit.Cucumber;
import io.cucumber.java.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class CucumberTestInit {

    @Before
    public void Init(Scenario scenario) {
        log.info("Before from MASTER");
        Settings.SCENARIO_NAME = scenario.getName();
        Settings.CUCUMBER_SCENARIO_TAGS = scenario.getSourceTagNames();
        log.info("[SCENRAIO NAME] Runing Scenario: " + Settings.SCENARIO_NAME);
        Cucumber.SCENARIO_RUNNING = scenario;
    }


    @After
    public void AfterScenario(Scenario scenario) {
        //      if (scenario.isFailed()) {
        //Wykluczenie printscreena dla testów API
        if (!Settings.RUNNER_TYPE.equals("API_TESTS")) {
            String currentPath = System.getProperty("user.dir");
            String filePath = currentPath + "/target/Screenshot.png";
            log.info("Scenario is failed getting printscreen");
            boolean screenshotNotAttached = true;
            for (int i = 0; i < 10; i++) {
                try {
                    byte[] screenshotBytes = ((TakesScreenshot) Objects.requireNonNull(DriverContext.getRemoteWebDriver())).getScreenshotAs(OutputType.BYTES);
                    try {
                        scenario.attach(screenshotBytes, "image/png", "Screenshot");
                    }
                    catch ( java.lang.NoSuchMethodError e){
                        log.info("Nie udało się przechwycić printscreena");
                    }
                    saveScreenshot(screenshotBytes, scenario.getName());
                    screenshotNotAttached = false;
                    break;
                } catch (org.openqa.selenium.remote.ScreenshotException e) {
                    log.info("ERROR TakesScreenshot");
                    log.info(e.getMessage());
                }
            }
            String pageSource = Objects.requireNonNull(DriverContext.getRemoteWebDriver()).getPageSource();
            scenario.attach(pageSource.getBytes(StandardCharsets.UTF_8), "plain/text", "pageSource");
        }
        //  }
        log.info("AfterScenario from MASTER");
        if (DriverContext.getRemoteWebDriver() != null) {
        }
    }

    public void saveScreenshot(byte[] screenshotBytes, String scenarioName) {
        // Formatowanie aktualnej daty i czasu do postaci 'YYYY-MM-DD_HH-MM-SS'
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = now.format(formatter);
        String directoryPath = "target/screenshot";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (!isCreated) {
                System.err.println("Failed to create directory for screenshots.");
                return;
            }
        }

        // Tworzenie ścieżki do pliku w katalogu 'target'
        String filePath = directoryPath + "/screenshot_" + scenarioName + "_" + formattedDate + ".png";

        // Zapisywanie zrzutu ekranu do pliku
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(screenshotBytes);
            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error while saving screenshot: " + e.getMessage());
        }
    }

    private byte[] convertFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[(int) file.length()];
            fis.read(byteArray);
            return byteArray;
        }
    }

    @BeforeStep
    public void CucumberBeforeStep(Scenario scenario) {
        log.info("BeforeStep from MASTER");
    }

    @AfterStep
    public void CucumberAfterStep(Scenario scenario) {
        log.info("AfterStep from MASTER");
        if (scenario.isFailed()) {
                    byte[] screenshotBytes = ((TakesScreenshot) Objects.requireNonNull(DriverContext.getRemoteWebDriver())).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Screenshot");
            scenario.attach(WebElementHelper.ReturnPageSource(), "text/xml", "pageSource.xml");
        }
    }
}
