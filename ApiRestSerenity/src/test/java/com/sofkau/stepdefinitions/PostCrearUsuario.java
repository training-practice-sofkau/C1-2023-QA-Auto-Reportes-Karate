package com.sofkau.stepdefinitions;

import com.sofkau.models.PeticionCrearUsuario;
import com.sofkau.models.ResponseCrearUsuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.json.simple.parser.ParseException;

import static com.sofkau.questions.ReturnCrearUsuario.returnCrearUsuario;
import static com.sofkau.tasks.DoPostCrearUsuario.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class PostCrearUsuario extends ApiSetUp {

    private ResponseCrearUsuario responseCrearUsuario = new ResponseCrearUsuario();

    private PeticionCrearUsuario peticionCrearUsuario = new PeticionCrearUsuario();

    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;
    private Response response;
    public static Logger LOGGER = Logger.getLogger(PostCrearUsuario.class);


    @Given("que el usuario se encuentra en la página crear usuario")
    public void queElUsuarioSeEncuentraEnLaPáginaCrearUsuario() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("el usuario envia la peticion de crea un nuevo usuario con {string}, {string}")
    public void elUsuarioEnviaLaPeticionDeCreaUnNuevoUsuarioCon(String name, String job) {

        setVariables(name, job);

        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_CREATE.getValue())
                            .andTheRequestBody(peticionCrearUsuario)
            );
            LOGGER.info("Realiza la peticion");
            System.out.println(lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @Then("el usuario debria ver un mensaje con informacion del nuevoo usuario creado con un estatus {int}")
    public void elUsuarioDebriaVerUnMensajeConInformacionDelNuevooUsuarioCreadoConUnEstatus(Integer code) {
        try {
            // Obtener la respuesta del servidor con Serenity BDD
            ResponseCrearUsuario actualResponse = returnCrearUsuario().answeredBy(actor);

            actor.should(
                    // Validar el código de estado HTTP con Serenity BDD
                    seeThatResponse("El codigo de respuesta es: " + code,
                            response -> response.statusCode(code)),
                    // Validar que la respuesta tenga información con Serenity BDD
                    seeThat("Retorna información",
                            act -> actualResponse.getJob(), notNullValue())
            );
            responseBody = (JSONObject) parser.parse(lastResponse().asString());
            ModeloRespuesta(actualResponse);

        } catch (AssertionError e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("La validación de la respuesta del servidor ha fallado.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void ModeloRespuesta(ResponseCrearUsuario actualResponse) {
        String name = (String) responseBody.get("name");
        String job = (String) responseBody.get("job");
        Assertions.assertEquals(name, actualResponse.getName());
        Assertions.assertEquals(job, actualResponse.getJob());
    }


    private void setVariables(String name, String job) {
        peticionCrearUsuario.setJob(name);
        peticionCrearUsuario.setName(job);
    }


}
