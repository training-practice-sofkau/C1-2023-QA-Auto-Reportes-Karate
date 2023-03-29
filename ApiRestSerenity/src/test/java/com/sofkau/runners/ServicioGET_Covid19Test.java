package com.sofkau.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.sofkau.stepdefinitions"},
        features = {"src/test/resources/features/ServicioGET_Covid19.feature"}
)
public class ServicioGET_Covid19Test {
}
