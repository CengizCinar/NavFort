package com.navfort.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber/cucumber.json",
                "junit:target/junit/junit-report.xml"
        },
        features = "src/test/resources/features",
        glue = "com/navfort/step_definitions",
        dryRun = false,
        tags = "@PERF-1461",
        publish = false
)
public class CukesRunner {
}
