package com.sofkau.stepdefinitions;


import com.sofkau.models.Tarea;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class NuevaTareaStepDefinitions extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(NuevaTareaStepDefinitions.class);
    private Tarea tarea = new Tarea();

    @Given("I have the service endpoint")
    public void iHaveTheServiceEndpoint() {
        try {
            LOGGER.info("Inicio de la Automatizacion");
            setUp(JSONPLACEHOLDER_BASE_URL.getValue());
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("the user send a POST request with the task data {string},{int},{string},{string}")
    public void theUserSendAPOSTRequestWithTheTaskData(String userId, Integer id, String title, String completed) {
        setNewTarea(userId, id, title, completed);
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(TAREA_POST_RESOURCE.getValue())
                            .andTheRequestBody(tarea)
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("the user sees a successful status code and the body of the response.")
    public void theUserSeesASuccessfulStatusCodeAndTheBodyOfTheResponse() {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(201)
                                                .body("id",equalTo(tarea.getId()))
                                                .body("completed", equalTo(tarea.getCompleted())))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    private void setNewTarea(String userId, Integer id, String title, String completed) {
        tarea.setUserId(userId);
        tarea.setId(id);
        tarea.setTitle(title);
        tarea.setCompleted(completed);
    }

}
