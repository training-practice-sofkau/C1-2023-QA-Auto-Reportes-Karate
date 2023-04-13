package com.sofkau.stepdefinitions;


import com.sofkau.models.ResponseUpdateUser;
import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;


import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import static org.hamcrest.CoreMatchers.notNullValue;
import static com.sofkau.questions.ReturnUpdateJsonResponse.returnUpdateJsonResponse;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL_PUT;
import static com.sofkau.utils.ReqresResources.RESOURCES_PUT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
public class UpdateUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(UpdateUserStepDefinition.class));
    ResponseUpdateUser actualResponseUpdateUser=new ResponseUpdateUser();

    private User user= new User();
    @Given("que estoy apuntando con un endpoint a la api de reqres.in")
    public void queEstoyApuntandoConUnEndpointALaApiDeReqresIn() {
        setUp(REQRES_BASE_URL_PUT.getValue());
        LOGGER.info("Se inicia la automatizacion");

    }

    @When("envio la peticion put con el {string} de el usuario el {string},{int} y {string}")
    public void envioLaPeticionPutConElDeElUsuarioElY(String id, String nombre, Integer edad, String trabajo) {
        try{
            user.setNombre(nombre);
            user.setEdad(edad);
            user.setTrabajo(trabajo);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(RESOURCES_PUT.getValue()+id)
                            .andTheRequestBody(user)
            );
        }catch (Exception e){
            LOGGER.info("Fallo enviando la informacion");
            LOGGER.warning(e.getMessage());
        }

    }

    @Then("se recibe un {int} de respuesta")
    public void seRecibeUnDeRespuesta(Integer code) {
        try {

            actualResponseUpdateUser=returnUpdateJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es "+ HttpStatus.SC_OK,
                            responseUpdate-> responseUpdate.statusCode(code))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando el codigo");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }

    }

    @Then("la informacion actualizada del usuario con la fecha de actualizacion")
    public void laInformacionActualizadaDelUsuarioConLaFechaDeActualizacion() {
        try {
            actor.should(
                    seeThat("Retorna informacion",
                            act-> actualResponseUpdateUser, notNullValue()),
                    seeThat("Se recibe el nombre del usuario actualizado",
                            nom-> user.getNombre(), equalTo(actualResponseUpdateUser.getNombre())),
                    seeThat("Se recibe la edad del usuario actualizado",
                            ed-> user.getEdad(), equalTo(actualResponseUpdateUser.getEdad())),
                    seeThat("Se recibe el trabajo del usuario actualizado",
                            tra-> user.getTrabajo(), equalTo(actualResponseUpdateUser.getTrabajo())),
                    seeThat("La fecha de actualizacion no es vacia",
                            fecha-> actualResponseUpdateUser.getUpdatedAt(),notNullValue())
            );
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
        LOGGER.info("Fin de la automatizacion");
    }
}
