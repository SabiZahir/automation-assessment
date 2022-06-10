package com.labcorp.e2e.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/labcorp/e2e/features/LabcorpCareerApply.feature",
        glue = {   "com/labcorp/e2e/step_definitions"},
        dryRun = false

)
public class RunnerUI {
}