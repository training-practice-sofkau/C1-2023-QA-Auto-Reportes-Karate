package com.sofkau.stepdefinitions;

import com.sofkau.models.UsuarioResponse;
import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnQuestionUsuario.returnQuestionUsuario;
import static com.sofkau.tasks.DoGetSimpleUsuario.doGetSimpleUsuario;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static com.sofkau.utils.ReqresResources.RESOURCE;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetSimpleUsuarioStepDefinition extends ApiSetUp {


    private UsuarioResponse usuarioResponse = new UsuarioResponse();

    public static Logger LOGGER = Logger.getLogger(GetSimpleUsuarioStepDefinition.class);
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;


    @Given("que el usuario se encuentra en la página obtener usuario")
    public void queElUsuarioSeEncuentraEnLaPáginaObtenerUsuario() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }


    }


    @When("el usuario envía una solicitud con el {int} del usuario deseado")
    public void elUsuarioEnvíaUnaSolicitudConElDelUsuarioDeseado(Integer id) {
        try {
            actor.attemptsTo(
                    doGetSimpleUsuario()
                            .withTheResource(RESOURCE.getValue() + id)
            );
            LOGGER.info("Realiza la peticion");
            System.out.println(SerenityRest.lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("el usuario  recibe un estatus {int} con usuario encontrado")
    public void elUsuarioRecibeUnEstatusConUsuarioEncontrado(Integer code) {

        try {

            if (code == 200) {
                UsuarioResponse actualResponse = returnQuestionUsuario().answeredBy(actor);
                LOGGER.info("respuesta de la api-->" + actualResponse.toString());

                actor.should(

                        // Validar el código de estado HTTP con Serenity BDD
                        seeThatResponse("El codigo de respuesta es: " + code,
                                response -> response.statusCode(code))

                );

                responseBody = (JSONObject) parser.parse(lastResponse().asString());
                LOGGER.info("respuesta de la api 22-->" + actualResponse.toString());
                ModeloRespuesta(actualResponse);
            }
            if (code == 400) {
                System.out.println("no hay respuesta");
            }

            LOGGER.info("CUMPLE");
        } catch (Exception e) {

            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    private void ModeloRespuesta(UsuarioResponse actualResponse) {
        String responseBody2 = lastResponse().asString();
        JsonPath jsonPath = new JsonPath(responseBody2);
        JSONObject data = (JSONObject) responseBody.get("data");
        String email = (String) data.get("email");
        String first_name = (String) data.get("first_name");
        String last_name = (String) data.get("last_name");
        Assertions.assertEquals(email, jsonPath.getString("data.email"));
        Assertions.assertEquals(first_name, jsonPath.getString("data.first_name"));
        Assertions.assertEquals(last_name, jsonPath.getString("data.last_name"));


    }


}
