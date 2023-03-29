package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import static com.sofkau.questions.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.json.simple.JSONArray;

public class ServicioGET_Covid19StepDefinition extends ApiSetUp {
    private JSONArray responseArray;
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    public static org.apache.log4j.Logger LOGGER= Logger.getLogger(ServicioGET_Covid19StepDefinition.class);
    private Response response;
    @Given("el cliente esta configurado para conectarse a la API de CovidTracking")
    public void elClienteEstaConfiguradoParaConectarseALaAPIDeCovidTracking() {
        try {
            setUp(COVIDTRACKING_BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @When("el cliente realiza una solicitud GET para New York para la fecha {int}")
    public void elClienteRealizaUnaSolicitudGETParaNewYorkParaLaFecha(Integer date) {
        try{
            actor.attemptsTo(
                    doGet().withTheResource(COVIDTRACKING_GET_RESOURCE.getValue())
            );
            System.out.println(lastResponse().body().asString());

        } catch (Exception e){
            LOGGER.info("ocurrio un error haciendo la peticion" + e);
}
        String responseAsString = lastResponse().body().asString();
        try {
            responseArray = (JSONArray) parser.parse(responseAsString);
        } catch (Exception e) {
            LOGGER.warn("Error al convertir la respuesta en JSONArray: " + e.getMessage());
        }

    }

    @Then("se debe recibir una respuesta con codigo de estado {int}")
    public void seDebeRecibirUnaRespuestaConCodigoDeEstado(Integer code) {

        try {
            Response actualResponse = returnGetResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Se a validado correctamente el codigo de estado:"+ code);
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
        }

    @Then("se debe validar el incremento de muertes, totalTestsPeopleAntibody, positiveTestsPeopleAntibody y totalTestResults")
    public void validarCampos() {
        JSONObject data = (JSONObject) responseArray.get(0);
        int deathIncrease = ((Number) data.get("deathIncrease")).intValue();
        Object totalTestsPeopleAntibody = data.get("totalTestsPeopleAntibody");
        Object positiveTestsPeopleAntibody = data.get("positiveTestsPeopleAntibody");
        int totalTestResults = ((Number) data.get("totalTestResults")).intValue();

        Assert.assertEquals("El incremento de muertes debe ser 59", 59, deathIncrease);
        Assert.assertNull("totalTestsPeopleAntibody debe ser null", totalTestsPeopleAntibody);
        Assert.assertNull("positiveTestsPeopleAntibody debe ser null", positiveTestsPeopleAntibody);
        Assert.assertTrue("totalTestResults debe ser mayor que 0", totalTestResults > 0);
    }
    }

