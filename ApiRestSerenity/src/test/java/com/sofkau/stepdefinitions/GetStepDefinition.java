package com.sofkau.stepdefinitions;


import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.google.common.base.Predicates.equalTo;
import static com.sofkau.questions.ReturnGetJsonResponse.returnGetJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static com.sofkau.utils.ReqresResources.RESOURCE_GET;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class GetStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(GetStepDefinition.class));
    public static Response respuesta;
    JSONParser parser = new JSONParser();
    String primerNombre;
    String segundoNombre;
    String nombre;
    int codRespuesta;
    JSONObject bodyResponse = null;

    @Given("access to the Reqres URL")
    public void access_to_the_Reqres_URL() {
        setUp(REQRES_BASE_URL.getValue());
        LOGGER.info("Inicio de la automatizacion");
    }


    @When("a method GET is performed")
    public void a_method_GET_is_performed() {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_GET.getValue())
            );
        } catch (Exception e) {
            LOGGER.info("Falla en el step: a method GET is performed" + e.getMessage());

        }
    }

    @Then("status {int}")
    public void status(Integer codigo) {
        try {
            codRespuesta = codigo;
            respuesta = returnGetJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + respuesta.getStatusCode(),
                            code -> code.statusCode(codigo))
            );
        } catch (Exception e) {
            LOGGER.info("Fallo en el step: Codigo de respuesta no es el esperado" + e.getMessage());
            Assertions.fail();
        }
    }

    @Then("I can see the users list")
    public void i_can_see_the_users_list() {

        if (codRespuesta == 200) {
            try {

                LOGGER.info("Se revisa la respuesta de los codigos 200");
                bodyResponse = (JSONObject) parser.parse(respuesta.getBody().asString());

                primerNombre = bodyResponse.get("first_name").toString();
                segundoNombre = bodyResponse.get("last_name").toString();
                nombre = primerNombre + " " + segundoNombre;
                actor.should(
                        seeThat("Retorna informacion",
                                info -> respuesta, notNullValue())


                );
            } catch (Exception e) {
                LOGGER.info("Falla en el step: I can see the users list" + e.getMessage());
                Assertions.fail();
            }
        } else if (codRespuesta == 404) {
            try {
                LOGGER.info("Se revisa la respuesta de los codigos 400");
                actor.should(
                        seeThat("El cuerpo de la respuesta no está vacío",
                                info -> respuesta, notNullValue())
                );
                LOGGER.info("Terminan los steps");

            } catch (Exception e) {

                LOGGER.info("Falla en el step: I can see the users list" + e.getMessage());
                Assertions.fail();
            }
        }

    }

}
