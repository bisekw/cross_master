package framework.steps;

import framework.config.Settings;
import framework.core.DriverType;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterSteps {

    private ScenarioContext scenarioContext;

    public MasterSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    @When("User is setting up the configuration for API testing.")
    public void configRestAssured() {
        Settings.DRIVER_TYPE = DriverType.API;
        Settings.RUNNER_TYPE = "API_TESTS";
        log.info("Runner =" + Settings.RUNNER_TYPE);
    }




}
