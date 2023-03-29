package com.sofkau.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.CreateUser.createUser;
import static com.sofkau.utils.Constants.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class CreateStepDef {
    private final Logger log = LoggerFactory.getLogger(CreateStepDef.class);
    private Actor actor;

    @Given("I have access to REQRES API server")
    public void iHaveAccessToREQRESAPIServer() {
        try {
            log.info("Init scenario");
            actor = Actor.named(ACTOR.getValue())
                    .whoCan(CallAnApi
                            .at(REQRES_BASE_URL.getValue()));
        } catch (Exception e) {
            log.error("Wrong Setup provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @When("I try to create a new user with {string} JSON")
    public void iTryToCreateANewUserWithJSON(String json) {
        try {
            if (json.equals("valid")) {
                log.info("Running step");
                actor.attemptsTo(
                        createUser()
                                .inTheResource(String.format(RESOURCE.getValue(), ""))
                                .andTheBody(String.format(USER_SCHEMATIC.getValue(), NAME_1.getValue(), ",",
                                                                                     JOB_1.getValue()))
                );
            } else if (json.equals("invalid")) {
                log.info("Running step");
                actor.attemptsTo(
                        createUser()
                                .inTheResource(String.format(RESOURCE.getValue(), ""))
                                .andTheBody(String.format(USER_SCHEMATIC.getValue(), NAME_2.getValue(),"",
                                                                                     JOB_2.getValue()))
                );
            } else {
                log.error("Wrong step provided");
                Assertions.fail();
            }
        } catch (Exception e) {
            log.error("Wrong step provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("I will see the status code {int}")
    public void iWillSeeTheStatusCode(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Status code",
                            response -> response.statusCode(code))
            );
            log.info("First assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @And("I will receive a user ID {string}")
    public void iWillReceiveAUserID(String id) {
        try {
            if (id.equals("not null")) {
                actor.should(
                        seeThatResponse("User created",
                                response -> response.body("id", notNullValue(Integer.class)))
                );
                log.info("Second assert passed");
            } else if (id.equals("null")) {
                actor.should(
                        seeThatResponse("User created",
                                response -> response.body("id", not(equalTo(notNullValue()))))
                );
                log.info("Second assert passed");
            } else {
                log.error("Test failed");
                Assertions.fail();
            }
        } catch (Exception e) {
            log.error("Test failed");
            log.error(e.getMessage());
            Assertions.fail();
        } finally {
            log.info("Test completed");
        }
    }
}