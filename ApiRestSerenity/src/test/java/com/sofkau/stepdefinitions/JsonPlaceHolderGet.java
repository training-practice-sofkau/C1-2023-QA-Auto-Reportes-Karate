package com.sofkau.stepdefinitions;

import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.JsonPlaceHolderResources.GET_RESOURCE;
import static com.sofkau.utils.JsonPlaceHolderResources.JSON_PLACE_HOLDER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class JsonPlaceHolderGet extends ApiSetUp{
    public static Logger LOGGER = Logger.getLogger(JsonPlaceHolderGet.class);
    @Given("the user is in the JSON place holder page")
    public void theUserIsInTheJSONPlaceHolderPage() {
        setUp(JSON_PLACE_HOLDER.getValue());
    }

    @When("the user sends the {int} that he wants to get")
    public void theUserSendsTheThatHeWantsToGet(Integer intUno) {
        actor.attemptsTo(
                doGet().withTheResource(GET_RESOURCE.getValue())
                        .andWithTheNumber(intUno + "")
        );
    }

    @Then("the user sees a status {int} and the post he wants with the id {int}")
    public void theUserSeesAStatusAndThePostHeWantsWithTheId(Integer intUno, Integer intDos) {
        Response actualResponse = returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(intUno)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
        if (intUno == 200)
            actor.attemptsTo(
                    Ensure.that(actualResponse.getId()).isEqualTo(intDos)
            );
        LOGGER.info("| Esperado | Obtenido | Valor |");
        status(intUno);
        id(intDos, actualResponse);
    }
    private void id(Integer intDos, Response actualResponse) {
        if(actualResponse.getId()== intDos || actualResponse.getId()==null)
            if (actualResponse.getId()==null)
                LOGGER.info("| null | "+ actualResponse.getId()+" | cumple |");
            else
                LOGGER.info("| "+ intDos +" | "+ actualResponse.getId()+" | cumple |");
        else
            LOGGER.info("| "+ intDos +" | "+ actualResponse.getId()+" | no cumple |");
    }
    private void status(Integer intUno) {
        if(SerenityRest.lastResponse().statusCode()== intUno)
            LOGGER.info("| "+ intUno +" | "+SerenityRest.lastResponse().statusCode()+" | cumple |");
        else
            LOGGER.info("| "+ intUno +" | "+SerenityRest.lastResponse().statusCode()+" | no cumple |");
    }
}
