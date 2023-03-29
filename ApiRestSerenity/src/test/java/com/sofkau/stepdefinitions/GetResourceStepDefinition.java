package com.sofkau.stepdefinitions;


import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static com.sofkau.utils.ReqresResources.RESOURCE;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class GetResourceStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(GetResourceStepDefinition.class.getName());

    @Given("the ReqRes API is available")
    public void theReqResAPIIsAvailable() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + REQRES_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatizacion : Detalles: "+ e.getMessage());
        }
    }

    @When("I make a GET request to retrieve the {string} list")
    public void iMakeAGETRequestToRetrieveTheList(String resource) {
        try {
            LOGGER.info("Realizando peticion GET...");

            actor.attemptsTo(
                    doGet().withTheResource(RESOURCE.getValue() + resource)
            );

        } catch (Exception e) {

            LOGGER.error("Error durante GET request: " + e.getMessage());
        }

    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer code) {

        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + code,
                            response -> response.statusCode(code))
            );

            LOGGER.info("Respuesta status code: " + code);

        } catch (Exception e) {
            LOGGER.error("Error: ", e);
            throw e;
        }

    }

    @Then("the response body should contain a list of {string}")
    public void theResponseBodyShouldContainAListOf(String resource) {

        try {

            int statusCode = lastResponse().getStatusCode();;

            // Obtener la respuesta como un String
            String responseBody = lastResponse().asString();
            System.out.println(responseBody);

            if (statusCode == HttpStatus.SC_OK) {

                // Parsear la respuesta a un objeto JsonPath
                JsonPath jsonPath = new JsonPath(responseBody);

                // Verificar que la respuesta contenga la lista de recursos
                List<?> resources = jsonPath.get("data");
                Assert.assertTrue(resources.size() > 0);

                // Verificar la estructura de la respuesta
                Assert.assertEquals(1, jsonPath.getInt("page"));
                Assert.assertEquals(6, jsonPath.getInt("per_page"));
                Assert.assertEquals(12, jsonPath.getInt("total"));
                Assert.assertEquals(2, jsonPath.getInt("total_pages"));
                Assert.assertEquals(1, jsonPath.getInt("data[0].id"));

            } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                LOGGER.info("Not found, status code: " + HttpStatus.SC_NOT_FOUND);
            } else {
                LOGGER.info("Unexpected status code");
            }

        } catch (Exception e) {
            LOGGER.error("Error: ", e);
            throw e;
        }

    }



}
