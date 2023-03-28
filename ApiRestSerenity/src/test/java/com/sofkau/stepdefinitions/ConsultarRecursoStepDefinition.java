package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnConsultaRecursoSuccessfulJsonResponse.consultaRecursoJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.LISTAR_RECURSO_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ConsultarRecursoStepDefinition extends ApiSetUp {
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
       // System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("el usuario debe observar un codigo de respuesta {int} y la la informacion del recurso")
    public void elUsuarioDebeObservarUnCodigoDeRespuestaYLaLaInformacionDelRecurso(Integer codigoStat) {
        try {
            Response actuallResponse = consultaRecursoJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("****** El codigo de respuesta de la peticion seria --> " + codigoStat,
                            response -> response.statusCode(codigoStat)),
                    seeThat("Retorna información",
                            act -> actuallResponse, notNullValue())
            );
            LOGGER.info("Respuesta status code: " + codigoStat);
        } catch (Exception e) {
            LOGGER.error("****** Se tiene el siguiente error -->: ", e);
            throw e;
        }

    }
}

