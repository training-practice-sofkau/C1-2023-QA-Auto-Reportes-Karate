package com.sofkau.stepdefinitions;


import com.sofkau.models.RegistrarUsuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnRegistrarUsuarioSuccessfulJsonResponse.returnRegistrarUsuario;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RegistrarUsuarioStepDefinition extends ApiSetUp {
    private RegistrarUsuario registrarUsuario = new RegistrarUsuario();

    @Given("que el usuario esta en la pagina de registro de la api")
    public void queElUsuarioEstaEnLaPaginaDeRegistroDeLaApi() {
        setUp(REQRES_BASE_URL.getValue());

    }
    @When("el usuario envia una peticion post con el name {string} y job {string}")
    public void elUsuarioEnviaUnaPeticionPostConElNameYJob(String name, String job) {
        registrarUsuario.setName(name);
        registrarUsuario.setJob(job);
        actor.attemptsTo(
                doPost()
                        .withTheResource(REGISTER_SUCCESSFUL_RESOURCE.getValue())
                        .andTheRequestBody(String.valueOf(registrarUsuario)));

    }
    @Then("el usuario debe ver un codeStatus {int} de respuesta y el id")
    public void elUsuarioDebeVerUnCodeStatusDeRespuestaYElId(Integer code) {
        RegistrarUsuario registrarUsuario1 = returnRegistrarUsuario().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(code)),
                seeThat("Retorna información",
                        act -> registrarUsuario1, notNullValue())
        );
    }


}
