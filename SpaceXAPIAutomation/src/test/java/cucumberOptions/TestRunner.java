package cucumberOptions;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features"
	,tags = {"@GetCapsules"}
	,plugin = {"pretty","html:target/cucumber-reports","json:target/cucumber.json"}
	,monochrome=true,glue={"stepDefination"})
public class TestRunner {
			
}
