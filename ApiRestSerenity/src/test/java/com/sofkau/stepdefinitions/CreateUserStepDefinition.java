package com.sofkau.stepdefinitions;

import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class CreateUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(CreateUserStepDefinition.class);
    private Response response;
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    @Given("the user is in the reqres web page")
    public void theUserIsInTheReqresWebPage() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("Fallo en la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends a request with the user's name {string} and job {string}")
    public void theUserSendsARequestWithTheUserSNameAndJob(String name, String job) {

        try {
            actor.attemptsTo(
                doPost()
                        .withTheResource(RESOURCE.getValue())
                        .andTheRequestBody(setUser(name, job))
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());

        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets a status code response Created, and can see the user's id, name {string}, job {string} and creation time")
    public void theUserGetsAStatusCodeResponseCreatedAndCanSeeTheUserSIdNameJobAndCreationTime(String name, String job) throws ParseException {

        try {
            String userName = getUserName();
            String userJob = getUserJob();
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_CREATED,
                            response -> response.statusCode(HttpStatus.SC_CREATED)),
                    seeThat("El nombre del nuevo usuario es: ", act-> userName,
                            equalToIgnoringCase(name)),
                    seeThat("El trabajo del nuevo usuario es: ", act-> userJob,
                            equalToIgnoringCase(job))
            );
            LOGGER.info("Cumple!!");
        } catch (Exception e){
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends an empty request")
    public void theUserSendsAnEmptyRequest() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE.getValue())
                            .andTheRequestBody(setUser(null, null))
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets a status code Created, an can see the user's id and creation time")
    public void theUserGetsAStatusCodeCreatedAnCanSeeTheUserSIdAndCreationTime() {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_CREATED,
                            response -> response.statusCode(HttpStatus.SC_CREATED))
            );
        } catch (Exception e){
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private static User setUser(String name, String job) {
        User user = new User();
        user.setName(name);
        user.setJob(job);
        return user;
    }

    private String getUserName() throws ParseException {
        response = (Response) SerenityRest.lastResponse().body();
        responseBody = (JSONObject) parser.parse(response.getBody().asString());
        String userName = responseBody.get(ATTRIBUTE_NAME.getValue()).toString();
        return userName;
    }
    private String getUserJob() throws ParseException{
        response = (Response) SerenityRest.lastResponse().body();
        responseBody = (JSONObject) parser.parse(response.getBody().asString());
        String userJob = responseBody.get(ATTRIBUTE_JOB.getValue()).toString();
        return userJob;
    }

}
