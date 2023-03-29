package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.RespuestaPeticion.respuestaPeticion;
import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.JsonPlaceHolderResources.JSONPLACE_BASE_URL;
import static com.sofkau.utils.JsonPlaceHolderResources.POSTS_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BorrarPublicacionStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(BorrarPublicacionStepDefinition.class);

    @Given("El usuario se encuentra en la web de JsonPlaceHolder para borrar un post")
    public void elUsuarioSeEncuentraEnLaWebDeJsonPlaceHolderParaBorrarUnPost() {
        try{
            setUp(JSONPLACE_BASE_URL.getValue());
            LOGGER.info("Inicia la automatizacion");
        } catch (Exception e){
            LOGGER.info("Falla la automatizacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("El usuario envia una solicitud del post que se desea borrar con el {string}")
    public void elUsuarioEnviaUnaSolicitudDelPostQueSeDeseaBorrarConEl(String id) {
        try {
            actor.attemptsTo(
                    doDelete()
                            .withTheResource(POSTS_RESOURCE.getValue()+"/"+id)
            );
            LOGGER.info("Se realiza la peticion");
        } catch (Exception e){
            LOGGER.info("Peticion fallida");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @Then("El usuario debe recibir un respuesta de status {int}")
    public void elUsuarioDebeRecibirUnRespuestaDeStatus(Integer codeStatus) {
        try {
            Response actualResponse = respuestaPeticion().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(codeStatus)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
                    );
            LOGGER.info("Asercion exitosa");
        }catch (Exception e){
            LOGGER.info("Asercion fallida");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
