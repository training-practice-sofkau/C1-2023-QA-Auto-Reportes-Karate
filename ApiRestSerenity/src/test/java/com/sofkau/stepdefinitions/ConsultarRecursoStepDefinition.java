package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Consequence;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.questions.ReturnConsultaRecursoSuccessfulJsonResponse.consultaRecursoJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.LISTAR_RECURSO_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
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
        actor.attemptsTo(
                doGet()
                        .withTheResource(LISTAR_RECURSO_SUCCESSFUL_RESOURCE.getValue() + id)
        );
    }

    @Then("debe retornar un codigo de respuesta {int} y name {string}")
    public void debe_retornar_un_codigo_de_respuesta_y_name(Integer codigoStat, String name) {
        try {
            Response actuallResponse = consultaRecursoJsonResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actuallResponse.getBody().asString());
            actor.should(
                    (Consequence) seeThatResponse("****** El codigo de respuesta de la peticion seria --> " + codigoStat,
                            response -> response.statusCode(codigoStat)),
                    seeThat("Retorna información",
                            act -> actuallResponse, notNullValue()),
                    seeThat("Retorna información",
                            nomb -> responseBody.get("name").toString(), equalTo(name))

            );
            LOGGER.info("Respuesta status code: " + codigoStat);
        } catch (Exception e) {
            LOGGER.error("****** Se tiene el siguiente error -->: ", e);
        }

    }


}


