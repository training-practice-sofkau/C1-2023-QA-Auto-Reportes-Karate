package com.sofkau.stepdefinitions;

import com.sofkau.models.Usuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

import static com.sofkau.tasks.RealizarUnGet.realizarUnGet;
import static com.sofkau.tasks.RealizarUnPost.realizarUnPost;
import static com.sofkau.utils.CovidTest.COVID_BASE_URL;
import static com.sofkau.utils.ReqresInCreate.REQRES_BASE_PAHT;
import static com.sofkau.utils.ReqresInCreate.REQRES_BASE_URL;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CrearUsuarioStepDefinition extends ApiSetUp {
    Usuario usuario  = new Usuario();
    public static Logger LOGGER = Logger.getLogger(CovidPporDiaStepDefinition.class);
    @Given("el usuario esta esta en la API")
    public void el_usuario_esta_esta_en_la_API() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("hace una peticio POST con {string} y {string}")
    public void hace_una_peticio_POST_con_y(String nombre, String trabajo) {
            usuario.setName(nombre);
            usuario.setJob(trabajo);
        try {
            actor.attemptsTo(
                   realizarUnPost().
                           withTheResource(REQRES_BASE_PAHT.getValue())
                           .andTheRequestBody(usuario)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("puede ver la informacion de usuario y {int}")
    public void puede_ver_la_informacion_de_usuario_y(Integer status) {
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> Objects.equals(lastResponse(), status))
        );
    }

}
