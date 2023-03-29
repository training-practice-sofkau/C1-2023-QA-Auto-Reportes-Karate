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

import static com.sofkau.tasks.ReadInfo.readInfo;
import static com.sofkau.utils.Constants.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class ReadStepDef {
    private final Logger log = LoggerFactory.getLogger(ReadStepDef.class);
    private Actor actor;

    @Given("I have access to the REQRES API server")
    public void iHaveAccessToTheREQRESAPIServer() {
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

    @When("I try to read user {string} info")
    public void iTryToReadUserIDInfo(String id) {
        try {
            log.info("Running step");
            actor.attemptsTo(
                    readInfo().ofResource(String.format(RESOURCE.getValue(), id))
            );
        } catch (Exception e) {
            log.error("Wrong step provided");
            log.error(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("I will see a status code {int}")
    public void iWillSeeAStatusCode(Integer code) {
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

    @And("I will receive a JSON {string} with user {string} info")
    public void iWillReceiveAJSONWithUserInfo(String json, String id) {
        try {
            if (json.equals("not empty")) {
                actor.should(
                        seeThatResponse("User info",
                                response -> response.body("data.id", notNullValue(Integer.class)))
                );
                log.info("Second assert passed");
            } else if (json.equals("empty")) {
                actor.should(
                        seeThatResponse("User created",
                                response -> response.body(equalTo("{}")))
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