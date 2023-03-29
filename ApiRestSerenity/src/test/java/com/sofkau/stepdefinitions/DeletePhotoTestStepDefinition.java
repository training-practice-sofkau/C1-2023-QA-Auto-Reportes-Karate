package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.ReqresResources.BASE_JSON_URL;
import static com.sofkau.utils.ReqresResources.DELETE_PHOTO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class DeletePhotoTestStepDefinition extends ApiSetUp {

    private Logger LOGGER = Logger.getLogger(DeletePhotoTestStepDefinition.class);

    @Given("el usuario esta en la pagina de jsonplaceholder")
    public void elUsuarioEstaEnLaPaginaDeJsonplaceholder() {
        setUp(BASE_JSON_URL.getValue());
    }

    @When("el usuario realiza la peticion para eliminar una foto con el id interno {int}")
    public void elUsuarioRealizaLaPeticionParaEliminarUnaFotoConElIdInterno(Integer int1) {
        try {
            actor.attemptsTo(
                    doDelete().conElRecurso(DELETE_PHOTO.getValue())
                            .yConelId(int1 + "")
            );
            LOGGER.info("Foto con id " + int1 + " eliminada");
        } catch (Exception e) {
            LOGGER.error("Error eliminando la foto: " + e.getMessage());
        }
    }

    @Then("el usuario obtendra un {int} de estado exitoso")
    public void elUsuarioObtendraUnDeEstadoExitoso(Integer int1) {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(int1))
            );
            LOGGER.info("Código de respuesta: " + int1);
        } catch (Exception e) {
            LOGGER.error("Error verificando el código de respuesta: " + e.getMessage());
        }
    }
}



