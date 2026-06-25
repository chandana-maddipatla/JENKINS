package runner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
    key = "cucumber.plugin",
    value = "pretty, json:target/cucumber.json, html:target/cucumber-html/report.html"
)
@ConfigurationParameter(
    key = "cucumber.glue",
    value = "stepdefinitions, hooks"
)
@ConfigurationParameter(
    key = "cucumber.filter.tags",
    value = "not @Ignore"
)
public class TestRunner {
}
