package cucumber_options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith (Cucumber.class)
@CucumberOptions(features ="C:\\Users\\vigne\\eclipse-workspace\\Rest_Assured_Cucumber\\src\\test\\java\\features\\AddPlace.feature",
plugin = "json:target/jsonReports/cucumber-report.json", glue= {"stepdefinitions"},
dryRun = ! true,monochrome = false)

public class TestRunner {

}  

