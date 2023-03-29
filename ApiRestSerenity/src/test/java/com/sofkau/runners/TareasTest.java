package com.sofkau.runners;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import static com.sofkau.utils.ReqresResources.JSONPLACEHOLDER_BASE_URL;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.sofkau.stepdefinitions"},
        features = {"src/test/resources/features/newtarea.feature"}
)
public class TareasTest {
}
