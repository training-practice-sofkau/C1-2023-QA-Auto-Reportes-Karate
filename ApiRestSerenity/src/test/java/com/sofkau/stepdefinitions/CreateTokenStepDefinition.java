package com.sofkau.stepdefinitions;

import com.sofkau.models.Auth;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.BookingResources.BOOKING_BASE_URL;
import static com.sofkau.utils.BookingResources.BOOKING_RESOURCES;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class CreateTokenStepDefinition extends ApiSetUp {

    private Auth auth = new Auth();
    private static final Logger LOGGER = Logger.getLogger(CreateTokenStepDefinition.class.getName());

    @Given("I am in the Booking API")
    public void iAmInTheBookingAPI(){
        try {
            setUp(BOOKING_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + BOOKING_BASE_URL.getValue());

        } catch (Exception e) {

            LOGGER.error("Error al iniciar la automatizacion : Detalles: "+ e.getMessage());
        }

    }

    @When("I make a POST request to create a new authentication token with valid username {string} and password {string} credentials")
    public void iMakeAPOSTRequestToCreateANewAuthenticationTokenWithValidUsernameAndPasswordCredentials(String username, String password)  {
        try {
            LOGGER.info("Realizando peticion POST...");

            auth.setUsername(username);
            auth.setPassword(password);

            actor.attemptsTo(
                    doPost()
                            .withTheResource(BOOKING_RESOURCES.getValue())
                            .andTheRequestBody(auth)
            );


        } catch (Exception e) {

            LOGGER.error("Error durante GET request: " + e.getMessage());
        }

    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(Integer code) {

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

    @Then("the response body should contain a valid authentication token")
    public void theResponseBodyShouldContainAValidAuthenticationToken() {
        try {
            // obtener el cuerpo de la respuesta
            JSONObject jsonObject = new JSONObject(SerenityRest.lastResponse().body().asString());

            // verificar si hay un campo "token"
            if (jsonObject.has("token")) {
                String token = jsonObject.optString("token");
                assertThat(token, not(isEmptyOrNullString()));

                // si no hay un campo "token", verificar si hay un campo "reason"
            } else if (jsonObject.has("reason")) {
                    String reason = jsonObject.optString("reason");
                    assertThat(reason, is("Bad credentials"));
                } else {
                    // si no hay ni "token" ni "reason", la respuesta es inesperada
                    throw new AssertionError("Respuesta inesperada del servicio");
                }
            LOGGER.debug("Respuesta del servicio: " + SerenityRest.lastResponse().prettyPrint());


        } catch (Exception e) {
            LOGGER.error("Error: ", e);
            throw e;
        }

    }

}
