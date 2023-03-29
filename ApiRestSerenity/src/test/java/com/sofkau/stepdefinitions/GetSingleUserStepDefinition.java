package com.sofkau.stepdefinitions;

import com.sofkau.models.User;
import com.sofkau.models.getmodel.ResponseUser;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnGetSingleUserJsonResponse.returnGetSingleUserJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static com.sofkau.utils.ReqresResources.SINGLE_USER_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetSingleUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER=Logger.getLogger(GetSingleUserStepDefinition.class);
    private User user = new User();
    @Given("the admi is on the list page")
    public void theAdmiIsOnTheListPage() {
        try {
            setUp(REQRES_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("the user makes a request with the user id {int}")
    public void theUserMakesARequestWithTheUserId(Integer id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(SINGLE_USER_RESOURCE.getValue() + id)
            );
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user should see a response containing the user id {int}, email  {string}, name {string}, last name {string} and status code {int}")
    public void theUserShouldSeeAResponseContainingTheUserIdEmailNameLastNameAndStatusCode(Integer id, String email, String name, String lastName, Integer statusCode) {
        try {
            ResponseUser actualResponseUser = returnGetSingleUserJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna id",
                            act -> actualResponseUser.getData().getId(), equalTo(id)),
                    seeThat("Retorna email",
                            act -> actualResponseUser.getData().getEmail(), containsString(email)),
                    seeThat("Retorna nombre",
                            act -> actualResponseUser.getData().getFirst_name(), containsString(name)),
                    seeThat("Retorna apellido",
                            act -> actualResponseUser.getData().getLast_name(), containsString(lastName))
            );
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

}
