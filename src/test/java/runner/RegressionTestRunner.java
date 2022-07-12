package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" },
                          glue = { "stepdefs" },
                          tags = "@regressiontest",
                          monochrome = true ,
                          plugin = { "pretty" })
			
public class RegressionTestRunner extends AbstractTestNGCucumberTests {

	

}



