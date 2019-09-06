package framework;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import java.lang.reflect.Method;

import org.junit.runner.RunWith;
import org.testng.ITestResult;
import org.testng.annotations.*;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/java/tests/cucumberTests/features/FleetManagmentService.feature",
        glue = {"cucumberIntegrationTests/stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber"}

)
public class CucumberRunnerUtil extends Hooks{

    private TestNGCucumberRunner testNGCucumberRunner;
    
    @BeforeMethod
	public void beforeMethod(Method testName) {
		startTest(testName.getName());
	}

    @BeforeSuite(alwaysRun = true)
    public void setCreateSession() throws Exception {
    	beforeTest();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @BeforeMethod(alwaysRun = true)
    public void driverObjectCreation() throws Exception{
    	
    }


    @Test(groups = "cucumber", description = "Runs Cucumber Feature",dataProvider = "features" )
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterMethod
	public void afterMethod(ITestResult result,Method testName)
	{
		getResult(result,testName.getName());
	}

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

    @AfterSuite
    public void cleanUp() throws Exception{
    	afterTest();
    }


}
