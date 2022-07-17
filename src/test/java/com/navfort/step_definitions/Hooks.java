package com.navfort.step_definitions;

import com.google.common.io.Files;
import com.navfort.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks {

    private static int id = 1;

    @After
    public void tearDownScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File camera = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            Files.move(camera, new File("screenshots/" + id + "_" + scenario.getName() + ".png"));
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", (id++) + scenario.getName());
        }

        Driver.closeDriver();
    }
}
