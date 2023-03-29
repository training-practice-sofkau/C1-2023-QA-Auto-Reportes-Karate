package com.sofkau.stepdefinitions;


import com.sofkau.models.RegistrarUsuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnRegistrarUsuarioSuccessfulJsonResponse.returnRegistrarUsuario;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RegistrarUsuarioStepDefinition extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(ConsultarRecursoStepDefinition.class.getName());
    private RegistrarUsuario registrarUsuario = new RegistrarUsuario();

    @Given("que el usuario esta en la pagina de registro de la api")
    public void queElUsuarioEstaEnLaPaginaDeRegistroDeLaApi() {
        setUp(REQRES_BASE_URL.getValue());

    }
    @When("el usuario envia una peticion post con el name {string} y job {string}")
    public void elUsuarioEnviaUnaPeticionPostConElNameYJob(String name, String job) {
       try {
           registrarUsuario.setName(name);
           registrarUsuario.setJob(job);
           actor.attemptsTo(
                   doPost()
                           .withTheResource(REGISTER_SUCCESSFUL_RESOURCE.getValue())
                           .andTheRequestBody(registrarUsuario)
           );
           LOGGER.info("Peticion enviada ");
       }catch (Exception e){
           LOGGER.error("****** Se tiene el siguiente error -->: ", e);
           throw e;
       }
    }
    @Then("el usuario debe ver un codeStatus {int} de respuesta y el id {int}")
    public void elUsuarioDebeVerUnCodeStatusDeRespuestaYElId(Integer code, Integer id) {
        try {
            if(code ==201) {
                RegistrarUsuario registrarUsuario1 = returnRegistrarUsuario().answeredBy(actor);
                actor.should(
                        seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_CREATED,
                                response -> response.statusCode(code)),
                        seeThat("Retorna información",
                                act -> registrarUsuario1, notNullValue())
                );
            }
            if (code == 400){
                LOGGER.info("Peticion no valida");
            }
            LOGGER.info("Respuesta status code: " + code);
        } catch (Exception e) {
            LOGGER.error("****** Se tiene el siguiente error -->: ", e);
            throw e;
        }
    }

}
