package com.sofkau.stepdefinitions;

import com.sofkau.models.Tarea;
import com.sofkau.models.UpdateTareaResponse;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.remote.Response;

import static com.sofkau.questions.ReturnNewTodoResponse.returnNewTodoResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.ReqresResources.JSONPLACEHOLDER_BASE_URL;
import static com.sofkau.utils.ReqresResources.TAREA_POST_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UpdateTareaStepDefinitions extends ApiSetUp {


    public static Logger LOGGER = Logger.getLogger(NuevaTareaStepDefinitions.class);
    private Tarea tarea = new Tarea();
    @Given("I have the service endpoint and the task id {int}")
    public void iHaveTheServiceEndpointAndTheTaskId(Integer id) {
        try {
            tarea.setId(id);
            LOGGER.info("Inicio de la Automatizacion");
            setUp(JSONPLACEHOLDER_BASE_URL.getValue());
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("I send a PUT request with the task data that i want to update {string},{string},{string}")
    public void iSendAPUTRequestWithTheTaskDataThatIWantToUpdate(String userId, String title, String completed) {
        try {
            updateTarea(userId, title, completed);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(TAREA_POST_RESOURCE.getValue()+"/"+tarea.getId())
                            .andTheRequestBody(tarea)
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("I should see a successful status code and the response body updated.")
    public void iShouldSeeASuccessfulStatusCodeAndTheResponseBodyUpdated() {
        try {
            UpdateTareaResponse actualResponse = returnNewTodoResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(200)),
                    seeThat("Retorna info",
                            act -> actualResponse, notNullValue()),
                    seeThat("El estado de la tarea actualizado",
                            act -> actualResponse.getCompleted(), equalTo(tarea.getCompleted()))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void updateTarea(String userId,String title, String completed) {
        tarea.setUserId(userId);
        tarea.setTitle(title);
        tarea.setCompleted(completed);
    }
}
