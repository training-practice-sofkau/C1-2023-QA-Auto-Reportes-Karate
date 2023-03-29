package com.sofkau.stepdefinitions;

import com.sofkau.models.ComentarioInformacion;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import static com.sofkau.questions.ReturnCreationValidComentary.returnCreationValidComentary;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.assertions.assertj.WebElementStateAssert.assertThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;

public class AgregarComentarioStepDefinitions extends ApiSetUp {
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    public static org.apache.log4j.Logger LOGGER = Logger.getLogger(String.valueOf(AgregarComentarioStepDefinitions.class));
    ComentarioInformacion comentarioInformacion = new ComentarioInformacion();
    @Given("que tengo acceso a la API")
    public void queTengoAccesoALaAPI() {
        try{
            setUp(CREAR_COMENTARIO_BASE_URL.getValue());
            LOGGER.info("inicio de la automatizacion " );

        } catch (Exception e){
            LOGGER.info("se ha producido un error" + e);
        }

    }

    @When("hago una peticion de tipo POST para crear un nuevo comentario especificando {string} , {string} y {string}")
    public void hagoUnaPeticionDeTipoPOSTParaCrearUnNuevoComentarioEspecificandoY(String body, String postId, String userId) {
    comentarioInformacion.setBody(body);
    comentarioInformacion.setPostId(postId);
    comentarioInformacion.setUserId(userId);
    actor.attemptsTo(
            doPost().withTheResource(RESOURCE_COMENTARIO_URL.getValue()).andTheRequestBody(comentarioInformacion)
    );


    }

    @Then("debe obtener un codigo de estado {int} y el JSON de creacion  correcto")
    public void debeObtenerUnCodigoDeEstadoYElJSONDeCreacionCorrecto(Integer expectedStatusCode) {
        try {
            Response actualResponse = returnCreationValidComentary().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());

            SerenityRest.lastResponse().then().statusCode(expectedStatusCode);
            LOGGER.info("//////Se ha validado correctamente el codigo de estado " + expectedStatusCode+"///////");

            if (expectedStatusCode == 404) {
                LOGGER.info("Validando que la respuesta contenga el texto 'not found'");
                SerenityRest.lastResponse().then().assertThat().body("message", containsString("not found"));
            } else {
                SerenityRest.lastResponse().then().assertThat().body("user", hasKey("id"));
                SerenityRest.lastResponse().then().assertThat().body("user", hasKey("username"));
                LOGGER.info("Validacion 1 del JSON cumplida!");

                actor.should(
                        seeThat("El campo 'id' no es nulo", response -> responseBody.get("id"), notNullValue()),
                        seeThat("El campo 'body' no es nulo", response -> responseBody.get("body"), notNullValue()),
                        seeThat("El campo 'postId' no es nulo", response -> responseBody.get("postId"), notNullValue()),
                        seeThat("El campo 'user' no es nulo", response -> responseBody.get("user"), notNullValue())
                );
                LOGGER.info("Validacion 2 del JSON cumplida!");
            }

        } catch (Exception e) {
            LOGGER.error("Ocurrió un error  " + e.getMessage());
            Assert.fail("Ocurrió un error  " + e.getMessage());
        }

    }



}
