package com.sofkau.stepdefinitions;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.sofkau.tasks.DoGetPokemon.doGetPokemon;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class PokemonStepDefinitions extends ApiSetUp {

    private Logger LOGGER = Logger.getLogger(PokemonStepDefinitions.class);


    @Given("el usuario esta en la PokeApi")
    public void elUsuarioEstaEnLaPokeApi() {
        setUp(BASE_POKE_URL.getValue());
    }

    @When("el usuario hace la peticion con {string}")
    public void elUsuarioHaceLaPeticionCon(String pokemon) {
        try {
            actor.attemptsTo(
                    doGetPokemon().conElRecurso(POKEMON_RESOURCE.getValue())
                            .yConElPokemon(pokemon)
            );
        } catch (Exception e) {
            LOGGER.error("Error making request: " + e.getMessage());
        }
    }

    @Then("se valida que el {int} sea el del pokemon correspondiente")
    public void seValidaQueElSeaElDelPokemonCorrespondiente(Integer id) {
        try {
            Gson gson = new Gson();
            JsonObject element = gson.fromJson(SerenityRest.lastResponse().getBody().asString(), JsonObject.class);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: 200",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("Retorna información",
                            act -> SerenityRest.lastResponse(), notNullValue())
            );
            actor.attemptsTo(
                    Ensure.that(element.get("id").getAsString()).isEqualTo(id.toString()),
                    Ensure.that(SerenityRest.lastResponse().getStatusCode()).isEqualTo(HttpStatus.SC_OK)
            );
            String pokemonId = element.get("id").getAsString();
            String pokemonCode = SerenityRest.lastResponse().getStatusCode() + "";
            String pokemonName = element.get("name").getAsString();
            List<String> typeNames = extractTypeNames(element.getAsJsonArray("types"));
            List<String> abilityNames = extractAbilityNames(element.getAsJsonArray("abilities"));
            LOGGER.info("Pokemon ID: " + pokemonId);
            LOGGER.info("Pokemon Code: " + pokemonCode);
            LOGGER.info("Pokemon Name: " + pokemonName);
            LOGGER.info("Pokemon Types: " + typeNames);
            LOGGER.info("Pokemon Abilities: " + abilityNames);
        } catch (Exception e) {
            LOGGER.error("Error validating response: " + e.getMessage());
        }
    }
    private List<String> extractTypeNames(JsonArray types) {
        List<String> typeNames = new ArrayList<>();
        for (JsonElement typeElement : types) {
            JsonObject typeObj = typeElement.getAsJsonObject();
            JsonObject type = typeObj.get("type").getAsJsonObject();
            String typeName = type.get("name").getAsString();
            typeNames.add(typeName);
        }
        return typeNames;
    }

    private List<String> extractAbilityNames(JsonArray abilities) {
        List<String> abilityNames = new ArrayList<>();
        for (JsonElement abilityElement : abilities) {
            JsonObject abilityObj = abilityElement.getAsJsonObject();
            JsonObject ability = abilityObj.get("ability").getAsJsonObject();
            String abilityName = ability.get("name").getAsString();
            abilityNames.add(abilityName);
        }
        return abilityNames;
    }
}
