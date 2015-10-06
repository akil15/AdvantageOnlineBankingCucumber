package com.aob.cucumber;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.hp.lft.sdk.*;
import unittesting.*;
// Cucumber
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
// Glue to the step definition file
// Report result is stored --> C:\Eclipse Kepler Workspace\AdvantageOnlineBankingCucumber\ressources\cucumber-html-report
@CucumberOptions(features="features/loginFeature.feature", glue="com.aob.cucumber", format={"pretty", "html:resources/cucumber-html-report"})
public class LeanFtTest extends UnitTestClassBase {

	@BeforeClass
	public static void beforeClass() throws Exception {
		globalSetup(LeanFtTest.class);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		globalTearDown();
	}

}
 