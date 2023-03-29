package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.rest.interactions.Ensure;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;

import static com.sofkau.questions.RetornarJsonCovid.retornarJsonCovid;
import static com.sofkau.tasks.RealizarUnGet.realizarUnGet;
import static com.sofkau.utils.CovidTest.COVID_BASE_URL;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.LastResponse.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;


public class CovidPporDiaStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(CovidPporDiaStepDefinition.class);
    @Given("el usuario esta en la API")
    public void el_usuario_esta_en_la_API() {

        try {
            setUp(COVID_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("se hace peticion get con {string}")
    public void se_hace_peticion_get_con(String fecha) {
        try {
            actor.attemptsTo(
                    realizarUnGet().conElRecurso(fecha)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibe {int} y la informacion de la fecha")
    public void recibeYLaInformacionDeLaFecha(Integer status) {
        try {
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + status,
                            response -> retornarJsonCovid().equals(status))
            );

            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
