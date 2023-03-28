package com.sofkau.stepdefinitions;


import com.sofkau.models.ResponseLogin;
import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;



import static com.sofkau.questions.ReturnPostLoginJsonResponse.returnPostLoginJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.LOGIN_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;


public class PostLoginStepDefinition extends ApiSetUp {
    public static Logger LOGGER=Logger.getLogger(PostLoginStepDefinition.class);
    private User user = new User();
    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        try {
            setUp(REQRES_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user send a login request with the email {string} and the password {string}")
    public void theUserSendALoginRequestWithTheEmailAndThePassword(String email, String password) {
        try {
            user.setEmail(email);
            user.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(LOGIN_RESOURCE.getValue())
                            .andTheRequestBody(user)
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("user then sees a response with a status code {int} and a token")
    public void userThenSeesAResponseWithAStatusCodeAndAToken(Integer statusCode) {
        try {
            ResponseLogin actualResponseLogin = returnPostLoginJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponseLogin, notNullValue())
            );
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


}
