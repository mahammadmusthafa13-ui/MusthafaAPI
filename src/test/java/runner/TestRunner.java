package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/Features",glue="stepDefination",monochrome=true,
plugin={"pretty", "json:target/jsonReports/cucumber.json",
        "html:target/htmlReports/cucumber-report.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}, tags="")

public class TestRunner extends AbstractTestNGCucumberTests
{

}
