package com.sofkau.stepdefinitions;

import com.sofkau.models.Data;
import com.sofkau.models.ResponseGet;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnGetReqresResponse.returnGetReqresResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresGetResources.REQRES_BASE_URL_GET;
import static com.sofkau.utils.ReqresPutResources.RESOURCE_PUT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetStepDefinition extends ApiSetUp {
    private Data data = new Data();
    public static Logger LOGGER = Logger.getLogger(GetStepDefinition.class);

    @Given("the admin is in the list page")
    public void theAdminIsInTheListPage() {
        try{
            setUp(REQRES_BASE_URL_GET.getValue());
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @When("send a list request with the {int}")
    public void sendAListRequestWithThe(Integer id) {
        try{
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_PUT.getValue() + id)
            );
            LOGGER.info(SerenityRest.lastResponse().asString());
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @Then("see a response with property {string} the {string} in addition to the {int}")
    public void seeAResponseWithPropertyTheInAdditionToThe(String lastName, String avatar, Integer statusCode) {
        try{
            ResponseGet actualResponse= returnGetReqresResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información del apellido: ",
                            act -> actualResponse.getData().getLast_name(), containsString(lastName)),
                    seeThat("Retorna información del avatar: ",
                            act -> actualResponse.getData().getAvatar(), containsString(avatar)),
                    seeThat("Retorna información: ",
                            act -> actualResponse.getData().getAvatar(), notNullValue())
            );

        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }
}