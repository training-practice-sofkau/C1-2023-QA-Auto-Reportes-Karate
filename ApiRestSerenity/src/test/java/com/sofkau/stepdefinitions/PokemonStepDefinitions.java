package com.sofkau.stepdefinitions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.tasks.DoGetPokemon.doGetPokemon;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PokemonStepDefinitions extends ApiSetUp {

    private Logger LOGGER = Logger.getLogger(PokemonStepDefinitions.class);

    @Given("el usuario esta en la PokeApi")
    public void elUsuarioEstaEnLaPokeApi() {
        try {
        setUp(BASE_POKE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("el usuario hace la peticion con {string}")
    public void elUsuarioHaceLaPeticionCon(String pokemon) {
        try {
            actor.attemptsTo(
                    doGetPokemon().conElRecurso(POKEMON_RESOURCE.getValue())
                            .yConElPokemon(pokemon)
            );
        } catch (Exception e) {
            LOGGER.error(" Error al realizar la solicitud: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Then("se valida que el {} y el {} sean correctos")
    public void seValidaQueElYElSeanCorrectos(Integer id, Integer status_code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + status_code,
                            response -> response.statusCode(status_code))
            );
            if (status_code != 404) {
                Gson gson = new Gson();
                JsonObject element = gson.fromJson(SerenityRest.lastResponse().getBody().asString(), JsonObject.class);
                actor.attemptsTo(
                        Ensure.that(element.get("id").getAsString()).isEqualTo(id.toString())
                );
                String pokemonId = element.get("id").getAsString();
                String pokemonName = element.get("name").getAsString();
                LOGGER.info("Pokemon ID: " + pokemonId);
                LOGGER.info("Pokemon Name: " + pokemonName);
            }
            actor.attemptsTo(
                    Ensure.that(SerenityRest.lastResponse().getStatusCode()).isEqualTo(status_code)
            );
            String statusCode = SerenityRest.lastResponse().getStatusCode() + "";
            LOGGER.info("Status Code: " + statusCode);
        } catch (Exception e) {
            LOGGER.error("Error validando la respuesta: " + e.getMessage());
            Assertions.fail();
        }
    }
}
