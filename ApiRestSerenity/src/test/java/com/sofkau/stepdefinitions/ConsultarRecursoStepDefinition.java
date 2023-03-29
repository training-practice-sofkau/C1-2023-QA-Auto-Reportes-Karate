package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.questions.ReturnConsultaRecursoSuccessfulJsonResponse.consultaRecursoJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.LISTAR_RECURSO_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import static org.hamcrest.core.IsEqual.equalTo;

public class ConsultarRecursoStepDefinition extends ApiSetUp {
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;
    private static final Logger LOGGER = Logger.getLogger(ConsultarRecursoStepDefinition.class.getName());


    @Given("que el usuario esta en la pagina de consulta de recursos")
    public void queElUsuarioEstaEnLaPaginaDeConsultaDeRecursos() {
        setUp(REQRES_BASE_URL.getValue());
    }


    @When("el usuario envia una peticion get con el {string} del recurso")
    public void elUsuarioEnviaUnaPeticionGetConElDelRecurso(String id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(LISTAR_RECURSO_SUCCESSFUL_RESOURCE.getValue() + id)
            );
            LOGGER.info("Peticion enviada ");
        }catch (Exception e){
            LOGGER.error("****** Se tiene el siguiente error -->: ", e);
            throw e;
        }
    }

    @Then("debe retornar un codigo de respuesta {int} y name {string}")
    public void debe_retornar_un_codigo_de_respuesta_y_name(Integer codigoStat, String name) {

        try {
            if(codigoStat ==200) {
                Response actuallResponse = consultaRecursoJsonResponse().answeredBy(actor);
                responseBody = (JSONObject) parser.parse(actuallResponse.getBody().asString());
                JSONObject data = (JSONObject) responseBody.get("data.name");

                actor.should(
                        seeThatResponse("****** El codigo de respuesta de la peticion seria --> " + codigoStat,
                                response -> response.statusCode(codigoStat))
                );
                actor.should(
                        seeThatResponse("****** El codigo de respuesta de la peticion seria --> " + name,
                                response -> response.body("data.name", equalTo(name)))
                );
            }
            if (codigoStat == 400){
                LOGGER.info("Peticion no valida");
            }
            LOGGER.info("Respuesta status code: " + codigoStat);
            LOGGER.info("Respuesta nombre: " + name);

        } catch (Exception e) {
            LOGGER.error("****** Se tiene el siguiente error -->: ", e);
        }

    }

}


