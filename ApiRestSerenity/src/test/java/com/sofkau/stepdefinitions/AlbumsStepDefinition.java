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

import static com.sofkau.questions.Return_Delete.returnAlbumSuccessfullJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.AlbumsResources.ALBUMS_BASE_URL;
import static com.sofkau.utils.AlbumsResources.RESOURCE_ALBUM;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;


public class AlbumsStepDefinition extends ApiSetUp {
    public static final Logger LOGGER = Logger.getLogger(AlbumsStepDefinition.class);

    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();

    @Given("el administrador esta en la pagina de albums")
    public void elAdministradorEstaEnLaPaginaDeAlbums() {
        try {
            setUp(ALBUMS_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info("fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("el usuario envia solicitud para eliminar albums  por id {string}")
    public void elUsuarioEnviaSolicitudParaEliminarAlbumsPorId(String id) {
        try {
            actor.attemptsTo(doGet().withTheResource(RESOURCE_ALBUM.getValue() + id));
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();

        }
    }

    @Then("la pagina retornara un estatus con codigo {int}")
    public void laPaginaRetornaraUnEstatusConCodigo(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code))
            );
            switch (code) {
               case 200:
                    LOGGER.info("Albúm eliminado");
                    break;
                case 404:
                    LOGGER.info("Id invalido");
                    break;
                default:
                    break;
            }
            LOGGER.info("Datos esperados y actuales correctos");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
