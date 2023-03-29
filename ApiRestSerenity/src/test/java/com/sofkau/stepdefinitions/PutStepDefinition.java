package com.sofkau.stepdefinitions;


import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnPutReqresResponse.returnPutReqresResponse;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.ReqresPutResources.REQRES_BASE_URL_PUT;
import static com.sofkau.utils.ReqresPutResources.RESOURCE_PUT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PutStepDefinition extends ApiSetUp {
    private User user = new User();
    public static Logger LOGGER = Logger.getLogger(PutStepDefinition.class);

    @Given("the admin is in the update page")
    public void theAdminIsInTheUpdatePage() {
        try{
            setUp(REQRES_BASE_URL_PUT.getValue());
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @When("send a update request with the {int} the {string} and the {string}")
    public void sendAUpdateRequestWithTheTheAndThe(Integer id, String firstName, String email) {
        try{
            user.setFirstName(firstName);
            user.setEmail(email);
            actor.attemptsTo(
                    doPut().withTheResource(RESOURCE_PUT.getValue() + id)
                           .andTheRequestBody(user)
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @Then("see a status {int} response code with update date and the {string}")
    public void seeAStatusResponseCodeWithUpdateDateAndThe(Integer statusCode, String firstName) {
        try{
            User actualResponse= returnPutReqresResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponse.getUpdatedAt(), notNullValue()),

                    seeThat("Retorna primer nombre",
                            act -> actualResponse.getFirstName(), containsString(firstName))
            );
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }
}