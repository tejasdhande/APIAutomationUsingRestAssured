package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/features", 
glue = {"stepDefinations"},
plugin = {"pretty", "json:target/jsonReports/cucumber-report.json"},
monochrome = true)


//tags = "@DeletePlace"
//plugin = {"pretty","html:target/cucumber.html"})

public class TestRunner extends AbstractTestNGCucumberTests {

}
