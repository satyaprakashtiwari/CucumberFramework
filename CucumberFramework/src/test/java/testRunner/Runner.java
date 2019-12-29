package testRunner;

import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features//Customers.feature",
		//features=".//Features",//execute all feature file in folder
		//features= {".//Features//Customers.feature",".//Features//Login.feature"},//running specific feature file
		glue="stepDefinitions",
		dryRun=false,
		monochrome=true,
		plugin= {"pretty","html:test-output"},
		tags= {"@Sanity"}
		//tags= {"@Sanity,@Regression"}// will run both sanity scenario and regression scenario
		//tags= {"@Sanity","@Regression"}// will run scenario having both sanity and regression tags
		)
public class Runner {

}
