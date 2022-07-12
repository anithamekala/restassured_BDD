package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class RealTestRunner {
	@CucumberOptions(features = { "src/test/resources/features" },
            glue = { "stepdefs" },
            tags = "@realtest",
            monochrome = true ,
            plugin = { "pretty" })

public class SmokeTestRunner extends AbstractTestNGCucumberTests {
	}
}
