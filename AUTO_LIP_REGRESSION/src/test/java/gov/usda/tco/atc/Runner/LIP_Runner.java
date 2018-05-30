package gov.usda.tco.atc.Runner;
import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions(features = { "./Features/" },
				plugin = {"pretty","html:target/cucumber"},
				glue = { "gov.usda.tco.atc.glue" },	monochrome = true)
@Test
public class LIP_Runner extends AbstractTestNGCucumberTests {
	
	
}
