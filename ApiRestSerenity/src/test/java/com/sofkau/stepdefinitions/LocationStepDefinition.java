package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.Return_Get.returnLocationSuccessfullJsonResponse;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.RickAndMortyResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;


public class LocationStepDefinition extends ApiSetUp {
    public static final Logger LOGGER = Logger.getLogger(LocationStepDefinition.class);

    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();

    @Given("el usuario esta en la pagina")
    public void elUsuarioEstaEnLaPagina() {
        try {
            setUp(RICK_AND_MORTY_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("el usuario envia solicitud de busqueda  por id {string}")
    public void elUsuarioEnviaSolicitudDeBusquedaPorId(String id) {
        try {
            actor.attemptsTo(doGet().withTheResource(RESOURCE_LOCATION.getValue() + id));
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @Then("la pagina retornara un estatus con codigo {int} nombre {string} tipo {string}")
    public void laPaginaRetornaraUnEstatusConCodigoNombreTipo(Integer code, String name, String type) {
        try {
            Response actualResponse = returnLocationSuccessfullJsonResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, CoreMatchers.notNullValue())

            );
            statusCode(code, name, type);
            LOGGER.info("Datos esperados y actuales correctos");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void statusCode(Integer code, String name, String type) {
        switch (code) {
            case 200:
                actor.should(
                        seeThat("Comparar name",
                                iName -> responseBody.get("name").toString(), equalTo(name)),
                        seeThat("Comparar type",
                                iName -> responseBody.get("type").toString(), equalTo(type)));
                break;
            case 404:
                LOGGER.info("Location no encontrada");
                break;
            case 500:
                LOGGER.info("id enviado es ivalido");
                break;
            default:
                break;
        }
    }


}
