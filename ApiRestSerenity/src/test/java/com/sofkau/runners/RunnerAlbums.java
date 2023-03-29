package com.sofkau.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.sofkau.stepdefinitions"},
        features = {"src/test/resources/features/albums.feature"},
        tags = {"not  @Get"}

)
public class RunnerAlbums {
}
