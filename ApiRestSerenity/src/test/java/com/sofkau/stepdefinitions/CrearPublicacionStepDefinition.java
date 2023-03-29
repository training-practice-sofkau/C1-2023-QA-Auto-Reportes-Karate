package com.sofkau.stepdefinitions;


import com.sofkau.models.Publicacion;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.RespuestaPeticion.respuestaPeticion;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.JsonPlaceHolderResources.JSONPLACE_BASE_URL;
import static com.sofkau.utils.JsonPlaceHolderResources.POSTS_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CrearPublicacionStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(CrearPublicacionStepDefinition.class);
    private Publicacion publicacion= new Publicacion();
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();


    @Given("El usuario se encuentra en la web de JsonPlaceHolder para crear un post")
    public void elUsuarioSeEncuentraEnLaWebDeJsonPlaceHolderParaCrearUnPost() {
        try{
         setUp(JSONPLACE_BASE_URL.getValue());
         LOGGER.info("Inicia la automatizacion");
        } catch (Exception e){
            LOGGER.info("Falla la automatizacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("El usuario envia una solicitud del post que se desea crear con {string} el {string} y el {string}")
    public void elUsuarioEnviaUnaSolicitudDelPostQueSeDeseaCrearConElYEl(String title, String userId, String body) {
        try {
            setValuesPublicacion(title, body, userId);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(POSTS_RESOURCE.getValue())
                            .andTheRequestBody(publicacion)
            );
            LOGGER.info("Se realiza la peticion");
        } catch (Exception e){
            LOGGER.info("Peticion fallida");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @Then("El usuario debe recibir un respuesta de status {int} y el post")
    public void elUsuarioDebeRecibirUnRespuestaDeStatusYElPost(Integer codeStatus) throws ParseException {
        try {
            Response actualResponse = respuestaPeticion().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(codeStatus)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar titulos",
                            info -> responseBody.get("title").toString(), equalTo(publicacion.getTitle())
                    )
            );
            LOGGER.info("Asercion exitosa");
        }catch (Exception e){
            LOGGER.info("Asercion fallida");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    public void setValuesPublicacion(String title, String body, String userId){
        publicacion.setTitle(title);
        publicacion.setBody(body);
        publicacion.setUserId(userId);
    }
}
